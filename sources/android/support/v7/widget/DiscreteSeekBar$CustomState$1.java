package android.support.v7.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.Toolbar;

final class DiscreteSeekBar$CustomState$1 implements Parcelable.ClassLoaderCreator<Toolbar.d> {
    DiscreteSeekBar$CustomState$1() {
    }

    public Toolbar.d createFromParcel(Parcel parcel) {
        return new Toolbar.d(parcel, (ClassLoader) null);
    }

    public Toolbar.d createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return new Toolbar.d(parcel, classLoader);
    }

    public Toolbar.d[] newArray(int i) {
        return new Toolbar.d[i];
    }
}
