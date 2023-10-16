package com.org.android.ui.asm;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.LinearGradient;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import com.org.provider.IpAddress;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

final class Status {
    private static Item add(Item item, int i, int i2, boolean z, int i3) {
        return item != null ? item : z ? new Item(i, i3, i2) : new Item(i, i2);
    }

    private static Shader.TileMode create(int i) {
        return i != 1 ? i != 2 ? Shader.TileMode.CLAMP : Shader.TileMode.MIRROR : Shader.TileMode.REPEAT;
    }

    static Shader inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        String $r4 = xmlPullParser.getName();
        if ($r4.equals("gradient")) {
            TypedArray $r6 = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, IpAddress.GradientColor);
            float $f0 = TypedArrayUtils.getNamedFloat($r6, xmlPullParser, "startX", IpAddress.GradientColor_android_startX, 0.0f);
            float $f1 = TypedArrayUtils.getNamedFloat($r6, xmlPullParser, "startY", IpAddress.GradientColor_android_startY, 0.0f);
            float $f2 = TypedArrayUtils.getNamedFloat($r6, xmlPullParser, "endX", IpAddress.GradientColor_android_endX, 0.0f);
            float $f3 = TypedArrayUtils.getNamedFloat($r6, xmlPullParser, "endY", IpAddress.GradientColor_android_endY, 0.0f);
            float $f4 = TypedArrayUtils.getNamedFloat($r6, xmlPullParser, "centerX", IpAddress.GradientColor_android_centerX, 0.0f);
            float $f5 = TypedArrayUtils.getNamedFloat($r6, xmlPullParser, "centerY", IpAddress.GradientColor_android_centerY, 0.0f);
            int $i0 = TypedArrayUtils.getString($r6, xmlPullParser, "type", IpAddress.GradientColor_android_type, 0);
            int $i1 = TypedArrayUtils.getNamedColor($r6, xmlPullParser, "startColor", IpAddress.GradientColor_android_startColor, 0);
            boolean $z0 = TypedArrayUtils.hasAttribute(xmlPullParser, "centerColor");
            int $i2 = TypedArrayUtils.getNamedColor($r6, xmlPullParser, "centerColor", IpAddress.GradientColor_android_centerColor, 0);
            int $i3 = TypedArrayUtils.getNamedColor($r6, xmlPullParser, "endColor", IpAddress.GradientColor_android_endColor, 0);
            int $i4 = TypedArrayUtils.getString($r6, xmlPullParser, "tileMode", IpAddress.GradientColor_android_tileMode, 0);
            float $f6 = TypedArrayUtils.getNamedFloat($r6, xmlPullParser, "gradientRadius", IpAddress.GradientColor_android_gradientRadius, 0.0f);
            $r6.recycle();
            Item $r7 = add(parse(resources, xmlPullParser, attributeSet, theme), $i1, $i3, $z0, $i2);
            if ($i0 != 1) {
                if ($i0 != 2) {
                    return new LinearGradient($f0, $f1, $f2, $f3, $r7.k, $r7.a, create($i4));
                }
                return new SweepGradient($f4, $f5, $r7.k, $r7.a);
            } else if ($f6 > 0.0f) {
                return new RadialGradient($f4, $f5, $f6, $r7.k, $r7.a, create($i4));
            } else {
                throw new XmlPullParserException("<gradient> tag requires 'gradientRadius' attribute with radial type");
            }
        } else {
            throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": invalid gradient color tag " + $r4);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x00af, code lost:
        throw new org.xmlpull.v1.XmlPullParserException(r24.getPositionDescription() + ": <item> tag requires a 'color' attribute and a 'offset' " + "attribute!");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.org.android.ui.asm.Item parse(android.content.res.Resources r23, org.xmlpull.v1.XmlPullParser r24, android.util.AttributeSet r25, android.content.res.Resources.Theme r26) {
        /*
            r0 = r24
            int r3 = r0.getDepth()
            int r3 = r3 + 1
            java.util.ArrayList r4 = new java.util.ArrayList
            r5 = 20
            r4.<init>(r5)
            java.util.ArrayList r6 = new java.util.ArrayList
            r5 = 20
            r6.<init>(r5)
        L_0x0016:
            r0 = r24
            int r7 = r0.next()
            r5 = 1
            if (r7 == r5) goto L_0x00b0
            r0 = r24
            int r8 = r0.getDepth()
            if (r8 >= r3) goto L_0x002a
            r5 = 3
            if (r7 == r5) goto L_0x00b0
        L_0x002a:
            r5 = 2
            if (r7 == r5) goto L_0x002e
            goto L_0x0016
        L_0x002e:
            if (r8 > r3) goto L_0x0016
            r0 = r24
            java.lang.String r9 = r0.getName()
            java.lang.String r11 = "item"
            boolean r10 = r9.equals(r11)
            if (r10 != 0) goto L_0x003f
            goto L_0x0016
        L_0x003f:
            int[] r12 = com.org.provider.IpAddress.GradientColorItem
            r0 = r23
            r1 = r26
            r2 = r25
            android.content.res.TypedArray r13 = com.org.android.ui.asm.TypedArrayUtils.obtainAttributes(r0, r1, r2, r12)
            int r7 = com.org.provider.IpAddress.GradientColorItem_android_color
            boolean r10 = r13.hasValue(r7)
            int r7 = com.org.provider.IpAddress.GradientColorItem_android_offset
            boolean r14 = r13.hasValue(r7)
            if (r10 == 0) goto L_0x0082
            if (r14 == 0) goto L_0x0082
            int r7 = com.org.provider.IpAddress.GradientColorItem_android_color
            r5 = 0
            int r7 = r13.getColor(r7, r5)
            int r8 = com.org.provider.IpAddress.GradientColorItem_android_offset
            r16 = 0
            r0 = r16
            float r15 = r13.getFloat(r8, r0)
            r13.recycle()
            java.lang.Integer r17 = java.lang.Integer.valueOf(r7)
            r0 = r17
            r6.add(r0)
            java.lang.Float r18 = java.lang.Float.valueOf(r15)
            r0 = r18
            r4.add(r0)
            goto L_0x0016
        L_0x0082:
            org.xmlpull.v1.XmlPullParserException r19 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuilder r20 = new java.lang.StringBuilder
            r0 = r20
            r0.<init>()
            r0 = r24
            java.lang.String r9 = r0.getPositionDescription()
            r0 = r20
            r0.append(r9)
            java.lang.String r11 = ": <item> tag requires a 'color' attribute and a 'offset' "
            r0 = r20
            r0.append(r11)
            java.lang.String r11 = "attribute!"
            r0 = r20
            r0.append(r11)
            r0 = r20
            java.lang.String r9 = r0.toString()
            r0 = r19
            r0.<init>(r9)
            throw r19
        L_0x00b0:
            int r3 = r6.size()
            if (r3 <= 0) goto L_0x00be
            com.org.android.ui.asm.Item r21 = new com.org.android.ui.asm.Item
            r0 = r21
            r0.<init>((java.util.List) r6, (java.util.List) r4)
            return r21
        L_0x00be:
            r22 = 0
            return r22
        */
        throw new UnsupportedOperationException("Method not decompiled: com.org.android.ui.asm.Status.parse(android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.res.Resources$Theme):com.org.android.ui.asm.Item");
    }
}
