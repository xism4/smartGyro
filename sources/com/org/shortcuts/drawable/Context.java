package com.org.shortcuts.drawable;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.util.Xml;
import android.view.InflateException;
import com.org.android.asm.PathParser;
import com.org.android.ui.asm.TypedArrayUtils;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class Context {
    private static void build(ValueAnimator valueAnimator, TypedArray typedArray, int i, float f, XmlPullParser xmlPullParser) {
        ObjectAnimator $r3 = (ObjectAnimator) valueAnimator;
        String $r4 = TypedArrayUtils.getString(typedArray, xmlPullParser, "pathData", 1);
        if ($r4 != null) {
            String $r5 = TypedArrayUtils.getString(typedArray, xmlPullParser, "propertyXName", 2);
            String $r6 = TypedArrayUtils.getString(typedArray, xmlPullParser, "propertyYName", 3);
            if (i != 2) {
            }
            if ($r5 == null && $r6 == null) {
                throw new InflateException(typedArray.getPositionDescription() + " propertyXName or propertyYName is needed for PathData");
            }
            parse(PathParser.createPathFromPathData($r4), $r3, f * 0.5f, $r5, $r6);
            return;
        }
        $r3.setPropertyName(TypedArrayUtils.getString(typedArray, xmlPullParser, "propertyName", 0));
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:65:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.animation.PropertyValuesHolder create(android.content.Context r30, android.content.res.Resources r31, android.content.res.Resources.Theme r32, org.xmlpull.v1.XmlPullParser r33, java.lang.String r34, int r35) {
        /*
            r6 = 0
        L_0x0001:
            r0 = r33
            int r7 = r0.next()
            r8 = 3
            if (r7 == r8) goto L_0x0057
            r8 = 1
            if (r7 == r8) goto L_0x0057
            r0 = r33
            java.lang.String r9 = r0.getName()
            java.lang.String r11 = "keyframe"
            boolean r10 = r9.equals(r11)
            if (r10 == 0) goto L_0x0001
            r8 = 4
            r0 = r35
            if (r0 != r8) goto L_0x0030
            r0 = r33
            android.util.AttributeSet r12 = android.util.Xml.asAttributeSet(r0)
            r0 = r31
            r1 = r32
            r2 = r33
            int r35 = inflate(r0, r1, r12, r2)
        L_0x0030:
            r0 = r33
            android.util.AttributeSet r12 = android.util.Xml.asAttributeSet(r0)
            r0 = r30
            r1 = r31
            r2 = r32
            r3 = r12
            r4 = r35
            r5 = r33
            android.animation.Keyframe r13 = inflate(r0, r1, r2, r3, r4, r5)
            if (r13 == 0) goto L_0x0051
            if (r6 != 0) goto L_0x004e
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
        L_0x004e:
            r6.add(r13)
        L_0x0051:
            r0 = r33
            r0.next()
            goto L_0x0001
        L_0x0057:
            if (r6 == 0) goto L_0x0157
            int r14 = r6.size()
            r15 = r14
            if (r14 <= 0) goto L_0x0157
            r7 = 0
            r8 = 0
            java.lang.Object r16 = r6.get(r8)
            r17 = r16
            android.animation.Keyframe r17 = (android.animation.Keyframe) r17
            r13 = r17
            int r18 = r14 + -1
            r0 = r18
            java.lang.Object r16 = r6.get(r0)
            r20 = r16
            android.animation.Keyframe r20 = (android.animation.Keyframe) r20
            r19 = r20
            r0 = r19
            float r21 = r0.getFraction()
            r23 = 1065353216(0x3f800000, float:1.0)
            int r22 = (r21 > r23 ? 1 : (r21 == r23 ? 0 : -1))
            if (r22 >= 0) goto L_0x00ae
            r23 = 0
            int r22 = (r21 > r23 ? 1 : (r21 == r23 ? 0 : -1))
            if (r22 >= 0) goto L_0x0098
            r23 = 1065353216(0x3f800000, float:1.0)
            r0 = r19
            r1 = r23
            r0.setFraction(r1)
            goto L_0x00ae
        L_0x0098:
            int r15 = r6.size()
            r23 = 1065353216(0x3f800000, float:1.0)
            r0 = r19
            r1 = r23
            android.animation.Keyframe r19 = toString(r0, r1)
            r0 = r19
            r6.add(r15, r0)
            int r15 = r14 + 1
        L_0x00ae:
            float r21 = r13.getFraction()
            r23 = 0
            int r22 = (r21 > r23 ? 1 : (r21 == r23 ? 0 : -1))
            if (r22 == 0) goto L_0x00d4
            r23 = 0
            int r22 = (r21 > r23 ? 1 : (r21 == r23 ? 0 : -1))
            if (r22 >= 0) goto L_0x00c6
            r23 = 0
            r0 = r23
            r13.setFraction(r0)
            goto L_0x00d4
        L_0x00c6:
            r23 = 0
            r0 = r23
            android.animation.Keyframe r13 = toString(r13, r0)
            r8 = 0
            r6.add(r8, r13)
            int r15 = r15 + 1
        L_0x00d4:
            android.animation.Keyframe[] r0 = new android.animation.Keyframe[r15]
            r24 = r0
            r6.toArray(r0)
        L_0x00db:
            if (r7 >= r15) goto L_0x013e
            r13 = r24[r7]
            float r21 = r13.getFraction()
            r23 = 0
            int r22 = (r21 > r23 ? 1 : (r21 == r23 ? 0 : -1))
            if (r22 >= 0) goto L_0x013b
            if (r7 != 0) goto L_0x00f3
            r23 = 0
            r0 = r23
            r13.setFraction(r0)
            goto L_0x013b
        L_0x00f3:
            int r18 = r15 + -1
            r0 = r18
            if (r7 != r0) goto L_0x0102
            r23 = 1065353216(0x3f800000, float:1.0)
            r0 = r23
            r13.setFraction(r0)
            goto L_0x013b
        L_0x0102:
            int r25 = r7 + 1
            r14 = r7
        L_0x0105:
            r0 = r25
            r1 = r18
            if (r0 >= r1) goto L_0x011d
            r13 = r24[r25]
            float r21 = r13.getFraction()
            r23 = 0
            int r22 = (r21 > r23 ? 1 : (r21 == r23 ? 0 : -1))
            if (r22 < 0) goto L_0x0118
            goto L_0x011d
        L_0x0118:
            r14 = r25
            int r25 = r25 + 1
            goto L_0x0105
        L_0x011d:
            int r18 = r14 + 1
            r13 = r24[r18]
            float r21 = r13.getFraction()
            int r18 = r7 + -1
            r13 = r24[r18]
            float r26 = r13.getFraction()
            r0 = r21
            r1 = r26
            float r0 = r0 - r1
            r21 = r0
            r0 = r24
            r1 = r21
            getValue(r0, r1, r7, r14)
        L_0x013b:
            int r7 = r7 + 1
            goto L_0x00db
        L_0x013e:
            r0 = r34
            r1 = r24
            android.animation.PropertyValuesHolder r27 = android.animation.PropertyValuesHolder.ofKeyframe(r0, r1)
            r8 = 3
            r0 = r35
            if (r0 != r8) goto L_0x015a
            com.org.shortcuts.drawable.Method r28 = com.org.shortcuts.drawable.Method.getMethod()
            r0 = r27
            r1 = r28
            r0.setEvaluator(r1)
            return r27
        L_0x0157:
            r29 = 0
            return r29
        L_0x015a:
            return r27
        */
        throw new UnsupportedOperationException("Method not decompiled: com.org.shortcuts.drawable.Context.create(android.content.Context, android.content.res.Resources, android.content.res.Resources$Theme, org.xmlpull.v1.XmlPullParser, java.lang.String, int):android.animation.PropertyValuesHolder");
    }

    private static Animator createAnimatorFromXml(android.content.Context context, Resources resources, Resources.Theme theme, XmlPullParser xmlPullParser, float f) {
        return createAnimatorFromXml(context, resources, theme, xmlPullParser, Xml.asAttributeSet(xmlPullParser), (AnimatorSet) null, 0, f);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v0, resolved type: android.animation.ValueAnimator} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v1, resolved type: android.animation.ValueAnimator} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r31v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r33v0, resolved type: android.animation.Animator} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: android.animation.ValueAnimator} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: android.animation.ValueAnimator} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v22, resolved type: android.animation.AnimatorSet} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v4, resolved type: android.animation.ValueAnimator} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: android.animation.ValueAnimator} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: android.animation.ValueAnimator} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.animation.Animator createAnimatorFromXml(android.content.Context r34, android.content.res.Resources r35, android.content.res.Resources.Theme r36, org.xmlpull.v1.XmlPullParser r37, android.util.AttributeSet r38, android.animation.AnimatorSet r39, int r40, float r41) {
        /*
            r0 = r37
            int r8 = r0.getDepth()
            r9 = 0
            r10 = 0
        L_0x0008:
            r0 = r37
            int r11 = r0.next()
            r12 = 0
            r13 = 3
            if (r11 != r13) goto L_0x001a
            r0 = r37
            int r14 = r0.getDepth()
            if (r14 <= r8) goto L_0x011d
        L_0x001a:
            r13 = 1
            if (r11 == r13) goto L_0x011d
            r13 = 2
            if (r11 == r13) goto L_0x0021
            goto L_0x0008
        L_0x0021:
            r0 = r37
            java.lang.String r15 = r0.getName()
            java.lang.String r17 = "objectAnimator"
            r0 = r17
            boolean r16 = r15.equals(r0)
            if (r16 == 0) goto L_0x0044
            r0 = r34
            r1 = r35
            r2 = r36
            r3 = r38
            r4 = r41
            r5 = r37
            android.animation.ObjectAnimator r9 = loadObjectAnimator(r0, r1, r2, r3, r4, r5)
        L_0x0041:
            goto L_0x00e3
        L_0x0044:
            java.lang.String r17 = "animator"
            r0 = r17
            boolean r16 = r15.equals(r0)
            if (r16 == 0) goto L_0x0063
            r18 = 0
            r0 = r34
            r1 = r35
            r2 = r36
            r3 = r38
            r4 = r18
            r5 = r41
            r6 = r37
            android.animation.ValueAnimator r9 = loadAnimator(r0, r1, r2, r3, r4, r5, r6)
            goto L_0x0041
        L_0x0063:
            java.lang.String r17 = "set"
            r0 = r17
            boolean r16 = r15.equals(r0)
            if (r16 == 0) goto L_0x00ad
            android.animation.AnimatorSet r19 = new android.animation.AnimatorSet
            r0 = r19
            r0.<init>()
            int[] r20 = com.org.shortcuts.drawable.AndroidResources.AnimatorSet
            r0 = r35
            r1 = r36
            r2 = r38
            r3 = r20
            android.content.res.TypedArray r21 = com.org.android.ui.asm.TypedArrayUtils.obtainAttributes(r0, r1, r2, r3)
            java.lang.String r17 = "ordering"
            r13 = 0
            r22 = 0
            r0 = r21
            r1 = r37
            r2 = r17
            r3 = r22
            int r11 = com.org.android.ui.asm.TypedArrayUtils.getString(r0, r1, r2, r13, r3)
            r0 = r34
            r1 = r35
            r2 = r36
            r3 = r37
            r4 = r38
            r5 = r19
            r6 = r11
            r7 = r41
            createAnimatorFromXml(r0, r1, r2, r3, r4, r5, r6, r7)
            r0 = r21
            r0.recycle()
            r9 = r19
            goto L_0x00e3
        L_0x00ad:
            java.lang.String r17 = "propertyValuesHolder"
            r0 = r17
            boolean r16 = r15.equals(r0)
            if (r16 == 0) goto L_0x00f4
            r0 = r37
            android.util.AttributeSet r23 = android.util.Xml.asAttributeSet(r0)
            r0 = r34
            r1 = r35
            r2 = r36
            r3 = r37
            r4 = r23
            android.animation.PropertyValuesHolder[] r24 = inflate(r0, r1, r2, r3, r4)
            if (r24 == 0) goto L_0x00e2
            if (r9 == 0) goto L_0x00e2
            boolean r0 = r9 instanceof android.animation.ValueAnimator
            r16 = r0
            if (r16 == 0) goto L_0x00e2
            r26 = r9
            android.animation.ValueAnimator r26 = (android.animation.ValueAnimator) r26
            r25 = r26
            r0 = r25
            r1 = r24
            r0.setValues(r1)
        L_0x00e2:
            r12 = 1
        L_0x00e3:
            if (r39 == 0) goto L_0x0008
            if (r12 != 0) goto L_0x0008
            if (r10 != 0) goto L_0x00ee
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
        L_0x00ee:
            r10.add(r9)
            goto L_0x0008
        L_0x00f4:
            java.lang.RuntimeException r27 = new java.lang.RuntimeException
            java.lang.StringBuilder r28 = new java.lang.StringBuilder
            r0 = r28
            r0.<init>()
            java.lang.String r17 = "Unknown animator name: "
            r0 = r28
            r1 = r17
            r0.append(r1)
            r0 = r37
            java.lang.String r15 = r0.getName()
            r0 = r28
            r0.append(r15)
            r0 = r28
            java.lang.String r15 = r0.toString()
            r0 = r27
            r0.<init>(r15)
            throw r27
        L_0x011d:
            if (r39 == 0) goto L_0x0157
            if (r10 == 0) goto L_0x0157
            int r8 = r10.size()
            android.animation.Animator[] r0 = new android.animation.Animator[r8]
            r29 = r0
            java.util.Iterator r30 = r10.iterator()
        L_0x012d:
            r0 = r30
            boolean r16 = r0.hasNext()
            if (r16 == 0) goto L_0x0146
            r0 = r30
            java.lang.Object r31 = r0.next()
            r33 = r31
            android.animation.Animator r33 = (android.animation.Animator) r33
            r32 = r33
            r29[r12] = r32
            int r12 = r12 + 1
            goto L_0x012d
        L_0x0146:
            if (r40 != 0) goto L_0x0150
            r0 = r39
            r1 = r29
            r0.playTogether(r1)
            return r9
        L_0x0150:
            r0 = r39
            r1 = r29
            r0.playSequentially(r1)
        L_0x0157:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.org.shortcuts.drawable.Context.createAnimatorFromXml(android.content.Context, android.content.res.Resources, android.content.res.Resources$Theme, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.animation.AnimatorSet, int, float):android.animation.Animator");
    }

    public static Animator getDrawable(android.content.Context context, int i) {
        return Build.VERSION.SDK_INT >= 24 ? AnimatorInflater.loadAnimator(context, i) : getDrawable(context, context.getResources(), context.getTheme(), i);
    }

    public static Animator getDrawable(android.content.Context context, Resources resources, Resources.Theme theme, int i) {
        return loadAnimator(context, resources, theme, i, 1.0f);
    }

    private static int getType(TypedArray typedArray, int i, int i2) {
        TypedValue $r1 = typedArray.peekValue(i);
        boolean $z0 = true;
        boolean $z1 = $r1 != null;
        int $i1 = $z1 ? $r1.type : 0;
        TypedValue $r12 = typedArray.peekValue(i2);
        if ($r12 == null) {
            $z0 = false;
        }
        int $i0 = $z0 ? $r12.type : 0;
        if (!$z1 || !open($i1)) {
            return (!$z0 || !open($i0)) ? 0 : 3;
        }
        return 3;
    }

    private static PropertyValuesHolder getType(TypedArray typedArray, int i, int i2, int i3, String $r1) {
        TypedValue $r2 = typedArray.peekValue(i2);
        boolean $z0 = $r2 != null;
        int $i3 = $z0 ? $r2.type : 0;
        TypedValue $r22 = typedArray.peekValue(i3);
        boolean $z1 = $r22 != null;
        int $i4 = $z1 ? $r22.type : 0;
        if (i == 4) {
            i = ((!$z0 || !open($i3)) && (!$z1 || !open($i4))) ? 0 : 3;
        }
        boolean $z2 = i == 0;
        PropertyValuesHolder $r3 = null;
        if (i == 2) {
            String $r4 = typedArray.getString(i2);
            String $r5 = typedArray.getString(i3);
            PathParser.PathDataNode[] $r6 = PathParser.createNodesFromPathData($r4);
            PathParser.PathDataNode[] $r7 = PathParser.createNodesFromPathData($r5);
            if ($r6 == null && $r7 == null) {
                return null;
            }
            if ($r6 != null) {
                Label $r8 = new Label();
                if ($r7 == null) {
                    return PropertyValuesHolder.ofObject($r1, $r8, new Object[]{$r6});
                } else if (PathParser.canMorph($r6, $r7)) {
                    return PropertyValuesHolder.ofObject($r1, $r8, new Object[]{$r6, $r7});
                } else {
                    throw new InflateException(" Can't morph from " + $r4 + " to " + $r5);
                }
            } else if ($r7 == null) {
                return null;
            } else {
                return PropertyValuesHolder.ofObject($r1, new Label(), new Object[]{$r7});
            }
        } else {
            Method $r12 = i == 3 ? Method.getMethod() : null;
            if ($z2) {
                if ($z0) {
                    float $f0 = $i3 == 5 ? typedArray.getDimension(i2, 0.0f) : typedArray.getFloat(i2, 0.0f);
                    if ($z1) {
                        $r3 = PropertyValuesHolder.ofFloat($r1, new float[]{$f0, $i4 == 5 ? typedArray.getDimension(i3, 0.0f) : typedArray.getFloat(i3, 0.0f)});
                    } else {
                        $r3 = PropertyValuesHolder.ofFloat($r1, new float[]{$f0});
                    }
                } else {
                    $r3 = PropertyValuesHolder.ofFloat($r1, new float[]{$i4 == 5 ? typedArray.getDimension(i3, 0.0f) : typedArray.getFloat(i3, 0.0f)});
                }
            } else if ($z0) {
                int $i0 = $i3 == 5 ? (int) typedArray.getDimension(i2, 0.0f) : open($i3) ? typedArray.getColor(i2, 0) : typedArray.getInt(i2, 0);
                if ($z1) {
                    $r3 = PropertyValuesHolder.ofInt($r1, new int[]{$i0, $i4 == 5 ? (int) typedArray.getDimension(i3, 0.0f) : open($i4) ? typedArray.getColor(i3, 0) : typedArray.getInt(i3, 0)});
                } else {
                    $r3 = PropertyValuesHolder.ofInt($r1, new int[]{$i0});
                }
            } else if ($z1) {
                $r3 = PropertyValuesHolder.ofInt($r1, new int[]{$i4 == 5 ? (int) typedArray.getDimension(i3, 0.0f) : open($i4) ? typedArray.getColor(i3, 0) : typedArray.getInt(i3, 0)});
            }
            if ($r3 == null || $r12 == null) {
                return $r3;
            }
            $r3.setEvaluator($r12);
            return $r3;
        }
    }

    private static void getValue(Keyframe[] keyframeArr, float f, int i, int i2) {
        float $f1 = f / ((float) ((i2 - i) + 2));
        while (i <= i2) {
            keyframeArr[i].setFraction(keyframeArr[i - 1].getFraction() + $f1);
            i++;
        }
    }

    private static int inflate(Resources resources, Resources.Theme theme, AttributeSet attributeSet, XmlPullParser xmlPullParser) {
        TypedArray $r5 = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.styleable_VectorDrawableGroup);
        byte $b0 = 0;
        TypedValue $r6 = TypedArrayUtils.getNamedFloat($r5, xmlPullParser, "value", 0);
        if (($r6 != null) && open($r6.type)) {
            $b0 = 3;
        }
        $r5.recycle();
        return $b0;
    }

    private static Keyframe inflate(android.content.Context context, Resources resources, Resources.Theme theme, AttributeSet attributeSet, int $i0, XmlPullParser xmlPullParser) {
        TypedArray $r6 = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.styleable_VectorDrawableGroup);
        float $f0 = TypedArrayUtils.getNamedFloat($r6, xmlPullParser, "fraction", 3, -1.0f);
        TypedValue $r7 = TypedArrayUtils.getNamedFloat($r6, xmlPullParser, "value", 0);
        boolean $z0 = $r7 != null;
        if ($i0 == 4) {
            if ($z0) {
                int $i02 = $r7.type;
                int i = $i02;
                if (open($i02)) {
                    $i0 = 3;
                }
            }
            $i0 = 0;
        }
        Keyframe $r8 = $z0 ? $i0 != 0 ? ($i0 == 1 || $i0 == 3) ? Keyframe.ofInt($f0, TypedArrayUtils.getString($r6, xmlPullParser, "value", 0, 0)) : null : Keyframe.ofFloat($f0, TypedArrayUtils.getNamedFloat($r6, xmlPullParser, "value", 0, 0.0f)) : $i0 == 0 ? Keyframe.ofFloat($f0) : Keyframe.ofInt($f0);
        int $i03 = TypedArrayUtils.getResourceId($r6, xmlPullParser, "interpolator", 1, 0);
        if ($i03 > 0) {
            $r8.setInterpolator(ArgbEvaluator.loadAnimator(context, $i03));
        }
        $r6.recycle();
        return $r8;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v0, resolved type: android.animation.PropertyValuesHolder} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x008a A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.animation.PropertyValuesHolder[] inflate(android.content.Context r22, android.content.res.Resources r23, android.content.res.Resources.Theme r24, org.xmlpull.v1.XmlPullParser r25, android.util.AttributeSet r26) {
        /*
            r6 = 0
        L_0x0001:
            r0 = r25
            int r7 = r0.getEventType()
            r8 = 0
            r9 = 3
            if (r7 == r9) goto L_0x006f
            r9 = 1
            if (r7 == r9) goto L_0x006f
            r9 = 2
            if (r7 == r9) goto L_0x0017
        L_0x0011:
            r0 = r25
            r0.next()
            goto L_0x0001
        L_0x0017:
            r0 = r25
            java.lang.String r10 = r0.getName()
            java.lang.String r12 = "propertyValuesHolder"
            boolean r11 = r10.equals(r12)
            if (r11 == 0) goto L_0x006e
            int[] r13 = com.org.shortcuts.drawable.AndroidResources.LayerDrawableItem
            r0 = r23
            r1 = r24
            r2 = r26
            android.content.res.TypedArray r14 = com.org.android.ui.asm.TypedArrayUtils.obtainAttributes(r0, r1, r2, r13)
            java.lang.String r12 = "propertyName"
            r9 = 3
            r0 = r25
            java.lang.String r10 = com.org.android.ui.asm.TypedArrayUtils.getString(r14, r0, r12, r9)
            java.lang.String r12 = "valueType"
            r9 = 2
            r15 = 4
            r0 = r25
            int r8 = com.org.android.ui.asm.TypedArrayUtils.getString(r14, r0, r12, r9, r15)
            r0 = r22
            r1 = r23
            r2 = r24
            r3 = r25
            r4 = r10
            r5 = r8
            android.animation.PropertyValuesHolder r16 = create(r0, r1, r2, r3, r4, r5)
            r17 = r16
            if (r16 != 0) goto L_0x005c
            r9 = 0
            r15 = 1
            android.animation.PropertyValuesHolder r17 = getType(r14, r8, r9, r15, r10)
        L_0x005c:
            if (r17 == 0) goto L_0x006a
            if (r6 != 0) goto L_0x0065
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
        L_0x0065:
            r0 = r17
            r6.add(r0)
        L_0x006a:
            r14.recycle()
            goto L_0x0011
        L_0x006e:
            goto L_0x0011
        L_0x006f:
            if (r6 == 0) goto L_0x008a
            int r7 = r6.size()
            android.animation.PropertyValuesHolder[] r0 = new android.animation.PropertyValuesHolder[r7]
            r18 = r0
        L_0x0079:
            if (r8 >= r7) goto L_0x008d
            java.lang.Object r19 = r6.get(r8)
            r20 = r19
            android.animation.PropertyValuesHolder r20 = (android.animation.PropertyValuesHolder) r20
            r16 = r20
            r18[r8] = r16
            int r8 = r8 + 1
            goto L_0x0079
        L_0x008a:
            r21 = 0
            return r21
        L_0x008d:
            return r18
        */
        throw new UnsupportedOperationException("Method not decompiled: com.org.shortcuts.drawable.Context.inflate(android.content.Context, android.content.res.Resources, android.content.res.Resources$Theme, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet):android.animation.PropertyValuesHolder[]");
    }

    private static void load(ValueAnimator valueAnimator, TypedArray typedArray, TypedArray typedArray2, float f, XmlPullParser xmlPullParser) {
        long $l1 = (long) TypedArrayUtils.getString(typedArray, xmlPullParser, "duration", 1, 300);
        long $l2 = (long) TypedArrayUtils.getString(typedArray, xmlPullParser, "startOffset", 2, 0);
        int $i3 = TypedArrayUtils.getString(typedArray, xmlPullParser, "valueType", 7, 4);
        int $i0 = $i3;
        if (TypedArrayUtils.hasAttribute(xmlPullParser, "valueFrom") && TypedArrayUtils.hasAttribute(xmlPullParser, "valueTo")) {
            if ($i3 == 4) {
                $i0 = getType(typedArray, 5, 6);
            }
            PropertyValuesHolder $r4 = getType(typedArray, $i0, 5, 6, "");
            if ($r4 != null) {
                valueAnimator.setValues(new PropertyValuesHolder[]{$r4});
            }
        }
        valueAnimator.setDuration($l1);
        valueAnimator.setStartDelay($l2);
        valueAnimator.setRepeatCount(TypedArrayUtils.getString(typedArray, xmlPullParser, "repeatCount", 3, 0));
        valueAnimator.setRepeatMode(TypedArrayUtils.getString(typedArray, xmlPullParser, "repeatMode", 4, 1));
        if (typedArray2 != null) {
            build(valueAnimator, typedArray2, $i0, f, xmlPullParser);
        }
    }

    public static Animator loadAnimator(android.content.Context context, Resources resources, Resources.Theme theme, int i, float f) {
        XmlResourceParser $r3 = null;
        try {
            XmlResourceParser $r4 = resources.getAnimation(i);
            $r3 = $r4;
            Animator $r5 = createAnimatorFromXml(context, resources, theme, $r4, f);
            if ($r4 == null) {
                return $r5;
            }
            $r4.close();
            return $r5;
        } catch (XmlPullParserException $r11) {
            Resources.NotFoundException $r8 = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i));
            $r8.initCause($r11);
            throw $r8;
        } catch (IOException $r7) {
            Resources.NotFoundException $r82 = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i));
            $r82.initCause($r7);
            throw $r82;
        } catch (Throwable $r6) {
            if ($r3 != null) {
                $r3.close();
            }
            throw $r6;
        }
    }

    private static ValueAnimator loadAnimator(android.content.Context context, Resources resources, Resources.Theme theme, AttributeSet attributeSet, ValueAnimator $r6, float f, XmlPullParser xmlPullParser) {
        TypedArray $r7 = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.Animator);
        TypedArray $r8 = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.styleable_VectorDrawablePath);
        if ($r6 == null) {
            $r6 = new ValueAnimator();
        }
        load($r6, $r7, $r8, f, xmlPullParser);
        int $i0 = TypedArrayUtils.getResourceId($r7, xmlPullParser, "interpolator", 0, 0);
        if ($i0 > 0) {
            $r6.setInterpolator(ArgbEvaluator.loadAnimator(context, $i0));
        }
        $r7.recycle();
        if ($r8 != null) {
            $r8.recycle();
        }
        return $r6;
    }

    private static ObjectAnimator loadObjectAnimator(android.content.Context context, Resources resources, Resources.Theme theme, AttributeSet attributeSet, float f, XmlPullParser xmlPullParser) {
        ObjectAnimator $r5 = new ObjectAnimator();
        loadAnimator(context, resources, theme, attributeSet, $r5, f, xmlPullParser);
        return $r5;
    }

    private static boolean open(int i) {
        return i >= 28 && i <= 31;
    }

    private static void parse(Path path, ObjectAnimator objectAnimator, float f, String str, String str2) {
        PropertyValuesHolder $r10;
        PathMeasure $r7 = new PathMeasure(path, false);
        ArrayList $r4 = new ArrayList();
        $r4.add(Float.valueOf(0.0f));
        float $f1 = 0.0f;
        do {
            $f1 += $r7.getLength();
            $r4.add(Float.valueOf($f1));
        } while ($r7.nextContour());
        PathMeasure $r72 = new PathMeasure(path, false);
        int $i0 = Math.min(100, ((int) ($f1 / f)) + 1);
        float[] $r9 = new float[$i0];
        float[] $r5 = new float[$i0];
        float[] $r6 = new float[2];
        float $f0 = $f1 / ((float) ($i0 - 1));
        int $i1 = 0;
        float $f12 = 0.0f;
        int $i2 = 0;
        while (true) {
            $r10 = null;
            if ($i1 >= $i0) {
                break;
            }
            $r72.getPosTan($f12 - ((Float) $r4.get($i2)).floatValue(), $r6, (float[]) null);
            $r9[$i1] = $r6[0];
            $r5[$i1] = $r6[1];
            $f12 += $f0;
            int $i3 = $i2 + 1;
            if ($i3 < $r4.size() && $f12 > ((Float) $r4.get($i3)).floatValue()) {
                $r72.nextContour();
                $i2 = $i3;
            }
            $i1++;
        }
        PropertyValuesHolder $r12 = str != null ? PropertyValuesHolder.ofFloat(str, $r9) : null;
        if (str2 != null) {
            $r10 = PropertyValuesHolder.ofFloat(str2, $r5);
        }
        if ($r12 == null) {
            objectAnimator.setValues(new PropertyValuesHolder[]{$r10});
        } else if ($r10 == null) {
            objectAnimator.setValues(new PropertyValuesHolder[]{$r12});
        } else {
            objectAnimator.setValues(new PropertyValuesHolder[]{$r12, $r10});
        }
    }

    private static Keyframe toString(Keyframe $r1, float f) {
        return $r1.getType() == Float.TYPE ? Keyframe.ofFloat(f) : $r1.getType() == Integer.TYPE ? Keyframe.ofInt(f) : Keyframe.ofObject(f);
    }
}
