package com.org.android.ui.asm;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import org.xmlpull.v1.XmlPullParser;

public class TypedArrayUtils {
    public static Label a(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme, String str, int $i0, int i) {
        if (hasAttribute(xmlPullParser, str)) {
            TypedValue $r4 = new TypedValue();
            typedArray.getValue($i0, $r4);
            int $i2 = $r4.type;
            if ($i2 >= 28 && $i2 <= 31) {
                return Label.a($r4.data);
            }
            Label $r5 = Label.read(typedArray.getResources(), typedArray.getResourceId($i0, 0), theme);
            if ($r5 != null) {
                return $r5;
            }
        }
        return Label.a(i);
    }

    public static boolean getNamedBoolean(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i, boolean z) {
        return !hasAttribute(xmlPullParser, str) ? z : typedArray.getBoolean(i, z);
    }

    public static int getNamedColor(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i, int i2) {
        return !hasAttribute(xmlPullParser, str) ? i2 : typedArray.getColor(i, i2);
    }

    public static float getNamedFloat(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i, float f) {
        return !hasAttribute(xmlPullParser, str) ? f : typedArray.getFloat(i, f);
    }

    public static TypedValue getNamedFloat(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i) {
        if (!hasAttribute(xmlPullParser, str)) {
            return null;
        }
        return typedArray.peekValue(i);
    }

    public static int getResourceId(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i, int i2) {
        return !hasAttribute(xmlPullParser, str) ? i2 : typedArray.getResourceId(i, i2);
    }

    public static int getString(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i, int i2) {
        return !hasAttribute(xmlPullParser, str) ? i2 : typedArray.getInt(i, i2);
    }

    public static String getString(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i) {
        if (!hasAttribute(xmlPullParser, str)) {
            return null;
        }
        return typedArray.getString(i);
    }

    public static boolean hasAttribute(XmlPullParser xmlPullParser, String str) {
        return xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", str) != null;
    }

    public static TypedArray obtainAttributes(Resources resources, Resources.Theme theme, AttributeSet attributeSet, int[] iArr) {
        return theme == null ? resources.obtainAttributes(attributeSet, iArr) : theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
    }
}
