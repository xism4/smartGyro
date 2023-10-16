package lombok.extern.asm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

public abstract class b
  extends Binder
  implements ByteVector
{
  public static ByteVector a(IBinder paramIBinder)
  {
    if (paramIBinder == null) {
      return null;
    }
    IInterface localIInterface = paramIBinder.queryLocalInterface("com.enhance.gameservice.IGameTuningService");
    if ((localIInterface != null) && ((localIInterface instanceof ByteVector))) {
      return (ByteVector)localIInterface;
    }
    return new a.a.a(paramIBinder);
  }
  
  public static ByteVector b()
  {
    return a.a.a.d;
  }
}
