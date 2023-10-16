package com.org.shortcuts.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.animation.Interpolator;
import com.org.android.asm.PathParser;
import com.org.android.ui.asm.TypedArrayUtils;
import org.xmlpull.v1.XmlPullParser;

public class PathInterpolatorDonut
  implements Interpolator
{
  private float[] mX;
  private float[] mY;
  
  public PathInterpolatorDonut(Context paramContext, AttributeSet paramAttributeSet, XmlPullParser paramXmlPullParser)
  {
    this(paramContext.getResources(), paramContext.getTheme(), paramAttributeSet, paramXmlPullParser);
  }
  
  public PathInterpolatorDonut(Resources paramResources, Resources.Theme paramTheme, AttributeSet paramAttributeSet, XmlPullParser paramXmlPullParser)
  {
    paramResources = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.Keyboard_Row);
    inflate(paramResources, paramXmlPullParser);
    paramResources.recycle();
  }
  
  private void create(float paramFloat1, float paramFloat2)
  {
    Path localPath = new Path();
    localPath.moveTo(0.0F, 0.0F);
    localPath.quadTo(paramFloat1, paramFloat2, 1.0F, 1.0F);
    update(localPath);
  }
  
  private void draw(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    Path localPath = new Path();
    localPath.moveTo(0.0F, 0.0F);
    localPath.cubicTo(paramFloat1, paramFloat2, paramFloat3, paramFloat4, 1.0F, 1.0F);
    update(localPath);
  }
  
  private void inflate(TypedArray paramTypedArray, XmlPullParser paramXmlPullParser)
  {
    if (TypedArrayUtils.hasAttribute(paramXmlPullParser, "pathData"))
    {
      paramTypedArray = TypedArrayUtils.getString(paramTypedArray, paramXmlPullParser, "pathData", 4);
      paramXmlPullParser = PathParser.createPathFromPathData(paramTypedArray);
      if (paramXmlPullParser != null)
      {
        update(paramXmlPullParser);
        return;
      }
      paramXmlPullParser = new StringBuilder();
      paramXmlPullParser.append("The path is null, which is created from ");
      paramXmlPullParser.append(paramTypedArray);
      throw new InflateException(paramXmlPullParser.toString());
    }
    if (TypedArrayUtils.hasAttribute(paramXmlPullParser, "controlX1"))
    {
      if (TypedArrayUtils.hasAttribute(paramXmlPullParser, "controlY1"))
      {
        float f1 = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "controlX1", 0, 0.0F);
        float f2 = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "controlY1", 1, 0.0F);
        boolean bool = TypedArrayUtils.hasAttribute(paramXmlPullParser, "controlX2");
        if (bool == TypedArrayUtils.hasAttribute(paramXmlPullParser, "controlY2"))
        {
          if (!bool)
          {
            create(f1, f2);
            return;
          }
          draw(f1, f2, TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "controlX2", 2, 0.0F), TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "controlY2", 3, 0.0F));
          return;
        }
        throw new InflateException("pathInterpolator requires both controlX2 and controlY2 for cubic Beziers.");
      }
      throw new InflateException("pathInterpolator requires the controlY1 attribute");
    }
    throw new InflateException("pathInterpolator requires the controlX1 attribute");
  }
  
  private void update(Path paramPath)
  {
    int j = 0;
    paramPath = new PathMeasure(paramPath, false);
    float f1 = paramPath.getLength();
    int k = Math.min(3000, (int)(f1 / 0.002F) + 1);
    if (k > 0)
    {
      mX = new float[k];
      mY = new float[k];
      float[] arrayOfFloat = new float[2];
      int i = 0;
      while (i < k)
      {
        paramPath.getPosTan(i * f1 / (k - 1), arrayOfFloat, null);
        mX[i] = arrayOfFloat[0];
        mY[i] = arrayOfFloat[1];
        i += 1;
      }
      if ((Math.abs(mX[0]) <= 1.0E-5D) && (Math.abs(mY[0]) <= 1.0E-5D))
      {
        arrayOfFloat = mX;
        i = k - 1;
        if ((Math.abs(arrayOfFloat[i] - 1.0F) <= 1.0E-5D) && (Math.abs(mY[i] - 1.0F) <= 1.0E-5D))
        {
          i = 0;
          f1 = 0.0F;
          while (j < k)
          {
            arrayOfFloat = mX;
            float f2 = arrayOfFloat[i];
            if (f2 >= f1)
            {
              arrayOfFloat[j] = f2;
              j += 1;
              f1 = f2;
              i += 1;
            }
            else
            {
              paramPath = new StringBuilder();
              paramPath.append("The Path cannot loop back on itself, x :");
              paramPath.append(f2);
              throw new IllegalArgumentException(paramPath.toString());
            }
          }
          if (!paramPath.nextContour()) {
            return;
          }
          throw new IllegalArgumentException("The Path should be continuous, can't have 2+ contours");
        }
      }
      paramPath = new StringBuilder();
      paramPath.append("The Path must start at (0,0) and end at (1,1) start: ");
      paramPath.append(mX[0]);
      paramPath.append(",");
      paramPath.append(mY[0]);
      paramPath.append(" end:");
      arrayOfFloat = mX;
      i = k - 1;
      paramPath.append(arrayOfFloat[i]);
      paramPath.append(",");
      paramPath.append(mY[i]);
      throw new IllegalArgumentException(paramPath.toString());
    }
    paramPath = new StringBuilder();
    paramPath.append("The Path has a invalid length ");
    paramPath.append(f1);
    paramPath = new IllegalArgumentException(paramPath.toString());
    throw paramPath;
  }
  
  public float getInterpolation(float paramFloat)
  {
    if (paramFloat <= 0.0F) {
      return 0.0F;
    }
    if (paramFloat >= 1.0F) {
      return 1.0F;
    }
    int j = 0;
    int i = mX.length - 1;
    while (i - j > 1)
    {
      int k = (j + i) / 2;
      if (paramFloat < mX[k]) {
        i = k;
      } else {
        j = k;
      }
    }
    float[] arrayOfFloat = mX;
    float f = arrayOfFloat[i] - arrayOfFloat[j];
    if (f == 0.0F) {
      return mY[j];
    }
    paramFloat = (paramFloat - arrayOfFloat[j]) / f;
    arrayOfFloat = mY;
    f = arrayOfFloat[j];
    return f + paramFloat * (arrayOfFloat[i] - f);
  }
}
