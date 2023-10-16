package org.cocos2dx.cpp;

import android.bluetooth.BluetoothDevice;
import java.util.Comparator;
import java.util.Map;

/* renamed from: org.cocos2dx.cpp.f */
class C0898f implements Comparator<Map.Entry<BluetoothDevice, Integer>> {
    C0898f() {
    }

    /* renamed from: a */
    public int compare(Map.Entry<BluetoothDevice, Integer> entry, Map.Entry<BluetoothDevice, Integer> entry2) {
        return entry2.getValue().compareTo(entry.getValue());
    }
}
