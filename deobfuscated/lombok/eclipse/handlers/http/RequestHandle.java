package lombok.eclipse.handlers.http;

import android.os.Looper;
import b.c.a.a.e;
import java.lang.ref.WeakReference;

public class RequestHandle
{
  private final WeakReference<e> request;
  
  public RequestHandle(AsyncHttpRequest paramAsyncHttpRequest)
  {
    request = new WeakReference(paramAsyncHttpRequest);
  }
  
  public boolean cancel(boolean paramBoolean)
  {
    AsyncHttpRequest localAsyncHttpRequest = (AsyncHttpRequest)request.get();
    if (localAsyncHttpRequest != null)
    {
      if (Looper.myLooper() == Looper.getMainLooper())
      {
        new Thread(new EventInfoFragment.1(this, localAsyncHttpRequest, paramBoolean)).start();
        return true;
      }
      return localAsyncHttpRequest.cancel(paramBoolean);
    }
    return false;
  }
  
  public boolean isCancelled()
  {
    AsyncHttpRequest localAsyncHttpRequest = (AsyncHttpRequest)request.get();
    return (localAsyncHttpRequest == null) || (localAsyncHttpRequest.isCancelled());
  }
  
  public boolean isFinished()
  {
    AsyncHttpRequest localAsyncHttpRequest = (AsyncHttpRequest)request.get();
    return (localAsyncHttpRequest == null) || (localAsyncHttpRequest.isDone());
  }
  
  public boolean shouldBeGarbageCollected()
  {
    boolean bool;
    if ((!isCancelled()) && (!isFinished())) {
      bool = false;
    } else {
      bool = true;
    }
    if (bool) {
      request.clear();
    }
    return bool;
  }
}
