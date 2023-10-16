package android.support.p025v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.p024v4.widget.C0221o;
import android.util.AttributeSet;
import android.widget.ImageButton;
import p000a.p001a.p005c.p014g.C0125s;
import p000a.p001a.p017d.p018a.C0136a;

/* renamed from: android.support.v7.widget.r */
public class C0434r extends ImageButton implements C0125s, C0221o {

    /* renamed from: a */
    private final C0412j f1628a;

    /* renamed from: b */
    private final C0436s f1629b;

    public C0434r(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0136a.imageButtonStyle);
    }

    public C0434r(Context context, AttributeSet attributeSet, int i) {
        super(C0433qa.m1887a(context), attributeSet, i);
        this.f1628a = new C0412j(this);
        this.f1628a.mo2170a(attributeSet, i);
        this.f1629b = new C0436s(this);
        this.f1629b.mo2258a(attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        C0412j jVar = this.f1628a;
        if (jVar != null) {
            jVar.mo2165a();
        }
        C0436s sVar = this.f1629b;
        if (sVar != null) {
            sVar.mo2254a();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        C0412j jVar = this.f1628a;
        if (jVar != null) {
            return jVar.mo2171b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C0412j jVar = this.f1628a;
        if (jVar != null) {
            return jVar.mo2173c();
        }
        return null;
    }

    public ColorStateList getSupportImageTintList() {
        C0436s sVar = this.f1629b;
        if (sVar != null) {
            return sVar.mo2259b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportImageTintMode() {
        C0436s sVar = this.f1629b;
        if (sVar != null) {
            return sVar.mo2260c();
        }
        return null;
    }

    public boolean hasOverlappingRendering() {
        return this.f1629b.mo2261d() && super.hasOverlappingRendering();
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0412j jVar = this.f1628a;
        if (jVar != null) {
            jVar.mo2169a(drawable);
        }
    }

    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        C0412j jVar = this.f1628a;
        if (jVar != null) {
            jVar.mo2166a(i);
        }
    }

    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        C0436s sVar = this.f1629b;
        if (sVar != null) {
            sVar.mo2254a();
        }
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        C0436s sVar = this.f1629b;
        if (sVar != null) {
            sVar.mo2254a();
        }
    }

    public void setImageResource(int i) {
        this.f1629b.mo2255a(i);
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        C0436s sVar = this.f1629b;
        if (sVar != null) {
            sVar.mo2254a();
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        C0412j jVar = this.f1628a;
        if (jVar != null) {
            jVar.mo2172b(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        C0412j jVar = this.f1628a;
        if (jVar != null) {
            jVar.mo2168a(mode);
        }
    }

    public void setSupportImageTintList(ColorStateList colorStateList) {
        C0436s sVar = this.f1629b;
        if (sVar != null) {
            sVar.mo2256a(colorStateList);
        }
    }

    public void setSupportImageTintMode(PorterDuff.Mode mode) {
        C0436s sVar = this.f1629b;
        if (sVar != null) {
            sVar.mo2257a(mode);
        }
    }
}
