package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.k */
class C0986k implements Runnable {

    /* renamed from: a */
    final /* synthetic */ String f2668a;

    /* renamed from: b */
    final /* synthetic */ Cocos2dxDownloader f2669b;

    /* renamed from: c */
    final /* synthetic */ int f2670c;

    /* renamed from: d */
    final /* synthetic */ String f2671d;

    C0986k(String str, Cocos2dxDownloader cocos2dxDownloader, int i, String str2) {
        this.f2668a = str;
        this.f2669b = cocos2dxDownloader;
        this.f2670c = i;
        this.f2671d = str2;
    }

    /* JADX WARNING: type inference failed for: r1v20, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r10 = this;
            org.cocos2dx.lib.Ra r0 = new org.cocos2dx.lib.Ra
            r0.<init>()
            java.lang.String r1 = r10.f2668a
            int r1 = r1.length()
            if (r1 != 0) goto L_0x002c
            org.cocos2dx.lib.Qa r1 = new org.cocos2dx.lib.Qa
            org.cocos2dx.lib.Cocos2dxDownloader r2 = r10.f2669b
            int r3 = r10.f2670c
            r1.<init>(r2, r3)
            r0.f2575b = r1
            org.cocos2dx.lib.Cocos2dxDownloader r1 = r10.f2669b
            b.c.a.a.d r1 = r1._httpClient
            android.app.Activity r2 = org.cocos2dx.lib.Cocos2dxHelper.getActivity()
            java.lang.String r3 = r10.f2671d
            b.c.a.a.f r4 = r0.f2575b
            b.c.a.a.q r1 = r1.mo2356a((android.content.Context) r2, (java.lang.String) r3, (p026b.p033c.p034a.p035a.C0482s) r4)
            r0.f2574a = r1
        L_0x002c:
            java.lang.String r1 = r10.f2668a
            int r1 = r1.length()
            if (r1 != 0) goto L_0x0036
            goto L_0x015b
        L_0x0036:
            java.net.URI r1 = new java.net.URI     // Catch:{ URISyntaxException -> 0x015a }
            java.lang.String r2 = r10.f2671d     // Catch:{ URISyntaxException -> 0x015a }
            r1.<init>(r2)     // Catch:{ URISyntaxException -> 0x015a }
            java.lang.String r1 = r1.getHost()     // Catch:{ URISyntaxException -> 0x015a }
            java.lang.String r2 = "www."
            boolean r2 = r1.startsWith(r2)
            if (r2 == 0) goto L_0x004e
            r2 = 4
            java.lang.String r1 = r1.substring(r2)
        L_0x004e:
            r5 = r1
            r1 = 0
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r1)
            r3 = 1
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
            java.util.HashMap r4 = org.cocos2dx.lib.Cocos2dxDownloader._resumingSupport
            boolean r4 = r4.containsKey(r5)
            if (r4 == 0) goto L_0x0071
            java.util.HashMap r2 = org.cocos2dx.lib.Cocos2dxDownloader._resumingSupport
            java.lang.Object r2 = r2.get(r5)
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r1)
        L_0x0071:
            boolean r1 = r3.booleanValue()
            if (r1 == 0) goto L_0x009f
            org.cocos2dx.lib.Xa r1 = new org.cocos2dx.lib.Xa
            org.cocos2dx.lib.Cocos2dxDownloader r3 = r10.f2669b
            int r4 = r10.f2670c
            java.lang.String r6 = r10.f2671d
            java.lang.String r7 = r10.f2668a
            r2 = r1
            r2.<init>(r3, r4, r5, r6, r7)
            r0.f2575b = r1
            org.cocos2dx.lib.Cocos2dxDownloader r1 = r10.f2669b
            b.c.a.a.d r2 = r1._httpClient
            android.app.Activity r3 = org.cocos2dx.lib.Cocos2dxHelper.getActivity()
            java.lang.String r4 = r10.f2671d
            r5 = 0
            r6 = 0
            b.c.a.a.f r7 = r0.f2575b
            b.c.a.a.q r1 = r2.mo2363b(r3, r4, r5, r6, r7)
        L_0x009b:
            r0.f2574a = r1
            goto L_0x015b
        L_0x009f:
            java.io.File r1 = new java.io.File
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = r10.f2668a
            r3.append(r4)
            org.cocos2dx.lib.Cocos2dxDownloader r4 = r10.f2669b
            java.lang.String r4 = r4._tempFileNameSufix
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r1.<init>(r3)
            boolean r3 = r1.isDirectory()
            if (r3 == 0) goto L_0x00c3
            goto L_0x015b
        L_0x00c3:
            java.io.File r3 = r1.getParentFile()
            boolean r4 = r3.isDirectory()
            if (r4 != 0) goto L_0x00d5
            boolean r3 = r3.mkdirs()
            if (r3 != 0) goto L_0x00d5
            goto L_0x015b
        L_0x00d5:
            java.io.File r3 = new java.io.File
            java.lang.String r4 = r10.f2668a
            r3.<init>(r4)
            boolean r4 = r3.isDirectory()
            if (r4 == 0) goto L_0x00e4
            goto L_0x015b
        L_0x00e4:
            org.cocos2dx.lib.Sa r4 = new org.cocos2dx.lib.Sa
            org.cocos2dx.lib.Cocos2dxDownloader r5 = r10.f2669b
            int r6 = r10.f2670c
            r4.<init>(r5, r6, r1, r3)
            r0.f2575b = r4
            r3 = 0
            long r4 = r1.length()
            boolean r2 = r2.booleanValue()
            r6 = 0
            if (r2 == 0) goto L_0x0133
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 <= 0) goto L_0x0133
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            c.a.a.a.k.b r2 = new c.a.a.a.k.b
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r6 = "bytes="
            r3.append(r6)
            r3.append(r4)
            java.lang.String r4 = "-"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "Range"
            r2.<init>(r4, r3)
            r1.add(r2)
            int r2 = r1.size()
            c.a.a.a.e[] r2 = new p036c.p037a.p038a.p039a.C0576e[r2]
            java.lang.Object[] r1 = r1.toArray(r2)
            r3 = r1
            c.a.a.a.e[] r3 = (p036c.p037a.p038a.p039a.C0576e[]) r3
            goto L_0x0144
        L_0x0133:
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 <= 0) goto L_0x0144
            java.io.PrintWriter r2 = new java.io.PrintWriter     // Catch:{ FileNotFoundException -> 0x0144 }
            r2.<init>(r1)     // Catch:{ FileNotFoundException -> 0x0144 }
            java.lang.String r1 = ""
            r2.print(r1)     // Catch:{ FileNotFoundException -> 0x0144 }
            r2.close()     // Catch:{ FileNotFoundException -> 0x0144 }
        L_0x0144:
            r7 = r3
            org.cocos2dx.lib.Cocos2dxDownloader r1 = r10.f2669b
            b.c.a.a.d r4 = r1._httpClient
            android.app.Activity r5 = org.cocos2dx.lib.Cocos2dxHelper.getActivity()
            java.lang.String r6 = r10.f2671d
            r8 = 0
            b.c.a.a.f r9 = r0.f2575b
            b.c.a.a.q r1 = r4.mo2357a(r5, r6, r7, r8, r9)
            goto L_0x009b
        L_0x015a:
        L_0x015b:
            b.c.a.a.q r1 = r0.f2574a
            if (r1 != 0) goto L_0x017b
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Can't create DownloadTask for "
            r0.append(r1)
            java.lang.String r1 = r10.f2671d
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            org.cocos2dx.lib.j r1 = new org.cocos2dx.lib.j
            r1.<init>(r10, r0)
            org.cocos2dx.lib.Cocos2dxHelper.runOnGLThread(r1)
            goto L_0x018a
        L_0x017b:
            org.cocos2dx.lib.Cocos2dxDownloader r1 = r10.f2669b
            java.util.HashMap r1 = r1._taskMap
            int r2 = r10.f2670c
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r1.put(r2, r0)
        L_0x018a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.cocos2dx.lib.C0986k.run():void");
    }
}
