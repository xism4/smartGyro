package lombok.extern.asm;

import android.os.IInterface;

public interface ByteVector extends IInterface {
    int a();

    int a(int i);

    int a(boolean z);

    int generateKey(int i);

    int get(int i);
}
