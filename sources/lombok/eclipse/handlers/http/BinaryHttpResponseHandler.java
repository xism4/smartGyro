package lombok.eclipse.handlers.http;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.StatusLine;
import cz.msebera.android.http.client.HttpResponseException;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public abstract class BinaryHttpResponseHandler extends AsyncHttpResponseHandler {
    private String[] mAllowedContentTypes = {"application/octet-stream", "image/jpeg", "image/png", "image/gif"};

    public BinaryHttpResponseHandler(String[] strArr) {
        if (strArr != null) {
            this.mAllowedContentTypes = strArr;
        } else {
            AsyncHttpClient.log.e("BinaryHttpRH", "Constructor passed allowedContentTypes was null !");
        }
    }

    public String[] getAllowedContentTypes() {
        return this.mAllowedContentTypes;
    }

    public final void sendResponseMessage(HttpResponse httpResponse) {
        StatusLine $r3 = httpResponse.getStatusLine();
        Header[] $r4 = httpResponse.getHeaders("Content-Type");
        if ($r4.length != 1) {
            sendFailureMessage($r3.getStatusCode(), httpResponse.getAllHeaders(), (byte[]) null, new HttpResponseException($r3.getStatusCode(), "None, or more than one, Content-Type Header found!"));
            return;
        }
        Header $r6 = $r4[0];
        boolean $z0 = false;
        for (String $r8 : getAllowedContentTypes()) {
            try {
                if (Pattern.matches($r8, $r6.getValue())) {
                    $z0 = true;
                }
            } catch (PatternSyntaxException $r10) {
                AsyncHttpClient.log.e("BinaryHttpRH", "Given pattern is not valid: " + $r8, $r10);
            }
        }
        if (!$z0) {
            int $i0 = $r3.getStatusCode();
            Header[] $r42 = httpResponse.getAllHeaders();
            int $i1 = $r3.getStatusCode();
            StringBuilder sb = new StringBuilder();
            sb.append("Content-Type (");
            sb.append($r6.getValue());
            sb.append(") not allowed!");
            sendFailureMessage($i0, $r42, (byte[]) null, new HttpResponseException($i1, sb.toString()));
            return;
        }
        super.sendResponseMessage(httpResponse);
    }
}
