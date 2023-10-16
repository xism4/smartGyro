package android.support.p025v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import p000a.p001a.p017d.p018a.C0145j;

/* renamed from: android.support.v7.widget.J */
class C0345J {

    /* renamed from: a */
    private static final RectF f1245a = new RectF();

    /* renamed from: b */
    private static ConcurrentHashMap<String, Method> f1246b = new ConcurrentHashMap<>();

    /* renamed from: c */
    private int f1247c = 0;

    /* renamed from: d */
    private boolean f1248d = false;

    /* renamed from: e */
    private float f1249e = -1.0f;

    /* renamed from: f */
    private float f1250f = -1.0f;

    /* renamed from: g */
    private float f1251g = -1.0f;

    /* renamed from: h */
    private int[] f1252h = new int[0];

    /* renamed from: i */
    private boolean f1253i = false;

    /* renamed from: j */
    private TextPaint f1254j;

    /* renamed from: k */
    private final TextView f1255k;

    /* renamed from: l */
    private final Context f1256l;

    C0345J(TextView textView) {
        this.f1255k = textView;
        this.f1256l = this.f1255k.getContext();
    }

    /* renamed from: a */
    private int m1502a(RectF rectF) {
        int length = this.f1252h.length;
        if (length != 0) {
            int i = length - 1;
            int i2 = 1;
            int i3 = 0;
            while (i2 <= i) {
                int i4 = (i2 + i) / 2;
                if (m1510a(this.f1252h[i4], rectF)) {
                    int i5 = i4 + 1;
                    i3 = i2;
                    i2 = i5;
                } else {
                    i3 = i4 - 1;
                    i = i3;
                }
            }
            return this.f1252h[i3];
        }
        throw new IllegalStateException("No available text sizes to choose from.");
    }

    /* renamed from: a */
    private StaticLayout m1503a(CharSequence charSequence, Layout.Alignment alignment, int i) {
        boolean z;
        float f;
        float f2;
        if (Build.VERSION.SDK_INT >= 16) {
            f2 = this.f1255k.getLineSpacingMultiplier();
            f = this.f1255k.getLineSpacingExtra();
            z = this.f1255k.getIncludeFontPadding();
        } else {
            f2 = ((Float) m1505a((Object) this.f1255k, "getLineSpacingMultiplier", Float.valueOf(1.0f))).floatValue();
            f = ((Float) m1505a((Object) this.f1255k, "getLineSpacingExtra", Float.valueOf(0.0f))).floatValue();
            z = ((Boolean) m1505a((Object) this.f1255k, "getIncludeFontPadding", true)).booleanValue();
        }
        return new StaticLayout(charSequence, this.f1254j, i, alignment, f2, f, z);
    }

    /* renamed from: a */
    private StaticLayout m1504a(CharSequence charSequence, Layout.Alignment alignment, int i, int i2) {
        TextDirectionHeuristic textDirectionHeuristic = (TextDirectionHeuristic) m1505a((Object) this.f1255k, "getTextDirectionHeuristic", TextDirectionHeuristics.FIRSTSTRONG_LTR);
        StaticLayout.Builder hyphenationFrequency = StaticLayout.Builder.obtain(charSequence, 0, charSequence.length(), this.f1254j, i).setAlignment(alignment).setLineSpacing(this.f1255k.getLineSpacingExtra(), this.f1255k.getLineSpacingMultiplier()).setIncludePad(this.f1255k.getIncludeFontPadding()).setBreakStrategy(this.f1255k.getBreakStrategy()).setHyphenationFrequency(this.f1255k.getHyphenationFrequency());
        if (i2 == -1) {
            i2 = Integer.MAX_VALUE;
        }
        return hyphenationFrequency.setMaxLines(i2).setTextDirection(textDirectionHeuristic).build();
    }

    /* renamed from: a */
    private <T> T m1505a(Object obj, String str, T t) {
        try {
            return m1506a(str).invoke(obj, new Object[0]);
        } catch (Exception e) {
            Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#" + str + "() method", e);
            return t;
        }
    }

    /* renamed from: a */
    private Method m1506a(String str) {
        try {
            Method method = f1246b.get(str);
            if (method == null && (method = TextView.class.getDeclaredMethod(str, new Class[0])) != null) {
                method.setAccessible(true);
                f1246b.put(str, method);
            }
            return method;
        } catch (Exception e) {
            Log.w("ACTVAutoSizeHelper", "Failed to retrieve TextView#" + str + "() method", e);
            return null;
        }
    }

    /* renamed from: a */
    private void m1507a(float f) {
        if (f != this.f1255k.getPaint().getTextSize()) {
            this.f1255k.getPaint().setTextSize(f);
            boolean isInLayout = Build.VERSION.SDK_INT >= 18 ? this.f1255k.isInLayout() : false;
            if (this.f1255k.getLayout() != null) {
                this.f1248d = false;
                try {
                    Method a = m1506a("nullLayouts");
                    if (a != null) {
                        a.invoke(this.f1255k, new Object[0]);
                    }
                } catch (Exception e) {
                    Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#nullLayouts() method", e);
                }
                if (!isInLayout) {
                    this.f1255k.requestLayout();
                } else {
                    this.f1255k.forceLayout();
                }
                this.f1255k.invalidate();
            }
        }
    }

    /* renamed from: a */
    private void m1508a(float f, float f2, float f3) {
        if (f <= 0.0f) {
            throw new IllegalArgumentException("Minimum auto-size text size (" + f + "px) is less or equal to (0px)");
        } else if (f2 <= f) {
            throw new IllegalArgumentException("Maximum auto-size text size (" + f2 + "px) is less or equal to minimum auto-size " + "text size (" + f + "px)");
        } else if (f3 > 0.0f) {
            this.f1247c = 1;
            this.f1250f = f;
            this.f1251g = f2;
            this.f1249e = f3;
            this.f1253i = false;
        } else {
            throw new IllegalArgumentException("The auto-size step granularity (" + f3 + "px) is less or equal to (0px)");
        }
    }

    /* renamed from: a */
    private void m1509a(TypedArray typedArray) {
        int length = typedArray.length();
        int[] iArr = new int[length];
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                iArr[i] = typedArray.getDimensionPixelSize(i, -1);
            }
            this.f1252h = m1511a(iArr);
            m1514j();
        }
    }

    /* renamed from: a */
    private boolean m1510a(int i, RectF rectF) {
        CharSequence transformation;
        CharSequence text = this.f1255k.getText();
        TransformationMethod transformationMethod = this.f1255k.getTransformationMethod();
        if (!(transformationMethod == null || (transformation = transformationMethod.getTransformation(text, this.f1255k)) == null)) {
            text = transformation;
        }
        int maxLines = Build.VERSION.SDK_INT >= 16 ? this.f1255k.getMaxLines() : -1;
        TextPaint textPaint = this.f1254j;
        if (textPaint == null) {
            this.f1254j = new TextPaint();
        } else {
            textPaint.reset();
        }
        this.f1254j.set(this.f1255k.getPaint());
        this.f1254j.setTextSize((float) i);
        Layout.Alignment alignment = (Layout.Alignment) m1505a((Object) this.f1255k, "getLayoutAlignment", Layout.Alignment.ALIGN_NORMAL);
        StaticLayout a = Build.VERSION.SDK_INT >= 23 ? m1504a(text, alignment, Math.round(rectF.right), maxLines) : m1503a(text, alignment, Math.round(rectF.right));
        return (maxLines == -1 || (a.getLineCount() <= maxLines && a.getLineEnd(a.getLineCount() - 1) == text.length())) && ((float) a.getHeight()) <= rectF.bottom;
    }

    /* renamed from: a */
    private int[] m1511a(int[] iArr) {
        if (r0 == 0) {
            return iArr;
        }
        Arrays.sort(iArr);
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            if (i > 0 && Collections.binarySearch(arrayList, Integer.valueOf(i)) < 0) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        if (r0 == arrayList.size()) {
            return iArr;
        }
        int size = arrayList.size();
        int[] iArr2 = new int[size];
        for (int i2 = 0; i2 < size; i2++) {
            iArr2[i2] = ((Integer) arrayList.get(i2)).intValue();
        }
        return iArr2;
    }

    /* renamed from: h */
    private void m1512h() {
        this.f1247c = 0;
        this.f1250f = -1.0f;
        this.f1251g = -1.0f;
        this.f1249e = -1.0f;
        this.f1252h = new int[0];
        this.f1248d = false;
    }

    /* renamed from: i */
    private boolean m1513i() {
        if (!m1515k() || this.f1247c != 1) {
            this.f1248d = false;
        } else {
            if (!this.f1253i || this.f1252h.length == 0) {
                float round = (float) Math.round(this.f1250f);
                int i = 1;
                while (Math.round(this.f1249e + round) <= Math.round(this.f1251g)) {
                    i++;
                    round += this.f1249e;
                }
                int[] iArr = new int[i];
                float f = this.f1250f;
                for (int i2 = 0; i2 < i; i2++) {
                    iArr[i2] = Math.round(f);
                    f += this.f1249e;
                }
                this.f1252h = m1511a(iArr);
            }
            this.f1248d = true;
        }
        return this.f1248d;
    }

    /* renamed from: j */
    private boolean m1514j() {
        int length = this.f1252h.length;
        this.f1253i = length > 0;
        if (this.f1253i) {
            this.f1247c = 1;
            int[] iArr = this.f1252h;
            this.f1250f = (float) iArr[0];
            this.f1251g = (float) iArr[length - 1];
            this.f1249e = -1.0f;
        }
        return this.f1253i;
    }

    /* renamed from: k */
    private boolean m1515k() {
        return !(this.f1255k instanceof C0430p);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1765a() {
        if (mo1776g()) {
            if (this.f1248d) {
                if (this.f1255k.getMeasuredHeight() > 0 && this.f1255k.getMeasuredWidth() > 0) {
                    int measuredWidth = ((Boolean) m1505a((Object) this.f1255k, "getHorizontallyScrolling", false)).booleanValue() ? 1048576 : (this.f1255k.getMeasuredWidth() - this.f1255k.getTotalPaddingLeft()) - this.f1255k.getTotalPaddingRight();
                    int height = (this.f1255k.getHeight() - this.f1255k.getCompoundPaddingBottom()) - this.f1255k.getCompoundPaddingTop();
                    if (measuredWidth > 0 && height > 0) {
                        synchronized (f1245a) {
                            f1245a.setEmpty();
                            f1245a.right = (float) measuredWidth;
                            f1245a.bottom = (float) height;
                            float a = (float) m1502a(f1245a);
                            if (a != this.f1255k.getTextSize()) {
                                mo1767a(0, a);
                            }
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
            this.f1248d = true;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1766a(int i) {
        if (!m1515k()) {
            return;
        }
        if (i == 0) {
            m1512h();
        } else if (i == 1) {
            DisplayMetrics displayMetrics = this.f1256l.getResources().getDisplayMetrics();
            m1508a(TypedValue.applyDimension(2, 12.0f, displayMetrics), TypedValue.applyDimension(2, 112.0f, displayMetrics), 1.0f);
            if (m1513i()) {
                mo1765a();
            }
        } else {
            throw new IllegalArgumentException("Unknown auto-size text type: " + i);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1767a(int i, float f) {
        Context context = this.f1256l;
        m1507a(TypedValue.applyDimension(i, f, (context == null ? Resources.getSystem() : context.getResources()).getDisplayMetrics()));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1768a(int i, int i2, int i3, int i4) {
        if (m1515k()) {
            DisplayMetrics displayMetrics = this.f1256l.getResources().getDisplayMetrics();
            m1508a(TypedValue.applyDimension(i4, (float) i, displayMetrics), TypedValue.applyDimension(i4, (float) i2, displayMetrics), TypedValue.applyDimension(i4, (float) i3, displayMetrics));
            if (m1513i()) {
                mo1765a();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1769a(AttributeSet attributeSet, int i) {
        int resourceId;
        TypedArray obtainStyledAttributes = this.f1256l.obtainStyledAttributes(attributeSet, C0145j.AppCompatTextView, i, 0);
        if (obtainStyledAttributes.hasValue(C0145j.AppCompatTextView_autoSizeTextType)) {
            this.f1247c = obtainStyledAttributes.getInt(C0145j.AppCompatTextView_autoSizeTextType, 0);
        }
        float dimension = obtainStyledAttributes.hasValue(C0145j.AppCompatTextView_autoSizeStepGranularity) ? obtainStyledAttributes.getDimension(C0145j.AppCompatTextView_autoSizeStepGranularity, -1.0f) : -1.0f;
        float dimension2 = obtainStyledAttributes.hasValue(C0145j.AppCompatTextView_autoSizeMinTextSize) ? obtainStyledAttributes.getDimension(C0145j.AppCompatTextView_autoSizeMinTextSize, -1.0f) : -1.0f;
        float dimension3 = obtainStyledAttributes.hasValue(C0145j.AppCompatTextView_autoSizeMaxTextSize) ? obtainStyledAttributes.getDimension(C0145j.AppCompatTextView_autoSizeMaxTextSize, -1.0f) : -1.0f;
        if (obtainStyledAttributes.hasValue(C0145j.AppCompatTextView_autoSizePresetSizes) && (resourceId = obtainStyledAttributes.getResourceId(C0145j.AppCompatTextView_autoSizePresetSizes, 0)) > 0) {
            TypedArray obtainTypedArray = obtainStyledAttributes.getResources().obtainTypedArray(resourceId);
            m1509a(obtainTypedArray);
            obtainTypedArray.recycle();
        }
        obtainStyledAttributes.recycle();
        if (!m1515k()) {
            this.f1247c = 0;
        } else if (this.f1247c == 1) {
            if (!this.f1253i) {
                DisplayMetrics displayMetrics = this.f1256l.getResources().getDisplayMetrics();
                if (dimension2 == -1.0f) {
                    dimension2 = TypedValue.applyDimension(2, 12.0f, displayMetrics);
                }
                if (dimension3 == -1.0f) {
                    dimension3 = TypedValue.applyDimension(2, 112.0f, displayMetrics);
                }
                if (dimension == -1.0f) {
                    dimension = 1.0f;
                }
                m1508a(dimension2, dimension3, dimension);
            }
            m1513i();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1770a(int[] iArr, int i) {
        if (m1515k()) {
            int length = iArr.length;
            if (length > 0) {
                int[] iArr2 = new int[length];
                if (i == 0) {
                    iArr2 = Arrays.copyOf(iArr, length);
                } else {
                    DisplayMetrics displayMetrics = this.f1256l.getResources().getDisplayMetrics();
                    for (int i2 = 0; i2 < length; i2++) {
                        iArr2[i2] = Math.round(TypedValue.applyDimension(i, (float) iArr[i2], displayMetrics));
                    }
                }
                this.f1252h = m1511a(iArr2);
                if (!m1514j()) {
                    throw new IllegalArgumentException("None of the preset sizes is valid: " + Arrays.toString(iArr));
                }
            } else {
                this.f1253i = false;
            }
            if (m1513i()) {
                mo1765a();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public int mo1771b() {
        return Math.round(this.f1251g);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public int mo1772c() {
        return Math.round(this.f1250f);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public int mo1773d() {
        return Math.round(this.f1249e);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public int[] mo1774e() {
        return this.f1252h;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public int mo1775f() {
        return this.f1247c;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public boolean mo1776g() {
        return m1515k() && this.f1247c != 0;
    }
}
