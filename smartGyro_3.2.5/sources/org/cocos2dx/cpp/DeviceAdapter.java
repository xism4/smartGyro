package org.cocos2dx.cpp;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

public class DeviceAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<BluetoothDevice> devices;

    public DeviceAdapter(Context context2, ArrayList<BluetoothDevice> arrayList) {
        this.context = context2;
        this.devices = arrayList;
    }

    public int getCount() {
        ArrayList<BluetoothDevice> arrayList = this.devices;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    public Object getItem(int i) {
        return this.devices.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return view;
    }
}
