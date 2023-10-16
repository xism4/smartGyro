package android.support.v7.app;

import android.content.res.Resources.NotFoundException;

final class ExceptionHandler
  implements Thread.UncaughtExceptionHandler
{
  ExceptionHandler(Thread.UncaughtExceptionHandler paramUncaughtExceptionHandler) {}
  
  private boolean register(Throwable paramThrowable)
  {
    if ((paramThrowable instanceof Resources.NotFoundException))
    {
      paramThrowable = paramThrowable.getMessage();
      if ((paramThrowable != null) && ((paramThrowable.contains("drawable")) || (paramThrowable.contains("Drawable")))) {
        return true;
      }
    }
    return false;
  }
  
  public void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    if (register(paramThrowable))
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramThrowable.getMessage());
      ((StringBuilder)localObject).append(". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.");
      localObject = new Resources.NotFoundException(((StringBuilder)localObject).toString());
      ((Throwable)localObject).initCause(paramThrowable.getCause());
      ((Throwable)localObject).setStackTrace(paramThrowable.getStackTrace());
      defaultExceptionHandler.uncaughtException(paramThread, (Throwable)localObject);
      return;
    }
    defaultExceptionHandler.uncaughtException(paramThread, paramThrowable);
  }
}
