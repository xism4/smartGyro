package com.org.android.view;

import a.a.c.g.c;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public abstract class Settings
  implements Parcelable
{
  public static final Parcelable.Creator<c> CREATOR = new VerticalProgressBar.SavedState.1();
  public static final Settings EMPTY_STATE = new JavacResolver.1();
  private final Parcelable mToken;
  
  private Settings()
  {
    mToken = null;
  }
  
  protected Settings(Parcel paramParcel, ClassLoader paramClassLoader)
  {
    paramClassLoader = paramParcel.readParcelable(paramClassLoader);
    paramParcel = paramClassLoader;
    if (paramClassLoader == null) {
      paramParcel = EMPTY_STATE;
    }
    mToken = paramParcel;
  }
  
  protected Settings(Parcelable paramParcelable)
  {
    if (paramParcelable != null)
    {
      if (paramParcelable == EMPTY_STATE) {
        paramParcelable = null;
      }
      mToken = paramParcelable;
      return;
    }
    throw new IllegalArgumentException("superState must not be null");
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public final Parcelable getToken()
  {
    return mToken;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeParcelable(mToken, paramInt);
  }
}
