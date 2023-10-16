package com.org.android.ui.asm;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.XmlResourceParser;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class Label
{
  private int a;
  private final Shader e;
  private final ColorStateList w;
  
  private Label(Shader paramShader, ColorStateList paramColorStateList, int paramInt)
  {
    e = paramShader;
    w = paramColorStateList;
    a = paramInt;
  }
  
  static Label a(int paramInt)
  {
    return new Label(null, null, paramInt);
  }
  
  private static Label create(Resources paramResources, int paramInt, Resources.Theme paramTheme)
  {
    XmlResourceParser localXmlResourceParser = paramResources.getXml(paramInt);
    AttributeSet localAttributeSet = Xml.asAttributeSet(localXmlResourceParser);
    do
    {
      paramInt = localXmlResourceParser.next();
    } while ((paramInt != 2) && (paramInt != 1));
    if (paramInt == 2)
    {
      String str = localXmlResourceParser.getName();
      paramInt = -1;
      int i = str.hashCode();
      if (i != 89650992)
      {
        if ((i == 1191572447) && (str.equals("selector"))) {
          paramInt = 0;
        }
      }
      else if (str.equals("gradient")) {
        paramInt = 1;
      }
      if (paramInt != 0)
      {
        if (paramInt == 1) {
          return getPath(Status.inflate(paramResources, localXmlResourceParser, localAttributeSet, paramTheme));
        }
        paramResources = new StringBuilder();
        paramResources.append(localXmlResourceParser.getPositionDescription());
        paramResources.append(": unsupported complex color tag ");
        paramResources.append(str);
        throw new XmlPullParserException(paramResources.toString());
      }
      return init(Handler.create(paramResources, localXmlResourceParser, localAttributeSet, paramTheme));
    }
    paramResources = new XmlPullParserException("No start tag found");
    throw paramResources;
  }
  
  static Label getPath(Shader paramShader)
  {
    return new Label(paramShader, null, 0);
  }
  
  static Label init(ColorStateList paramColorStateList)
  {
    return new Label(null, paramColorStateList, paramColorStateList.getDefaultColor());
  }
  
  public static Label read(Resources paramResources, int paramInt, Resources.Theme paramTheme)
  {
    try
    {
      paramResources = create(paramResources, paramInt, paramTheme);
      return paramResources;
    }
    catch (Exception paramResources)
    {
      Log.e("ComplexColorCompat", "Failed to inflate ComplexColor.", paramResources);
    }
    return null;
  }
  
  public boolean a()
  {
    return (setStyle()) || (a != 0);
  }
  
  public boolean a(int[] paramArrayOfInt)
  {
    if (b())
    {
      ColorStateList localColorStateList = w;
      int i = localColorStateList.getColorForState(paramArrayOfInt, localColorStateList.getDefaultColor());
      if (i != a)
      {
        a = i;
        return true;
      }
    }
    return false;
  }
  
  public boolean b()
  {
    if (e == null)
    {
      ColorStateList localColorStateList = w;
      if ((localColorStateList != null) && (localColorStateList.isStateful())) {
        return true;
      }
    }
    return false;
  }
  
  public Shader e()
  {
    return e;
  }
  
  public int getColor()
  {
    return a;
  }
  
  public void setColor(int paramInt)
  {
    a = paramInt;
  }
  
  public boolean setStyle()
  {
    return e != null;
  }
}
