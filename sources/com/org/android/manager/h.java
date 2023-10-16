package com.org.android.manager;

import android.util.Base64;
import com.org.android.util.m;
import java.util.List;

public final class h {
    private final String b;
    private final String c;
    private final String d;
    private final List<List<byte[]>> e;
    private final String h = (this.c + "-" + this.b + "-" + this.d);
    private final int p = 0;

    public h(String $r1, String $r12, String $r13, List $r4) {
        m.a($r1);
        this.c = $r1;
        m.a($r12);
        this.b = $r12;
        m.a($r13);
        this.d = $r13;
        m.a($r4);
        this.e = $r4;
    }

    public String a() {
        return this.h;
    }

    public int b() {
        return this.p;
    }

    public String c() {
        return this.c;
    }

    public String e() {
        return this.d;
    }

    public String getGroupId() {
        return this.b;
    }

    public List getTitle() {
        return this.e;
    }

    public String toString() {
        StringBuilder $r1 = new StringBuilder();
        $r1.append("FontRequest {mProviderAuthority: " + this.c + ", mProviderPackage: " + this.b + ", mQuery: " + this.d + ", mCertificates:");
        for (int $i0 = 0; $i0 < this.e.size(); $i0++) {
            $r1.append(" [");
            List $r4 = this.e.get($i0);
            for (int $i1 = 0; $i1 < $r4.size(); $i1++) {
                $r1.append(" \"");
                $r1.append(Base64.encodeToString((byte[]) $r4.get($i1), 0));
                $r1.append("\"");
            }
            $r1.append(" ]");
        }
        $r1.append("}");
        $r1.append("mCertificatesArray: " + this.p);
        return $r1.toString();
    }
}
