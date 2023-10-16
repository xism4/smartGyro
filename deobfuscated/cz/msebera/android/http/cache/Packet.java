package cz.msebera.android.http.cache;

public class Packet
{
  public static byte[] encode(byte[] paramArrayOfByte, int paramInt)
  {
    return encode(paramArrayOfByte, 0, paramArrayOfByte.length, paramInt);
  }
  
  public static byte[] encode(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
  {
    Base64.Encoder localEncoder = new Base64.Encoder(paramInt3, null);
    int i = paramInt2 / 3 * 4;
    boolean bool = do_padding;
    int j = 2;
    int k;
    if (bool)
    {
      paramInt3 = i;
      if (paramInt2 % 3 > 0) {
        paramInt3 = i + 4;
      }
    }
    else
    {
      k = paramInt2 % 3;
      paramInt3 = i;
      if (k != 0) {
        if (k != 1)
        {
          if (k != 2) {
            paramInt3 = i;
          } else {
            paramInt3 = i + 3;
          }
        }
        else {
          paramInt3 = i + 2;
        }
      }
    }
    i = paramInt3;
    if (do_newline)
    {
      i = paramInt3;
      if (paramInt2 > 0)
      {
        k = (paramInt2 - 1) / 57;
        if (do_cr) {
          i = j;
        } else {
          i = 1;
        }
        i = paramInt3 + (k + 1) * i;
      }
    }
    output = new byte[i];
    localEncoder.process(paramArrayOfByte, paramInt1, paramInt2, true);
    return output;
  }
}
