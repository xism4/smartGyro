package p036c.p037a.p038a.p039a.p060i.p062b;

import p036c.p037a.p038a.p039a.p070l.C0838a;
import p036c.p037a.p038a.p039a.p070l.C0844g;
import p036c.p037a.p038a.p039a.p074p.C0870a;

@Deprecated
/* renamed from: c.a.a.a.i.b.g */
public class C0695g extends C0838a {

    /* renamed from: a */
    protected final C0844g f2071a;

    /* renamed from: b */
    protected final C0844g f2072b;

    /* renamed from: c */
    protected final C0844g f2073c;

    /* renamed from: d */
    protected final C0844g f2074d;

    public C0695g(C0844g gVar, C0844g gVar2, C0844g gVar3, C0844g gVar4) {
        this.f2071a = gVar;
        this.f2072b = gVar2;
        this.f2073c = gVar3;
        this.f2074d = gVar4;
    }

    public Object getParameter(String str) {
        C0844g gVar;
        C0844g gVar2;
        C0844g gVar3;
        C0870a.m3042a(str, "Parameter name");
        C0844g gVar4 = this.f2074d;
        Object parameter = gVar4 != null ? gVar4.getParameter(str) : null;
        if (parameter == null && (gVar3 = this.f2073c) != null) {
            parameter = gVar3.getParameter(str);
        }
        if (parameter == null && (gVar2 = this.f2072b) != null) {
            parameter = gVar2.getParameter(str);
        }
        return (parameter != null || (gVar = this.f2071a) == null) ? parameter : gVar.getParameter(str);
    }

    public C0844g setParameter(String str, Object obj) {
        throw new UnsupportedOperationException("Setting parameters in a stack is not supported.");
    }
}
