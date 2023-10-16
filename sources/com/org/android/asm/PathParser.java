package com.org.android.asm;

import android.graphics.Path;
import android.util.Log;
import java.util.ArrayList;

public class PathParser {

    class ExtractFloatResult {
        int mEndPosition;
        boolean mEndWithNegOrDot;

        ExtractFloatResult() {
        }
    }

    public class PathDataNode {
        public float[] params;
        public char type;

        PathDataNode(char c, float[] fArr) {
            this.type = c;
            this.params = fArr;
        }

        PathDataNode(PathDataNode pathDataNode) {
            this.type = pathDataNode.type;
            float[] $r1 = pathDataNode.params;
            this.params = PathParser.copyOfRange($r1, 0, $r1.length);
        }

        private static void addCommand(Path path, float[] fArr, char c, char c2, float[] fArr2) {
            int i;
            float $f1;
            float $f0;
            int $i5;
            float $f02;
            float $f12;
            float $f6;
            float $f3;
            float $f2;
            int i2;
            float $f32;
            float $f22;
            float $f33;
            float $f23;
            char c3 = c2;
            float $f03 = fArr[0];
            float $f13 = fArr[1];
            float $f24 = fArr[2];
            float $f34 = fArr[3];
            float $f4 = fArr[4];
            float $f5 = fArr[5];
            switch (c2) {
                case 'A':
                case 'a':
                    i = 7;
                    break;
                case 'C':
                case 'c':
                    i = 6;
                    break;
                case 'H':
                case 'V':
                case 'h':
                case 'v':
                    i = 1;
                    break;
                case 'Q':
                case 'S':
                case 'q':
                case 's':
                    i = 4;
                    break;
                case 'Z':
                case 'z':
                    path.close();
                    path.moveTo($f4, $f5);
                    $f03 = $f4;
                    $f24 = $f4;
                    $f13 = $f5;
                    $f34 = $f5;
                    break;
            }
            i = 2;
            int $i4 = 0;
            while ($i4 < fArr2.length) {
                if (c3 != 'A') {
                    if (c3 == 'C') {
                        $i5 = $i4;
                        int $i6 = $i4 + 2;
                        int $i7 = $i4 + 3;
                        int $i8 = $i4 + 4;
                        int $i42 = $i4 + 5;
                        path.cubicTo(fArr2[$i4 + 0], fArr2[$i4 + 1], fArr2[$i6], fArr2[$i7], fArr2[$i8], fArr2[$i42]);
                        $f0 = fArr2[$i8];
                        $f1 = fArr2[$i42];
                        $f34 = fArr2[$i7];
                        $f24 = fArr2[$i6];
                    } else if (c3 == 'H') {
                        $i5 = $i4;
                        int $i43 = $i4 + 0;
                        path.lineTo(fArr2[$i43], $f1);
                        $f0 = fArr2[$i43];
                    } else if (c3 == 'Q') {
                        $i5 = $i4;
                        int $i62 = $i4 + 0;
                        int $i72 = $i4 + 1;
                        int $i82 = $i4 + 2;
                        int $i44 = $i4 + 3;
                        path.quadTo(fArr2[$i62], fArr2[$i72], fArr2[$i82], fArr2[$i44]);
                        $f0 = fArr2[$i82];
                        $f1 = fArr2[$i44];
                        $f24 = fArr2[$i62];
                        $f34 = fArr2[$i72];
                    } else if (c3 == 'V') {
                        $i5 = $i4;
                        int $i45 = $i4 + 0;
                        path.lineTo($f0, fArr2[$i45]);
                        $f1 = fArr2[$i45];
                    } else if (c3 != 'a') {
                        if (c3 == 'c') {
                            int $i52 = $i4 + 2;
                            int $i63 = $i4 + 3;
                            int $i73 = $i4 + 4;
                            int $i83 = $i4 + 5;
                            path.rCubicTo(fArr2[$i4 + 0], fArr2[$i4 + 1], fArr2[$i52], fArr2[$i63], fArr2[$i73], fArr2[$i83]);
                            $f3 = fArr2[$i52] + $f0;
                            $f6 = fArr2[$i63] + $f1;
                            $f0 += fArr2[$i73];
                            $f2 = fArr2[$i83];
                            $f1 += $f2;
                            $f24 = $f3;
                            $f34 = $f6;
                        } else if (c3 != 'h') {
                            if (c3 != 'q') {
                                if (c3 == 'v') {
                                    int $i53 = $i4 + 0;
                                    path.rLineTo(0.0f, fArr2[$i53]);
                                    i2 = fArr2[$i53];
                                } else if (c3 != 'L') {
                                    if (c3 == 'M') {
                                        int $i54 = $i4 + 0;
                                        $f0 = fArr2[$i54];
                                        int $i64 = $i4 + 1;
                                        $f1 = fArr2[$i64];
                                        if ($i4 > 0) {
                                            path.lineTo(fArr2[$i54], fArr2[$i64]);
                                        } else {
                                            path.moveTo(fArr2[$i54], fArr2[$i64]);
                                        }
                                    } else if (c3 == 'S') {
                                        if (c == 'c' || c == 's' || c == 'C' || c == 'S') {
                                            $f0 = ($f0 * 2.0f) - $f24;
                                            $f1 = ($f1 * 2.0f) - $f34;
                                        }
                                        int $i55 = $i4 + 0;
                                        int $i65 = $i4 + 1;
                                        int $i74 = $i4 + 2;
                                        int $i84 = $i4 + 3;
                                        path.cubicTo($f0, $f1, fArr2[$i55], fArr2[$i65], fArr2[$i74], fArr2[$i84]);
                                        $f3 = fArr2[$i55];
                                        $f6 = fArr2[$i65];
                                        $f0 = fArr2[$i74];
                                        $f1 = fArr2[$i84];
                                        $f24 = $f3;
                                        $f34 = $f6;
                                    } else if (c3 == 'T') {
                                        if (c == 'q' || c == 't' || c == 'Q' || c == 'T') {
                                            $f0 = ($f0 * 2.0f) - $f24;
                                            $f1 = ($f1 * 2.0f) - $f34;
                                        }
                                        int $i56 = $i4 + 0;
                                        int $i66 = $i4 + 1;
                                        path.quadTo($f0, $f1, fArr2[$i56], fArr2[$i66]);
                                        float $f62 = fArr2[$i56];
                                        $f34 = $f1;
                                        $f24 = $f0;
                                        $i5 = $i4;
                                        $f0 = $f62;
                                        $f1 = fArr2[$i66];
                                    } else if (c3 == 'l') {
                                        int $i57 = $i4 + 0;
                                        int $i67 = $i4 + 1;
                                        path.rLineTo(fArr2[$i57], fArr2[$i67]);
                                        $f0 += fArr2[$i57];
                                        i2 = fArr2[$i67];
                                    } else if (c3 == 'm') {
                                        int $i58 = $i4 + 0;
                                        $f0 += fArr2[$i58];
                                        int $i68 = $i4 + 1;
                                        $f1 += fArr2[$i68];
                                        if ($i4 > 0) {
                                            path.rLineTo(fArr2[$i58], fArr2[$i68]);
                                        } else {
                                            path.rMoveTo(fArr2[$i58], fArr2[$i68]);
                                        }
                                    } else if (c3 == 's') {
                                        if (c == 'c' || c == 's' || c == 'C' || c == 'S') {
                                            $f32 = $f1 - $f34;
                                            $f22 = $f0 - $f24;
                                        } else {
                                            $f22 = 0.0f;
                                            $f32 = 0.0f;
                                        }
                                        int $i59 = $i4 + 0;
                                        int $i69 = $i4 + 1;
                                        int $i75 = $i4 + 2;
                                        int $i85 = $i4 + 3;
                                        path.rCubicTo($f22, $f32, fArr2[$i59], fArr2[$i69], fArr2[$i75], fArr2[$i85]);
                                        $f3 = fArr2[$i59] + $f0;
                                        $f6 = fArr2[$i69] + $f1;
                                        $f0 += fArr2[$i75];
                                        $f2 = fArr2[$i85];
                                    } else if (c3 == 't') {
                                        if (c == 'q' || c == 't' || c == 'Q' || c == 'T') {
                                            $f23 = $f0 - $f24;
                                            $f33 = $f1 - $f34;
                                        } else {
                                            $f33 = 0.0f;
                                            $f23 = 0.0f;
                                        }
                                        int $i510 = $i4 + 0;
                                        int $i610 = $i4 + 1;
                                        path.rQuadTo($f23, $f33, fArr2[$i510], fArr2[$i610]);
                                        $f24 = $f23 + $f0;
                                        $f34 = $f33 + $f1;
                                        $f0 += fArr2[$i510];
                                        $f1 += fArr2[$i610];
                                    }
                                    $f5 = $f1;
                                    $f4 = $f0;
                                } else {
                                    int $i511 = $i4 + 0;
                                    int $i611 = $i4 + 1;
                                    path.lineTo(fArr2[$i511], fArr2[$i611]);
                                    $f0 = fArr2[$i511];
                                    $f1 = fArr2[$i611];
                                }
                                $f1 += i2;
                            } else {
                                int $i512 = $i4 + 0;
                                int $i612 = $i4 + 1;
                                int $i76 = $i4 + 2;
                                int $i86 = $i4 + 3;
                                path.rQuadTo(fArr2[$i512], fArr2[$i612], fArr2[$i76], fArr2[$i86]);
                                $f3 = fArr2[$i512] + $f0;
                                $f6 = fArr2[$i612] + $f1;
                                $f0 += fArr2[$i76];
                                $f2 = fArr2[$i86];
                            }
                            $f1 += $f2;
                            $f24 = $f3;
                            $f34 = $f6;
                        } else {
                            int $i513 = $i4 + 0;
                            path.rLineTo(fArr2[$i513], 0.0f);
                            $f0 += fArr2[$i513];
                        }
                        $i5 = $i4;
                    } else {
                        int $i613 = $i4 + 5;
                        int $i77 = $i4 + 6;
                        $i5 = $i4;
                        drawArc(path, $f0, $f1, fArr2[$i613] + $f0, fArr2[$i77] + $f1, fArr2[$i4 + 0], fArr2[$i4 + 1], fArr2[$i4 + 2], fArr2[$i4 + 3] != 0.0f, fArr2[$i4 + 4] != 0.0f);
                        $f02 = $f0 + fArr2[$i613];
                        $f12 = $f1 + fArr2[$i77];
                    }
                    $i4 = $i5 + i;
                    c = c2;
                    c3 = c2;
                } else {
                    $i5 = $i4;
                    int $i614 = $i4 + 5;
                    int $i78 = $i4 + 6;
                    drawArc(path, $f0, $f1, fArr2[$i614], fArr2[$i78], fArr2[$i4 + 0], fArr2[$i4 + 1], fArr2[$i4 + 2], fArr2[$i4 + 3] != 0.0f, fArr2[$i5 + 4] != 0.0f);
                    $f02 = fArr2[$i614];
                    $f12 = fArr2[$i78];
                }
                $f34 = $f1;
                $f24 = $f0;
                $i4 = $i5 + i;
                c = c2;
                c3 = c2;
            }
            fArr[0] = $f0;
            fArr[1] = $f1;
            fArr[2] = $f24;
            fArr[3] = $f34;
            fArr[4] = $f4;
            fArr[5] = $f5;
        }

        private static void arcToBezier(Path path, double d, double d2, double $d17, double $d9, double d3, double $d15, double d4, double d5, double $d5) {
            double $d10 = $d17;
            int $i0 = (int) Math.ceil(Math.abs(($d5 * 4.0d) / 3.141592653589793d));
            double $d12 = Math.cos(d4);
            double $d11 = $d12;
            double $d13 = Math.sin(d4);
            double $d14 = $d13;
            double $d152 = Math.cos(d5);
            double $d16 = Math.sin(d5);
            double $d172 = -$d17;
            double $d6 = $d172 * $d12;
            double $d18 = $d6 * $d16;
            double $d7 = $d9 * $d13;
            double $d19 = $d7 * $d152;
            double $d132 = $d172 * $d13;
            double d6 = $d9 * $d12;
            double $d153 = ($d16 * $d132) + ($d152 * d6);
            double $d122 = (double) $i0;
            Double.isNaN($d122);
            double d7 = $d5 / $d122;
            double $d123 = d3;
            double $d182 = $d18 - $d19;
            int i = 0;
            double $d192 = d5;
            while (i < $i0) {
                double $d8 = $d192 + d7;
                double $d20 = Math.sin($d8);
                double $d173 = Math.cos($d8);
                double d8 = (d + (($d10 * $d11) * $d173)) - ($d7 * $d20);
                double $d102 = d2 + ($d10 * $d14 * $d173) + (d6 * $d20);
                double d9 = ($d6 * $d20) - ($d7 * $d173);
                double $d174 = ($d20 * $d132) + ($d173 * d6);
                double $d193 = $d8 - $d192;
                double $d202 = Math.tan($d193 / 2.0d);
                double $d203 = ($d202 * 3.0d * $d202) + 4.0d;
                double d10 = $d203;
                double sin = (Math.sin($d193) * (Math.sqrt($d203) - 1.0d)) / 3.0d;
                double $d194 = $d182 * sin;
                double d11 = $d194;
                path.rLineTo(0.0f, 0.0f);
                path.cubicTo((float) ($d123 + $d194), (float) ($d15 + ($d153 * sin)), (float) (d8 - (sin * d9)), (float) ($d102 - (sin * $d174)), (float) d8, (float) $d102);
                i++;
                $d15 = $d102;
                $d192 = $d8;
                $d153 = $d174;
                $d182 = d9;
                $d10 = $d17;
                $d123 = d8;
            }
        }

        private static void drawArc(Path path, float $f7, float f, float $f72, float f2, float $f4, float $f5, float f3, boolean z, boolean z2) {
            double $d13;
            double $d0;
            double $d1 = (double) f3;
            double d = $d1;
            double $d12 = Math.toRadians($d1);
            double $d2 = Math.cos($d12);
            double $d3 = Math.sin($d12);
            double $d4 = (double) $f7;
            double $d42 = $d4;
            Double.isNaN($d4);
            double $d6 = (double) f;
            double $d62 = $d6;
            Double.isNaN($d6);
            double $d5 = (double) $f4;
            double $d52 = $d5;
            Double.isNaN($d5);
            double $d8 = (($d42 * $d2) + ($d62 * $d3)) / $d52;
            float $f73 = -$f7;
            float f4 = $f73;
            double $d7 = (double) $f73;
            Double.isNaN($d7);
            Double.isNaN($d62);
            double $d9 = ($d7 * $d3) + ($d62 * $d2);
            double $d72 = (double) $f5;
            double $d73 = $d72;
            Double.isNaN($d72);
            double d2 = $d9 / $d73;
            double $d10 = (double) $f72;
            Double.isNaN($d10);
            double d3 = $d10 * $d2;
            double $d11 = (double) f2;
            double $d112 = $d11;
            Double.isNaN($d11);
            Double.isNaN($d52);
            double d4 = (d3 + ($d112 * $d3)) / $d52;
            float $f74 = -$f72;
            float f5 = $f74;
            double $d02 = (double) $f74;
            Double.isNaN($d02);
            Double.isNaN($d112);
            Double.isNaN($d73);
            double d5 = (($d02 * $d3) + ($d112 * $d2)) / $d73;
            double $d122 = $d8 - d4;
            double $d03 = d2 - d5;
            double $d132 = ($d8 + d4) / 2.0d;
            double d6 = (d2 + d5) / 2.0d;
            double $d16 = ($d122 * $d122) + ($d03 * $d03);
            if ($d16 == 0.0d) {
                Log.w("PathParser", " Points are coincident");
                return;
            }
            double d7 = (1.0d / $d16) - 0.25d;
            if (d7 < 0.0d) {
                Log.w("PathParser", "Points are too far apart " + $d16);
                double $d14 = Math.sqrt($d16) / 1.99999d;
                double d8 = $d14;
                float $f75 = (float) $d14;
                drawArc(path, $f7, f, $f72, f2, $f4 * $f75, $f5 * $f75, f3, z, z2);
                return;
            }
            double $d15 = Math.sqrt(d7);
            double d9 = $d122 * $d15;
            double $d04 = $d15 * $d03;
            if (z == z2) {
                $d0 = $d132 - $d04;
                $d13 = d6 + d9;
            } else {
                $d0 = $d132 + $d04;
                $d13 = d6 - d9;
            }
            double $d82 = Math.atan2(d2 - $d13, $d8 - $d0);
            double $d92 = Math.atan2(d5 - $d13, d4 - $d0) - $d82;
            if (z2 != ($d92 >= 0.0d)) {
                $d92 = $d92 > 0.0d ? $d92 - 6.283185307179586d : $d92 + 6.283185307179586d;
            }
            Double.isNaN($d52);
            double $d102 = $d0 * $d52;
            Double.isNaN($d73);
            double $d113 = $d13 * $d73;
            arcToBezier(path, ($d102 * $d2) - ($d113 * $d3), ($d102 * $d3) + ($d113 * $d2), $d52, $d73, $d42, $d62, $d12, $d82, $d92);
        }

        public static void nodesToPath(PathDataNode[] pathDataNodeArr, Path path) {
            float[] $r2 = new float[6];
            char $c0 = 'm';
            for (int $i1 = 0; $i1 < pathDataNodeArr.length; $i1++) {
                addCommand(path, $r2, $c0, pathDataNodeArr[$i1].type, pathDataNodeArr[$i1].params);
                $c0 = pathDataNodeArr[$i1].type;
            }
        }

        public void interpolatePathDataNode(PathDataNode pathDataNode, PathDataNode pathDataNode2, float f) {
            int $i0 = 0;
            while (true) {
                float[] $r3 = pathDataNode.params;
                if ($i0 < $r3.length) {
                    this.params[$i0] = ($r3[$i0] * (1.0f - f)) + (pathDataNode2.params[$i0] * f);
                    $i0++;
                } else {
                    return;
                }
            }
        }
    }

    private static void addNode(ArrayList arrayList, char c, float[] fArr) {
        arrayList.add(new PathDataNode(c, fArr));
    }

    public static boolean canMorph(PathDataNode[] pathDataNodeArr, PathDataNode[] pathDataNodeArr2) {
        if (pathDataNodeArr == null || pathDataNodeArr2 == null || pathDataNodeArr.length != pathDataNodeArr2.length) {
            return false;
        }
        for (int $i0 = 0; $i0 < pathDataNodeArr.length; $i0++) {
            if (pathDataNodeArr[$i0].type != pathDataNodeArr2[$i0].type || pathDataNodeArr[$i0].params.length != pathDataNodeArr2[$i0].params.length) {
                return false;
            }
        }
        return true;
    }

    static float[] copyOfRange(float[] fArr, int i, int i2) {
        if (i <= i2) {
            int $i1 = fArr.length;
            if (i < 0 || i > $i1) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int $i2 = i2 - i;
            float[] $r1 = new float[$i2];
            System.arraycopy(fArr, i, $r1, 0, Math.min($i2, $i1 - i));
            return $r1;
        }
        throw new IllegalArgumentException();
    }

    public static PathDataNode[] createNodesFromPathData(String str) {
        if (str == null) {
            return null;
        }
        ArrayList $r0 = new ArrayList();
        int $i0 = 1;
        int $i1 = 0;
        while ($i0 < str.length()) {
            int $i02 = nextStart(str, $i0);
            String $r2 = str.substring($i1, $i02).trim();
            if ($r2.length() > 0) {
                addNode($r0, $r2.charAt(0), getFloats($r2));
            }
            $i1 = $i02;
            $i0 = $i02 + 1;
        }
        if ($i0 - $i1 == 1 && $i1 < str.length()) {
            addNode($r0, str.charAt($i1), new float[0]);
        }
        return (PathDataNode[]) $r0.toArray(new PathDataNode[$r0.size()]);
    }

    public static Path createPathFromPathData(String str) {
        Path $r2 = new Path();
        PathDataNode[] $r3 = createNodesFromPathData(str);
        if ($r3 == null) {
            return null;
        }
        try {
            PathDataNode.nodesToPath($r3, $r2);
            return $r2;
        } catch (RuntimeException $r4) {
            throw new RuntimeException("Error in parsing " + str, $r4);
        }
    }

    public static PathDataNode[] deepCopyNodes(PathDataNode[] pathDataNodeArr) {
        if (pathDataNodeArr == null) {
            return null;
        }
        PathDataNode[] $r2 = new PathDataNode[pathDataNodeArr.length];
        for (int $i0 = 0; $i0 < pathDataNodeArr.length; $i0++) {
            $r2[$i0] = new PathDataNode(pathDataNodeArr[$i0]);
        }
        return $r2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0027, code lost:
        r9.mEndWithNegOrDot = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002d, code lost:
        if (r2 == false) goto L_0x0027;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0030, code lost:
        r2 = false;
     */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0039 A[LOOP:0: B:1:0x0007->B:19:0x0039, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x003c A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void extract(java.lang.String r7, int r8, com.org.android.asm.PathParser.ExtractFloatResult r9) {
        /*
            r0 = 0
            r9.mEndWithNegOrDot = r0
            r1 = r8
            r2 = 0
            r3 = 0
            r4 = 0
        L_0x0007:
            int r5 = r7.length()
            if (r1 >= r5) goto L_0x003c
            char r6 = r7.charAt(r1)
            r0 = 32
            if (r6 == r0) goto L_0x0034
            r0 = 69
            if (r6 == r0) goto L_0x0032
            r0 = 101(0x65, float:1.42E-43)
            if (r6 == r0) goto L_0x0032
            switch(r6) {
                case 44: goto L_0x0034;
                case 45: goto L_0x002b;
                case 46: goto L_0x0022;
                default: goto L_0x0020;
            }
        L_0x0020:
            goto L_0x0021
        L_0x0021:
            goto L_0x0030
        L_0x0022:
            if (r3 != 0) goto L_0x0027
            r2 = 0
            r3 = 1
            goto L_0x0036
        L_0x0027:
            r0 = 1
            r9.mEndWithNegOrDot = r0
            goto L_0x0034
        L_0x002b:
            if (r1 == r8) goto L_0x0030
            if (r2 != 0) goto L_0x0030
            goto L_0x0027
        L_0x0030:
            r2 = 0
            goto L_0x0036
        L_0x0032:
            r2 = 1
            goto L_0x0036
        L_0x0034:
            r2 = 0
            r4 = 1
        L_0x0036:
            if (r4 == 0) goto L_0x0039
            goto L_0x003c
        L_0x0039:
            int r1 = r1 + 1
            goto L_0x0007
        L_0x003c:
            r9.mEndPosition = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.org.android.asm.PathParser.extract(java.lang.String, int, com.org.android.asm.PathParser$ExtractFloatResult):void");
    }

    private static float[] getFloats(String str) {
        if (str.charAt(0) == 'z' || str.charAt(0) == 'Z') {
            return new float[0];
        }
        try {
            float[] $r1 = new float[str.length()];
            ExtractFloatResult $r2 = new ExtractFloatResult();
            int $i3 = str.length();
            int $i4 = 1;
            int $i5 = 0;
            while ($i4 < $i3) {
                extract(str, $i4, $r2);
                int $i0 = $r2.mEndPosition;
                if ($i4 < $i0) {
                    $r1[$i5] = Float.parseFloat(str.substring($i4, $i0));
                    $i5++;
                }
                $i4 = $r2.mEndWithNegOrDot ? $i0 : $i0 + 1;
            }
            return copyOfRange($r1, 0, $i5);
        } catch (NumberFormatException $r4) {
            throw new RuntimeException("error in parsing \"" + str + "\"", $r4);
        }
    }

    private static int nextStart(String str, int $i0) {
        while ($i0 < str.length()) {
            char $c2 = str.charAt($i0);
            if ((($c2 - 'A') * ($c2 - 'Z') <= 0 || ($c2 - 'a') * ($c2 - 'z') <= 0) && $c2 != 'e' && $c2 != 'E') {
                return $i0;
            }
            $i0++;
        }
        return $i0;
    }

    public static void updateNodes(PathDataNode[] pathDataNodeArr, PathDataNode[] pathDataNodeArr2) {
        for (int $i0 = 0; $i0 < pathDataNodeArr2.length; $i0++) {
            pathDataNodeArr[$i0].type = pathDataNodeArr2[$i0].type;
            for (int $i1 = 0; $i1 < pathDataNodeArr2[$i0].params.length; $i1++) {
                pathDataNodeArr[$i0].params[$i1] = pathDataNodeArr2[$i0].params[$i1];
            }
        }
    }
}
