package lombok.eclipse.handlers.http;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpEntity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public abstract class FileAsyncHttpResponseHandler extends AsyncHttpResponseHandler {
    protected final boolean append;
    protected final File file;
    protected File frontendFile;
    protected final boolean renameIfExists;

    public FileAsyncHttpResponseHandler(File file2, boolean z) {
        this(file2, z, false);
    }

    public FileAsyncHttpResponseHandler(File file2, boolean z, boolean z2) {
        this(file2, z, z2, false);
    }

    public FileAsyncHttpResponseHandler(File file2, boolean z, boolean z2, boolean z3) {
        super(z3);
        Utils.asserts(file2 != null, "File passed into FileAsyncHttpResponseHandler constructor must not be null");
        if (!file2.isDirectory() && !file2.getParentFile().isDirectory()) {
            Utils.asserts(file2.getParentFile().mkdirs(), "Cannot create parent directories for requested File location");
        }
        if (file2.isDirectory() && !file2.mkdirs()) {
            AsyncHttpClient.log.d("FileAsyncHttpRH", "Cannot create directories for requested Directory location, might not be a problem");
        }
        this.file = file2;
        this.append = z;
        this.renameIfExists = z2;
    }

    public abstract void b(int i, Header[] headerArr, File file2);

    public final void close(int i, Header[] headerArr, byte[] bArr, Throwable th) {
        write(i, headerArr, th, getTargetFile());
    }

    /* access modifiers changed from: protected */
    public File getOriginalFile() {
        Utils.asserts(this.file != null, "Target file is null, fatal!");
        return this.file;
    }

    /* access modifiers changed from: protected */
    public byte[] getResponseData(HttpEntity httpEntity) {
        if (httpEntity == null) {
            return null;
        }
        InputStream $r2 = httpEntity.getContent();
        long $l0 = httpEntity.getContentLength();
        FileOutputStream $r3 = new FileOutputStream(getTargetFile(), this.append);
        if ($r2 == null) {
            return null;
        }
        try {
            byte[] $r5 = new byte[4096];
            int $i1 = 0;
            while (true) {
                int $i2 = $r2.read($r5);
                if ($i2 != -1) {
                    if (Thread.currentThread().isInterrupted()) {
                        break;
                    }
                    $i1 += $i2;
                    $r3.write($r5, 0, $i2);
                    sendProgressMessage((long) $i1, $l0);
                } else {
                    break;
                }
            }
            return null;
        } finally {
            AsyncHttpClient.silentCloseInputStream($r2);
            $r3.flush();
            AsyncHttpClient.silentCloseOutputStream($r3);
        }
    }

    public File getTargetFile() {
        if (this.frontendFile == null) {
            this.frontendFile = getOriginalFile().isDirectory() ? getTargetFileByParsingURL() : getOriginalFile();
        }
        return this.frontendFile;
    }

    /* access modifiers changed from: protected */
    public File getTargetFileByParsingURL() {
        StringBuilder $r5;
        Utils.asserts(getOriginalFile().isDirectory(), "Target file is not a directory, cannot proceed");
        Utils.asserts(getRequestURI() != null, "RequestURI is null, cannot proceed");
        String $r3 = getRequestURI().toString();
        String $r32 = $r3.substring($r3.lastIndexOf(47) + 1, $r3.length());
        File $r1 = new File(getOriginalFile(), $r32);
        if (!$r1.exists() || !this.renameIfExists) {
            return $r1;
        }
        if (!$r32.contains(".")) {
            $r5 = new StringBuilder();
            $r5.append($r32);
            $r5.append(" (%d)");
        } else {
            $r5 = new StringBuilder();
            $r5.append($r32.substring(0, $r32.lastIndexOf(46)));
            $r5.append(" (%d)");
            $r5.append($r32.substring($r32.lastIndexOf(46), $r32.length()));
        }
        String $r33 = $r5.toString();
        int $i0 = 0;
        while (true) {
            File $r12 = new File(getOriginalFile(), String.format($r33, new Object[]{Integer.valueOf($i0)}));
            if (!$r12.exists()) {
                return $r12;
            }
            $i0++;
        }
    }

    public final void read(int i, Header[] headerArr, byte[] bArr) {
        b(i, headerArr, getTargetFile());
    }

    public abstract void write(int i, Header[] headerArr, Throwable th, File file2);
}
