package org.cocos2dx.lib;

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
        String str2 = str;
        int i3 = i;
        int i4 = i2;
        float f2 = f;
        TextPaint textPaint2 = textPaint;
        if (i3 == 0 || i4 == 0) {
            return textPaint.getTypeface();
        }
        float f3 = (float) (i3 + 1);
        float f4 = (float) (i4 + 1);
        float f5 = f2 + 1.0f;
        if (!z) {
            while (true) {
                if (f3 <= ((float) i3) && f4 <= ((float) i4)) {
                    break;
                }
                f5 -= 1.0f;
                f3 = (float) ((int) Math.ceil((double) StaticLayout.getDesiredWidth(str2, textPaint2)));
                f4 = (float) getTextHeight(str2, (int) f3, f5, textPaint.getTypeface());
                textPaint2.setTextSize(f5);
                if (f5 <= 0.0f) {
                    break;
                }
            }
            return textPaint.getTypeface();
        }
        while (true) {
            if (f4 <= ((float) i4) && f3 <= ((float) i3)) {
                break;
            }
            float f6 = f5 - 1.0f;
            StaticLayout staticLayout = r0;
            StaticLayout staticLayout2 = new StaticLayout(str, textPaint, i, alignment, 1.0f, 0.0f, false);
            f3 = (float) staticLayout.getWidth();
            f4 = (float) staticLayout.getLineTop(staticLayout.getLineCount());
            textPaint2.setTextSize(f6);
            if (f6 <= 0.0f) {
                break;
            }
            f5 = f6;
        }
        return textPaint.getTypeface();
        textPaint2.setTextSize(f2);
        return textPaint.getTypeface();
    }

    public static boolean createTextBitmapShadowStroke(byte[] bArr, String str, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z, float f, float f2, float f3, float f4, boolean z2, int i9, int i10, int i11, int i12, float f5, boolean z3, int i13) {
        int i14;
        boolean z4;
        TextPaint textPaint;
        int i15;
        StaticLayout staticLayout;
        TextPaint textPaint2;
        byte[] bArr2 = bArr;
        int i16 = i7;
        int i17 = i13;
        if (bArr2 == null || bArr2.length == 0) {
            return false;
        }
        String str2 = new String(bArr2);
        Layout.Alignment alignment = Layout.Alignment.ALIGN_NORMAL;
        int i18 = i6 & 15;
        int i19 = 2;
        if (i18 != 1) {
            if (i18 == 2) {
                alignment = Layout.Alignment.ALIGN_OPPOSITE;
            } else if (i18 == 3) {
                alignment = Layout.Alignment.ALIGN_CENTER;
            }
        }
        Layout.Alignment alignment2 = alignment;
        TextPaint newPaint = newPaint(str, i);
        if (z2) {
            newPaint.setStyle(Paint.Style.STROKE);
            newPaint.setStrokeWidth(f5);
        }
        int ceil = i16 <= 0 ? (int) Math.ceil((double) StaticLayout.getDesiredWidth(str2, newPaint)) : i16;
        if (i17 != 1 || z3) {
            if (i17 == 2) {
                textPaint2 = newPaint;
                z4 = true;
                i14 = i18;
                calculateShrinkTypeFace(str2, i7, i8, alignment2, (float) i, textPaint2, z3);
            } else {
                textPaint2 = newPaint;
                i14 = i18;
                z4 = true;
            }
            i15 = 3;
            i19 = 2;
            textPaint = textPaint2;
            staticLayout = new StaticLayout(str2, textPaint2, ceil, alignment2, 1.0f, 0.0f, false);
        } else {
            staticLayout = new StaticLayout(str2, newPaint, (int) Math.ceil((double) StaticLayout.getDesiredWidth(str2, newPaint)), alignment2, 1.0f, 0.0f, false);
            textPaint = newPaint;
            i14 = i18;
            i15 = 3;
            z4 = true;
        }
        int width = staticLayout.getWidth();
        int lineTop = staticLayout.getLineTop(staticLayout.getLineCount());
        int max = Math.max(width, i16);
        int i20 = i8 > 0 ? i8 : lineTop;
        if (i17 == z4 && !z3 && i16 > 0) {
            max = i16;
        }
        if (max == 0 || i20 == 0) {
            return false;
        }
        int i21 = i14;
        int i22 = i21 == i15 ? (max - width) / i19 : i21 == i19 ? max - width : 0;
        int i23 = (i6 >> 4) & 15;
        int i24 = i23 != i19 ? i23 != i15 ? 0 : (i20 - lineTop) / i19 : i20 - lineTop;
        Bitmap createBitmap = Bitmap.createBitmap(max, i20, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.translate((float) i22, (float) i24);
        if (z2) {
            textPaint.setARGB(i12, i9, i10, i11);
            staticLayout.draw(canvas);
        }
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setARGB(i5, i2, i3, i4);
        staticLayout.draw(canvas);
        initNativeObject(createBitmap);
        return z4;
    }

    public static int getFontSizeAccordingHeight(int i) {
        TextPaint textPaint = new TextPaint();
        Rect rect = new Rect();
        textPaint.setTypeface(Typeface.DEFAULT);
        boolean z = false;
        int i2 = 1;
        while (!z) {
            textPaint.setTextSize((float) i2);
            textPaint.getTextBounds("SghMNy", 0, 6, rect);
            i2++;
            if (i - rect.height() <= 2) {
                z = true;
            }
        }
        return i2;
    }

    private static byte[] getPixels(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        byte[] bArr = new byte[(bitmap.getWidth() * bitmap.getHeight() * 4)];
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.nativeOrder());
        bitmap.copyPixelsToBuffer(wrap);
        return bArr;
    }

    private static String getStringWithEllipsis(String str, float f, float f2) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        TextPaint textPaint = new TextPaint();
        textPaint.setTypeface(Typeface.DEFAULT);
        textPaint.setTextSize(f2);
        return TextUtils.ellipsize(str, textPaint, f, TextUtils.TruncateAt.END).toString();
    }

    public static int getTextHeight(String str, int i, float f, Typeface typeface) {
        TextPaint textPaint = new TextPaint(129);
        textPaint.setTextSize(f);
        textPaint.setTypeface(typeface);
        int length = str.length();
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int breakText = textPaint.breakText(str, i2, length, true, (float) i, (float[]) null);
            if (breakText == 0) {
                i2++;
            } else {
                i2 += breakText;
                i3++;
            }
        }
        return (int) Math.floor((double) (((float) i3) * (Math.abs(textPaint.ascent()) + Math.abs(textPaint.descent()))));
    }

    private static void initNativeObject(Bitmap bitmap) {
        byte[] pixels = getPixels(bitmap);
        if (pixels != null) {
            nativeInitBitmapDC(bitmap.getWidth(), bitmap.getHeight(), pixels);
        }
    }

    private static native void nativeInitBitmapDC(int i, int i2, byte[] bArr);

    private static TextPaint newPaint(String str, int i) {
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize((float) i);
        textPaint.setAntiAlias(true);
        if (str.endsWith(".ttf")) {
            try {
                textPaint.setTypeface(Cocos2dxTypefaces.get(sContext, str));
            } catch (Exception unused) {
                Log.e("Cocos2dxBitmap", "error to create ttf type face: " + str);
            }
            return textPaint;
        }
        textPaint.setTypeface(Typeface.create(str, 0));
        return textPaint;
    }

    public static void setContext(Context context) {
        sContext = context;
    }
}
