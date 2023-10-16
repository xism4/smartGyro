package p026b.p027a.p028a.p029a.p030a;

import android.content.res.AssetFileDescriptor;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.zip.ZipFile;

/* renamed from: b.a.a.a.a.b */
public class C0457b {

    /* renamed from: a */
    private HashMap<String, C0458a> f1672a = new HashMap<>();

    /* renamed from: b */
    public HashMap<File, ZipFile> f1673b = new HashMap<>();

    /* renamed from: c */
    ByteBuffer f1674c = ByteBuffer.allocate(4);

    /* renamed from: b.a.a.a.a.b$a */
    public static final class C0458a {

        /* renamed from: a */
        public final File f1675a;

        /* renamed from: b */
        public final String f1676b;

        /* renamed from: c */
        public final String f1677c;

        /* renamed from: d */
        public long f1678d;

        /* renamed from: e */
        public int f1679e;

        /* renamed from: f */
        public long f1680f;

        /* renamed from: g */
        public long f1681g;

        /* renamed from: h */
        public long f1682h;

        /* renamed from: i */
        public long f1683i;

        /* renamed from: j */
        public long f1684j = -1;

        public C0458a(String str, File file, String str2) {
            this.f1676b = str2;
            this.f1677c = str;
            this.f1675a = file;
        }

        /* renamed from: a */
        public AssetFileDescriptor mo2343a() {
            if (this.f1679e != 0) {
                return null;
            }
            try {
                return new AssetFileDescriptor(ParcelFileDescriptor.open(this.f1675a, 268435456), mo2345b(), this.f1683i);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }

        /* renamed from: a */
        public void mo2344a(RandomAccessFile randomAccessFile, ByteBuffer byteBuffer) {
            long j = this.f1678d;
            try {
                randomAccessFile.seek(j);
                randomAccessFile.readFully(byteBuffer.array());
                if (byteBuffer.getInt(0) == 67324752) {
                    this.f1684j = j + 30 + ((long) (byteBuffer.getShort(26) & 65535)) + ((long) (byteBuffer.getShort(28) & 65535));
                } else {
                    Log.w("zipro", "didn't find signature at start of lfh");
                    throw new IOException();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }

        /* renamed from: b */
        public long mo2345b() {
            return this.f1684j;
        }
    }

    public C0457b(String str) {
        mo2341a(str);
    }

    /* renamed from: a */
    private static int m1976a(int i) {
        return ((i & 255) << 24) + ((65280 & i) << 8) + ((16711680 & i) >>> 8) + ((i >>> 24) & 255);
    }

    /* renamed from: a */
    private static int m1977a(RandomAccessFile randomAccessFile) {
        return m1976a(randomAccessFile.readInt());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo2341a(String str) {
        String str2 = str;
        File file = new File(str2);
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        long length = randomAccessFile.length();
        if (length >= 22) {
            long j = 65557;
            if (65557 > length) {
                j = length;
            }
            randomAccessFile.seek(0);
            int a = m1977a(randomAccessFile);
            if (a == 101010256) {
                Log.i("zipro", "Found Zip archive, but it looks empty");
                throw new IOException();
            } else if (a == 67324752) {
                randomAccessFile.seek(length - j);
                ByteBuffer allocate = ByteBuffer.allocate((int) j);
                byte[] array = allocate.array();
                randomAccessFile.readFully(array);
                allocate.order(ByteOrder.LITTLE_ENDIAN);
                int length2 = array.length - 22;
                while (length2 >= 0 && (array[length2] != 80 || allocate.getInt(length2) != 101010256)) {
                    length2--;
                }
                if (length2 < 0) {
                    Log.d("zipro", "Zip: EOCD not found, " + str2 + " is not zip");
                }
                short s = allocate.getShort(length2 + 8);
                long j2 = ((long) allocate.getInt(length2 + 12)) & 4294967295L;
                long j3 = ((long) allocate.getInt(length2 + 16)) & 4294967295L;
                if (j3 + j2 > length) {
                    Log.w("zipro", "bad offsets (dir " + j3 + ", size " + j2 + ", eocd " + length2 + ")");
                    throw new IOException();
                } else if (s != 0) {
                    MappedByteBuffer map = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY, j3, j2);
                    map.order(ByteOrder.LITTLE_ENDIAN);
                    short s2 = 65535;
                    byte[] bArr = new byte[65535];
                    ByteBuffer allocate2 = ByteBuffer.allocate(30);
                    allocate2.order(ByteOrder.LITTLE_ENDIAN);
                    int i = 0;
                    int i2 = 0;
                    int i3 = 0;
                    while (i2 < s) {
                        if (map.getInt(i3) == 33639248) {
                            short s3 = map.getShort(i3 + 28) & s2;
                            short s4 = map.getShort(i3 + 30) & s2;
                            short s5 = map.getShort(i3 + 32) & s2;
                            map.position(i3 + 46);
                            map.get(bArr, i, s3);
                            map.position(i);
                            String str3 = new String(bArr, i, s3);
                            C0458a aVar = new C0458a(str2, file, str3);
                            aVar.f1679e = map.getShort(i3 + 10) & 65535;
                            aVar.f1680f = ((long) map.getInt(i3 + 12)) & 4294967295L;
                            aVar.f1681g = map.getLong(i3 + 16) & 4294967295L;
                            aVar.f1682h = map.getLong(i3 + 20) & 4294967295L;
                            aVar.f1683i = map.getLong(i3 + 24) & 4294967295L;
                            aVar.f1678d = ((long) map.getInt(i3 + 42)) & 4294967295L;
                            allocate2.clear();
                            aVar.mo2344a(randomAccessFile, allocate2);
                            this.f1672a.put(str3, aVar);
                            i3 += s3 + 46 + s4 + s5;
                            i2++;
                            str2 = str;
                            file = file;
                            s2 = 65535;
                            i = 0;
                        } else {
                            Log.w("zipro", "Missed a central dir sig (at " + i3 + ")");
                            throw new IOException();
                        }
                    }
                } else {
                    Log.w("zipro", "empty archive?");
                    throw new IOException();
                }
            } else {
                Log.v("zipro", "Not a Zip archive");
                throw new IOException();
            }
        } else {
            throw new IOException();
        }
    }

    /* renamed from: b */
    public AssetFileDescriptor mo2342b(String str) {
        C0458a aVar = this.f1672a.get(str);
        if (aVar != null) {
            return aVar.mo2343a();
        }
        return null;
    }
}
