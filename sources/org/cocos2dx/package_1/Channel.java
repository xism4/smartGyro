package org.cocos2dx.package_1;

final class Channel implements Runnable {
    final /* synthetic */ int d;
    final /* synthetic */ String id;
    final /* synthetic */ String image;
    final /* synthetic */ Cocos2dxDownloader this$0;

    Channel(String str, Cocos2dxDownloader cocos2dxDownloader, int i, String str2) {
        this.image = str;
        this.this$0 = cocos2dxDownloader;
        this.d = i;
        this.id = str2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v0, resolved type: java.lang.Boolean} */
    /* JADX WARNING: type inference failed for: r41v0, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r48 = this;
            org.cocos2dx.package_1.Page r6 = new org.cocos2dx.package_1.Page
            r6.<init>()
            r0 = r48
            java.lang.String r7 = r0.image
            int r8 = r7.length()
            if (r8 != 0) goto L_0x0036
            org.cocos2dx.package_1.BinDecorator r9 = new org.cocos2dx.package_1.BinDecorator
            r0 = r48
            org.cocos2dx.package_1.Cocos2dxDownloader r10 = r0.this$0
            r0 = r48
            int r8 = r0.d
            r9.<init>(r10, r8)
            r6.name = r9
            r0 = r48
            org.cocos2dx.package_1.Cocos2dxDownloader r10 = r0.this$0
            lombok.eclipse.handlers.http.AsyncHttpClient r11 = r10._httpClient
            android.app.Activity r12 = org.cocos2dx.package_1.Cocos2dxHelper.getActivity()
            r0 = r48
            java.lang.String r7 = r0.id
            lombok.eclipse.handlers.http.AsyncHttpResponseHandler r13 = r6.name
            lombok.eclipse.handlers.http.RequestHandle r14 = r11.get(r12, r7, r13)
            r6.status = r14
        L_0x0036:
            r0 = r48
            java.lang.String r7 = r0.image
            int r8 = r7.length()
            if (r8 != 0) goto L_0x0043
            goto L_0x021c
        L_0x0043:
            java.net.URI r15 = new java.net.URI
            r0 = r48
            java.lang.String r7 = r0.id
            r15.<init>(r7)     // Catch:{ URISyntaxException -> 0x0266 }
            java.lang.String r7 = r15.getHost()     // Catch:{ URISyntaxException -> 0x0266 }
            r16 = r7
            java.lang.String r18 = "www."
            r0 = r18
            boolean r17 = r7.startsWith(r0)
            if (r17 == 0) goto L_0x0064
            r19 = 4
            r0 = r19
            java.lang.String r16 = r7.substring(r0)
        L_0x0064:
            r7 = r16
            r19 = 0
            r0 = r19
            java.lang.Boolean r20 = java.lang.Boolean.valueOf(r0)
            r19 = 1
            r0 = r19
            java.lang.Boolean r21 = java.lang.Boolean.valueOf(r0)
            java.util.HashMap r22 = org.cocos2dx.package_1.Cocos2dxDownloader._resumingSupport
            r0 = r22
            r1 = r16
            boolean r17 = r0.containsKey(r1)
            if (r17 == 0) goto L_0x009e
            java.util.HashMap r22 = org.cocos2dx.package_1.Cocos2dxDownloader._resumingSupport
            r0 = r22
            r1 = r16
            java.lang.Object r23 = r0.get(r1)
            r24 = r23
            java.lang.Boolean r24 = (java.lang.Boolean) r24
            r20 = r24
            r19 = 0
            r0 = r19
            java.lang.Boolean r21 = java.lang.Boolean.valueOf(r0)
        L_0x009e:
            r0 = r21
            boolean r17 = r0.booleanValue()
            if (r17 == 0) goto L_0x00f3
            org.cocos2dx.package_1.a r25 = new org.cocos2dx.package_1.a
            r0 = r48
            org.cocos2dx.package_1.Cocos2dxDownloader r10 = r0.this$0
            r0 = r48
            int r8 = r0.d
            r0 = r48
            java.lang.String r0 = r0.id
            r16 = r0
            r0 = r48
            java.lang.String r0 = r0.image
            r26 = r0
            r0 = r25
            r1 = r10
            r2 = r8
            r3 = r7
            r4 = r16
            r5 = r26
            r0.<init>(r1, r2, r3, r4, r5)
            r0 = r25
            r6.name = r0
            r0 = r48
            org.cocos2dx.package_1.Cocos2dxDownloader r10 = r0.this$0
            lombok.eclipse.handlers.http.AsyncHttpClient r11 = r10._httpClient
            android.app.Activity r12 = org.cocos2dx.package_1.Cocos2dxHelper.getActivity()
            r0 = r48
            java.lang.String r7 = r0.id
            lombok.eclipse.handlers.http.AsyncHttpResponseHandler r13 = r6.name
            r27 = 0
            r28 = 0
            r0 = r11
            r1 = r12
            r2 = r7
            r3 = r27
            r4 = r28
            r5 = r13
            lombok.eclipse.handlers.http.RequestHandle r14 = r0.delete(r1, r2, r3, r4, r5)
        L_0x00ee:
            r6.status = r14
            goto L_0x021c
        L_0x00f3:
            java.io.File r29 = new java.io.File
            java.lang.StringBuilder r30 = new java.lang.StringBuilder
            r0 = r30
            r0.<init>()
            r0 = r48
            java.lang.String r7 = r0.image
            r0 = r30
            r0.append(r7)
            r0 = r48
            org.cocos2dx.package_1.Cocos2dxDownloader r10 = r0.this$0
            java.lang.String r7 = r10._tempFileNameSufix
            r0 = r30
            r0.append(r7)
            r0 = r30
            java.lang.String r7 = r0.toString()
            r0 = r29
            r0.<init>(r7)
            r0 = r29
            boolean r17 = r0.isDirectory()
            if (r17 == 0) goto L_0x0128
            goto L_0x021c
        L_0x0128:
            r0 = r29
            java.io.File r31 = r0.getParentFile()
            r0 = r31
            boolean r17 = r0.isDirectory()
            if (r17 != 0) goto L_0x0141
            r0 = r31
            boolean r17 = r0.mkdirs()
            if (r17 != 0) goto L_0x0141
            goto L_0x021c
        L_0x0141:
            java.io.File r31 = new java.io.File
            r0 = r48
            java.lang.String r7 = r0.image
            r0 = r31
            r0.<init>(r7)
            r0 = r31
            boolean r17 = r0.isDirectory()
            if (r17 == 0) goto L_0x0157
            goto L_0x021c
        L_0x0157:
            org.cocos2dx.package_1.ByteVector r32 = new org.cocos2dx.package_1.ByteVector
            r0 = r48
            org.cocos2dx.package_1.Cocos2dxDownloader r10 = r0.this$0
            r0 = r48
            int r8 = r0.d
            r0 = r32
            r1 = r29
            r2 = r31
            r0.<init>(r10, r8, r1, r2)
            r0 = r32
            r6.name = r0
            r33 = 0
            r0 = r29
            long r34 = r0.length()
            r0 = r20
            boolean r17 = r0.booleanValue()
            if (r17 == 0) goto L_0x01dc
            r37 = 0
            int r36 = (r34 > r37 ? 1 : (r34 == r37 ? 0 : -1))
            if (r36 <= 0) goto L_0x01dc
            java.util.ArrayList r39 = new java.util.ArrayList
            r0 = r39
            r0.<init>()
            cz.msebera.android.http.message.BasicHeader r40 = new cz.msebera.android.http.message.BasicHeader
            java.lang.StringBuilder r30 = new java.lang.StringBuilder
            r0 = r30
            r0.<init>()
            java.lang.String r18 = "bytes="
            r0 = r30
            r1 = r18
            r0.append(r1)
            r0 = r30
            r1 = r34
            r0.append(r1)
            java.lang.String r18 = "-"
            r0 = r30
            r1 = r18
            r0.append(r1)
            r0 = r30
            java.lang.String r7 = r0.toString()
            java.lang.String r18 = "Range"
            r0 = r40
            r1 = r18
            r0.<init>(r1, r7)
            r0 = r39
            r1 = r40
            r0.add(r1)
            r0 = r39
            int r8 = r0.size()
            cz.msebera.android.http.Header[] r0 = new cz.msebera.android.http.Header[r8]
            r33 = r0
            r0 = r39
            r1 = r33
            java.lang.Object[] r41 = r0.toArray(r1)
            r42 = r41
            cz.msebera.android.http.Header[] r42 = (cz.msebera.android.http.Header[]) r42
            r33 = r42
            goto L_0x01f9
        L_0x01dc:
            r37 = 0
            int r36 = (r34 > r37 ? 1 : (r34 == r37 ? 0 : -1))
            if (r36 <= 0) goto L_0x01f9
            java.io.PrintWriter r43 = new java.io.PrintWriter
            r0 = r43
            r1 = r29
            r0.<init>(r1)     // Catch:{ FileNotFoundException -> 0x0268 }
            java.lang.String r18 = ""
            r0 = r43
            r1 = r18
            r0.print(r1)     // Catch:{ FileNotFoundException -> 0x0268 }
            r0 = r43
            r0.close()     // Catch:{ FileNotFoundException -> 0x0268 }
        L_0x01f9:
            r0 = r48
            org.cocos2dx.package_1.Cocos2dxDownloader r10 = r0.this$0
            lombok.eclipse.handlers.http.AsyncHttpClient r11 = r10._httpClient
            android.app.Activity r12 = org.cocos2dx.package_1.Cocos2dxHelper.getActivity()
            r0 = r48
            java.lang.String r7 = r0.id
            lombok.eclipse.handlers.http.AsyncHttpResponseHandler r13 = r6.name
            r27 = 0
            r0 = r11
            r1 = r12
            r2 = r7
            r3 = r33
            r4 = r27
            r5 = r13
            lombok.eclipse.handlers.http.RequestHandle r14 = r0.get(r1, r2, r3, r4, r5)
            goto L_0x00ee
        L_0x021c:
            lombok.eclipse.handlers.http.RequestHandle r14 = r6.status
            if (r14 != 0) goto L_0x024e
            java.lang.StringBuilder r30 = new java.lang.StringBuilder
            r0 = r30
            r0.<init>()
            java.lang.String r18 = "Can't create DownloadTask for "
            r0 = r30
            r1 = r18
            r0.append(r1)
            r0 = r48
            java.lang.String r7 = r0.id
            r0 = r30
            r0.append(r7)
            r0 = r30
            java.lang.String r7 = r0.toString()
            org.cocos2dx.package_1.SelectAction r44 = new org.cocos2dx.package_1.SelectAction
            r0 = r44
            r1 = r48
            r0.<init>(r1, r7)
            r0 = r44
            org.cocos2dx.package_1.Cocos2dxHelper.runOnGLThread(r0)
            return
        L_0x024e:
            r0 = r48
            org.cocos2dx.package_1.Cocos2dxDownloader r10 = r0.this$0
            java.util.HashMap r22 = r10._taskMap
            r0 = r48
            int r8 = r0.d
            java.lang.Integer r45 = java.lang.Integer.valueOf(r8)
            r0 = r22
            r1 = r45
            r0.put(r1, r6)
            return
        L_0x0266:
            r46 = move-exception
            goto L_0x021c
        L_0x0268:
            r47 = move-exception
            goto L_0x01f9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.cocos2dx.package_1.Channel.run():void");
    }
}
