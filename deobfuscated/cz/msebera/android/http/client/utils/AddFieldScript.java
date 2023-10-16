package cz.msebera.android.http.client.utils;

import cz.msebera.android.http.client.ssl.URLEncodedUtils;
import cz.msebera.android.http.entity.ByteArrayEntity;
import cz.msebera.android.http.entity.ContentType;
import java.nio.charset.Charset;

public class AddFieldScript
  extends ByteArrayEntity
{
  public AddFieldScript(Iterable paramIterable, Charset paramCharset)
  {
    super(URLEncodedUtils.format(paramIterable, localCharset), ContentType.create("application/x-www-form-urlencoded", paramCharset));
  }
}
