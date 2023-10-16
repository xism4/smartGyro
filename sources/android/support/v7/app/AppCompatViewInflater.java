package android.support.v7.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatMultiAutoCompleteTextView;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.TintContextWrapper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.org.android.util.ArrayMap;
import com.org.android.view.ViewCompat;
import com.org.v4.util.R$styleable;
import com.org.v4.view.ContextThemeWrapper;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class AppCompatViewInflater {
    private static final String[] sClassPrefixList = {"android.widget.", "android.view.", "android.webkit."};
    private static final Map<String, Constructor<? extends View>> sConstructorMap = new ArrayMap();
    private static final Class<?>[] sConstructorSignature = {Context.class, AttributeSet.class};
    private static final int[] sOnClickAttrs = {16843375};
    private final Object[] mConstructorArgs = new Object[2];

    private static class a implements View.OnClickListener {
        private final View mHostView;
        private final String mMethodName;
        private Context mResolvedContext;
        private Method mResolvedMethod;

        public a(View view, String str) {
            this.mHostView = view;
            this.mMethodName = str;
        }

        private void resolveMethod(Context $r2, String str) {
            String $r3;
            while ($r2 != null) {
                try {
                    if (!$r2.isRestricted()) {
                        Method $r6 = $r2.getClass().getMethod(this.mMethodName, new Class[]{View.class});
                        if ($r6 != null) {
                            this.mResolvedMethod = $r6;
                            this.mResolvedContext = $r2;
                            return;
                        }
                    }
                } catch (NoSuchMethodException e) {
                }
                $r2 = $r2 instanceof ContextWrapper ? ((ContextWrapper) $r2).getBaseContext() : null;
            }
            int $i0 = this.mHostView.getId();
            if ($i0 == -1) {
                $r3 = "";
            } else {
                $r3 = " with id '" + this.mHostView.getContext().getResources().getResourceEntryName($i0) + "'";
            }
            StringBuilder $r9 = new StringBuilder();
            $r9.append("Could not find method ");
            String $r12 = this.mMethodName;
            String str2 = $r12;
            $r9.append($r12);
            $r9.append("(View) in a parent or ancestor Context for android:onClick ");
            $r9.append("attribute defined on view ");
            $r9.append(this.mHostView.getClass());
            $r9.append($r3);
            throw new IllegalStateException($r9.toString());
        }

        public void onClick(View view) {
            if (this.mResolvedMethod == null) {
                resolveMethod(this.mHostView.getContext(), this.mMethodName);
            }
            try {
                this.mResolvedMethod.invoke(this.mResolvedContext, new Object[]{view});
            } catch (IllegalAccessException $r9) {
                throw new IllegalStateException("Could not execute non-public method for android:onClick", $r9);
            } catch (InvocationTargetException $r7) {
                throw new IllegalStateException("Could not execute method for android:onClick", $r7);
            }
        }
    }

    private void checkOnClickListener(View view, AttributeSet attributeSet) {
        Context $r3 = view.getContext();
        if (!($r3 instanceof ContextWrapper)) {
            return;
        }
        if (Build.VERSION.SDK_INT < 15 || ViewCompat.hasOnClickListeners(view)) {
            TypedArray $r5 = $r3.obtainStyledAttributes(attributeSet, sOnClickAttrs);
            String $r6 = $r5.getString(0);
            if ($r6 != null) {
                view.setOnClickListener(new a(view, $r6));
            }
            $r5.recycle();
        }
    }

    private View createView(Context context, String str, String str2) {
        String $r4;
        Constructor $r7 = sConstructorMap.get(str);
        if ($r7 == null) {
            try {
                ClassLoader $r8 = context.getClassLoader();
                if (str2 != null) {
                    $r4 = str2 + str;
                } else {
                    $r4 = str;
                }
                Constructor $r12 = $r8.loadClass($r4).asSubclass(View.class).getConstructor(sConstructorSignature);
                $r7 = $r12;
                sConstructorMap.put(str, $r12);
            } catch (Exception e) {
                return null;
            }
        }
        $r7.setAccessible(true);
        return (View) $r7.newInstance(this.mConstructorArgs);
    }

    private View createViewFromTag(Context context, String $r4, AttributeSet attributeSet) {
        if ($r4.equals("view")) {
            $r4 = attributeSet.getAttributeValue((String) null, "class");
        }
        this.mConstructorArgs[0] = context;
        this.mConstructorArgs[1] = attributeSet;
        try {
            if (-1 == $r4.indexOf(46)) {
                int $i0 = 0;
                while ($i0 < sClassPrefixList.length) {
                    try {
                        View $r9 = createView(context, $r4, sClassPrefixList[$i0]);
                        if ($r9 != null) {
                            return $r9;
                        }
                        $i0++;
                    } finally {
                        Object[] $r6 = this.mConstructorArgs;
                        $r6[0] = null;
                        $r6[1] = null;
                    }
                }
                Object[] $r62 = this.mConstructorArgs;
                $r62[0] = null;
                $r62[1] = null;
                return null;
            }
            try {
                View $r92 = createView(context, $r4, (String) null);
                Object[] $r63 = this.mConstructorArgs;
                $r63[0] = null;
                $r63[1] = null;
                return $r92;
            } catch (Exception e) {
                Object[] $r64 = this.mConstructorArgs;
                $r64[0] = null;
                $r64[1] = null;
                return null;
            }
        } catch (Exception e2) {
            Object[] $r642 = this.mConstructorArgs;
            $r642[0] = null;
            $r642[1] = null;
            return null;
        }
    }

    private static Context themifyContext(Context context, AttributeSet attributeSet, boolean z, boolean z2) {
        TypedArray $r3 = context.obtainStyledAttributes(attributeSet, R$styleable.View, 0, 0);
        int $i0 = z ? $r3.getResourceId(R$styleable.View_android_theme, 0) : 0;
        if (z2 && $i0 == 0) {
            int $i1 = $r3.getResourceId(R$styleable.View_theme, 0);
            $i0 = $i1;
            if ($i1 != 0) {
                Log.i("AppCompatViewInflater", "app:theme is now deprecated. Please move to using android:theme instead.");
            }
        }
        $r3.recycle();
        return ($i0 == 0 || ((context instanceof ContextThemeWrapper) && ((ContextThemeWrapper) context).getThemeResId() == $i0)) ? context : new ContextThemeWrapper(context, $i0);
    }

    private void wrap(View view, String str) {
        if (view == null) {
            throw new IllegalStateException(AppCompatViewInflater.class.getName() + " asked to inflate view for <" + str + ">, but returned null");
        }
    }

    /* access modifiers changed from: protected */
    public AppCompatCheckedTextView build(Context context, AttributeSet attributeSet) {
        return new AppCompatCheckedTextView(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public SwitchCompat createListView(Context context, AttributeSet attributeSet) {
        return new SwitchCompat(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public AppCompatButton createView(Context context, AttributeSet attributeSet) {
        return new AppCompatButton(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public View createView(Context context, String str, AttributeSet attributeSet) {
        return null;
    }

    /* access modifiers changed from: package-private */
    public final View createView(View view, String str, Context context, AttributeSet attributeSet, boolean z, boolean z2, boolean z3, boolean z4) {
        View $r4;
        Context $r5 = (!z || view == null) ? context : view.getContext();
        if (z2 || z3) {
            $r5 = themifyContext($r5, attributeSet, z2, z3);
        }
        if (z4) {
            $r5 = TintContextWrapper.wrap($r5);
        }
        byte $b0 = -1;
        switch (str.hashCode()) {
            case -1946472170:
                if (str.equals("RatingBar")) {
                    $b0 = 11;
                    break;
                }
                break;
            case -1455429095:
                if (str.equals("CheckedTextView")) {
                    $b0 = 8;
                    break;
                }
                break;
            case -1346021293:
                if (str.equals("MultiAutoCompleteTextView")) {
                    $b0 = 10;
                    break;
                }
                break;
            case -938935918:
                if (str.equals("TextView")) {
                    $b0 = 0;
                    break;
                }
                break;
            case -937446323:
                if (str.equals("ImageButton")) {
                    $b0 = 5;
                    break;
                }
                break;
            case -658531749:
                if (str.equals("SeekBar")) {
                    $b0 = 12;
                    break;
                }
                break;
            case -339785223:
                if (str.equals("Spinner")) {
                    $b0 = 4;
                    break;
                }
                break;
            case 776382189:
                if (str.equals("RadioButton")) {
                    $b0 = 7;
                    break;
                }
                break;
            case 1125864064:
                if (str.equals("ImageView")) {
                    $b0 = 1;
                    break;
                }
                break;
            case 1413872058:
                if (str.equals("AutoCompleteTextView")) {
                    $b0 = 9;
                    break;
                }
                break;
            case 1601505219:
                if (str.equals("CheckBox")) {
                    $b0 = 6;
                    break;
                }
                break;
            case 1666676343:
                if (str.equals("EditText")) {
                    $b0 = 3;
                    break;
                }
                break;
            case 2001146706:
                if (str.equals("Button")) {
                    $b0 = 2;
                    break;
                }
                break;
        }
        switch ($b0) {
            case 0:
                $r4 = setTextAppearance($r5, attributeSet);
                break;
            case 1:
                $r4 = onCreateView($r5, attributeSet);
                break;
            case 2:
                $r4 = createView($r5, attributeSet);
                break;
            case 3:
                $r4 = inflate($r5, attributeSet);
                break;
            case 4:
                $r4 = themifyContext($r5, attributeSet);
                break;
            case 5:
                $r4 = get($r5, attributeSet);
                break;
            case 6:
                $r4 = wrap($r5, attributeSet);
                break;
            case 7:
                $r4 = createViewFromTag($r5, attributeSet);
                break;
            case 8:
                $r4 = build($r5, attributeSet);
                break;
            case 9:
                $r4 = getDrawable($r5, attributeSet);
                break;
            case 10:
                $r4 = init($r5, attributeSet);
                break;
            case 11:
                $r4 = newInstance($r5, attributeSet);
                break;
            case 12:
                $r4 = createListView($r5, attributeSet);
                break;
            default:
                $r4 = createView($r5, str, attributeSet);
                break;
        }
        wrap($r4, str);
        if ($r4 == null && context != $r5) {
            $r4 = createViewFromTag($r5, str, attributeSet);
        }
        if ($r4 != null) {
            checkOnClickListener($r4, attributeSet);
        }
        return $r4;
    }

    /* access modifiers changed from: protected */
    public AppCompatRadioButton createViewFromTag(Context context, AttributeSet attributeSet) {
        return new AppCompatRadioButton(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public AppCompatImageButton get(Context context, AttributeSet attributeSet) {
        return new AppCompatImageButton(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public AppCompatAutoCompleteTextView getDrawable(Context context, AttributeSet attributeSet) {
        return new AppCompatAutoCompleteTextView(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public AppCompatEditText inflate(Context context, AttributeSet attributeSet) {
        return new AppCompatEditText(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public AppCompatMultiAutoCompleteTextView init(Context context, AttributeSet attributeSet) {
        return new AppCompatMultiAutoCompleteTextView(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public AppCompatRatingBar newInstance(Context context, AttributeSet attributeSet) {
        return new AppCompatRatingBar(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public AppCompatImageView onCreateView(Context context, AttributeSet attributeSet) {
        return new AppCompatImageView(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public AppCompatTextView setTextAppearance(Context context, AttributeSet attributeSet) {
        return new AppCompatTextView(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public AppCompatSpinner themifyContext(Context context, AttributeSet attributeSet) {
        return new AppCompatSpinner(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public AppCompatCheckBox wrap(Context context, AttributeSet attributeSet) {
        return new AppCompatCheckBox(context, attributeSet);
    }
}
