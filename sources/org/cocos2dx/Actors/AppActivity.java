package org.cocos2dx.Actors;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.v4.app.b;
import android.support.v7.app.ByteVector;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.org.android.ui.Resources;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.cocos2dx.package_1.Cocos2dxActivity;
import org.cocos2dx.package_1.GameControllerDelegate;

public class AppActivity extends BaseActivity implements SensorEventListener {
    private static String APK_URL_01 = null;
    private static final int CMD_AddDevice_AGV = 50011;
    private static final int CMD_BeginLoadApk = 20011;
    private static final int CMD_CheckCode_AGV = 40011;
    private static final int CMD_Copy = 30011;
    private static final boolean IS_ICS = false;
    private static final String PAGE_KEY = "AppActivity";
    private static final int REQUEST_CODE_LOCATION_SETTINGS = 2000;
    /* access modifiers changed from: private */
    public static String _DevicName;
    private static String _bleName;
    /* access modifiers changed from: private */
    public static String _defaultDeviceName;
    private static String _emailFrom;
    private static String _emailMessage;
    /* access modifiers changed from: private */
    public static boolean _isAutoConnect;
    /* access modifiers changed from: private */
    public static ArrayList<byte[]> _msgList;
    private static String _orderID;
    /* access modifiers changed from: private */
    public static String _phoneNum;
    /* access modifiers changed from: private */
    public static String _url;
    /* access modifiers changed from: private */
    public static Activity a;
    private static BluetoothHolder bluetoothHolder;
    private static int delayTime;
    /* access modifiers changed from: private */
    public static ArrayList<BluetoothDevice> devices;
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
    private final int PERMISSON_SCAN = GameControllerDelegate.THUMBSTICK_LEFT_Y;
    private final String USER_TABLE = "NormalUser";
    /* access modifiers changed from: private */
    public String _checkCode = "";
    private int _checkTime = 0;
    private int _checkTime2 = 0;
    /* access modifiers changed from: private */
    public int _deviceID = 0;
    /* access modifiers changed from: private */
    public int _fun = 0;
    boolean _haveInitBLE = false;
    private int _isChinese = 0;
    private boolean _isFirstInit = true;
    /* access modifiers changed from: private */
    public String _psw = "";
    int _rssi = 0;
    /* access modifiers changed from: private */
    public String _userName = "";
    LocationManager calc;
    LocationListener gpsLocationListener = new GpsMyLocationProvider(this);
    boolean haveSend = false;
    Double lat;
    private final BluetoothAdapter.LeScanCallback leScanCallback = new UploadActivity$2(this);
    Double lon;
    Location mLocation;
    private Sensor mSensor;
    private SensorManager mSensorManager;
    Handler myHandler = new SlidingDrawer$SlidingHandler(this);
    private String[] permission = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};

    /* access modifiers changed from: private */
    public void AddNewDevice2() {
    }

    public static void AddOtherM3(String str) {
        _bleName = str;
        instance.SendMsg(1);
    }

    public static void BeginLoadApk(String str) {
        APK_URL_01 = str;
        instance.SendMsg(CMD_BeginLoadApk);
    }

    public static native void CallBackOnTouchCopy(String str);

    public static native void CallBackRegist(int i);

    private void ChangePos(int $i0, int $i1, int i) {
        int $i3 = $i0 < 0 ? $i0 * -1 : $i0;
        int $i4 = $i1 < 0 ? $i1 * -1 : $i1;
        if (i < 0) {
            if ($i4 > $i3) {
                $i1 = $i1 > 0 ? 20 - $i1 : -20 - $i1;
            } else {
                $i0 = $i0 > 0 ? 20 - $i0 : -20 - $i0;
            }
        }
        int $i12 = $i1 * -1;
        int $i2 = $i0 * -1;
        int $i02 = 0;
        int $i22 = $i2 > 1 ? $i2 - 1 : $i2 < -1 ? $i2 + 1 : 0;
        if ($i12 > 1) {
            $i02 = $i12 - 1;
        } else if ($i12 < -1) {
            $i02 = $i12 + 1;
        }
        OnAccuracyChanged($i22 * 2, $i02 * 2);
    }

    private boolean CheckNet() {
        return true;
    }

    public static native void CkeckCodeCallBack(int i);

    public static native void FinishLoad();

    public static String GetCurTime() {
        return "";
    }

    public static int GetGameRunTime(String str) {
        return 0;
    }

    public static Object GetInstance() {
        return instance;
    }

    public static String GetPhoneNumber() {
        try {
            if (Resources.checkSelfPermission(instance, "android.permission.READ_SMS") != 0) {
                if (Resources.checkSelfPermission(instance, "android.permission.READ_PHONE_NUMBERS") != 0) {
                    if (Resources.checkSelfPermission(instance, "android.permission.READ_PHONE_STATE") != 0) {
                        return "";
                    }
                }
            }
            String $r3 = ((TelephonyManager) instance.getSystemService("phone")).getLine1Number();
            if ($r3 == null) {
                Log.e("GetPhoneNumber:", "获取手机号码失败");
                return "";
            }
            Log.d("GetPhoneNumber", "getLine1Number:" + $r3);
            return $r3;
        } catch (Exception $r6) {
            Log.e("GetPhoneNumber", $r6.toString());
            return "";
        }
    }

    public static String GetScanBleList() {
        return _DevicName;
    }

    public static native void LoadCallBack(int i, int i2);

    static native void LoadTaoBao();

    public static native void OnAccuracyChanged(int i, int i2);

    public static void OpenURL(String str) {
        _url = str;
        instance.SendMsg(100);
    }

    public static native void ReadMsg(byte[] bArr);

    public static native void ReadMsg2(byte[] bArr);

    public static native void SendEmailCallBack(String str);

    public static native void SendEmailCallBack2(int i);

    public static void SendMessage(byte[] bArr, int i) {
        if (instance.IsConnected()) {
            byte[] $r2 = new byte[i];
            for (int $i2 = 0; $i2 < i; $i2++) {
                $r2[$i2] = bArr[$i2];
            }
            BluetoothHolder.getInstance().writeCharacteristic($r2);
        }
    }

    private void SendMsg(int i) {
        Message $r1 = new Message();
        $r1.what = i;
        this.myHandler.sendMessage($r1);
    }

    public static void SetDefaultConn(String str) {
        instance.DisConnect();
        _defaultDeviceName = str;
        instance.BeginScanBLE();
        _isAutoConnect = true;
    }

    public static void SetDefaultConn2(String str) {
        instance.DisConnect();
        BluetoothHolder.getInstance().reConnect();
    }

    public static native void SetDeviceID(int i);

    private void bluetoothDestroy(boolean z) {
        try {
            DisConnect();
            bluetoothHolder.unbindService(z);
            bluetoothHolder.unregisterReceiver(z);
            this._haveInitBLE = false;
        } catch (Exception $r3) {
            Log.e("bluetoothDestroy", $r3.toString());
        }
    }

    private void bluetoothResume() {
        bluetoothHolder = BluetoothHolder.getInstance();
        BluetoothHolder $r1 = bluetoothHolder;
        isSupportBLE = BluetoothHolder.init(this);
        bluetoothHolder.openBluetooth();
        bluetoothHolder.bindService();
        bluetoothHolder.registerReceiver(this);
        this._haveInitBLE = true;
        Log.d(PAGE_KEY, "buletoothResume");
    }

    public static String byteToHex(byte b) {
        return new String(new char[]{Character.forDigit((b >> 4) & 15, 16), Character.forDigit(b & 15, 16)});
    }

    public static void cancelVibrate() {
        ((Vibrator) instance.getSystemService("vibrator")).cancel();
    }

    /* access modifiers changed from: private */
    public void checkCode(String str, String str2, boolean z) {
    }

    public static AppActivity getInstance() {
        return instance;
    }

    private void initPermission() {
        ArrayList $r1 = new ArrayList();
        Log.d(PAGE_KEY, "BeginScanBLE() b:" + Build.VERSION.SDK_INT);
        if (Build.VERSION.SDK_INT >= 31) {
            $r1.add("android.permission.BLUETOOTH_CONNECT");
            $r1.add("android.permission.BLUETOOTH_SCAN");
            Log.d(PAGE_KEY, "BeginScanBLE() 12");
        }
        if ($r1.size() > 0) {
            b.a(this, (String[]) $r1.toArray(new String[0]), GameControllerDelegate.THUMBSTICK_LEFT_Y);
        }
    }

    public static boolean isAppInstalled(Context context, String str) {
        return new File("/data/data/" + str).exists();
    }

    public static final boolean isLocationEnable(Context context) {
        if (Build.VERSION.SDK_INT < 19) {
            return !TextUtils.isEmpty(Settings.Secure.getString(context.getContentResolver(), "location_providers_allowed"));
        }
        try {
            return Settings.Secure.getInt(context.getContentResolver(), "location_mode") != 0;
        } catch (Settings.SettingNotFoundException $r2) {
            $r2.printStackTrace();
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void setLocationService() {
        startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), REQUEST_CODE_LOCATION_SETTINGS);
    }

    /* access modifiers changed from: private */
    public void showMissingGPSPermissionDialog() {
        ByteVector $r1 = new ByteVector(this);
        $r1.b((CharSequence) this._isChinese == 1 ? "提示" : "Tip");
        $r1.a((CharSequence) this._isChinese == 1 ? "搜索蓝牙设备需要开启位置服务(GPS)" : "Searching for Bluetooth devices requires location service (GPS) to be turned on ");
        $r1.b(this._isChinese == 1 ? "取消" : "Cancel", (DialogInterface.OnClickListener) null);
        $r1.a((CharSequence) this._isChinese == 1 ? "去开启" : "Go to Settings", (DialogInterface.OnClickListener) new FileBrowser$9(this));
        $r1.b(false);
        $r1.a();
        $r1.b();
    }

    private void showMissingPermissionDialog() {
        try {
            ByteVector $r2 = new ByteVector(this);
            $r2.b((CharSequence) this._isChinese == 1 ? "提示" : "Tip");
            $r2.a((CharSequence) this._isChinese == 1 ? "该应用需打开定位权限才能扫描到蓝牙设备。请点击\\\"设置\\\"-\\\"权限管理\\\"-打开定位权限" : "The application needs to get the location permission to scan to the Bluetooth device. Please click \\\"Settings\\\"-\\\"Permissions\\\"-Get location info");
            $r2.b(this._isChinese == 1 ? "取消" : "Cancel", new ProfileActivity$2(this));
            $r2.a((CharSequence) this._isChinese == 1 ? "设置" : "Settings", (DialogInterface.OnClickListener) new WhitelistFragment$3(this));
            $r2.b(false);
            $r2.b();
        } catch (Throwable $r5) {
            $r5.printStackTrace();
        }
    }

    private void showMissingPermissionDialog2() {
        try {
            ByteVector $r2 = new ByteVector(this);
            $r2.b((CharSequence) this._isChinese == 1 ? "提示" : "Tip");
            $r2.a((CharSequence) this._isChinese == 1 ? "该应用需打开连接附近的设备权限才能扫描到蓝牙设备。请点击\\\"设置\\\"-\\\"权限管理\\\"-连接附近的设备" : "The application needs to get the connect to nearby devices permission to scan to the Bluetooth device. Please click \\\"Settings\\\"-\\\"Permissions\\\"-Connect to nearby devices");
            $r2.b(this._isChinese == 1 ? "取消" : "Cancel", new NoteEditor$4(this));
            $r2.a((CharSequence) this._isChinese == 1 ? "设置" : "Settings", (DialogInterface.OnClickListener) new FileBrowser$11(this));
            $r2.b(false);
            $r2.b();
        } catch (Throwable $r5) {
            $r5.printStackTrace();
        }
    }

    private static List sortByValueFloatDesc(Map map) {
        ArrayList $r0 = new ArrayList(map.entrySet());
        Collections.sort($r0, new SearchResultsActivity$1());
        return $r0;
    }

    /* access modifiers changed from: private */
    public void startAppSettings() {
        try {
            Intent $r1 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            $r1.setData(Uri.parse("package:" + getPackageName()));
            startActivity($r1);
        } catch (Throwable $r5) {
            $r5.printStackTrace();
        }
    }

    public static void startTaobaoApp() {
        if (isAppInstalled(instance, "com.taobao.taobao")) {
            Intent $r1 = new Intent();
            $r1.setAction("Android.intent.action.VIEW");
            $r1.setFlags(268435456);
            $r1.setData(Uri.parse("https://item.taobao.com/item.htm?spm=a1z10.1-c-s.w4004-23989050866.2.108c2ea4jRnp21&id=652257996818"));
            $r1.setClassName("com.taobao.taobao", "com.taobao.tao.detail.activity.DetailActivity");
            instance.startActivity($r1);
            return;
        }
        LoadTaoBao();
        Log.e("startTaobaoApp", "您还没有安装淘宝客户端！");
    }

    private boolean verifyPermissions(int[] iArr) {
        try {
            for (int $i1 : iArr) {
                if ($i1 != 0) {
                    return false;
                }
            }
            return true;
        } catch (Throwable $r2) {
            $r2.printStackTrace();
            return true;
        }
    }

    public static void vibrate(long j) {
        ((Vibrator) instance.getSystemService("vibrator")).vibrate(j);
    }

    public static void vibrateWithPattern(long[] jArr, int i) {
        ((Vibrator) instance.getSystemService("vibrator")).vibrate(jArr, i);
    }

    public void ActivityAGV(String str, int i, int i2) {
        this._checkCode = str;
        this._fun = i;
        this._deviceID = i2;
        Log.d(PAGE_KEY, "ActivityAGV");
        SendMsg(CMD_CheckCode_AGV);
    }

    /* access modifiers changed from: package-private */
    public void AddDeviceToService(int i, int i2) {
    }

    public void AddNewDevice() {
        Log.d(PAGE_KEY, "AddNewDevice");
        SendMsg(CMD_AddDevice_AGV);
    }

    public void BeginScanBLE() {
        _isAutoConnect = false;
        if (Build.VERSION.SDK_INT >= 23) {
            ArrayList $r2 = new ArrayList();
            for (String $r1 : this.permission) {
                if (Resources.checkSelfPermission(this, $r1) != 0) {
                    $r2.add($r1);
                }
            }
            if (!$r2.isEmpty()) {
                b.a(this, (String[]) $r2.toArray(new String[$r2.size()]), 800);
                return;
            }
        }
        if (Build.VERSION.SDK_INT < 23 || isLocationEnable(this)) {
            if (Build.VERSION.SDK_INT >= 31) {
                initPermission();
                if (Resources.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") != 0) {
                    Log.d(PAGE_KEY, "BeginScanBLE() 55");
                    return;
                } else if (Resources.checkSelfPermission(this, "android.permission.BLUETOOTH_SCAN") != 0) {
                    Log.d(PAGE_KEY, "BeginScanBLE() 66");
                    return;
                }
            }
            this._checkTime = 0;
            this._checkTime2 = 0;
            devices.clear();
            rissList.clear();
            _msgList.clear();
            _DevicName = "";
            DisConnect();
            if (this._haveInitBLE) {
                bluetoothDestroy(true);
            }
            bluetoothResume();
            BluetoothHolder.getInstance().scanDevice(false, this.leScanCallback);
            BluetoothHolder.getInstance().scanDevice(true, this.leScanCallback);
            return;
        }
        instance.SendMsg(10003);
    }

    /* access modifiers changed from: package-private */
    public void CheckCode(String str, int i, int i2) {
    }

    public void ConnectBLE(int i) {
        this.haveSend = false;
        ReadMsg2(_msgList.get(i));
        FinshScanBLE();
        BluetoothDevice $r4 = devices.get(i);
        BluetoothHolder.getInstance().connect($r4.getName(), $r4.getAddress());
        Log.d(PAGE_KEY, "reConnect:deviceAddress0" + $r4.getAddress());
        SetAppType($r4.getName());
    }

    public void DisConnect() {
        if (IsConnected()) {
            BluetoothHolder.getInstance().disconnect();
        }
    }

    public void FinshScanBLE() {
        if (BluetoothHolder.getInstance().isScanning()) {
            BluetoothHolder.getInstance().scanDevice(false, this.leScanCallback);
        }
    }

    public String GetCopyString() {
        ClipboardManager $r2 = (ClipboardManager) getSystemService("clipboard");
        if ($r2.getText() == null) {
            return "";
        }
        String $r4 = $r2.getText().toString();
        Log.d("GetCopyString", "msg:" + $r4);
        return $r4 != null ? Pattern.compile("\\s*|\t|\r|\n").matcher($r4).replaceAll("") : "";
    }

    public int GetDay() {
        return Calendar.getInstance().get(5);
    }

    public int GetHour() {
        return Calendar.getInstance().get(11);
    }

    public int GetMinute() {
        return Calendar.getInstance().get(12);
    }

    public int GetMonth() {
        return Calendar.getInstance().get(2);
    }

    /* access modifiers changed from: package-private */
    public int GetRandID() {
        double $d0 = Math.random();
        Double.isNaN(2.68435455E8d);
        Double.isNaN(0.0d);
        return (int) (($d0 * 2.68435455E8d) + 0.0d);
    }

    public int GetSecond() {
        return Calendar.getInstance().get(13);
    }

    public int GetWeek() {
        return Calendar.getInstance().get(7) - 1;
    }

    public int GetYear() {
        return Calendar.getInstance().get(1);
    }

    public boolean IsConnected() {
        return BluetoothHolder.getInstance().isConnected();
    }

    public void MyPause() {
        DisConnect();
    }

    public void MyResume() {
    }

    public void OnTouchCopy() {
        Log.d(PAGE_KEY, "OnTouchCopy");
        SendMsg(CMD_Copy);
    }

    public void SendEmail(String str) {
    }

    public void SendToGetCheckCode(String str) {
        Log.d("SendToGetCheckCode", "phoneNum：" + str);
        _phoneNum = str;
        if (this._isChinese == 0) {
            SendEmail(str);
        } else {
            SendMsg(264830);
        }
    }

    public void SetAppType(String str) {
        BluetoothHolder.SetIsSmallType(str.startsWith("M6") ? (byte) 0 : 1);
    }

    public void TestSendData() {
    }

    public void TouchLogin1(String str, String str2) {
        this._userName = str;
        _phoneNum = str;
        this._psw = str2;
        SendMsg(789461);
        Log.d("TouchLogin1", "phoneNum：" + str + "  psw:" + str2);
    }

    public void TouchLogin2(String str, String str2) {
        this._userName = str;
        _phoneNum = str;
        this._checkCode = str2;
        this._psw = "";
        SendMsg(789462);
    }

    public void TouchLogin3(String str) {
        this._userName = str;
        _phoneNum = str;
        this._psw = "";
        SendMsg(789463);
    }

    public void TouchRegister(String str, String str2, String str3) {
        this._userName = str;
        _phoneNum = str;
        this._checkCode = str2;
        this._psw = str3;
        SendMsg(789464);
    }

    /* access modifiers changed from: package-private */
    public void UpdataDeviceInfo(int i, int i2) {
    }

    public void addFriend(String str) {
    }

    public void addUserToService(String str, String str2, String str3) {
    }

    public void checkPsw(String str, String str2) {
    }

    public void connected(Intent intent) {
        Log.e(PAGE_KEY, "connected------");
    }

    public synchronized void data(Intent intent, byte[] bArr) {
        if (bArr != null) {
            ReadMsg(bArr);
        }
    }

    public void disconnected(Intent intent) {
        Log.e(PAGE_KEY, "disconnected");
    }

    public void discovered(Intent intent) {
        Log.d(PAGE_KEY, "discovered");
        BluetoothHolder.getInstance().enableTXNotification();
    }

    /* access modifiers changed from: package-private */
    public void downloadApk() {
    }

    public int getMaxValue(int i, int i2, int i3) {
        if (i > i2 && i > i3) {
            return i;
        }
        if (i2 > i && i2 > i3) {
            return i2;
        }
        if (i3 <= i || i3 <= i2) {
            return 0;
        }
        return i3;
    }

    public void get_gps() {
        String $r6;
        this.calc = (LocationManager) getSystemService("location");
        Location $r3 = this.mLocation;
        if ($r3 != null) {
            this.lat = Double.valueOf($r3.getLatitude());
            this.lon = Double.valueOf(this.mLocation.getLongitude());
            Log.d("get_gps", "纬度:" + String.valueOf(this.lat));
            $r6 = "经度:" + String.valueOf(this.lon);
        } else {
            $r6 = "经度/纬度:未知";
        }
        Log.d("get_gps", $r6);
        Criteria $r7 = new Criteria();
        $r7.setAccuracy(1);
        $r7.setAltitudeRequired(true);
        $r7.setBearingRequired(true);
        $r7.setCostAllowed(false);
        $r7.setPowerRequirement(1);
        this.calc.getBestProvider($r7, true);
    }

    public void gotoDouYin() {
        Intent $r3;
        String $r2 = "snssdk1128://user/profile/2304231254538824";
        if (isAppInstalled(Cocos2dxActivity.getContext(), "com.ss.android.ugc.aweme")) {
            $r3 = new Intent();
        } else if (isAppInstalled(Cocos2dxActivity.getContext(), "com.ss.android.article.video")) {
            $r3 = new Intent();
            $r2 = "snssdk1112://user/profile/2304231254538824";
        } else if (isAppInstalled(Cocos2dxActivity.getContext(), "com.ss.android.ugc.live")) {
            $r3 = new Intent();
            $r2 = "snssdk1112://profile?id=2304231254538824";
        } else {
            try {
                Intent $r32 = new Intent();
                $r32.setData(Uri.parse("snssdk1128://user/profile/2304231254538824"));
                startActivityForResult($r32, 9000);
                return;
            } catch (Exception $r5) {
                $r5.printStackTrace();
                return;
            }
        }
        $r3.setData(Uri.parse($r2));
        startActivityForResult($r3, 9000);
    }

    public void gotoShop() {
        try {
            Intent $r2 = new Intent();
            $r2.setAction("android.intent.action.VIEW");
            $r2.setData(Uri.parse("taobao://shop.m.taobao.com/shop/shop_index.htm?shop_id=339928477"));
            startActivityForResult($r2, 8989);
        } catch (Exception $r3) {
            $r3.printStackTrace();
        }
    }

    public boolean initBluetooth() {
        if (!isSupportBLE) {
            isSupportBLE = BluetoothHolder.init(this);
        }
        Log.e(PAGE_KEY, "isSupportBLE:" + isSupportBLE);
        return isSupportBLE;
    }

    /* access modifiers changed from: package-private */
    public void installAPK(String str) {
    }

    public boolean isPhoneNumberValid(String $r1) {
        if (Pattern.compile("^1[3456789]\\d{9}$").matcher($r1).matches()) {
            Log.d("isPhoneNumberValid：", "有效手机号:" + $r1);
            return true;
        }
        Log.d("isPhoneNumberValid：", "无效手机号:" + $r1);
        return false;
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        Intent $r1;
        String $r3;
        super.onActivityResult(i, i2, intent);
        if (i != REQUEST_CODE_LOCATION_SETTINGS) {
            if (i == 8989) {
                Log.d("onActivityResult", "resultCode:" + i2);
                if (i2 == 0) {
                    $r1 = new Intent();
                    $r1.setAction("android.intent.action.VIEW");
                    $r3 = "https://market.m.taobao.com/app/starlink/wakeup-transit/pages/download?star_id=3027&slk_force_set_request=true&targetUrl=https%3A%2F%2Fh5.m.taobao.com%2Fawp%2Fcore%2Fdetail.htm%3Fwp_pk%3Dshop%2Findex_2212387364356_%26wp_m%3Ditem_rank_list_-2%26from%3Dinshop%26id%3D654274384555%26wp_app%3Dweapp%26wp_p%3D5";
                } else {
                    return;
                }
            } else if (i == 9000) {
                Log.d("onActivityResult", "resultCode:" + i2);
                if (i2 == 0) {
                    $r1 = new Intent();
                    $r1.setAction("android.intent.action.VIEW");
                    $r3 = "https://z.douyin.com/YCvp?scheme=snssdk1128%3A%2F%2Fec_goods_detail%3Fpromotion_id%3D3552098369008746198%26meta_params%3D%257B%2522entrance_info%2522%253A%2522%257B%255C%2522share_content%255C%2522%253A%255C%2522product_detail%255C%2522%252C%255C%2522share_object%255C%2522%253A%255C%2522copy%255C%2522%257D%2522%257D%26enter_from%3Dnew_h5_product_detail%26request_additions%3D%257B%2522sec_author_id%2522%253A%2522MS4wLjABAAAA7goVXO3rYnUasFoMtoxhS_K_GCUXwJy5xQFuDSX4P2BEkhObhOVz7ofm91852RIL%2522%252C%2522enter_from%2522%253A%2522new_h5_product_detail%2522%257D%26scene_from%3Dshare_reflow%26use_link_command%3D1";
                } else {
                    return;
                }
            } else {
                return;
            }
            $r1.setData(Uri.parse($r3));
            startActivity($r1);
        } else if (isLocationEnable(this)) {
            BeginScanBLE();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Log.d(PAGE_KEY, "onCreate");
        Log.d(PAGE_KEY, "包名：" + getPackageName());
        super.onCreate(bundle);
        byte $b0 = 0;
        Cocos2dxActivity.setStateBarColor(false);
        getWindow().addFlags(128);
        if (getResources().getConfiguration().locale.getLanguage().endsWith("zh")) {
            $b0 = 1;
        }
        this._isChinese = $b0;
        instance = this;
        _msgList = new ArrayList();
        devices = new ArrayList();
        rissList = new ArrayList();
        isSupportBLE = BluetoothHolder.init(this);
        a = this;
        if (Build.VERSION.SDK_INT >= 31) {
            initPermission();
            if (Resources.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") != 0) {
                Log.d(PAGE_KEY, "蓝牙权限未开启");
                return;
            }
        }
        bluetoothResume();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        Log.d(PAGE_KEY, "onDestroy");
        super.onDestroy();
        if (this._haveInitBLE) {
            bluetoothDestroy(true);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        Log.d(PAGE_KEY, "onPause");
        super.onPause();
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 800) {
            this._checkTime++;
            if (this._checkTime <= 3) {
                BeginScanBLE();
            } else if (!verifyPermissions(iArr)) {
                showMissingPermissionDialog();
            }
        }
        if (i == 1001) {
            this._checkTime2++;
            if (this._checkTime2 > 3 && !verifyPermissions(iArr)) {
                showMissingPermissionDialog2();
            }
            if (Resources.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") == 0) {
                Log.d(PAGE_KEY, "蓝牙权限已开启2");
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        Log.d(PAGE_KEY, "onResume");
        super.onResume();
        this._isChinese = getResources().getConfiguration().locale.getLanguage().endsWith("zh") ? (byte) 1 : 0;
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor != null && IsConnected() && sensorEvent.sensor.getType() == 1) {
            float[] $r3 = sensorEvent.values;
            ChangePos((int) $r3[0], (int) $r3[1], (int) $r3[2]);
        }
    }

    public void sendCode(String str) {
    }
}
