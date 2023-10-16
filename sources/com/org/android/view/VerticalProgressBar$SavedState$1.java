package com.org.android.view;

import a.a.c.g.c;
import android.os.Parcel;
import android.os.Parcelable;

final class VerticalProgressBar$SavedState$1 implements Parcelable.ClassLoaderCreator<c> {
    VerticalProgressBar$SavedState$1() {
    }

    public Settings createFromParcel(Parcel parcel) {
        return createFromParcel(parcel, (ClassLoader) null);
    }

    public Settings createFromParcel(Parcel parcel, ClassLoader classLoader) {
        if (parcel.readParcelable(classLoader) == null) {
            return Settings.EMPTY_STATE;
        }
        throw new IllegalStateException("superState must be null");
    }

    public Settings[] newArray(int i) {
        return new Settings[i];
    }
}
