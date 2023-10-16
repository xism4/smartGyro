package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatDrawableManager;
import android.util.AttributeSet;
import android.util.Log;
import com.org.shortcuts.drawable.VectorDrawableCompat;
import org.xmlpull.v1.XmlPullParser;

class ServiceController implements AppCompatDrawableManager.InflateDelegate {
    ServiceController() {
    }

    public Drawable createFromXmlInner(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        try {
            return VectorDrawableCompat.createFromXmlInner(context.getResources(), xmlPullParser, attributeSet, theme);
        } catch (Exception $r7) {
            Log.e("VdcInflateDelegate", "Exception while inflating <vector>", $r7);
            return null;
        }
    }
}
