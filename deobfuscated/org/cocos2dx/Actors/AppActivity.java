package org.cocos2dx.Actors;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.bluetooth.BluetoothDevice;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import android.support.v4.app.b;
import android.support.v7.app.ByteVector;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Window;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.cocos2dx.package_1.Cocos2dxActivity;

public class AppActivity
  extends BaseActivity
  implements SensorEventListener
{
  private static String APK_URL_01;
  private static final int CMD_AddDevice_AGV = 50011;
  private static final int CMD_BeginLoadApk = 20011;
  private static final int CMD_CheckCode_AGV = 40011;
  private static final int CMD_Copy = 30011;
  private static final boolean IS_ICS = false;
  private static final String PAGE_KEY = "AppActivity";
  private static final int REQUEST_CODE_LOCATION_SETTINGS = 2000;
  private static String _DevicName;
  private static String _bleName;
  private static String _defaultDeviceName;
  private static String _emailFrom;
  private static String _emailMessage;
  private static boolean _isAutoConnect;
  private static ArrayList<byte[]> _msgList;
  private static String _orderID;
  private static String _phoneNum;
  private static String _url;
  private static Activity a;
  private static BluetoothHolder bluetoothHolder;
  private static int delayTime;
  private static ArrayList<BluetoothDevice> devices;
  private static AppActivity instance;
  private static boolean isSupportBLE;
  private static ArrayList<Integer> rissList;
  private final int CMD_GET_CHECKCODE = 264830;
  private final int CMD_LOGIN1 = 789461;
  private final int CMD_LOGIN2 = 789462;
  private final int CMD_LOGIN3 = 789463;
  private final int CMD_REGISTER = 789464;
  private final int CMD_ShowDialogGPS = 10003;
  private final String CODE_TABLE = "CheckCode";
  private final String DEVICE_TABLE = "Device";
  private final String KEY_FRIENDS = "Friends";
  private final String KEY_PSW = "Password";
  private final String KEY_USERNAME = "UserName";
  private final int PERMISSON_LOCATION = 800;
  private final int PERMISSON_SCAN = 1001;
  private final String USER_TABLE = "NormalUser";
  private String _checkCode = "";
  private int _checkTime = 0;
  private int _checkTime2 = 0;
  private int _deviceID = 0;
  private int _fun = 0;
  boolean _haveInitBLE = false;
  private int _isChinese = 0;
  private boolean _isFirstInit = true;
  private String _psw = "";
  int _rssi = 0;
  private String _userName = "";
  LocationManager calc;
  LocationListener gpsLocationListener = new GpsMyLocationProvider(this);
  boolean haveSend = false;
  Double lat;
  private final BluetoothAdapter.LeScanCallback leScanCallback = new UploadActivity.2(this);
  Double lon;
  Location mLocation;
  private Sensor mSensor;
  private SensorManager mSensorManager;
  Handler myHandler = new SlidingDrawer.SlidingHandler(this);
  private String[] permission = { "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION" };
  
  public AppActivity() {}
  
  private void AddNewDevice2() {}
  
  public static void AddOtherM3(String paramString)
  {
    _bleName = paramString;
    instance.SendMsg(1);
  }
  
  public static void BeginLoadApk(String paramString)
  {
    APK_URL_01 = paramString;
    instance.SendMsg(20011);
  }
  
  public static native void CallBackOnTouchCopy(String paramString);
  
  public static native void CallBackRegist(int paramInt);
  
  private void ChangePos(int paramInt1, int paramInt2, int paramInt3)
  {
    int k;
    if (paramInt1 < 0) {
      k = paramInt1 * -1;
    } else {
      k = paramInt1;
    }
    int m;
    if (paramInt2 < 0) {
      m = paramInt2 * -1;
    } else {
      m = paramInt2;
    }
    int i = paramInt1;
    int j = paramInt2;
    if (paramInt3 < 0) {
      if (m > k)
      {
        if (paramInt2 > 0)
        {
          j = 20 - paramInt2;
          i = paramInt1;
        }
        else
        {
          j = -20 - paramInt2;
          i = paramInt1;
        }
      }
      else if (paramInt1 > 0)
      {
        i = 20 - paramInt1;
        j = paramInt2;
      }
      else
      {
        i = -20 - paramInt1;
        j = paramInt2;
      }
    }
    paramInt3 = j * -1;
    paramInt1 = i * -1;
    paramInt2 = 0;
    if (paramInt1 > 1) {
      paramInt1 -= 1;
    } else if (paramInt1 < -1) {
      paramInt1 += 1;
    } else {
      paramInt1 = 0;
    }
    if (paramInt3 > 1) {
      paramInt2 = paramInt3 - 1;
    } else if (paramInt3 < -1) {
      paramInt2 = paramInt3 + 1;
    }
    OnAccuracyChanged(paramInt1 * 2, paramInt2 * 2);
  }
  
  private boolean CheckNet()
  {
    return true;
  }
  
  public static native void CkeckCodeCallBack(int paramInt);
  
  public static native void FinishLoad();
  
  public static String GetCurTime()
  {
    return "";
  }
  
  public static int GetGameRunTime(String paramString)
  {
    return 0;
  }
  
  public static Object GetInstance()
  {
    return instance;
  }
  
  public static String GetPhoneNumber()
  {
    Object localObject = instance;
    try
    {
      int i = com.org.android.ui.Resources.checkSelfPermission((Context)localObject, "android.permission.READ_SMS");
      if (i != 0)
      {
        localObject = instance;
        i = com.org.android.ui.Resources.checkSelfPermission((Context)localObject, "android.permission.READ_PHONE_NUMBERS");
        if (i != 0)
        {
          localObject = instance;
          i = com.org.android.ui.Resources.checkSelfPermission((Context)localObject, "android.permission.READ_PHONE_STATE");
          if (i != 0) {
            return "";
          }
        }
      }
      localObject = instance;
      localObject = ((Activity)localObject).getSystemService("phone");
      localObject = (TelephonyManager)localObject;
      localObject = ((TelephonyManager)localObject).getLine1Number();
      if (localObject == null)
      {
        Log.e("GetPhoneNumber:", "????????");
        return "";
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("getLine1Number:");
      localStringBuilder.append((String)localObject);
      Log.d("GetPhoneNumber", localStringBuilder.toString());
      return localObject;
    }
    catch (Exception localException)
    {
      Log.e("GetPhoneNumber", localException.toString());
    }
    return "";
  }
  
  public static String GetScanBleList()
  {
    return _DevicName;
  }
  
  public static native void LoadCallBack(int paramInt1, int paramInt2);
  
  static native void LoadTaoBao();
  
  public static native void OnAccuracyChanged(int paramInt1, int paramInt2);
  
  public static void OpenURL(String paramString)
  {
    _url = paramString;
    instance.SendMsg(100);
  }
  
  public static native void ReadMsg(byte[] paramArrayOfByte);
  
  public static native void ReadMsg2(byte[] paramArrayOfByte);
  
  public static native void SendEmailCallBack(String paramString);
  
  public static native void SendEmailCallBack2(int paramInt);
  
  public static void SendMessage(byte[] paramArrayOfByte, int paramInt)
  {
    if (instance.IsConnected())
    {
      byte[] arrayOfByte = new byte[paramInt];
      int i = 0;
      while (i < paramInt)
      {
        arrayOfByte[i] = paramArrayOfByte[i];
        i += 1;
      }
      BluetoothHolder.getInstance().writeCharacteristic(arrayOfByte);
    }
  }
  
  private void SendMsg(int paramInt)
  {
    Message localMessage = new Message();
    what = paramInt;
    myHandler.sendMessage(localMessage);
  }
  
  public static void SetDefaultConn(String paramString)
  {
    instance.DisConnect();
    _defaultDeviceName = paramString;
    instance.BeginScanBLE();
    _isAutoConnect = true;
  }
  
  public static void SetDefaultConn2(String paramString)
  {
    instance.DisConnect();
    BluetoothHolder.getInstance().reConnect();
  }
  
  public static native void SetDeviceID(int paramInt);
  
  private void bluetoothDestroy(boolean paramBoolean)
  {
    try
    {
      DisConnect();
      BluetoothHolder localBluetoothHolder = bluetoothHolder;
      localBluetoothHolder.unbindService(paramBoolean);
      localBluetoothHolder = bluetoothHolder;
      localBluetoothHolder.unregisterReceiver(paramBoolean);
      _haveInitBLE = false;
      return;
    }
    catch (Exception localException)
    {
      Log.e("bluetoothDestroy", localException.toString());
    }
  }
  
  private void bluetoothResume()
  {
    bluetoothHolder = BluetoothHolder.getInstance();
    BluetoothHolder localBluetoothHolder = bluetoothHolder;
    isSupportBLE = BluetoothHolder.init(this);
    bluetoothHolder.openBluetooth();
    bluetoothHolder.bindService();
    bluetoothHolder.registerReceiver(this);
    _haveInitBLE = true;
    Log.d("AppActivity", "buletoothResume");
  }
  
  public static String byteToHex(byte paramByte)
  {
    return new String(new char[] { Character.forDigit(paramByte >> 4 & 0xF, 16), Character.forDigit(paramByte & 0xF, 16) });
  }
  
  public static void cancelVibrate()
  {
    ((Vibrator)instance.getSystemService("vibrator")).cancel();
  }
  
  private void checkCode(String paramString1, String paramString2, boolean paramBoolean) {}
  
  public static AppActivity getInstance()
  {
    return instance;
  }
  
  private void initPermission()
  {
    ArrayList localArrayList = new ArrayList();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("BeginScanBLE() b:");
    localStringBuilder.append(Build.VERSION.SDK_INT);
    Log.d("AppActivity", localStringBuilder.toString());
    if (Build.VERSION.SDK_INT >= 31)
    {
      localArrayList.add("android.permission.BLUETOOTH_CONNECT");
      localArrayList.add("android.permission.BLUETOOTH_SCAN");
      Log.d("AppActivity", "BeginScanBLE() 12");
    }
    if (localArrayList.size() > 0) {
      b.a(this, (String[])localArrayList.toArray(new String[0]), 1001);
    }
  }
  
  public static boolean isAppInstalled(Context paramContext, String paramString)
  {
    paramContext = new StringBuilder();
    paramContext.append("/data/data/");
    paramContext.append(paramString);
    return new File(paramContext.toString()).exists();
  }
  
  public static final boolean isLocationEnable(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      try
      {
        int i = Settings.Secure.getInt(paramContext.getContentResolver(), "location_mode");
        if (i == 0) {
          break label48;
        }
        return true;
      }
      catch (Settings.SettingNotFoundException paramContext)
      {
        paramContext.printStackTrace();
        return false;
      }
    } else {
      return TextUtils.isEmpty(Settings.Secure.getString(paramContext.getContentResolver(), "location_providers_allowed")) ^ true;
    }
    label48:
    return false;
  }
  
  private void setLocationService()
  {
    startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 2000);
  }
  
  private void showMissingGPSPermissionDialog()
  {
    ByteVector localByteVector = new ByteVector(this);
    String str;
    if (_isChinese == 1) {
      str = "??";
    } else {
      str = "Tip";
    }
    localByteVector.b(str);
    if (_isChinese == 1) {
      str = "??????????????(GPS)";
    } else {
      str = "Searching for Bluetooth devices requires location service (GPS) to be turned on ";
    }
    localByteVector.a(str);
    if (_isChinese == 1) {
      str = "??";
    } else {
      str = "Cancel";
    }
    localByteVector.b(str, null);
    if (_isChinese == 1) {
      str = "???";
    } else {
      str = "Go to Settings";
    }
    localByteVector.a(str, new FileBrowser.9(this));
    localByteVector.b(false);
    localByteVector.a();
    localByteVector.b();
  }
  
  private void showMissingPermissionDialog()
  {
    try
    {
      ByteVector localByteVector = new ByteVector(this);
      int i = _isChinese;
      String str;
      if (i == 1) {
        str = "??";
      } else {
        str = "Tip";
      }
      localByteVector.b(str);
      i = _isChinese;
      if (i == 1) {
        str = "???????????????????????\\\"??\\\"-\\\"????\\\"-??????";
      } else {
        str = "The application needs to get the location permission to scan to the Bluetooth device. Please click \\\"Settings\\\"-\\\"Permissions\\\"-Get location info";
      }
      localByteVector.a(str);
      i = _isChinese;
      if (i == 1) {
        str = "??";
      } else {
        str = "Cancel";
      }
      localByteVector.b(str, new ProfileActivity.2(this));
      i = _isChinese;
      if (i == 1) {
        str = "??";
      } else {
        str = "Settings";
      }
      localByteVector.a(str, new WhitelistFragment.3(this));
      localByteVector.b(false);
      localByteVector.b();
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
  
  private void showMissingPermissionDialog2()
  {
    try
    {
      ByteVector localByteVector = new ByteVector(this);
      int i = _isChinese;
      String str;
      if (i == 1) {
        str = "??";
      } else {
        str = "Tip";
      }
      localByteVector.b(str);
      i = _isChinese;
      if (i == 1) {
        str = "????????????????????????????\\\"??\\\"-\\\"????\\\"-???????";
      } else {
        str = "The application needs to get the connect to nearby devices permission to scan to the Bluetooth device. Please click \\\"Settings\\\"-\\\"Permissions\\\"-Connect to nearby devices";
      }
      localByteVector.a(str);
      i = _isChinese;
      if (i == 1) {
        str = "??";
      } else {
        str = "Cancel";
      }
      localByteVector.b(str, new NoteEditor.4(this));
      i = _isChinese;
      if (i == 1) {
        str = "??";
      } else {
        str = "Settings";
      }
      localByteVector.a(str, new FileBrowser.11(this));
      localByteVector.b(false);
      localByteVector.b();
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
  
  private static List sortByValueFloatDesc(Map paramMap)
  {
    paramMap = new ArrayList(paramMap.entrySet());
    Collections.sort(paramMap, new SearchResultsActivity.1());
    return paramMap;
  }
  
  private void startAppSettings()
  {
    try
    {
      Intent localIntent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("package:");
      localStringBuilder.append(getPackageName());
      localIntent.setData(Uri.parse(localStringBuilder.toString()));
      startActivity(localIntent);
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
  
  public static void startTaobaoApp()
  {
    if (isAppInstalled(instance, "com.taobao.taobao"))
    {
      Intent localIntent = new Intent();
      localIntent.setAction("Android.intent.action.VIEW");
      localIntent.setFlags(268435456);
      localIntent.setData(Uri.parse("https://item.taobao.com/item.htm?spm=a1z10.1-c-s.w4004-23989050866.2.108c2ea4jRnp21&id=652257996818"));
      localIntent.setClassName("com.taobao.taobao", "com.taobao.tao.detail.activity.DetailActivity");
      instance.startActivity(localIntent);
      return;
    }
    LoadTaoBao();
    Log.e("startTaobaoApp", "????????????");
  }
  
  private boolean verifyPermissions(int[] paramArrayOfInt)
  {
    try
    {
      int j = paramArrayOfInt.length;
      int i = 0;
      while (i < j)
      {
        if (paramArrayOfInt[i] != 0) {
          return false;
        }
        i += 1;
      }
      return true;
    }
    catch (Throwable paramArrayOfInt)
    {
      paramArrayOfInt.printStackTrace();
    }
  }
  
  public static void vibrate(long paramLong)
  {
    ((Vibrator)instance.getSystemService("vibrator")).vibrate(paramLong);
  }
  
  public static void vibrateWithPattern(long[] paramArrayOfLong, int paramInt)
  {
    ((Vibrator)instance.getSystemService("vibrator")).vibrate(paramArrayOfLong, paramInt);
  }
  
  public void ActivityAGV(String paramString, int paramInt1, int paramInt2)
  {
    _checkCode = paramString;
    _fun = paramInt1;
    _deviceID = paramInt2;
    Log.d("AppActivity", "ActivityAGV");
    SendMsg(40011);
  }
  
  void AddDeviceToService(int paramInt1, int paramInt2) {}
  
  public void AddNewDevice()
  {
    Log.d("AppActivity", "AddNewDevice");
    SendMsg(50011);
  }
  
  public void BeginScanBLE()
  {
    _isAutoConnect = false;
    if (Build.VERSION.SDK_INT >= 23)
    {
      ArrayList localArrayList = new ArrayList();
      String[] arrayOfString = permission;
      int j = arrayOfString.length;
      int i = 0;
      while (i < j)
      {
        String str = arrayOfString[i];
        if (com.org.android.ui.Resources.checkSelfPermission(this, str) != 0) {
          localArrayList.add(str);
        }
        i += 1;
      }
      if (!localArrayList.isEmpty())
      {
        b.a(this, (String[])localArrayList.toArray(new String[localArrayList.size()]), 800);
        return;
      }
    }
    if ((Build.VERSION.SDK_INT >= 23) && (!isLocationEnable(this)))
    {
      instance.SendMsg(10003);
      return;
    }
    if (Build.VERSION.SDK_INT >= 31)
    {
      initPermission();
      if (com.org.android.ui.Resources.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") != 0)
      {
        Log.d("AppActivity", "BeginScanBLE() 55");
        return;
      }
      if (com.org.android.ui.Resources.checkSelfPermission(this, "android.permission.BLUETOOTH_SCAN") != 0)
      {
        Log.d("AppActivity", "BeginScanBLE() 66");
        return;
      }
    }
    _checkTime = 0;
    _checkTime2 = 0;
    devices.clear();
    rissList.clear();
    _msgList.clear();
    _DevicName = "";
    DisConnect();
    if (_haveInitBLE) {
      bluetoothDestroy(true);
    }
    bluetoothResume();
    BluetoothHolder.getInstance().scanDevice(false, leScanCallback);
    BluetoothHolder.getInstance().scanDevice(true, leScanCallback);
  }
  
  void CheckCode(String paramString, int paramInt1, int paramInt2) {}
  
  public void ConnectBLE(int paramInt)
  {
    haveSend = false;
    ReadMsg2((byte[])_msgList.get(paramInt));
    FinshScanBLE();
    BluetoothDevice localBluetoothDevice = (BluetoothDevice)devices.get(paramInt);
    BluetoothHolder.getInstance().connect(localBluetoothDevice.getName(), localBluetoothDevice.getAddress());
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("reConnect:deviceAddress0");
    localStringBuilder.append(localBluetoothDevice.getAddress());
    Log.d("AppActivity", localStringBuilder.toString());
    SetAppType(localBluetoothDevice.getName());
  }
  
  public void DisConnect()
  {
    if (IsConnected()) {
      BluetoothHolder.getInstance().disconnect();
    }
  }
  
  public void FinshScanBLE()
  {
    if (BluetoothHolder.getInstance().isScanning()) {
      BluetoothHolder.getInstance().scanDevice(false, leScanCallback);
    }
  }
  
  public String GetCopyString()
  {
    Object localObject = (ClipboardManager)getSystemService("clipboard");
    if (((ClipboardManager)localObject).getText() != null)
    {
      localObject = ((ClipboardManager)localObject).getText().toString();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("msg:");
      localStringBuilder.append((String)localObject);
      Log.d("GetCopyString", localStringBuilder.toString());
      if (localObject != null) {
        return Pattern.compile("\\s*|\t|\r|\n").matcher((CharSequence)localObject).replaceAll("");
      }
    }
    return "";
  }
  
  public int GetDay()
  {
    return Calendar.getInstance().get(5);
  }
  
  public int GetHour()
  {
    return Calendar.getInstance().get(11);
  }
  
  public int GetMinute()
  {
    return Calendar.getInstance().get(12);
  }
  
  public int GetMonth()
  {
    return Calendar.getInstance().get(2);
  }
  
  int GetRandID()
  {
    double d = Math.random();
    Double.isNaN(2.68435455E8D);
    Double.isNaN(0.0D);
    return (int)(d * 2.68435455E8D + 0.0D);
  }
  
  public int GetSecond()
  {
    return Calendar.getInstance().get(13);
  }
  
  public int GetWeek()
  {
    return Calendar.getInstance().get(7) - 1;
  }
  
  public int GetYear()
  {
    return Calendar.getInstance().get(1);
  }
  
  public boolean IsConnected()
  {
    return BluetoothHolder.getInstance().isConnected();
  }
  
  public void MyPause()
  {
    DisConnect();
  }
  
  public void MyResume() {}
  
  public void OnTouchCopy()
  {
    Log.d("AppActivity", "OnTouchCopy");
    SendMsg(30011);
  }
  
  public void SendEmail(String paramString) {}
  
  public void SendToGetCheckCode(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("phoneNum?");
    localStringBuilder.append(paramString);
    Log.d("SendToGetCheckCode", localStringBuilder.toString());
    _phoneNum = paramString;
    if (_isChinese == 0)
    {
      SendEmail(paramString);
      return;
    }
    SendMsg(264830);
  }
  
  public void SetAppType(String paramString)
  {
    int i;
    if (paramString.startsWith("M6")) {
      i = 0;
    } else {
      i = 1;
    }
    BluetoothHolder.SetIsSmallType(i);
  }
  
  public void TestSendData() {}
  
  public void TouchLogin1(String paramString1, String paramString2)
  {
    _userName = paramString1;
    _phoneNum = paramString1;
    _psw = paramString2;
    SendMsg(789461);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("phoneNum?");
    localStringBuilder.append(paramString1);
    localStringBuilder.append("  psw:");
    localStringBuilder.append(paramString2);
    Log.d("TouchLogin1", localStringBuilder.toString());
  }
  
  public void TouchLogin2(String paramString1, String paramString2)
  {
    _userName = paramString1;
    _phoneNum = paramString1;
    _checkCode = paramString2;
    _psw = "";
    SendMsg(789462);
  }
  
  public void TouchLogin3(String paramString)
  {
    _userName = paramString;
    _phoneNum = paramString;
    _psw = "";
    SendMsg(789463);
  }
  
  public void TouchRegister(String paramString1, String paramString2, String paramString3)
  {
    _userName = paramString1;
    _phoneNum = paramString1;
    _checkCode = paramString2;
    _psw = paramString3;
    SendMsg(789464);
  }
  
  void UpdataDeviceInfo(int paramInt1, int paramInt2) {}
  
  public void addFriend(String paramString) {}
  
  public void addUserToService(String paramString1, String paramString2, String paramString3) {}
  
  public void checkPsw(String paramString1, String paramString2) {}
  
  public void connected(Intent paramIntent)
  {
    Log.e("AppActivity", "connected------");
  }
  
  public void data(Intent paramIntent, byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte != null) {
      try
      {
        ReadMsg(paramArrayOfByte);
      }
      catch (Throwable paramIntent)
      {
        throw paramIntent;
      }
    }
  }
  
  public void disconnected(Intent paramIntent)
  {
    Log.e("AppActivity", "disconnected");
  }
  
  public void discovered(Intent paramIntent)
  {
    Log.d("AppActivity", "discovered");
    BluetoothHolder.getInstance().enableTXNotification();
  }
  
  void downloadApk() {}
  
  public int getMaxValue(int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramInt1 > paramInt2) && (paramInt1 > paramInt3)) {
      return paramInt1;
    }
    if ((paramInt2 > paramInt1) && (paramInt2 > paramInt3)) {
      return paramInt2;
    }
    if ((paramInt3 > paramInt1) && (paramInt3 > paramInt2)) {
      return paramInt3;
    }
    return 0;
  }
  
  public void get_gps()
  {
    calc = ((LocationManager)getSystemService("location"));
    Object localObject = mLocation;
    if (localObject != null)
    {
      lat = Double.valueOf(((Location)localObject).getLatitude());
      lon = Double.valueOf(mLocation.getLongitude());
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("??:");
      ((StringBuilder)localObject).append(String.valueOf(lat));
      Log.d("get_gps", ((StringBuilder)localObject).toString());
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("??:");
      ((StringBuilder)localObject).append(String.valueOf(lon));
      localObject = ((StringBuilder)localObject).toString();
    }
    else
    {
      localObject = "??/??:??";
    }
    Log.d("get_gps", (String)localObject);
    localObject = new Criteria();
    ((Criteria)localObject).setAccuracy(1);
    ((Criteria)localObject).setAltitudeRequired(true);
    ((Criteria)localObject).setBearingRequired(true);
    ((Criteria)localObject).setCostAllowed(false);
    ((Criteria)localObject).setPowerRequirement(1);
    calc.getBestProvider((Criteria)localObject, true);
  }
  
  public void gotoDouYin()
  {
    boolean bool = isAppInstalled(Cocos2dxActivity.getContext(), "com.ss.android.ugc.aweme");
    Object localObject = "snssdk1128://user/profile/2304231254538824";
    Intent localIntent;
    if (bool) {
      localIntent = new Intent();
    }
    for (;;)
    {
      localIntent.setData(Uri.parse((String)localObject));
      startActivityForResult(localIntent, 9000);
      return;
      if (isAppInstalled(Cocos2dxActivity.getContext(), "com.ss.android.article.video"))
      {
        localIntent = new Intent();
        localObject = "snssdk1112://user/profile/2304231254538824";
      }
      else
      {
        if (!isAppInstalled(Cocos2dxActivity.getContext(), "com.ss.android.ugc.live")) {
          break;
        }
        localIntent = new Intent();
        localObject = "snssdk1112://profile?id=2304231254538824";
      }
    }
    try
    {
      localObject = new Intent();
      ((Intent)localObject).setData(Uri.parse("snssdk1128://user/profile/2304231254538824"));
      startActivityForResult((Intent)localObject, 9000);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void gotoShop()
  {
    try
    {
      Intent localIntent = new Intent();
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.setData(Uri.parse("taobao://shop.m.taobao.com/shop/shop_index.htm?shop_id=339928477"));
      startActivityForResult(localIntent, 8989);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public boolean initBluetooth()
  {
    if (!isSupportBLE) {
      isSupportBLE = BluetoothHolder.init(this);
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("isSupportBLE:");
    localStringBuilder.append(isSupportBLE);
    Log.e("AppActivity", localStringBuilder.toString());
    return isSupportBLE;
  }
  
  void installAPK(String paramString) {}
  
  public boolean isPhoneNumberValid(String paramString)
  {
    if (Pattern.compile("^1[3456789]\\d{9}$").matcher(paramString).matches())
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("?????:");
      localStringBuilder.append(paramString);
      Log.d("isPhoneNumberValid?", localStringBuilder.toString());
      return true;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("?????:");
    localStringBuilder.append(paramString);
    Log.d("isPhoneNumberValid?", localStringBuilder.toString());
    return false;
  }
  
  public void onAccuracyChanged(Sensor paramSensor, int paramInt) {}
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 2000)
    {
      if (isLocationEnable(this)) {
        BeginScanBLE();
      }
    }
    else
    {
      Intent localIntent;
      if (paramInt1 == 8989)
      {
        paramIntent = new StringBuilder();
        paramIntent.append("resultCode:");
        paramIntent.append(paramInt2);
        Log.d("onActivityResult", paramIntent.toString());
        if (paramInt2 == 0)
        {
          localIntent = new Intent();
          localIntent.setAction("android.intent.action.VIEW");
        }
      }
      else
      {
        for (paramIntent = "https://market.m.taobao.com/app/starlink/wakeup-transit/pages/download?star_id=3027&slk_force_set_request=true&targetUrl=https%3A%2F%2Fh5.m.taobao.com%2Fawp%2Fcore%2Fdetail.htm%3Fwp_pk%3Dshop%2Findex_2212387364356_%26wp_m%3Ditem_rank_list_-2%26from%3Dinshop%26id%3D654274384555%26wp_app%3Dweapp%26wp_p%3D5";; paramIntent = "https://z.douyin.com/YCvp?scheme=snssdk1128%3A%2F%2Fec_goods_detail%3Fpromotion_id%3D3552098369008746198%26meta_params%3D%257B%2522entrance_info%2522%253A%2522%257B%255C%2522share_content%255C%2522%253A%255C%2522product_detail%255C%2522%252C%255C%2522share_object%255C%2522%253A%255C%2522copy%255C%2522%257D%2522%257D%26enter_from%3Dnew_h5_product_detail%26request_additions%3D%257B%2522sec_author_id%2522%253A%2522MS4wLjABAAAA7goVXO3rYnUasFoMtoxhS_K_GCUXwJy5xQFuDSX4P2BEkhObhOVz7ofm91852RIL%2522%252C%2522enter_from%2522%253A%2522new_h5_product_detail%2522%257D%26scene_from%3Dshare_reflow%26use_link_command%3D1")
        {
          localIntent.setData(Uri.parse(paramIntent));
          startActivity(localIntent);
          return;
          if (paramInt1 != 9000) {
            break;
          }
          paramIntent = new StringBuilder();
          paramIntent.append("resultCode:");
          paramIntent.append(paramInt2);
          Log.d("onActivityResult", paramIntent.toString());
          if (paramInt2 != 0) {
            break;
          }
          localIntent = new Intent();
          localIntent.setAction("android.intent.action.VIEW");
        }
      }
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    Log.d("AppActivity", "onCreate");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("???");
    localStringBuilder.append(getPackageName());
    Log.d("AppActivity", localStringBuilder.toString());
    super.onCreate(paramBundle);
    int i = 0;
    Cocos2dxActivity.setStateBarColor(false);
    getWindow().addFlags(128);
    if (getResourcesgetConfigurationlocale.getLanguage().endsWith("zh")) {
      i = 1;
    }
    _isChinese = i;
    instance = this;
    _msgList = new ArrayList();
    devices = new ArrayList();
    rissList = new ArrayList();
    isSupportBLE = BluetoothHolder.init(this);
    a = this;
    if (Build.VERSION.SDK_INT >= 31)
    {
      initPermission();
      if (com.org.android.ui.Resources.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") != 0)
      {
        Log.d("AppActivity", "???????");
        return;
      }
    }
    bluetoothResume();
  }
  
  protected void onDestroy()
  {
    Log.d("AppActivity", "onDestroy");
    super.onDestroy();
    if (_haveInitBLE) {
      bluetoothDestroy(true);
    }
  }
  
  protected void onPause()
  {
    Log.d("AppActivity", "onPause");
    super.onPause();
  }
  
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    super.onRequestPermissionsResult(paramInt, paramArrayOfString, paramArrayOfInt);
    if (paramInt == 800)
    {
      _checkTime += 1;
      if (_checkTime > 3)
      {
        if (!verifyPermissions(paramArrayOfInt)) {
          showMissingPermissionDialog();
        }
      }
      else {
        BeginScanBLE();
      }
    }
    if (paramInt == 1001)
    {
      _checkTime2 += 1;
      if ((_checkTime2 > 3) && (!verifyPermissions(paramArrayOfInt))) {
        showMissingPermissionDialog2();
      }
      if (com.org.android.ui.Resources.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") == 0) {
        Log.d("AppActivity", "???????2");
      }
    }
  }
  
  protected void onResume()
  {
    Log.d("AppActivity", "onResume");
    super.onResume();
    int i;
    if (getResourcesgetConfigurationlocale.getLanguage().endsWith("zh")) {
      i = 1;
    } else {
      i = 0;
    }
    _isChinese = i;
  }
  
  public void onSensorChanged(SensorEvent paramSensorEvent)
  {
    if (sensor == null) {
      return;
    }
    if (!IsConnected()) {
      return;
    }
    if (sensor.getType() == 1)
    {
      paramSensorEvent = values;
      ChangePos((int)paramSensorEvent[0], (int)paramSensorEvent[1], (int)paramSensorEvent[2]);
    }
  }
  
  public void sendCode(String paramString) {}
}
