package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Parcelable;
import android.util.Log;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;

public class IconCompat extends CustomVersionedParcelable {
    static final PorterDuff.Mode d = PorterDuff.Mode.SRC_IN;
    Object a;
    public Parcelable b;
    public int c;
    PorterDuff.Mode e = d;
    public ColorStateList i = null;
    public int p;
    public int r;
    public byte[] s;
    public String x;

    private static int a(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getResId();
        }
        try {
            return ((Integer) icon.getClass().getMethod("getResId", new Class[0]).invoke(icon, new Object[0])).intValue();
        } catch (IllegalAccessException $r9) {
            Log.e("IconCompat", "Unable to get icon resource", $r9);
            return 0;
        } catch (InvocationTargetException $r8) {
            Log.e("IconCompat", "Unable to get icon resource", $r8);
            return 0;
        } catch (NoSuchMethodException $r7) {
            Log.e("IconCompat", "Unable to get icon resource", $r7);
            return 0;
        }
    }

    private static String getType(int i2) {
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? i2 != 5 ? "UNKNOWN" : "BITMAP_MASKABLE" : "URI" : "DATA" : "RESOURCE" : "BITMAP";
    }

    private static String init(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getResPackage();
        }
        try {
            return (String) icon.getClass().getMethod("getResPackage", new Class[0]).invoke(icon, new Object[0]);
        } catch (IllegalAccessException $r9) {
            Log.e("IconCompat", "Unable to get icon package", $r9);
            return null;
        } catch (InvocationTargetException $r8) {
            Log.e("IconCompat", "Unable to get icon package", $r8);
            return null;
        } catch (NoSuchMethodException $r7) {
            Log.e("IconCompat", "Unable to get icon package", $r7);
            return null;
        }
    }

    public int a() {
        if (this.c == -1 && Build.VERSION.SDK_INT >= 23) {
            return a((Icon) this.a);
        }
        if (this.c == 2) {
            return this.r;
        }
        throw new IllegalStateException("called getResId() on " + this);
    }

    public void encode(boolean z) {
        this.x = this.e.name();
        int $i0 = this.c;
        if ($i0 != -1) {
            if ($i0 != 1) {
                if ($i0 == 2) {
                    this.s = ((String) this.a).getBytes(Charset.forName("UTF-16"));
                    return;
                } else if ($i0 == 3) {
                    this.s = (byte[]) this.a;
                    return;
                } else if ($i0 == 4) {
                    this.s = this.a.toString().getBytes(Charset.forName("UTF-16"));
                    return;
                } else if ($i0 != 5) {
                    return;
                }
            }
            if (z) {
                ByteArrayOutputStream $r7 = new ByteArrayOutputStream();
                ((Bitmap) this.a).compress(Bitmap.CompressFormat.PNG, 90, $r7);
                this.s = $r7.toByteArray();
                return;
            }
        } else if (z) {
            throw new IllegalArgumentException("Can't serialize Icon created with IconCompat#createFromIcon");
        }
        this.b = (Parcelable) this.a;
    }

    public String get() {
        if (this.c == -1 && Build.VERSION.SDK_INT >= 23) {
            return init((Icon) this.a);
        }
        if (this.c == 2) {
            return ((String) this.a).split(":", -1)[0];
        }
        throw new IllegalStateException("called getResPackage() on " + this);
    }

    public void init() {
        Parcelable $r5;
        this.e = PorterDuff.Mode.valueOf(this.x);
        int $i0 = this.c;
        if ($i0 != -1) {
            if ($i0 != 1) {
                if ($i0 != 2) {
                    if ($i0 == 3) {
                        this.a = this.s;
                        return;
                    } else if ($i0 != 4) {
                        if ($i0 != 5) {
                            return;
                        }
                    }
                }
                this.a = new String(this.s, Charset.forName("UTF-16"));
                return;
            }
            $r5 = this.b;
            if ($r5 == null) {
                byte[] $r3 = this.s;
                this.a = $r3;
                this.c = 3;
                this.r = 0;
                this.p = $r3.length;
                return;
            }
        } else {
            $r5 = this.b;
            if ($r5 == null) {
                throw new IllegalArgumentException("Invalid icon");
            }
        }
        this.a = $r5;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002b, code lost:
        if (r0 != 5) goto L_0x009c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00b0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String toString() {
        /*
            r14 = this;
            int r0 = r14.c
            r1 = -1
            if (r0 != r1) goto L_0x000c
            java.lang.Object r2 = r14.a
            java.lang.String r3 = java.lang.String.valueOf(r2)
            return r3
        L_0x000c:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Icon(typ="
            r4.<init>(r5)
            int r0 = r14.c
            java.lang.String r3 = getType(r0)
            r4.append(r3)
            int r0 = r14.c
            r1 = 1
            if (r0 == r1) goto L_0x0078
            r1 = 2
            if (r0 == r1) goto L_0x004f
            r1 = 3
            if (r0 == r1) goto L_0x0039
            r1 = 4
            if (r0 == r1) goto L_0x002e
            r1 = 5
            if (r0 == r1) goto L_0x0078
            goto L_0x009c
        L_0x002e:
            java.lang.String r5 = " uri="
            r4.append(r5)
            java.lang.Object r2 = r14.a
            r4.append(r2)
            goto L_0x009c
        L_0x0039:
            java.lang.String r5 = " len="
            r4.append(r5)
            int r0 = r14.r
            r4.append(r0)
            int r0 = r14.p
            if (r0 == 0) goto L_0x009c
            java.lang.String r5 = " off="
            r4.append(r5)
            int r0 = r14.p
            goto L_0x0099
        L_0x004f:
            java.lang.String r5 = " pkg="
            r4.append(r5)
            java.lang.String r3 = r14.get()
            r4.append(r3)
            java.lang.String r5 = " id="
            r4.append(r5)
            r1 = 1
            java.lang.Object[] r6 = new java.lang.Object[r1]
            int r0 = r14.a()
            java.lang.Integer r7 = java.lang.Integer.valueOf(r0)
            r1 = 0
            r6[r1] = r7
            java.lang.String r5 = "0x%08x"
            java.lang.String r3 = java.lang.String.format(r5, r6)
            r4.append(r3)
            goto L_0x009c
        L_0x0078:
            java.lang.String r5 = " size="
            r4.append(r5)
            java.lang.Object r2 = r14.a
            r9 = r2
            android.graphics.Bitmap r9 = (android.graphics.Bitmap) r9
            r8 = r9
            int r0 = r8.getWidth()
            r4.append(r0)
            java.lang.String r5 = "x"
            r4.append(r5)
            java.lang.Object r2 = r14.a
            r10 = r2
            android.graphics.Bitmap r10 = (android.graphics.Bitmap) r10
            r8 = r10
            int r0 = r8.getHeight()
        L_0x0099:
            r4.append(r0)
        L_0x009c:
            android.content.res.ColorStateList r11 = r14.i
            if (r11 == 0) goto L_0x00aa
            java.lang.String r5 = " tint="
            r4.append(r5)
            android.content.res.ColorStateList r11 = r14.i
            r4.append(r11)
        L_0x00aa:
            android.graphics.PorterDuff$Mode r12 = r14.e
            android.graphics.PorterDuff$Mode r13 = d
            if (r12 == r13) goto L_0x00ba
            java.lang.String r5 = " mode="
            r4.append(r5)
            android.graphics.PorterDuff$Mode r12 = r14.e
            r4.append(r12)
        L_0x00ba:
            java.lang.String r5 = ")"
            r4.append(r5)
            java.lang.String r3 = r4.toString()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.drawable.IconCompat.toString():java.lang.String");
    }
}
