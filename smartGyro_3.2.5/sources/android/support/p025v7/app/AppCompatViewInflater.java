package android.support.p025v7.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.p025v7.widget.C0328C;
import android.support.p025v7.widget.C0343I;
import android.support.p025v7.widget.C0410i;
import android.support.p025v7.widget.C0414k;
import android.support.p025v7.widget.C0416l;
import android.support.p025v7.widget.C0418m;
import android.support.p025v7.widget.C0430p;
import android.support.p025v7.widget.C0433qa;
import android.support.p025v7.widget.C0434r;
import android.support.p025v7.widget.C0438t;
import android.support.p025v7.widget.C0440u;
import android.support.p025v7.widget.C0446x;
import android.support.p025v7.widget.C0448y;
import android.support.p025v7.widget.C0450z;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import p000a.p001a.p005c.p013f.C0078b;
import p000a.p001a.p005c.p014g.C0127u;
import p000a.p001a.p017d.p018a.C0145j;
import p000a.p001a.p017d.p023d.C0167d;

/* renamed from: android.support.v7.app.AppCompatViewInflater */
public class AppCompatViewInflater {

    /* renamed from: a */
    private static final Class<?>[] f664a = {Context.class, AttributeSet.class};

    /* renamed from: b */
    private static final int[] f665b = {16843375};

    /* renamed from: c */
    private static final String[] f666c = {"android.widget.", "android.view.", "android.webkit."};

    /* renamed from: d */
    private static final Map<String, Constructor<? extends View>> f667d = new C0078b();

    /* renamed from: e */
    private final Object[] f668e = new Object[2];

    /* renamed from: android.support.v7.app.AppCompatViewInflater$a */
    private static class C0227a implements View.OnClickListener {

        /* renamed from: a */
        private final View f669a;

        /* renamed from: b */
        private final String f670b;

        /* renamed from: c */
        private Method f671c;

        /* renamed from: d */
        private Context f672d;

        public C0227a(View view, String str) {
            this.f669a = view;
            this.f670b = str;
        }

        /* renamed from: a */
        private void m876a(Context context, String str) {
            String str2;
            Method method;
            while (context != null) {
                try {
                    if (!context.isRestricted() && (method = context.getClass().getMethod(this.f670b, new Class[]{View.class})) != null) {
                        this.f671c = method;
                        this.f672d = context;
                        return;
                    }
                } catch (NoSuchMethodException unused) {
                }
                context = context instanceof ContextWrapper ? ((ContextWrapper) context).getBaseContext() : null;
            }
            int id = this.f669a.getId();
            if (id == -1) {
                str2 = "";
            } else {
                str2 = " with id '" + this.f669a.getContext().getResources().getResourceEntryName(id) + "'";
            }
            throw new IllegalStateException("Could not find method " + this.f670b + "(View) in a parent or ancestor Context for android:onClick " + "attribute defined on view " + this.f669a.getClass() + str2);
        }

        public void onClick(View view) {
            if (this.f671c == null) {
                m876a(this.f669a.getContext(), this.f670b);
            }
            try {
                this.f671c.invoke(this.f672d, new Object[]{view});
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Could not execute non-public method for android:onClick", e);
            } catch (InvocationTargetException e2) {
                throw new IllegalStateException("Could not execute method for android:onClick", e2);
            }
        }
    }

    /* renamed from: a */
    private static Context m856a(Context context, AttributeSet attributeSet, boolean z, boolean z2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0145j.View, 0, 0);
        int resourceId = z ? obtainStyledAttributes.getResourceId(C0145j.View_android_theme, 0) : 0;
        if (z2 && resourceId == 0 && (resourceId = obtainStyledAttributes.getResourceId(C0145j.View_theme, 0)) != 0) {
            Log.i("AppCompatViewInflater", "app:theme is now deprecated. Please move to using android:theme instead.");
        }
        obtainStyledAttributes.recycle();
        return resourceId != 0 ? (!(context instanceof C0167d) || ((C0167d) context).mo669a() != resourceId) ? new C0167d(context, resourceId) : context : context;
    }

    /* renamed from: a */
    private View m857a(Context context, String str, String str2) {
        String str3;
        Constructor<? extends U> constructor = f667d.get(str);
        if (constructor == null) {
            try {
                ClassLoader classLoader = context.getClassLoader();
                if (str2 != null) {
                    str3 = str2 + str;
                } else {
                    str3 = str;
                }
                constructor = classLoader.loadClass(str3).asSubclass(View.class).getConstructor(f664a);
                f667d.put(str, constructor);
            } catch (Exception unused) {
                return null;
            }
        }
        constructor.setAccessible(true);
        return (View) constructor.newInstance(this.f668e);
    }

    /* renamed from: a */
    private void m858a(View view, AttributeSet attributeSet) {
        Context context = view.getContext();
        if (!(context instanceof ContextWrapper)) {
            return;
        }
        if (Build.VERSION.SDK_INT < 15 || C0127u.m453g(view)) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f665b);
            String string = obtainStyledAttributes.getString(0);
            if (string != null) {
                view.setOnClickListener(new C0227a(view, string));
            }
            obtainStyledAttributes.recycle();
        }
    }

    /* renamed from: a */
    private void m859a(View view, String str) {
        if (view == null) {
            throw new IllegalStateException(AppCompatViewInflater.class.getName() + " asked to inflate view for <" + str + ">, but returned null");
        }
    }

    /* renamed from: b */
    private View m860b(Context context, String str, AttributeSet attributeSet) {
        if (str.equals("view")) {
            str = attributeSet.getAttributeValue((String) null, "class");
        }
        try {
            this.f668e[0] = context;
            this.f668e[1] = attributeSet;
            if (-1 == str.indexOf(46)) {
                for (String a : f666c) {
                    View a2 = m857a(context, str, a);
                    if (a2 != null) {
                        return a2;
                    }
                }
                Object[] objArr = this.f668e;
                objArr[0] = null;
                objArr[1] = null;
                return null;
            }
            View a3 = m857a(context, str, (String) null);
            Object[] objArr2 = this.f668e;
            objArr2[0] = null;
            objArr2[1] = null;
            return a3;
        } catch (Exception unused) {
            return null;
        } finally {
            Object[] objArr3 = this.f668e;
            objArr3[0] = null;
            objArr3[1] = null;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C0410i mo944a(Context context, AttributeSet attributeSet) {
        return new C0410i(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public View mo945a(Context context, String str, AttributeSet attributeSet) {
        return null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final View mo946a(View view, String str, Context context, AttributeSet attributeSet, boolean z, boolean z2, boolean z3, boolean z4) {
        View view2;
        Context context2 = (!z || view == null) ? context : view.getContext();
        if (z2 || z3) {
            context2 = m856a(context2, attributeSet, z2, z3);
        }
        if (z4) {
            context2 = C0433qa.m1887a(context2);
        }
        char c = 65535;
        switch (str.hashCode()) {
            case -1946472170:
                if (str.equals("RatingBar")) {
                    c = 11;
                    break;
                }
                break;
            case -1455429095:
                if (str.equals("CheckedTextView")) {
                    c = 8;
                    break;
                }
                break;
            case -1346021293:
                if (str.equals("MultiAutoCompleteTextView")) {
                    c = 10;
                    break;
                }
                break;
            case -938935918:
                if (str.equals("TextView")) {
                    c = 0;
                    break;
                }
                break;
            case -937446323:
                if (str.equals("ImageButton")) {
                    c = 5;
                    break;
                }
                break;
            case -658531749:
                if (str.equals("SeekBar")) {
                    c = 12;
                    break;
                }
                break;
            case -339785223:
                if (str.equals("Spinner")) {
                    c = 4;
                    break;
                }
                break;
            case 776382189:
                if (str.equals("RadioButton")) {
                    c = 7;
                    break;
                }
                break;
            case 1125864064:
                if (str.equals("ImageView")) {
                    c = 1;
                    break;
                }
                break;
            case 1413872058:
                if (str.equals("AutoCompleteTextView")) {
                    c = 9;
                    break;
                }
                break;
            case 1601505219:
                if (str.equals("CheckBox")) {
                    c = 6;
                    break;
                }
                break;
            case 1666676343:
                if (str.equals("EditText")) {
                    c = 3;
                    break;
                }
                break;
            case 2001146706:
                if (str.equals("Button")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                view2 = mo958m(context2, attributeSet);
                break;
            case 1:
                view2 = mo952g(context2, attributeSet);
                break;
            case 2:
                view2 = mo947b(context2, attributeSet);
                break;
            case 3:
                view2 = mo950e(context2, attributeSet);
                break;
            case 4:
                view2 = mo957l(context2, attributeSet);
                break;
            case 5:
                view2 = mo951f(context2, attributeSet);
                break;
            case 6:
                view2 = mo948c(context2, attributeSet);
                break;
            case 7:
                view2 = mo954i(context2, attributeSet);
                break;
            case 8:
                view2 = mo949d(context2, attributeSet);
                break;
            case 9:
                view2 = mo944a(context2, attributeSet);
                break;
            case 10:
                view2 = mo953h(context2, attributeSet);
                break;
            case 11:
                view2 = mo955j(context2, attributeSet);
                break;
            case 12:
                view2 = mo956k(context2, attributeSet);
                break;
            default:
                view2 = mo945a(context2, str, attributeSet);
                break;
        }
        m859a(view2, str);
        if (view2 == null && context != context2) {
            view2 = m860b(context2, str, attributeSet);
        }
        if (view2 != null) {
            m858a(view2, attributeSet);
        }
        return view2;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public C0414k mo947b(Context context, AttributeSet attributeSet) {
        return new C0414k(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public C0416l mo948c(Context context, AttributeSet attributeSet) {
        return new C0416l(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public C0418m mo949d(Context context, AttributeSet attributeSet) {
        return new C0418m(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public C0430p mo950e(Context context, AttributeSet attributeSet) {
        return new C0430p(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public C0434r mo951f(Context context, AttributeSet attributeSet) {
        return new C0434r(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    /* renamed from: g */
    public C0438t mo952g(Context context, AttributeSet attributeSet) {
        return new C0438t(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    /* renamed from: h */
    public C0440u mo953h(Context context, AttributeSet attributeSet) {
        return new C0440u(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    /* renamed from: i */
    public C0446x mo954i(Context context, AttributeSet attributeSet) {
        return new C0446x(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    /* renamed from: j */
    public C0448y mo955j(Context context, AttributeSet attributeSet) {
        return new C0448y(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    /* renamed from: k */
    public C0450z mo956k(Context context, AttributeSet attributeSet) {
        return new C0450z(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    /* renamed from: l */
    public C0328C mo957l(Context context, AttributeSet attributeSet) {
        return new C0328C(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    /* renamed from: m */
    public C0343I mo958m(Context context, AttributeSet attributeSet) {
        return new C0343I(context, attributeSet);
    }
}
