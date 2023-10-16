package com.org.android.manager;

import java.util.Comparator;

final class Version$BuildAwareOrder implements Comparator<byte[]> {
    Version$BuildAwareOrder() {
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int compare(byte[] r6, byte[] r7) {
        /*
            r5 = this;
            int r0 = r6.length
            int r1 = r7.length
            if (r0 == r1) goto L_0x0008
            int r0 = r6.length
            int r1 = r7.length
        L_0x0006:
            int r0 = r0 - r1
            return r0
        L_0x0008:
            r1 = 0
        L_0x0009:
            int r0 = r6.length
            if (r1 >= r0) goto L_0x001a
            byte r2 = r6[r1]
            byte r3 = r7[r1]
            if (r2 == r3) goto L_0x0017
            byte r0 = r6[r1]
            byte r1 = r7[r1]
            goto L_0x0006
        L_0x0017:
            int r1 = r1 + 1
            goto L_0x0009
        L_0x001a:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.org.android.manager.Version$BuildAwareOrder.compare(byte[], byte[]):int");
    }
}
