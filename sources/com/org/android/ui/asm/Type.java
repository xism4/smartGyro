package com.org.android.ui.asm;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.Base64;
import android.util.TypedValue;
import android.util.Xml;
import com.org.android.manager.h;
import com.org.provider.IpAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

public class Type {
    private static f a(XmlPullParser xmlPullParser, Resources resources) {
        TypedArray $r4 = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), IpAddress.FontFamilyFont);
        int $i0 = $r4.getInt($r4.hasValue(IpAddress.FontFamilyFont_fontWeight) ? IpAddress.FontFamilyFont_fontWeight : IpAddress.FontFamilyFont_android_fontWeight, 400);
        boolean $z0 = 1 == $r4.getInt($r4.hasValue(IpAddress.FontFamilyFont_fontStyle) ? IpAddress.FontFamilyFont_fontStyle : IpAddress.FontFamilyFont_android_fontStyle, 0);
        int $i1 = $r4.hasValue(IpAddress.FontFamilyFont_ttcIndex) ? IpAddress.FontFamilyFont_ttcIndex : IpAddress.FontFamilyFont_android_ttcIndex;
        String $r5 = $r4.getString($r4.hasValue(IpAddress.FontFamilyFont_fontVariationSettings) ? IpAddress.FontFamilyFont_fontVariationSettings : IpAddress.FontFamilyFont_android_fontVariationSettings);
        int $i12 = $r4.getInt($i1, 0);
        int $i3 = $r4.hasValue(IpAddress.FontFamilyFont_font) ? IpAddress.FontFamilyFont_font : IpAddress.FontFamilyFont_android_font;
        int $i2 = $r4.getResourceId($i3, 0);
        String $r6 = $r4.getString($i3);
        $r4.recycle();
        while (xmlPullParser.next() != 3) {
            a(xmlPullParser);
        }
        return new f($r6, $i0, $z0, $r5, $i12, $i2);
    }

    private static void a(XmlPullParser xmlPullParser) {
        int $i1 = 1;
        while ($i1 > 0) {
            int $i0 = xmlPullParser.next();
            if ($i0 == 2) {
                $i1++;
            } else if ($i0 == 3) {
                $i1--;
            }
        }
    }

    private static l create(XmlPullParser xmlPullParser, Resources resources) {
        xmlPullParser.require(2, (String) null, "font-family");
        if (xmlPullParser.getName().equals("font-family")) {
            return initialize(xmlPullParser, resources);
        }
        a(xmlPullParser);
        return null;
    }

    /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public static com.org.android.ui.asm.l createFromXml(org.xmlpull.v1.XmlPullParser r5, android.content.res.Resources r6) {
        /*
        L_0x0000:
            int r0 = r5.next()
            r1 = 2
            if (r0 == r1) goto L_0x000b
            r1 = 1
            if (r0 == r1) goto L_0x000b
            goto L_0x0000
        L_0x000b:
            r1 = 2
            if (r0 != r1) goto L_0x0013
            com.org.android.ui.asm.l r2 = create(r5, r6)
            return r2
        L_0x0013:
            org.xmlpull.v1.XmlPullParserException r3 = new org.xmlpull.v1.XmlPullParserException
            java.lang.String r4 = "No start tag found"
            r3.<init>(r4)
            goto L_0x001b
        L_0x001b:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.org.android.ui.asm.Type.createFromXml(org.xmlpull.v1.XmlPullParser, android.content.res.Resources):com.org.android.ui.asm.l");
    }

    private static int getType(TypedArray typedArray, int $i0) {
        if (Build.VERSION.SDK_INT >= 21) {
            return typedArray.getType($i0);
        }
        TypedValue $r1 = new TypedValue();
        typedArray.getValue($i0, $r1);
        return $r1.type;
    }

    public static List getValue(Resources resources, int $i0) {
        if ($i0 == 0) {
            return Collections.emptyList();
        }
        TypedArray $r2 = resources.obtainTypedArray($i0);
        try {
            if ($r2.length() == 0) {
                return Collections.emptyList();
            }
            ArrayList $r3 = new ArrayList();
            if (getType($r2, 0) == 1) {
                for (int $i02 = 0; $i02 < $r2.length(); $i02++) {
                    int $i1 = $r2.getResourceId($i02, 0);
                    if ($i1 != 0) {
                        $r3.add(read(resources.getStringArray($i1)));
                    }
                }
            } else {
                $r3.add(read(resources.getStringArray($i0)));
            }
            $r2.recycle();
            return $r3;
        } finally {
            $r2.recycle();
        }
    }

    private static l initialize(XmlPullParser xmlPullParser, Resources resources) {
        TypedArray $r4 = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), IpAddress.FontFamily);
        String $r5 = $r4.getString(IpAddress.FontFamily_fontProviderAuthority);
        String $r6 = $r4.getString(IpAddress.FontFamily_fontProviderPackage);
        String $r7 = $r4.getString(IpAddress.FontFamily_fontProviderQuery);
        int $i0 = $r4.getResourceId(IpAddress.FontFamily_fontProviderCerts, 0);
        int $i1 = $r4.getInteger(IpAddress.FontFamily_fontProviderFetchStrategy, 1);
        int $i2 = $r4.getInteger(IpAddress.FontFamily_fontProviderFetchTimeout, 500);
        $r4.recycle();
        if ($r5 == null || $r6 == null || $r7 == null) {
            ArrayList arrayList = new ArrayList();
            while (xmlPullParser.next() != 3) {
                if (xmlPullParser.getEventType() == 2) {
                    if (xmlPullParser.getName().equals("font")) {
                        arrayList.add(a(xmlPullParser, resources));
                    } else {
                        a(xmlPullParser);
                    }
                }
            }
            if (arrayList.isEmpty()) {
                return null;
            }
            return new e((f[]) arrayList.toArray(new f[arrayList.size()]));
        }
        while (xmlPullParser.next() != 3) {
            a(xmlPullParser);
        }
        return new b(new h($r5, $r6, $r7, getValue(resources, $i0)), $i1, $i2);
    }

    private static List read(String[] strArr) {
        ArrayList $r1 = new ArrayList();
        for (String $r3 : strArr) {
            $r1.add(Base64.decode($r3, 0));
        }
        return $r1;
    }
}
