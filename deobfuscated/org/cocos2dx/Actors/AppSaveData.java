package org.cocos2dx.Actors;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AppSaveData
{
  private static AppSaveData _instance;
  SharedPreferences.Editor _editor = sharedPrefs.edit();
  private SharedPreferences sharedPrefs = AppActivity.getInstance().getApplicationContext().getSharedPreferences("DATABASE", 0);
  
  public AppSaveData() {}
  
  public static AppSaveData getInstance()
  {
    if (_instance == null) {
      _instance = new AppSaveData();
    }
    return _instance;
  }
  
  public void clearAllData()
  {
    _editor.clear().commit();
  }
  
  public boolean getDataForBoolean(String paramString, boolean paramBoolean)
  {
    return sharedPrefs.getBoolean(paramString, paramBoolean);
  }
  
  public String getDataForString(String paramString1, String paramString2)
  {
    return sharedPrefs.getString(paramString1, paramString2);
  }
  
  public void setDataForBoolean(String paramString, boolean paramBoolean)
  {
    _editor.putBoolean(paramString, paramBoolean).commit();
  }
  
  public void setDataForString(String paramString1, String paramString2)
  {
    _editor.putString(paramString1, paramString2).commit();
  }
}
