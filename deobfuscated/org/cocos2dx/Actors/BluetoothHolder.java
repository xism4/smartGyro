package org.cocos2dx.Actors;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class BluetoothHolder
{
  private static final String LOGTAG = "BluetoothHolder";
  public static final UUID UUID_NOTIFY;
  public static final UUID UUID_NOTIFY2 = UUID.fromString("6E400002-B5A3-F393-E0A9-E50E24DCCA9E");
  public static final UUID UUID_NOTIFY3;
  public static final UUID UUID_NOTIFY4;
  public static final UUID UUID_NOTIFY5;
  public static final UUID UUID_NOTIFY6;
  public static final UUID UUID_Read3;
  public static final UUID UUID_Read4;
  public static final UUID UUID_Read5;
  public static final UUID UUID_Read6;
  public static final UUID UUID_SERVICE;
  public static final UUID UUID_SERVICE2 = UUID.fromString("6E400001-B5A3-F393-E0A9-E50E24DCCA9E");
  public static final UUID UUID_SERVICE3;
  public static final UUID UUID_SERVICE4;
  public static final UUID UUID_SERVICE5;
  private static int _isSmallType = 1;
  private static BluetoothHolder holder;
  private boolean bindService;
  private BluetoothAdapter bluetoothAdapter;
  private BluetoothLeService bluetoothLeService;
  private BluetoothManager bluetoothManager;
  private BroadcastCallback broadcastCallback;
  private final BroadcastReceiver broadcastReceiver = new WalletTransactionsFragment.TransactionsLoader.2(this);
  private ArrayList<BluetoothGattCharacteristic> characteristics = new ArrayList();
  private boolean connected;
  private Context context;
  private String deviceAddress;
  private String deviceName;
  private HashMap<BluetoothGattService, ArrayList<BluetoothGattCharacteristic>> gattServiceData;
  private BluetoothGattCharacteristic notifyCharacteristic;
  private boolean scanning;
  private final ServiceConnection serviceConnection = new OpenPgpServiceConnection.1(this);
  
  static
  {
    UUID_NOTIFY = UUID.fromString("0000ffe1-0000-1000-8000-00805f9b34fb");
    UUID_SERVICE = UUID.fromString("0000ffe0-0000-1000-8000-00805f9b34fb");
    UUID_SERVICE3 = UUID.fromString("0000ae00-0000-1000-8000-00805f9b34fb");
    UUID_NOTIFY3 = UUID.fromString("0000ae01-0000-1000-8000-00805f9b34fb");
    UUID_Read3 = UUID.fromString("0000ae02-0000-1000-8000-00805f9b34fb");
    UUID_SERVICE4 = UUID.fromString("0000ffe0-0000-1000-8000-00805f9b34fb");
    UUID_NOTIFY4 = UUID.fromString("0000fff3-0000-1000-8000-00805f9b34fb");
    UUID_Read4 = UUID.fromString("0000fff4-0000-1000-8000-00805f9b34fb");
    UUID_SERVICE5 = UUID.fromString("0000fff0-0000-1000-8000-00805f9b34fb");
    UUID_NOTIFY5 = UUID.fromString("0000fff3-0000-1000-8000-00805f9b34fb");
    UUID_Read5 = UUID.fromString("0000fff7-0000-1000-8000-00805f9b34fb");
    UUID_NOTIFY6 = UUID.fromString("0000fff2-0000-1000-8000-00805f9b34fb");
    UUID_Read6 = UUID.fromString("0000fff1-0000-1000-8000-00805f9b34fb");
    holder = new BluetoothHolder();
  }
  
  private BluetoothHolder() {}
  
  public static int IsSmallType()
  {
    return _isSmallType;
  }
  
  public static void SetIsSmallType(int paramInt)
  {
    _isSmallType = paramInt;
  }
  
  public static BluetoothHolder getInstance()
  {
    BluetoothHolder localBluetoothHolder = holder;
    if (context != null) {
      return localBluetoothHolder;
    }
    throw new NullPointerException("??????????BluetoothHolder.init(context)");
  }
  
  public static boolean init(Context paramContext)
  {
    BluetoothHolder localBluetoothHolder = holder;
    context = paramContext;
    return localBluetoothHolder.initBluetootAdaper();
  }
  
  private static IntentFilter makeGattUpdateIntentFilter()
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("ACTION_GATT_CONNECTED");
    localIntentFilter.addAction("ACTION_GATT_DISCONNECTED");
    localIntentFilter.addAction("ACTION_GATT_SERVICES_DISCOVERED");
    localIntentFilter.addAction("ACTION_DATA_AVAILABLE");
    return localIntentFilter;
  }
  
  private void searchGattServices(List paramList)
  {
    if (paramList == null) {
      return;
    }
    gattServiceData = new HashMap();
    Log.d(LOGTAG, "?????????");
    Iterator localIterator1 = paramList.iterator();
    while (localIterator1.hasNext())
    {
      BluetoothGattService localBluetoothGattService = (BluetoothGattService)localIterator1.next();
      paramList = LOGTAG;
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Sevice uuid : ");
      ((StringBuilder)localObject).append(localBluetoothGattService.getUuid().toString());
      Log.d(paramList, ((StringBuilder)localObject).toString());
      if (localBluetoothGattService.getUuid().toString().equalsIgnoreCase(UUID_SERVICE.toString())) {
        if (_isSmallType != 1) {}
      }
      int i;
      for (;;)
      {
        i = 3;
        break label244;
        i = 0;
        break label244;
        if (localBluetoothGattService.getUuid().toString().equalsIgnoreCase(UUID_SERVICE2.toString()))
        {
          _isSmallType = 1;
          i = 1;
          break label244;
        }
        if (localBluetoothGattService.getUuid().toString().equalsIgnoreCase(UUID_SERVICE3.toString()))
        {
          _isSmallType = 1;
          i = 2;
          break label244;
        }
        if (!localBluetoothGattService.getUuid().toString().equalsIgnoreCase(UUID_SERVICE4.toString())) {
          break;
        }
        _isSmallType = 1;
      }
      if (localBluetoothGattService.getUuid().toString().equalsIgnoreCase(UUID_SERVICE5.toString()))
      {
        _isSmallType = 1;
        i = 4;
        label244:
        ArrayList localArrayList = new ArrayList();
        bluetoothLeService.SetAppType(i);
        paramList = LOGTAG;
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("type:");
        ((StringBuilder)localObject).append(i);
        Log.d(paramList, ((StringBuilder)localObject).toString());
        String str2 = UUID_NOTIFY2.toString();
        if (i == 0) {
          paramList = UUID_NOTIFY;
        }
        for (;;)
        {
          str2 = paramList.toString();
          break;
          if (i == 2)
          {
            paramList = UUID_NOTIFY3;
          }
          else if (i == 3)
          {
            paramList = UUID_NOTIFY4;
          }
          else
          {
            if (i != 4) {
              break;
            }
            paramList = UUID_NOTIFY5;
          }
        }
        Iterator localIterator2 = localBluetoothGattService.getCharacteristics().iterator();
        while (localIterator2.hasNext())
        {
          BluetoothGattCharacteristic localBluetoothGattCharacteristic = (BluetoothGattCharacteristic)localIterator2.next();
          bluetoothLeService.setCharacteristicNotification(localBluetoothGattCharacteristic, true, _isSmallType);
          if (localBluetoothGattCharacteristic.getUuid().toString().equalsIgnoreCase(str2))
          {
            localArrayList.add(localBluetoothGattCharacteristic);
            paramList = LOGTAG;
          }
          for (localObject = new StringBuilder();; localObject = new StringBuilder())
          {
            ((StringBuilder)localObject).append("???:");
            ((StringBuilder)localObject).append(localBluetoothGattCharacteristic.getUuid().toString());
            localObject = ((StringBuilder)localObject).toString();
            Log.d(paramList, (String)localObject);
            break label764;
            if (!localBluetoothGattCharacteristic.getUuid().toString().equalsIgnoreCase(UUID_NOTIFY6.toString())) {
              break;
            }
            if (i == 4) {
              bluetoothLeService.SetAppType(5);
            }
            localArrayList.add(localBluetoothGattCharacteristic);
            paramList = LOGTAG;
          }
          if (localBluetoothGattCharacteristic.getUuid().toString().equalsIgnoreCase(UUID_Read3.toString()))
          {
            localArrayList.add(localBluetoothGattCharacteristic);
          }
          else
          {
            String str1;
            if (localBluetoothGattCharacteristic.getUuid().toString().equalsIgnoreCase(UUID_Read4.toString()))
            {
              localArrayList.add(localBluetoothGattCharacteristic);
              paramList = LOGTAG;
              localObject = new StringBuilder();
              str1 = "UUID_Read4:";
            }
            for (;;)
            {
              ((StringBuilder)localObject).append(str1);
              ((StringBuilder)localObject).append(localBluetoothGattCharacteristic.getUuid().toString());
              localObject = ((StringBuilder)localObject).toString();
              break;
              if (localBluetoothGattCharacteristic.getUuid().toString().equalsIgnoreCase(UUID_Read5.toString()))
              {
                localArrayList.add(localBluetoothGattCharacteristic);
                paramList = LOGTAG;
                localObject = new StringBuilder();
                str1 = "UUID_Read5:";
              }
              else if (localBluetoothGattCharacteristic.getUuid().toString().equalsIgnoreCase(UUID_Read6.toString()))
              {
                if (i == 4) {
                  bluetoothLeService.SetAppType(5);
                }
                localArrayList.add(localBluetoothGattCharacteristic);
                paramList = LOGTAG;
                localObject = new StringBuilder();
                str1 = "UUID_Read6:";
              }
              else
              {
                paramList = LOGTAG;
                localObject = new StringBuilder();
                str1 = "UUID_NOTIFY=";
              }
            }
          }
          label764:
          localArrayList.add(localBluetoothGattCharacteristic);
          paramList = LOGTAG;
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("???:");
          ((StringBuilder)localObject).append(localBluetoothGattCharacteristic.getUuid().toString());
          Log.d(paramList, ((StringBuilder)localObject).toString());
        }
        gattServiceData.put(localBluetoothGattService, localArrayList);
      }
    }
  }
  
  public void bindService()
  {
    if (!bindService)
    {
      Log.d(LOGTAG, "????");
      Intent localIntent = new Intent(context, org.cocos2dx.cpp.BluetoothLeService.class);
      bindService = context.bindService(localIntent, serviceConnection, 1);
      if (bindService)
      {
        Log.d(LOGTAG, "????");
        return;
      }
      Log.e(LOGTAG, "????");
    }
  }
  
  public boolean connect(String paramString1, String paramString2)
  {
    Log.e(LOGTAG, "connect");
    if (bluetoothLeService != null)
    {
      deviceName = paramString1;
      deviceAddress = paramString2;
      AppSaveData.getInstance().setDataForString("deviceAddress", deviceAddress);
      return bluetoothLeService.connect(deviceAddress);
    }
    Log.e(LOGTAG, "bluetoothLeService == null");
    return false;
  }
  
  public void disconnect()
  {
    characteristics.clear();
    if (bluetoothLeService != null)
    {
      Log.d(LOGTAG, "????");
      bluetoothLeService.disconnect();
    }
  }
  
  public void enableTXNotification()
  {
    if (_isSmallType == 0) {
      return;
    }
    BluetoothLeService localBluetoothLeService = bluetoothLeService;
    if (localBluetoothLeService != null) {
      localBluetoothLeService.enableTXNotification();
    }
  }
  
  public String getDeviceAddress()
  {
    return deviceAddress;
  }
  
  public String getDeviceName()
  {
    return deviceName;
  }
  
  public boolean initBluetootAdaper()
  {
    if (isSupportedBLE())
    {
      bluetoothManager = ((BluetoothManager)context.getSystemService("bluetooth"));
      bluetoothAdapter = bluetoothManager.getAdapter();
      if (bluetoothAdapter == null) {
        Log.e(LOGTAG, "bluetoothAdapter == null");
      }
      return true;
    }
    Log.d(LOGTAG, "?????");
    return false;
  }
  
  public boolean isBindService()
  {
    return bindService;
  }
  
  public boolean isConnected()
  {
    BluetoothLeService localBluetoothLeService = bluetoothLeService;
    if (localBluetoothLeService == null) {
      return false;
    }
    return localBluetoothLeService.getConnectionState() > 0;
  }
  
  public boolean isOpen()
  {
    BluetoothAdapter localBluetoothAdapter = bluetoothAdapter;
    if (localBluetoothAdapter != null) {
      return localBluetoothAdapter.isEnabled();
    }
    return false;
  }
  
  public boolean isScanning()
  {
    return scanning;
  }
  
  public boolean isSupportedBLE()
  {
    return context.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le");
  }
  
  public boolean openBluetooth()
  {
    if ((bluetoothAdapter != null) && (!isOpen()))
    {
      Log.d(LOGTAG, "????");
      return bluetoothAdapter.enable();
    }
    return false;
  }
  
  public boolean reConnect()
  {
    Log.d(LOGTAG, "reConnect");
    if (bluetoothLeService != null)
    {
      deviceAddress = AppSaveData.getInstance().getDataForString("deviceAddress", deviceAddress);
      String str = LOGTAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("reConnect:deviceAddress");
      localStringBuilder.append(deviceAddress);
      Log.d(str, localStringBuilder.toString());
      return bluetoothLeService.connect(deviceAddress);
    }
    Log.e(LOGTAG, "bluetoothLeService == null");
    return false;
  }
  
  public void readCharacteristic()
  {
    Object localObject1 = gattServiceData;
    if ((localObject1 != null) && (!((HashMap)localObject1).isEmpty()))
    {
      localObject1 = gattServiceData.values().iterator();
      while (((Iterator)localObject1).hasNext())
      {
        Object localObject2 = (ArrayList)((Iterator)localObject1).next();
        if (localObject2 == null) {
          return;
        }
        localObject2 = ((ArrayList)localObject2).iterator();
        while (((Iterator)localObject2).hasNext())
        {
          BluetoothGattCharacteristic localBluetoothGattCharacteristic1 = (BluetoothGattCharacteristic)((Iterator)localObject2).next();
          int i = localBluetoothGattCharacteristic1.getProperties();
          if ((i | 0x2) > 0)
          {
            BluetoothGattCharacteristic localBluetoothGattCharacteristic2 = notifyCharacteristic;
            if (localBluetoothGattCharacteristic2 != null)
            {
              bluetoothLeService.setCharacteristicNotification(localBluetoothGattCharacteristic2, false, _isSmallType);
              notifyCharacteristic = null;
            }
            bluetoothLeService.readCharacteristic(localBluetoothGattCharacteristic1);
          }
          if ((i | 0x10) > 0)
          {
            notifyCharacteristic = localBluetoothGattCharacteristic1;
            bluetoothLeService.setCharacteristicNotification(notifyCharacteristic, true, _isSmallType);
          }
        }
      }
    }
  }
  
  public void registerReceiver(BroadcastCallback paramBroadcastCallback)
  {
    Log.e(LOGTAG, "?????????");
    broadcastCallback = paramBroadcastCallback;
    context.registerReceiver(broadcastReceiver, makeGattUpdateIntentFilter());
    if ((bluetoothLeService != null) && (!TextUtils.isEmpty(deviceAddress)))
    {
      boolean bool = bluetoothLeService.connect(deviceAddress);
      paramBroadcastCallback = LOGTAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("????????");
      localStringBuilder.append(bool);
      Log.d(paramBroadcastCallback, localStringBuilder.toString());
    }
  }
  
  public void scanDevice(boolean paramBoolean, BluetoothAdapter.LeScanCallback paramLeScanCallback)
  {
    if (bluetoothAdapter != null)
    {
      scanning = paramBoolean;
      if (scanning)
      {
        Log.d(LOGTAG, "??????");
        bluetoothAdapter.startLeScan(null, paramLeScanCallback);
        return;
      }
      Log.d(LOGTAG, "??????");
      bluetoothAdapter.stopLeScan(paramLeScanCallback);
      return;
    }
    Log.d(LOGTAG, "bluetoothAdapter == null");
  }
  
  public void unbindService(boolean paramBoolean)
  {
    if (bindService)
    {
      Log.d(LOGTAG, "??????");
      context.unbindService(serviceConnection);
    }
    BluetoothLeService localBluetoothLeService = bluetoothLeService;
    if (localBluetoothLeService != null) {
      localBluetoothLeService.close();
    }
    bindService = false;
    bluetoothLeService = null;
  }
  
  public void unregisterReceiver(boolean paramBoolean)
  {
    context.unregisterReceiver(broadcastReceiver);
    broadcastCallback = null;
    if (paramBoolean) {
      deviceAddress = null;
    }
  }
  
  public void writeCharacteristic(byte[] paramArrayOfByte)
  {
    Object localObject = bluetoothLeService;
    if (localObject == null)
    {
      Log.e(LOGTAG, "bluetoothLeService == null");
      return;
    }
    if (_isSmallType == 1)
    {
      ((BluetoothService)localObject).writeCharacteristic(paramArrayOfByte);
      return;
    }
    localObject = gattServiceData;
    if ((localObject != null) && (!((HashMap)localObject).isEmpty()))
    {
      localObject = gattServiceData.values().iterator();
      while (((Iterator)localObject).hasNext())
      {
        Iterator localIterator = ((ArrayList)((Iterator)localObject).next()).iterator();
        while (localIterator.hasNext())
        {
          BluetoothGattCharacteristic localBluetoothGattCharacteristic = (BluetoothGattCharacteristic)localIterator.next();
          int i = localBluetoothGattCharacteristic.getProperties();
          notifyCharacteristic = null;
          if ((i | 0x10) > 0)
          {
            notifyCharacteristic = localBluetoothGattCharacteristic;
            BluetoothLeService localBluetoothLeService = bluetoothLeService;
            if (localBluetoothLeService != null) {
              localBluetoothLeService.setCharacteristicNotification(localBluetoothGattCharacteristic, true, _isSmallType);
            }
          }
          if ((i | 0x8) > 0)
          {
            localBluetoothGattCharacteristic.setValue(paramArrayOfByte);
            if (notifyCharacteristic != null) {
              bluetoothLeService.writeCharacteristic(localBluetoothGattCharacteristic);
            }
          }
        }
      }
    }
  }
}