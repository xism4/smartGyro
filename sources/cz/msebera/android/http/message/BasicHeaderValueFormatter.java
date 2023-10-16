package cz.msebera.android.http.message;

import cz.msebera.android.http.HeaderElement;
import cz.msebera.android.http.NameValuePair;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;

public class BasicHeaderValueFormatter implements HeaderValueFormatter {
    @Deprecated
    public static final BasicHeaderValueFormatter DEFAULT = new BasicHeaderValueFormatter();
    public static final BasicHeaderValueFormatter INSTANCE = new BasicHeaderValueFormatter();

    /* access modifiers changed from: protected */
    public void doFormatValue(CharArrayBuffer charArrayBuffer, String str, boolean $z0) {
        if (!$z0) {
            for (int $i1 = 0; $i1 < str.length() && !$z0; $i1++) {
                $z0 = isSeparator(str.charAt($i1));
            }
        }
        if ($z0) {
            charArrayBuffer.append('\"');
        }
        for (int $i0 = 0; $i0 < str.length(); $i0++) {
            char $c3 = str.charAt($i0);
            if (isUnsafe($c3)) {
                charArrayBuffer.append('\\');
            }
            charArrayBuffer.append($c3);
        }
        if ($z0) {
            charArrayBuffer.append('\"');
        }
    }

    /* access modifiers changed from: protected */
    public int estimateHeaderElementLen(HeaderElement headerElement) {
        if (headerElement == null) {
            return 0;
        }
        int $i1 = headerElement.getName().length();
        int $i2 = $i1;
        String $r2 = headerElement.getValue();
        if ($r2 != null) {
            $i2 = $i1 + $r2.length() + 3;
        }
        int $i12 = headerElement.getParameterCount();
        if ($i12 > 0) {
            for (int $i0 = 0; $i0 < $i12; $i0++) {
                $i2 += estimateNameValuePairLen(headerElement.getParameter($i0)) + 2;
            }
        }
        return $i2;
    }

    /* access modifiers changed from: protected */
    public int estimateNameValuePairLen(NameValuePair nameValuePair) {
        if (nameValuePair == null) {
            return 0;
        }
        int $i0 = nameValuePair.getName().length();
        String $r2 = nameValuePair.getValue();
        return $r2 != null ? $i0 + $r2.length() + 3 : $i0;
    }

    /* access modifiers changed from: protected */
    public int estimateParametersLen(NameValuePair[] nameValuePairArr) {
        if (nameValuePairArr == null || nameValuePairArr.length < 1) {
            return 0;
        }
        int $i2 = (nameValuePairArr.length - 1) * 2;
        for (NameValuePair $r2 : nameValuePairArr) {
            $i2 += estimateNameValuePairLen($r2);
        }
        return $i2;
    }

    public CharArrayBuffer formatHeaderElement(CharArrayBuffer $r2, HeaderElement headerElement, boolean z) {
        Args.notNull(headerElement, "Header element");
        int $i0 = estimateHeaderElementLen(headerElement);
        if ($r2 == null) {
            $r2 = new CharArrayBuffer($i0);
        } else {
            $r2.ensureCapacity($i0);
        }
        $r2.append(headerElement.getName());
        String $r3 = headerElement.getValue();
        if ($r3 != null) {
            $r2.append('=');
            doFormatValue($r2, $r3, z);
        }
        int $i02 = headerElement.getParameterCount();
        if ($i02 > 0) {
            for (int $i1 = 0; $i1 < $i02; $i1++) {
                $r2.append("; ");
                formatNameValuePair($r2, headerElement.getParameter($i1), z);
            }
        }
        return $r2;
    }

    public CharArrayBuffer formatNameValuePair(CharArrayBuffer $r1, NameValuePair nameValuePair, boolean z) {
        Args.notNull(nameValuePair, "Name / value pair");
        int $i0 = estimateNameValuePairLen(nameValuePair);
        if ($r1 == null) {
            $r1 = new CharArrayBuffer($i0);
        } else {
            $r1.ensureCapacity($i0);
        }
        $r1.append(nameValuePair.getName());
        String $r3 = nameValuePair.getValue();
        if ($r3 != null) {
            $r1.append('=');
            doFormatValue($r1, $r3, z);
        }
        return $r1;
    }

    public CharArrayBuffer formatParameters(CharArrayBuffer $r2, NameValuePair[] nameValuePairArr, boolean z) {
        Args.notNull(nameValuePairArr, "Header parameter array");
        int $i0 = estimateParametersLen(nameValuePairArr);
        if ($r2 == null) {
            $r2 = new CharArrayBuffer($i0);
        } else {
            $r2.ensureCapacity($i0);
        }
        for (int $i02 = 0; $i02 < nameValuePairArr.length; $i02++) {
            if ($i02 > 0) {
                $r2.append("; ");
            }
            formatNameValuePair($r2, nameValuePairArr[$i02], z);
        }
        return $r2;
    }

    /* access modifiers changed from: protected */
    public boolean isSeparator(char c) {
        return " ;,:@()<>\\\"/[]?={}\t".indexOf(c) >= 0;
    }

    /* access modifiers changed from: protected */
    public boolean isUnsafe(char c) {
        return "\"\\".indexOf(c) >= 0;
    }
}
