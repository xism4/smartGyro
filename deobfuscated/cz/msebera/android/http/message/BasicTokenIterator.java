package cz.msebera.android.http.message;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HeaderIterator;
import cz.msebera.android.http.ParseException;
import cz.msebera.android.http.TokenIterator;
import cz.msebera.android.http.mime.Args;
import java.util.NoSuchElementException;

public class BasicTokenIterator
  implements TokenIterator
{
  protected String currentHeader;
  protected String currentToken;
  protected final HeaderIterator headerIt;
  protected int searchPos;
  
  public BasicTokenIterator(HeaderIterator paramHeaderIterator)
  {
    Args.notNull(paramHeaderIterator, "Header iterator");
    headerIt = ((HeaderIterator)paramHeaderIterator);
    searchPos = findNext(-1);
  }
  
  protected String createToken(String paramString, int paramInt1, int paramInt2)
  {
    return paramString.substring(paramInt1, paramInt2);
  }
  
  protected int findNext(int paramInt)
  {
    int i = -1;
    if (paramInt < 0)
    {
      if (!headerIt.hasNext()) {
        return -1;
      }
      currentHeader = headerIt.nextHeader().getValue();
      paramInt = 0;
    }
    else
    {
      paramInt = findTokenSeparator(paramInt);
    }
    int j = findTokenStart(paramInt);
    String str;
    if (j < 0)
    {
      str = null;
      paramInt = i;
    }
    for (;;)
    {
      currentToken = str;
      return paramInt;
      i = findTokenEnd(j);
      paramInt = i;
      str = createToken(currentHeader, j, i);
    }
  }
  
  protected int findTokenEnd(int paramInt)
  {
    Args.notNegative(paramInt, "Search position");
    int i = currentHeader.length();
    do
    {
      paramInt += 1;
    } while ((paramInt < i) && (isTokenChar(currentHeader.charAt(paramInt))));
    return paramInt;
  }
  
  protected int findTokenSeparator(int paramInt)
  {
    Args.notNegative(paramInt, "Search position");
    int j = currentHeader.length();
    int i = 0;
    while ((i == 0) && (paramInt < j))
    {
      char c = currentHeader.charAt(paramInt);
      if (isTokenSeparator(c))
      {
        i = 1;
      }
      else if (isWhitespace(c))
      {
        paramInt += 1;
      }
      else
      {
        if (isTokenChar(c))
        {
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("Tokens without separator (pos ");
          localStringBuilder.append(paramInt);
          localStringBuilder.append("): ");
          localStringBuilder.append(currentHeader);
          throw new ParseException(localStringBuilder.toString());
        }
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Invalid character after token (pos ");
        localStringBuilder.append(paramInt);
        localStringBuilder.append("): ");
        localStringBuilder.append(currentHeader);
        throw new ParseException(localStringBuilder.toString());
      }
    }
    return paramInt;
  }
  
  protected int findTokenStart(int paramInt)
  {
    Args.notNegative(paramInt, "Search position");
    int j = 0;
    while (j == 0)
    {
      Object localObject = currentHeader;
      if (localObject == null) {
        break;
      }
      int m = ((String)localObject).length();
      int k = paramInt;
      int i = j;
      while ((i == 0) && (k < m))
      {
        char c = currentHeader.charAt(k);
        if ((!isTokenSeparator(c)) && (!isWhitespace(c)))
        {
          if (isTokenChar(currentHeader.charAt(k)))
          {
            i = 1;
          }
          else
          {
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append("Invalid character before token (pos ");
            ((StringBuilder)localObject).append(k);
            ((StringBuilder)localObject).append("): ");
            ((StringBuilder)localObject).append(currentHeader);
            throw new ParseException(((StringBuilder)localObject).toString());
          }
        }
        else {
          k += 1;
        }
      }
      j = i;
      paramInt = k;
      if (i == 0) {
        if (headerIt.hasNext())
        {
          currentHeader = headerIt.nextHeader().getValue();
          paramInt = 0;
          j = i;
        }
        else
        {
          currentHeader = null;
          j = i;
          paramInt = k;
        }
      }
    }
    if (j != 0) {
      return paramInt;
    }
    return -1;
  }
  
  public boolean hasNext()
  {
    return currentToken != null;
  }
  
  protected boolean isHttpSeparator(char paramChar)
  {
    return " ,;=()<>@:\\\"/[]?{}\t".indexOf(paramChar) >= 0;
  }
  
  protected boolean isTokenChar(char paramChar)
  {
    if (Character.isLetterOrDigit(paramChar)) {
      return true;
    }
    if (Character.isISOControl(paramChar)) {
      return false;
    }
    return !isHttpSeparator(paramChar);
  }
  
  protected boolean isTokenSeparator(char paramChar)
  {
    return paramChar == ',';
  }
  
  protected boolean isWhitespace(char paramChar)
  {
    return (paramChar == '\t') || (Character.isSpaceChar(paramChar));
  }
  
  public final Object next()
  {
    return nextToken();
  }
  
  public String nextToken()
  {
    String str = currentToken;
    if (str != null)
    {
      searchPos = findNext(searchPos);
      return str;
    }
    throw new NoSuchElementException("Iteration already finished.");
  }
  
  public final void remove()
  {
    throw new UnsupportedOperationException("Removing tokens is not supported.");
  }
}
