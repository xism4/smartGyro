package lombok.eclipse.handlers.http;

import android.os.Build.VERSION;
import android.util.Log;

public class LogHandler
  implements LogInterface
{
  boolean mLoggingEnabled = true;
  int mLoggingLevel = 2;
  
  public LogHandler() {}
  
  private void log(String paramString1, String paramString2, Throwable paramThrowable)
  {
    Log.wtf(paramString1, paramString2, paramThrowable);
  }
  
  public void d(String paramString1, String paramString2)
  {
    log(2, paramString1, paramString2);
  }
  
  public void e(String paramString1, String paramString2)
  {
    log(6, paramString1, paramString2);
  }
  
  public void e(String paramString1, String paramString2, Throwable paramThrowable)
  {
    log(6, paramString1, paramString2, paramThrowable);
  }
  
  public boolean isLoggingEnabled()
  {
    return mLoggingEnabled;
  }
  
  public void log(int paramInt, String paramString1, String paramString2)
  {
    log(paramInt, paramString1, paramString2, null);
  }
  
  public void log(int paramInt, String paramString1, String paramString2, Throwable paramThrowable)
  {
    if ((isLoggingEnabled()) && (shouldLog(paramInt)))
    {
      if (paramInt != 2)
      {
        if (paramInt != 3)
        {
          if (paramInt != 4)
          {
            if (paramInt != 5)
            {
              if (paramInt != 6)
              {
                if (paramInt != 8) {
                  return;
                }
                if (Integer.valueOf(Build.VERSION.SDK).intValue() > 8)
                {
                  log(paramString1, paramString2, paramThrowable);
                  return;
                }
              }
              Log.e(paramString1, paramString2, paramThrowable);
              return;
            }
            Log.w(paramString1, paramString2, paramThrowable);
            return;
          }
          Log.i(paramString1, paramString2, paramThrowable);
          return;
        }
        Log.d(paramString1, paramString2, paramThrowable);
        return;
      }
      Log.v(paramString1, paramString2, paramThrowable);
    }
  }
  
  public boolean shouldLog(int paramInt)
  {
    return paramInt >= mLoggingLevel;
  }
  
  public void v(String paramString1, String paramString2)
  {
    log(2, paramString1, paramString2);
  }
  
  public void w(String paramString1, String paramString2)
  {
    log(5, paramString1, paramString2);
  }
  
  public void w(String paramString1, String paramString2, Throwable paramThrowable)
  {
    log(5, paramString1, paramString2, paramThrowable);
  }
}
