package lombok.libs.org.objectweb.asm;

import android.content.res.AssetFileDescriptor;
import b.a.a.a.a.b;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.zip.ZipFile;

public class ByteVector {
    ByteBuffer buffer = ByteBuffer.allocate(4);
    public HashMap<File, ZipFile> data = new HashMap();
    private HashMap<String, b.a> header = new HashMap();

    public ByteVector(String str) {
        read(str);
    }

    private static int read(int i) {
        return ((i & 255) << 24) + ((65280 & i) << 8) + ((16711680 & i) >>> 8) + ((i >>> 24) & 255);
    }

    private static int read(RandomAccessFile randomAccessFile) {
        return read(randomAccessFile.readInt());
    }

    public AssetFileDescriptor a(String str) {
        a $r4 = this.header.get(str);
        if ($r4 != null) {
            return $r4.a();
        }
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v5, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v6, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v9, resolved type: short} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void read(java.lang.String r43) {
        /*
            r42 = this;
            r6 = r43
            java.io.File r7 = new java.io.File
            r0 = r43
            r7.<init>(r0)
            java.io.RandomAccessFile r8 = new java.io.RandomAccessFile
            java.lang.String r9 = "r"
            r8.<init>(r7, r9)
            long r10 = r8.length()
            r13 = 22
            int r12 = (r10 > r13 ? 1 : (r10 == r13 ? 0 : -1))
            if (r12 < 0) goto L_0x0314
            r15 = 65557(0x10015, double:3.23895E-319)
            r13 = 65557(0x10015, double:3.23895E-319)
            int r12 = (r13 > r10 ? 1 : (r13 == r10 ? 0 : -1))
            if (r12 <= 0) goto L_0x0026
            r15 = r10
        L_0x0026:
            r13 = 0
            r8.seek(r13)
            int r17 = read((java.io.RandomAccessFile) r8)
            r18 = 101010256(0x6054b50, float:2.506985E-35)
            r0 = r17
            r1 = r18
            if (r0 == r1) goto L_0x0303
            r18 = 67324752(0x4034b50, float:1.5433558E-36)
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x02f2
            long r19 = r10 - r15
            r0 = r19
            r8.seek(r0)
            r0 = r15
            int r0 = (int) r0
            r17 = r0
            java.nio.ByteBuffer r21 = java.nio.ByteBuffer.allocate(r0)
            r0 = r21
            byte[] r22 = r0.array()
            r0 = r22
            r8.readFully(r0)
            java.nio.ByteOrder r23 = java.nio.ByteOrder.LITTLE_ENDIAN
            r0 = r21
            r1 = r23
            r0.order(r1)
            r0 = r22
            int r0 = r0.length
            r17 = r0
            int r17 = r17 + -22
        L_0x006c:
            if (r17 < 0) goto L_0x008b
            byte r12 = r22[r17]
            r18 = 80
            r0 = r18
            if (r12 != r0) goto L_0x0088
            r0 = r21
            r1 = r17
            int r24 = r0.getInt(r1)
            r18 = 101010256(0x6054b50, float:2.506985E-35)
            r0 = r24
            r1 = r18
            if (r0 != r1) goto L_0x0088
            goto L_0x008b
        L_0x0088:
            int r17 = r17 + -1
            goto L_0x006c
        L_0x008b:
            if (r17 >= 0) goto L_0x00b6
            java.lang.StringBuilder r25 = new java.lang.StringBuilder
            r0 = r25
            r0.<init>()
            java.lang.String r9 = "Zip: EOCD not found, "
            r0 = r25
            r0.append(r9)
            r0 = r25
            r1 = r43
            r0.append(r1)
            java.lang.String r9 = " is not zip"
            r0 = r25
            r0.append(r9)
            r0 = r25
            java.lang.String r26 = r0.toString()
            java.lang.String r9 = "zipro"
            r0 = r26
            android.util.Log.d(r9, r0)
        L_0x00b6:
            int r24 = r17 + 8
            r0 = r21
            r1 = r24
            short r27 = r0.getShort(r1)
            int r24 = r17 + 12
            r0 = r21
            r1 = r24
            int r24 = r0.getInt(r1)
            r0 = r24
            long r0 = (long) r0
            r15 = r0
            r13 = 4294967295(0xffffffff, double:2.1219957905E-314)
            r0 = r15
            long r0 = r0 & r13
            r15 = r0
            int r24 = r17 + 16
            r0 = r21
            r1 = r24
            int r24 = r0.getInt(r1)
            r0 = r24
            long r0 = (long) r0
            r19 = r0
            r13 = 4294967295(0xffffffff, double:2.1219957905E-314)
            r0 = r19
            long r0 = r0 & r13
            r19 = r0
            long r28 = r19 + r15
            int r12 = (r28 > r10 ? 1 : (r28 == r10 ? 0 : -1))
            if (r12 > 0) goto L_0x02a5
            if (r27 == 0) goto L_0x0294
            java.nio.channels.FileChannel r30 = r8.getChannel()
            java.nio.channels.FileChannel$MapMode r31 = java.nio.channels.FileChannel.MapMode.READ_ONLY
            r0 = r30
            r1 = r31
            r2 = r19
            r4 = r15
            java.nio.MappedByteBuffer r32 = r0.map(r1, r2, r4)
            java.nio.ByteOrder r23 = java.nio.ByteOrder.LITTLE_ENDIAN
            r0 = r32
            r1 = r23
            r0.order(r1)
            r18 = 65535(0xffff, float:9.1834E-41)
            r0 = r18
            byte[] r0 = new byte[r0]
            r22 = r0
            r18 = 30
            r0 = r18
            java.nio.ByteBuffer r21 = java.nio.ByteBuffer.allocate(r0)
            java.nio.ByteOrder r23 = java.nio.ByteOrder.LITTLE_ENDIAN
            r0 = r21
            r1 = r23
            r0.order(r1)
            r17 = 0
            r24 = 0
        L_0x0133:
            r0 = r17
            r1 = r27
            if (r0 >= r1) goto L_0x0293
            r0 = r32
            r1 = r24
            int r33 = r0.getInt(r1)
            r18 = 33639248(0x2014b50, float:9.499037E-38)
            r0 = r33
            r1 = r18
            if (r0 != r1) goto L_0x0262
            int r33 = r24 + 28
            r0 = r32
            r1 = r33
            short r34 = r0.getShort(r1)
            r18 = 65535(0xffff, float:9.1834E-41)
            r33 = r34 & r18
            int r35 = r24 + 30
            r0 = r32
            r1 = r35
            short r34 = r0.getShort(r1)
            r18 = 65535(0xffff, float:9.1834E-41)
            r35 = r34 & r18
            int r36 = r24 + 32
            r0 = r32
            r1 = r36
            short r34 = r0.getShort(r1)
            r18 = 65535(0xffff, float:9.1834E-41)
            r36 = r34 & r18
            int r37 = r24 + 46
            r0 = r32
            r1 = r37
            r0.position(r1)
            r18 = 0
            r0 = r32
            r1 = r22
            r2 = r18
            r3 = r33
            r0.get(r1, r2, r3)
            r18 = 0
            r0 = r32
            r1 = r18
            r0.position(r1)
            java.lang.String r26 = new java.lang.String
            r18 = 0
            r0 = r26
            r1 = r22
            r2 = r18
            r3 = r33
            r0.<init>(r1, r2, r3)
            lombok.libs.org.objectweb.asm.a r38 = new lombok.libs.org.objectweb.asm.a
            r0 = r38
            r1 = r26
            r0.<init>(r6, r7, r1)
            int r37 = r24 + 10
            r0 = r32
            r1 = r37
            short r34 = r0.getShort(r1)
            r18 = 65535(0xffff, float:9.1834E-41)
            r37 = r34 & r18
            r0 = r37
            r1 = r38
            r1.b = r0
            int r37 = r24 + 12
            r0 = r32
            r1 = r37
            int r37 = r0.getInt(r1)
            r0 = r37
            long r10 = (long) r0
            r13 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r10 = r10 & r13
            r0 = r38
            r0.n = r10
            int r37 = r24 + 16
            r0 = r32
            r1 = r37
            long r10 = r0.getLong(r1)
            r13 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r10 = r10 & r13
            r0 = r38
            r0.s = r10
            int r37 = r24 + 20
            r0 = r32
            r1 = r37
            long r10 = r0.getLong(r1)
            r13 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r10 = r10 & r13
            r0 = r38
            r0.i = r10
            int r37 = r24 + 24
            r0 = r32
            r1 = r37
            long r10 = r0.getLong(r1)
            r13 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r10 = r10 & r13
            r0 = r38
            r0.a = r10
            int r37 = r24 + 42
            r0 = r32
            r1 = r37
            int r37 = r0.getInt(r1)
            r0 = r37
            long r10 = (long) r0
            r13 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r10 = r10 & r13
            r0 = r38
            r0.p = r10
            r0 = r21
            r0.clear()
            r0 = r38
            r1 = r21
            r0.read(r8, r1)
            r0 = r42
            java.util.HashMap<java.lang.String, b.a.a.a.a.b$a> r0 = r0.header
            r39 = r0
            r1 = r26
            r2 = r38
            r0.put(r1, r2)
            int r33 = r33 + 46
            r0 = r33
            r1 = r35
            int r0 = r0 + r1
            r33 = r0
            r1 = r36
            int r0 = r0 + r1
            r33 = r0
            r0 = r24
            r1 = r33
            int r0 = r0 + r1
            r24 = r0
            int r17 = r17 + 1
            r6 = r43
            goto L_0x0133
        L_0x0262:
            java.lang.StringBuilder r25 = new java.lang.StringBuilder
            r0 = r25
            r0.<init>()
            java.lang.String r9 = "Missed a central dir sig (at "
            r0 = r25
            r0.append(r9)
            r0 = r25
            r1 = r24
            r0.append(r1)
            java.lang.String r9 = ")"
            r0 = r25
            r0.append(r9)
            r0 = r25
            java.lang.String r43 = r0.toString()
            java.lang.String r9 = "zipro"
            r0 = r43
            android.util.Log.w(r9, r0)
            java.io.IOException r40 = new java.io.IOException
            r0 = r40
            r0.<init>()
            throw r40
        L_0x0293:
            return
        L_0x0294:
            java.lang.String r9 = "zipro"
            java.lang.String r41 = "empty archive?"
            r0 = r41
            android.util.Log.w(r9, r0)
            java.io.IOException r40 = new java.io.IOException
            r0 = r40
            r0.<init>()
            throw r40
        L_0x02a5:
            java.lang.StringBuilder r25 = new java.lang.StringBuilder
            r0 = r25
            r0.<init>()
            java.lang.String r9 = "bad offsets (dir "
            r0 = r25
            r0.append(r9)
            r0 = r25
            r1 = r19
            r0.append(r1)
            java.lang.String r9 = ", size "
            r0 = r25
            r0.append(r9)
            r0 = r25
            r1 = r15
            r0.append(r1)
            java.lang.String r9 = ", eocd "
            r0 = r25
            r0.append(r9)
            r0 = r25
            r1 = r17
            r0.append(r1)
            java.lang.String r9 = ")"
            r0 = r25
            r0.append(r9)
            r0 = r25
            java.lang.String r43 = r0.toString()
            java.lang.String r9 = "zipro"
            r0 = r43
            android.util.Log.w(r9, r0)
            java.io.IOException r40 = new java.io.IOException
            r0 = r40
            r0.<init>()
            throw r40
        L_0x02f2:
            java.lang.String r9 = "zipro"
            java.lang.String r41 = "Not a Zip archive"
            r0 = r41
            android.util.Log.v(r9, r0)
            java.io.IOException r40 = new java.io.IOException
            r0 = r40
            r0.<init>()
            throw r40
        L_0x0303:
            java.lang.String r9 = "zipro"
            java.lang.String r41 = "Found Zip archive, but it looks empty"
            r0 = r41
            android.util.Log.i(r9, r0)
            java.io.IOException r40 = new java.io.IOException
            r0 = r40
            r0.<init>()
            throw r40
        L_0x0314:
            java.io.IOException r40 = new java.io.IOException
            r0 = r40
            r0.<init>()
            goto L_0x031c
        L_0x031c:
            throw r40
        */
        throw new UnsupportedOperationException("Method not decompiled: lombok.libs.org.objectweb.asm.ByteVector.read(java.lang.String):void");
    }
}
