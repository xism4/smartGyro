package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.HeaderElement;
import cz.msebera.android.http.NameValuePair;
import cz.msebera.android.http.message.BasicHeaderElement;
import cz.msebera.android.http.message.BasicNameValuePair;
import cz.msebera.android.http.message.TokenParser;
import cz.msebera.android.http.mime.Args;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class NetscapeDraftHeaderParser
{
  public static final NetscapeDraftHeaderParser DEFAULT = new NetscapeDraftHeaderParser();
  private static final BitSet TOKEN_DELIMS = TokenParser.INIT_BITSET(new int[] { 61, 59 });
  private static final BitSet VALUE_DELIMS = TokenParser.INIT_BITSET(new int[] { 59 });
  private final TokenParser tokenParser = TokenParser.INSTANCE;
  
  public NetscapeDraftHeaderParser() {}
  
  private NameValuePair parseNameValuePair(cz.msebera.android.http.mime.CharArrayBuffer paramCharArrayBuffer, cz.msebera.android.http.message.CharArrayBuffer paramCharArrayBuffer1)
  {
    String str = tokenParser.parseToken(paramCharArrayBuffer, paramCharArrayBuffer1, TOKEN_DELIMS);
    if (paramCharArrayBuffer1.atEnd()) {
      return new BasicNameValuePair(str, null);
    }
    int i = paramCharArrayBuffer.charAt(paramCharArrayBuffer1.getPos());
    paramCharArrayBuffer1.append(paramCharArrayBuffer1.getPos() + 1);
    if (i != 61) {
      return new BasicNameValuePair(str, null);
    }
    paramCharArrayBuffer = tokenParser.parseToken(paramCharArrayBuffer, paramCharArrayBuffer1, VALUE_DELIMS);
    if (!paramCharArrayBuffer1.atEnd()) {
      paramCharArrayBuffer1.append(paramCharArrayBuffer1.getPos() + 1);
    }
    return new BasicNameValuePair(str, paramCharArrayBuffer);
  }
  
  public HeaderElement parseHeader(cz.msebera.android.http.mime.CharArrayBuffer paramCharArrayBuffer, cz.msebera.android.http.message.CharArrayBuffer paramCharArrayBuffer1)
  {
    Args.notNull(paramCharArrayBuffer, "Char array buffer");
    Args.notNull(paramCharArrayBuffer1, "Parser cursor");
    NameValuePair localNameValuePair = parseNameValuePair(paramCharArrayBuffer, paramCharArrayBuffer1);
    ArrayList localArrayList = new ArrayList();
    while (!paramCharArrayBuffer1.atEnd()) {
      localArrayList.add(parseNameValuePair(paramCharArrayBuffer, paramCharArrayBuffer1));
    }
    return new BasicHeaderElement(localNameValuePair.getName(), localNameValuePair.getValue(), (NameValuePair[])localArrayList.toArray(new NameValuePair[localArrayList.size()]));
  }
}
