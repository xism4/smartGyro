package p000a.p001a.p005c.p006a.p007a;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* renamed from: a.a.c.a.a.b */
public final class C0027b {

    /* renamed from: a */
    private final Shader f101a;

    /* renamed from: b */
    private final ColorStateList f102b;

    /* renamed from: c */
    private int f103c;

    private C0027b(Shader shader, ColorStateList colorStateList, int i) {
        this.f101a = shader;
        this.f102b = colorStateList;
        this.f103c = i;
    }

    /* renamed from: a */
    static C0027b m83a(int i) {
        return new C0027b((Shader) null, (ColorStateList) null, i);
    }

    /* renamed from: a */
    static C0027b m84a(ColorStateList colorStateList) {
        return new C0027b((Shader) null, colorStateList, colorStateList.getDefaultColor());
    }

    /* renamed from: a */
    public static C0027b m85a(Resources resources, int i, Resources.Theme theme) {
        try {
            return m87b(resources, i, theme);
        } catch (Exception e) {
            Log.e("ComplexColorCompat", "Failed to inflate ComplexColor.", e);
            return null;
        }
    }

    /* renamed from: a */
    static C0027b m86a(Shader shader) {
        return new C0027b(shader, (ColorStateList) null, 0);
    }

    /* renamed from: b */
    private static C0027b m87b(Resources resources, int i, Resources.Theme theme) {
        int next;
        XmlResourceParser xml = resources.getXml(i);
        AttributeSet asAttributeSet = Xml.asAttributeSet(xml);
        do {
            next = xml.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next == 2) {
            String name = xml.getName();
            char c = 65535;
            int hashCode = name.hashCode();
            if (hashCode != 89650992) {
                if (hashCode == 1191572447 && name.equals("selector")) {
                    c = 0;
                }
            } else if (name.equals("gradient")) {
                c = 1;
            }
            if (c == 0) {
                return m84a(C0026a.m80a(resources, (XmlPullParser) xml, asAttributeSet, theme));
            }
            if (c == 1) {
                return m86a(C0033d.m115a(resources, xml, asAttributeSet, theme));
            }
            throw new XmlPullParserException(xml.getPositionDescription() + ": unsupported complex color tag " + name);
        }
        throw new XmlPullParserException("No start tag found");
    }

    /* renamed from: a */
    public int mo151a() {
        return this.f103c;
    }

    /* renamed from: a */
    public boolean mo152a(int[] iArr) {
        if (mo156d()) {
            ColorStateList colorStateList = this.f102b;
            int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
            if (colorForState != this.f103c) {
                this.f103c = colorForState;
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    public Shader mo153b() {
        return this.f101a;
    }

    /* renamed from: b */
    public void mo154b(int i) {
        this.f103c = i;
    }

    /* renamed from: c */
    public boolean mo155c() {
        return this.f101a != null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r1.f102b;
     */
    /* renamed from: d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo156d() {
        /*
            r1 = this;
            android.graphics.Shader r0 = r1.f101a
            if (r0 != 0) goto L_0x0010
            android.content.res.ColorStateList r0 = r1.f102b
            if (r0 == 0) goto L_0x0010
            boolean r0 = r0.isStateful()
            if (r0 == 0) goto L_0x0010
            r0 = 1
            goto L_0x0011
        L_0x0010:
            r0 = 0
        L_0x0011:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000a.p001a.p005c.p006a.p007a.C0027b.mo156d():boolean");
    }

    /* renamed from: e */
    public boolean mo157e() {
        return mo155c() || this.f103c != 0;
    }
}
