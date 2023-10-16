package android.support.v7.widget;

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
import com.org.v4.util.R$styleable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

class f {
    private static final RectF g = new RectF();
    private static ConcurrentHashMap<String, Method> m = new ConcurrentHashMap();
    private final TextView a;
    private TextPaint b;
    private final Context c;
    private boolean h = false;
    private int k = 0;
    private boolean l = false;
    private int[] n = new int[0];
    private float x = -1.0f;
    private float y = -1.0f;
    private float z = -1.0f;

    f(TextView textView) {
        this.a = textView;
        this.c = this.a.getContext();
    }

    private int a(RectF rectF) {
        int $i0 = this.n.length;
        if ($i0 != 0) {
            int $i02 = $i0 - 1;
            int $i1 = 1;
            int $i2 = 0;
            while ($i1 <= $i02) {
                int $i22 = ($i1 + $i02) / 2;
                if (a(this.n[$i22], rectF)) {
                    int $i3 = $i22 + 1;
                    $i2 = $i1;
                    $i1 = $i3;
                } else {
                    $i2 = $i22 - 1;
                    $i02 = $i2;
                }
            }
            return this.n[$i2];
        }
        throw new IllegalStateException("No available text sizes to choose from.");
    }

    private StaticLayout a(CharSequence charSequence, Layout.Alignment alignment, int i) {
        boolean $z0;
        float $f1;
        float $f0;
        if (Build.VERSION.SDK_INT >= 16) {
            $f0 = this.a.getLineSpacingMultiplier();
            $f1 = this.a.getLineSpacingExtra();
            $z0 = this.a.getIncludeFontPadding();
        } else {
            $f0 = ((Float) a((Object) this.a, "getLineSpacingMultiplier", (Object) Float.valueOf(1.0f))).floatValue();
            $f1 = ((Float) a((Object) this.a, "getLineSpacingExtra", (Object) Float.valueOf(0.0f))).floatValue();
            $z0 = ((Boolean) a((Object) this.a, "getIncludeFontPadding", (Object) 1)).booleanValue();
        }
        return new StaticLayout(charSequence, this.b, i, alignment, $f0, $f1, $z0);
    }

    private StaticLayout a(CharSequence charSequence, Layout.Alignment alignment, int i, int $i1) {
        TextDirectionHeuristic $r4 = (TextDirectionHeuristic) a((Object) this.a, "getTextDirectionHeuristic", (Object) TextDirectionHeuristics.FIRSTSTRONG_LTR);
        StaticLayout.Builder $r7 = StaticLayout.Builder.obtain(charSequence, 0, charSequence.length(), this.b, i).setAlignment(alignment).setLineSpacing(this.a.getLineSpacingExtra(), this.a.getLineSpacingMultiplier()).setIncludePad(this.a.getIncludeFontPadding()).setBreakStrategy(this.a.getBreakStrategy()).setHyphenationFrequency(this.a.getHyphenationFrequency());
        if ($i1 == -1) {
            $i1 = Integer.MAX_VALUE;
        }
        return $r7.setMaxLines($i1).setTextDirection($r4).build();
    }

    private Object a(Object obj, String str, Object obj2) {
        try {
            return get(str).invoke(obj, new Object[0]);
        } catch (Exception $r7) {
            Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#" + str + "() method", $r7);
            return obj2;
        }
    }

    private void a(float f) {
        if (f != this.a.getPaint().getTextSize()) {
            this.a.getPaint().setTextSize(f);
            boolean $z0 = Build.VERSION.SDK_INT >= 18 ? this.a.isInLayout() : false;
            if (this.a.getLayout() != null) {
                this.l = false;
                try {
                    Method $r4 = get("nullLayouts");
                    if ($r4 != null) {
                        $r4.invoke(this.a, new Object[0]);
                    }
                } catch (Exception $r6) {
                    Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#nullLayouts() method", $r6);
                }
                if (!$z0) {
                    this.a.requestLayout();
                } else {
                    this.a.forceLayout();
                }
                this.a.invalidate();
            }
        }
    }

    private void a(float f, float f2, float f3) {
        if (f <= 0.0f) {
            throw new IllegalArgumentException("Minimum auto-size text size (" + f + "px) is less or equal to (0px)");
        } else if (f2 <= f) {
            throw new IllegalArgumentException("Maximum auto-size text size (" + f2 + "px) is less or equal to minimum auto-size " + "text size (" + f + "px)");
        } else if (f3 > 0.0f) {
            this.k = 1;
            this.z = f;
            this.x = f2;
            this.y = f3;
            this.h = false;
        } else {
            throw new IllegalArgumentException("The auto-size step granularity (" + f3 + "px) is less or equal to (0px)");
        }
    }

    private boolean a(int i, RectF rectF) {
        CharSequence $r3;
        CharSequence $r32 = this.a.getText();
        CharSequence $r4 = $r32;
        TransformationMethod $r5 = this.a.getTransformationMethod();
        if (!($r5 == null || ($r3 = $r5.getTransformation($r32, this.a)) == null)) {
            $r4 = $r3;
        }
        int $i1 = Build.VERSION.SDK_INT >= 16 ? this.a.getMaxLines() : -1;
        TextPaint $r6 = this.b;
        if ($r6 == null) {
            this.b = new TextPaint();
        } else {
            $r6.reset();
        }
        this.b.set(this.a.getPaint());
        this.b.setTextSize((float) i);
        Layout.Alignment $r8 = (Layout.Alignment) a((Object) this.a, "getLayoutAlignment", (Object) Layout.Alignment.ALIGN_NORMAL);
        StaticLayout $r10 = Build.VERSION.SDK_INT >= 23 ? a($r4, $r8, Math.round(rectF.right), $i1) : a($r4, $r8, Math.round(rectF.right));
        if ($i1 != -1) {
            if ($r10.getLineCount() > $i1) {
                return false;
            }
            int $i0 = $r10.getLineCount() - 1;
            int i2 = $i0;
            if ($r10.getLineEnd($i0) != $r4.length()) {
                return false;
            }
        }
        return ((float) $r10.getHeight()) <= rectF.bottom;
    }

    private boolean b() {
        if (!size() || this.k != 1) {
            this.l = false;
        } else {
            if (!this.h || this.n.length == 0) {
                float $f0 = (float) Math.round(this.z);
                int $i1 = 1;
                while (Math.round(this.y + $f0) <= Math.round(this.x)) {
                    $i1++;
                    $f0 += this.y;
                }
                int[] $r1 = new int[$i1];
                float $f02 = this.z;
                for (int $i0 = 0; $i0 < $i1; $i0++) {
                    $r1[$i0] = Math.round($f02);
                    $f02 += this.y;
                }
                this.n = sort($r1);
            }
            this.l = true;
        }
        return this.l;
    }

    private void c() {
        this.k = 0;
        this.z = -1.0f;
        this.x = -1.0f;
        this.y = -1.0f;
        this.n = new int[0];
        this.l = false;
    }

    private boolean d() {
        int $i0 = this.n.length;
        this.h = $i0 > 0;
        if (this.h) {
            this.k = 1;
            int[] $r1 = this.n;
            this.z = (float) $r1[0];
            this.x = (float) $r1[$i0 - 1];
            this.y = -1.0f;
        }
        return this.h;
    }

    private Method get(String str) {
        try {
            Method $r4 = m.get(str);
            if ($r4 == null) {
                $r4 = TextView.class.getDeclaredMethod(str, new Class[0]);
                if ($r4 != null) {
                    $r4.setAccessible(true);
                    m.put(str, $r4);
                    return $r4;
                }
            }
            return $r4;
        } catch (Exception $r7) {
            Log.w("ACTVAutoSizeHelper", "Failed to retrieve TextView#" + str + "() method", $r7);
            return null;
        }
    }

    private void init(TypedArray typedArray) {
        int $i0 = typedArray.length();
        int[] $r1 = new int[$i0];
        if ($i0 > 0) {
            for (int $i1 = 0; $i1 < $i0; $i1++) {
                $r1[$i1] = typedArray.getDimensionPixelSize($i1, -1);
            }
            this.n = sort($r1);
            d();
        }
    }

    private boolean size() {
        return !(this.a instanceof AppCompatEditText);
    }

    private int[] sort(int[] iArr) {
        if ($i0 == 0) {
            return iArr;
        }
        Arrays.sort(iArr);
        ArrayList $r1 = new ArrayList();
        for (int $i3 : iArr) {
            if ($i3 > 0 && Collections.binarySearch($r1, Integer.valueOf($i3)) < 0) {
                $r1.add(Integer.valueOf($i3));
            }
        }
        if ($i0 == $r1.size()) {
            return iArr;
        }
        int $i0 = $r1.size();
        int[] $r2 = new int[$i0];
        for (int $i1 = 0; $i1 < $i0; $i1++) {
            $r2[$i1] = ((Integer) $r1.get($i1)).intValue();
        }
        return $r2;
    }

    /* access modifiers changed from: package-private */
    public void a() {
        if (i()) {
            if (this.l) {
                if (this.a.getMeasuredHeight() > 0 && this.a.getMeasuredWidth() > 0) {
                    int $i0 = ((Boolean) a((Object) this.a, "getHorizontallyScrolling", (Object) false)).booleanValue() ? 1048576 : (this.a.getMeasuredWidth() - this.a.getTotalPaddingLeft()) - this.a.getTotalPaddingRight();
                    int $i1 = (this.a.getHeight() - this.a.getCompoundPaddingBottom()) - this.a.getCompoundPaddingTop();
                    if ($i0 > 0 && $i1 > 0) {
                        synchronized (g) {
                            g.setEmpty();
                            g.right = (float) $i0;
                            g.bottom = (float) $i1;
                            float $f0 = (float) a(g);
                            if ($f0 != this.a.getTextSize()) {
                                b(0, $f0);
                            }
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
            this.l = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void a(int i, int i2, int i3, int i4) {
        if (size()) {
            DisplayMetrics $r3 = this.c.getResources().getDisplayMetrics();
            a(TypedValue.applyDimension(i4, (float) i, $r3), TypedValue.applyDimension(i4, (float) i2, $r3), TypedValue.applyDimension(i4, (float) i3, $r3));
            if (b()) {
                a();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(AttributeSet attributeSet, int i) {
        int $i0;
        TypedArray $r4 = this.c.obtainStyledAttributes(attributeSet, R$styleable.AppCompatTextView, i, 0);
        if ($r4.hasValue(R$styleable.AppCompatTextView_autoSizeTextType)) {
            this.k = $r4.getInt(R$styleable.AppCompatTextView_autoSizeTextType, 0);
        }
        float $f0 = $r4.hasValue(R$styleable.AppCompatTextView_autoSizeStepGranularity) ? $r4.getDimension(R$styleable.AppCompatTextView_autoSizeStepGranularity, -1.0f) : -1.0f;
        float $f1 = $r4.hasValue(R$styleable.AppCompatTextView_autoSizeMinTextSize) ? $r4.getDimension(R$styleable.AppCompatTextView_autoSizeMinTextSize, -1.0f) : -1.0f;
        float $f2 = $r4.hasValue(R$styleable.AppCompatTextView_autoSizeMaxTextSize) ? $r4.getDimension(R$styleable.AppCompatTextView_autoSizeMaxTextSize, -1.0f) : -1.0f;
        if ($r4.hasValue(R$styleable.AppCompatTextView_autoSizePresetSizes) && ($i0 = $r4.getResourceId(R$styleable.AppCompatTextView_autoSizePresetSizes, 0)) > 0) {
            TypedArray $r6 = $r4.getResources().obtainTypedArray($i0);
            init($r6);
            $r6.recycle();
        }
        $r4.recycle();
        if (!size()) {
            this.k = 0;
        } else if (this.k == 1) {
            if (!this.h) {
                DisplayMetrics $r7 = this.c.getResources().getDisplayMetrics();
                if ($f1 == -1.0f) {
                    $f1 = TypedValue.applyDimension(2, 12.0f, $r7);
                }
                if ($f2 == -1.0f) {
                    $f2 = TypedValue.applyDimension(2, 112.0f, $r7);
                }
                if ($f0 == -1.0f) {
                    $f0 = 1.0f;
                }
                a($f1, $f2, $f0);
            }
            b();
        }
    }

    /* access modifiers changed from: package-private */
    public void a(int[] iArr, int i) {
        if (size()) {
            int $i1 = iArr.length;
            if ($i1 > 0) {
                int[] $r2 = new int[$i1];
                if (i == 0) {
                    $r2 = Arrays.copyOf(iArr, $i1);
                } else {
                    DisplayMetrics $r5 = this.c.getResources().getDisplayMetrics();
                    for (int $i2 = 0; $i2 < $i1; $i2++) {
                        $r2[$i2] = Math.round(TypedValue.applyDimension(i, (float) iArr[$i2], $r5));
                    }
                }
                this.n = sort($r2);
                if (!d()) {
                    throw new IllegalArgumentException("None of the preset sizes is valid: " + Arrays.toString(iArr));
                }
            } else {
                this.h = false;
            }
            if (b()) {
                a();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int add() {
        return Math.round(this.z);
    }

    /* access modifiers changed from: package-private */
    public void b(int i) {
        if (!size()) {
            return;
        }
        if (i == 0) {
            c();
        } else if (i == 1) {
            DisplayMetrics $r3 = this.c.getResources().getDisplayMetrics();
            a(TypedValue.applyDimension(2, 12.0f, $r3), TypedValue.applyDimension(2, 112.0f, $r3), 1.0f);
            if (b()) {
                a();
            }
        } else {
            throw new IllegalArgumentException("Unknown auto-size text type: " + i);
        }
    }

    /* access modifiers changed from: package-private */
    public void b(int i, float f) {
        Context $r1 = this.c;
        a(TypedValue.applyDimension(i, f, ($r1 == null ? Resources.getSystem() : $r1.getResources()).getDisplayMetrics()));
    }

    /* access modifiers changed from: package-private */
    public int clear() {
        return Math.round(this.x);
    }

    /* access modifiers changed from: package-private */
    public int[] close() {
        return this.n;
    }

    /* access modifiers changed from: package-private */
    public int getHeight() {
        return Math.round(this.y);
    }

    /* access modifiers changed from: package-private */
    public boolean i() {
        return size() && this.k != 0;
    }

    /* access modifiers changed from: package-private */
    public int k() {
        return this.k;
    }
}
