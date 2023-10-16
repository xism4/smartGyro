package android.support.v4.app;

import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import com.org.android.ui.Resources;

public class b
  extends Resources
{
  private static ByteVector l;
  
  public static void a(Activity paramActivity, String[] paramArrayOfString, int paramInt)
  {
    ByteVector localByteVector = l;
    if ((localByteVector != null) && (localByteVector.get(paramActivity, paramArrayOfString, paramInt))) {
      return;
    }
    if (Build.VERSION.SDK_INT >= 23)
    {
      if ((paramActivity instanceof MenuItem)) {
        ((MenuItem)paramActivity).setTitle(paramInt);
      }
      paramActivity.requestPermissions(paramArrayOfString, paramInt);
      return;
    }
    if ((paramActivity instanceof Item)) {
      new Handler(Looper.getMainLooper()).post(new ActivityUtils.1(paramArrayOfString, paramActivity, paramInt));
    }
  }
}
