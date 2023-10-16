package com.org.android.view;

import a.a.c.g.c;
import android.os.Parcel;
import android.os.Parcelable;

public abstract class Settings implements Parcelable {
    public static final Parcelable.Creator<c> CREATOR = new VerticalProgressBar$SavedState$1();
    public static final Settings EMPTY_STATE = new JavacResolver$1();
    private final Parcelable mToken;

    private Settings() {
        this.mToken = null;
    }

    protected Settings(Parcel parcel, ClassLoader classLoader) {
        Parcelable $r2 = parcel.readParcelable(classLoader);
        this.mToken = $r2 == null ? EMPTY_STATE : $r2;
    }

    protected Settings(Parcelable $r1) {
        if ($r1 != null) {
            this.mToken = $r1 == EMPTY_STATE ? null : $r1;
            return;
        }
        throw new IllegalArgumentException("superState must not be null");
    }

    /* synthetic */ Settings(JavacResolver$1 javacResolver$1) {
        this();
    }

    public int describeContents() {
        return 0;
    }

    public final Parcelable getToken() {
        return this.mToken;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mToken, i);
    }
}
