package lombok.eclipse.handlers.http;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpResponse;
import java.net.URI;

public abstract interface ResponseHandlerInterface
{
  public abstract boolean getUsePoolThread();
  
  public abstract boolean getUseSynchronousMode();
  
  public abstract void onPostProcessResponse(ResponseHandlerInterface paramResponseHandlerInterface, HttpResponse paramHttpResponse);
  
  public abstract void onPreProcessResponse(ResponseHandlerInterface paramResponseHandlerInterface, HttpResponse paramHttpResponse);
  
  public abstract void sendCancelMessage();
  
  public abstract void sendFailureMessage(int paramInt, Header[] paramArrayOfHeader, byte[] paramArrayOfByte, Throwable paramThrowable);
  
  public abstract void sendFinishMessage();
  
  public abstract void sendResponseMessage(HttpResponse paramHttpResponse);
  
  public abstract void sendRetryMessage(int paramInt);
  
  public abstract void sendStartMessage();
  
  public abstract void setRequestHeaders(Header[] paramArrayOfHeader);
  
  public abstract void setRequestURI(URI paramURI);
}
