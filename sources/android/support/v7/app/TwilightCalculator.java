package android.support.v7.app;

class TwilightCalculator {
    private static TwilightCalculator sInstance;
    public int state;
    public long sunrise;
    public long sunset;

    TwilightCalculator() {
    }

    static TwilightCalculator getInstance() {
        if (sInstance == null) {
            sInstance = new TwilightCalculator();
        }
        return sInstance;
    }

    public void calculateTwilight(long j, double d, double $d1) {
        float $f0 = ((float) (j - 946728000000L)) / 8.64E7f;
        float $f1 = (0.01720197f * $f0) + 6.24006f;
        double $d2 = (double) $f1;
        Double.isNaN($d2);
        double $d4 = (double) (2.0f * $f1);
        double d2 = $d4;
        double $d42 = Math.sin($d4) * 3.4906598739326E-4d;
        double d3 = $d42;
        double $d3 = (Math.sin($d2) * 0.03341960161924362d) + $d2 + $d42;
        double $d43 = (double) ($f1 * 3.0f);
        double d4 = $d43;
        double $d44 = Math.sin($d43) * 5.236000106378924E-6d;
        double d5 = $d44;
        double $d32 = $d3 + $d44 + 1.796593063d + 3.141592653589793d;
        double d6 = (-$d1) / 360.0d;
        double $d12 = (double) ($f0 - 9.0E-4f);
        Double.isNaN($d12);
        double $d45 = $d12 - d6;
        double d7 = $d45;
        double $d46 = (double) (((float) Math.round($d45)) + 9.0E-4f);
        Double.isNaN($d46);
        double sin = $d46 + d6 + (Math.sin($d2) * 0.0053d) + (Math.sin(2.0d * $d32) * -0.0069d);
        double $d22 = Math.asin(Math.sin($d32) * Math.sin(0.4092797040939331d));
        double $d0 = 0.01745329238474369d * d;
        double $d33 = Math.sin(-0.10471975803375244d);
        double $d47 = Math.sin($d0) * Math.sin($d22);
        double d8 = $d47;
        double $d02 = ($d33 - $d47) / (Math.cos($d0) * Math.cos($d22));
        if ($d02 >= 1.0d) {
            this.state = 1;
        } else if ($d02 <= -1.0d) {
            this.state = 0;
        } else {
            double $d03 = Math.acos($d02) / 6.283185307179586d;
            double d9 = $d03;
            double $d04 = (double) ((float) $d03);
            double $d05 = $d04;
            Double.isNaN($d04);
            this.sunset = Math.round((sin + $d05) * 8.64E7d) + 946728000000L;
            Double.isNaN($d05);
            double $d06 = (sin - $d05) * 8.64E7d;
            double d10 = $d06;
            this.sunrise = Math.round($d06) + 946728000000L;
            if (this.sunrise >= j || this.sunset <= j) {
                this.state = 1;
                return;
            } else {
                this.state = 0;
                return;
            }
        }
        this.sunset = -1;
        this.sunrise = -1;
    }
}
