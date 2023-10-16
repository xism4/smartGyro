package com.org.android.ui.asm;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import org.xmlpull.v1.XmlPullParserException;

public final class Label {
    private int a;
    private final Shader e;
    private final ColorStateList w;

    private Label(Shader shader, ColorStateList colorStateList, int i) {
        this.e = shader;
        this.w = colorStateList;
        this.a = i;
    }

    static Label a(int i) {
        return new Label((Shader) null, (ColorStateList) null, i);
    }

    private static Label create(Resources resources, int i, Resources.Theme theme) {
        int $i0;
        XmlResourceParser $r2 = resources.getXml(i);
        AttributeSet $r3 = Xml.asAttributeSet($r2);
        do {
            $i0 = $r2.next();
            if ($i0 == 2 || $i0 == 1) {
            }
            $i0 = $r2.next();
            break;
        } while ($i0 == 1);
        if ($i0 == 2) {
            String $r4 = $r2.getName();
            byte $b1 = -1;
            int $i02 = $r4.hashCode();
            if ($i02 != 89650992) {
                if ($i02 == 1191572447 && $r4.equals("selector")) {
                    $b1 = 0;
                }
            } else if ($r4.equals("gradient")) {
                $b1 = 1;
            }
            if ($b1 == 0) {
                return init(Handler.create(resources, $r2, $r3, theme));
            }
            if ($b1 == 1) {
                return getPath(Status.inflate(resources, $r2, $r3, theme));
            }
            throw new XmlPullParserException($r2.getPositionDescription() + ": unsupported complex color tag " + $r4);
        }
        throw new XmlPullParserException("No start tag found");
    }

    static Label getPath(Shader shader) {
        return new Label(shader, (ColorStateList) null, 0);
    }

    static Label init(ColorStateList colorStateList) {
        return new Label((Shader) null, colorStateList, colorStateList.getDefaultColor());
    }

    public static Label read(Resources resources, int i, Resources.Theme theme) {
        try {
            return create(resources, i, theme);
        } catch (Exception $r3) {
            Log.e("ComplexColorCompat", "Failed to inflate ComplexColor.", $r3);
            return null;
        }
    }

    public boolean a() {
        return setStyle() || this.a != 0;
    }

    public boolean a(int[] iArr) {
        if (!b()) {
            return false;
        }
        ColorStateList $r2 = this.w;
        int $i0 = $r2.getColorForState(iArr, $r2.getDefaultColor());
        if ($i0 == this.a) {
            return false;
        }
        this.a = $i0;
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r1 = r4.w;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean b() {
        /*
            r4 = this;
            android.graphics.Shader r0 = r4.e
            if (r0 != 0) goto L_0x0010
            android.content.res.ColorStateList r1 = r4.w
            if (r1 == 0) goto L_0x0010
            boolean r2 = r1.isStateful()
            if (r2 == 0) goto L_0x0010
            r3 = 1
            return r3
        L_0x0010:
            r3 = 0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.org.android.ui.asm.Label.b():boolean");
    }

    public Shader e() {
        return this.e;
    }

    public int getColor() {
        return this.a;
    }

    public void setColor(int i) {
        this.a = i;
    }

    public boolean setStyle() {
        return this.e != null;
    }
}
