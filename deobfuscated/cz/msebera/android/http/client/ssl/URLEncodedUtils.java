package cz.msebera.android.http.client.ssl;

import cz.msebera.android.http.Consts;
import cz.msebera.android.http.HttpEntity;
import cz.msebera.android.http.NameValuePair;
import cz.msebera.android.http.entity.ContentType;
import cz.msebera.android.http.execchain.HTTP;
import cz.msebera.android.http.message.BasicNameValuePair;
import cz.msebera.android.http.message.TokenParser;
import cz.msebera.android.http.mime.Args;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class URLEncodedUtils
{
  private static final BitSet PATHSAFE;
  private static final BitSet PUNCT;
  private static final BitSet RESERVED;
  private static final BitSet UNRESERVED = new BitSet(256);
  private static final BitSet URIC;
  private static final BitSet URLENCODER;
  private static final BitSet USERINFO;
  
  static
  {
    PUNCT = new BitSet(256);
    USERINFO = new BitSet(256);
    PATHSAFE = new BitSet(256);
    URIC = new BitSet(256);
    RESERVED = new BitSet(256);
    URLENCODER = new BitSet(256);
    int i = 97;
    while (i <= 122)
    {
      UNRESERVED.set(i);
      i += 1;
    }
    i = 65;
    while (i <= 90)
    {
      UNRESERVED.set(i);
      i += 1;
    }
    i = 48;
    while (i <= 57)
    {
      UNRESERVED.set(i);
      i += 1;
    }
    UNRESERVED.set(95);
    UNRESERVED.set(45);
    UNRESERVED.set(46);
    UNRESERVED.set(42);
    URLENCODER.or(UNRESERVED);
    UNRESERVED.set(33);
    UNRESERVED.set(126);
    UNRESERVED.set(39);
    UNRESERVED.set(40);
    UNRESERVED.set(41);
    PUNCT.set(44);
    PUNCT.set(59);
    PUNCT.set(58);
    PUNCT.set(36);
    PUNCT.set(38);
    PUNCT.set(43);
    PUNCT.set(61);
    USERINFO.or(UNRESERVED);
    USERINFO.or(PUNCT);
    PATHSAFE.or(UNRESERVED);
    PATHSAFE.set(47);
    PATHSAFE.set(59);
    PATHSAFE.set(58);
    PATHSAFE.set(64);
    PATHSAFE.set(38);
    PATHSAFE.set(61);
    PATHSAFE.set(43);
    PATHSAFE.set(36);
    PATHSAFE.set(44);
    RESERVED.set(59);
    RESERVED.set(47);
    RESERVED.set(63);
    RESERVED.set(58);
    RESERVED.set(64);
    RESERVED.set(38);
    RESERVED.set(61);
    RESERVED.set(43);
    RESERVED.set(36);
    RESERVED.set(44);
    RESERVED.set(91);
    RESERVED.set(93);
    URIC.or(RESERVED);
    URIC.or(UNRESERVED);
  }
  
  private static String decodeFormFields(String paramString, Charset paramCharset)
  {
    if (paramString == null) {
      return null;
    }
    if (paramCharset == null) {
      paramCharset = Consts.UTF_8;
    }
    return urlDecode(paramString, paramCharset, true);
  }
  
  static String encPath(String paramString, Charset paramCharset)
  {
    return urlEncode(paramString, paramCharset, USERINFO, false);
  }
  
  static String encUric(String paramString, Charset paramCharset)
  {
    return urlEncode(paramString, paramCharset, URIC, false);
  }
  
  static String encUserInfo(String paramString, Charset paramCharset)
  {
    return urlEncode(paramString, paramCharset, PATHSAFE, false);
  }
  
  private static String encodeFormFields(String paramString, Charset paramCharset)
  {
    if (paramString == null) {
      return null;
    }
    if (paramCharset == null) {
      paramCharset = Consts.UTF_8;
    }
    return urlEncode(paramString, paramCharset, URLENCODER, true);
  }
  
  public static String format(Iterable paramIterable, char paramChar, Charset paramCharset)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext())
    {
      Object localObject = (NameValuePair)paramIterable.next();
      String str = encodeFormFields(((NameValuePair)localObject).getName(), paramCharset);
      localObject = encodeFormFields(((NameValuePair)localObject).getValue(), paramCharset);
      if (localStringBuilder.length() > 0) {
        localStringBuilder.append(paramChar);
      }
      localStringBuilder.append(str);
      if (localObject != null)
      {
        localStringBuilder.append("=");
        localStringBuilder.append((String)localObject);
      }
    }
    return localStringBuilder.toString();
  }
  
  public static String format(Iterable paramIterable, Charset paramCharset)
  {
    return format(paramIterable, '&', paramCharset);
  }
  
  public static List parse(HttpEntity paramHttpEntity)
  {
    Object localObject = ContentType.get(paramHttpEntity);
    if ((localObject != null) && (((ContentType)localObject).getMimeType().equalsIgnoreCase("application/x-www-form-urlencoded")))
    {
      long l = paramHttpEntity.getContentLength();
      boolean bool;
      if (l <= 2147483647L) {
        bool = true;
      } else {
        bool = false;
      }
      Args.check(bool, "HTTP entity is too large");
      if (((ContentType)localObject).getCharset() != null) {
        localObject = ((ContentType)localObject).getCharset();
      } else {
        localObject = HTTP.DEF_CONTENT_CHARSET;
      }
      paramHttpEntity = paramHttpEntity.getContent();
      if (paramHttpEntity == null) {
        return Collections.emptyList();
      }
      int i;
      if (l > 0L) {
        i = (int)l;
      } else {
        i = 1024;
      }
      try
      {
        cz.msebera.android.http.mime.CharArrayBuffer localCharArrayBuffer = new cz.msebera.android.http.mime.CharArrayBuffer(i);
        InputStreamReader localInputStreamReader = new InputStreamReader(paramHttpEntity, (Charset)localObject);
        char[] arrayOfChar = new char['?'];
        for (;;)
        {
          i = localInputStreamReader.read(arrayOfChar);
          if (i == -1) {
            break;
          }
          localCharArrayBuffer.append(arrayOfChar, 0, i);
        }
        paramHttpEntity.close();
        if (localCharArrayBuffer.length() == 0) {
          return Collections.emptyList();
        }
        return parse(localCharArrayBuffer, (Charset)localObject, new char[] { '&' });
      }
      catch (Throwable localThrowable)
      {
        paramHttpEntity.close();
        throw localThrowable;
      }
    }
    return Collections.emptyList();
  }
  
  public static List parse(cz.msebera.android.http.mime.CharArrayBuffer paramCharArrayBuffer, Charset paramCharset, char... paramVarArgs)
  {
    Args.notNull(paramCharArrayBuffer, "Char array buffer");
    TokenParser localTokenParser = TokenParser.INSTANCE;
    BitSet localBitSet = new BitSet();
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      localBitSet.set(paramVarArgs[i]);
      i += 1;
    }
    cz.msebera.android.http.message.CharArrayBuffer localCharArrayBuffer = new cz.msebera.android.http.message.CharArrayBuffer(0, paramCharArrayBuffer.length());
    ArrayList localArrayList = new ArrayList();
    while (!localCharArrayBuffer.atEnd())
    {
      localBitSet.set(61);
      String str2 = localTokenParser.parseToken(paramCharArrayBuffer, localCharArrayBuffer, localBitSet);
      String str1 = null;
      paramVarArgs = str1;
      if (!localCharArrayBuffer.atEnd())
      {
        i = paramCharArrayBuffer.charAt(localCharArrayBuffer.getPos());
        localCharArrayBuffer.append(localCharArrayBuffer.getPos() + 1);
        paramVarArgs = str1;
        if (i == 61)
        {
          localBitSet.clear(61);
          str1 = localTokenParser.parseValue(paramCharArrayBuffer, localCharArrayBuffer, localBitSet);
          paramVarArgs = str1;
          if (!localCharArrayBuffer.atEnd())
          {
            localCharArrayBuffer.append(localCharArrayBuffer.getPos() + 1);
            paramVarArgs = str1;
          }
        }
      }
      if (!str2.isEmpty()) {
        localArrayList.add(new BasicNameValuePair(decodeFormFields(str2, paramCharset), decodeFormFields(paramVarArgs, paramCharset)));
      }
    }
    return localArrayList;
  }
  
  public static List parse(String paramString, Charset paramCharset)
  {
    cz.msebera.android.http.mime.CharArrayBuffer localCharArrayBuffer = new cz.msebera.android.http.mime.CharArrayBuffer(paramString.length());
    localCharArrayBuffer.append(paramString);
    return parse(localCharArrayBuffer, paramCharset, new char[] { 38, 59 });
  }
  
  private static String urlDecode(String paramString, Charset paramCharset, boolean paramBoolean)
  {
    if (paramString == null) {
      return null;
    }
    ByteBuffer localByteBuffer = ByteBuffer.allocate(paramString.length());
    paramString = CharBuffer.wrap(paramString);
    if (paramString.hasRemaining())
    {
      int k = paramString.get();
      int i = k;
      char c2;
      int j;
      if ((k == 37) && (paramString.remaining() >= 2))
      {
        char c1 = paramString.get();
        c2 = paramString.get();
        i = Character.digit(c1, 16);
        j = Character.digit(c2, 16);
        if ((i != -1) && (j != -1))
        {
          j = (i << 4) + j;
          label103:
          b = (byte)j;
        }
        else
        {
          localByteBuffer.put((byte)37);
          localByteBuffer.put((byte)c1);
        }
      }
      for (byte b = (byte)c2;; b = 32)
      {
        localByteBuffer.put(b);
        break;
        j = i;
        if (!paramBoolean) {
          break label103;
        }
        j = i;
        if (k != 43) {
          break label103;
        }
      }
    }
    localByteBuffer.flip();
    return paramCharset.decode(localByteBuffer).toString();
  }
  
  private static String urlEncode(String paramString, Charset paramCharset, BitSet paramBitSet, boolean paramBoolean)
  {
    if (paramString == null) {
      return null;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    paramString = paramCharset.encode(paramString);
    if (paramString.hasRemaining())
    {
      int i = paramString.get() & 0xFF;
      char c1;
      if (paramBitSet.get(i)) {
        c1 = (char)i;
      }
      for (;;)
      {
        localStringBuilder.append(c1);
        break;
        if ((paramBoolean) && (i == 32))
        {
          c1 = '+';
        }
        else
        {
          localStringBuilder.append("%");
          char c2 = Character.toUpperCase(Character.forDigit(i >> 4 & 0xF, 16));
          c1 = Character.toUpperCase(Character.forDigit(i & 0xF, 16));
          localStringBuilder.append(c2);
        }
      }
    }
    return localStringBuilder.toString();
  }
}
