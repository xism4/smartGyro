package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseIntArray;

class i
  extends f
{
  private final SparseIntArray a = new SparseIntArray();
  private final Parcel b;
  private final String h;
  private int i = -1;
  private final int k;
  private int m = 0;
  private final int n;
  
  i(Parcel paramParcel)
  {
    this(paramParcel, paramParcel.dataPosition(), paramParcel.dataSize(), "");
  }
  
  i(Parcel paramParcel, int paramInt1, int paramInt2, String paramString)
  {
    b = paramParcel;
    k = paramInt1;
    n = paramInt2;
    m = k;
    h = paramString;
  }
  
  private int a(int paramInt)
  {
    int i1;
    do
    {
      int j = m;
      if (j >= n) {
        break;
      }
      b.setDataPosition(j);
      j = b.readInt();
      i1 = b.readInt();
      m += j;
    } while (i1 != paramInt);
    return b.dataPosition();
    return -1;
  }
  
  public void a()
  {
    int j = i;
    if (j >= 0)
    {
      j = a.get(j);
      int i1 = b.dataPosition();
      b.setDataPosition(j);
      b.writeInt(i1 - j);
      b.setDataPosition(i1);
    }
  }
  
  public void a(String paramString)
  {
    b.writeString(paramString);
  }
  
  public void a(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte != null)
    {
      b.writeInt(paramArrayOfByte.length);
      b.writeByteArray(paramArrayOfByte);
      return;
    }
    b.writeInt(-1);
  }
  
  public Parcelable add()
  {
    return b.readParcelable(c.class.getClassLoader());
  }
  
  public boolean add(int paramInt)
  {
    paramInt = a(paramInt);
    if (paramInt == -1) {
      return false;
    }
    b.setDataPosition(paramInt);
    return true;
  }
  
  public void b(int paramInt)
  {
    b.writeInt(paramInt);
  }
  
  public byte[] b()
  {
    int j = b.readInt();
    if (j < 0) {
      return null;
    }
    byte[] arrayOfByte = new byte[j];
    b.readByteArray(arrayOfByte);
    return arrayOfByte;
  }
  
  protected f c()
  {
    Parcel localParcel = b;
    int i2 = localParcel.dataPosition();
    int i1 = m;
    int j = i1;
    if (i1 == k) {
      j = n;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(h);
    localStringBuilder.append("  ");
    return new i(localParcel, i2, j, localStringBuilder.toString());
  }
  
  public void clear(Parcelable paramParcelable)
  {
    b.writeParcelable(paramParcelable, 0);
  }
  
  public void e(int paramInt)
  {
    a();
    i = paramInt;
    a.put(paramInt, b.dataPosition());
    b(0);
    b(paramInt);
  }
  
  public String getValue()
  {
    return b.readString();
  }
  
  public int size()
  {
    return b.readInt();
  }
}
