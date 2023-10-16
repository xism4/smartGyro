package p036c.p037a.p038a.p039a.p069k;

import p036c.p037a.p038a.p039a.C0639f;
import p036c.p037a.p038a.p039a.C0889z;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0873d;

/* renamed from: c.a.a.a.k.f */
public class C0818f implements C0831s {
    @Deprecated

    /* renamed from: a */
    public static final C0818f f2359a = new C0818f();

    /* renamed from: b */
    public static final C0818f f2360b = new C0818f();

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public int mo3141a(C0639f fVar) {
        if (fVar == null) {
            return 0;
        }
        int length = fVar.getName().length();
        String value = fVar.getValue();
        if (value != null) {
            length += value.length() + 3;
        }
        int parameterCount = fVar.getParameterCount();
        if (parameterCount > 0) {
            for (int i = 0; i < parameterCount; i++) {
                length += mo3142a(fVar.getParameter(i)) + 2;
            }
        }
        return length;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public int mo3142a(C0889z zVar) {
        if (zVar == null) {
            return 0;
        }
        int length = zVar.getName().length();
        String value = zVar.getValue();
        return value != null ? length + value.length() + 3 : length;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public int mo3143a(C0889z[] zVarArr) {
        if (zVarArr == null || zVarArr.length < 1) {
            return 0;
        }
        int length = (zVarArr.length - 1) * 2;
        for (C0889z a : zVarArr) {
            length += mo3142a(a);
        }
        return length;
    }

    /* renamed from: a */
    public C0873d mo3144a(C0873d dVar, C0639f fVar, boolean z) {
        C0870a.m3042a(fVar, "Header element");
        int a = mo3141a(fVar);
        if (dVar == null) {
            dVar = new C0873d(a);
        } else {
            dVar.mo3295a(a);
        }
        dVar.mo3298a(fVar.getName());
        String value = fVar.getValue();
        if (value != null) {
            dVar.append('=');
            mo3147a(dVar, value, z);
        }
        int parameterCount = fVar.getParameterCount();
        if (parameterCount > 0) {
            for (int i = 0; i < parameterCount; i++) {
                dVar.mo3298a("; ");
                mo3145a(dVar, fVar.getParameter(i), z);
            }
        }
        return dVar;
    }

    /* renamed from: a */
    public C0873d mo3145a(C0873d dVar, C0889z zVar, boolean z) {
        C0870a.m3042a(zVar, "Name / value pair");
        int a = mo3142a(zVar);
        if (dVar == null) {
            dVar = new C0873d(a);
        } else {
            dVar.mo3295a(a);
        }
        dVar.mo3298a(zVar.getName());
        String value = zVar.getValue();
        if (value != null) {
            dVar.append('=');
            mo3147a(dVar, value, z);
        }
        return dVar;
    }

    /* renamed from: a */
    public C0873d mo3146a(C0873d dVar, C0889z[] zVarArr, boolean z) {
        C0870a.m3042a(zVarArr, "Header parameter array");
        int a = mo3143a(zVarArr);
        if (dVar == null) {
            dVar = new C0873d(a);
        } else {
            dVar.mo3295a(a);
        }
        for (int i = 0; i < zVarArr.length; i++) {
            if (i > 0) {
                dVar.mo3298a("; ");
            }
            mo3145a(dVar, zVarArr[i], z);
        }
        return dVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo3147a(C0873d dVar, String str, boolean z) {
        if (!z) {
            boolean z2 = z;
            for (int i = 0; i < str.length() && !z2; i++) {
                z2 = mo3148a(str.charAt(i));
            }
            z = z2;
        }
        if (z) {
            dVar.append('\"');
        }
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (mo3149b(charAt)) {
                dVar.append('\\');
            }
            dVar.append(charAt);
        }
        if (z) {
            dVar.append('\"');
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo3148a(char c) {
        return " ;,:@()<>\\\"/[]?={}\t".indexOf(c) >= 0;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public boolean mo3149b(char c) {
        return "\"\\".indexOf(c) >= 0;
    }
}
