package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Icon;
import android.os.Build.VERSION;
import android.os.Parcelable;
import android.util.Log;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;

public class IconCompat
  extends CustomVersionedParcelable
{
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
  
  public IconCompat() {}
  
  private static int a(Icon paramIcon)
  {
    if (Build.VERSION.SDK_INT >= 28) {
      return paramIcon.getResId();
    }
    try
    {
      Object localObject = paramIcon.getClass();
      localObject = ((Class)localObject).getMethod("getResId", new Class[0]);
      paramIcon = ((Method)localObject).invoke(paramIcon, new Object[0]);
      paramIcon = (Integer)paramIcon;
      int j = paramIcon.intValue();
      return j;
    }
    catch (NoSuchMethodException paramIcon)
    {
      Log.e("IconCompat", "Unable to get icon resource", paramIcon);
      return 0;
    }
    catch (InvocationTargetException paramIcon)
    {
      Log.e("IconCompat", "Unable to get icon resource", paramIcon);
      return 0;
    }
    catch (IllegalAccessException paramIcon)
    {
      Log.e("IconCompat", "Unable to get icon resource", paramIcon);
    }
    return 0;
  }
  
  private static String getType(int paramInt)
  {
    if (paramInt != 1)
    {
      if (paramInt != 2)
      {
        if (paramInt != 3)
        {
          if (paramInt != 4)
          {
            if (paramInt != 5) {
              return "UNKNOWN";
            }
            return "BITMAP_MASKABLE";
          }
          return "URI";
        }
        return "DATA";
      }
      return "RESOURCE";
    }
    return "BITMAP";
  }
  
  private static String init(Icon paramIcon)
  {
    if (Build.VERSION.SDK_INT >= 28) {
      return paramIcon.getResPackage();
    }
    try
    {
      Object localObject = paramIcon.getClass();
      localObject = ((Class)localObject).getMethod("getResPackage", new Class[0]);
      paramIcon = ((Method)localObject).invoke(paramIcon, new Object[0]);
      return (String)paramIcon;
    }
    catch (NoSuchMethodException paramIcon)
    {
      Log.e("IconCompat", "Unable to get icon package", paramIcon);
      return null;
    }
    catch (InvocationTargetException paramIcon)
    {
      Log.e("IconCompat", "Unable to get icon package", paramIcon);
      return null;
    }
    catch (IllegalAccessException paramIcon)
    {
      Log.e("IconCompat", "Unable to get icon package", paramIcon);
    }
    return null;
  }
  
  public int a()
  {
    if ((c == -1) && (Build.VERSION.SDK_INT >= 23)) {
      return a((Icon)a);
    }
    if (c == 2) {
      return r;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("called getResId() on ");
    localStringBuilder.append(this);
    throw new IllegalStateException(localStringBuilder.toString());
  }
  
  public void encode(boolean paramBoolean)
  {
    x = e.name();
    int j = c;
    if (j != -1)
    {
      if (j != 1) {
        if (j != 2)
        {
          if (j != 3)
          {
            if (j != 4)
            {
              if (j == 5) {}
            }
            else {
              s = a.toString().getBytes(Charset.forName("UTF-16"));
            }
          }
          else {
            s = ((byte[])a);
          }
        }
        else
        {
          s = ((String)a).getBytes(Charset.forName("UTF-16"));
          return;
        }
      }
      if (paramBoolean)
      {
        Bitmap localBitmap = (Bitmap)a;
        ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
        localBitmap.compress(Bitmap.CompressFormat.PNG, 90, localByteArrayOutputStream);
        s = localByteArrayOutputStream.toByteArray();
      }
    }
    else
    {
      if (paramBoolean) {
        break label158;
      }
    }
    b = ((Parcelable)a);
    return;
    label158:
    throw new IllegalArgumentException("Can't serialize Icon created with IconCompat#createFromIcon");
  }
  
  public String get()
  {
    if ((c == -1) && (Build.VERSION.SDK_INT >= 23)) {
      return init((Icon)a);
    }
    if (c == 2) {
      return ((String)a).split(":", -1)[0];
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("called getResPackage() on ");
    localStringBuilder.append(this);
    throw new IllegalStateException(localStringBuilder.toString());
  }
  
  public void init()
  {
    e = PorterDuff.Mode.valueOf(x);
    int j = c;
    label77:
    Object localObject;
    if (j != -1)
    {
      if (j != 1)
      {
        if (j != 2) {
          if (j != 3)
          {
            if (j != 4) {
              if (j == 5) {
                break label77;
              }
            }
          }
          else
          {
            a = s;
            return;
          }
        }
        a = new String(s, Charset.forName("UTF-16"));
        return;
      }
      localObject = b;
      if (localObject == null)
      {
        localObject = s;
        a = localObject;
        c = 3;
        r = 0;
        p = localObject.length;
      }
    }
    else
    {
      localObject = b;
      if (localObject == null) {
        break label131;
      }
    }
    a = localObject;
    return;
    label131:
    throw new IllegalArgumentException("Invalid icon");
  }
  
  public String toString()
  {
    if (c == -1) {
      return String.valueOf(a);
    }
    StringBuilder localStringBuilder = new StringBuilder("Icon(typ=");
    localStringBuilder.append(getType(c));
    int j = c;
    if (j != 1) {
      if (j != 2)
      {
        if (j != 3)
        {
          if (j != 4)
          {
            if (j != 5) {
              break label225;
            }
          }
          else
          {
            localStringBuilder.append(" uri=");
            localStringBuilder.append(a);
            break label225;
          }
        }
        else
        {
          localStringBuilder.append(" len=");
          localStringBuilder.append(r);
          if (p == 0) {
            break label225;
          }
          localStringBuilder.append(" off=");
          j = p;
          break label219;
        }
      }
      else
      {
        localStringBuilder.append(" pkg=");
        localStringBuilder.append(get());
        localStringBuilder.append(" id=");
        localStringBuilder.append(String.format("0x%08x", new Object[] { Integer.valueOf(a()) }));
        break label225;
      }
    }
    localStringBuilder.append(" size=");
    localStringBuilder.append(((Bitmap)a).getWidth());
    localStringBuilder.append("x");
    j = ((Bitmap)a).getHeight();
    label219:
    localStringBuilder.append(j);
    label225:
    if (i != null)
    {
      localStringBuilder.append(" tint=");
      localStringBuilder.append(i);
    }
    if (e != d)
    {
      localStringBuilder.append(" mode=");
      localStringBuilder.append(e);
    }
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}
