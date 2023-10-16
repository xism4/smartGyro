package org.cocos2dx.package_1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class Cocos2dxBitmap
{
  private static final int HORIZONTAL_ALIGN_CENTER = 3;
  private static final int HORIZONTAL_ALIGN_LEFT = 1;
  private static final int HORIZONTAL_ALIGN_RIGHT = 2;
  private static final int VERTICAL_ALIGN_BOTTOM = 2;
  private static final int VERTICAL_ALIGN_CENTER = 3;
  private static final int VERTICAL_ALIGN_TOP = 1;
  private static Context sContext;
  
  public Cocos2dxBitmap() {}
  
  public static Typeface calculateShrinkTypeFace(String paramString, int paramInt1, int paramInt2, Layout.Alignment paramAlignment, float paramFloat, TextPaint paramTextPaint, boolean paramBoolean)
  {
    if ((paramInt1 != 0) && (paramInt2 != 0))
    {
      float f1 = paramInt1 + 1;
      float f3 = paramInt2 + 1;
      float f5 = paramFloat + 1.0F;
      float f2 = f1;
      float f4 = f3;
      float f6 = f5;
      if (!paramBoolean) {
        while ((f1 > paramInt1) || (f3 > paramInt2))
        {
          f2 = f5 - 1.0F;
          f1 = (int)Math.ceil(Layout.getDesiredWidth(paramString, paramTextPaint));
          f3 = getTextHeight(paramString, (int)f1, f2, paramTextPaint.getTypeface());
          paramTextPaint.setTextSize(f2);
          f5 = f2;
          if (f2 <= 0.0F) {
            paramTextPaint.setTextSize(paramFloat);
          }
        }
      }
      label210:
      for (;;)
      {
        if ((f4 > paramInt2) || (f2 > paramInt1))
        {
          f6 -= 1.0F;
          StaticLayout localStaticLayout = new StaticLayout(paramString, paramTextPaint, paramInt1, paramAlignment, 1.0F, 0.0F, false);
          f2 = localStaticLayout.getWidth();
          f4 = localStaticLayout.getLineTop(localStaticLayout.getLineCount());
          paramTextPaint.setTextSize(f6);
          if (f6 > 0.0F) {
            break label210;
          }
          break;
        }
        return paramTextPaint.getTypeface();
      }
    }
    return paramTextPaint.getTypeface();
  }
  
  public static boolean createTextBitmapShadowStroke(byte[] paramArrayOfByte, String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, boolean paramBoolean1, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, boolean paramBoolean2, int paramInt9, int paramInt10, int paramInt11, int paramInt12, float paramFloat5, boolean paramBoolean3, int paramInt13)
  {
    if ((paramArrayOfByte != null) && (paramArrayOfByte.length != 0))
    {
      Object localObject2 = new String(paramArrayOfByte);
      Object localObject1 = Layout.Alignment.ALIGN_NORMAL;
      int j = paramInt6 & 0xF;
      paramArrayOfByte = (byte[])localObject1;
      if (j != 1) {
        if (j != 2)
        {
          if (j != 3) {
            paramArrayOfByte = (byte[])localObject1;
          } else {
            paramArrayOfByte = Layout.Alignment.ALIGN_CENTER;
          }
        }
        else {
          paramArrayOfByte = Layout.Alignment.ALIGN_OPPOSITE;
        }
      }
      localObject1 = newPaint(paramString, paramInt1);
      paramString = (String)localObject1;
      if (paramBoolean2)
      {
        paramString.setStyle(Paint.Style.STROKE);
        paramString.setStrokeWidth(paramFloat5);
      }
      if (paramInt7 <= 0) {
        i = (int)Math.ceil(Layout.getDesiredWidth((CharSequence)localObject2, paramString));
      } else {
        i = paramInt7;
      }
      if ((paramInt13 == 1) && (!paramBoolean3))
      {
        paramArrayOfByte = new StaticLayout((CharSequence)localObject2, paramString, (int)Math.ceil(Layout.getDesiredWidth((CharSequence)localObject2, paramString)), paramArrayOfByte, 1.0F, 0.0F, false);
      }
      else
      {
        if (paramInt13 == 2) {
          calculateShrinkTypeFace((String)localObject2, paramInt7, paramInt8, paramArrayOfByte, paramInt1, paramString, paramBoolean3);
        }
        paramString = (String)localObject1;
        paramArrayOfByte = new StaticLayout((CharSequence)localObject2, (TextPaint)localObject1, i, paramArrayOfByte, 1.0F, 0.0F, false);
      }
      paramInt1 = j;
      int m = paramArrayOfByte.getWidth();
      j = paramArrayOfByte.getLineTop(paramArrayOfByte.getLineCount());
      int k = Math.max(m, paramInt7);
      if (paramInt8 <= 0) {
        paramInt8 = j;
      }
      int i = k;
      if (paramInt13 == 1)
      {
        i = k;
        if (!paramBoolean3)
        {
          i = k;
          if (paramInt7 > 0) {
            i = paramInt7;
          }
        }
      }
      if ((i != 0) && (paramInt8 != 0))
      {
        if (paramInt1 == 3) {
          paramInt1 = (i - m) / 2;
        } else if (paramInt1 == 2) {
          paramInt1 = i - m;
        } else {
          paramInt1 = 0;
        }
        paramInt6 = paramInt6 >> 4 & 0xF;
        if (paramInt6 != 2)
        {
          if (paramInt6 != 3) {
            paramInt6 = 0;
          } else {
            paramInt6 = (paramInt8 - j) / 2;
          }
        }
        else {
          paramInt6 = paramInt8 - j;
        }
        localObject1 = Bitmap.createBitmap(i, paramInt8, Bitmap.Config.ARGB_8888);
        localObject2 = new Canvas((Bitmap)localObject1);
        ((Canvas)localObject2).translate(paramInt1, paramInt6);
        if (paramBoolean2)
        {
          paramString.setARGB(paramInt12, paramInt9, paramInt10, paramInt11);
          paramArrayOfByte.draw((Canvas)localObject2);
        }
        paramString.setStyle(Paint.Style.FILL);
        paramString.setARGB(paramInt5, paramInt2, paramInt3, paramInt4);
        paramArrayOfByte.draw((Canvas)localObject2);
        initNativeObject((Bitmap)localObject1);
        return true;
      }
      return false;
    }
    return false;
  }
  
  public static int getFontSizeAccordingHeight(int paramInt)
  {
    TextPaint localTextPaint = new TextPaint();
    Rect localRect = new Rect();
    localTextPaint.setTypeface(Typeface.DEFAULT);
    int j = 0;
    int i = 1;
    while (j == 0)
    {
      localTextPaint.setTextSize(i);
      localTextPaint.getTextBounds("SghMNy", 0, 6, localRect);
      int k = i + 1;
      i = k;
      if (paramInt - localRect.height() <= 2)
      {
        j = 1;
        i = k;
      }
    }
    return i;
  }
  
  private static byte[] getPixels(Bitmap paramBitmap)
  {
    if (paramBitmap != null)
    {
      byte[] arrayOfByte = new byte[paramBitmap.getWidth() * paramBitmap.getHeight() * 4];
      ByteBuffer localByteBuffer = ByteBuffer.wrap(arrayOfByte);
      localByteBuffer.order(ByteOrder.nativeOrder());
      paramBitmap.copyPixelsToBuffer(localByteBuffer);
      return arrayOfByte;
    }
    return null;
  }
  
  private static String getStringWithEllipsis(String paramString, float paramFloat1, float paramFloat2)
  {
    if (TextUtils.isEmpty(paramString)) {
      return "";
    }
    TextPaint localTextPaint = new TextPaint();
    localTextPaint.setTypeface(Typeface.DEFAULT);
    localTextPaint.setTextSize(paramFloat2);
    return TextUtils.ellipsize(paramString, localTextPaint, paramFloat1, TextUtils.TruncateAt.END).toString();
  }
  
  public static int getTextHeight(String paramString, int paramInt, float paramFloat, Typeface paramTypeface)
  {
    TextPaint localTextPaint = new TextPaint(129);
    localTextPaint.setTextSize(paramFloat);
    localTextPaint.setTypeface(paramTypeface);
    int k = paramString.length();
    int i = 0;
    int j = 0;
    while (i < k)
    {
      int m = localTextPaint.breakText(paramString, i, k, true, paramInt, null);
      if (m == 0)
      {
        i += 1;
      }
      else
      {
        i += m;
        j += 1;
      }
    }
    paramFloat = Math.abs(localTextPaint.ascent());
    float f = Math.abs(localTextPaint.descent());
    return (int)Math.floor(j * (paramFloat + f));
  }
  
  private static void initNativeObject(Bitmap paramBitmap)
  {
    byte[] arrayOfByte = getPixels(paramBitmap);
    if (arrayOfByte == null) {
      return;
    }
    nativeInitBitmapDC(paramBitmap.getWidth(), paramBitmap.getHeight(), arrayOfByte);
  }
  
  private static native void nativeInitBitmapDC(int paramInt1, int paramInt2, byte[] paramArrayOfByte);
  
  private static TextPaint newPaint(String paramString, int paramInt)
  {
    TextPaint localTextPaint = new TextPaint();
    localTextPaint.setTextSize(paramInt);
    localTextPaint.setAntiAlias(true);
    if (paramString.endsWith(".ttf")) {
      localObject = sContext;
    }
    try
    {
      localTextPaint.setTypeface(Cocos2dxTypefaces.get((Context)localObject, paramString));
      return localTextPaint;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("error to create ttf type face: ");
    ((StringBuilder)localObject).append(paramString);
    Log.e("Cocos2dxBitmap", ((StringBuilder)localObject).toString());
    localTextPaint.setTypeface(Typeface.create(paramString, 0));
    return localTextPaint;
  }
  
  public static void setContext(Context paramContext)
  {
    sContext = paramContext;
  }
}
