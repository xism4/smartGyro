package cz.msebera.android.http.cache;

public class Packet {
    public static byte[] encode(byte[] bArr, int i) {
        return encode(bArr, 0, bArr.length, i);
    }

    public static byte[] encode(byte[] bArr, int i, int i2, int i3) {
        Base64$Encoder $r0 = new Base64$Encoder(i3, (byte[]) null);
        int $i2 = (i2 / 3) * 4;
        byte $b3 = 2;
        if (!$r0.do_padding) {
            int $i4 = i2 % 3;
            if ($i4 != 0) {
                if ($i4 == 1) {
                    $i2 += 2;
                } else if ($i4 == 2) {
                    $i2 += 3;
                }
            }
        } else if (i2 % 3 > 0) {
            $i2 += 4;
        }
        if ($r0.do_newline && i2 > 0) {
            int $i42 = ((i2 - 1) / 57) + 1;
            if (!$r0.do_cr) {
                $b3 = 1;
            }
            $i2 += $i42 * $b3;
        }
        $r0.output = new byte[$i2];
        $r0.process(bArr, i, i2, true);
        return $r0.output;
    }
}
