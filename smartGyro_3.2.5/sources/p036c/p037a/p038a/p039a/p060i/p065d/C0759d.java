package p036c.p037a.p038a.p039a.p060i.p065d;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import p036c.p037a.p038a.p039a.p057f.C0640a;
import p036c.p037a.p038a.p039a.p057f.C0655p;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.i.d.d */
public class C0759d implements C0655p, C0640a, Cloneable, Serializable {

    /* renamed from: a */
    private final String f2241a;

    /* renamed from: b */
    private Map<String, String> f2242b = new HashMap();

    /* renamed from: c */
    private String f2243c;

    /* renamed from: d */
    private String f2244d;

    /* renamed from: e */
    private String f2245e;

    /* renamed from: f */
    private Date f2246f;

    /* renamed from: g */
    private String f2247g;

    /* renamed from: h */
    private boolean f2248h;

    /* renamed from: i */
    private int f2249i;

    public C0759d(String str, String str2) {
        C0870a.m3042a(str, "Name");
        this.f2241a = str;
        this.f2243c = str2;
    }

    /* renamed from: a */
    public void mo3042a(String str, String str2) {
        this.f2242b.put(str, str2);
    }

    public Object clone() {
        C0759d dVar = (C0759d) super.clone();
        dVar.f2242b = new HashMap(this.f2242b);
        return dVar;
    }

    public boolean containsAttribute(String str) {
        return this.f2242b.containsKey(str);
    }

    public String getAttribute(String str) {
        return this.f2242b.get(str);
    }

    public String getDomain() {
        return this.f2245e;
    }

    public Date getExpiryDate() {
        return this.f2246f;
    }

    public String getName() {
        return this.f2241a;
    }

    public String getPath() {
        return this.f2247g;
    }

    public int[] getPorts() {
        return null;
    }

    public String getValue() {
        return this.f2243c;
    }

    public int getVersion() {
        return this.f2249i;
    }

    public boolean isExpired(Date date) {
        C0870a.m3042a(date, "Date");
        Date date2 = this.f2246f;
        return date2 != null && date2.getTime() <= date.getTime();
    }

    public boolean isSecure() {
        return this.f2248h;
    }

    public void setComment(String str) {
        this.f2244d = str;
    }

    public void setDomain(String str) {
        this.f2245e = str != null ? str.toLowerCase(Locale.ROOT) : null;
    }

    public void setExpiryDate(Date date) {
        this.f2246f = date;
    }

    public void setPath(String str) {
        this.f2247g = str;
    }

    public void setSecure(boolean z) {
        this.f2248h = z;
    }

    public void setVersion(int i) {
        this.f2249i = i;
    }

    public String toString() {
        return "[version: " + Integer.toString(this.f2249i) + "]" + "[name: " + this.f2241a + "]" + "[value: " + this.f2243c + "]" + "[domain: " + this.f2245e + "]" + "[path: " + this.f2247g + "]" + "[expiry: " + this.f2246f + "]";
    }
}
