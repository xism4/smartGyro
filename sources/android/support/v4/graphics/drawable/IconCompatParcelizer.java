package android.support.v4.graphics.drawable;

import androidx.versionedparcelable.f;

public final class IconCompatParcelizer extends androidx.core.graphics.drawable.IconCompatParcelizer {
    public static IconCompat read(f fVar) {
        return androidx.core.graphics.drawable.IconCompatParcelizer.read(fVar);
    }

    public static void write(IconCompat iconCompat, f fVar) {
        androidx.core.graphics.drawable.IconCompatParcelizer.write(iconCompat, fVar);
    }
}
