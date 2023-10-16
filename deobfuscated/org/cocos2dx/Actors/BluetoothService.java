package org.cocos2dx.Actors;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.ContextWrapper;
import android.content.Intent;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BluetoothService
  extends BaseService
{
  public static final String ACTION_DATA_AVAILABLE = "ACTION_DATA_AVAILABLE";
  public static final String ACTION_GATT_CONNECTED = "ACTION_GATT_CONNECTED";
  public static final String ACTION_GATT_DISCONNECTED = "ACTION_GATT_DISCONNECTED";
  public static final String ACTION_GATT_SERVICES_DISCOVERED = "ACTION_GATT_SERVICES_DISCOVERED";
  public static final UUID CCCD = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
  public static final String EXTRA_DATA = "EXTRA_DATA";
  public static final UUID RX_CHAR_UUID;
  public static final UUID RX_CHAR_UUID3;
  public static final UUID RX_CHAR_UUID4;
  public static final UUID RX_CHAR_UUID5;
  public static final UUID RX_CHAR_UUID6 = UUID.fromString("0000fff2-0000-1000-8000-00805f9b34fb");
  public static final UUID RX_SERVICE_UUID = UUID.fromString("6e400001-b5a3-f393-e0a9-e50e24dcca9e");
  public static final UUID RX_SERVICE_UUID3;
  public static final UUID RX_SERVICE_UUID4;
  public static final UUID RX_SERVICE_UUID5;
  public static final int STATE_CONNECTED = 2;
  public static final int STATE_CONNECTING = 1;
  public static final int STATE_DISCONNECTED = 0;
  public static final UUID TX_CHAR_UUID;
  public static final UUID TX_CHAR_UUID3;
  public static final UUID TX_CHAR_UUID4;
  public static final UUID TX_CHAR_UUID5;
  public static final UUID TX_CHAR_UUID6 = UUID.fromString("0000fff1-0000-1000-8000-00805f9b34fb");
  private static final String packetWriter = "BluetoothService";
  private int _appType = 1;
  private BluetoothAdapter bluetoothAdapter;
  private String bluetoothDeviceAddress;
  private BluetoothGatt bluetoothGatt;
  private BluetoothManager bluetoothManager;
  private int connectionState = 0;
  private final BluetoothGattCallback gattCallback = new DayFragment.1(this);
  
  static
  {
    RX_CHAR_UUID = UUID.fromString("6e400002-b5a3-f393-e0a9-e50e24dcca9e");
    TX_CHAR_UUID = UUID.fromString("6e400003-b5a3-f393-e0a9-e50e24dcca9e");
    RX_SERVICE_UUID3 = UUID.fromString("0000ae00-0000-1000-8000-00805f9b34fb");
    RX_CHAR_UUID3 = UUID.fromString("0000ae01-0000-1000-8000-00805f9b34fb");
    TX_CHAR_UUID3 = UUID.fromString("0000ae02-0000-1000-8000-00805f9b34fb");
    RX_SERVICE_UUID4 = UUID.fromString("0000ffe0-0000-1000-8000-00805f9b34fb");
    RX_CHAR_UUID4 = UUID.fromString("0000fff3-0000-1000-8000-00805f9b34fb");
    TX_CHAR_UUID4 = UUID.fromString("0000fff4-0000-1000-8000-00805f9b34fb");
    RX_SERVICE_UUID5 = UUID.fromString("0000fff0-0000-1000-8000-00805f9b34fb");
    RX_CHAR_UUID5 = UUID.fromString("0000fff3-0000-1000-8000-00805f9b34fb");
    TX_CHAR_UUID5 = UUID.fromString("0000fff7-0000-1000-8000-00805f9b34fb");
  }
  
  public BluetoothService() {}
  
  private void broadcastUpdate(String paramString)
  {
    sendBroadcast(new Intent(paramString));
  }
  
  private void broadcastUpdate(String paramString, BluetoothGattCharacteristic paramBluetoothGattCharacteristic)
  {
    paramString = new Intent(paramString);
    paramBluetoothGattCharacteristic = paramBluetoothGattCharacteristic.getValue();
    if ((paramBluetoothGattCharacteristic != null) && (paramBluetoothGattCharacteristic.length > 0))
    {
      int i = 0;
      while (i < paramBluetoothGattCharacteristic.length)
      {
        paramBluetoothGattCharacteristic[i] = ((byte)(paramBluetoothGattCharacteristic[i] & 0xFF));
        i += 1;
      }
      paramString.putExtra("EXTRA_DATA", paramBluetoothGattCharacteristic);
    }
    sendBroadcast(paramString);
  }
  
  public BluetoothGatt GetBluetoothGatt()
  {
    return bluetoothGatt;
  }
  
  public void SetAppType(int paramInt)
  {
    _appType = paramInt;
  }
  
  public void close()
  {
    BluetoothGatt localBluetoothGatt = bluetoothGatt;
    if (localBluetoothGatt == null) {
      return;
    }
    localBluetoothGatt.close();
    bluetoothGatt = null;
  }
  
  public boolean connect(String paramString)
  {
    if (bluetoothAdapter == null)
    {
      Log.e(packetWriter, "BluetoothAdapter?????");
      initialize();
    }
    Object localObject = bluetoothAdapter;
    if (localObject == null) {
      return false;
    }
    if (paramString == null) {
      localObject = packetWriter;
    }
    for (paramString = "BluetoothAdapter??????";; paramString = "????????????")
    {
      Log.e((String)localObject, paramString);
      return false;
      localObject = ((BluetoothAdapter)localObject).getRemoteDevice(paramString);
      if (localObject != null) {
        break;
      }
      localObject = packetWriter;
    }
    BluetoothGatt localBluetoothGatt = bluetoothGatt;
    if (localBluetoothGatt != null)
    {
      localBluetoothGatt.close();
      bluetoothGatt = null;
    }
    bluetoothGatt = ((BluetoothDevice)localObject).connectGatt(this, false, gattCallback);
    Log.d(packetWriter, "??????????");
    bluetoothDeviceAddress = paramString;
    connectionState = 1;
    return true;
  }
  
  public void disconnect()
  {
    if (bluetoothAdapter != null)
    {
      BluetoothGatt localBluetoothGatt = bluetoothGatt;
      if (localBluetoothGatt != null)
      {
        localBluetoothGatt.disconnect();
        return;
      }
    }
    Log.w(packetWriter, "BluetoothAdapter?????");
  }
  
  public void enableTXNotification()
  {
    Log.d(packetWriter, "enableTXNotification");
    Object localObject = bluetoothGatt;
    if (localObject == null) {
      return;
    }
    int i = _appType;
    BluetoothGattDescriptor localBluetoothGattDescriptor;
    if (i == 2)
    {
      localObject = ((BluetoothGatt)localObject).getService(RX_SERVICE_UUID3);
      if (localObject == null)
      {
        Log.e(packetWriter, "RxService == null");
        return;
      }
      localObject = ((BluetoothGattService)localObject).getCharacteristic(TX_CHAR_UUID3);
      if (localObject == null)
      {
        Log.e(packetWriter, "TxChar == null");
        return;
      }
      bluetoothGatt.setCharacteristicNotification((BluetoothGattCharacteristic)localObject, true);
      localBluetoothGattDescriptor = ((BluetoothGattCharacteristic)localObject).getDescriptor(CCCD);
      localObject = localBluetoothGattDescriptor;
      localBluetoothGattDescriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
    }
    for (;;)
    {
      bluetoothGatt.writeDescriptor((BluetoothGattDescriptor)localObject);
      return;
      if (i == 3)
      {
        localObject = ((BluetoothGatt)localObject).getService(RX_SERVICE_UUID4);
        if (localObject == null)
        {
          Log.e(packetWriter, "RxService == null");
          return;
        }
        localObject = ((BluetoothGattService)localObject).getCharacteristic(TX_CHAR_UUID4);
        if (localObject == null)
        {
          Log.e(packetWriter, "TxChar == null");
          return;
        }
        bluetoothGatt.setCharacteristicNotification((BluetoothGattCharacteristic)localObject, true);
        localBluetoothGattDescriptor = ((BluetoothGattCharacteristic)localObject).getDescriptor(CCCD);
        localObject = localBluetoothGattDescriptor;
        localBluetoothGattDescriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
      }
      else if (i == 4)
      {
        localObject = ((BluetoothGatt)localObject).getService(RX_SERVICE_UUID5);
        if (localObject == null)
        {
          Log.e(packetWriter, "RxService == null");
          return;
        }
        localObject = ((BluetoothGattService)localObject).getCharacteristic(TX_CHAR_UUID5);
        if (localObject == null)
        {
          Log.e(packetWriter, "TxChar5 == null");
          return;
        }
        bluetoothGatt.setCharacteristicNotification((BluetoothGattCharacteristic)localObject, true);
        localBluetoothGattDescriptor = ((BluetoothGattCharacteristic)localObject).getDescriptor(CCCD);
        localObject = localBluetoothGattDescriptor;
        localBluetoothGattDescriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
      }
      else if (i == 5)
      {
        localObject = ((BluetoothGatt)localObject).getService(RX_SERVICE_UUID5);
        if (localObject == null)
        {
          Log.e(packetWriter, "RxService == null");
          return;
        }
        localObject = ((BluetoothGattService)localObject).getCharacteristic(TX_CHAR_UUID6);
        if (localObject == null)
        {
          Log.e(packetWriter, "TxChar5 == null");
          return;
        }
        bluetoothGatt.setCharacteristicNotification((BluetoothGattCharacteristic)localObject, true);
        localBluetoothGattDescriptor = ((BluetoothGattCharacteristic)localObject).getDescriptor(CCCD);
        localObject = localBluetoothGattDescriptor;
        localBluetoothGattDescriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
      }
      else
      {
        localObject = ((BluetoothGatt)localObject).getService(RX_SERVICE_UUID);
        if (localObject == null)
        {
          Log.e(packetWriter, "RxService == null");
          return;
        }
        localObject = ((BluetoothGattService)localObject).getCharacteristic(TX_CHAR_UUID);
        if (localObject == null)
        {
          Log.e(packetWriter, "TxChar == null");
          return;
        }
        bluetoothGatt.setCharacteristicNotification((BluetoothGattCharacteristic)localObject, true);
        localBluetoothGattDescriptor = ((BluetoothGattCharacteristic)localObject).getDescriptor(CCCD);
        localObject = localBluetoothGattDescriptor;
        localBluetoothGattDescriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
      }
    }
  }
  
  public int getConnectionState()
  {
    return connectionState;
  }
  
  public List getSupportedGattServices()
  {
    BluetoothGatt localBluetoothGatt = bluetoothGatt;
    if (localBluetoothGatt == null)
    {
      Log.e(packetWriter, "bluetoothGatt == null");
      return new ArrayList();
    }
    return localBluetoothGatt.getServices();
  }
  
  public boolean initialize()
  {
    String str1;
    if (bluetoothManager == null)
    {
      bluetoothManager = ((BluetoothManager)getSystemService("bluetooth"));
      if (bluetoothManager == null) {
        str1 = packetWriter;
      }
    }
    for (String str2 = "?????BluetoothManager";; str2 = "????BluetoothAdapter")
    {
      Log.e(str1, str2);
      return false;
      bluetoothAdapter = bluetoothManager.getAdapter();
      if (bluetoothAdapter != null) {
        break;
      }
      str1 = packetWriter;
    }
    return true;
  }
  
  public boolean onUnbind(Intent paramIntent)
  {
    close();
    return super.onUnbind(paramIntent);
  }
  
  public void readCharacteristic(BluetoothGattCharacteristic paramBluetoothGattCharacteristic)
  {
    if (bluetoothAdapter != null)
    {
      Object localObject = bluetoothGatt;
      if (localObject != null)
      {
        boolean bool = ((BluetoothGatt)localObject).readCharacteristic(paramBluetoothGattCharacteristic);
        paramBluetoothGattCharacteristic = packetWriter;
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("******** readCharacteristic: ");
        ((StringBuilder)localObject).append(bool);
        Log.d(paramBluetoothGattCharacteristic, ((StringBuilder)localObject).toString());
        return;
      }
    }
    Log.d(packetWriter, "BluetoothAdapter?????");
  }
  
  public void setCharacteristicNotification(BluetoothGattCharacteristic paramBluetoothGattCharacteristic, boolean paramBoolean, int paramInt)
  {
    if (bluetoothAdapter != null)
    {
      localObject = bluetoothGatt;
      if (localObject != null)
      {
        if (localObject != null) {
          ((BluetoothGatt)localObject).setCharacteristicNotification(paramBluetoothGattCharacteristic, paramBoolean);
        } else {
          Log.e(packetWriter, "bluetoothGatt == null");
        }
        if (paramInt == 0) {
          return;
        }
        paramBluetoothGattCharacteristic = paramBluetoothGattCharacteristic.getDescriptor(CCCD);
        if (paramBluetoothGattCharacteristic == null) {
          paramBluetoothGattCharacteristic = packetWriter;
        }
      }
    }
    for (Object localObject = "clientConfig == null";; localObject = "BluetoothAdapter not initialized")
    {
      Log.e(paramBluetoothGattCharacteristic, (String)localObject);
      return;
      if (bluetoothGatt == null) {
        break;
      }
      paramBluetoothGattCharacteristic.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
      bluetoothGatt.writeDescriptor(paramBluetoothGattCharacteristic);
      return;
      paramBluetoothGattCharacteristic = packetWriter;
    }
  }
  
  public void writeCharacteristic(BluetoothGattCharacteristic paramBluetoothGattCharacteristic)
  {
    if (bluetoothAdapter != null)
    {
      BluetoothGatt localBluetoothGatt = bluetoothGatt;
      if (localBluetoothGatt != null)
      {
        localBluetoothGatt.writeCharacteristic(paramBluetoothGattCharacteristic);
        return;
      }
    }
    Log.d(packetWriter, "BluetoothAdapter?????");
  }
  
  public void writeCharacteristic(byte[] paramArrayOfByte)
  {
    Object localObject = bluetoothGatt;
    if (localObject == null) {
      return;
    }
    int i = _appType;
    if (i == 2)
    {
      localObject = ((BluetoothGatt)localObject).getService(RX_SERVICE_UUID3);
      if (localObject == null)
      {
        Log.e(packetWriter, "RxService3 == null");
        return;
      }
      localObject = ((BluetoothGattService)localObject).getCharacteristic(RX_CHAR_UUID3);
      if (localObject == null)
      {
        Log.e(packetWriter, "sendCharacteristic3 == null");
        return;
      }
      ((BluetoothGattCharacteristic)localObject).setValue(paramArrayOfByte);
      if (bluetoothGatt.writeCharacteristic((BluetoothGattCharacteristic)localObject)) {
        break label372;
      }
    }
    else if (i == 3)
    {
      localObject = ((BluetoothGatt)localObject).getService(RX_SERVICE_UUID4);
      if (localObject == null)
      {
        Log.e(packetWriter, "RxService4 == null");
        return;
      }
      localObject = ((BluetoothGattService)localObject).getCharacteristic(RX_CHAR_UUID4);
      if (localObject == null)
      {
        Log.e(packetWriter, "sendCharacteristic4 == null");
        return;
      }
      ((BluetoothGattCharacteristic)localObject).setValue(paramArrayOfByte);
      if (bluetoothGatt.writeCharacteristic((BluetoothGattCharacteristic)localObject)) {
        break label372;
      }
    }
    else if (i == 4)
    {
      localObject = ((BluetoothGatt)localObject).getService(RX_SERVICE_UUID5);
      if (localObject == null)
      {
        Log.e(packetWriter, "RxService4 == null");
        return;
      }
      localObject = ((BluetoothGattService)localObject).getCharacteristic(RX_CHAR_UUID5);
      if (localObject == null)
      {
        Log.e(packetWriter, "sendCharacteristic5 == null");
        return;
      }
      ((BluetoothGattCharacteristic)localObject).setValue(paramArrayOfByte);
      if (bluetoothGatt.writeCharacteristic((BluetoothGattCharacteristic)localObject)) {
        break label372;
      }
    }
    else if (i == 5)
    {
      localObject = ((BluetoothGatt)localObject).getService(RX_SERVICE_UUID5);
      if (localObject == null)
      {
        Log.e(packetWriter, "RxService4 == null");
        return;
      }
      localObject = ((BluetoothGattService)localObject).getCharacteristic(RX_CHAR_UUID6);
      if (localObject == null)
      {
        Log.e(packetWriter, "sendCharacteristic5 == null");
        return;
      }
      ((BluetoothGattCharacteristic)localObject).setValue(paramArrayOfByte);
      if (bluetoothGatt.writeCharacteristic((BluetoothGattCharacteristic)localObject)) {
        break label372;
      }
    }
    else
    {
      localObject = ((BluetoothGatt)localObject).getService(RX_SERVICE_UUID);
      if (localObject == null)
      {
        Log.e(packetWriter, "RxService == null");
        return;
      }
      localObject = ((BluetoothGattService)localObject).getCharacteristic(RX_CHAR_UUID);
      if (localObject == null)
      {
        Log.e(packetWriter, "sendCharacteristic == null");
        return;
      }
      ((BluetoothGattCharacteristic)localObject).setValue(paramArrayOfByte);
      if (bluetoothGatt.writeCharacteristic((BluetoothGattCharacteristic)localObject)) {
        break label372;
      }
    }
    Log.e(packetWriter, "??????");
    return;
    label372:
    Log.d(packetWriter, "??????");
  }
}
