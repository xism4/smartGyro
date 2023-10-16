package p000a.p001a.p005c.p006a.p007a;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import org.xmlpull.v1.XmlPullParser;

/* renamed from: a.a.c.a.a.i */
public class C0040i {
    /* renamed from: a */
    public static float m128a(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i, float f) {
        return !m134a(xmlPullParser, str) ? f : typedArray.getFloat(i, f);
    }

    /* renamed from: a */
    public static int m129a(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i, int i2) {
        return !m134a(xmlPullParser, str) ? i2 : typedArray.getColor(i, i2);
    }

    /* renamed from: a */
    public static C0027b m130a(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme, String str, int i, int i2) {
        if (m134a(xmlPullParser, str)) {
            TypedValue typedValue = new TypedValue();
            typedArray.getValue(i, typedValue);
            int i3 = typedValue.type;
            if (i3 >= 28 && i3 <= 31) {
                return C0027b.m83a(typedValue.data);
            }
            C0027b a = C0027b.m85a(typedArray.getResources(), typedArray.getResourceId(i, 0), theme);
            if (a != null) {
                return a;
            }
        }
        return C0027b.m83a(i2);
    }

    /* renamed from: a */
    public static TypedArray m131a(Resources resources, Resources.Theme theme, AttributeSet attributeSet, int[] iArr) {
        return theme == null ? resources.obtainAttributes(attributeSet, iArr) : theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
    }

    /* renamed from: a */
    public static String m132a(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i) {
        if (!m134a(xmlPullParser, str)) {
            return null;
        }
        return typedArray.getString(i);
    }

    /* renamed from: a */
    public static boolean m133a(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i, boolean z) {
        return !m134a(xmlPullParser, str) ? z : typedArray.getBoolean(i, z);
    }

    /* renamed from: a */
    public static boolean m134a(XmlPullParser xmlPullParser, String str) {
        return xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", str) != null;
    }

    /* renamed from: b */
    public static int m135b(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i, int i2) {
        return !m134a(xmlPullParser, str) ? i2 : typedArray.getInt(i, i2);
    }

    /* renamed from: b */
    public static TypedValue m136b(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i) {
        if (!m134a(xmlPullParser, str)) {
            return null;
        }
        return typedArray.peekValue(i);
    }

    /* renamed from: c */
    public static int m137c(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i, int i2) {
        return !m134a(xmlPullParser, str) ? i2 : typedArray.getResourceId(i, i2);
    }
}
