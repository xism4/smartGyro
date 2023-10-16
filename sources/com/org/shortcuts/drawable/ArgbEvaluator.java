package com.org.shortcuts.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Build;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import com.org.android.view.style.FastOutLinearInInterpolator;
import com.org.android.view.style.FastOutSlowInInterpolator;
import com.org.android.view.style.LinearOutSlowInInterpolator;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

public class ArgbEvaluator {
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: android.view.animation.LinearInterpolator} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: android.view.animation.LinearInterpolator} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: android.view.animation.LinearInterpolator} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: com.org.shortcuts.drawable.PathInterpolatorDonut} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v4, resolved type: com.org.shortcuts.drawable.PathInterpolatorDonut} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: android.view.animation.BounceInterpolator} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: com.org.shortcuts.drawable.PathInterpolatorDonut} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v12, resolved type: android.view.animation.AnticipateOvershootInterpolator} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: com.org.shortcuts.drawable.PathInterpolatorDonut} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v13, resolved type: android.view.animation.OvershootInterpolator} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v7, resolved type: com.org.shortcuts.drawable.PathInterpolatorDonut} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v14, resolved type: android.view.animation.AnticipateInterpolator} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v8, resolved type: com.org.shortcuts.drawable.PathInterpolatorDonut} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v15, resolved type: android.view.animation.CycleInterpolator} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v9, resolved type: android.view.animation.AccelerateDecelerateInterpolator} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v10, resolved type: android.view.animation.DecelerateInterpolator} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v11, resolved type: android.view.animation.AccelerateInterpolator} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v12, resolved type: android.view.animation.LinearInterpolator} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.view.animation.Interpolator createFromXml(android.content.Context r25, android.content.res.Resources r26, android.content.res.Resources.Theme r27, org.xmlpull.v1.XmlPullParser r28) {
        /*
            r0 = r28
            int r3 = r0.getDepth()
            r4 = 0
        L_0x0007:
            r0 = r28
            int r5 = r0.next()
            r6 = 3
            if (r5 != r6) goto L_0x0018
            r0 = r28
            int r7 = r0.getDepth()
            if (r7 <= r3) goto L_0x0111
        L_0x0018:
            r6 = 1
            if (r5 == r6) goto L_0x0111
            r6 = 2
            if (r5 == r6) goto L_0x001f
            goto L_0x0007
        L_0x001f:
            r0 = r28
            android.util.AttributeSet r8 = android.util.Xml.asAttributeSet(r0)
            r0 = r28
            java.lang.String r9 = r0.getName()
            java.lang.String r11 = "linearInterpolator"
            boolean r10 = r9.equals(r11)
            if (r10 == 0) goto L_0x003a
            android.view.animation.LinearInterpolator r12 = new android.view.animation.LinearInterpolator
            r4 = r12
            r12.<init>()
            goto L_0x0007
        L_0x003a:
            java.lang.String r11 = "accelerateInterpolator"
            boolean r10 = r9.equals(r11)
            if (r10 == 0) goto L_0x004b
            android.view.animation.AccelerateInterpolator r13 = new android.view.animation.AccelerateInterpolator
            r4 = r13
            r0 = r25
            r13.<init>(r0, r8)
        L_0x004a:
            goto L_0x0007
        L_0x004b:
            java.lang.String r11 = "decelerateInterpolator"
            boolean r10 = r9.equals(r11)
            if (r10 == 0) goto L_0x005c
            android.view.animation.DecelerateInterpolator r14 = new android.view.animation.DecelerateInterpolator
            r4 = r14
            r0 = r25
            r14.<init>(r0, r8)
            goto L_0x004a
        L_0x005c:
            java.lang.String r11 = "accelerateDecelerateInterpolator"
            boolean r10 = r9.equals(r11)
            if (r10 == 0) goto L_0x006c
            android.view.animation.AccelerateDecelerateInterpolator r15 = new android.view.animation.AccelerateDecelerateInterpolator
            r4 = r15
            r15.<init>()
            goto L_0x0007
        L_0x006c:
            java.lang.String r11 = "cycleInterpolator"
            boolean r10 = r9.equals(r11)
            if (r10 == 0) goto L_0x0080
            android.view.animation.CycleInterpolator r16 = new android.view.animation.CycleInterpolator
            r4 = r16
            r0 = r16
            r1 = r25
            r0.<init>(r1, r8)
            goto L_0x004a
        L_0x0080:
            java.lang.String r11 = "anticipateInterpolator"
            boolean r10 = r9.equals(r11)
            if (r10 == 0) goto L_0x0094
            android.view.animation.AnticipateInterpolator r17 = new android.view.animation.AnticipateInterpolator
            r4 = r17
            r0 = r17
            r1 = r25
            r0.<init>(r1, r8)
            goto L_0x004a
        L_0x0094:
            java.lang.String r11 = "overshootInterpolator"
            boolean r10 = r9.equals(r11)
            if (r10 == 0) goto L_0x00a8
            android.view.animation.OvershootInterpolator r18 = new android.view.animation.OvershootInterpolator
            r4 = r18
            r0 = r18
            r1 = r25
            r0.<init>(r1, r8)
            goto L_0x004a
        L_0x00a8:
            java.lang.String r11 = "anticipateOvershootInterpolator"
            boolean r10 = r9.equals(r11)
            if (r10 == 0) goto L_0x00bc
            android.view.animation.AnticipateOvershootInterpolator r19 = new android.view.animation.AnticipateOvershootInterpolator
            r4 = r19
            r0 = r19
            r1 = r25
            r0.<init>(r1, r8)
            goto L_0x004a
        L_0x00bc:
            java.lang.String r11 = "bounceInterpolator"
            boolean r10 = r9.equals(r11)
            if (r10 == 0) goto L_0x00d0
            android.view.animation.BounceInterpolator r20 = new android.view.animation.BounceInterpolator
            r4 = r20
            r0 = r20
            r0.<init>()
            goto L_0x0007
        L_0x00d0:
            java.lang.String r11 = "pathInterpolator"
            boolean r10 = r9.equals(r11)
            if (r10 == 0) goto L_0x00e8
            com.org.shortcuts.drawable.PathInterpolatorDonut r21 = new com.org.shortcuts.drawable.PathInterpolatorDonut
            r4 = r21
            r0 = r21
            r1 = r25
            r2 = r28
            r0.<init>(r1, r8, r2)
            goto L_0x004a
        L_0x00e8:
            java.lang.RuntimeException r22 = new java.lang.RuntimeException
            java.lang.StringBuilder r23 = new java.lang.StringBuilder
            r24 = r23
            r0 = r23
            r0.<init>()
            java.lang.String r11 = "Unknown interpolator name: "
            r0 = r24
            r0.append(r11)
            r0 = r28
            java.lang.String r9 = r0.getName()
            r0 = r24
            r0.append(r9)
            r0 = r24
            java.lang.String r9 = r0.toString()
            r0 = r22
            r0.<init>(r9)
            throw r22
        L_0x0111:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.org.shortcuts.drawable.ArgbEvaluator.createFromXml(android.content.Context, android.content.res.Resources, android.content.res.Resources$Theme, org.xmlpull.v1.XmlPullParser):android.view.animation.Interpolator");
    }

    public static Interpolator loadAnimator(Context context, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            return AnimationUtils.loadInterpolator(context, i);
        }
        XmlResourceParser $r2 = null;
        if (i == 17563663) {
            try {
                return new FastOutLinearInInterpolator();
            } catch (XmlPullParserException $r6) {
                Resources.NotFoundException $r12 = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i));
                $r12.initCause($r6);
                throw $r12;
            } catch (IOException $r5) {
                Resources.NotFoundException $r122 = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i));
                $r122.initCause($r5);
                throw $r122;
            } catch (Throwable $r4) {
                if ($r2 != null) {
                    $r2.close();
                }
                throw $r4;
            }
        } else if (i == 17563661) {
            return new FastOutSlowInInterpolator();
        } else {
            if (i == 17563662) {
                return new LinearOutSlowInInterpolator();
            }
            XmlResourceParser $r10 = context.getResources().getAnimation(i);
            $r2 = $r10;
            Interpolator $r1 = createFromXml(context, context.getResources(), context.getTheme(), $r10);
            if ($r10 == null) {
                return $r1;
            }
            $r10.close();
            return $r1;
        }
    }
}
