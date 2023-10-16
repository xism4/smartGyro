package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class ParcelImpl
  implements Parcelable
{
  public static final Parcelable.Creator<ParcelImpl> CREATOR = new Server.1();
  private final Context a;
  
  protected ParcelImpl(Parcel paramParcel)
  {
    a = new i(paramParcel).f();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    new i(paramParcel).d(a);
  }
}
