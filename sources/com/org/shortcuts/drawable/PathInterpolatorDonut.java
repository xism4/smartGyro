package com.org.shortcuts.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.animation.Interpolator;
import com.org.android.asm.PathParser;
import com.org.android.ui.asm.TypedArrayUtils;
import org.xmlpull.v1.XmlPullParser;

public class PathInterpolatorDonut implements Interpolator {
    private float[] mX;
    private float[] mY;

    public PathInterpolatorDonut(Context context, AttributeSet attributeSet, XmlPullParser xmlPullParser) {
        this(context.getResources(), context.getTheme(), attributeSet, xmlPullParser);
    }

    public PathInterpolatorDonut(Resources resources, Resources.Theme theme, AttributeSet attributeSet, XmlPullParser xmlPullParser) {
        TypedArray $r5 = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.Keyboard_Row);
        inflate($r5, xmlPullParser);
        $r5.recycle();
    }

    private void create(float f, float f2) {
        Path $r1 = new Path();
        $r1.moveTo(0.0f, 0.0f);
        $r1.quadTo(f, f2, 1.0f, 1.0f);
        update($r1);
    }

    private void draw(float f, float f2, float f3, float f4) {
        Path $r1 = new Path();
        $r1.moveTo(0.0f, 0.0f);
        $r1.cubicTo(f, f2, f3, f4, 1.0f, 1.0f);
        update($r1);
    }

    private void inflate(TypedArray typedArray, XmlPullParser xmlPullParser) {
        if (TypedArrayUtils.hasAttribute(xmlPullParser, "pathData")) {
            String $r3 = TypedArrayUtils.getString(typedArray, xmlPullParser, "pathData", 4);
            Path $r4 = PathParser.createPathFromPathData($r3);
            if ($r4 != null) {
                update($r4);
                return;
            }
            throw new InflateException("The path is null, which is created from " + $r3);
        } else if (!TypedArrayUtils.hasAttribute(xmlPullParser, "controlX1")) {
            throw new InflateException("pathInterpolator requires the controlX1 attribute");
        } else if (TypedArrayUtils.hasAttribute(xmlPullParser, "controlY1")) {
            float $f0 = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "controlX1", 0, 0.0f);
            float $f1 = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "controlY1", 1, 0.0f);
            boolean $z0 = TypedArrayUtils.hasAttribute(xmlPullParser, "controlX2");
            if ($z0 != TypedArrayUtils.hasAttribute(xmlPullParser, "controlY2")) {
                throw new InflateException("pathInterpolator requires both controlX2 and controlY2 for cubic Beziers.");
            } else if (!$z0) {
                create($f0, $f1);
            } else {
                draw($f0, $f1, TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "controlX2", 2, 0.0f), TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "controlY2", 3, 0.0f));
            }
        } else {
            throw new InflateException("pathInterpolator requires the controlY1 attribute");
        }
    }

    private void update(Path path) {
        int $i0 = 0;
        PathMeasure $r2 = new PathMeasure(path, false);
        float $f0 = $r2.getLength();
        int $i1 = Math.min(3000, ((int) ($f0 / 0.002f)) + 1);
        if ($i1 > 0) {
            this.mX = new float[$i1];
            this.mY = new float[$i1];
            float[] $r3 = new float[2];
            for (int $i2 = 0; $i2 < $i1; $i2++) {
                $r2.getPosTan((((float) $i2) * $f0) / ((float) ($i1 - 1)), $r3, (float[]) null);
                this.mX[$i2] = $r3[0];
                this.mY[$i2] = $r3[1];
            }
            if (((double) Math.abs(this.mX[0])) <= 1.0E-5d && ((double) Math.abs(this.mY[0])) <= 1.0E-5d) {
                int $i22 = $i1 - 1;
                if (((double) Math.abs(this.mX[$i22] - 1.0f)) <= 1.0E-5d && ((double) Math.abs(this.mY[$i22] - 1.0f)) <= 1.0E-5d) {
                    int $i23 = 0;
                    float $f02 = 0.0f;
                    while ($i0 < $i1) {
                        float[] $r32 = this.mX;
                        float $f1 = $r32[$i23];
                        if ($f1 >= $f02) {
                            $r32[$i0] = $f1;
                            $i0++;
                            $f02 = $f1;
                            $i23++;
                        } else {
                            throw new IllegalArgumentException("The Path cannot loop back on itself, x :" + $f1);
                        }
                    }
                    if ($r2.nextContour()) {
                        throw new IllegalArgumentException("The Path should be continuous, can't have 2+ contours");
                    }
                    return;
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("The Path must start at (0,0) and end at (1,1) start: ");
            sb.append(this.mX[0]);
            sb.append(",");
            sb.append(this.mY[0]);
            sb.append(" end:");
            int $i02 = $i1 - 1;
            sb.append(this.mX[$i02]);
            sb.append(",");
            sb.append(this.mY[$i02]);
            throw new IllegalArgumentException(sb.toString());
        }
        throw new IllegalArgumentException("The Path has a invalid length " + $f0);
    }

    public float getInterpolation(float $f0) {
        if ($f0 <= 0.0f) {
            return 0.0f;
        }
        if ($f0 >= 1.0f) {
            return 1.0f;
        }
        int $i1 = 0;
        int $i2 = this.mX.length - 1;
        while ($i2 - $i1 > 1) {
            int $i3 = ($i1 + $i2) / 2;
            if ($f0 < this.mX[$i3]) {
                $i2 = $i3;
            } else {
                $i1 = $i3;
            }
        }
        float[] $r1 = this.mX;
        float $f1 = $r1[$i2] - $r1[$i1];
        if ($f1 == 0.0f) {
            return this.mY[$i1];
        }
        float $f12 = ($f0 - $r1[$i1]) / $f1;
        float[] $r12 = this.mY;
        float $f02 = $r12[$i1];
        return $f02 + ($f12 * ($r12[$i2] - $f02));
    }
}
