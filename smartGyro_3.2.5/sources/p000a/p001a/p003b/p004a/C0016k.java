package p000a.p001a.p003b.p004a;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.support.p024v4.graphics.drawable.C0190a;
import android.util.AttributeSet;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p000a.p001a.p005c.p006a.p007a.C0027b;
import p000a.p001a.p005c.p006a.p007a.C0040i;
import p000a.p001a.p005c.p008b.C0043b;
import p000a.p001a.p005c.p013f.C0078b;

/* renamed from: a.a.b.a.k */
public class C0016k extends C0014i {

    /* renamed from: b */
    static final PorterDuff.Mode f30b = PorterDuff.Mode.SRC_IN;

    /* renamed from: c */
    private C0023g f31c;

    /* renamed from: d */
    private PorterDuffColorFilter f32d;

    /* renamed from: e */
    private ColorFilter f33e;

    /* renamed from: f */
    private boolean f34f;

    /* renamed from: g */
    private boolean f35g;

    /* renamed from: h */
    private Drawable.ConstantState f36h;

    /* renamed from: i */
    private final float[] f37i;

    /* renamed from: j */
    private final Matrix f38j;

    /* renamed from: k */
    private final Rect f39k;

    /* renamed from: a.a.b.a.k$a */
    private static class C0017a extends C0021e {
        public C0017a() {
        }

        public C0017a(C0017a aVar) {
            super(aVar);
        }

        /* renamed from: a */
        private void m40a(TypedArray typedArray) {
            String string = typedArray.getString(0);
            if (string != null) {
                this.f67b = string;
            }
            String string2 = typedArray.getString(1);
            if (string2 != null) {
                this.f66a = C0043b.m149a(string2);
            }
        }

        /* renamed from: a */
        public void mo84a(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            if (C0040i.m134a(xmlPullParser, "pathData")) {
                TypedArray a = C0040i.m131a(resources, theme, attributeSet, C0003a.f3d);
                m40a(a);
                a.recycle();
            }
        }

        /* renamed from: b */
        public boolean mo85b() {
            return true;
        }
    }

    /* renamed from: a.a.b.a.k$b */
    private static class C0018b extends C0021e {

        /* renamed from: d */
        private int[] f40d;

        /* renamed from: e */
        C0027b f41e;

        /* renamed from: f */
        float f42f = 0.0f;

        /* renamed from: g */
        C0027b f43g;

        /* renamed from: h */
        float f44h = 1.0f;

        /* renamed from: i */
        int f45i = 0;

        /* renamed from: j */
        float f46j = 1.0f;

        /* renamed from: k */
        float f47k = 0.0f;

        /* renamed from: l */
        float f48l = 1.0f;

        /* renamed from: m */
        float f49m = 0.0f;

        /* renamed from: n */
        Paint.Cap f50n = Paint.Cap.BUTT;

        /* renamed from: o */
        Paint.Join f51o = Paint.Join.MITER;

        /* renamed from: p */
        float f52p = 4.0f;

        public C0018b() {
        }

        public C0018b(C0018b bVar) {
            super(bVar);
            this.f40d = bVar.f40d;
            this.f41e = bVar.f41e;
            this.f42f = bVar.f42f;
            this.f44h = bVar.f44h;
            this.f43g = bVar.f43g;
            this.f45i = bVar.f45i;
            this.f46j = bVar.f46j;
            this.f47k = bVar.f47k;
            this.f48l = bVar.f48l;
            this.f49m = bVar.f49m;
            this.f50n = bVar.f50n;
            this.f51o = bVar.f51o;
            this.f52p = bVar.f52p;
        }

        /* renamed from: a */
        private Paint.Cap m43a(int i, Paint.Cap cap) {
            return i != 0 ? i != 1 ? i != 2 ? cap : Paint.Cap.SQUARE : Paint.Cap.ROUND : Paint.Cap.BUTT;
        }

        /* renamed from: a */
        private Paint.Join m44a(int i, Paint.Join join) {
            return i != 0 ? i != 1 ? i != 2 ? join : Paint.Join.BEVEL : Paint.Join.ROUND : Paint.Join.MITER;
        }

        /* renamed from: a */
        private void m45a(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme) {
            this.f40d = null;
            if (C0040i.m134a(xmlPullParser, "pathData")) {
                String string = typedArray.getString(0);
                if (string != null) {
                    this.f67b = string;
                }
                String string2 = typedArray.getString(2);
                if (string2 != null) {
                    this.f66a = C0043b.m149a(string2);
                }
                Resources.Theme theme2 = theme;
                this.f43g = C0040i.m130a(typedArray, xmlPullParser, theme2, "fillColor", 1, 0);
                this.f46j = C0040i.m128a(typedArray, xmlPullParser, "fillAlpha", 12, this.f46j);
                this.f50n = m43a(C0040i.m135b(typedArray, xmlPullParser, "strokeLineCap", 8, -1), this.f50n);
                this.f51o = m44a(C0040i.m135b(typedArray, xmlPullParser, "strokeLineJoin", 9, -1), this.f51o);
                this.f52p = C0040i.m128a(typedArray, xmlPullParser, "strokeMiterLimit", 10, this.f52p);
                this.f41e = C0040i.m130a(typedArray, xmlPullParser, theme2, "strokeColor", 3, 0);
                this.f44h = C0040i.m128a(typedArray, xmlPullParser, "strokeAlpha", 11, this.f44h);
                this.f42f = C0040i.m128a(typedArray, xmlPullParser, "strokeWidth", 4, this.f42f);
                this.f48l = C0040i.m128a(typedArray, xmlPullParser, "trimPathEnd", 6, this.f48l);
                this.f49m = C0040i.m128a(typedArray, xmlPullParser, "trimPathOffset", 7, this.f49m);
                this.f47k = C0040i.m128a(typedArray, xmlPullParser, "trimPathStart", 5, this.f47k);
                this.f45i = C0040i.m135b(typedArray, xmlPullParser, "fillType", 13, this.f45i);
            }
        }

        /* renamed from: a */
        public void mo86a(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray a = C0040i.m131a(resources, theme, attributeSet, C0003a.f2c);
            m45a(a, xmlPullParser, theme);
            a.recycle();
        }

        /* renamed from: a */
        public boolean mo87a() {
            return this.f43g.mo156d() || this.f41e.mo156d();
        }

        /* renamed from: a */
        public boolean mo88a(int[] iArr) {
            return this.f41e.mo152a(iArr) | this.f43g.mo152a(iArr);
        }

        /* access modifiers changed from: package-private */
        public float getFillAlpha() {
            return this.f46j;
        }

        /* access modifiers changed from: package-private */
        public int getFillColor() {
            return this.f43g.mo151a();
        }

        /* access modifiers changed from: package-private */
        public float getStrokeAlpha() {
            return this.f44h;
        }

        /* access modifiers changed from: package-private */
        public int getStrokeColor() {
            return this.f41e.mo151a();
        }

        /* access modifiers changed from: package-private */
        public float getStrokeWidth() {
            return this.f42f;
        }

        /* access modifiers changed from: package-private */
        public float getTrimPathEnd() {
            return this.f48l;
        }

        /* access modifiers changed from: package-private */
        public float getTrimPathOffset() {
            return this.f49m;
        }

        /* access modifiers changed from: package-private */
        public float getTrimPathStart() {
            return this.f47k;
        }

        /* access modifiers changed from: package-private */
        public void setFillAlpha(float f) {
            this.f46j = f;
        }

        /* access modifiers changed from: package-private */
        public void setFillColor(int i) {
            this.f43g.mo154b(i);
        }

        /* access modifiers changed from: package-private */
        public void setStrokeAlpha(float f) {
            this.f44h = f;
        }

        /* access modifiers changed from: package-private */
        public void setStrokeColor(int i) {
            this.f41e.mo154b(i);
        }

        /* access modifiers changed from: package-private */
        public void setStrokeWidth(float f) {
            this.f42f = f;
        }

        /* access modifiers changed from: package-private */
        public void setTrimPathEnd(float f) {
            this.f48l = f;
        }

        /* access modifiers changed from: package-private */
        public void setTrimPathOffset(float f) {
            this.f49m = f;
        }

        /* access modifiers changed from: package-private */
        public void setTrimPathStart(float f) {
            this.f47k = f;
        }
    }

    /* renamed from: a.a.b.a.k$c */
    private static class C0019c extends C0020d {

        /* renamed from: a */
        final Matrix f53a = new Matrix();

        /* renamed from: b */
        final ArrayList<C0020d> f54b = new ArrayList<>();

        /* renamed from: c */
        float f55c = 0.0f;

        /* renamed from: d */
        private float f56d = 0.0f;

        /* renamed from: e */
        private float f57e = 0.0f;

        /* renamed from: f */
        private float f58f = 1.0f;

        /* renamed from: g */
        private float f59g = 1.0f;

        /* renamed from: h */
        private float f60h = 0.0f;

        /* renamed from: i */
        private float f61i = 0.0f;

        /* renamed from: j */
        final Matrix f62j = new Matrix();

        /* renamed from: k */
        int f63k;

        /* renamed from: l */
        private int[] f64l;

        /* renamed from: m */
        private String f65m = null;

        public C0019c() {
            super();
        }

        public C0019c(C0019c cVar, C0078b<String, Object> bVar) {
            super();
            C0021e eVar;
            this.f55c = cVar.f55c;
            this.f56d = cVar.f56d;
            this.f57e = cVar.f57e;
            this.f58f = cVar.f58f;
            this.f59g = cVar.f59g;
            this.f60h = cVar.f60h;
            this.f61i = cVar.f61i;
            this.f64l = cVar.f64l;
            this.f65m = cVar.f65m;
            this.f63k = cVar.f63k;
            String str = this.f65m;
            if (str != null) {
                bVar.put(str, this);
            }
            this.f62j.set(cVar.f62j);
            ArrayList<C0020d> arrayList = cVar.f54b;
            for (int i = 0; i < arrayList.size(); i++) {
                C0020d dVar = arrayList.get(i);
                if (dVar instanceof C0019c) {
                    this.f54b.add(new C0019c((C0019c) dVar, bVar));
                } else {
                    if (dVar instanceof C0018b) {
                        eVar = new C0018b((C0018b) dVar);
                    } else if (dVar instanceof C0017a) {
                        eVar = new C0017a((C0017a) dVar);
                    } else {
                        throw new IllegalStateException("Unknown object in the tree!");
                    }
                    this.f54b.add(eVar);
                    String str2 = eVar.f67b;
                    if (str2 != null) {
                        bVar.put(str2, eVar);
                    }
                }
            }
        }

        /* renamed from: a */
        private void m49a(TypedArray typedArray, XmlPullParser xmlPullParser) {
            this.f64l = null;
            this.f55c = C0040i.m128a(typedArray, xmlPullParser, "rotation", 5, this.f55c);
            this.f56d = typedArray.getFloat(1, this.f56d);
            this.f57e = typedArray.getFloat(2, this.f57e);
            this.f58f = C0040i.m128a(typedArray, xmlPullParser, "scaleX", 3, this.f58f);
            this.f59g = C0040i.m128a(typedArray, xmlPullParser, "scaleY", 4, this.f59g);
            this.f60h = C0040i.m128a(typedArray, xmlPullParser, "translateX", 6, this.f60h);
            this.f61i = C0040i.m128a(typedArray, xmlPullParser, "translateY", 7, this.f61i);
            String string = typedArray.getString(0);
            if (string != null) {
                this.f65m = string;
            }
            m50b();
        }

        /* renamed from: b */
        private void m50b() {
            this.f62j.reset();
            this.f62j.postTranslate(-this.f56d, -this.f57e);
            this.f62j.postScale(this.f58f, this.f59g);
            this.f62j.postRotate(this.f55c, 0.0f, 0.0f);
            this.f62j.postTranslate(this.f60h + this.f56d, this.f61i + this.f57e);
        }

        /* renamed from: a */
        public void mo105a(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray a = C0040i.m131a(resources, theme, attributeSet, C0003a.f1b);
            m49a(a, xmlPullParser);
            a.recycle();
        }

        /* renamed from: a */
        public boolean mo87a() {
            for (int i = 0; i < this.f54b.size(); i++) {
                if (this.f54b.get(i).mo87a()) {
                    return true;
                }
            }
            return false;
        }

        /* renamed from: a */
        public boolean mo88a(int[] iArr) {
            boolean z = false;
            for (int i = 0; i < this.f54b.size(); i++) {
                z |= this.f54b.get(i).mo88a(iArr);
            }
            return z;
        }

        public String getGroupName() {
            return this.f65m;
        }

        public Matrix getLocalMatrix() {
            return this.f62j;
        }

        public float getPivotX() {
            return this.f56d;
        }

        public float getPivotY() {
            return this.f57e;
        }

        public float getRotation() {
            return this.f55c;
        }

        public float getScaleX() {
            return this.f58f;
        }

        public float getScaleY() {
            return this.f59g;
        }

        public float getTranslateX() {
            return this.f60h;
        }

        public float getTranslateY() {
            return this.f61i;
        }

        public void setPivotX(float f) {
            if (f != this.f56d) {
                this.f56d = f;
                m50b();
            }
        }

        public void setPivotY(float f) {
            if (f != this.f57e) {
                this.f57e = f;
                m50b();
            }
        }

        public void setRotation(float f) {
            if (f != this.f55c) {
                this.f55c = f;
                m50b();
            }
        }

        public void setScaleX(float f) {
            if (f != this.f58f) {
                this.f58f = f;
                m50b();
            }
        }

        public void setScaleY(float f) {
            if (f != this.f59g) {
                this.f59g = f;
                m50b();
            }
        }

        public void setTranslateX(float f) {
            if (f != this.f60h) {
                this.f60h = f;
                m50b();
            }
        }

        public void setTranslateY(float f) {
            if (f != this.f61i) {
                this.f61i = f;
                m50b();
            }
        }
    }

    /* renamed from: a.a.b.a.k$d */
    private static abstract class C0020d {
        private C0020d() {
        }

        /* renamed from: a */
        public boolean mo87a() {
            return false;
        }

        /* renamed from: a */
        public boolean mo88a(int[] iArr) {
            return false;
        }
    }

    /* renamed from: a.a.b.a.k$e */
    private static abstract class C0021e extends C0020d {

        /* renamed from: a */
        protected C0043b.C0045b[] f66a = null;

        /* renamed from: b */
        String f67b;

        /* renamed from: c */
        int f68c;

        public C0021e() {
            super();
        }

        public C0021e(C0021e eVar) {
            super();
            this.f67b = eVar.f67b;
            this.f68c = eVar.f68c;
            this.f66a = C0043b.m150a(eVar.f66a);
        }

        /* renamed from: a */
        public void mo122a(Path path) {
            path.reset();
            C0043b.C0045b[] bVarArr = this.f66a;
            if (bVarArr != null) {
                C0043b.C0045b.m157a(bVarArr, path);
            }
        }

        /* renamed from: b */
        public boolean mo85b() {
            return false;
        }

        public C0043b.C0045b[] getPathData() {
            return this.f66a;
        }

        public String getPathName() {
            return this.f67b;
        }

        public void setPathData(C0043b.C0045b[] bVarArr) {
            if (!C0043b.m147a(this.f66a, bVarArr)) {
                this.f66a = C0043b.m150a(bVarArr);
            } else {
                C0043b.m152b(this.f66a, bVarArr);
            }
        }
    }

    /* renamed from: a.a.b.a.k$f */
    private static class C0022f {

        /* renamed from: a */
        private static final Matrix f69a = new Matrix();

        /* renamed from: b */
        private final Path f70b;

        /* renamed from: c */
        private final Path f71c;

        /* renamed from: d */
        private final Matrix f72d;

        /* renamed from: e */
        Paint f73e;

        /* renamed from: f */
        Paint f74f;

        /* renamed from: g */
        private PathMeasure f75g;

        /* renamed from: h */
        private int f76h;

        /* renamed from: i */
        final C0019c f77i;

        /* renamed from: j */
        float f78j;

        /* renamed from: k */
        float f79k;

        /* renamed from: l */
        float f80l;

        /* renamed from: m */
        float f81m;

        /* renamed from: n */
        int f82n;

        /* renamed from: o */
        String f83o;

        /* renamed from: p */
        Boolean f84p;

        /* renamed from: q */
        final C0078b<String, Object> f85q;

        public C0022f() {
            this.f72d = new Matrix();
            this.f78j = 0.0f;
            this.f79k = 0.0f;
            this.f80l = 0.0f;
            this.f81m = 0.0f;
            this.f82n = 255;
            this.f83o = null;
            this.f84p = null;
            this.f85q = new C0078b<>();
            this.f77i = new C0019c();
            this.f70b = new Path();
            this.f71c = new Path();
        }

        public C0022f(C0022f fVar) {
            this.f72d = new Matrix();
            this.f78j = 0.0f;
            this.f79k = 0.0f;
            this.f80l = 0.0f;
            this.f81m = 0.0f;
            this.f82n = 255;
            this.f83o = null;
            this.f84p = null;
            this.f85q = new C0078b<>();
            this.f77i = new C0019c(fVar.f77i, this.f85q);
            this.f70b = new Path(fVar.f70b);
            this.f71c = new Path(fVar.f71c);
            this.f78j = fVar.f78j;
            this.f79k = fVar.f79k;
            this.f80l = fVar.f80l;
            this.f81m = fVar.f81m;
            this.f76h = fVar.f76h;
            this.f82n = fVar.f82n;
            this.f83o = fVar.f83o;
            String str = fVar.f83o;
            if (str != null) {
                this.f85q.put(str, this);
            }
            this.f84p = fVar.f84p;
        }

        /* renamed from: a */
        private static float m58a(float f, float f2, float f3, float f4) {
            return (f * f4) - (f2 * f3);
        }

        /* renamed from: a */
        private float m59a(Matrix matrix) {
            float[] fArr = {0.0f, 1.0f, 1.0f, 0.0f};
            matrix.mapVectors(fArr);
            float a = m58a(fArr[0], fArr[1], fArr[2], fArr[3]);
            float max = Math.max((float) Math.hypot((double) fArr[0], (double) fArr[1]), (float) Math.hypot((double) fArr[2], (double) fArr[3]));
            if (max > 0.0f) {
                return Math.abs(a) / max;
            }
            return 0.0f;
        }

        /* renamed from: a */
        private void m60a(C0019c cVar, C0021e eVar, Canvas canvas, int i, int i2, ColorFilter colorFilter) {
            float f = ((float) i) / this.f80l;
            float f2 = ((float) i2) / this.f81m;
            float min = Math.min(f, f2);
            Matrix matrix = cVar.f53a;
            this.f72d.set(matrix);
            this.f72d.postScale(f, f2);
            float a = m59a(matrix);
            if (a != 0.0f) {
                eVar.mo122a(this.f70b);
                Path path = this.f70b;
                this.f71c.reset();
                if (eVar.mo85b()) {
                    this.f71c.addPath(path, this.f72d);
                    canvas.clipPath(this.f71c);
                    return;
                }
                C0018b bVar = (C0018b) eVar;
                if (!(bVar.f47k == 0.0f && bVar.f48l == 1.0f)) {
                    float f3 = bVar.f47k;
                    float f4 = bVar.f49m;
                    float f5 = (f3 + f4) % 1.0f;
                    float f6 = (bVar.f48l + f4) % 1.0f;
                    if (this.f75g == null) {
                        this.f75g = new PathMeasure();
                    }
                    this.f75g.setPath(this.f70b, false);
                    float length = this.f75g.getLength();
                    float f7 = f5 * length;
                    float f8 = f6 * length;
                    path.reset();
                    if (f7 > f8) {
                        this.f75g.getSegment(f7, length, path, true);
                        this.f75g.getSegment(0.0f, f8, path, true);
                    } else {
                        this.f75g.getSegment(f7, f8, path, true);
                    }
                    path.rLineTo(0.0f, 0.0f);
                }
                this.f71c.addPath(path, this.f72d);
                if (bVar.f43g.mo157e()) {
                    C0027b bVar2 = bVar.f43g;
                    if (this.f74f == null) {
                        this.f74f = new Paint(1);
                        this.f74f.setStyle(Paint.Style.FILL);
                    }
                    Paint paint = this.f74f;
                    if (bVar2.mo155c()) {
                        Shader b = bVar2.mo153b();
                        b.setLocalMatrix(this.f72d);
                        paint.setShader(b);
                        paint.setAlpha(Math.round(bVar.f46j * 255.0f));
                    } else {
                        paint.setColor(C0016k.m31a(bVar2.mo151a(), bVar.f46j));
                    }
                    paint.setColorFilter(colorFilter);
                    this.f71c.setFillType(bVar.f45i == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                    canvas.drawPath(this.f71c, paint);
                }
                if (bVar.f41e.mo157e()) {
                    C0027b bVar3 = bVar.f41e;
                    if (this.f73e == null) {
                        this.f73e = new Paint(1);
                        this.f73e.setStyle(Paint.Style.STROKE);
                    }
                    Paint paint2 = this.f73e;
                    Paint.Join join = bVar.f51o;
                    if (join != null) {
                        paint2.setStrokeJoin(join);
                    }
                    Paint.Cap cap = bVar.f50n;
                    if (cap != null) {
                        paint2.setStrokeCap(cap);
                    }
                    paint2.setStrokeMiter(bVar.f52p);
                    if (bVar3.mo155c()) {
                        Shader b2 = bVar3.mo153b();
                        b2.setLocalMatrix(this.f72d);
                        paint2.setShader(b2);
                        paint2.setAlpha(Math.round(bVar.f44h * 255.0f));
                    } else {
                        paint2.setColor(C0016k.m31a(bVar3.mo151a(), bVar.f44h));
                    }
                    paint2.setColorFilter(colorFilter);
                    paint2.setStrokeWidth(bVar.f42f * min * a);
                    canvas.drawPath(this.f71c, paint2);
                }
            }
        }

        /* renamed from: a */
        private void m61a(C0019c cVar, Matrix matrix, Canvas canvas, int i, int i2, ColorFilter colorFilter) {
            cVar.f53a.set(matrix);
            cVar.f53a.preConcat(cVar.f62j);
            canvas.save();
            for (int i3 = 0; i3 < cVar.f54b.size(); i3++) {
                C0020d dVar = cVar.f54b.get(i3);
                if (dVar instanceof C0019c) {
                    m61a((C0019c) dVar, cVar.f53a, canvas, i, i2, colorFilter);
                } else if (dVar instanceof C0021e) {
                    m60a(cVar, (C0021e) dVar, canvas, i, i2, colorFilter);
                }
            }
            canvas.restore();
        }

        /* renamed from: a */
        public void mo126a(Canvas canvas, int i, int i2, ColorFilter colorFilter) {
            m61a(this.f77i, f69a, canvas, i, i2, colorFilter);
        }

        /* renamed from: a */
        public boolean mo127a() {
            if (this.f84p == null) {
                this.f84p = Boolean.valueOf(this.f77i.mo87a());
            }
            return this.f84p.booleanValue();
        }

        /* renamed from: a */
        public boolean mo128a(int[] iArr) {
            return this.f77i.mo88a(iArr);
        }

        public float getAlpha() {
            return ((float) getRootAlpha()) / 255.0f;
        }

        public int getRootAlpha() {
            return this.f82n;
        }

        public void setAlpha(float f) {
            setRootAlpha((int) (f * 255.0f));
        }

        public void setRootAlpha(int i) {
            this.f82n = i;
        }
    }

    /* renamed from: a.a.b.a.k$g */
    private static class C0023g extends Drawable.ConstantState {

        /* renamed from: a */
        int f86a;

        /* renamed from: b */
        C0022f f87b;

        /* renamed from: c */
        ColorStateList f88c;

        /* renamed from: d */
        PorterDuff.Mode f89d;

        /* renamed from: e */
        boolean f90e;

        /* renamed from: f */
        Bitmap f91f;

        /* renamed from: g */
        ColorStateList f92g;

        /* renamed from: h */
        PorterDuff.Mode f93h;

        /* renamed from: i */
        int f94i;

        /* renamed from: j */
        boolean f95j;

        /* renamed from: k */
        boolean f96k;

        /* renamed from: l */
        Paint f97l;

        public C0023g() {
            this.f88c = null;
            this.f89d = C0016k.f30b;
            this.f87b = new C0022f();
        }

        public C0023g(C0023g gVar) {
            this.f88c = null;
            this.f89d = C0016k.f30b;
            if (gVar != null) {
                this.f86a = gVar.f86a;
                this.f87b = new C0022f(gVar.f87b);
                Paint paint = gVar.f87b.f74f;
                if (paint != null) {
                    this.f87b.f74f = new Paint(paint);
                }
                Paint paint2 = gVar.f87b.f73e;
                if (paint2 != null) {
                    this.f87b.f73e = new Paint(paint2);
                }
                this.f88c = gVar.f88c;
                this.f89d = gVar.f89d;
                this.f90e = gVar.f90e;
            }
        }

        /* renamed from: a */
        public Paint mo133a(ColorFilter colorFilter) {
            if (!mo139b() && colorFilter == null) {
                return null;
            }
            if (this.f97l == null) {
                this.f97l = new Paint();
                this.f97l.setFilterBitmap(true);
            }
            this.f97l.setAlpha(this.f87b.getRootAlpha());
            this.f97l.setColorFilter(colorFilter);
            return this.f97l;
        }

        /* renamed from: a */
        public void mo134a(Canvas canvas, ColorFilter colorFilter, Rect rect) {
            canvas.drawBitmap(this.f91f, (Rect) null, rect, mo133a(colorFilter));
        }

        /* renamed from: a */
        public boolean mo135a() {
            return !this.f96k && this.f92g == this.f88c && this.f93h == this.f89d && this.f95j == this.f90e && this.f94i == this.f87b.getRootAlpha();
        }

        /* renamed from: a */
        public boolean mo136a(int i, int i2) {
            return i == this.f91f.getWidth() && i2 == this.f91f.getHeight();
        }

        /* renamed from: a */
        public boolean mo137a(int[] iArr) {
            boolean a = this.f87b.mo128a(iArr);
            this.f96k |= a;
            return a;
        }

        /* renamed from: b */
        public void mo138b(int i, int i2) {
            if (this.f91f == null || !mo136a(i, i2)) {
                this.f91f = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
                this.f96k = true;
            }
        }

        /* renamed from: b */
        public boolean mo139b() {
            return this.f87b.getRootAlpha() < 255;
        }

        /* renamed from: c */
        public void mo140c(int i, int i2) {
            this.f91f.eraseColor(0);
            this.f87b.mo126a(new Canvas(this.f91f), i, i2, (ColorFilter) null);
        }

        /* renamed from: c */
        public boolean mo141c() {
            return this.f87b.mo127a();
        }

        /* renamed from: d */
        public void mo142d() {
            this.f92g = this.f88c;
            this.f93h = this.f89d;
            this.f94i = this.f87b.getRootAlpha();
            this.f95j = this.f90e;
            this.f96k = false;
        }

        public int getChangingConfigurations() {
            return this.f86a;
        }

        public Drawable newDrawable() {
            return new C0016k(this);
        }

        public Drawable newDrawable(Resources resources) {
            return new C0016k(this);
        }
    }

    /* renamed from: a.a.b.a.k$h */
    private static class C0024h extends Drawable.ConstantState {

        /* renamed from: a */
        private final Drawable.ConstantState f98a;

        public C0024h(Drawable.ConstantState constantState) {
            this.f98a = constantState;
        }

        public boolean canApplyTheme() {
            return this.f98a.canApplyTheme();
        }

        public int getChangingConfigurations() {
            return this.f98a.getChangingConfigurations();
        }

        public Drawable newDrawable() {
            C0016k kVar = new C0016k();
            kVar.f29a = (VectorDrawable) this.f98a.newDrawable();
            return kVar;
        }

        public Drawable newDrawable(Resources resources) {
            C0016k kVar = new C0016k();
            kVar.f29a = (VectorDrawable) this.f98a.newDrawable(resources);
            return kVar;
        }

        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            C0016k kVar = new C0016k();
            kVar.f29a = (VectorDrawable) this.f98a.newDrawable(resources, theme);
            return kVar;
        }
    }

    C0016k() {
        this.f35g = true;
        this.f37i = new float[9];
        this.f38j = new Matrix();
        this.f39k = new Rect();
        this.f31c = new C0023g();
    }

    C0016k(C0023g gVar) {
        this.f35g = true;
        this.f37i = new float[9];
        this.f38j = new Matrix();
        this.f39k = new Rect();
        this.f31c = gVar;
        this.f32d = mo59a(this.f32d, gVar.f88c, gVar.f89d);
    }

    /* renamed from: a */
    static int m31a(int i, float f) {
        return (i & 16777215) | (((int) (((float) Color.alpha(i)) * f)) << 24);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0038 A[Catch:{ IOException | XmlPullParserException -> 0x0045 }] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003d A[Catch:{ IOException | XmlPullParserException -> 0x0045 }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static p000a.p001a.p003b.p004a.C0016k m32a(android.content.res.Resources r6, int r7, android.content.res.Resources.Theme r8) {
        /*
            java.lang.String r0 = "parser error"
            java.lang.String r1 = "VectorDrawableCompat"
            int r2 = android.os.Build.VERSION.SDK_INT
            r3 = 24
            if (r2 < r3) goto L_0x0023
            a.a.b.a.k r0 = new a.a.b.a.k
            r0.<init>()
            android.graphics.drawable.Drawable r6 = p000a.p001a.p005c.p006a.p007a.C0038h.m123a(r6, r7, r8)
            r0.f29a = r6
            a.a.b.a.k$h r6 = new a.a.b.a.k$h
            android.graphics.drawable.Drawable r7 = r0.f29a
            android.graphics.drawable.Drawable$ConstantState r7 = r7.getConstantState()
            r6.<init>(r7)
            r0.f36h = r6
            return r0
        L_0x0023:
            android.content.res.XmlResourceParser r7 = r6.getXml(r7)     // Catch:{ XmlPullParserException -> 0x0047, IOException -> 0x0045 }
            android.util.AttributeSet r2 = android.util.Xml.asAttributeSet(r7)     // Catch:{ XmlPullParserException -> 0x0047, IOException -> 0x0045 }
        L_0x002b:
            int r3 = r7.next()     // Catch:{ XmlPullParserException -> 0x0047, IOException -> 0x0045 }
            r4 = 2
            if (r3 == r4) goto L_0x0036
            r5 = 1
            if (r3 == r5) goto L_0x0036
            goto L_0x002b
        L_0x0036:
            if (r3 != r4) goto L_0x003d
            a.a.b.a.k r6 = createFromXmlInner(r6, r7, r2, r8)     // Catch:{ XmlPullParserException -> 0x0047, IOException -> 0x0045 }
            return r6
        L_0x003d:
            org.xmlpull.v1.XmlPullParserException r6 = new org.xmlpull.v1.XmlPullParserException     // Catch:{ XmlPullParserException -> 0x0047, IOException -> 0x0045 }
            java.lang.String r7 = "No start tag found"
            r6.<init>(r7)     // Catch:{ XmlPullParserException -> 0x0047, IOException -> 0x0045 }
            throw r6     // Catch:{ XmlPullParserException -> 0x0047, IOException -> 0x0045 }
        L_0x0045:
            r6 = move-exception
            goto L_0x0048
        L_0x0047:
            r6 = move-exception
        L_0x0048:
            android.util.Log.e(r1, r0, r6)
            r6 = 0
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: p000a.p001a.p003b.p004a.C0016k.m32a(android.content.res.Resources, int, android.content.res.Resources$Theme):a.a.b.a.k");
    }

    /* renamed from: a */
    private static PorterDuff.Mode m33a(int i, PorterDuff.Mode mode) {
        if (i == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i) {
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: a.a.b.a.k$a} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v15, resolved type: a.a.b.a.k$b} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v16, resolved type: a.a.b.a.k$a} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v17, resolved type: a.a.b.a.k$a} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v18, resolved type: a.a.b.a.k$a} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m34a(android.content.res.Resources r11, org.xmlpull.v1.XmlPullParser r12, android.util.AttributeSet r13, android.content.res.Resources.Theme r14) {
        /*
            r10 = this;
            a.a.b.a.k$g r0 = r10.f31c
            a.a.b.a.k$f r1 = r0.f87b
            java.util.ArrayDeque r2 = new java.util.ArrayDeque
            r2.<init>()
            a.a.b.a.k$c r3 = r1.f77i
            r2.push(r3)
            int r3 = r12.getEventType()
            int r4 = r12.getDepth()
            r5 = 1
            int r4 = r4 + r5
            r6 = 1
        L_0x0019:
            if (r3 == r5) goto L_0x00c4
            int r7 = r12.getDepth()
            r8 = 3
            if (r7 >= r4) goto L_0x0024
            if (r3 == r8) goto L_0x00c4
        L_0x0024:
            r7 = 2
            java.lang.String r9 = "group"
            if (r3 != r7) goto L_0x00af
            java.lang.String r3 = r12.getName()
            java.lang.Object r7 = r2.peek()
            a.a.b.a.k$c r7 = (p000a.p001a.p003b.p004a.C0016k.C0019c) r7
            java.lang.String r8 = "path"
            boolean r8 = r8.equals(r3)
            if (r8 == 0) goto L_0x0059
            a.a.b.a.k$b r3 = new a.a.b.a.k$b
            r3.<init>()
            r3.mo86a(r11, r13, r14, r12)
            java.util.ArrayList<a.a.b.a.k$d> r6 = r7.f54b
            r6.add(r3)
            java.lang.String r6 = r3.getPathName()
            if (r6 == 0) goto L_0x0057
            a.a.c.f.b<java.lang.String, java.lang.Object> r6 = r1.f85q
            java.lang.String r7 = r3.getPathName()
            r6.put(r7, r3)
        L_0x0057:
            r6 = 0
            goto L_0x007d
        L_0x0059:
            java.lang.String r8 = "clip-path"
            boolean r8 = r8.equals(r3)
            if (r8 == 0) goto L_0x0085
            a.a.b.a.k$a r3 = new a.a.b.a.k$a
            r3.<init>()
            r3.mo84a(r11, r13, r14, r12)
            java.util.ArrayList<a.a.b.a.k$d> r7 = r7.f54b
            r7.add(r3)
            java.lang.String r7 = r3.getPathName()
            if (r7 == 0) goto L_0x007d
            a.a.c.f.b<java.lang.String, java.lang.Object> r7 = r1.f85q
            java.lang.String r8 = r3.getPathName()
            r7.put(r8, r3)
        L_0x007d:
            int r7 = r0.f86a
            int r3 = r3.f68c
        L_0x0081:
            r3 = r3 | r7
            r0.f86a = r3
            goto L_0x00be
        L_0x0085:
            boolean r3 = r9.equals(r3)
            if (r3 == 0) goto L_0x00be
            a.a.b.a.k$c r3 = new a.a.b.a.k$c
            r3.<init>()
            r3.mo105a(r11, r13, r14, r12)
            java.util.ArrayList<a.a.b.a.k$d> r7 = r7.f54b
            r7.add(r3)
            r2.push(r3)
            java.lang.String r7 = r3.getGroupName()
            if (r7 == 0) goto L_0x00aa
            a.a.c.f.b<java.lang.String, java.lang.Object> r7 = r1.f85q
            java.lang.String r8 = r3.getGroupName()
            r7.put(r8, r3)
        L_0x00aa:
            int r7 = r0.f86a
            int r3 = r3.f63k
            goto L_0x0081
        L_0x00af:
            if (r3 != r8) goto L_0x00be
            java.lang.String r3 = r12.getName()
            boolean r3 = r9.equals(r3)
            if (r3 == 0) goto L_0x00be
            r2.pop()
        L_0x00be:
            int r3 = r12.next()
            goto L_0x0019
        L_0x00c4:
            if (r6 != 0) goto L_0x00c7
            return
        L_0x00c7:
            org.xmlpull.v1.XmlPullParserException r11 = new org.xmlpull.v1.XmlPullParserException
            java.lang.String r12 = "no path defined"
            r11.<init>(r12)
            goto L_0x00d0
        L_0x00cf:
            throw r11
        L_0x00d0:
            goto L_0x00cf
        */
        throw new UnsupportedOperationException("Method not decompiled: p000a.p001a.p003b.p004a.C0016k.m34a(android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.res.Resources$Theme):void");
    }

    /* renamed from: a */
    private void m35a(TypedArray typedArray, XmlPullParser xmlPullParser) {
        C0023g gVar = this.f31c;
        C0022f fVar = gVar.f87b;
        gVar.f89d = m33a(C0040i.m135b(typedArray, xmlPullParser, "tintMode", 6, -1), PorterDuff.Mode.SRC_IN);
        ColorStateList colorStateList = typedArray.getColorStateList(1);
        if (colorStateList != null) {
            gVar.f88c = colorStateList;
        }
        gVar.f90e = C0040i.m133a(typedArray, xmlPullParser, "autoMirrored", 5, gVar.f90e);
        fVar.f80l = C0040i.m128a(typedArray, xmlPullParser, "viewportWidth", 7, fVar.f80l);
        fVar.f81m = C0040i.m128a(typedArray, xmlPullParser, "viewportHeight", 8, fVar.f81m);
        if (fVar.f80l <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportWidth > 0");
        } else if (fVar.f81m > 0.0f) {
            fVar.f78j = typedArray.getDimension(3, fVar.f78j);
            fVar.f79k = typedArray.getDimension(2, fVar.f79k);
            if (fVar.f78j <= 0.0f) {
                throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires width > 0");
            } else if (fVar.f79k > 0.0f) {
                fVar.setAlpha(C0040i.m128a(typedArray, xmlPullParser, "alpha", 4, fVar.getAlpha()));
                String string = typedArray.getString(0);
                if (string != null) {
                    fVar.f83o = string;
                    fVar.f85q.put(string, fVar);
                }
            } else {
                throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires height > 0");
            }
        } else {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportHeight > 0");
        }
    }

    /* renamed from: a */
    private boolean m36a() {
        return Build.VERSION.SDK_INT >= 17 && isAutoMirrored() && C0190a.m684d(this) == 1;
    }

    public static C0016k createFromXmlInner(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        C0016k kVar = new C0016k();
        kVar.inflate(resources, xmlPullParser, attributeSet, theme);
        return kVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public PorterDuffColorFilter mo59a(PorterDuffColorFilter porterDuffColorFilter, ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public Object mo60a(String str) {
        return this.f31c.f87b.f85q.get(str);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo61a(boolean z) {
        this.f35g = z;
    }

    public /* bridge */ /* synthetic */ void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
    }

    public boolean canApplyTheme() {
        Drawable drawable = this.f29a;
        if (drawable == null) {
            return false;
        }
        C0190a.m679a(drawable);
        return false;
    }

    public /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    public void draw(Canvas canvas) {
        Drawable drawable = this.f29a;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        copyBounds(this.f39k);
        if (this.f39k.width() > 0 && this.f39k.height() > 0) {
            ColorFilter colorFilter = this.f33e;
            if (colorFilter == null) {
                colorFilter = this.f32d;
            }
            canvas.getMatrix(this.f38j);
            this.f38j.getValues(this.f37i);
            float abs = Math.abs(this.f37i[0]);
            float abs2 = Math.abs(this.f37i[4]);
            float abs3 = Math.abs(this.f37i[1]);
            float abs4 = Math.abs(this.f37i[3]);
            if (!(abs3 == 0.0f && abs4 == 0.0f)) {
                abs = 1.0f;
                abs2 = 1.0f;
            }
            int min = Math.min(2048, (int) (((float) this.f39k.width()) * abs));
            int min2 = Math.min(2048, (int) (((float) this.f39k.height()) * abs2));
            if (min > 0 && min2 > 0) {
                int save = canvas.save();
                Rect rect = this.f39k;
                canvas.translate((float) rect.left, (float) rect.top);
                if (m36a()) {
                    canvas.translate((float) this.f39k.width(), 0.0f);
                    canvas.scale(-1.0f, 1.0f);
                }
                this.f39k.offsetTo(0, 0);
                this.f31c.mo138b(min, min2);
                if (!this.f35g) {
                    this.f31c.mo140c(min, min2);
                } else if (!this.f31c.mo135a()) {
                    this.f31c.mo140c(min, min2);
                    this.f31c.mo142d();
                }
                this.f31c.mo134a(canvas, colorFilter, this.f39k);
                canvas.restoreToCount(save);
            }
        }
    }

    public int getAlpha() {
        Drawable drawable = this.f29a;
        return drawable != null ? C0190a.m681b(drawable) : this.f31c.f87b.getRootAlpha();
    }

    public int getChangingConfigurations() {
        Drawable drawable = this.f29a;
        return drawable != null ? drawable.getChangingConfigurations() : super.getChangingConfigurations() | this.f31c.getChangingConfigurations();
    }

    public /* bridge */ /* synthetic */ ColorFilter getColorFilter() {
        return super.getColorFilter();
    }

    public Drawable.ConstantState getConstantState() {
        Drawable drawable = this.f29a;
        if (drawable != null && Build.VERSION.SDK_INT >= 24) {
            return new C0024h(drawable.getConstantState());
        }
        this.f31c.f86a = getChangingConfigurations();
        return this.f31c;
    }

    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    public int getIntrinsicHeight() {
        Drawable drawable = this.f29a;
        return drawable != null ? drawable.getIntrinsicHeight() : (int) this.f31c.f87b.f79k;
    }

    public int getIntrinsicWidth() {
        Drawable drawable = this.f29a;
        return drawable != null ? drawable.getIntrinsicWidth() : (int) this.f31c.f87b.f78j;
    }

    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    public int getOpacity() {
        Drawable drawable = this.f29a;
        if (drawable != null) {
            return drawable.getOpacity();
        }
        return -3;
    }

    public /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    public /* bridge */ /* synthetic */ int[] getState() {
        return super.getState();
    }

    public /* bridge */ /* synthetic */ Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) {
        Drawable drawable = this.f29a;
        if (drawable != null) {
            drawable.inflate(resources, xmlPullParser, attributeSet);
        } else {
            inflate(resources, xmlPullParser, attributeSet, (Resources.Theme) null);
        }
    }

    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        Drawable drawable = this.f29a;
        if (drawable != null) {
            C0190a.m676a(drawable, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        C0023g gVar = this.f31c;
        gVar.f87b = new C0022f();
        TypedArray a = C0040i.m131a(resources, theme, attributeSet, C0003a.f0a);
        m35a(a, xmlPullParser);
        a.recycle();
        gVar.f86a = getChangingConfigurations();
        gVar.f96k = true;
        m34a(resources, xmlPullParser, attributeSet, theme);
        this.f32d = mo59a(this.f32d, gVar.f88c, gVar.f89d);
    }

    public void invalidateSelf() {
        Drawable drawable = this.f29a;
        if (drawable != null) {
            drawable.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    public boolean isAutoMirrored() {
        Drawable drawable = this.f29a;
        return drawable != null ? C0190a.m685e(drawable) : this.f31c.f90e;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0019, code lost:
        r0 = r1.f31c.f88c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000f, code lost:
        r0 = r1.f31c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isStateful() {
        /*
            r1 = this;
            android.graphics.drawable.Drawable r0 = r1.f29a
            if (r0 == 0) goto L_0x0009
            boolean r0 = r0.isStateful()
            return r0
        L_0x0009:
            boolean r0 = super.isStateful()
            if (r0 != 0) goto L_0x0028
            a.a.b.a.k$g r0 = r1.f31c
            if (r0 == 0) goto L_0x0026
            boolean r0 = r0.mo141c()
            if (r0 != 0) goto L_0x0028
            a.a.b.a.k$g r0 = r1.f31c
            android.content.res.ColorStateList r0 = r0.f88c
            if (r0 == 0) goto L_0x0026
            boolean r0 = r0.isStateful()
            if (r0 == 0) goto L_0x0026
            goto L_0x0028
        L_0x0026:
            r0 = 0
            goto L_0x0029
        L_0x0028:
            r0 = 1
        L_0x0029:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000a.p001a.p003b.p004a.C0016k.isStateful():boolean");
    }

    public /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    public Drawable mutate() {
        Drawable drawable = this.f29a;
        if (drawable != null) {
            drawable.mutate();
            return this;
        }
        if (!this.f34f && super.mutate() == this) {
            this.f31c = new C0023g(this.f31c);
            this.f34f = true;
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.f29a;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        PorterDuff.Mode mode;
        Drawable drawable = this.f29a;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        boolean z = false;
        C0023g gVar = this.f31c;
        ColorStateList colorStateList = gVar.f88c;
        if (!(colorStateList == null || (mode = gVar.f89d) == null)) {
            this.f32d = mo59a(this.f32d, colorStateList, mode);
            invalidateSelf();
            z = true;
        }
        if (!gVar.mo141c() || !gVar.mo137a(iArr)) {
            return z;
        }
        invalidateSelf();
        return true;
    }

    public void scheduleSelf(Runnable runnable, long j) {
        Drawable drawable = this.f29a;
        if (drawable != null) {
            drawable.scheduleSelf(runnable, j);
        } else {
            super.scheduleSelf(runnable, j);
        }
    }

    public void setAlpha(int i) {
        Drawable drawable = this.f29a;
        if (drawable != null) {
            drawable.setAlpha(i);
        } else if (this.f31c.f87b.getRootAlpha() != i) {
            this.f31c.f87b.setRootAlpha(i);
            invalidateSelf();
        }
    }

    public void setAutoMirrored(boolean z) {
        Drawable drawable = this.f29a;
        if (drawable != null) {
            C0190a.m678a(drawable, z);
        } else {
            this.f31c.f90e = z;
        }
    }

    public /* bridge */ /* synthetic */ void setChangingConfigurations(int i) {
        super.setChangingConfigurations(i);
    }

    public /* bridge */ /* synthetic */ void setColorFilter(int i, PorterDuff.Mode mode) {
        super.setColorFilter(i, mode);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f29a;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
            return;
        }
        this.f33e = colorFilter;
        invalidateSelf();
    }

    public /* bridge */ /* synthetic */ void setFilterBitmap(boolean z) {
        super.setFilterBitmap(z);
    }

    public /* bridge */ /* synthetic */ void setHotspot(float f, float f2) {
        super.setHotspot(f, f2);
    }

    public /* bridge */ /* synthetic */ void setHotspotBounds(int i, int i2, int i3, int i4) {
        super.setHotspotBounds(i, i2, i3, i4);
    }

    public /* bridge */ /* synthetic */ boolean setState(int[] iArr) {
        return super.setState(iArr);
    }

    public void setTint(int i) {
        Drawable drawable = this.f29a;
        if (drawable != null) {
            C0190a.m682b(drawable, i);
        } else {
            setTintList(ColorStateList.valueOf(i));
        }
    }

    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f29a;
        if (drawable != null) {
            C0190a.m674a(drawable, colorStateList);
            return;
        }
        C0023g gVar = this.f31c;
        if (gVar.f88c != colorStateList) {
            gVar.f88c = colorStateList;
            this.f32d = mo59a(this.f32d, colorStateList, gVar.f89d);
            invalidateSelf();
        }
    }

    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f29a;
        if (drawable != null) {
            C0190a.m677a(drawable, mode);
            return;
        }
        C0023g gVar = this.f31c;
        if (gVar.f89d != mode) {
            gVar.f89d = mode;
            this.f32d = mo59a(this.f32d, gVar.f88c, mode);
            invalidateSelf();
        }
    }

    public boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = this.f29a;
        return drawable != null ? drawable.setVisible(z, z2) : super.setVisible(z, z2);
    }

    public void unscheduleSelf(Runnable runnable) {
        Drawable drawable = this.f29a;
        if (drawable != null) {
            drawable.unscheduleSelf(runnable);
        } else {
            super.unscheduleSelf(runnable);
        }
    }
}
