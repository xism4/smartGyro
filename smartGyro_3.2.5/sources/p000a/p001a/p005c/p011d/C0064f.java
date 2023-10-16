package p000a.p001a.p005c.p011d;

import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.CancellationSignal;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import p000a.p001a.p005c.p006a.p007a.C0028c;
import p000a.p001a.p005c.p008b.C0046c;
import p000a.p001a.p005c.p008b.C0055k;
import p000a.p001a.p005c.p011d.C0072k;
import p000a.p001a.p005c.p013f.C0081e;
import p000a.p001a.p005c.p013f.C0089h;
import p000a.p001a.p005c.p013f.C0090i;

/* renamed from: a.a.c.d.f */
public class C0064f {

    /* renamed from: a */
    static final C0081e<String, Typeface> f153a = new C0081e<>(16);

    /* renamed from: b */
    private static final C0072k f154b = new C0072k("fonts", 10, 10000);

    /* renamed from: c */
    static final Object f155c = new Object();

    /* renamed from: d */
    static final C0090i<String, ArrayList<C0072k.C0073a<C0067c>>> f156d = new C0090i<>();

    /* renamed from: e */
    private static final Comparator<byte[]> f157e = new C0063e();

    /* renamed from: a.a.c.d.f$a */
    public static class C0065a {

        /* renamed from: a */
        private final int f158a;

        /* renamed from: b */
        private final C0066b[] f159b;

        public C0065a(int i, C0066b[] bVarArr) {
            this.f158a = i;
            this.f159b = bVarArr;
        }

        /* renamed from: a */
        public C0066b[] mo230a() {
            return this.f159b;
        }

        /* renamed from: b */
        public int mo231b() {
            return this.f158a;
        }
    }

    /* renamed from: a.a.c.d.f$b */
    public static class C0066b {

        /* renamed from: a */
        private final Uri f160a;

        /* renamed from: b */
        private final int f161b;

        /* renamed from: c */
        private final int f162c;

        /* renamed from: d */
        private final boolean f163d;

        /* renamed from: e */
        private final int f164e;

        public C0066b(Uri uri, int i, int i2, boolean z, int i3) {
            C0089h.m313a(uri);
            this.f160a = uri;
            this.f161b = i;
            this.f162c = i2;
            this.f163d = z;
            this.f164e = i3;
        }

        /* renamed from: a */
        public int mo232a() {
            return this.f164e;
        }

        /* renamed from: b */
        public int mo233b() {
            return this.f161b;
        }

        /* renamed from: c */
        public Uri mo234c() {
            return this.f160a;
        }

        /* renamed from: d */
        public int mo235d() {
            return this.f162c;
        }

        /* renamed from: e */
        public boolean mo236e() {
            return this.f163d;
        }
    }

    /* renamed from: a.a.c.d.f$c */
    private static final class C0067c {

        /* renamed from: a */
        final Typeface f165a;

        /* renamed from: b */
        final int f166b;

        C0067c(Typeface typeface, int i) {
            this.f165a = typeface;
            this.f166b = i;
        }
    }

    /* renamed from: a */
    public static C0065a m228a(Context context, CancellationSignal cancellationSignal, C0059a aVar) {
        ProviderInfo a = m230a(context.getPackageManager(), aVar, context.getResources());
        return a == null ? new C0065a(1, (C0066b[]) null) : new C0065a(0, m236a(context, aVar, a.authority, cancellationSignal));
    }

    /* renamed from: a */
    static C0067c m229a(Context context, C0059a aVar, int i) {
        try {
            C0065a a = m228a(context, (CancellationSignal) null, aVar);
            int i2 = -3;
            if (a.mo231b() == 0) {
                Typeface a2 = C0046c.m161a(context, (CancellationSignal) null, a.mo230a(), i);
                if (a2 != null) {
                    i2 = 0;
                }
                return new C0067c(a2, i2);
            }
            if (a.mo231b() == 1) {
                i2 = -2;
            }
            return new C0067c((Typeface) null, i2);
        } catch (PackageManager.NameNotFoundException unused) {
            return new C0067c((Typeface) null, -1);
        }
    }

    /* renamed from: a */
    public static ProviderInfo m230a(PackageManager packageManager, C0059a aVar, Resources resources) {
        String d = aVar.mo220d();
        ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(d, 0);
        if (resolveContentProvider == null) {
            throw new PackageManager.NameNotFoundException("No package found for authority: " + d);
        } else if (resolveContentProvider.packageName.equals(aVar.mo221e())) {
            List<byte[]> a = m233a(packageManager.getPackageInfo(resolveContentProvider.packageName, 64).signatures);
            Collections.sort(a, f157e);
            List<List<byte[]>> a2 = m232a(aVar, resources);
            for (int i = 0; i < a2.size(); i++) {
                ArrayList arrayList = new ArrayList(a2.get(i));
                Collections.sort(arrayList, f157e);
                if (m235a(a, (List<byte[]>) arrayList)) {
                    return resolveContentProvider;
                }
            }
            return null;
        } else {
            throw new PackageManager.NameNotFoundException("Found content provider " + d + ", but package was not " + aVar.mo221e());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0078, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0089, code lost:
        f154b.mo244a(r1, new p000a.p001a.p005c.p011d.C0062d(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0093, code lost:
        return null;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Typeface m231a(android.content.Context r2, p000a.p001a.p005c.p011d.C0059a r3, p000a.p001a.p005c.p006a.p007a.C0038h.C0039a r4, android.os.Handler r5, boolean r6, int r7, int r8) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r3.mo219c()
            r0.append(r1)
            java.lang.String r1 = "-"
            r0.append(r1)
            r0.append(r8)
            java.lang.String r0 = r0.toString()
            a.a.c.f.e<java.lang.String, android.graphics.Typeface> r1 = f153a
            java.lang.Object r1 = r1.mo297b(r0)
            android.graphics.Typeface r1 = (android.graphics.Typeface) r1
            if (r1 == 0) goto L_0x0028
            if (r4 == 0) goto L_0x0027
            r4.mo172a((android.graphics.Typeface) r1)
        L_0x0027:
            return r1
        L_0x0028:
            if (r6 == 0) goto L_0x0043
            r1 = -1
            if (r7 != r1) goto L_0x0043
            a.a.c.d.f$c r2 = m229a((android.content.Context) r2, (p000a.p001a.p005c.p011d.C0059a) r3, (int) r8)
            if (r4 == 0) goto L_0x0040
            int r3 = r2.f166b
            if (r3 != 0) goto L_0x003d
            android.graphics.Typeface r3 = r2.f165a
            r4.mo173a((android.graphics.Typeface) r3, (android.os.Handler) r5)
            goto L_0x0040
        L_0x003d:
            r4.mo171a((int) r3, (android.os.Handler) r5)
        L_0x0040:
            android.graphics.Typeface r2 = r2.f165a
            return r2
        L_0x0043:
            a.a.c.d.b r1 = new a.a.c.d.b
            r1.<init>(r2, r3, r8, r0)
            r2 = 0
            if (r6 == 0) goto L_0x0056
            a.a.c.d.k r3 = f154b     // Catch:{ InterruptedException -> 0x0055 }
            java.lang.Object r3 = r3.mo241a(r1, (int) r7)     // Catch:{ InterruptedException -> 0x0055 }
            a.a.c.d.f$c r3 = (p000a.p001a.p005c.p011d.C0064f.C0067c) r3     // Catch:{ InterruptedException -> 0x0055 }
            android.graphics.Typeface r2 = r3.f165a     // Catch:{ InterruptedException -> 0x0055 }
        L_0x0055:
            return r2
        L_0x0056:
            if (r4 != 0) goto L_0x005a
            r3 = r2
            goto L_0x005f
        L_0x005a:
            a.a.c.d.c r3 = new a.a.c.d.c
            r3.<init>(r4, r5)
        L_0x005f:
            java.lang.Object r4 = f155c
            monitor-enter(r4)
            a.a.c.f.i<java.lang.String, java.util.ArrayList<a.a.c.d.k$a<a.a.c.d.f$c>>> r5 = f156d     // Catch:{ all -> 0x0094 }
            boolean r5 = r5.containsKey(r0)     // Catch:{ all -> 0x0094 }
            if (r5 == 0) goto L_0x0079
            if (r3 == 0) goto L_0x0077
            a.a.c.f.i<java.lang.String, java.util.ArrayList<a.a.c.d.k$a<a.a.c.d.f$c>>> r5 = f156d     // Catch:{ all -> 0x0094 }
            java.lang.Object r5 = r5.get(r0)     // Catch:{ all -> 0x0094 }
            java.util.ArrayList r5 = (java.util.ArrayList) r5     // Catch:{ all -> 0x0094 }
            r5.add(r3)     // Catch:{ all -> 0x0094 }
        L_0x0077:
            monitor-exit(r4)     // Catch:{ all -> 0x0094 }
            return r2
        L_0x0079:
            if (r3 == 0) goto L_0x0088
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x0094 }
            r5.<init>()     // Catch:{ all -> 0x0094 }
            r5.add(r3)     // Catch:{ all -> 0x0094 }
            a.a.c.f.i<java.lang.String, java.util.ArrayList<a.a.c.d.k$a<a.a.c.d.f$c>>> r3 = f156d     // Catch:{ all -> 0x0094 }
            r3.put(r0, r5)     // Catch:{ all -> 0x0094 }
        L_0x0088:
            monitor-exit(r4)     // Catch:{ all -> 0x0094 }
            a.a.c.d.k r3 = f154b
            a.a.c.d.d r4 = new a.a.c.d.d
            r4.<init>(r0)
            r3.mo244a(r1, r4)
            return r2
        L_0x0094:
            r2 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0094 }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p000a.p001a.p005c.p011d.C0064f.m231a(android.content.Context, a.a.c.d.a, a.a.c.a.a.h$a, android.os.Handler, boolean, int, int):android.graphics.Typeface");
    }

    /* renamed from: a */
    private static List<List<byte[]>> m232a(C0059a aVar, Resources resources) {
        return aVar.mo217a() != null ? aVar.mo217a() : C0028c.m97a(resources, aVar.mo218b());
    }

    /* renamed from: a */
    private static List<byte[]> m233a(Signature[] signatureArr) {
        ArrayList arrayList = new ArrayList();
        for (Signature byteArray : signatureArr) {
            arrayList.add(byteArray.toByteArray());
        }
        return arrayList;
    }

    /* renamed from: a */
    public static Map<Uri, ByteBuffer> m234a(Context context, C0066b[] bVarArr, CancellationSignal cancellationSignal) {
        HashMap hashMap = new HashMap();
        for (C0066b bVar : bVarArr) {
            if (bVar.mo232a() == 0) {
                Uri c = bVar.mo234c();
                if (!hashMap.containsKey(c)) {
                    hashMap.put(c, C0055k.m210a(context, cancellationSignal, c));
                }
            }
        }
        return Collections.unmodifiableMap(hashMap);
    }

    /* renamed from: a */
    private static boolean m235a(List<byte[]> list, List<byte[]> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!Arrays.equals(list.get(i), list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    static C0066b[] m236a(Context context, C0059a aVar, String str, CancellationSignal cancellationSignal) {
        String str2 = str;
        ArrayList arrayList = new ArrayList();
        Uri build = new Uri.Builder().scheme("content").authority(str2).build();
        Uri build2 = new Uri.Builder().scheme("content").authority(str2).appendPath("file").build();
        Cursor cursor = null;
        try {
            cursor = Build.VERSION.SDK_INT > 16 ? context.getContentResolver().query(build, new String[]{"_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"}, "query = ?", new String[]{aVar.mo222f()}, (String) null, cancellationSignal) : context.getContentResolver().query(build, new String[]{"_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"}, "query = ?", new String[]{aVar.mo222f()}, (String) null);
            if (cursor != null && cursor.getCount() > 0) {
                int columnIndex = cursor.getColumnIndex("result_code");
                ArrayList arrayList2 = new ArrayList();
                int columnIndex2 = cursor.getColumnIndex("_id");
                int columnIndex3 = cursor.getColumnIndex("file_id");
                int columnIndex4 = cursor.getColumnIndex("font_ttc_index");
                int columnIndex5 = cursor.getColumnIndex("font_weight");
                int columnIndex6 = cursor.getColumnIndex("font_italic");
                while (cursor.moveToNext()) {
                    int i = columnIndex != -1 ? cursor.getInt(columnIndex) : 0;
                    arrayList2.add(new C0066b(columnIndex3 == -1 ? ContentUris.withAppendedId(build, cursor.getLong(columnIndex2)) : ContentUris.withAppendedId(build2, cursor.getLong(columnIndex3)), columnIndex4 != -1 ? cursor.getInt(columnIndex4) : 0, columnIndex5 != -1 ? cursor.getInt(columnIndex5) : 400, columnIndex6 != -1 && cursor.getInt(columnIndex6) == 1, i));
                }
                arrayList = arrayList2;
            }
            return (C0066b[]) arrayList.toArray(new C0066b[0]);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
