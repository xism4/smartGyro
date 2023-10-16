package p036c.p037a.p038a.p039a.p070l;

@Deprecated
/* renamed from: c.a.a.a.l.a */
public abstract class C0838a implements C0844g, C0845h {
    protected C0838a() {
    }

    public boolean getBooleanParameter(String str, boolean z) {
        Object parameter = getParameter(str);
        return parameter == null ? z : ((Boolean) parameter).booleanValue();
    }

    public int getIntParameter(String str, int i) {
        Object parameter = getParameter(str);
        return parameter == null ? i : ((Integer) parameter).intValue();
    }

    public long getLongParameter(String str, long j) {
        Object parameter = getParameter(str);
        return parameter == null ? j : ((Long) parameter).longValue();
    }

    public boolean isParameterFalse(String str) {
        return !getBooleanParameter(str, false);
    }

    public boolean isParameterTrue(String str) {
        return getBooleanParameter(str, false);
    }

    public C0844g setBooleanParameter(String str, boolean z) {
        setParameter(str, z ? Boolean.TRUE : Boolean.FALSE);
        return this;
    }

    public C0844g setIntParameter(String str, int i) {
        setParameter(str, Integer.valueOf(i));
        return this;
    }

    public C0844g setLongParameter(String str, long j) {
        setParameter(str, Long.valueOf(j));
        return this;
    }
}
