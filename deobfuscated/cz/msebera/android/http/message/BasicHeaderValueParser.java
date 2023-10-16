package cz.msebera.android.http.message;

import cz.msebera.android.http.HeaderElement;
import cz.msebera.android.http.NameValuePair;
import cz.msebera.android.http.mime.Args;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class BasicHeaderValueParser
  implements HeaderValueParser
{
  @Deprecated
  public static final BasicHeaderValueParser DEFAULT = new BasicHeaderValueParser();
  public static final BasicHeaderValueParser INSTANCE = new BasicHeaderValueParser();
  private static final BitSet TOKEN_DELIMS = TokenParser.INIT_BITSET(new int[] { 61, 59, 44 });
  private static final BitSet VALUE_DELIMS = TokenParser.INIT_BITSET(new int[] { 59, 44 });
  private final TokenParser tokenParser = TokenParser.INSTANCE;
  
  public BasicHeaderValueParser() {}
  
  public static HeaderElement[] parseElements(String paramString, HeaderValueParser paramHeaderValueParser)
  {
    Args.notNull(paramString, "Value");
    cz.msebera.android.http.mime.CharArrayBuffer localCharArrayBuffer = new cz.msebera.android.http.mime.CharArrayBuffer(paramString.length());
    localCharArrayBuffer.append(paramString);
    paramString = new CharArrayBuffer(0, paramString.length());
    if (paramHeaderValueParser == null) {
      paramHeaderValueParser = INSTANCE;
    }
    return paramHeaderValueParser.parseElements(localCharArrayBuffer, paramString);
  }
  
  protected HeaderElement createHeaderElement(String paramString1, String paramString2, NameValuePair[] paramArrayOfNameValuePair)
  {
    return new BasicHeaderElement(paramString1, paramString2, paramArrayOfNameValuePair);
  }
  
  protected NameValuePair createNameValuePair(String paramString1, String paramString2)
  {
    return new BasicNameValuePair(paramString1, paramString2);
  }
  
  public HeaderElement[] parseElements(cz.msebera.android.http.mime.CharArrayBuffer paramCharArrayBuffer, CharArrayBuffer paramCharArrayBuffer1)
  {
    Args.notNull(paramCharArrayBuffer, "Char array buffer");
    Args.notNull(paramCharArrayBuffer1, "Parser cursor");
    ArrayList localArrayList = new ArrayList();
    while (!paramCharArrayBuffer1.atEnd())
    {
      HeaderElement localHeaderElement = parseHeaderElement(paramCharArrayBuffer, paramCharArrayBuffer1);
      if ((localHeaderElement.getName().length() != 0) || (localHeaderElement.getValue() != null)) {
        localArrayList.add(localHeaderElement);
      }
    }
    return (HeaderElement[])localArrayList.toArray(new HeaderElement[localArrayList.size()]);
  }
  
  public HeaderElement parseHeaderElement(cz.msebera.android.http.mime.CharArrayBuffer paramCharArrayBuffer, CharArrayBuffer paramCharArrayBuffer1)
  {
    Args.notNull(paramCharArrayBuffer, "Char array buffer");
    Args.notNull(paramCharArrayBuffer1, "Parser cursor");
    NameValuePair localNameValuePair = parseNameValuePair(paramCharArrayBuffer, paramCharArrayBuffer1);
    if ((!paramCharArrayBuffer1.atEnd()) && (paramCharArrayBuffer.charAt(paramCharArrayBuffer1.getPos() - 1) != ',')) {
      paramCharArrayBuffer = parseParameters(paramCharArrayBuffer, paramCharArrayBuffer1);
    } else {
      paramCharArrayBuffer = null;
    }
    return createHeaderElement(localNameValuePair.getName(), localNameValuePair.getValue(), paramCharArrayBuffer);
  }
  
  public NameValuePair parseNameValuePair(cz.msebera.android.http.mime.CharArrayBuffer paramCharArrayBuffer, CharArrayBuffer paramCharArrayBuffer1)
  {
    Args.notNull(paramCharArrayBuffer, "Char array buffer");
    Args.notNull(paramCharArrayBuffer1, "Parser cursor");
    String str = tokenParser.parseToken(paramCharArrayBuffer, paramCharArrayBuffer1, TOKEN_DELIMS);
    if (paramCharArrayBuffer1.atEnd()) {
      return new BasicNameValuePair(str, null);
    }
    int i = paramCharArrayBuffer.charAt(paramCharArrayBuffer1.getPos());
    paramCharArrayBuffer1.append(paramCharArrayBuffer1.getPos() + 1);
    if (i != 61) {
      return createNameValuePair(str, null);
    }
    paramCharArrayBuffer = tokenParser.parseValue(paramCharArrayBuffer, paramCharArrayBuffer1, VALUE_DELIMS);
    if (!paramCharArrayBuffer1.atEnd()) {
      paramCharArrayBuffer1.append(paramCharArrayBuffer1.getPos() + 1);
    }
    return createNameValuePair(str, paramCharArrayBuffer);
  }
  
  public NameValuePair[] parseParameters(cz.msebera.android.http.mime.CharArrayBuffer paramCharArrayBuffer, CharArrayBuffer paramCharArrayBuffer1)
  {
    Args.notNull(paramCharArrayBuffer, "Char array buffer");
    Args.notNull(paramCharArrayBuffer1, "Parser cursor");
    tokenParser.skipWhiteSpace(paramCharArrayBuffer, paramCharArrayBuffer1);
    ArrayList localArrayList = new ArrayList();
    do
    {
      if (paramCharArrayBuffer1.atEnd()) {
        break;
      }
      localArrayList.add(parseNameValuePair(paramCharArrayBuffer, paramCharArrayBuffer1));
    } while (paramCharArrayBuffer.charAt(paramCharArrayBuffer1.getPos() - 1) != ',');
    return (NameValuePair[])localArrayList.toArray(new NameValuePair[localArrayList.size()]);
  }
}
