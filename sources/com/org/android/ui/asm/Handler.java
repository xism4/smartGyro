package com.org.android.ui.asm;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class Handler {
    public static ColorStateList create(Resources resources, XmlPullParser xmlPullParser, Resources.Theme theme) {
        int $i0;
        AttributeSet $r3 = Xml.asAttributeSet(xmlPullParser);
        do {
            $i0 = xmlPullParser.next();
            if ($i0 == 2 || $i0 == 1) {
            }
            $i0 = xmlPullParser.next();
            break;
        } while ($i0 == 1);
        if ($i0 == 2) {
            return create(resources, xmlPullParser, $r3, theme);
        }
        throw new XmlPullParserException("No start tag found");
    }

    public static ColorStateList create(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        String $r4 = xmlPullParser.getName();
        if ($r4.equals("selector")) {
            return init(resources, xmlPullParser, attributeSet, theme);
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": invalid color state list tag " + $r4);
    }

    private static int getThemeAttrColor(int i, float f) {
        return (i & 16777215) | (Math.round(((float) Color.alpha(i)) * f) << 24);
    }

    /* JADX WARNING: type inference failed for: r22v0, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0084  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.content.res.ColorStateList init(android.content.res.Resources r27, org.xmlpull.v1.XmlPullParser r28, android.util.AttributeSet r29, android.content.res.Resources.Theme r30) {
        /*
            r0 = r28
            int r3 = r0.getDepth()
            int r3 = r3 + 1
            r5 = 20
            int[][] r4 = new int[r5][]
            int r6 = r4.length
            int[] r7 = new int[r6]
            r6 = 0
        L_0x0010:
            r0 = r28
            int r8 = r0.next()
            r5 = 1
            if (r8 == r5) goto L_0x00de
            r0 = r28
            int r9 = r0.getDepth()
            if (r9 >= r3) goto L_0x0024
            r5 = 3
            if (r8 == r5) goto L_0x00de
        L_0x0024:
            r5 = 2
            if (r8 != r5) goto L_0x00db
            if (r9 > r3) goto L_0x00db
            r0 = r28
            java.lang.String r10 = r0.getName()
            java.lang.String r12 = "item"
            boolean r11 = r10.equals(r12)
            if (r11 != 0) goto L_0x003a
            goto L_0x00db
        L_0x003a:
            int[] r13 = com.org.provider.IpAddress.ColorStateListItem
            r0 = r27
            r1 = r30
            r2 = r29
            android.content.res.TypedArray r14 = obtainAttributes(r0, r1, r2, r13)
            int r8 = com.org.provider.IpAddress.ColorStateListItem_android_color
            r5 = -65281(0xffffffffffff00ff, float:NaN)
            int r8 = r14.getColor(r8, r5)
            r15 = 1065353216(0x3f800000, float:1.0)
            int r9 = com.org.provider.IpAddress.ColorStateListItem_android_alpha
            boolean r11 = r14.hasValue(r9)
            if (r11 == 0) goto L_0x0066
            int r9 = com.org.provider.IpAddress.ColorStateListItem_android_alpha
        L_0x005c:
            r16 = 1065353216(0x3f800000, float:1.0)
            r0 = r16
            float r15 = r14.getFloat(r9, r0)
            goto L_0x0071
        L_0x0066:
            int r9 = com.org.provider.IpAddress.ColorStateListItem_alpha
            boolean r11 = r14.hasValue(r9)
            if (r11 == 0) goto L_0x0071
            int r9 = com.org.provider.IpAddress.ColorStateListItem_alpha
            goto L_0x005c
        L_0x0071:
            r14.recycle()
            r0 = r29
            int r9 = r0.getAttributeCount()
            int[] r13 = new int[r9]
            r17 = 0
            r18 = 0
        L_0x0080:
            r0 = r17
            if (r0 >= r9) goto L_0x00be
            r0 = r29
            r1 = r17
            int r19 = r0.getAttributeNameResource(r1)
            r20 = r19
            r5 = 16843173(0x10101a5, float:2.3694738E-38)
            r0 = r19
            if (r0 == r5) goto L_0x00bb
            r5 = 16843551(0x101031f, float:2.3695797E-38)
            r0 = r19
            if (r0 == r5) goto L_0x00bb
            int r21 = com.org.provider.DocumentFont.alpha
            r0 = r19
            r1 = r21
            if (r0 == r1) goto L_0x00bb
            int r21 = r18 + 1
            r5 = 0
            r0 = r29
            r1 = r17
            boolean r11 = r0.getAttributeBooleanValue(r1, r5)
            if (r11 == 0) goto L_0x00b2
            goto L_0x00b7
        L_0x00b2:
            r0 = r19
            int r0 = -r0
            r20 = r0
        L_0x00b7:
            r13[r18] = r20
            r18 = r21
        L_0x00bb:
            int r17 = r17 + 1
            goto L_0x0080
        L_0x00be:
            r0 = r18
            int[] r13 = android.util.StateSet.trimStateSet(r13, r0)
            int r8 = getThemeAttrColor(r8, r15)
            if (r6 == 0) goto L_0x00ca
        L_0x00ca:
            int[] r7 = com.org.android.ui.asm.ByteVector.get(r7, r6, r8)
            java.lang.Object[] r22 = com.org.android.ui.asm.ByteVector.read(r4, r6, r13)
            r23 = r22
            int[][] r23 = (int[][]) r23
            r4 = r23
            int r6 = r6 + 1
            goto L_0x00db
        L_0x00db:
            goto L_0x0010
        L_0x00de:
            int[] r13 = new int[r6]
            int[][] r0 = new int[r6][]
            r24 = r0
            r5 = 0
            r25 = 0
            r0 = r25
            java.lang.System.arraycopy(r7, r5, r13, r0, r6)
            r5 = 0
            r25 = 0
            r0 = r24
            r1 = r25
            java.lang.System.arraycopy(r4, r5, r0, r1, r6)
            android.content.res.ColorStateList r26 = new android.content.res.ColorStateList
            r0 = r26
            r1 = r24
            r0.<init>(r1, r13)
            return r26
        */
        throw new UnsupportedOperationException("Method not decompiled: com.org.android.ui.asm.Handler.init(android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.res.Resources$Theme):android.content.res.ColorStateList");
    }

    private static TypedArray obtainAttributes(Resources resources, Resources.Theme theme, AttributeSet attributeSet, int[] iArr) {
        return theme == null ? resources.obtainAttributes(attributeSet, iArr) : theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
    }
}
