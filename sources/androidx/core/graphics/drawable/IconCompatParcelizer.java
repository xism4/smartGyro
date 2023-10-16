package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.os.Parcelable;
import android.support.v4.graphics.drawable.IconCompat;
import androidx.versionedparcelable.f;

public class IconCompatParcelizer {
    public static IconCompat read(f fVar) {
        IconCompat $r0 = new IconCompat();
        $r0.c = fVar.add($r0.c, 1);
        $r0.s = fVar.a($r0.s, 2);
        $r0.b = fVar.add($r0.b, 3);
        $r0.r = fVar.add($r0.r, 4);
        $r0.p = fVar.add($r0.p, 5);
        $r0.i = (ColorStateList) fVar.add((Parcelable) $r0.i, 6);
        $r0.x = fVar.a($r0.x, 7);
        $r0.init();
        return $r0;
    }

    public static void write(IconCompat iconCompat, f fVar) {
        fVar.a(true, true);
        iconCompat.encode(fVar.processBytes());
        fVar.a(iconCompat.c, 1);
        fVar.clear(iconCompat.s, 2);
        fVar.a(iconCompat.b, 3);
        fVar.a(iconCompat.r, 4);
        fVar.a(iconCompat.p, 5);
        fVar.a((Parcelable) iconCompat.i, 6);
        fVar.b(iconCompat.x, 7);
    }
}
