package lombok.eclipse.handlers.http;

import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.client.auth.HttpUriRequest;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.impl.client.AbstractHttpClient;
import cz.msebera.android.http.impl.client.CloseableHttpClient;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.concurrent.atomic.AtomicBoolean;

public class AsyncHttpRequest
  implements Runnable
{
  private boolean cancelIsNotified;
  private final AbstractHttpClient client;
  private final HttpContext context;
  private int executionCount;
  private final AtomicBoolean isCancelled = new AtomicBoolean();
  private volatile boolean isFinished;
  private boolean isRequestPreProcessed;
  private final HttpUriRequest request;
  private final ResponseHandlerInterface responseHandler;
  
  public AsyncHttpRequest(AbstractHttpClient paramAbstractHttpClient, HttpContext paramHttpContext, HttpUriRequest paramHttpUriRequest, ResponseHandlerInterface paramResponseHandlerInterface)
  {
    Utils.notNull(paramAbstractHttpClient, "client");
    client = ((AbstractHttpClient)paramAbstractHttpClient);
    Utils.notNull(paramHttpContext, "context");
    context = ((HttpContext)paramHttpContext);
    Utils.notNull(paramHttpUriRequest, "request");
    request = ((HttpUriRequest)paramHttpUriRequest);
    Utils.notNull(paramResponseHandlerInterface, "responseHandler");
    responseHandler = ((ResponseHandlerInterface)paramResponseHandlerInterface);
  }
  
  private void makeRequest()
  {
    if (isCancelled()) {
      return;
    }
    if (request.getURI().getScheme() != null)
    {
      Object localObject = responseHandler;
      if ((localObject instanceof RangeFileAsyncHttpResponseHandler)) {
        ((RangeFileAsyncHttpResponseHandler)localObject).updateRequestHeaders(request);
      }
      localObject = client.execute(request, context);
      if (isCancelled()) {
        return;
      }
      ResponseHandlerInterface localResponseHandlerInterface = responseHandler;
      localResponseHandlerInterface.onPreProcessResponse(localResponseHandlerInterface, (HttpResponse)localObject);
      if (isCancelled()) {
        return;
      }
      responseHandler.sendResponseMessage((HttpResponse)localObject);
      if (isCancelled()) {
        return;
      }
      localResponseHandlerInterface = responseHandler;
      localResponseHandlerInterface.onPostProcessResponse(localResponseHandlerInterface, (HttpResponse)localObject);
      return;
    }
    throw new MalformedURLException("No valid URI scheme was provided");
  }
  
  private void makeRequestWithRetries()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a21 = a20\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n");
  }
  
  private void sendCancelNotification()
  {
    try
    {
      if ((!isFinished) && (isCancelled.get()) && (!cancelIsNotified))
      {
        cancelIsNotified = true;
        responseHandler.sendCancelMessage();
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public boolean cancel(boolean paramBoolean)
  {
    isCancelled.set(true);
    request.abort();
    return isCancelled();
  }
  
  public boolean isCancelled()
  {
    boolean bool = isCancelled.get();
    if (bool) {
      sendCancelNotification();
    }
    return bool;
  }
  
  public boolean isDone()
  {
    return (isCancelled()) || (isFinished);
  }
  
  public void onPostProcessRequest(AsyncHttpRequest paramAsyncHttpRequest) {}
  
  public void onPreProcessRequest(AsyncHttpRequest paramAsyncHttpRequest) {}
  
  public void run()
  {
    if (isCancelled()) {
      return;
    }
    if (!isRequestPreProcessed)
    {
      isRequestPreProcessed = true;
      onPostProcessRequest(this);
    }
    if (isCancelled()) {
      return;
    }
    responseHandler.sendStartMessage();
    if (isCancelled()) {
      return;
    }
    try
    {
      makeRequestWithRetries();
    }
    catch (IOException localIOException)
    {
      if (!isCancelled()) {
        responseHandler.sendFailureMessage(0, null, null, localIOException);
      } else {
        AsyncHttpClient.log.e("AsyncHttpRequest", "makeRequestWithRetries returned error", localIOException);
      }
    }
    if (isCancelled()) {
      return;
    }
    responseHandler.sendFinishMessage();
    if (isCancelled()) {
      return;
    }
    onPreProcessRequest(this);
    isFinished = true;
  }
}
