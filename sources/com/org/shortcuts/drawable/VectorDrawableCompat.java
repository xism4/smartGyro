package com.org.shortcuts.drawable;

import a.a.b.a.k;
import a.a.c.f.b;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
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
import android.os.Build;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import com.org.android.asm.PathParser;
import com.org.android.ui.asm.ClassReader;
import com.org.android.ui.asm.Label;
import com.org.android.ui.asm.TypedArrayUtils;
import com.org.android.util.ArrayMap;
import com.org.shortcuts.drawable.AnimatedVectorDrawableCompat;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class VectorDrawableCompat extends VectorDrawableCommon {
    static final PorterDuff.Mode DEFAULT_TINT_MODE = PorterDuff.Mode.SRC_IN;
    private boolean mAllowCaching;
    private Drawable.ConstantState mCachedConstantStateDelegate;
    private ColorFilter mColorFilter;
    private boolean mMutated;
    private PorterDuffColorFilter mTintFilter;
    private final Rect mTmpBounds;
    private final float[] mTmpFloats;
    private final Matrix mTmpMatrix;
    private VectorDrawableCompatState mVectorState;

    class VClipPath extends VPath {
        public VClipPath() {
        }

        public VClipPath(VClipPath vClipPath) {
            super(vClipPath);
        }

        private void updateStateFromTypedArray(TypedArray typedArray) {
            String $r2 = typedArray.getString(0);
            if ($r2 != null) {
                this.mPathName = $r2;
            }
            String $r22 = typedArray.getString(1);
            if ($r22 != null) {
                this.mNodes = PathParser.createNodesFromPathData($r22);
            }
        }

        public void inflate(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            if (TypedArrayUtils.hasAttribute(xmlPullParser, "pathData")) {
                TypedArray $r6 = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.styleable_VectorDrawableClipPath);
                updateStateFromTypedArray($r6);
                $r6.recycle();
            }
        }

        public boolean isClipPath() {
            return true;
        }
    }

    class VFullPath extends VPath {
        Label a;
        float mFillAlpha = 1.0f;
        int mFillColor = 0;
        float mStrokeAlpha = 1.0f;
        Label mStrokeColor;
        Paint.Cap mStrokeLineCap = Paint.Cap.BUTT;
        Paint.Join mStrokeLineJoin = Paint.Join.MITER;
        float mStrokeMiterlimit = 4.0f;
        float mStrokeWidth = 0.0f;
        private int[] mThemeAttrs;
        float mTrimPathEnd = 1.0f;
        float mTrimPathOffset = 0.0f;
        float mTrimPathStart = 0.0f;

        public VFullPath() {
        }

        public VFullPath(VFullPath vFullPath) {
            super(vFullPath);
            this.mThemeAttrs = vFullPath.mThemeAttrs;
            this.mStrokeColor = vFullPath.mStrokeColor;
            this.mStrokeWidth = vFullPath.mStrokeWidth;
            this.mStrokeAlpha = vFullPath.mStrokeAlpha;
            this.a = vFullPath.a;
            this.mFillColor = vFullPath.mFillColor;
            this.mFillAlpha = vFullPath.mFillAlpha;
            this.mTrimPathStart = vFullPath.mTrimPathStart;
            this.mTrimPathEnd = vFullPath.mTrimPathEnd;
            this.mTrimPathOffset = vFullPath.mTrimPathOffset;
            this.mStrokeLineCap = vFullPath.mStrokeLineCap;
            this.mStrokeLineJoin = vFullPath.mStrokeLineJoin;
            this.mStrokeMiterlimit = vFullPath.mStrokeMiterlimit;
        }

        private Paint.Cap getStrokeLineCap(int i, Paint.Cap cap) {
            return i != 0 ? i != 1 ? i != 2 ? cap : Paint.Cap.SQUARE : Paint.Cap.ROUND : Paint.Cap.BUTT;
        }

        private Paint.Join getStrokeLineJoin(int i, Paint.Join join) {
            return i != 0 ? i != 1 ? i != 2 ? join : Paint.Join.BEVEL : Paint.Join.ROUND : Paint.Join.MITER;
        }

        private void updateStateFromTypedArray(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme) {
            this.mThemeAttrs = null;
            if (TypedArrayUtils.hasAttribute(xmlPullParser, "pathData")) {
                String $r4 = typedArray.getString(0);
                if ($r4 != null) {
                    this.mPathName = $r4;
                }
                String $r42 = typedArray.getString(2);
                if ($r42 != null) {
                    this.mNodes = PathParser.createNodesFromPathData($r42);
                }
                this.a = TypedArrayUtils.a(typedArray, xmlPullParser, theme, "fillColor", 1, 0);
                this.mFillAlpha = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "fillAlpha", 12, this.mFillAlpha);
                this.mStrokeLineCap = getStrokeLineCap(TypedArrayUtils.getString(typedArray, xmlPullParser, "strokeLineCap", 8, -1), this.mStrokeLineCap);
                this.mStrokeLineJoin = getStrokeLineJoin(TypedArrayUtils.getString(typedArray, xmlPullParser, "strokeLineJoin", 9, -1), this.mStrokeLineJoin);
                this.mStrokeMiterlimit = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "strokeMiterLimit", 10, this.mStrokeMiterlimit);
                this.mStrokeColor = TypedArrayUtils.a(typedArray, xmlPullParser, theme, "strokeColor", 3, 0);
                this.mStrokeAlpha = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "strokeAlpha", 11, this.mStrokeAlpha);
                this.mStrokeWidth = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "strokeWidth", 4, this.mStrokeWidth);
                this.mTrimPathEnd = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "trimPathEnd", 6, this.mTrimPathEnd);
                this.mTrimPathOffset = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "trimPathOffset", 7, this.mTrimPathOffset);
                this.mTrimPathStart = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "trimPathStart", 5, this.mTrimPathStart);
                this.mFillColor = TypedArrayUtils.getString(typedArray, xmlPullParser, "fillType", 13, this.mFillColor);
            }
        }

        public boolean a() {
            return this.a.b() || this.mStrokeColor.b();
        }

        public boolean a(int[] iArr) {
            return this.mStrokeColor.a(iArr) | this.a.a(iArr);
        }

        /* access modifiers changed from: package-private */
        public float getFillAlpha() {
            return this.mFillAlpha;
        }

        /* access modifiers changed from: package-private */
        public int getFillColor() {
            return this.a.getColor();
        }

        /* access modifiers changed from: package-private */
        public float getStrokeAlpha() {
            return this.mStrokeAlpha;
        }

        /* access modifiers changed from: package-private */
        public int getStrokeColor() {
            return this.mStrokeColor.getColor();
        }

        /* access modifiers changed from: package-private */
        public float getStrokeWidth() {
            return this.mStrokeWidth;
        }

        /* access modifiers changed from: package-private */
        public float getTrimPathEnd() {
            return this.mTrimPathEnd;
        }

        /* access modifiers changed from: package-private */
        public float getTrimPathOffset() {
            return this.mTrimPathOffset;
        }

        /* access modifiers changed from: package-private */
        public float getTrimPathStart() {
            return this.mTrimPathStart;
        }

        public void inflate(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray $r5 = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.ColorDrawable);
            updateStateFromTypedArray($r5, xmlPullParser, theme);
            $r5.recycle();
        }

        /* access modifiers changed from: package-private */
        public void setFillAlpha(float f) {
            this.mFillAlpha = f;
        }

        /* access modifiers changed from: package-private */
        public void setFillColor(int i) {
            this.a.setColor(i);
        }

        /* access modifiers changed from: package-private */
        public void setStrokeAlpha(float f) {
            this.mStrokeAlpha = f;
        }

        /* access modifiers changed from: package-private */
        public void setStrokeColor(int i) {
            this.mStrokeColor.setColor(i);
        }

        /* access modifiers changed from: package-private */
        public void setStrokeWidth(float f) {
            this.mStrokeWidth = f;
        }

        /* access modifiers changed from: package-private */
        public void setTrimPathEnd(float f) {
            this.mTrimPathEnd = f;
        }

        /* access modifiers changed from: package-private */
        public void setTrimPathOffset(float f) {
            this.mTrimPathOffset = f;
        }

        /* access modifiers changed from: package-private */
        public void setTrimPathStart(float f) {
            this.mTrimPathStart = f;
        }
    }

    class VGroup extends x {
        int mChangingConfigurations;
        final ArrayList<k.d> mChildren = new ArrayList();
        private String mGroupName = null;
        final Matrix mLocalMatrix = new Matrix();
        private float mPivotX = 0.0f;
        private float mPivotY = 0.0f;
        float mRotate = 0.0f;
        private float mScaleX = 1.0f;
        private float mScaleY = 1.0f;
        final Matrix mStackedMatrix = new Matrix();
        private int[] mThemeAttrs;
        private float mTranslateX = 0.0f;
        private float mTranslateY = 0.0f;

        public VGroup() {
            super();
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v0, resolved type: java.util.ArrayList<a.a.b.a.k$d>} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v0, resolved type: com.org.shortcuts.drawable.VectorDrawableCompat$VGroup} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v0, resolved type: com.org.shortcuts.drawable.VectorDrawableCompat$VClipPath} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v39, resolved type: com.org.android.util.SimpleArrayMap} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v1, resolved type: com.org.shortcuts.drawable.VectorDrawableCompat$VClipPath} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v0, resolved type: com.org.shortcuts.drawable.VectorDrawableCompat$VClipPath} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v2, resolved type: com.org.shortcuts.drawable.VectorDrawableCompat$VClipPath} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v0, resolved type: com.org.shortcuts.drawable.VectorDrawableCompat$VFullPath} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v42, resolved type: com.org.shortcuts.drawable.VectorDrawableCompat$VFullPath} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v0, resolved type: com.org.shortcuts.drawable.VectorDrawableCompat$VGroup} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v46, resolved type: com.org.android.util.SimpleArrayMap} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public VGroup(com.org.shortcuts.drawable.VectorDrawableCompat.VGroup r28, com.org.android.util.ArrayMap r29) {
            /*
                r27 = this;
                r2 = 0
                r0 = r27
                r0.<init>()
                android.graphics.Matrix r3 = new android.graphics.Matrix
                r3.<init>()
                r0 = r27
                r0.mStackedMatrix = r3
                java.util.ArrayList r4 = new java.util.ArrayList
                r4.<init>()
                r0 = r27
                r0.mChildren = r4
                r5 = 0
                r0 = r27
                r0.mRotate = r5
                r5 = 0
                r0 = r27
                r0.mPivotX = r5
                r5 = 0
                r0 = r27
                r0.mPivotY = r5
                r5 = 1065353216(0x3f800000, float:1.0)
                r0 = r27
                r0.mScaleX = r5
                r5 = 1065353216(0x3f800000, float:1.0)
                r0 = r27
                r0.mScaleY = r5
                r5 = 0
                r0 = r27
                r0.mTranslateX = r5
                r5 = 0
                r0 = r27
                r0.mTranslateY = r5
                android.graphics.Matrix r3 = new android.graphics.Matrix
                r3.<init>()
                r0 = r27
                r0.mLocalMatrix = r3
                r2 = 0
                r0 = r27
                r0.mGroupName = r2
                r0 = r28
                float r6 = r0.mRotate
                r0 = r27
                r0.mRotate = r6
                r0 = r28
                float r6 = r0.mPivotX
                r0 = r27
                r0.mPivotX = r6
                r0 = r28
                float r6 = r0.mPivotY
                r0 = r27
                r0.mPivotY = r6
                r0 = r28
                float r6 = r0.mScaleX
                r0 = r27
                r0.mScaleX = r6
                r0 = r28
                float r6 = r0.mScaleY
                r0 = r27
                r0.mScaleY = r6
                r0 = r28
                float r6 = r0.mTranslateX
                r0 = r27
                r0.mTranslateX = r6
                r0 = r28
                float r6 = r0.mTranslateY
                r0 = r27
                r0.mTranslateY = r6
                r0 = r28
                int[] r7 = r0.mThemeAttrs
                r0 = r27
                r0.mThemeAttrs = r7
                r0 = r28
                java.lang.String r8 = r0.mGroupName
                r0 = r27
                r0.mGroupName = r8
                r0 = r28
                int r9 = r0.mChangingConfigurations
                r0 = r27
                r0.mChangingConfigurations = r9
                r0 = r27
                java.lang.String r8 = r0.mGroupName
                if (r8 == 0) goto L_0x00aa
                r0 = r29
                r1 = r27
                r0.put(r8, r1)
            L_0x00aa:
                r0 = r27
                android.graphics.Matrix r10 = r0.mLocalMatrix
                r0 = r28
                android.graphics.Matrix r3 = r0.mLocalMatrix
                r10.set(r3)
                r0 = r28
                java.util.ArrayList<a.a.b.a.k$d> r11 = r0.mChildren
                r9 = 0
            L_0x00ba:
                int r12 = r11.size()
                if (r9 >= r12) goto L_0x0131
                java.lang.Object r13 = r11.get(r9)
                boolean r14 = r13 instanceof com.org.shortcuts.drawable.VectorDrawableCompat.VGroup
                if (r14 == 0) goto L_0x00e1
                r16 = r13
                com.org.shortcuts.drawable.VectorDrawableCompat$VGroup r16 = (com.org.shortcuts.drawable.VectorDrawableCompat.VGroup) r16
                r15 = r16
                r0 = r27
                java.util.ArrayList<a.a.b.a.k$d> r4 = r0.mChildren
                com.org.shortcuts.drawable.VectorDrawableCompat$VGroup r17 = new com.org.shortcuts.drawable.VectorDrawableCompat$VGroup
                r0 = r17
                r1 = r29
                r0.<init>(r15, r1)
                r0 = r17
                r4.add(r0)
                goto L_0x0122
            L_0x00e1:
                boolean r14 = r13 instanceof com.org.shortcuts.drawable.VectorDrawableCompat.VFullPath
                if (r14 == 0) goto L_0x00f7
                com.org.shortcuts.drawable.VectorDrawableCompat$VFullPath r18 = new com.org.shortcuts.drawable.VectorDrawableCompat$VFullPath
                r19 = r18
                r21 = r13
                com.org.shortcuts.drawable.VectorDrawableCompat$VFullPath r21 = (com.org.shortcuts.drawable.VectorDrawableCompat.VFullPath) r21
                r20 = r21
                r0 = r18
                r1 = r20
                r0.<init>(r1)
                goto L_0x010c
            L_0x00f7:
                boolean r14 = r13 instanceof com.org.shortcuts.drawable.VectorDrawableCompat.VClipPath
                if (r14 == 0) goto L_0x0125
                com.org.shortcuts.drawable.VectorDrawableCompat$VClipPath r22 = new com.org.shortcuts.drawable.VectorDrawableCompat$VClipPath
                r19 = r22
                r24 = r13
                com.org.shortcuts.drawable.VectorDrawableCompat$VClipPath r24 = (com.org.shortcuts.drawable.VectorDrawableCompat.VClipPath) r24
                r23 = r24
                r0 = r22
                r1 = r23
                r0.<init>(r1)
            L_0x010c:
                r0 = r27
                java.util.ArrayList<a.a.b.a.k$d> r4 = r0.mChildren
                r0 = r19
                r4.add(r0)
                r0 = r19
                java.lang.String r8 = r0.mPathName
                if (r8 == 0) goto L_0x0122
                r0 = r29
                r1 = r19
                r0.put(r8, r1)
            L_0x0122:
                int r9 = r9 + 1
                goto L_0x00ba
            L_0x0125:
                java.lang.IllegalStateException r25 = new java.lang.IllegalStateException
                java.lang.String r26 = "Unknown object in the tree!"
                r0 = r25
                r1 = r26
                r0.<init>(r1)
                throw r25
            L_0x0131:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.org.shortcuts.drawable.VectorDrawableCompat.VGroup.<init>(com.org.shortcuts.drawable.VectorDrawableCompat$VGroup, com.org.android.util.ArrayMap):void");
        }

        private void updateLocalMatrix() {
            this.mLocalMatrix.reset();
            this.mLocalMatrix.postTranslate(-this.mPivotX, -this.mPivotY);
            this.mLocalMatrix.postScale(this.mScaleX, this.mScaleY);
            this.mLocalMatrix.postRotate(this.mRotate, 0.0f, 0.0f);
            this.mLocalMatrix.postTranslate(this.mTranslateX + this.mPivotX, this.mTranslateY + this.mPivotY);
        }

        private void updateStateFromTypedArray(TypedArray typedArray, XmlPullParser xmlPullParser) {
            this.mThemeAttrs = null;
            this.mRotate = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "rotation", 5, this.mRotate);
            this.mPivotX = typedArray.getFloat(1, this.mPivotX);
            this.mPivotY = typedArray.getFloat(2, this.mPivotY);
            this.mScaleX = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "scaleX", 3, this.mScaleX);
            this.mScaleY = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "scaleY", 4, this.mScaleY);
            this.mTranslateX = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "translateX", 6, this.mTranslateX);
            this.mTranslateY = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "translateY", 7, this.mTranslateY);
            String $r3 = typedArray.getString(0);
            if ($r3 != null) {
                this.mGroupName = $r3;
            }
            updateLocalMatrix();
        }

        public boolean a() {
            for (int $i0 = 0; $i0 < this.mChildren.size(); $i0++) {
                if (this.mChildren.get($i0).a()) {
                    return true;
                }
            }
            return false;
        }

        public boolean a(int[] iArr) {
            boolean $z0 = false;
            for (int $i0 = 0; $i0 < this.mChildren.size(); $i0++) {
                $z0 |= this.mChildren.get($i0).a(iArr);
            }
            return $z0;
        }

        public String getGroupName() {
            return this.mGroupName;
        }

        public Matrix getLocalMatrix() {
            return this.mLocalMatrix;
        }

        public float getPivotX() {
            return this.mPivotX;
        }

        public float getPivotY() {
            return this.mPivotY;
        }

        public float getRotation() {
            return this.mRotate;
        }

        public float getScaleX() {
            return this.mScaleX;
        }

        public float getScaleY() {
            return this.mScaleY;
        }

        public float getTranslateX() {
            return this.mTranslateX;
        }

        public float getTranslateY() {
            return this.mTranslateY;
        }

        public void inflate(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray $r5 = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.LayerDrawable);
            updateStateFromTypedArray($r5, xmlPullParser);
            $r5.recycle();
        }

        public void setPivotX(float f) {
            if (f != this.mPivotX) {
                this.mPivotX = f;
                updateLocalMatrix();
            }
        }

        public void setPivotY(float f) {
            if (f != this.mPivotY) {
                this.mPivotY = f;
                updateLocalMatrix();
            }
        }

        public void setRotation(float f) {
            if (f != this.mRotate) {
                this.mRotate = f;
                updateLocalMatrix();
            }
        }

        public void setScaleX(float f) {
            if (f != this.mScaleX) {
                this.mScaleX = f;
                updateLocalMatrix();
            }
        }

        public void setScaleY(float f) {
            if (f != this.mScaleY) {
                this.mScaleY = f;
                updateLocalMatrix();
            }
        }

        public void setTranslateX(float f) {
            if (f != this.mTranslateX) {
                this.mTranslateX = f;
                updateLocalMatrix();
            }
        }

        public void setTranslateY(float f) {
            if (f != this.mTranslateY) {
                this.mTranslateY = f;
                updateLocalMatrix();
            }
        }
    }

    abstract class VPath extends x {
        int mChangingConfigurations;
        protected PathParser.PathDataNode[] mNodes = null;
        String mPathName;

        public VPath() {
            super();
        }

        public VPath(VPath vPath) {
            super();
            this.mPathName = vPath.mPathName;
            this.mChangingConfigurations = vPath.mChangingConfigurations;
            this.mNodes = PathParser.deepCopyNodes(vPath.mNodes);
        }

        public PathParser.PathDataNode[] getPathData() {
            return this.mNodes;
        }

        public String getPathName() {
            return this.mPathName;
        }

        public boolean isClipPath() {
            return false;
        }

        public void setPathData(PathParser.PathDataNode[] $r1) {
            if (!PathParser.canMorph(this.mNodes, $r1)) {
                this.mNodes = PathParser.deepCopyNodes($r1);
            } else {
                PathParser.updateNodes(this.mNodes, $r1);
            }
        }

        public void toPath(Path path) {
            path.reset();
            PathParser.PathDataNode[] $r2 = this.mNodes;
            if ($r2 != null) {
                PathParser.PathDataNode.nodesToPath($r2, path);
            }
        }
    }

    class VPathRenderer {
        private static final Matrix IDENTITY_MATRIX = new Matrix();
        float mBaseHeight;
        float mBaseWidth;
        private int mChangingConfigurations;
        Paint mFillPaint;
        private final Matrix mFinalPathMatrix;
        Boolean mGenerated;
        private final Path mPath;
        private PathMeasure mPathMeasure;
        private final Path mRenderPath;
        int mRootAlpha;
        final VGroup mRootGroup;
        String mRootName;
        Paint mStrokePaint;
        final b<String, Object> mVGTargetsMap;
        float mViewportHeight;
        float mViewportWidth;

        public VPathRenderer() {
            this.mFinalPathMatrix = new Matrix();
            this.mBaseWidth = 0.0f;
            this.mBaseHeight = 0.0f;
            this.mViewportWidth = 0.0f;
            this.mViewportHeight = 0.0f;
            this.mRootAlpha = 255;
            this.mRootName = null;
            this.mGenerated = null;
            this.mVGTargetsMap = new ArrayMap();
            this.mRootGroup = new VGroup();
            this.mPath = new Path();
            this.mRenderPath = new Path();
        }

        public VPathRenderer(VPathRenderer vPathRenderer) {
            this.mFinalPathMatrix = new Matrix();
            this.mBaseWidth = 0.0f;
            this.mBaseHeight = 0.0f;
            this.mViewportWidth = 0.0f;
            this.mViewportHeight = 0.0f;
            this.mRootAlpha = 255;
            this.mRootName = null;
            this.mGenerated = null;
            this.mVGTargetsMap = new ArrayMap();
            this.mRootGroup = new VGroup(vPathRenderer.mRootGroup, this.mVGTargetsMap);
            this.mPath = new Path(vPathRenderer.mPath);
            this.mRenderPath = new Path(vPathRenderer.mRenderPath);
            this.mBaseWidth = vPathRenderer.mBaseWidth;
            this.mBaseHeight = vPathRenderer.mBaseHeight;
            this.mViewportWidth = vPathRenderer.mViewportWidth;
            this.mViewportHeight = vPathRenderer.mViewportHeight;
            this.mChangingConfigurations = vPathRenderer.mChangingConfigurations;
            this.mRootAlpha = vPathRenderer.mRootAlpha;
            this.mRootName = vPathRenderer.mRootName;
            String $r8 = vPathRenderer.mRootName;
            if ($r8 != null) {
                this.mVGTargetsMap.put($r8, this);
            }
            this.mGenerated = vPathRenderer.mGenerated;
        }

        private static float cross(float f, float f2, float f3, float f4) {
            return (f * f4) - (f2 * f3);
        }

        private void drawGroupTree(VGroup vGroup, Matrix matrix, Canvas canvas, int i, int i2, ColorFilter colorFilter) {
            vGroup.mStackedMatrix.set(matrix);
            vGroup.mStackedMatrix.preConcat(vGroup.mLocalMatrix);
            canvas.save();
            for (int $i2 = 0; $i2 < vGroup.mChildren.size(); $i2++) {
                x $r8 = vGroup.mChildren.get($i2);
                if ($r8 instanceof VGroup) {
                    drawGroupTree((VGroup) $r8, vGroup.mStackedMatrix, canvas, i, i2, colorFilter);
                } else if ($r8 instanceof VPath) {
                    drawPath(vGroup, (VPath) $r8, canvas, i, i2, colorFilter);
                }
            }
            canvas.restore();
        }

        private void drawPath(VGroup vGroup, VPath vPath, Canvas canvas, int i, int i2, ColorFilter colorFilter) {
            float $f1 = ((float) i) / this.mViewportWidth;
            float $f2 = ((float) i2) / this.mViewportHeight;
            float $f0 = Math.min($f1, $f2);
            Matrix $r5 = vGroup.mStackedMatrix;
            this.mFinalPathMatrix.set($r5);
            this.mFinalPathMatrix.postScale($f1, $f2);
            float $f12 = getMatrixScale($r5);
            if ($f12 != 0.0f) {
                vPath.toPath(this.mPath);
                Path $r7 = this.mPath;
                this.mRenderPath.reset();
                if (vPath.isClipPath()) {
                    this.mRenderPath.addPath($r7, this.mFinalPathMatrix);
                    canvas.clipPath(this.mRenderPath);
                    return;
                }
                VFullPath $r9 = (VFullPath) vPath;
                if (!($r9.mTrimPathStart == 0.0f && $r9.mTrimPathEnd == 1.0f)) {
                    float $f22 = $r9.mTrimPathStart;
                    float $f3 = $r9.mTrimPathOffset;
                    float $f23 = ($f22 + $f3) % 1.0f;
                    float $f4 = ($r9.mTrimPathEnd + $f3) % 1.0f;
                    if (this.mPathMeasure == null) {
                        this.mPathMeasure = new PathMeasure();
                    }
                    this.mPathMeasure.setPath(this.mPath, false);
                    PathMeasure $r10 = this.mPathMeasure;
                    PathMeasure pathMeasure = $r10;
                    float $f32 = $r10.getLength();
                    float $f24 = $f23 * $f32;
                    float $f42 = $f4 * $f32;
                    $r7.reset();
                    if ($f24 > $f42) {
                        this.mPathMeasure.getSegment($f24, $f32, $r7, true);
                        this.mPathMeasure.getSegment(0.0f, $f42, $r7, true);
                    } else {
                        this.mPathMeasure.getSegment($f24, $f42, $r7, true);
                    }
                    $r7.rLineTo(0.0f, 0.0f);
                }
                this.mRenderPath.addPath($r7, this.mFinalPathMatrix);
                Label $r11 = $r9.a;
                Label label = $r11;
                if ($r11.a()) {
                    Label $r112 = $r9.a;
                    if (this.mFillPaint == null) {
                        this.mFillPaint = new Paint(1);
                        this.mFillPaint.setStyle(Paint.Style.FILL);
                    }
                    Paint $r12 = this.mFillPaint;
                    if ($r112.setStyle()) {
                        Shader $r14 = $r112.e();
                        $r14.setLocalMatrix(this.mFinalPathMatrix);
                        $r12.setShader($r14);
                        $r12.setAlpha(Math.round($r9.mFillAlpha * 255.0f));
                    } else {
                        $r12.setColor(VectorDrawableCompat.access$getAlpha($r112.getColor(), $r9.mFillAlpha));
                    }
                    $r12.setColorFilter(colorFilter);
                    this.mRenderPath.setFillType($r9.mFillColor == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                    canvas.drawPath(this.mRenderPath, $r12);
                }
                Label $r113 = $r9.mStrokeColor;
                if ($r113.a()) {
                    Label $r114 = $r9.mStrokeColor;
                    if (this.mStrokePaint == null) {
                        this.mStrokePaint = new Paint(1);
                        this.mStrokePaint.setStyle(Paint.Style.STROKE);
                    }
                    Paint $r122 = this.mStrokePaint;
                    Paint.Join $r16 = $r9.mStrokeLineJoin;
                    if ($r16 != null) {
                        $r122.setStrokeJoin($r16);
                    }
                    Paint.Cap $r17 = $r9.mStrokeLineCap;
                    if ($r17 != null) {
                        $r122.setStrokeCap($r17);
                    }
                    $r122.setStrokeMiter($r9.mStrokeMiterlimit);
                    if ($r114.setStyle()) {
                        Shader $r142 = $r114.e();
                        $r142.setLocalMatrix(this.mFinalPathMatrix);
                        $r122.setShader($r142);
                        $r122.setAlpha(Math.round($r9.mStrokeAlpha * 255.0f));
                    } else {
                        $r122.setColor(VectorDrawableCompat.access$getAlpha($r114.getColor(), $r9.mStrokeAlpha));
                    }
                    $r122.setColorFilter(colorFilter);
                    $r122.setStrokeWidth($r9.mStrokeWidth * $f0 * $f12);
                    canvas.drawPath(this.mRenderPath, $r122);
                }
            }
        }

        private float getMatrixScale(Matrix matrix) {
            float[] $r2 = {0.0f, 1.0f, 1.0f, 0.0f};
            matrix.mapVectors($r2);
            float $f2 = cross($r2[0], $r2[1], $r2[2], $r2[3]);
            float $f0 = Math.max((float) Math.hypot((double) $r2[0], (double) $r2[1]), (float) Math.hypot((double) $r2[2], (double) $r2[3]));
            if ($f0 > 0.0f) {
                return Math.abs($f2) / $f0;
            }
            return 0.0f;
        }

        public void draw(Canvas canvas, int i, int i2, ColorFilter colorFilter) {
            drawGroupTree(this.mRootGroup, IDENTITY_MATRIX, canvas, i, i2, colorFilter);
        }

        public boolean draw() {
            if (this.mGenerated == null) {
                this.mGenerated = Boolean.valueOf(this.mRootGroup.a());
            }
            return this.mGenerated.booleanValue();
        }

        public boolean draw(int[] iArr) {
            return this.mRootGroup.a(iArr);
        }

        public float getAlpha() {
            return ((float) getRootAlpha()) / 255.0f;
        }

        public int getRootAlpha() {
            return this.mRootAlpha;
        }

        public void setAlpha(float f) {
            setRootAlpha((int) (f * 255.0f));
        }

        public void setRootAlpha(int i) {
            this.mRootAlpha = i;
        }
    }

    class VectorDrawableCompatState extends Drawable.ConstantState {
        boolean mAutoMirrored;
        boolean mCacheDirty;
        boolean mCachedAutoMirrored;
        Bitmap mCachedBitmap;
        int mCachedRootAlpha;
        ColorStateList mCachedTint;
        PorterDuff.Mode mCachedTintMode;
        int mChangingConfigurations;
        Paint mTempPaint;
        ColorStateList mTint;
        PorterDuff.Mode mTintMode;
        VPathRenderer mVPathRenderer;

        public VectorDrawableCompatState() {
            this.mTint = null;
            this.mTintMode = VectorDrawableCompat.DEFAULT_TINT_MODE;
            this.mVPathRenderer = new VPathRenderer();
        }

        public VectorDrawableCompatState(VectorDrawableCompatState vectorDrawableCompatState) {
            this.mTint = null;
            this.mTintMode = VectorDrawableCompat.DEFAULT_TINT_MODE;
            if (vectorDrawableCompatState != null) {
                this.mChangingConfigurations = vectorDrawableCompatState.mChangingConfigurations;
                this.mVPathRenderer = new VPathRenderer(vectorDrawableCompatState.mVPathRenderer);
                Paint $r5 = vectorDrawableCompatState.mVPathRenderer.mFillPaint;
                if ($r5 != null) {
                    this.mVPathRenderer.mFillPaint = new Paint($r5);
                }
                Paint $r52 = vectorDrawableCompatState.mVPathRenderer.mStrokePaint;
                if ($r52 != null) {
                    this.mVPathRenderer.mStrokePaint = new Paint($r52);
                }
                this.mTint = vectorDrawableCompatState.mTint;
                this.mTintMode = vectorDrawableCompatState.mTintMode;
                this.mAutoMirrored = vectorDrawableCompatState.mAutoMirrored;
            }
        }

        public boolean canReuseBitmap(int i, int i2) {
            return i == this.mCachedBitmap.getWidth() && i2 == this.mCachedBitmap.getHeight();
        }

        public boolean canReuseCache() {
            return !this.mCacheDirty && this.mCachedTint == this.mTint && this.mCachedTintMode == this.mTintMode && this.mCachedAutoMirrored == this.mAutoMirrored && this.mCachedRootAlpha == this.mVPathRenderer.getRootAlpha();
        }

        public void createCachedBitmapIfNeeded(int i, int i2) {
            if (this.mCachedBitmap == null || !canReuseBitmap(i, i2)) {
                this.mCachedBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
                this.mCacheDirty = true;
            }
        }

        public boolean draw(int[] iArr) {
            boolean $z0 = this.mVPathRenderer.draw(iArr);
            this.mCacheDirty |= $z0;
            return $z0;
        }

        public void drawCachedBitmapWithRootAlpha(Canvas canvas, ColorFilter colorFilter, Rect rect) {
            canvas.drawBitmap(this.mCachedBitmap, (Rect) null, rect, getPaint(colorFilter));
        }

        public int getChangingConfigurations() {
            return this.mChangingConfigurations;
        }

        public Paint getPaint(ColorFilter colorFilter) {
            if (!hasTranslucentRoot() && colorFilter == null) {
                return null;
            }
            if (this.mTempPaint == null) {
                this.mTempPaint = new Paint();
                this.mTempPaint.setFilterBitmap(true);
            }
            this.mTempPaint.setAlpha(this.mVPathRenderer.getRootAlpha());
            this.mTempPaint.setColorFilter(colorFilter);
            return this.mTempPaint;
        }

        public boolean hasTranslucentRoot() {
            return this.mVPathRenderer.getRootAlpha() < 255;
        }

        public Drawable newDrawable() {
            return new VectorDrawableCompat(this);
        }

        public Drawable newDrawable(Resources resources) {
            return new VectorDrawableCompat(this);
        }

        public void updateCacheStates() {
            this.mCachedTint = this.mTint;
            this.mCachedTintMode = this.mTintMode;
            this.mCachedRootAlpha = this.mVPathRenderer.getRootAlpha();
            this.mCachedAutoMirrored = this.mAutoMirrored;
            this.mCacheDirty = false;
        }

        public void updateCachedBitmap(int i, int i2) {
            this.mCachedBitmap.eraseColor(0);
            this.mVPathRenderer.draw(new Canvas(this.mCachedBitmap), i, i2, (ColorFilter) null);
        }

        public boolean updateCachedBitmap() {
            return this.mVPathRenderer.draw();
        }
    }

    class VectorDrawableDelegateState extends Drawable.ConstantState {
        private final Drawable.ConstantState mDelegateState;

        public VectorDrawableDelegateState(Drawable.ConstantState constantState) {
            this.mDelegateState = constantState;
        }

        public boolean canApplyTheme() {
            return this.mDelegateState.canApplyTheme();
        }

        public int getChangingConfigurations() {
            return this.mDelegateState.getChangingConfigurations();
        }

        public Drawable newDrawable() {
            AnimatedVectorDrawableCompat $r1 = new AnimatedVectorDrawableCompat();
            $r1.mDelegateDrawable = this.mDelegateState.newDrawable();
            $r1.mDelegateDrawable.setCallback($r1.mCallback);
            return $r1;
        }

        public Drawable newDrawable(Resources resources) {
            AnimatedVectorDrawableCompat $r1 = new AnimatedVectorDrawableCompat();
            $r1.mDelegateDrawable = this.mDelegateState.newDrawable(resources);
            $r1.mDelegateDrawable.setCallback($r1.mCallback);
            return $r1;
        }

        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            AnimatedVectorDrawableCompat $r1 = new AnimatedVectorDrawableCompat();
            $r1.mDelegateDrawable = this.mDelegateState.newDrawable(resources, theme);
            $r1.mDelegateDrawable.setCallback($r1.mCallback);
            return $r1;
        }
    }

    VectorDrawableCompat() {
        this.mAllowCaching = true;
        this.mTmpFloats = new float[9];
        this.mTmpMatrix = new Matrix();
        this.mTmpBounds = new Rect();
        this.mVectorState = new VectorDrawableCompatState();
    }

    VectorDrawableCompat(VectorDrawableCompatState vectorDrawableCompatState) {
        this.mAllowCaching = true;
        this.mTmpFloats = new float[9];
        this.mTmpMatrix = new Matrix();
        this.mTmpBounds = new Rect();
        this.mVectorState = vectorDrawableCompatState;
        this.mTintFilter = updateTintFilter(this.mTintFilter, vectorDrawableCompatState.mTint, vectorDrawableCompatState.mTintMode);
    }

    static int access$getAlpha(int i, float f) {
        return (i & 16777215) | (((int) (((float) Color.alpha(i)) * f)) << 24);
    }

    public static VectorDrawableCompat create(Resources resources, int i, Resources.Theme theme) {
        int $i0;
        if (Build.VERSION.SDK_INT >= 24) {
            VectorDrawableCompat $r2 = new VectorDrawableCompat();
            $r2.mDelegateDrawable = ClassReader.getDrawable(resources, i, theme);
            $r2.mCachedConstantStateDelegate = new AnimatedVectorDrawableCompat.AnimatedVectorDrawableDelegateState($r2.mDelegateDrawable.getConstantState());
            return $r2;
        }
        try {
            XmlResourceParser $r6 = resources.getXml(i);
            AttributeSet $r7 = Xml.asAttributeSet($r6);
            do {
                $i0 = $r6.next();
                if ($i0 == 2 || $i0 == 1) {
                }
                $i0 = $r6.next();
                break;
            } while ($i0 == 1);
            if ($i0 == 2) {
                return createFromXmlInner(resources, $r6, $r7, theme);
            }
            try {
                throw new XmlPullParserException("No start tag found");
            } catch (XmlPullParserException e) {
                $r9 = e;
            }
        } catch (IOException e2) {
            $r9 = e2;
            Log.e("VectorDrawableCompat", "parser error", $r9);
            return null;
        }
    }

    public static VectorDrawableCompat createFromXmlInner(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        VectorDrawableCompat $r4 = new VectorDrawableCompat();
        $r4.inflate(resources, xmlPullParser, attributeSet, theme);
        return $r4;
    }

    private boolean draw() {
        return Build.VERSION.SDK_INT >= 17 && isAutoMirrored() && DrawableCompat.getLayoutDirection(this) == 1;
    }

    private void inflateInternal(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        int $i2;
        int $i1;
        VFullPath r19;
        VectorDrawableCompatState $r3 = this.mVectorState;
        VPathRenderer $r4 = $r3.mVPathRenderer;
        ArrayDeque $r5 = r18;
        ArrayDeque r18 = new ArrayDeque();
        $r5.push($r4.mRootGroup);
        int $i12 = xmlPullParser.getEventType();
        int $i0 = xmlPullParser.getDepth() + 1;
        boolean $z0 = true;
        while ($i12 != 1 && (xmlPullParser.getDepth() >= $i0 || $i12 != 3)) {
            if ($i12 == 2) {
                String $r10 = xmlPullParser.getName();
                VGroup $r9 = (VGroup) $r5.peek();
                if ("path".equals($r10)) {
                    r19 = r0;
                    VFullPath vFullPath = new VFullPath();
                    r19.inflate(resources, attributeSet, theme, xmlPullParser);
                    ArrayList<k.d> arrayList = $r9.mChildren;
                    ArrayList<k.d> arrayList2 = arrayList;
                    arrayList.add(r19);
                    if (r19.getPathName() != null) {
                        $r4.mVGTargetsMap.put(r19.getPathName(), r19);
                    }
                    $z0 = false;
                } else if ("clip-path".equals($r10)) {
                    r19 = r0;
                    VClipPath vClipPath = new VClipPath();
                    r19.inflate(resources, attributeSet, theme, xmlPullParser);
                    ArrayList arrayList3 = $r9.mChildren;
                    ArrayList $r14 = arrayList3;
                    arrayList3.add(r19);
                    if (r19.getPathName() != null) {
                        $r4.mVGTargetsMap.put(r19.getPathName(), r19);
                    }
                } else if ("group".equals($r10)) {
                    VGroup $r16 = r0;
                    VGroup vGroup = new VGroup();
                    $r16.inflate(resources, attributeSet, theme, xmlPullParser);
                    ArrayList arrayList4 = $r9.mChildren;
                    ArrayList $r142 = arrayList4;
                    arrayList4.add($r16);
                    $r5.push($r16);
                    if ($r16.getGroupName() != null) {
                        $r4.mVGTargetsMap.put($r16.getGroupName(), $r16);
                    }
                    $i1 = $r3.mChangingConfigurations;
                    $i2 = $r16.mChangingConfigurations;
                    $r3.mChangingConfigurations = $i2 | $i1;
                }
                $i1 = $r3.mChangingConfigurations;
                $i2 = r19.mChangingConfigurations;
                $r3.mChangingConfigurations = $i2 | $i1;
            } else if ($i12 == 3 && "group".equals(xmlPullParser.getName())) {
                $r5.pop();
            }
            $i12 = xmlPullParser.next();
        }
        if ($z0) {
            throw new XmlPullParserException("no path defined");
        }
    }

    private static PorterDuff.Mode parseTintModeCompat(int i, PorterDuff.Mode mode) {
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

    private void updateStateFromTypedArray(TypedArray typedArray, XmlPullParser xmlPullParser) {
        VectorDrawableCompatState $r4 = this.mVectorState;
        VPathRenderer $r1 = $r4.mVPathRenderer;
        $r4.mTintMode = parseTintModeCompat(TypedArrayUtils.getString(typedArray, xmlPullParser, "tintMode", 6, -1), PorterDuff.Mode.SRC_IN);
        ColorStateList $r6 = typedArray.getColorStateList(1);
        if ($r6 != null) {
            $r4.mTint = $r6;
        }
        $r4.mAutoMirrored = TypedArrayUtils.getNamedBoolean(typedArray, xmlPullParser, "autoMirrored", 5, $r4.mAutoMirrored);
        $r1.mViewportWidth = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "viewportWidth", 7, $r1.mViewportWidth);
        $r1.mViewportHeight = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "viewportHeight", 8, $r1.mViewportHeight);
        if ($r1.mViewportWidth <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportWidth > 0");
        } else if ($r1.mViewportHeight > 0.0f) {
            $r1.mBaseWidth = typedArray.getDimension(3, $r1.mBaseWidth);
            $r1.mBaseHeight = typedArray.getDimension(2, $r1.mBaseHeight);
            if ($r1.mBaseWidth <= 0.0f) {
                throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires width > 0");
            } else if ($r1.mBaseHeight > 0.0f) {
                $r1.setAlpha(TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "alpha", 4, $r1.getAlpha()));
                String $r7 = typedArray.getString(0);
                if ($r7 != null) {
                    $r1.mRootName = $r7;
                    $r1.mVGTargetsMap.put($r7, $r1);
                }
            } else {
                throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires height > 0");
            }
        } else {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportHeight > 0");
        }
    }

    public /* bridge */ /* synthetic */ void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
    }

    public boolean canApplyTheme() {
        Drawable $r1 = this.mDelegateDrawable;
        if ($r1 == null) {
            return false;
        }
        DrawableCompat.canApplyTheme($r1);
        return false;
    }

    public /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    public void draw(Canvas canvas) {
        Drawable $r2 = this.mDelegateDrawable;
        if ($r2 != null) {
            $r2.draw(canvas);
            return;
        }
        copyBounds(this.mTmpBounds);
        if (this.mTmpBounds.width() > 0 && this.mTmpBounds.height() > 0) {
            ColorFilter $r4 = this.mColorFilter;
            if ($r4 == null) {
                $r4 = this.mTintFilter;
            }
            canvas.getMatrix(this.mTmpMatrix);
            this.mTmpMatrix.getValues(this.mTmpFloats);
            float $f0 = Math.abs(this.mTmpFloats[0]);
            float $f1 = Math.abs(this.mTmpFloats[4]);
            float $f2 = Math.abs(this.mTmpFloats[1]);
            float $f3 = Math.abs(this.mTmpFloats[3]);
            if (!($f2 == 0.0f && $f3 == 0.0f)) {
                $f0 = 1.0f;
                $f1 = 1.0f;
            }
            int $i0 = Math.min(2048, (int) (((float) this.mTmpBounds.width()) * $f0));
            int $i2 = Math.min(2048, (int) (((float) this.mTmpBounds.height()) * $f1));
            if ($i0 > 0 && $i2 > 0) {
                int $i3 = canvas.save();
                Rect $r3 = this.mTmpBounds;
                int $i4 = $r3.left;
                int i = $i4;
                float $f02 = (float) $i4;
                int $i42 = $r3.top;
                canvas.translate($f02, (float) $i42);
                if (draw()) {
                    canvas.translate((float) this.mTmpBounds.width(), 0.0f);
                    canvas.scale(-1.0f, 1.0f);
                }
                this.mTmpBounds.offsetTo(0, 0);
                VectorDrawableCompatState $r7 = this.mVectorState;
                VectorDrawableCompatState vectorDrawableCompatState = $r7;
                $r7.createCachedBitmapIfNeeded($i0, $i2);
                if (!this.mAllowCaching) {
                    VectorDrawableCompatState $r72 = this.mVectorState;
                    VectorDrawableCompatState vectorDrawableCompatState2 = $r72;
                    $r72.updateCachedBitmap($i0, $i2);
                } else {
                    VectorDrawableCompatState $r73 = this.mVectorState;
                    VectorDrawableCompatState vectorDrawableCompatState3 = $r73;
                    if (!$r73.canReuseCache()) {
                        VectorDrawableCompatState $r74 = this.mVectorState;
                        VectorDrawableCompatState vectorDrawableCompatState4 = $r74;
                        $r74.updateCachedBitmap($i0, $i2);
                        VectorDrawableCompatState $r75 = this.mVectorState;
                        VectorDrawableCompatState vectorDrawableCompatState5 = $r75;
                        $r75.updateCacheStates();
                    }
                }
                this.mVectorState.drawCachedBitmapWithRootAlpha(canvas, $r4, this.mTmpBounds);
                canvas.restoreToCount($i3);
            }
        }
    }

    public int getAlpha() {
        Drawable $r1 = this.mDelegateDrawable;
        return $r1 != null ? DrawableCompat.getAlpha($r1) : this.mVectorState.mVPathRenderer.getRootAlpha();
    }

    public int getChangingConfigurations() {
        Drawable $r1 = this.mDelegateDrawable;
        return $r1 != null ? $r1.getChangingConfigurations() : super.getChangingConfigurations() | this.mVectorState.getChangingConfigurations();
    }

    public /* bridge */ /* synthetic */ ColorFilter getColorFilter() {
        return super.getColorFilter();
    }

    public Drawable.ConstantState getConstantState() {
        Drawable $r1 = this.mDelegateDrawable;
        if ($r1 != null && Build.VERSION.SDK_INT >= 24) {
            return new AnimatedVectorDrawableCompat.AnimatedVectorDrawableDelegateState($r1.getConstantState());
        }
        this.mVectorState.mChangingConfigurations = getChangingConfigurations();
        return this.mVectorState;
    }

    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    public int getIntrinsicHeight() {
        Drawable $r1 = this.mDelegateDrawable;
        return $r1 != null ? $r1.getIntrinsicHeight() : (int) this.mVectorState.mVPathRenderer.mBaseHeight;
    }

    public int getIntrinsicWidth() {
        Drawable $r1 = this.mDelegateDrawable;
        return $r1 != null ? $r1.getIntrinsicWidth() : (int) this.mVectorState.mVPathRenderer.mBaseWidth;
    }

    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    public int getOpacity() {
        Drawable $r1 = this.mDelegateDrawable;
        if ($r1 != null) {
            return $r1.getOpacity();
        }
        return -3;
    }

    public /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    public /* bridge */ /* synthetic */ int[] getState() {
        return super.getState();
    }

    /* access modifiers changed from: package-private */
    public Object getTargetByName(String str) {
        return this.mVectorState.mVPathRenderer.mVGTargetsMap.get(str);
    }

    public /* bridge */ /* synthetic */ Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) {
        Drawable $r4 = this.mDelegateDrawable;
        if ($r4 != null) {
            $r4.inflate(resources, xmlPullParser, attributeSet);
        } else {
            inflate(resources, xmlPullParser, attributeSet, (Resources.Theme) null);
        }
    }

    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        Drawable $r5 = this.mDelegateDrawable;
        if ($r5 != null) {
            DrawableCompat.inflate($r5, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        VectorDrawableCompatState $r6 = this.mVectorState;
        $r6.mVPathRenderer = new VPathRenderer();
        TypedArray $r9 = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.styleable_VectorDrawableTypeArray);
        updateStateFromTypedArray($r9, xmlPullParser);
        $r9.recycle();
        $r6.mChangingConfigurations = getChangingConfigurations();
        $r6.mCacheDirty = true;
        inflateInternal(resources, xmlPullParser, attributeSet, theme);
        this.mTintFilter = updateTintFilter(this.mTintFilter, $r6.mTint, $r6.mTintMode);
    }

    public void invalidateSelf() {
        Drawable $r1 = this.mDelegateDrawable;
        if ($r1 != null) {
            $r1.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    public boolean isAutoMirrored() {
        Drawable $r1 = this.mDelegateDrawable;
        return $r1 != null ? DrawableCompat.isAutoMirrored($r1) : this.mVectorState.mAutoMirrored;
    }

    public boolean isStateful() {
        Drawable $r1 = this.mDelegateDrawable;
        if ($r1 != null) {
            return $r1.isStateful();
        }
        if (super.isStateful()) {
            return true;
        }
        VectorDrawableCompatState $r2 = this.mVectorState;
        if ($r2 == null) {
            return false;
        }
        if ($r2.updateCachedBitmap()) {
            return true;
        }
        ColorStateList $r3 = this.mVectorState.mTint;
        return $r3 != null && $r3.isStateful();
    }

    public /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    public Drawable mutate() {
        Drawable $r2 = this.mDelegateDrawable;
        if ($r2 != null) {
            $r2.mutate();
            return this;
        }
        if (!this.mMutated && super.mutate() == this) {
            this.mVectorState = new VectorDrawableCompatState(this.mVectorState);
            this.mMutated = true;
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        Drawable $r2 = this.mDelegateDrawable;
        if ($r2 != null) {
            $r2.setBounds(rect);
        }
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        PorterDuff.Mode $r2;
        Drawable $r4 = this.mDelegateDrawable;
        if ($r4 != null) {
            return $r4.setState(iArr);
        }
        boolean $z0 = false;
        VectorDrawableCompatState $r1 = this.mVectorState;
        ColorStateList $r5 = $r1.mTint;
        if (!($r5 == null || ($r2 = $r1.mTintMode) == null)) {
            this.mTintFilter = updateTintFilter(this.mTintFilter, $r5, $r2);
            invalidateSelf();
            $z0 = true;
        }
        if (!$r1.updateCachedBitmap() || !$r1.draw(iArr)) {
            return $z0;
        }
        invalidateSelf();
        return true;
    }

    public void scheduleSelf(Runnable runnable, long j) {
        Drawable $r2 = this.mDelegateDrawable;
        if ($r2 != null) {
            $r2.scheduleSelf(runnable, j);
        } else {
            super.scheduleSelf(runnable, j);
        }
    }

    /* access modifiers changed from: package-private */
    public void setAllowCaching(boolean z) {
        this.mAllowCaching = z;
    }

    public void setAlpha(int i) {
        Drawable $r1 = this.mDelegateDrawable;
        if ($r1 != null) {
            $r1.setAlpha(i);
        } else if (this.mVectorState.mVPathRenderer.getRootAlpha() != i) {
            this.mVectorState.mVPathRenderer.setRootAlpha(i);
            invalidateSelf();
        }
    }

    public void setAutoMirrored(boolean z) {
        Drawable $r1 = this.mDelegateDrawable;
        if ($r1 != null) {
            DrawableCompat.setHotspotBounds($r1, z);
        } else {
            this.mVectorState.mAutoMirrored = z;
        }
    }

    public /* bridge */ /* synthetic */ void setChangingConfigurations(int i) {
        super.setChangingConfigurations(i);
    }

    public /* bridge */ /* synthetic */ void setColorFilter(int i, PorterDuff.Mode mode) {
        super.setColorFilter(i, mode);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        Drawable $r2 = this.mDelegateDrawable;
        if ($r2 != null) {
            $r2.setColorFilter(colorFilter);
            return;
        }
        this.mColorFilter = colorFilter;
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
        Drawable $r1 = this.mDelegateDrawable;
        if ($r1 != null) {
            DrawableCompat.setTint($r1, i);
        } else {
            setTintList(ColorStateList.valueOf(i));
        }
    }

    public void setTintList(ColorStateList colorStateList) {
        Drawable $r3 = this.mDelegateDrawable;
        if ($r3 != null) {
            DrawableCompat.setTintList($r3, colorStateList);
            return;
        }
        VectorDrawableCompatState $r4 = this.mVectorState;
        if ($r4.mTint != colorStateList) {
            $r4.mTint = colorStateList;
            this.mTintFilter = updateTintFilter(this.mTintFilter, colorStateList, $r4.mTintMode);
            invalidateSelf();
        }
    }

    public void setTintMode(PorterDuff.Mode mode) {
        Drawable $r3 = this.mDelegateDrawable;
        if ($r3 != null) {
            DrawableCompat.setTintMode($r3, mode);
            return;
        }
        VectorDrawableCompatState $r4 = this.mVectorState;
        if ($r4.mTintMode != mode) {
            $r4.mTintMode = mode;
            this.mTintFilter = updateTintFilter(this.mTintFilter, $r4.mTint, mode);
            invalidateSelf();
        }
    }

    public boolean setVisible(boolean $z1, boolean z) {
        Drawable $r1 = this.mDelegateDrawable;
        return $r1 != null ? $r1.setVisible($z1, z) : super.setVisible($z1, z);
    }

    public void unscheduleSelf(Runnable runnable) {
        Drawable $r2 = this.mDelegateDrawable;
        if ($r2 != null) {
            $r2.unscheduleSelf(runnable);
        } else {
            super.unscheduleSelf(runnable);
        }
    }

    /* access modifiers changed from: package-private */
    public PorterDuffColorFilter updateTintFilter(PorterDuffColorFilter porterDuffColorFilter, ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }
}
