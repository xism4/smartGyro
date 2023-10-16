package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.os.Parcelable;
import android.support.p024v4.graphics.drawable.IconCompat;
import androidx.versionedparcelable.C0453b;

public class IconCompatParcelizer {
    public static IconCompat read(C0453b bVar) {
        IconCompat iconCompat = new IconCompat();
        iconCompat.f454b = bVar.mo2317a(iconCompat.f454b, 1);
        iconCompat.f456d = bVar.mo2327a(iconCompat.f456d, 2);
        iconCompat.f457e = bVar.mo2318a(iconCompat.f457e, 3);
        iconCompat.f458f = bVar.mo2317a(iconCompat.f458f, 4);
        iconCompat.f459g = bVar.mo2317a(iconCompat.f459g, 5);
        iconCompat.f460h = (ColorStateList) bVar.mo2318a(iconCompat.f460h, 6);
        iconCompat.f462j = bVar.mo2319a(iconCompat.f462j, 7);
        iconCompat.mo752c();
        return iconCompat;
    }

    public static void write(IconCompat iconCompat, C0453b bVar) {
        bVar.mo2324a(true, true);
        iconCompat.mo750a(bVar.mo2335c());
        bVar.mo2330b(iconCompat.f454b, 1);
        bVar.mo2333b(iconCompat.f456d, 2);
        bVar.mo2331b(iconCompat.f457e, 3);
        bVar.mo2330b(iconCompat.f458f, 4);
        bVar.mo2330b(iconCompat.f459g, 5);
        bVar.mo2331b((Parcelable) iconCompat.f460h, 6);
        bVar.mo2332b(iconCompat.f462j, 7);
    }
}
