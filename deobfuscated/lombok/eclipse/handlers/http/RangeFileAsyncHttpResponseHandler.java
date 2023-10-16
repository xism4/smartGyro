package lombok.eclipse.handlers.http;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpEntity;
import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.StatusLine;
import cz.msebera.android.http.client.HttpResponseException;
import cz.msebera.android.http.client.auth.HttpUriRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public abstract class RangeFileAsyncHttpResponseHandler
  extends FileAsyncHttpResponseHandler
{
  private boolean append;
  private long current;
  
  protected byte[] getResponseData(HttpEntity paramHttpEntity)
  {
    if (paramHttpEntity != null)
    {
      InputStream localInputStream = paramHttpEntity.getContent();
      long l1 = paramHttpEntity.getContentLength() + current;
      paramHttpEntity = new FileOutputStream(getTargetFile(), append);
      if (localInputStream != null) {
        try
        {
          byte[] arrayOfByte = new byte['?'];
          for (;;)
          {
            long l2 = current;
            if (l2 >= l1) {
              break;
            }
            int i = localInputStream.read(arrayOfByte);
            if (i == -1) {
              break;
            }
            boolean bool = Thread.currentThread().isInterrupted();
            if (bool) {
              break;
            }
            l2 = current;
            long l3 = i;
            current = (l2 + l3);
            paramHttpEntity.write(arrayOfByte, 0, i);
            sendProgressMessage(current, l1);
          }
          localInputStream.close();
          paramHttpEntity.flush();
          paramHttpEntity.close();
        }
        catch (Throwable localThrowable)
        {
          localInputStream.close();
          paramHttpEntity.flush();
          paramHttpEntity.close();
          throw localThrowable;
        }
      }
    }
    return null;
  }
  
  public void sendResponseMessage(HttpResponse paramHttpResponse)
  {
    if (!Thread.currentThread().isInterrupted())
    {
      StatusLine localStatusLine = paramHttpResponse.getStatusLine();
      if (localStatusLine.getStatusCode() == 416)
      {
        if (!Thread.currentThread().isInterrupted()) {
          sendSuccessMessage(localStatusLine.getStatusCode(), paramHttpResponse.getAllHeaders(), null);
        }
      }
      else if (localStatusLine.getStatusCode() >= 300)
      {
        if (!Thread.currentThread().isInterrupted()) {
          sendFailureMessage(localStatusLine.getStatusCode(), paramHttpResponse.getAllHeaders(), null, new HttpResponseException(localStatusLine.getStatusCode(), localStatusLine.getReasonPhrase()));
        }
      }
      else if (!Thread.currentThread().isInterrupted())
      {
        Header localHeader = paramHttpResponse.getFirstHeader("Content-Range");
        if (localHeader == null)
        {
          append = false;
          current = 0L;
        }
        else
        {
          LogInterface localLogInterface = AsyncHttpClient.log;
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Content-Range: ");
          localStringBuilder.append(localHeader.getValue());
          localLogInterface.v("RangeFileAsyncHttpRH", localStringBuilder.toString());
        }
        sendSuccessMessage(localStatusLine.getStatusCode(), paramHttpResponse.getAllHeaders(), getResponseData(paramHttpResponse.getEntity()));
      }
    }
  }
  
  public void updateRequestHeaders(HttpUriRequest paramHttpUriRequest)
  {
    if ((file.exists()) && (file.canWrite())) {
      current = file.length();
    }
    if (current > 0L)
    {
      append = true;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("bytes=");
      localStringBuilder.append(current);
      localStringBuilder.append("-");
      paramHttpUriRequest.setHeader("Range", localStringBuilder.toString());
    }
  }
}
