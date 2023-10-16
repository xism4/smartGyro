package lombok.eclipse.handlers.http;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpEntity;
import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.StatusLine;
import cz.msebera.android.http.client.HttpResponseException;
import cz.msebera.android.http.mime.ByteArrayBuffer;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URI;

public abstract class AsyncHttpResponseHandler
  implements ResponseHandlerInterface
{
  private WeakReference<Object> TAG = new WeakReference(null);
  private Handler handler;
  private Looper looper = null;
  private Header[] requestHeaders = null;
  private URI requestURI = null;
  private String responseCharset = "UTF-8";
  private boolean usePoolThread;
  private boolean useSynchronousMode;
  
  public AsyncHttpResponseHandler()
  {
    this(null);
  }
  
  public AsyncHttpResponseHandler(Looper paramLooper)
  {
    Looper localLooper = paramLooper;
    if (paramLooper == null) {
      localLooper = Looper.myLooper();
    }
    looper = localLooper;
    setUseSynchronousMode(false);
    setUsePoolThread(false);
  }
  
  public AsyncHttpResponseHandler(boolean paramBoolean)
  {
    setUsePoolThread(paramBoolean);
    if (!getUsePoolThread())
    {
      looper = Looper.myLooper();
      setUseSynchronousMode(false);
    }
  }
  
  public abstract void close(int paramInt, Header[] paramArrayOfHeader, byte[] paramArrayOfByte, Throwable paramThrowable);
  
  public URI getRequestURI()
  {
    return requestURI;
  }
  
  byte[] getResponseData(HttpEntity paramHttpEntity)
  {
    InputStream localInputStream;
    long l2;
    int i;
    if (paramHttpEntity != null)
    {
      localInputStream = paramHttpEntity.getContent();
      if (localInputStream != null)
      {
        l2 = paramHttpEntity.getContentLength();
        if (l2 <= 2147483647L) {
          if (l2 <= 0L) {
            i = 4096;
          } else {
            i = (int)l2;
          }
        }
      }
    }
    try
    {
      ByteArrayBuffer localByteArrayBuffer = new ByteArrayBuffer(i);
      try
      {
        byte[] arrayOfByte = new byte['?'];
        long l3;
        for (long l1 = 0L;; l1 = l3)
        {
          i = localInputStream.read(arrayOfByte);
          if (i == -1) {
            break;
          }
          boolean bool = Thread.currentThread().isInterrupted();
          if (bool) {
            break;
          }
          l3 = l1 + i;
          localByteArrayBuffer.append(arrayOfByte, 0, i);
          if (l2 <= 0L) {
            l1 = 1L;
          } else {
            l1 = l2;
          }
          sendProgressMessage(l3, l1);
        }
        System.gc();
      }
      catch (Throwable localThrowable)
      {
        try
        {
          AsyncHttpClient.silentCloseInputStream(localInputStream);
          AsyncHttpClient.endEntityViaReflection(paramHttpEntity);
          paramHttpEntity = localByteArrayBuffer.toByteArray();
          return paramHttpEntity;
        }
        catch (OutOfMemoryError paramHttpEntity)
        {
          for (;;) {}
        }
        localThrowable = localThrowable;
        AsyncHttpClient.silentCloseInputStream(localInputStream);
        AsyncHttpClient.endEntityViaReflection(paramHttpEntity);
        throw localThrowable;
      }
    }
    catch (OutOfMemoryError paramHttpEntity)
    {
      for (;;) {}
    }
    throw new IOException("File too large to fit into available memory");
    throw new IllegalArgumentException("HTTP entity too large to be buffered in memory");
    return null;
  }
  
  public boolean getUsePoolThread()
  {
    return usePoolThread;
  }
  
  public boolean getUseSynchronousMode()
  {
    return useSynchronousMode;
  }
  
  /* Error */
  protected void handleMessage(Message paramMessage)
  {
    // Byte code:
    //   0: aload_1
    //   1: getfield 160	android/os/Message:what	I
    //   4: istore_2
    //   5: iload_2
    //   6: lookupswitch	default:+66->72, 0:+263->269, 1:+201->207, 2:+196->202, 3:+191->197, 4:+123->129, 5:+72->78, 6:+67->73
    //   72: return
    //   73: aload_0
    //   74: invokevirtual 163	lombok/eclipse/handlers/http/AsyncHttpResponseHandler:onCancel	()V
    //   77: return
    //   78: aload_1
    //   79: getfield 167	android/os/Message:obj	Ljava/lang/Object;
    //   82: checkcast 169	[Ljava/lang/Object;
    //   85: astore_1
    //   86: aload_1
    //   87: ifnull +25 -> 112
    //   90: aload_1
    //   91: arraylength
    //   92: istore_2
    //   93: iload_2
    //   94: iconst_1
    //   95: if_icmpne +17 -> 112
    //   98: aload_0
    //   99: aload_1
    //   100: iconst_0
    //   101: aaload
    //   102: checkcast 171	java/lang/Integer
    //   105: invokevirtual 175	java/lang/Integer:intValue	()I
    //   108: invokevirtual 178	lombok/eclipse/handlers/http/AsyncHttpResponseHandler:onRetry	(I)V
    //   111: return
    //   112: getstatic 182	lombok/eclipse/handlers/http/AsyncHttpClient:log	Llombok/eclipse/handlers/http/LogInterface;
    //   115: astore_1
    //   116: ldc -72
    //   118: astore_3
    //   119: aload_1
    //   120: ldc -70
    //   122: aload_3
    //   123: invokeinterface 192 3 0
    //   128: return
    //   129: aload_1
    //   130: getfield 167	android/os/Message:obj	Ljava/lang/Object;
    //   133: checkcast 169	[Ljava/lang/Object;
    //   136: astore_1
    //   137: aload_1
    //   138: ifnull +49 -> 187
    //   141: aload_1
    //   142: arraylength
    //   143: istore_2
    //   144: iload_2
    //   145: iconst_2
    //   146: if_icmplt +41 -> 187
    //   149: aload_0
    //   150: aload_1
    //   151: iconst_0
    //   152: aaload
    //   153: checkcast 194	java/lang/Long
    //   156: invokevirtual 197	java/lang/Long:longValue	()J
    //   159: aload_1
    //   160: iconst_1
    //   161: aaload
    //   162: checkcast 194	java/lang/Long
    //   165: invokevirtual 197	java/lang/Long:longValue	()J
    //   168: invokevirtual 200	lombok/eclipse/handlers/http/AsyncHttpResponseHandler:write	(JJ)V
    //   171: return
    //   172: astore_1
    //   173: getstatic 182	lombok/eclipse/handlers/http/AsyncHttpClient:log	Llombok/eclipse/handlers/http/LogInterface;
    //   176: ldc -70
    //   178: ldc -54
    //   180: aload_1
    //   181: invokeinterface 205 4 0
    //   186: return
    //   187: getstatic 182	lombok/eclipse/handlers/http/AsyncHttpClient:log	Llombok/eclipse/handlers/http/LogInterface;
    //   190: astore_1
    //   191: ldc -49
    //   193: astore_3
    //   194: goto -75 -> 119
    //   197: aload_0
    //   198: invokevirtual 209	lombok/eclipse/handlers/http/AsyncHttpResponseHandler:read	()V
    //   201: return
    //   202: aload_0
    //   203: invokevirtual 211	lombok/eclipse/handlers/http/AsyncHttpResponseHandler:write	()V
    //   206: return
    //   207: aload_1
    //   208: getfield 167	android/os/Message:obj	Ljava/lang/Object;
    //   211: checkcast 169	[Ljava/lang/Object;
    //   214: astore_1
    //   215: aload_1
    //   216: ifnull +43 -> 259
    //   219: aload_1
    //   220: arraylength
    //   221: istore_2
    //   222: iload_2
    //   223: iconst_4
    //   224: if_icmplt +35 -> 259
    //   227: aload_0
    //   228: aload_1
    //   229: iconst_0
    //   230: aaload
    //   231: checkcast 171	java/lang/Integer
    //   234: invokevirtual 175	java/lang/Integer:intValue	()I
    //   237: aload_1
    //   238: iconst_1
    //   239: aaload
    //   240: checkcast 212	[Lcz/msebera/android/http/Header;
    //   243: aload_1
    //   244: iconst_2
    //   245: aaload
    //   246: checkcast 214	[B
    //   249: aload_1
    //   250: iconst_3
    //   251: aaload
    //   252: checkcast 76	java/lang/Throwable
    //   255: invokevirtual 216	lombok/eclipse/handlers/http/AsyncHttpResponseHandler:close	(I[Lcz/msebera/android/http/Header;[BLjava/lang/Throwable;)V
    //   258: return
    //   259: getstatic 182	lombok/eclipse/handlers/http/AsyncHttpClient:log	Llombok/eclipse/handlers/http/LogInterface;
    //   262: astore_1
    //   263: ldc -38
    //   265: astore_3
    //   266: goto -147 -> 119
    //   269: aload_1
    //   270: getfield 167	android/os/Message:obj	Ljava/lang/Object;
    //   273: checkcast 169	[Ljava/lang/Object;
    //   276: astore_1
    //   277: aload_1
    //   278: ifnull +37 -> 315
    //   281: aload_1
    //   282: arraylength
    //   283: istore_2
    //   284: iload_2
    //   285: iconst_3
    //   286: if_icmplt +29 -> 315
    //   289: aload_0
    //   290: aload_1
    //   291: iconst_0
    //   292: aaload
    //   293: checkcast 171	java/lang/Integer
    //   296: invokevirtual 175	java/lang/Integer:intValue	()I
    //   299: aload_1
    //   300: iconst_1
    //   301: aaload
    //   302: checkcast 212	[Lcz/msebera/android/http/Header;
    //   305: aload_1
    //   306: iconst_2
    //   307: aaload
    //   308: checkcast 214	[B
    //   311: invokevirtual 221	lombok/eclipse/handlers/http/AsyncHttpResponseHandler:read	(I[Lcz/msebera/android/http/Header;[B)V
    //   314: return
    //   315: getstatic 182	lombok/eclipse/handlers/http/AsyncHttpClient:log	Llombok/eclipse/handlers/http/LogInterface;
    //   318: astore_1
    //   319: ldc -33
    //   321: astore_3
    //   322: goto -203 -> 119
    //   325: astore_1
    //   326: aload_0
    //   327: aload_1
    //   328: invokevirtual 227	lombok/eclipse/handlers/http/AsyncHttpResponseHandler:onUserException	(Ljava/lang/Throwable;)V
    //   331: goto +3 -> 334
    //   334: new 229	java/lang/NullPointerException
    //   337: dup
    //   338: ldc -25
    //   340: invokespecial 232	java/lang/NullPointerException:<init>	(Ljava/lang/String;)V
    //   343: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	344	0	this	AsyncHttpResponseHandler
    //   0	344	1	paramMessage	Message
    //   4	283	2	i	int
    //   118	204	3	str	String
    // Exception table:
    //   from	to	target	type
    //   149	171	172	java/lang/Throwable
    //   0	5	325	java/lang/Throwable
    //   73	77	325	java/lang/Throwable
    //   78	86	325	java/lang/Throwable
    //   90	93	325	java/lang/Throwable
    //   98	111	325	java/lang/Throwable
    //   112	116	325	java/lang/Throwable
    //   119	128	325	java/lang/Throwable
    //   129	137	325	java/lang/Throwable
    //   141	144	325	java/lang/Throwable
    //   173	186	325	java/lang/Throwable
    //   187	191	325	java/lang/Throwable
    //   197	201	325	java/lang/Throwable
    //   202	206	325	java/lang/Throwable
    //   207	215	325	java/lang/Throwable
    //   219	222	325	java/lang/Throwable
    //   227	258	325	java/lang/Throwable
    //   259	263	325	java/lang/Throwable
    //   269	277	325	java/lang/Throwable
    //   281	284	325	java/lang/Throwable
    //   289	314	325	java/lang/Throwable
    //   315	319	325	java/lang/Throwable
  }
  
  protected Message obtainMessage(int paramInt, Object paramObject)
  {
    return Message.obtain(handler, paramInt, paramObject);
  }
  
  public void onCancel()
  {
    AsyncHttpClient.log.d("AsyncHttpRH", "Request got cancelled");
  }
  
  public void onPostProcessResponse(ResponseHandlerInterface paramResponseHandlerInterface, HttpResponse paramHttpResponse) {}
  
  public void onPreProcessResponse(ResponseHandlerInterface paramResponseHandlerInterface, HttpResponse paramHttpResponse) {}
  
  public void onRetry(int paramInt)
  {
    AsyncHttpClient.log.d("AsyncHttpRH", String.format("Request retry no. %d", new Object[] { Integer.valueOf(paramInt) }));
  }
  
  public void onUserException(Throwable paramThrowable)
  {
    AsyncHttpClient.log.e("AsyncHttpRH", "User-space exception detected!", paramThrowable);
    throw new RuntimeException(paramThrowable);
  }
  
  public void read() {}
  
  public abstract void read(int paramInt, Header[] paramArrayOfHeader, byte[] paramArrayOfByte);
  
  public final void sendCancelMessage()
  {
    sendMessage(obtainMessage(6, null));
  }
  
  public final void sendFailureMessage(int paramInt, Header[] paramArrayOfHeader, byte[] paramArrayOfByte, Throwable paramThrowable)
  {
    sendMessage(obtainMessage(1, new Object[] { Integer.valueOf(paramInt), paramArrayOfHeader, paramArrayOfByte, paramThrowable }));
  }
  
  public final void sendFinishMessage()
  {
    sendMessage(obtainMessage(3, null));
  }
  
  protected void sendMessage(Message paramMessage)
  {
    if ((!getUseSynchronousMode()) && (handler != null))
    {
      if (!Thread.currentThread().isInterrupted())
      {
        boolean bool;
        if (handler != null) {
          bool = true;
        } else {
          bool = false;
        }
        Utils.asserts(bool, "handler should not be null!");
        handler.sendMessage(paramMessage);
      }
    }
    else {
      handleMessage(paramMessage);
    }
  }
  
  public final void sendProgressMessage(long paramLong1, long paramLong2)
  {
    sendMessage(obtainMessage(4, new Object[] { Long.valueOf(paramLong1), Long.valueOf(paramLong2) }));
  }
  
  public void sendResponseMessage(HttpResponse paramHttpResponse)
  {
    if (!Thread.currentThread().isInterrupted())
    {
      StatusLine localStatusLine = paramHttpResponse.getStatusLine();
      byte[] arrayOfByte = getResponseData(paramHttpResponse.getEntity());
      if (!Thread.currentThread().isInterrupted())
      {
        if (localStatusLine.getStatusCode() >= 300)
        {
          sendFailureMessage(localStatusLine.getStatusCode(), paramHttpResponse.getAllHeaders(), arrayOfByte, new HttpResponseException(localStatusLine.getStatusCode(), localStatusLine.getReasonPhrase()));
          return;
        }
        sendSuccessMessage(localStatusLine.getStatusCode(), paramHttpResponse.getAllHeaders(), arrayOfByte);
      }
    }
  }
  
  public final void sendRetryMessage(int paramInt)
  {
    sendMessage(obtainMessage(5, new Object[] { Integer.valueOf(paramInt) }));
  }
  
  public final void sendStartMessage()
  {
    sendMessage(obtainMessage(2, null));
  }
  
  public final void sendSuccessMessage(int paramInt, Header[] paramArrayOfHeader, byte[] paramArrayOfByte)
  {
    sendMessage(obtainMessage(0, new Object[] { Integer.valueOf(paramInt), paramArrayOfHeader, paramArrayOfByte }));
  }
  
  public void setRequestHeaders(Header[] paramArrayOfHeader)
  {
    requestHeaders = paramArrayOfHeader;
  }
  
  public void setRequestURI(URI paramURI)
  {
    requestURI = paramURI;
  }
  
  public void setUsePoolThread(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      looper = null;
      handler = null;
    }
    usePoolThread = paramBoolean;
  }
  
  public void setUseSynchronousMode(boolean paramBoolean)
  {
    boolean bool = paramBoolean;
    if (!paramBoolean)
    {
      bool = paramBoolean;
      if (looper == null)
      {
        bool = true;
        AsyncHttpClient.log.w("AsyncHttpRH", "Current thread has not called Looper.prepare(). Forcing synchronous mode.");
      }
    }
    if ((!bool) && (handler == null)) {}
    for (ResponderHandler localResponderHandler = new ResponderHandler(looper);; localResponderHandler = null)
    {
      handler = localResponderHandler;
      break;
      if ((!bool) || (handler == null)) {
        break;
      }
    }
    useSynchronousMode = bool;
  }
  
  public void write() {}
  
  public void write(long paramLong1, long paramLong2)
  {
    LogInterface localLogInterface = AsyncHttpClient.log;
    double d1;
    if (paramLong2 > 0L)
    {
      d1 = paramLong1;
      Double.isNaN(d1);
      double d2 = paramLong2;
      Double.isNaN(d2);
      d1 = d1 * 1.0D / d2 * 100.0D;
    }
    else
    {
      d1 = -1.0D;
    }
    localLogInterface.v("AsyncHttpRH", String.format("Progress %d from %d (%2.0f%%)", new Object[] { Long.valueOf(paramLong1), Long.valueOf(paramLong2), Double.valueOf(d1) }));
  }
  
  class ResponderHandler
    extends Handler
  {
    ResponderHandler(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      AsyncHttpResponseHandler.this.handleMessage(paramMessage);
    }
  }
}
