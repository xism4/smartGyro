package p000a.p001a.p005c.p006a.p007a;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.Base64;
import android.util.TypedValue;
import android.util.Xml;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import p000a.p001a.p002a.C0002c;
import p000a.p001a.p005c.p011d.C0059a;

/* renamed from: a.a.c.a.a.c */
public class C0028c {

    /* renamed from: a.a.c.a.a.c$a */
    public interface C0029a {
    }

    /* renamed from: a.a.c.a.a.c$b */
    public static final class C0030b implements C0029a {

        /* renamed from: a */
        private final C0031c[] f104a;

        public C0030b(C0031c[] cVarArr) {
            this.f104a = cVarArr;
        }

        /* renamed from: a */
        public C0031c[] mo158a() {
            return this.f104a;
        }
    }

    /* renamed from: a.a.c.a.a.c$c */
    public static final class C0031c {

        /* renamed from: a */
        private final String f105a;

        /* renamed from: b */
        private int f106b;

        /* renamed from: c */
        private boolean f107c;

        /* renamed from: d */
        private String f108d;

        /* renamed from: e */
        private int f109e;

        /* renamed from: f */
        private int f110f;

        public C0031c(String str, int i, boolean z, String str2, int i2, int i3) {
            this.f105a = str;
            this.f106b = i;
            this.f107c = z;
            this.f108d = str2;
            this.f109e = i2;
            this.f110f = i3;
        }

        /* renamed from: a */
        public String mo159a() {
            return this.f105a;
        }

        /* renamed from: b */
        public int mo160b() {
            return this.f110f;
        }

        /* renamed from: c */
        public int mo161c() {
            return this.f109e;
        }

        /* renamed from: d */
        public String mo162d() {
            return this.f108d;
        }

        /* renamed from: e */
        public int mo163e() {
            return this.f106b;
        }

        /* renamed from: f */
        public boolean mo164f() {
            return this.f107c;
        }
    }

    /* renamed from: a.a.c.a.a.c$d */
    public static final class C0032d implements C0029a {

        /* renamed from: a */
        private final C0059a f111a;

        /* renamed from: b */
        private final int f112b;

        /* renamed from: c */
        private final int f113c;

        public C0032d(C0059a aVar, int i, int i2) {
            this.f111a = aVar;
            this.f113c = i;
            this.f112b = i2;
        }

        /* renamed from: a */
        public int mo165a() {
            return this.f113c;
        }

        /* renamed from: b */
        public C0059a mo166b() {
            return this.f111a;
        }

        /* renamed from: c */
        public int mo167c() {
            return this.f112b;
        }
    }

    /* renamed from: a */
    private static int m95a(TypedArray typedArray, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            return typedArray.getType(i);
        }
        TypedValue typedValue = new TypedValue();
        typedArray.getValue(i, typedValue);
        return typedValue.type;
    }

    /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* renamed from: a */
    public static p000a.p001a.p005c.p006a.p007a.C0028c.C0029a m96a(org.xmlpull.v1.XmlPullParser r3, android.content.res.Resources r4) {
        /*
        L_0x0000:
            int r0 = r3.next()
            r1 = 2
            if (r0 == r1) goto L_0x000b
            r2 = 1
            if (r0 == r2) goto L_0x000b
            goto L_0x0000
        L_0x000b:
            if (r0 != r1) goto L_0x0012
            a.a.c.a.a.c$a r3 = m100b(r3, r4)
            return r3
        L_0x0012:
            org.xmlpull.v1.XmlPullParserException r3 = new org.xmlpull.v1.XmlPullParserException
            java.lang.String r4 = "No start tag found"
            r3.<init>(r4)
            goto L_0x001b
        L_0x001a:
            throw r3
        L_0x001b:
            goto L_0x001a
        */
        throw new UnsupportedOperationException("Method not decompiled: p000a.p001a.p005c.p006a.p007a.C0028c.m96a(org.xmlpull.v1.XmlPullParser, android.content.res.Resources):a.a.c.a.a.c$a");
    }

    /* renamed from: a */
    public static List<List<byte[]>> m97a(Resources resources, int i) {
        if (i == 0) {
            return Collections.emptyList();
        }
        TypedArray obtainTypedArray = resources.obtainTypedArray(i);
        try {
            if (obtainTypedArray.length() == 0) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            if (m95a(obtainTypedArray, 0) == 1) {
                for (int i2 = 0; i2 < obtainTypedArray.length(); i2++) {
                    int resourceId = obtainTypedArray.getResourceId(i2, 0);
                    if (resourceId != 0) {
                        arrayList.add(m98a(resources.getStringArray(resourceId)));
                    }
                }
            } else {
                arrayList.add(m98a(resources.getStringArray(i)));
            }
            obtainTypedArray.recycle();
            return arrayList;
        } finally {
            obtainTypedArray.recycle();
        }
    }

    /* renamed from: a */
    private static List<byte[]> m98a(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (String decode : strArr) {
            arrayList.add(Base64.decode(decode, 0));
        }
        return arrayList;
    }

    /* renamed from: a */
    private static void m99a(XmlPullParser xmlPullParser) {
        int i = 1;
        while (i > 0) {
            int next = xmlPullParser.next();
            if (next == 2) {
                i++;
            } else if (next == 3) {
                i--;
            }
        }
    }

    /* renamed from: b */
    private static C0029a m100b(XmlPullParser xmlPullParser, Resources resources) {
        xmlPullParser.require(2, (String) null, "font-family");
        if (xmlPullParser.getName().equals("font-family")) {
            return m101c(xmlPullParser, resources);
        }
        m99a(xmlPullParser);
        return null;
    }

    /* renamed from: c */
    private static C0029a m101c(XmlPullParser xmlPullParser, Resources resources) {
        TypedArray obtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), C0002c.FontFamily);
        String string = obtainAttributes.getString(C0002c.FontFamily_fontProviderAuthority);
        String string2 = obtainAttributes.getString(C0002c.FontFamily_fontProviderPackage);
        String string3 = obtainAttributes.getString(C0002c.FontFamily_fontProviderQuery);
        int resourceId = obtainAttributes.getResourceId(C0002c.FontFamily_fontProviderCerts, 0);
        int integer = obtainAttributes.getInteger(C0002c.FontFamily_fontProviderFetchStrategy, 1);
        int integer2 = obtainAttributes.getInteger(C0002c.FontFamily_fontProviderFetchTimeout, 500);
        obtainAttributes.recycle();
        if (string == null || string2 == null || string3 == null) {
            ArrayList arrayList = new ArrayList();
            while (xmlPullParser.next() != 3) {
                if (xmlPullParser.getEventType() == 2) {
                    if (xmlPullParser.getName().equals("font")) {
                        arrayList.add(m102d(xmlPullParser, resources));
                    } else {
                        m99a(xmlPullParser);
                    }
                }
            }
            if (arrayList.isEmpty()) {
                return null;
            }
            return new C0030b((C0031c[]) arrayList.toArray(new C0031c[arrayList.size()]));
        }
        while (xmlPullParser.next() != 3) {
            m99a(xmlPullParser);
        }
        return new C0032d(new C0059a(string, string2, string3, m97a(resources, resourceId)), integer, integer2);
    }

    /* renamed from: d */
    private static C0031c m102d(XmlPullParser xmlPullParser, Resources resources) {
        TypedArray obtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), C0002c.FontFamilyFont);
        int i = obtainAttributes.getInt(obtainAttributes.hasValue(C0002c.FontFamilyFont_fontWeight) ? C0002c.FontFamilyFont_fontWeight : C0002c.FontFamilyFont_android_fontWeight, 400);
        boolean z = 1 == obtainAttributes.getInt(obtainAttributes.hasValue(C0002c.FontFamilyFont_fontStyle) ? C0002c.FontFamilyFont_fontStyle : C0002c.FontFamilyFont_android_fontStyle, 0);
        int i2 = obtainAttributes.hasValue(C0002c.FontFamilyFont_ttcIndex) ? C0002c.FontFamilyFont_ttcIndex : C0002c.FontFamilyFont_android_ttcIndex;
        String string = obtainAttributes.getString(obtainAttributes.hasValue(C0002c.FontFamilyFont_fontVariationSettings) ? C0002c.FontFamilyFont_fontVariationSettings : C0002c.FontFamilyFont_android_fontVariationSettings);
        int i3 = obtainAttributes.getInt(i2, 0);
        int i4 = obtainAttributes.hasValue(C0002c.FontFamilyFont_font) ? C0002c.FontFamilyFont_font : C0002c.FontFamilyFont_android_font;
        int resourceId = obtainAttributes.getResourceId(i4, 0);
        String string2 = obtainAttributes.getString(i4);
        obtainAttributes.recycle();
        while (xmlPullParser.next() != 3) {
            m99a(xmlPullParser);
        }
        return new C0031c(string2, i, z, string, i3, resourceId);
    }
}
