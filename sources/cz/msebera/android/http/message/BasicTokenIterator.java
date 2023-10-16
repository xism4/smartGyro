package cz.msebera.android.http.message;

import cz.msebera.android.http.HeaderIterator;
import cz.msebera.android.http.ParseException;
import cz.msebera.android.http.TokenIterator;
import cz.msebera.android.http.mime.Args;
import java.util.NoSuchElementException;

public class BasicTokenIterator implements TokenIterator {
    protected String currentHeader;
    protected String currentToken;
    protected final HeaderIterator headerIt;
    protected int searchPos = findNext(-1);

    public BasicTokenIterator(HeaderIterator $r1) {
        Args.notNull($r1, "Header iterator");
        this.headerIt = $r1;
    }

    /* access modifiers changed from: protected */
    public String createToken(String str, int i, int i2) {
        return str.substring(i, i2);
    }

    /* access modifiers changed from: protected */
    public int findNext(int $i0) {
        int $i02;
        String $r1;
        int $i1 = -1;
        if ($i0 >= 0) {
            $i02 = findTokenSeparator($i0);
        } else if (!this.headerIt.hasNext()) {
            return -1;
        } else {
            this.currentHeader = this.headerIt.nextHeader().getValue();
            $i02 = 0;
        }
        int $i03 = findTokenStart($i02);
        if ($i03 < 0) {
            $r1 = null;
        } else {
            int $i2 = findTokenEnd($i03);
            $i1 = $i2;
            $r1 = createToken(this.currentHeader, $i03, $i2);
        }
        this.currentToken = $r1;
        return $i1;
    }

    /* access modifiers changed from: protected */
    public int findTokenEnd(int $i0) {
        Args.notNegative($i0, "Search position");
        int $i1 = this.currentHeader.length();
        do {
            $i0++;
            if ($i0 >= $i1 || !isTokenChar(this.currentHeader.charAt($i0))) {
                return $i0;
            }
            $i0++;
            break;
        } while (!isTokenChar(this.currentHeader.charAt($i0)));
        return $i0;
    }

    /* access modifiers changed from: protected */
    public int findTokenSeparator(int $i0) {
        Args.notNegative($i0, "Search position");
        int $i1 = this.currentHeader.length();
        boolean $z0 = false;
        while (!$z0 && $i0 < $i1) {
            char $c2 = this.currentHeader.charAt($i0);
            if (isTokenSeparator($c2)) {
                $z0 = true;
            } else if (isWhitespace($c2)) {
                $i0++;
            } else if (isTokenChar($c2)) {
                throw new ParseException("Tokens without separator (pos " + $i0 + "): " + this.currentHeader);
            } else {
                throw new ParseException("Invalid character after token (pos " + $i0 + "): " + this.currentHeader);
            }
        }
        return $i0;
    }

    /* access modifiers changed from: protected */
    public int findTokenStart(int $i0) {
        Args.notNegative($i0, "Search position");
        boolean $z0 = false;
        while (!$z0) {
            String $r1 = this.currentHeader;
            if ($r1 == null) {
                break;
            }
            int $i1 = $r1.length();
            while (!$z0 && $i0 < $i1) {
                char $c2 = this.currentHeader.charAt($i0);
                if (isTokenSeparator($c2) || isWhitespace($c2)) {
                    $i0++;
                } else if (isTokenChar(this.currentHeader.charAt($i0))) {
                    $z0 = true;
                } else {
                    throw new ParseException("Invalid character before token (pos " + $i0 + "): " + this.currentHeader);
                }
            }
            if (!$z0) {
                if (this.headerIt.hasNext()) {
                    this.currentHeader = this.headerIt.nextHeader().getValue();
                    $i0 = 0;
                } else {
                    this.currentHeader = null;
                }
            }
        }
        if ($z0) {
            return $i0;
        }
        return -1;
    }

    public boolean hasNext() {
        return this.currentToken != null;
    }

    /* access modifiers changed from: protected */
    public boolean isHttpSeparator(char c) {
        return " ,;=()<>@:\\\"/[]?{}\t".indexOf(c) >= 0;
    }

    /* access modifiers changed from: protected */
    public boolean isTokenChar(char c) {
        if (Character.isLetterOrDigit(c)) {
            return true;
        }
        return !Character.isISOControl(c) && !isHttpSeparator(c);
    }

    /* access modifiers changed from: protected */
    public boolean isTokenSeparator(char c) {
        return c == ',';
    }

    /* access modifiers changed from: protected */
    public boolean isWhitespace(char c) {
        return c == 9 || Character.isSpaceChar(c);
    }

    public final Object next() {
        return nextToken();
    }

    public String nextToken() {
        String $r1 = this.currentToken;
        if ($r1 != null) {
            this.searchPos = findNext(this.searchPos);
            return $r1;
        }
        throw new NoSuchElementException("Iteration already finished.");
    }

    public final void remove() {
        throw new UnsupportedOperationException("Removing tokens is not supported.");
    }
}
