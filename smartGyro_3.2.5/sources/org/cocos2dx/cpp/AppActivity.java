package org.cocos2dx.cpp;

import android.annotation.TargetApi;
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
import android.support.p024v4.app.C0183b;
import android.support.p025v7.app.C0249k;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.cocos2dx.lib.Cocos2dxActivity;
import org.cocos2dx.lib.GameControllerDelegate;
import p000a.p001a.p005c.p006a.C0025a;

public class AppActivity extends BaseActivity implements SensorEventListener {
    private static String APK_URL_01 = "";
    private static final int CMD_AddDevice_AGV = 50011;
    private static final int CMD_BeginLoadApk = 20011;
    private static final int CMD_CheckCode_AGV = 40011;
    private static final int CMD_Copy = 30011;

    /* renamed from: D */
    private static final boolean f2448D = false;
    private static final int REQUEST_CODE_LOCATION_SETTINGS = 2000;
    private static final String TAG = "AppActivity";
    /* access modifiers changed from: private */
    public static String _DevicName = "";
    private static String _bleName = "";
    /* access modifiers changed from: private */
    public static String _defaultDeviceName = "--";
    private static String _emailFrom = "895901747@qq.com";
    private static String _emailMessage = "";
    /* access modifiers changed from: private */
    public static boolean _isAutoConnect = false;
    /* access modifiers changed from: private */
    public static ArrayList<byte[]> _msgList = null;
    private static String _orderID = "订单号：88888888";
    /* access modifiers changed from: private */
    public static String _phoneNum = "15815888888";
    /* access modifiers changed from: private */
    public static String _url = "http://zhushou.360.cn/detail/index/soft_id/3246982";
    private static BluetoothHolder bluetoothHolder = null;
    private static int delayTime = 10;
    /* access modifiers changed from: private */
    public static ArrayList<BluetoothDevice> devices = null;
    private static AppActivity instance = null;
    private static boolean isSupportBLE = false;
    /* access modifiers changed from: private */

    /* renamed from: me */
    public static Activity f2449me;
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
    boolean haveSend = false;
    Double lat;
    private final BluetoothAdapter.LeScanCallback leScanCallback = new C0900h(this);
    Double lng;
    Location loc;
    LocationManager lom;
    LocationListener los = new C0901i(this);
    private Sensor mSensor;
    private SensorManager mSensorManager;
    Handler myHandler = new C0902j(this);
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

    private void ChangePos(int i, int i2, int i3) {
        int i4 = i < 0 ? i * -1 : i;
        int i5 = i2 < 0 ? i2 * -1 : i2;
        if (i3 < 0) {
            if (i5 > i4) {
                i2 = i2 > 0 ? 20 - i2 : -20 - i2;
            } else {
                i = i > 0 ? 20 - i : -20 - i;
            }
        }
        int i6 = i2 * -1;
        int i7 = i * -1;
        int i8 = 0;
        int i9 = i7 > 1 ? i7 - 1 : i7 < -1 ? i7 + 1 : 0;
        if (i6 > 1) {
            i8 = i6 - 1;
        } else if (i6 < -1) {
            i8 = i6 + 1;
        }
        OnAccuracyChanged(i9 * 2, i8 * 2);
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
            if (C0025a.m75a((Context) instance, "android.permission.READ_SMS") != 0 && C0025a.m75a((Context) instance, "android.permission.READ_PHONE_NUMBERS") != 0 && C0025a.m75a((Context) instance, "android.permission.READ_PHONE_STATE") != 0) {
                return "";
            }
            String line1Number = ((TelephonyManager) instance.getSystemService("phone")).getLine1Number();
            if (line1Number == null) {
                Log.e("GetPhoneNumber:", "获取手机号码失败");
                return "";
            }
            Log.d("GetPhoneNumber", "getLine1Number:" + line1Number);
            return line1Number;
        } catch (Exception e) {
            Log.e("GetPhoneNumber", e.toString());
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
            byte[] bArr2 = new byte[i];
            for (int i2 = 0; i2 < i; i2++) {
                bArr2[i2] = bArr[i2];
            }
            BluetoothHolder.getInstance().writeCharacteristic(bArr2);
        }
    }

    private void SendMsg(int i) {
        Message message = new Message();
        message.what = i;
        this.myHandler.sendMessage(message);
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
        } catch (Exception e) {
            Log.e("bluetoothDestroy", e.toString());
        }
    }

    private void bluetoothResume() {
        bluetoothHolder = BluetoothHolder.getInstance();
        BluetoothHolder bluetoothHolder2 = bluetoothHolder;
        isSupportBLE = BluetoothHolder.init(this);
        bluetoothHolder.openBluetooth();
        bluetoothHolder.bindService();
        bluetoothHolder.registerReceiver(this);
        this._haveInitBLE = true;
        Log.d(TAG, "buletoothResume");
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
        ArrayList arrayList = new ArrayList();
        Log.d(TAG, "BeginScanBLE() b:" + Build.VERSION.SDK_INT);
        if (Build.VERSION.SDK_INT >= 31) {
            arrayList.add("android.permission.BLUETOOTH_CONNECT");
            arrayList.add("android.permission.BLUETOOTH_SCAN");
            Log.d(TAG, "BeginScanBLE() 12");
        }
        if (arrayList.size() > 0) {
            C0183b.m657a(this, (String[]) arrayList.toArray(new String[0]), GameControllerDelegate.THUMBSTICK_LEFT_Y);
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
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void setLocationService() {
        startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), REQUEST_CODE_LOCATION_SETTINGS);
    }

    /* access modifiers changed from: private */
    public void showMissingGPSPermissionDialog() {
        C0249k.C0250a aVar = new C0249k.C0250a(this);
        aVar.mo1017b(this._isChinese == 1 ? "提示" : "Tip");
        aVar.mo1012a((CharSequence) this._isChinese == 1 ? "搜索蓝牙设备需要开启位置服务(GPS)" : "Searching for Bluetooth devices requires location service (GPS) to be turned on ");
        aVar.mo1013a((CharSequence) this._isChinese == 1 ? "取消" : "Cancel", (DialogInterface.OnClickListener) null);
        aVar.mo1018b(this._isChinese == 1 ? "去开启" : "Go to Settings", new C0897e(this));
        aVar.mo1014a(false);
        aVar.mo1015a();
        aVar.mo1019c();
    }

    private void showMissingPermissionDialog() {
        try {
            C0249k.C0250a aVar = new C0249k.C0250a(this);
            aVar.mo1017b(this._isChinese == 1 ? "提示" : "Tip");
            aVar.mo1012a((CharSequence) this._isChinese == 1 ? "该应用需打开定位权限才能扫描到蓝牙设备。请点击\\\"设置\\\"-\\\"权限管理\\\"-打开定位权限" : "The application needs to get the location permission to scan to the Bluetooth device. Please click \\\"Settings\\\"-\\\"Permissions\\\"-Get location info");
            aVar.mo1013a((CharSequence) this._isChinese == 1 ? "取消" : "Cancel", (DialogInterface.OnClickListener) new C0893a(this));
            aVar.mo1018b(this._isChinese == 1 ? "设置" : "Settings", new C0894b(this));
            aVar.mo1014a(false);
            aVar.mo1019c();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void showMissingPermissionDialog2() {
        try {
            C0249k.C0250a aVar = new C0249k.C0250a(this);
            aVar.mo1017b(this._isChinese == 1 ? "提示" : "Tip");
            aVar.mo1012a((CharSequence) this._isChinese == 1 ? "该应用需打开连接附近的设备权限才能扫描到蓝牙设备。请点击\\\"设置\\\"-\\\"权限管理\\\"-连接附近的设备" : "The application needs to get the connect to nearby devices permission to scan to the Bluetooth device. Please click \\\"Settings\\\"-\\\"Permissions\\\"-Connect to nearby devices");
            aVar.mo1013a((CharSequence) this._isChinese == 1 ? "取消" : "Cancel", (DialogInterface.OnClickListener) new C0895c(this));
            aVar.mo1018b(this._isChinese == 1 ? "设置" : "Settings", new C0896d(this));
            aVar.mo1014a(false);
            aVar.mo1019c();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static List<Map.Entry<BluetoothDevice, Integer>> sortByValueFloatDesc(Map<BluetoothDevice, Integer> map) {
        ArrayList arrayList = new ArrayList(map.entrySet());
        Collections.sort(arrayList, new C0898f());
        return arrayList;
    }

    /* access modifiers changed from: private */
    public void startAppSettings() {
        try {
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivity(intent);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void startTaobaoApp() {
        if (isAppInstalled(instance, "com.taobao.taobao")) {
            Intent intent = new Intent();
            intent.setAction("Android.intent.action.VIEW");
            intent.setFlags(268435456);
            intent.setData(Uri.parse("https://item.taobao.com/item.htm?spm=a1z10.1-c-s.w4004-23989050866.2.108c2ea4jRnp21&id=652257996818"));
            intent.setClassName("com.taobao.taobao", "com.taobao.tao.detail.activity.DetailActivity");
            instance.startActivity(intent);
            return;
        }
        LoadTaoBao();
        Log.e("startTaobaoApp", "您还没有安装淘宝客户端！");
    }

    private boolean verifyPermissions(int[] iArr) {
        try {
            for (int i : iArr) {
                if (i != 0) {
                    return false;
                }
            }
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
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
        Log.d(TAG, "ActivityAGV");
        SendMsg(CMD_CheckCode_AGV);
    }

    /* access modifiers changed from: package-private */
    public void AddDeviceToService(int i, int i2) {
    }

    public void AddNewDevice() {
        Log.d(TAG, "AddNewDevice");
        SendMsg(CMD_AddDevice_AGV);
    }

    public void BeginScanBLE() {
        _isAutoConnect = false;
        if (Build.VERSION.SDK_INT >= 23) {
            ArrayList arrayList = new ArrayList();
            for (String str : this.permission) {
                if (C0025a.m75a((Context) this, str) != 0) {
                    arrayList.add(str);
                }
            }
            if (!arrayList.isEmpty()) {
                C0183b.m657a(this, (String[]) arrayList.toArray(new String[arrayList.size()]), 800);
                return;
            }
        }
        if (Build.VERSION.SDK_INT < 23 || isLocationEnable(this)) {
            if (Build.VERSION.SDK_INT >= 31) {
                initPermission();
                if (C0025a.m75a((Context) this, "android.permission.BLUETOOTH_CONNECT") != 0) {
                    Log.d(TAG, "BeginScanBLE() 55");
                    return;
                } else if (C0025a.m75a((Context) this, "android.permission.BLUETOOTH_SCAN") != 0) {
                    Log.d(TAG, "BeginScanBLE() 66");
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
        BluetoothDevice bluetoothDevice = devices.get(i);
        BluetoothHolder.getInstance().connect(bluetoothDevice.getName(), bluetoothDevice.getAddress());
        Log.d(TAG, "reConnect:deviceAddress0" + bluetoothDevice.getAddress());
        SetAppType(bluetoothDevice.getName());
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
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService("clipboard");
        if (clipboardManager.getText() == null) {
            return "";
        }
        String charSequence = clipboardManager.getText().toString();
        Log.d("GetCopyString", "msg:" + charSequence);
        return charSequence != null ? Pattern.compile("\\s*|\t|\r|\n").matcher(charSequence).replaceAll("") : "";
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
        double random = Math.random();
        double d = (double) 268435455;
        Double.isNaN(d);
        double d2 = random * d;
        double d3 = (double) 0;
        Double.isNaN(d3);
        return (int) (d2 + d3);
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
        Log.d(TAG, "OnTouchCopy");
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
        BluetoothHolder.SetIsSmallType(str.startsWith("M6") ? 0 : 1);
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
        Log.e(TAG, "connected------");
    }

    public synchronized void data(Intent intent, byte[] bArr) {
        if (bArr != null) {
            ReadMsg(bArr);
        }
    }

    public void disconnected(Intent intent) {
        Log.e(TAG, "disconnected");
    }

    public void discovered(Intent intent) {
        Log.d(TAG, "discovered");
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
        String str;
        this.lom = (LocationManager) getSystemService("location");
        Location location = this.loc;
        if (location != null) {
            this.lat = Double.valueOf(location.getLatitude());
            this.lng = Double.valueOf(this.loc.getLongitude());
            Log.d("get_gps", "纬度:" + String.valueOf(this.lat));
            str = "经度:" + String.valueOf(this.lng);
        } else {
            str = "经度/纬度:未知";
        }
        Log.d("get_gps", str);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(1);
        criteria.setAltitudeRequired(true);
        criteria.setBearingRequired(true);
        criteria.setCostAllowed(false);
        criteria.setPowerRequirement(1);
        this.lom.getBestProvider(criteria, true);
    }

    public void gotoDouYin() {
        Intent intent;
        String str = "snssdk1128://user/profile/2304231254538824";
        if (isAppInstalled(Cocos2dxActivity.getContext(), "com.ss.android.ugc.aweme")) {
            intent = new Intent();
        } else if (isAppInstalled(Cocos2dxActivity.getContext(), "com.ss.android.article.video")) {
            intent = new Intent();
            str = "snssdk1112://user/profile/2304231254538824";
        } else if (isAppInstalled(Cocos2dxActivity.getContext(), "com.ss.android.ugc.live")) {
            intent = new Intent();
            str = "snssdk1112://profile?id=2304231254538824";
        } else {
            try {
                Intent intent2 = new Intent();
                intent2.setData(Uri.parse(str));
                startActivityForResult(intent2, 9000);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        intent.setData(Uri.parse(str));
        startActivityForResult(intent, 9000);
    }

    public void gotoShop() {
        try {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse("taobao://shop.m.taobao.com/shop/shop_index.htm?shop_id=339928477"));
            startActivityForResult(intent, 8989);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean initBluetooth() {
        if (!isSupportBLE) {
            isSupportBLE = BluetoothHolder.init(this);
        }
        Log.e(TAG, "isSupportBLE:" + isSupportBLE);
        return isSupportBLE;
    }

    /* access modifiers changed from: package-private */
    public void installAPK(String str) {
    }

    public boolean isPhoneNumberValid(String str) {
        if (Pattern.compile("^1[3456789]\\d{9}$").matcher(str).matches()) {
            Log.d("isPhoneNumberValid：", "有效手机号:" + str);
            return true;
        }
        Log.d("isPhoneNumberValid：", "无效手机号:" + str);
        return false;
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        Intent intent2;
        String str;
        super.onActivityResult(i, i2, intent);
        if (i != REQUEST_CODE_LOCATION_SETTINGS) {
            if (i == 8989) {
                Log.d("onActivityResult", "resultCode:" + i2);
                if (i2 == 0) {
                    intent2 = new Intent();
                    intent2.setAction("android.intent.action.VIEW");
                    str = "https://market.m.taobao.com/app/starlink/wakeup-transit/pages/download?star_id=3027&slk_force_set_request=true&targetUrl=https%3A%2F%2Fh5.m.taobao.com%2Fawp%2Fcore%2Fdetail.htm%3Fwp_pk%3Dshop%2Findex_2212387364356_%26wp_m%3Ditem_rank_list_-2%26from%3Dinshop%26id%3D654274384555%26wp_app%3Dweapp%26wp_p%3D5";
                } else {
                    return;
                }
            } else if (i == 9000) {
                Log.d("onActivityResult", "resultCode:" + i2);
                if (i2 == 0) {
                    intent2 = new Intent();
                    intent2.setAction("android.intent.action.VIEW");
                    str = "https://z.douyin.com/YCvp?scheme=snssdk1128%3A%2F%2Fec_goods_detail%3Fpromotion_id%3D3552098369008746198%26meta_params%3D%257B%2522entrance_info%2522%253A%2522%257B%255C%2522share_content%255C%2522%253A%255C%2522product_detail%255C%2522%252C%255C%2522share_object%255C%2522%253A%255C%2522copy%255C%2522%257D%2522%257D%26enter_from%3Dnew_h5_product_detail%26request_additions%3D%257B%2522sec_author_id%2522%253A%2522MS4wLjABAAAA7goVXO3rYnUasFoMtoxhS_K_GCUXwJy5xQFuDSX4P2BEkhObhOVz7ofm91852RIL%2522%252C%2522enter_from%2522%253A%2522new_h5_product_detail%2522%257D%26scene_from%3Dshare_reflow%26use_link_command%3D1";
                } else {
                    return;
                }
            } else {
                return;
            }
            intent2.setData(Uri.parse(str));
            startActivity(intent2);
        } else if (isLocationEnable(this)) {
            BeginScanBLE();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Log.d(TAG, "onCreate");
        Log.d(TAG, "包名：" + getPackageName());
        super.onCreate(bundle);
        int i = 0;
        Cocos2dxActivity.setStateBarColor(false);
        getWindow().addFlags(128);
        if (getResources().getConfiguration().locale.getLanguage().endsWith("zh")) {
            i = 1;
        }
        this._isChinese = i;
        instance = this;
        _msgList = new ArrayList<>();
        devices = new ArrayList<>();
        rissList = new ArrayList<>();
        isSupportBLE = BluetoothHolder.init(this);
        f2449me = this;
        if (Build.VERSION.SDK_INT >= 31) {
            initPermission();
            if (C0025a.m75a((Context) this, "android.permission.BLUETOOTH_CONNECT") != 0) {
                Log.d(TAG, "蓝牙权限未开启");
                return;
            }
        }
        bluetoothResume();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
        if (this._haveInitBLE) {
            bluetoothDestroy(true);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
    }

    @TargetApi(23)
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
            if (C0025a.m75a((Context) this, "android.permission.BLUETOOTH_CONNECT") == 0) {
                Log.d(TAG, "蓝牙权限已开启2");
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
        this._isChinese = getResources().getConfiguration().locale.getLanguage().endsWith("zh") ? 1 : 0;
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor != null && IsConnected() && sensorEvent.sensor.getType() == 1) {
            float[] fArr = sensorEvent.values;
            ChangePos((int) fArr[0], (int) fArr[1], (int) fArr[2]);
        }
    }

    public void sendCode(String str) {
    }
}
