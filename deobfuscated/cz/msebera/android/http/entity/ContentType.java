package cz.msebera.android.http.entity;

import cz.msebera.android.http.Consts;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HeaderElement;
import cz.msebera.android.http.HttpEntity;
import cz.msebera.android.http.NameValuePair;
import cz.msebera.android.http.message.BasicHeaderValueFormatter;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;
import cz.msebera.android.http.mime.TextUtils;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Locale;

public final class ContentType
  implements Serializable
{
  public static final ContentType APPLICATION_ATOM_XML = create("application/atom+xml", Consts.ISO_8859_1);
  public static final ContentType APPLICATION_FORM_URLENCODED = create("application/x-www-form-urlencoded", Consts.ISO_8859_1);
  public static final ContentType APPLICATION_JSON = create("application/json", Consts.UTF_8);
  public static final ContentType APPLICATION_OCTET_STREAM = create("application/octet-stream", null);
  public static final ContentType APPLICATION_SVG_XML = create("application/svg+xml", Consts.ISO_8859_1);
  public static final ContentType APPLICATION_XHTML_XML = create("application/xhtml+xml", Consts.ISO_8859_1);
  public static final ContentType APPLICATION_XML = create("application/xml", Consts.ISO_8859_1);
  public static final ContentType DEFAULT_BINARY = APPLICATION_OCTET_STREAM;
  public static final ContentType DEFAULT_TEXT;
  public static final ContentType MULTIPART_FORM_DATA = create("multipart/form-data", Consts.ISO_8859_1);
  public static final ContentType TEXT_HTML = create("text/html", Consts.ISO_8859_1);
  public static final ContentType TEXT_PLAIN = create("text/plain", Consts.ISO_8859_1);
  public static final ContentType TEXT_XML = create("text/xml", Consts.ISO_8859_1);
  public static final ContentType WILDCARD = create("*/*", null);
  private final Charset charset;
  private final String mimeType;
  private final NameValuePair[] params;
  
  static
  {
    DEFAULT_TEXT = TEXT_PLAIN;
  }
  
  ContentType(String paramString, Charset paramCharset)
  {
    mimeType = paramString;
    charset = paramCharset;
    params = null;
  }
  
  ContentType(String paramString, Charset paramCharset, NameValuePair[] paramArrayOfNameValuePair)
  {
    mimeType = paramString;
    charset = paramCharset;
    params = paramArrayOfNameValuePair;
  }
  
  private static ContentType create(HeaderElement paramHeaderElement, boolean paramBoolean)
  {
    return create(paramHeaderElement.getName(), paramHeaderElement.getParameters(), paramBoolean);
  }
  
  public static ContentType create(String paramString, Charset paramCharset)
  {
    Args.notBlank(paramString, "MIME type");
    paramString = ((String)paramString).toLowerCase(Locale.ROOT);
    Args.check(valid(paramString), "MIME type may not contain reserved characters");
    return new ContentType(paramString, paramCharset);
  }
  
  private static ContentType create(String paramString, NameValuePair[] paramArrayOfNameValuePair, boolean paramBoolean)
  {
    int j = paramArrayOfNameValuePair.length;
    int i = 0;
    while (i < j)
    {
      Object localObject = paramArrayOfNameValuePair[i];
      if (((NameValuePair)localObject).getName().equalsIgnoreCase("charset"))
      {
        localObject = ((NameValuePair)localObject).getValue();
        if (TextUtils.isBlank((CharSequence)localObject)) {
          break;
        }
        try
        {
          localObject = Charset.forName((String)localObject);
        }
        catch (UnsupportedCharsetException localUnsupportedCharsetException)
        {
          if (paramBoolean) {
            break label68;
          }
        }
        break;
        label68:
        throw localUnsupportedCharsetException;
      }
      i += 1;
    }
    Charset localCharset = null;
    if ((paramArrayOfNameValuePair == null) || (paramArrayOfNameValuePair.length <= 0)) {
      paramArrayOfNameValuePair = null;
    }
    return new ContentType(paramString, localCharset, paramArrayOfNameValuePair);
  }
  
  public static ContentType get(HttpEntity paramHttpEntity)
  {
    if (paramHttpEntity == null) {
      return null;
    }
    paramHttpEntity = paramHttpEntity.getContentType();
    if (paramHttpEntity != null)
    {
      paramHttpEntity = paramHttpEntity.getElements();
      if (paramHttpEntity.length > 0) {
        return create(paramHttpEntity[0], true);
      }
    }
    return null;
  }
  
  private static boolean valid(String paramString)
  {
    int i = 0;
    while (i < paramString.length())
    {
      int j = paramString.charAt(i);
      if (j != 34)
      {
        if (j == 44) {
          break label47;
        }
        if (j == 59) {
          return false;
        }
        i += 1;
      }
      else
      {
        return false;
      }
    }
    return true;
    label47:
    return false;
  }
  
  public Charset getCharset()
  {
    return charset;
  }
  
  public String getMimeType()
  {
    return mimeType;
  }
  
  public String toString()
  {
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(64);
    localCharArrayBuffer.append(mimeType);
    if (params != null)
    {
      localCharArrayBuffer.append("; ");
      BasicHeaderValueFormatter.INSTANCE.formatParameters(localCharArrayBuffer, params, false);
    }
    else if (charset != null)
    {
      localCharArrayBuffer.append("; charset=");
      localCharArrayBuffer.append(charset.name());
    }
    return localCharArrayBuffer.toString();
  }
}
