package cz.msebera.android.http.client.utils;

import cz.msebera.android.http.client.ssl.URLEncodedUtils;
import cz.msebera.android.http.entity.ByteArrayEntity;
import cz.msebera.android.http.entity.ContentType;
import cz.msebera.android.http.execchain.HTTP;
import java.nio.charset.Charset;

public class AddFieldScript extends ByteArrayEntity {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AddFieldScript(Iterable iterable, Charset charset) {
        super(URLEncodedUtils.format(iterable, charset != null ? charset : HTTP.DEF_CONTENT_CHARSET), ContentType.create("application/x-www-form-urlencoded", charset));
    }
}
