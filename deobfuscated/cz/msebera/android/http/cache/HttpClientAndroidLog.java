package cz.msebera.android.http.cache;

import android.util.Log;

public class HttpClientAndroidLog
{
  private boolean debugEnabled;
  private boolean errorEnabled;
  private boolean infoEnabled;
  private String logTag;
  private boolean traceEnabled;
  private boolean warnEnabled;
  
  public HttpClientAndroidLog(Object paramObject)
  {
    logTag = paramObject.toString();
    debugEnabled = false;
    errorEnabled = false;
    traceEnabled = false;
    warnEnabled = false;
    infoEnabled = false;
  }
  
  public void debug(Object paramObject)
  {
    if (isDebugEnabled()) {
      Log.d(logTag, paramObject.toString());
    }
  }
  
  public void debug(Object paramObject, Throwable paramThrowable)
  {
    if (isDebugEnabled()) {
      Log.d(logTag, paramObject.toString(), paramThrowable);
    }
  }
  
  public void error(Object paramObject)
  {
    if (isErrorEnabled()) {
      Log.e(logTag, paramObject.toString());
    }
  }
  
  public void info(Object paramObject)
  {
    if (isInfoEnabled()) {
      Log.i(logTag, paramObject.toString());
    }
  }
  
  public boolean isDebugEnabled()
  {
    return debugEnabled;
  }
  
  public boolean isErrorEnabled()
  {
    return errorEnabled;
  }
  
  public boolean isInfoEnabled()
  {
    return infoEnabled;
  }
  
  public boolean isWarnEnabled()
  {
    return warnEnabled;
  }
  
  public void warn(Object paramObject)
  {
    if (isWarnEnabled()) {
      Log.w(logTag, paramObject.toString());
    }
  }
  
  public void warn(Object paramObject, Throwable paramThrowable)
  {
    if (isWarnEnabled()) {
      Log.w(logTag, paramObject.toString(), paramThrowable);
    }
  }
}
