package android.support.v4.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.Editable;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

class i implements ActionMode.Callback {
    private final TextView a;
    private Class b;
    private Method c;
    private boolean d;
    private boolean f = false;
    private final ActionMode.Callback mWrapped;

    i(ActionMode.Callback callback, TextView textView) {
        this.mWrapped = callback;
        this.a = textView;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00ce A[LOOP:1: B:42:0x00c4->B:44:0x00ce, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(android.view.Menu r35) {
        /*
            r34 = this;
            r0 = r34
            android.widget.TextView r4 = r0.a
            android.content.Context r5 = r4.getContext()
            android.content.pm.PackageManager r6 = r5.getPackageManager()
            r0 = r34
            boolean r7 = r0.f
            if (r7 != 0) goto L_0x004c
            r8 = 1
            r0 = r34
            r0.f = r8
            java.lang.String r10 = "com.android.internal.view.menu.MenuBuilder"
            java.lang.Class r9 = java.lang.Class.forName(r10)     // Catch:{ ClassNotFoundException -> 0x010f, NoSuchMethodException -> 0x0113 }
            r0 = r34
            r0.b = r9
            r0 = r34
            java.lang.Class r9 = r0.b
            r8 = 1
            java.lang.Class[] r11 = new java.lang.Class[r8]
            java.lang.Class r12 = java.lang.Integer.TYPE
            r8 = 0
            r11[r8] = r12
            java.lang.String r10 = "removeItemAt"
            java.lang.reflect.Method r13 = r9.getDeclaredMethod(r10, r11)     // Catch:{ ClassNotFoundException -> 0x010f, NoSuchMethodException -> 0x0113 }
            r0 = r34
            r0.c = r13
            r8 = 1
            r0 = r34
            r0.d = r8
            goto L_0x004c
        L_0x003d:
            r14 = 0
            r0 = r34
            r0.b = r14
            r14 = 0
            r0 = r34
            r0.c = r14
            r8 = 0
            r0 = r34
            r0.d = r8
        L_0x004c:
            r0 = r34
            boolean r7 = r0.d
            if (r7 == 0) goto L_0x0063
            r0 = r34
            java.lang.Class r9 = r0.b
            r0 = r35
            boolean r7 = r9.isInstance(r0)     // Catch:{ NoSuchMethodException -> 0x0117, IllegalAccessException -> 0x0119, InvocationTargetException -> 0x011b }
            if (r7 == 0) goto L_0x0063
            r0 = r34
            java.lang.reflect.Method r13 = r0.c
            goto L_0x0077
        L_0x0063:
            r0 = r35
            java.lang.Class r9 = r0.getClass()     // Catch:{ NoSuchMethodException -> 0x0117, IllegalAccessException -> 0x0119, InvocationTargetException -> 0x011b }
            r8 = 1
            java.lang.Class[] r11 = new java.lang.Class[r8]
            java.lang.Class r12 = java.lang.Integer.TYPE
            r8 = 0
            r11[r8] = r12
            java.lang.String r10 = "removeItemAt"
            java.lang.reflect.Method r13 = r9.getDeclaredMethod(r10, r11)     // Catch:{ NoSuchMethodException -> 0x0117, IllegalAccessException -> 0x0119, InvocationTargetException -> 0x011b }
        L_0x0077:
            r0 = r35
            int r15 = r0.size()     // Catch:{ NoSuchMethodException -> 0x0117, IllegalAccessException -> 0x0119, InvocationTargetException -> 0x011b }
            r8 = 1
            int r15 = r15 - r8
        L_0x007f:
            if (r15 < 0) goto L_0x00bd
            r0 = r35
            android.view.MenuItem r16 = r0.getItem(r15)     // Catch:{ NoSuchMethodException -> 0x0117, IllegalAccessException -> 0x0119, InvocationTargetException -> 0x011b }
            r0 = r16
            android.content.Intent r17 = r0.getIntent()     // Catch:{ NoSuchMethodException -> 0x0117, IllegalAccessException -> 0x0119, InvocationTargetException -> 0x011b }
            if (r17 == 0) goto L_0x00ba
            java.lang.String r18 = "android.intent.action.PROCESS_TEXT"
            r0 = r16
            android.content.Intent r17 = r0.getIntent()     // Catch:{ NoSuchMethodException -> 0x0117, IllegalAccessException -> 0x0119, InvocationTargetException -> 0x011b }
            r0 = r17
            java.lang.String r19 = r0.getAction()     // Catch:{ NoSuchMethodException -> 0x0117, IllegalAccessException -> 0x0119, InvocationTargetException -> 0x011b }
            r0 = r18
            r1 = r19
            boolean r7 = r0.equals(r1)     // Catch:{ NoSuchMethodException -> 0x0117, IllegalAccessException -> 0x0119, InvocationTargetException -> 0x011b }
            if (r7 == 0) goto L_0x00ba
            r8 = 1
            java.lang.Object[] r0 = new java.lang.Object[r8]
            r20 = r0
            java.lang.Integer r21 = java.lang.Integer.valueOf(r15)     // Catch:{ NoSuchMethodException -> 0x0117, IllegalAccessException -> 0x0119, InvocationTargetException -> 0x011b }
            r8 = 0
            r20[r8] = r21
            r0 = r35
            r1 = r20
            r13.invoke(r0, r1)     // Catch:{ NoSuchMethodException -> 0x0117, IllegalAccessException -> 0x0119, InvocationTargetException -> 0x011b }
        L_0x00ba:
            int r15 = r15 + -1
            goto L_0x007f
        L_0x00bd:
            r0 = r34
            java.util.List r22 = r0.get(r5, r6)
            r15 = 0
        L_0x00c4:
            r0 = r22
            int r23 = r0.size()
            r0 = r23
            if (r15 >= r0) goto L_0x010e
            r0 = r22
            java.lang.Object r24 = r0.get(r15)
            r26 = r24
            android.content.pm.ResolveInfo r26 = (android.content.pm.ResolveInfo) r26
            r25 = r26
            int r23 = r15 + 100
            r0 = r25
            java.lang.CharSequence r27 = r0.loadLabel(r6)
            r8 = 0
            r28 = 0
            r0 = r35
            r1 = r28
            r2 = r23
            r3 = r27
            android.view.MenuItem r16 = r0.add(r8, r1, r2, r3)
            r0 = r34
            android.widget.TextView r4 = r0.a
            r0 = r34
            r1 = r25
            android.content.Intent r17 = r0.onClick(r1, r4)
            r0 = r16
            r1 = r17
            android.view.MenuItem r16 = r0.setIntent(r1)
            r8 = 1
            r0 = r16
            r0.setShowAsAction(r8)
            int r15 = r15 + 1
            goto L_0x00c4
        L_0x010e:
            return
        L_0x010f:
            r29 = move-exception
            goto L_0x003d
        L_0x0113:
            r30 = move-exception
            goto L_0x003d
        L_0x0117:
            r31 = move-exception
            return
        L_0x0119:
            r32 = move-exception
            return
        L_0x011b:
            r33 = move-exception
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.i.a(android.view.Menu):void");
    }

    private boolean a(TextView textView) {
        return (textView instanceof Editable) && textView.onCheckIsTextEditor() && textView.isEnabled();
    }

    private boolean add(ResolveInfo resolveInfo, Context context) {
        if (context.getPackageName().equals(resolveInfo.activityInfo.packageName)) {
            return true;
        }
        if (!resolveInfo.activityInfo.exported) {
            return false;
        }
        String $r3 = resolveInfo.activityInfo.permission;
        return $r3 == null || context.checkSelfPermission($r3) == 0;
    }

    private List get(Context context, PackageManager packageManager) {
        ArrayList $r2 = new ArrayList();
        if (!(context instanceof Activity)) {
            return $r2;
        }
        for (ResolveInfo $r8 : packageManager.queryIntentActivities(getIntent(), 0)) {
            if (add($r8, context)) {
                $r2.add($r8);
            }
        }
        return $r2;
    }

    private Intent getIntent() {
        return new Intent().setAction("android.intent.action.PROCESS_TEXT").setType("text/plain");
    }

    private Intent onClick(ResolveInfo resolveInfo, TextView textView) {
        return getIntent().putExtra("android.intent.extra.PROCESS_TEXT_READONLY", !a(textView)).setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
    }

    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        return this.mWrapped.onActionItemClicked(actionMode, menuItem);
    }

    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        return this.mWrapped.onCreateActionMode(actionMode, menu);
    }

    public void onDestroyActionMode(ActionMode actionMode) {
        this.mWrapped.onDestroyActionMode(actionMode);
    }

    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        a(menu);
        return this.mWrapped.onPrepareActionMode(actionMode, menu);
    }
}
