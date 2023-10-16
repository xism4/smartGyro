package com.org.android.ui.asm;

import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.LinearGradient;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import com.org.provider.IpAddress;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

final class Status
{
  private static Item add(Item paramItem, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3)
  {
    if (paramItem != null) {
      return paramItem;
    }
    if (paramBoolean) {
      return new Item(paramInt1, paramInt3, paramInt2);
    }
    return new Item(paramInt1, paramInt2);
  }
  
  private static Shader.TileMode create(int paramInt)
  {
    if (paramInt != 1)
    {
      if (paramInt != 2) {
        return Shader.TileMode.CLAMP;
      }
      return Shader.TileMode.MIRROR;
    }
    return Shader.TileMode.REPEAT;
  }
  
  static Shader inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme)
  {
    Object localObject = paramXmlPullParser.getName();
    if (((String)localObject).equals("gradient"))
    {
      localObject = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, IpAddress.GradientColor);
      float f1 = TypedArrayUtils.getNamedFloat((TypedArray)localObject, paramXmlPullParser, "startX", IpAddress.GradientColor_android_startX, 0.0F);
      float f2 = TypedArrayUtils.getNamedFloat((TypedArray)localObject, paramXmlPullParser, "startY", IpAddress.GradientColor_android_startY, 0.0F);
      float f3 = TypedArrayUtils.getNamedFloat((TypedArray)localObject, paramXmlPullParser, "endX", IpAddress.GradientColor_android_endX, 0.0F);
      float f4 = TypedArrayUtils.getNamedFloat((TypedArray)localObject, paramXmlPullParser, "endY", IpAddress.GradientColor_android_endY, 0.0F);
      float f5 = TypedArrayUtils.getNamedFloat((TypedArray)localObject, paramXmlPullParser, "centerX", IpAddress.GradientColor_android_centerX, 0.0F);
      float f6 = TypedArrayUtils.getNamedFloat((TypedArray)localObject, paramXmlPullParser, "centerY", IpAddress.GradientColor_android_centerY, 0.0F);
      int i = TypedArrayUtils.getString((TypedArray)localObject, paramXmlPullParser, "type", IpAddress.GradientColor_android_type, 0);
      int j = TypedArrayUtils.getNamedColor((TypedArray)localObject, paramXmlPullParser, "startColor", IpAddress.GradientColor_android_startColor, 0);
      boolean bool = TypedArrayUtils.hasAttribute(paramXmlPullParser, "centerColor");
      int k = TypedArrayUtils.getNamedColor((TypedArray)localObject, paramXmlPullParser, "centerColor", IpAddress.GradientColor_android_centerColor, 0);
      int m = TypedArrayUtils.getNamedColor((TypedArray)localObject, paramXmlPullParser, "endColor", IpAddress.GradientColor_android_endColor, 0);
      int n = TypedArrayUtils.getString((TypedArray)localObject, paramXmlPullParser, "tileMode", IpAddress.GradientColor_android_tileMode, 0);
      float f7 = TypedArrayUtils.getNamedFloat((TypedArray)localObject, paramXmlPullParser, "gradientRadius", IpAddress.GradientColor_android_gradientRadius, 0.0F);
      ((TypedArray)localObject).recycle();
      paramResources = add(parse(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme), j, m, bool, k);
      if (i != 1)
      {
        if (i != 2) {
          return new LinearGradient(f1, f2, f3, f4, paramResources.k, a, create(n));
        }
        return new SweepGradient(f5, f6, paramResources.k, a);
      }
      if (f7 > 0.0F) {
        return new RadialGradient(f5, f6, f7, paramResources.k, a, create(n));
      }
      throw new XmlPullParserException("<gradient> tag requires 'gradientRadius' attribute with radial type");
    }
    paramResources = new StringBuilder();
    paramResources.append(paramXmlPullParser.getPositionDescription());
    paramResources.append(": invalid gradient color tag ");
    paramResources.append((String)localObject);
    throw new XmlPullParserException(paramResources.toString());
  }
  
  private static Item parse(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme)
  {
    int i = paramXmlPullParser.getDepth() + 1;
    ArrayList localArrayList1 = new ArrayList(20);
    ArrayList localArrayList2 = new ArrayList(20);
    for (;;)
    {
      int j = paramXmlPullParser.next();
      if (j == 1) {
        break label242;
      }
      int k = paramXmlPullParser.getDepth();
      if ((k < i) && (j == 3)) {
        break label242;
      }
      if ((j == 2) && (k <= i) && (paramXmlPullParser.getName().equals("item")))
      {
        TypedArray localTypedArray = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, IpAddress.GradientColorItem);
        boolean bool1 = localTypedArray.hasValue(IpAddress.GradientColorItem_android_color);
        boolean bool2 = localTypedArray.hasValue(IpAddress.GradientColorItem_android_offset);
        if ((!bool1) || (!bool2)) {
          break;
        }
        j = localTypedArray.getColor(IpAddress.GradientColorItem_android_color, 0);
        float f = localTypedArray.getFloat(IpAddress.GradientColorItem_android_offset, 0.0F);
        localTypedArray.recycle();
        localArrayList2.add(Integer.valueOf(j));
        localArrayList1.add(Float.valueOf(f));
      }
    }
    paramResources = new StringBuilder();
    paramResources.append(paramXmlPullParser.getPositionDescription());
    paramResources.append(": <item> tag requires a 'color' attribute and a 'offset' ");
    paramResources.append("attribute!");
    throw new XmlPullParserException(paramResources.toString());
    label242:
    if (localArrayList2.size() > 0) {
      return new Item(localArrayList2, localArrayList1);
    }
    return null;
  }
}
