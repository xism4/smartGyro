package com.org.android.ui.asm;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.util.Base64;
import android.util.TypedValue;
import android.util.Xml;
import com.org.android.manager.h;
import com.org.provider.IpAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class Type
{
  private static f a(XmlPullParser paramXmlPullParser, Resources paramResources)
  {
    paramResources = paramResources.obtainAttributes(Xml.asAttributeSet(paramXmlPullParser), IpAddress.FontFamilyFont);
    int i;
    if (paramResources.hasValue(IpAddress.FontFamilyFont_fontWeight)) {
      i = IpAddress.FontFamilyFont_fontWeight;
    } else {
      i = IpAddress.FontFamilyFont_android_fontWeight;
    }
    int k = paramResources.getInt(i, 400);
    if (paramResources.hasValue(IpAddress.FontFamilyFont_fontStyle)) {
      i = IpAddress.FontFamilyFont_fontStyle;
    } else {
      i = IpAddress.FontFamilyFont_android_fontStyle;
    }
    boolean bool;
    if (1 == paramResources.getInt(i, 0)) {
      bool = true;
    } else {
      bool = false;
    }
    if (paramResources.hasValue(IpAddress.FontFamilyFont_ttcIndex)) {
      i = IpAddress.FontFamilyFont_ttcIndex;
    } else {
      i = IpAddress.FontFamilyFont_android_ttcIndex;
    }
    if (paramResources.hasValue(IpAddress.FontFamilyFont_fontVariationSettings)) {
      j = IpAddress.FontFamilyFont_fontVariationSettings;
    } else {
      j = IpAddress.FontFamilyFont_android_fontVariationSettings;
    }
    String str1 = paramResources.getString(j);
    int j = paramResources.getInt(i, 0);
    if (paramResources.hasValue(IpAddress.FontFamilyFont_font)) {
      i = IpAddress.FontFamilyFont_font;
    } else {
      i = IpAddress.FontFamilyFont_android_font;
    }
    int m = paramResources.getResourceId(i, 0);
    String str2 = paramResources.getString(i);
    paramResources.recycle();
    while (paramXmlPullParser.next() != 3) {
      a(paramXmlPullParser);
    }
    return new f(str2, k, bool, str1, j, m);
  }
  
  private static void a(XmlPullParser paramXmlPullParser)
  {
    int i = 1;
    while (i > 0)
    {
      int j = paramXmlPullParser.next();
      if (j != 2)
      {
        if (j == 3) {
          i -= 1;
        }
      }
      else {
        i += 1;
      }
    }
  }
  
  private static l create(XmlPullParser paramXmlPullParser, Resources paramResources)
  {
    paramXmlPullParser.require(2, null, "font-family");
    if (paramXmlPullParser.getName().equals("font-family")) {
      return initialize(paramXmlPullParser, paramResources);
    }
    a(paramXmlPullParser);
    return null;
  }
  
  public static l createFromXml(XmlPullParser paramXmlPullParser, Resources paramResources)
  {
    int i;
    do
    {
      i = paramXmlPullParser.next();
    } while ((i != 2) && (i != 1));
    if (i == 2) {
      return create(paramXmlPullParser, paramResources);
    }
    paramXmlPullParser = new XmlPullParserException("No start tag found");
    throw paramXmlPullParser;
  }
  
  private static int getType(TypedArray paramTypedArray, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return paramTypedArray.getType(paramInt);
    }
    TypedValue localTypedValue = new TypedValue();
    paramTypedArray.getValue(paramInt, localTypedValue);
    return type;
  }
  
  public static List getValue(Resources paramResources, int paramInt)
  {
    if (paramInt == 0) {
      return Collections.emptyList();
    }
    TypedArray localTypedArray = paramResources.obtainTypedArray(paramInt);
    try
    {
      int i = localTypedArray.length();
      if (i == 0)
      {
        paramResources = Collections.emptyList();
        localTypedArray.recycle();
        return paramResources;
      }
      ArrayList localArrayList = new ArrayList();
      i = getType(localTypedArray, 0);
      if (i == 1)
      {
        paramInt = 0;
        for (;;)
        {
          i = localTypedArray.length();
          if (paramInt >= i) {
            break;
          }
          i = localTypedArray.getResourceId(paramInt, 0);
          if (i != 0) {
            localArrayList.add(read(paramResources.getStringArray(i)));
          }
          paramInt += 1;
        }
      }
      localArrayList.add(read(paramResources.getStringArray(paramInt)));
      localTypedArray.recycle();
      return localArrayList;
    }
    catch (Throwable paramResources)
    {
      localTypedArray.recycle();
      throw paramResources;
    }
  }
  
  private static l initialize(XmlPullParser paramXmlPullParser, Resources paramResources)
  {
    Object localObject = paramResources.obtainAttributes(Xml.asAttributeSet(paramXmlPullParser), IpAddress.FontFamily);
    String str1 = ((TypedArray)localObject).getString(IpAddress.FontFamily_fontProviderAuthority);
    String str2 = ((TypedArray)localObject).getString(IpAddress.FontFamily_fontProviderPackage);
    String str3 = ((TypedArray)localObject).getString(IpAddress.FontFamily_fontProviderQuery);
    int i = ((TypedArray)localObject).getResourceId(IpAddress.FontFamily_fontProviderCerts, 0);
    int j = ((TypedArray)localObject).getInteger(IpAddress.FontFamily_fontProviderFetchStrategy, 1);
    int k = ((TypedArray)localObject).getInteger(IpAddress.FontFamily_fontProviderFetchTimeout, 500);
    ((TypedArray)localObject).recycle();
    if ((str1 != null) && (str2 != null) && (str3 != null))
    {
      while (paramXmlPullParser.next() != 3) {
        a(paramXmlPullParser);
      }
      return new b(new h(str1, str2, str3, getValue(paramResources, i)), j, k);
    }
    localObject = new ArrayList();
    while (paramXmlPullParser.next() != 3) {
      if (paramXmlPullParser.getEventType() == 2) {
        if (paramXmlPullParser.getName().equals("font")) {
          ((List)localObject).add(a(paramXmlPullParser, paramResources));
        } else {
          a(paramXmlPullParser);
        }
      }
    }
    if (((List)localObject).isEmpty()) {
      return null;
    }
    return new e((f[])((List)localObject).toArray(new f[((List)localObject).size()]));
  }
  
  private static List read(String[] paramArrayOfString)
  {
    ArrayList localArrayList = new ArrayList();
    int j = paramArrayOfString.length;
    int i = 0;
    while (i < j)
    {
      localArrayList.add(Base64.decode(paramArrayOfString[i], 0));
      i += 1;
    }
    return localArrayList;
  }
}
