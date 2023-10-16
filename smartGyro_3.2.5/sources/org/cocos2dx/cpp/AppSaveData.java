package org.cocos2dx.cpp;

import android.content.SharedPreferences;

public class AppSaveData {
    private static AppSaveData _instance;
    SharedPreferences.Editor _editor = this._sp.edit();
    private SharedPreferences _sp = AppActivity.getInstance().getApplicationContext().getSharedPreferences("DATABASE", 0);

    public static AppSaveData getInstance() {
        if (_instance == null) {
            _instance = new AppSaveData();
        }
        return _instance;
    }

    public void clearAllData() {
        this._editor.clear().commit();
    }

    public boolean getDataForBoolean(String str, boolean z) {
        return this._sp.getBoolean(str, z);
    }

    public String getDataForString(String str, String str2) {
        return this._sp.getString(str, str2);
    }

    public void setDataForBoolean(String str, boolean z) {
        this._editor.putBoolean(str, z).commit();
    }

    public void setDataForString(String str, String str2) {
        this._editor.putString(str, str2).commit();
    }
}
