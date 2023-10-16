package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.org.v4.util.R$styleable;
import java.lang.ref.WeakReference;

public final class ViewStubCompat extends View {
    private a mInflateListener;
    private int mInflatedId;
    private WeakReference<View> mInflatedViewRef;
    private LayoutInflater mInflater;
    private int mLayoutResource;

    public interface a {
        void onInflate(ViewStubCompat viewStubCompat, View view);
    }

    public ViewStubCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ViewStubCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mLayoutResource = 0;
        TypedArray $r4 = context.obtainStyledAttributes(attributeSet, R$styleable.ViewStubCompat, i, 0);
        this.mInflatedId = $r4.getResourceId(R$styleable.ViewStubCompat_android_inflatedId, -1);
        this.mLayoutResource = $r4.getResourceId(R$styleable.ViewStubCompat_android_layout, 0);
        setId($r4.getResourceId(R$styleable.ViewStubCompat_android_id, -1));
        $r4.recycle();
        setVisibility(8);
        setWillNotDraw(true);
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
    }

    public void draw(Canvas canvas) {
    }

    public int getInflatedId() {
        return this.mInflatedId;
    }

    public LayoutInflater getLayoutInflater() {
        return this.mInflater;
    }

    public int getLayoutResource() {
        return this.mLayoutResource;
    }

    public View inflate() {
        ViewParent $r1 = getParent();
        if ($r1 == null || !($r1 instanceof ViewGroup)) {
            throw new IllegalStateException("ViewStub must have a non-null ViewGroup viewParent");
        } else if (this.mLayoutResource != 0) {
            ViewGroup $r2 = (ViewGroup) $r1;
            LayoutInflater $r3 = this.mInflater;
            if ($r3 == null) {
                $r3 = LayoutInflater.from(getContext());
            }
            View $r5 = $r3.inflate(this.mLayoutResource, $r2, false);
            int $i0 = this.mInflatedId;
            if ($i0 != -1) {
                $r5.setId($i0);
            }
            int $i02 = $r2.indexOfChild(this);
            $r2.removeViewInLayout(this);
            ViewGroup.LayoutParams $r6 = getLayoutParams();
            if ($r6 != null) {
                $r2.addView($r5, $i02, $r6);
            } else {
                $r2.addView($r5, $i02);
            }
            this.mInflatedViewRef = new WeakReference($r5);
            a $r8 = this.mInflateListener;
            if ($r8 == null) {
                return $r5;
            }
            $r8.onInflate(this, $r5);
            return $r5;
        } else {
            throw new IllegalArgumentException("ViewStub must have a valid layoutResource");
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        setMeasuredDimension(0, 0);
    }

    public void setInflatedId(int i) {
        this.mInflatedId = i;
    }

    public void setLayoutInflater(LayoutInflater layoutInflater) {
        this.mInflater = layoutInflater;
    }

    public void setLayoutResource(int i) {
        this.mLayoutResource = i;
    }

    public void setOnInflateListener(a aVar) {
        this.mInflateListener = aVar;
    }

    public void setVisibility(int i) {
        WeakReference $r2 = this.mInflatedViewRef;
        if ($r2 != null) {
            View $r3 = (View) $r2.get();
            if ($r3 != null) {
                $r3.setVisibility(i);
                return;
            }
            throw new IllegalStateException("setVisibility called on un-referenced view");
        }
        super.setVisibility(i);
        if (i == 0 || i == 4) {
            inflate();
        }
    }
}
