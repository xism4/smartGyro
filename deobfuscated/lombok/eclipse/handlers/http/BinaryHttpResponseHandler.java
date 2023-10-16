package lombok.eclipse.handlers.http;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.StatusLine;
import cz.msebera.android.http.client.HttpResponseException;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public abstract class BinaryHttpResponseHandler
  extends AsyncHttpResponseHandler
{
  private String[] mAllowedContentTypes = { "application/octet-stream", "image/jpeg", "image/png", "image/gif" };
  
  public BinaryHttpResponseHandler(String[] paramArrayOfString)
  {
    if (paramArrayOfString != null)
    {
      mAllowedContentTypes = paramArrayOfString;
      return;
    }
    AsyncHttpClient.log.e("BinaryHttpRH", "Constructor passed allowedContentTypes was null !");
  }
  
  public String[] getAllowedContentTypes()
  {
    return mAllowedContentTypes;
  }
  
  public final void sendResponseMessage(HttpResponse paramHttpResponse)
  {
    Object localObject1 = paramHttpResponse.getStatusLine();
    Object localObject2 = paramHttpResponse.getHeaders("Content-Type");
    if (localObject2.length != 1)
    {
      sendFailureMessage(((StatusLine)localObject1).getStatusCode(), paramHttpResponse.getAllHeaders(), null, new HttpResponseException(((StatusLine)localObject1).getStatusCode(), "None, or more than one, Content-Type Header found!"));
      return;
    }
    int i = 0;
    localObject2 = localObject2[0];
    String[] arrayOfString = getAllowedContentTypes();
    int k = arrayOfString.length;
    int j = 0;
    while (i < k)
    {
      String str = arrayOfString[i];
      try
      {
        boolean bool = Pattern.matches(str, ((Header)localObject2).getValue());
        if (bool) {
          j = 1;
        }
      }
      catch (PatternSyntaxException localPatternSyntaxException)
      {
        LogInterface localLogInterface = AsyncHttpClient.log;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Given pattern is not valid: ");
        localStringBuilder.append(str);
        localLogInterface.e("BinaryHttpRH", localStringBuilder.toString(), localPatternSyntaxException);
      }
      i += 1;
    }
    if (j == 0)
    {
      i = ((StatusLine)localObject1).getStatusCode();
      paramHttpResponse = paramHttpResponse.getAllHeaders();
      j = ((StatusLine)localObject1).getStatusCode();
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("Content-Type (");
      ((StringBuilder)localObject1).append(((Header)localObject2).getValue());
      ((StringBuilder)localObject1).append(") not allowed!");
      sendFailureMessage(i, paramHttpResponse, null, new HttpResponseException(j, ((StringBuilder)localObject1).toString()));
      return;
    }
    super.sendResponseMessage(paramHttpResponse);
  }
}
