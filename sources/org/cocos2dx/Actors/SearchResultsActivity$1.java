package org.cocos2dx.Actors;

import android.bluetooth.BluetoothDevice;
import java.util.Comparator;
import java.util.Map;

final class SearchResultsActivity$1 implements Comparator<Map.Entry<BluetoothDevice, Integer>> {
    SearchResultsActivity$1() {
    }

    public int compare(Map.Entry entry, Map.Entry entry2) {
        return ((Integer) entry2.getValue()).compareTo((Integer) entry.getValue());
    }
}
