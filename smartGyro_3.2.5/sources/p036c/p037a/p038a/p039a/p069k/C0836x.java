package p036c.p037a.p038a.p039a.p069k;

import java.util.BitSet;
import p036c.p037a.p038a.p039a.p074p.C0873d;

/* renamed from: c.a.a.a.k.x */
public class C0836x {

    /* renamed from: a */
    public static final C0836x f2405a = new C0836x();

    /* renamed from: a */
    public static BitSet m2968a(int... iArr) {
        BitSet bitSet = new BitSet();
        for (int i : iArr) {
            bitSet.set(i);
        }
        return bitSet;
    }

    /* renamed from: a */
    public static boolean m2969a(char c) {
        return c == ' ' || c == 9 || c == 13 || c == 10;
    }

    /* renamed from: a */
    public String mo3223a(C0873d dVar, C0835w wVar, BitSet bitSet) {
        StringBuilder sb = new StringBuilder();
        loop0:
        while (true) {
            boolean z = false;
            while (!wVar.mo3219a()) {
                char charAt = dVar.charAt(wVar.mo3220b());
                if (bitSet != null && bitSet.get(charAt)) {
                    break loop0;
                } else if (m2969a(charAt)) {
                    mo3224a(dVar, wVar);
                    z = true;
                } else {
                    if (z && sb.length() > 0) {
                        sb.append(' ');
                    }
                    mo3226a(dVar, wVar, bitSet, sb);
                }
            }
            break loop0;
        }
        return sb.toString();
    }

    /* renamed from: a */
    public void mo3224a(C0873d dVar, C0835w wVar) {
        int b = wVar.mo3220b();
        int b2 = wVar.mo3220b();
        int c = wVar.mo3221c();
        while (b2 < c && m2969a(dVar.charAt(b2))) {
            b++;
            b2++;
        }
        wVar.mo3218a(b);
    }

    /* renamed from: a */
    public void mo3225a(C0873d dVar, C0835w wVar, StringBuilder sb) {
        if (!wVar.mo3219a()) {
            int b = wVar.mo3220b();
            int b2 = wVar.mo3220b();
            int c = wVar.mo3221c();
            if (dVar.charAt(b) == '\"') {
                int i = b2 + 1;
                int i2 = b + 1;
                boolean z = false;
                while (true) {
                    if (i >= c) {
                        break;
                    }
                    char charAt = dVar.charAt(i);
                    if (z) {
                        if (!(charAt == '\"' || charAt == '\\')) {
                            sb.append('\\');
                        }
                        sb.append(charAt);
                        z = false;
                    } else if (charAt == '\"') {
                        i2++;
                        break;
                    } else if (charAt == '\\') {
                        z = true;
                    } else if (!(charAt == 13 || charAt == 10)) {
                        sb.append(charAt);
                    }
                    i++;
                    i2++;
                }
                wVar.mo3218a(i2);
            }
        }
    }

    /* renamed from: a */
    public void mo3226a(C0873d dVar, C0835w wVar, BitSet bitSet, StringBuilder sb) {
        int b = wVar.mo3220b();
        int c = wVar.mo3221c();
        for (int b2 = wVar.mo3220b(); b2 < c; b2++) {
            char charAt = dVar.charAt(b2);
            if ((bitSet != null && bitSet.get(charAt)) || m2969a(charAt)) {
                break;
            }
            b++;
            sb.append(charAt);
        }
        wVar.mo3218a(b);
    }

    /* renamed from: b */
    public String mo3227b(C0873d dVar, C0835w wVar, BitSet bitSet) {
        StringBuilder sb = new StringBuilder();
        loop0:
        while (true) {
            boolean z = false;
            while (!wVar.mo3219a()) {
                char charAt = dVar.charAt(wVar.mo3220b());
                if (bitSet != null && bitSet.get(charAt)) {
                    break loop0;
                } else if (m2969a(charAt)) {
                    mo3224a(dVar, wVar);
                    z = true;
                } else if (charAt == '\"') {
                    if (z && sb.length() > 0) {
                        sb.append(' ');
                    }
                    mo3225a(dVar, wVar, sb);
                } else {
                    if (z && sb.length() > 0) {
                        sb.append(' ');
                    }
                    mo3228b(dVar, wVar, bitSet, sb);
                }
            }
            break loop0;
        }
        return sb.toString();
    }

    /* renamed from: b */
    public void mo3228b(C0873d dVar, C0835w wVar, BitSet bitSet, StringBuilder sb) {
        int b = wVar.mo3220b();
        int c = wVar.mo3221c();
        for (int b2 = wVar.mo3220b(); b2 < c; b2++) {
            char charAt = dVar.charAt(b2);
            if ((bitSet != null && bitSet.get(charAt)) || m2969a(charAt) || charAt == '\"') {
                break;
            }
            b++;
            sb.append(charAt);
        }
        wVar.mo3218a(b);
    }
}
