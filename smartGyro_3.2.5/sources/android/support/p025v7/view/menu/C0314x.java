package android.support.p025v7.view.menu;

import android.content.Context;
import android.os.Build;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import p000a.p001a.p005c.p009c.p010a.C0056a;
import p000a.p001a.p005c.p009c.p010a.C0057b;
import p000a.p001a.p005c.p009c.p010a.C0058c;

/* renamed from: android.support.v7.view.menu.x */
public final class C0314x {
    /* renamed from: a */
    public static Menu m1337a(Context context, C0056a aVar) {
        return new C0315y(context, aVar);
    }

    /* renamed from: a */
    public static MenuItem m1338a(Context context, C0057b bVar) {
        return Build.VERSION.SDK_INT >= 16 ? new C0305r(context, bVar) : new C0300q(context, bVar);
    }

    /* renamed from: a */
    public static SubMenu m1339a(Context context, C0058c cVar) {
        return new C0279E(context, cVar);
    }
}
