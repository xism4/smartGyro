package p026b.p033c.p034a.p035a;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.GZIPInputStream;
import p036c.p037a.p038a.p039a.C0488D;
import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0837l;
import p036c.p037a.p038a.p039a.C0882s;
import p036c.p037a.p038a.p039a.C0885v;
import p036c.p037a.p038a.p039a.C0886w;
import p036c.p037a.p038a.p039a.p041b.C0562k;
import p036c.p037a.p038a.p039a.p041b.C0566o;
import p036c.p037a.p038a.p039a.p041b.p044c.C0527g;
import p036c.p037a.p038a.p039a.p041b.p044c.C0530j;
import p036c.p037a.p038a.p039a.p041b.p044c.C0532l;
import p036c.p037a.p038a.p039a.p050e.C0585b;
import p036c.p037a.p038a.p039a.p050e.p051a.C0580c;
import p036c.p037a.p038a.p039a.p050e.p051a.C0581d;
import p036c.p037a.p038a.p039a.p050e.p051a.C0582e;
import p036c.p037a.p038a.p039a.p050e.p053c.C0598d;
import p036c.p037a.p038a.p039a.p050e.p053c.C0599e;
import p036c.p037a.p038a.p039a.p050e.p053c.C0603i;
import p036c.p037a.p038a.p039a.p050e.p053c.C0606l;
import p036c.p037a.p038a.p039a.p050e.p055e.C0620i;
import p036c.p037a.p038a.p039a.p058g.C0662f;
import p036c.p037a.p038a.p039a.p060i.p062b.C0699k;
import p036c.p037a.p038a.p039a.p060i.p063c.p064a.C0725i;
import p036c.p037a.p038a.p039a.p070l.C0839b;
import p036c.p037a.p038a.p039a.p070l.C0842e;
import p036c.p037a.p038a.p039a.p070l.C0844g;
import p036c.p037a.p038a.p039a.p070l.C0846i;
import p036c.p037a.p038a.p039a.p072n.C0851a;
import p036c.p037a.p038a.p039a.p072n.C0855e;
import p036c.p037a.p038a.p039a.p072n.C0866p;

/* renamed from: b.c.a.a.d */
public class C0465d {

    /* renamed from: a */
    public static C0474k f1690a = new C0473j();

    /* renamed from: b */
    private final C0699k f1691b;

    /* renamed from: c */
    private final C0855e f1692c;

    /* renamed from: d */
    private final Map<Context, List<C0480q>> f1693d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public final Map<String, String> f1694e;

    /* renamed from: f */
    private int f1695f;

    /* renamed from: g */
    private int f1696g;

    /* renamed from: h */
    private int f1697h;

    /* renamed from: i */
    private ExecutorService f1698i;

    /* renamed from: j */
    private boolean f1699j;

    /* renamed from: b.c.a.a.d$a */
    private static class C0466a extends C0662f {

        /* renamed from: b */
        InputStream f1700b;

        /* renamed from: c */
        PushbackInputStream f1701c;

        /* renamed from: d */
        GZIPInputStream f1702d;

        public C0466a(C0837l lVar) {
            super(lVar);
        }

        public void consumeContent() {
            C0465d.m2002a(this.f1700b);
            C0465d.m2002a((InputStream) this.f1701c);
            C0465d.m2002a((InputStream) this.f1702d);
            super.consumeContent();
        }

        public InputStream getContent() {
            this.f1700b = this.f1975a.getContent();
            this.f1701c = new PushbackInputStream(this.f1700b, 2);
            if (!C0465d.m2005a(this.f1701c)) {
                return this.f1701c;
            }
            this.f1702d = new GZIPInputStream(this.f1701c);
            return this.f1702d;
        }

        public long getContentLength() {
            C0837l lVar = this.f1975a;
            if (lVar == null) {
                return 0;
            }
            return lVar.getContentLength();
        }
    }

    public C0465d() {
        this(false, 80, 443);
    }

    public C0465d(C0603i iVar) {
        this.f1695f = 10;
        this.f1696g = 10000;
        this.f1697h = 10000;
        boolean z = true;
        this.f1699j = true;
        C0839b bVar = new C0839b();
        C0580c.m2292a((C0844g) bVar, (long) this.f1696g);
        C0580c.m2293a((C0844g) bVar, (C0581d) new C0582e(this.f1695f));
        C0580c.m2291a((C0844g) bVar, 10);
        C0842e.m2981b(bVar, this.f1697h);
        C0842e.m2978a((C0844g) bVar, this.f1696g);
        C0842e.m2979a((C0844g) bVar, true);
        C0842e.m2982c(bVar, 8192);
        C0846i.m2989a((C0844g) bVar, (C0488D) C0886w.f2446f);
        C0585b a = mo2358a(iVar, bVar);
        C0484u.m2111a(a == null ? false : z, "Custom implementation of #createConnectionManager(SchemeRegistry, BasicHttpParams) returned null");
        this.f1698i = mo2359a();
        this.f1693d = Collections.synchronizedMap(new WeakHashMap());
        this.f1694e = new HashMap();
        this.f1692c = new C0866p(new C0851a());
        this.f1691b = new C0699k(a, bVar);
        this.f1691b.mo2856a((C0882s) new C0462a(this));
        this.f1691b.mo2858a((C0885v) new C0463b(this));
        this.f1691b.mo2857a(new C0464c(this), 0);
        this.f1691b.mo2854a((C0562k) new C0483t(5, 1500));
    }

    public C0465d(boolean z, int i, int i2) {
        this(m1998a(z, i, i2));
    }

    /* renamed from: a */
    private static C0603i m1998a(boolean z, int i, int i2) {
        if (z) {
            f1690a.mo2422d("AsyncHttpClient", "Beware! Using the fix is insecure, as it doesn't verify SSL certificates.");
        }
        if (i < 1) {
            i = 80;
            f1690a.mo2422d("AsyncHttpClient", "Invalid HTTP port number specified, defaulting to 80");
        }
        if (i2 < 1) {
            i2 = 443;
            f1690a.mo2422d("AsyncHttpClient", "Invalid HTTPS port number specified, defaulting to 443");
        }
        C0620i b = z ? C0477n.m2085b() : C0620i.m2365a();
        C0603i iVar = new C0603i();
        iVar.mo2673a(new C0599e("http", (C0606l) C0598d.m2320a(), i));
        iVar.mo2673a(new C0599e("https", (C0606l) b, i2));
        return iVar;
    }

    /* renamed from: a */
    public static String m1999a(boolean z, String str, C0481r rVar) {
        if (str == null) {
            return null;
        }
        if (z) {
            try {
                URL url = new URL(URLDecoder.decode(str, "UTF-8"));
                str = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef()).toASCIIString();
            } catch (Exception e) {
                f1690a.mo2420b("AsyncHttpClient", "getUrlWithQueryString encoding URL", e);
            }
        }
        if (rVar == null) {
            return str;
        }
        rVar.mo2436a();
        throw null;
    }

    /* renamed from: a */
    public static void m2001a(C0837l lVar) {
        if (lVar instanceof C0662f) {
            Field field = null;
            try {
                Field[] declaredFields = C0662f.class.getDeclaredFields();
                int length = declaredFields.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    Field field2 = declaredFields[i];
                    if (field2.getName().equals("wrappedEntity")) {
                        field = field2;
                        break;
                    }
                    i++;
                }
                if (field != null) {
                    field.setAccessible(true);
                    C0837l lVar2 = (C0837l) field.get(lVar);
                    if (lVar2 != null) {
                        lVar2.consumeContent();
                    }
                }
            } catch (Throwable th) {
                f1690a.mo2420b("AsyncHttpClient", "wrappedEntity consume", th);
            }
        }
    }

    /* renamed from: a */
    public static void m2002a(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                f1690a.mo2416a("AsyncHttpClient", "Cannot close input stream", e);
            }
        }
    }

    /* renamed from: a */
    public static void m2003a(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                f1690a.mo2416a("AsyncHttpClient", "Cannot close output stream", e);
            }
        }
    }

    /* renamed from: a */
    public static void m2004a(Class<?> cls) {
        if (cls != null) {
            C0483t.m2107a(cls);
        }
    }

    /* renamed from: a */
    public static boolean m2005a(PushbackInputStream pushbackInputStream) {
        if (pushbackInputStream == null) {
            return false;
        }
        byte[] bArr = new byte[2];
        int i = 0;
        while (i < 2) {
            try {
                int read = pushbackInputStream.read(bArr, i, 2 - i);
                if (read < 0) {
                    return false;
                }
                i += read;
            } finally {
                pushbackInputStream.unread(bArr, 0, i);
            }
        }
        pushbackInputStream.unread(bArr, 0, i);
        return 35615 == ((bArr[0] & 255) | ((bArr[1] << 8) & 65280));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C0467e mo2354a(C0699k kVar, C0855e eVar, C0532l lVar, String str, C0482s sVar, Context context) {
        return new C0467e(kVar, eVar, lVar, sVar);
    }

    /* renamed from: a */
    public C0480q mo2355a(Context context, String str, C0481r rVar, C0482s sVar) {
        return mo2364b(this.f1691b, this.f1692c, new C0472i(m1999a(this.f1699j, str, rVar)), (String) null, sVar, context);
    }

    /* renamed from: a */
    public C0480q mo2356a(Context context, String str, C0482s sVar) {
        return mo2355a(context, str, (C0481r) null, sVar);
    }

    /* renamed from: a */
    public C0480q mo2357a(Context context, String str, C0576e[] eVarArr, C0481r rVar, C0482s sVar) {
        C0472i iVar = new C0472i(m1999a(this.f1699j, str, rVar));
        if (eVarArr != null) {
            iVar.mo3117a(eVarArr);
        }
        return mo2364b(this.f1691b, this.f1692c, iVar, (String) null, sVar, context);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C0585b mo2358a(C0603i iVar, C0839b bVar) {
        return new C0725i(bVar, iVar);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public ExecutorService mo2359a() {
        return Executors.newCachedThreadPool();
    }

    /* renamed from: a */
    public void mo2360a(int i) {
        if (i < 1000) {
            i = 10000;
        }
        this.f1696g = i;
        C0844g B = this.f1691b.mo2844B();
        C0580c.m2292a(B, (long) this.f1696g);
        C0842e.m2978a(B, this.f1696g);
    }

    /* renamed from: a */
    public void mo2361a(boolean z) {
        mo2362a(z, z, z);
    }

    /* renamed from: a */
    public void mo2362a(boolean z, boolean z2, boolean z3) {
        this.f1691b.mo2844B().setBooleanParameter("http.protocol.reject-relative-redirect", !z2);
        this.f1691b.mo2844B().setBooleanParameter("http.protocol.allow-circular-redirects", z3);
        this.f1691b.mo2855a((C0566o) new C0475l(z));
    }

    /* renamed from: b */
    public C0480q mo2363b(Context context, String str, C0576e[] eVarArr, C0481r rVar, C0482s sVar) {
        C0530j jVar = new C0530j(m1999a(this.f1699j, str, rVar));
        if (eVarArr != null) {
            jVar.mo3117a(eVarArr);
        }
        return mo2364b(this.f1691b, this.f1692c, jVar, (String) null, sVar, context);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public C0480q mo2364b(C0699k kVar, C0855e eVar, C0532l lVar, String str, C0482s sVar, Context context) {
        List list;
        if (lVar == null) {
            throw new IllegalArgumentException("HttpUriRequest must not be null");
        } else if (sVar == null) {
            throw new IllegalArgumentException("ResponseHandler must not be null");
        } else if (!sVar.mo2397b() || sVar.mo2388a()) {
            if (str != null) {
                if (!(lVar instanceof C0527g) || ((C0527g) lVar).getEntity() == null || !lVar.containsHeader("Content-Type")) {
                    lVar.setHeader("Content-Type", str);
                } else {
                    f1690a.mo2415a("AsyncHttpClient", "Passed contentType will be ignored because HttpEntity sets content type");
                }
            }
            sVar.mo2387a(lVar.getAllHeaders());
            sVar.mo2385a(lVar.getURI());
            C0467e a = mo2354a(kVar, eVar, lVar, str, sVar, context);
            this.f1698i.submit(a);
            C0480q qVar = new C0480q(a);
            if (context != null) {
                synchronized (this.f1693d) {
                    list = this.f1693d.get(context);
                    if (list == null) {
                        list = Collections.synchronizedList(new LinkedList());
                        this.f1693d.put(context, list);
                    }
                }
                list.add(qVar);
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    if (((C0480q) it.next()).mo2435c()) {
                        it.remove();
                    }
                }
            }
            return qVar;
        } else {
            throw new IllegalArgumentException("Synchronous ResponseHandler used in AsyncHttpClient. You should create your response handler in a looper thread or use SyncHttpClient instead.");
        }
    }

    /* renamed from: b */
    public void mo2365b(int i) {
        if (i < 1000) {
            i = 10000;
        }
        this.f1697h = i;
        C0842e.m2981b(this.f1691b.mo2844B(), this.f1697h);
    }

    /* renamed from: c */
    public void mo2366c(int i) {
        if (i < 1000) {
            i = 10000;
        }
        mo2360a(i);
        mo2365b(i);
    }
}
