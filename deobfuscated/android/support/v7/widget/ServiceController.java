package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import com.org.shortcuts.drawable.VectorDrawableCompat;
import org.xmlpull.v1.XmlPullParser;

class ServiceController
  implements AppCompatDrawableManager.InflateDelegate
{
  ServiceController() {}
  
  public Drawable createFromXmlInner(Context paramContext, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme)
  {
    try
    {
      paramContext = VectorDrawableCompat.createFromXmlInner(paramContext.getResources(), paramXmlPullParser, paramAttributeSet, paramTheme);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      Log.e("VdcInflateDelegate", "Exception while inflating <vector>", paramContext);
    }
    return null;
  }
}
