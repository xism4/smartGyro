package lombok.extern.asm;

import android.os.IInterface;

public abstract interface ByteVector
  extends IInterface
{
  public abstract int a();
  
  public abstract int a(int paramInt);
  
  public abstract int a(boolean paramBoolean);
  
  public abstract int generateKey(int paramInt);
  
  public abstract int get(int paramInt);
}
