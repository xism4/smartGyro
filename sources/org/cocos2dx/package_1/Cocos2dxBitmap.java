package org.cocos2dx.package_1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class Cocos2dxBitmap {
    private static final int HORIZONTAL_ALIGN_CENTER = 3;
    private static final int HORIZONTAL_ALIGN_LEFT = 1;
    private static final int HORIZONTAL_ALIGN_RIGHT = 2;
    private static final int VERTICAL_ALIGN_BOTTOM = 2;
    private static final int VERTICAL_ALIGN_CENTER = 3;
    private static final int VERTICAL_ALIGN_TOP = 1;
    private static Context sContext;

    public static Typeface calculateShrinkTypeFace(String str, int i, int i2, Layout.Alignment alignment, float f, TextPaint textPaint, boolean z) {
        if (i == 0 || i2 == 0) {
            return textPaint.getTypeface();
        }
        float $f2 = (float) (i + 1);
        float $f3 = (float) (i2 + 1);
        float $f1 = f + 1.0f;
        if (!z) {
            while (true) {
                if ($f2 <= ((float) i) && $f3 <= ((float) i2)) {
                    break;
                }
                $f1 -= 1.0f;
                double $d0 = (double) Layout.getDesiredWidth(str, textPaint);
                double d = $d0;
                $f2 = (float) ((int) Math.ceil($d0));
                String str2 = str;
                $f3 = (float) getTextHeight(str2, (int) $f2, $f1, textPaint.getTypeface());
                textPaint.setTextSize($f1);
                if ($f1 <= 0.0f) {
                    break;
                }
            }
        } else {
            while (true) {
                if ($f3 <= ((float) i2) && $f2 <= ((float) i)) {
                    break;
                }
                $f1 -= 1.0f;
                StaticLayout staticLayout = new StaticLayout(str, textPaint, i, alignment, 1.0f, 0.0f, false);
                $f2 = (float) staticLayout.getWidth();
                $f3 = (float) staticLayout.getLineTop(staticLayout.getLineCount());
                textPaint.setTextSize($f1);
                if ($f1 <= 0.0f) {
                    break;
                }
            }
            return textPaint.getTypeface();
        }
        textPaint.setTextSize(f);
        return textPaint.getTypeface();
    }

    public static boolean createTextBitmapShadowStroke(byte[] bArr, String str, int $i0, int i, int i2, int i3, int i4, int i5, int i6, int $i7, boolean z, float f, float f2, float f3, float f4, boolean z2, int i7, int i8, int i9, int i10, float f5, boolean z3, int i11) {
        int $i14;
        StaticLayout $r6;
        TextPaint $r7;
        if (bArr == null || bArr.length == 0) {
            return false;
        }
        String $r2 = new String(bArr);
        Layout.Alignment $r3 = Layout.Alignment.ALIGN_NORMAL;
        int $i13 = i5 & 15;
        if ($i13 != 1) {
            if ($i13 == 2) {
                $r3 = Layout.Alignment.ALIGN_OPPOSITE;
            } else if ($i13 == 3) {
                $r3 = Layout.Alignment.ALIGN_CENTER;
            }
        }
        TextPaint $r4 = newPaint(str, $i0);
        if (z2) {
            $r4.setStyle(Paint.Style.STROKE);
            $r4.setStrokeWidth(f5);
        }
        int $i15 = i6 <= 0 ? (int) Math.ceil((double) Layout.getDesiredWidth($r2, $r4)) : i6;
        if (i11 != 1 || z3) {
            if (i11 == 2) {
                $r7 = $r4;
                $i14 = $i13;
                calculateShrinkTypeFace($r2, i6, $i7, $r3, (float) $i0, $r4, z3);
            } else {
                $r7 = $r4;
                $i14 = $i13;
            }
            $r4 = $r7;
            $r6 = new StaticLayout($r2, $r7, $i15, $r3, 1.0f, 0.0f, false);
        } else {
            $r6 = new StaticLayout($r2, $r4, (int) Math.ceil((double) Layout.getDesiredWidth($r2, $r4)), $r3, 1.0f, 0.0f, false);
            $i14 = $i13;
        }
        int $i152 = $r6.getWidth();
        int $i132 = $r6.getLineTop($r6.getLineCount());
        int $i02 = Math.max($i152, i6);
        if ($i7 <= 0) {
            $i7 = $i132;
        }
        if (i11 == 1 && !z3 && i6 > 0) {
            $i02 = i6;
        }
        if ($i02 == 0 || $i7 == 0) {
            return false;
        }
        int $i6 = $i14 == 3 ? ($i02 - $i152) / 2 : $i14 == 2 ? $i02 - $i152 : 0;
        int $i5 = (i5 >> 4) & 15;
        int $i52 = $i5 != 2 ? $i5 != 3 ? 0 : ($i7 - $i132) / 2 : $i7 - $i132;
        Bitmap $r9 = Bitmap.createBitmap($i02, $i7, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas($r9);
        canvas.translate((float) $i6, (float) $i52);
        if (z2) {
            $r4.setARGB(i10, i7, i8, i9);
            $r6.draw(canvas);
        }
        $r4.setStyle(Paint.Style.FILL);
        $r4.setARGB(i4, i, i2, i3);
        $r6.draw(canvas);
        initNativeObject($r9);
        return true;
    }

    public static int getFontSizeAccordingHeight(int i) {
        TextPaint $r0 = new TextPaint();
        Rect $r1 = new Rect();
        $r0.setTypeface(Typeface.DEFAULT);
        boolean $z0 = false;
        int $i2 = 1;
        while (!$z0) {
            $r0.setTextSize((float) $i2);
            $r0.getTextBounds("SghMNy", 0, 6, $r1);
            $i2++;
            if (i - $r1.height() <= 2) {
                $z0 = true;
            }
        }
        return $i2;
    }

    private static byte[] getPixels(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        byte[] $r1 = new byte[(bitmap.getWidth() * bitmap.getHeight() * 4)];
        ByteBuffer $r2 = ByteBuffer.wrap($r1);
        $r2.order(ByteOrder.nativeOrder());
        bitmap.copyPixelsToBuffer($r2);
        return $r1;
    }

    private static String getStringWithEllipsis(String str, float f, float f2) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        TextPaint $r2 = new TextPaint();
        $r2.setTypeface(Typeface.DEFAULT);
        $r2.setTextSize(f2);
        return TextUtils.ellipsize(str, $r2, f, TextUtils.TruncateAt.END).toString();
    }

    public static int getTextHeight(String str, int i, float f, Typeface typeface) {
        TextPaint $r0 = new TextPaint(129);
        $r0.setTextSize(f);
        $r0.setTypeface(typeface);
        int $i1 = str.length();
        int $i2 = 0;
        int $i3 = 0;
        while ($i2 < $i1) {
            int $i4 = $r0.breakText(str, $i2, $i1, true, (float) i, (float[]) null);
            if ($i4 == 0) {
                $i2++;
            } else {
                $i2 += $i4;
                $i3++;
            }
        }
        float $f1 = (float) $i3;
        double $d0 = (double) ($f1 * (Math.abs($r0.ascent()) + Math.abs($r0.descent())));
        double d = $d0;
        return (int) Math.floor($d0);
    }

    private static void initNativeObject(Bitmap bitmap) {
        byte[] $r1 = getPixels(bitmap);
        if ($r1 != null) {
            nativeInitBitmapDC(bitmap.getWidth(), bitmap.getHeight(), $r1);
        }
    }

    private static native void nativeInitBitmapDC(int i, int i2, byte[] bArr);

    private static TextPaint newPaint(String str, int i) {
        TextPaint $r0 = new TextPaint();
        $r0.setTextSize((float) i);
        $r0.setAntiAlias(true);
        if (str.endsWith(".ttf")) {
            try {
                $r0.setTypeface(Cocos2dxTypefaces.get(sContext, str));
                return $r0;
            } catch (Exception e) {
                Log.e("Cocos2dxBitmap", "error to create ttf type face: " + str);
            }
        }
        $r0.setTypeface(Typeface.create(str, 0));
        return $r0;
    }

    public static void setContext(Context context) {
        sContext = context;
    }
}
