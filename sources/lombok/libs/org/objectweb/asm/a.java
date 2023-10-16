package lombok.libs.org.objectweb.asm;

import android.content.res.AssetFileDescriptor;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

public final class a {
    public long a;
    public int b;
    public long count = -1;
    public final String d;
    public final File f;
    public final String g;
    public long i;
    public long n;
    public long p;
    public long s;

    public a(String str, File file, String str2) {
        this.d = str2;
        this.g = str;
        this.f = file;
    }

    public AssetFileDescriptor a() {
        if (this.b != 0) {
            return null;
        }
        try {
            AssetFileDescriptor assetFileDescriptor = new AssetFileDescriptor(ParcelFileDescriptor.open(this.f, 268435456), read(), this.a);
            return assetFileDescriptor;
        } catch (FileNotFoundException $r4) {
            $r4.printStackTrace();
            return null;
        }
    }

    public long read() {
        return this.count;
    }

    public void read(RandomAccessFile randomAccessFile, ByteBuffer byteBuffer) {
        long $l0 = this.p;
        try {
            randomAccessFile.seek($l0);
            randomAccessFile.readFully(byteBuffer.array());
            if (byteBuffer.getInt(0) == 67324752) {
                this.count = $l0 + 30 + ((long) (byteBuffer.getShort(26) & 65535)) + ((long) (byteBuffer.getShort(28) & 65535));
                return;
            }
            Log.w("zipro", "didn't find signature at start of lfh");
            try {
                throw new IOException();
            } catch (IOException $r4) {
                $r4.printStackTrace();
            }
        } catch (FileNotFoundException $r5) {
            $r5.printStackTrace();
        }
    }
}
