package org.cocos2dx.package_1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteClosable;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Cocos2dxLocalStorage
{
  private static String DATABASE_NAME;
  private static final int DATABASE_VERSION = 1;
  private static final String PAGE_KEY = "Cocos2dxLocalStorage";
  private static String TABLE_NAME;
  private static SQLiteDatabase mDatabase;
  private static a mDatabaseOpenHelper;
  
  public Cocos2dxLocalStorage() {}
  
  public static void clear()
  {
    try
    {
      Object localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("delete from ");
      Object localObject2 = TABLE_NAME;
      ((StringBuilder)localObject1).append((String)localObject2);
      localObject1 = ((StringBuilder)localObject1).toString();
      localObject2 = mDatabase;
      ((SQLiteDatabase)localObject2).execSQL((String)localObject1);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public static void destroy()
  {
    SQLiteDatabase localSQLiteDatabase = mDatabase;
    if (localSQLiteDatabase != null) {
      localSQLiteDatabase.close();
    }
  }
  
  public static String getItem(String paramString)
  {
    Cursor localCursor = null;
    Object localObject2 = null;
    Object localObject1 = localCursor;
    try
    {
      Object localObject3 = new StringBuilder();
      localObject1 = localCursor;
      ((StringBuilder)localObject3).append("select value from ");
      Object localObject4 = TABLE_NAME;
      localObject1 = localCursor;
      ((StringBuilder)localObject3).append((String)localObject4);
      localObject1 = localCursor;
      ((StringBuilder)localObject3).append(" where key=?");
      localObject1 = localCursor;
      localObject3 = ((StringBuilder)localObject3).toString();
      localObject4 = mDatabase;
      localObject1 = localCursor;
      localCursor = ((SQLiteDatabase)localObject4).rawQuery((String)localObject3, new String[] { paramString });
      for (paramString = localObject2;; paramString = localCursor.getString(localCursor.getColumnIndex("value")))
      {
        localObject1 = paramString;
        boolean bool = localCursor.moveToNext();
        if (!bool) {
          break;
        }
        if (paramString != null)
        {
          localObject1 = paramString;
          Log.e("Cocos2dxLocalStorage", "The key contains more than one value.");
          break;
        }
        localObject1 = paramString;
      }
      localObject1 = paramString;
      localCursor.close();
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return localObject1;
  }
  
  public static boolean init(String paramString1, String paramString2)
  {
    if (Cocos2dxActivity.getContext() != null)
    {
      DATABASE_NAME = paramString1;
      TABLE_NAME = paramString2;
      mDatabaseOpenHelper = new a(Cocos2dxActivity.getContext());
      mDatabase = mDatabaseOpenHelper.getWritableDatabase();
      return true;
    }
    return false;
  }
  
  public static void removeItem(String paramString)
  {
    try
    {
      Object localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("delete from ");
      Object localObject2 = TABLE_NAME;
      ((StringBuilder)localObject1).append((String)localObject2);
      ((StringBuilder)localObject1).append(" where key=?");
      localObject1 = ((StringBuilder)localObject1).toString();
      localObject2 = mDatabase;
      ((SQLiteDatabase)localObject2).execSQL((String)localObject1, new Object[] { paramString });
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static void setItem(String paramString1, String paramString2)
  {
    try
    {
      Object localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("replace into ");
      Object localObject2 = TABLE_NAME;
      ((StringBuilder)localObject1).append((String)localObject2);
      ((StringBuilder)localObject1).append("(key,value)values(?,?)");
      localObject1 = ((StringBuilder)localObject1).toString();
      localObject2 = mDatabase;
      ((SQLiteDatabase)localObject2).execSQL((String)localObject1, new Object[] { paramString1, paramString2 });
      return;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  class a
    extends SQLiteOpenHelper
  {
    a()
    {
      super(Cocos2dxLocalStorage.DATABASE_NAME, null, 1);
    }
    
    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("CREATE TABLE IF NOT EXISTS ");
      localStringBuilder.append(Cocos2dxLocalStorage.TABLE_NAME);
      localStringBuilder.append("(key TEXT PRIMARY KEY,value TEXT);");
      paramSQLiteDatabase.execSQL(localStringBuilder.toString());
    }
    
    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
      paramSQLiteDatabase = new StringBuilder();
      paramSQLiteDatabase.append("Upgrading database from version ");
      paramSQLiteDatabase.append(paramInt1);
      paramSQLiteDatabase.append(" to ");
      paramSQLiteDatabase.append(paramInt2);
      paramSQLiteDatabase.append(", which will destroy all old data");
      Log.w("Cocos2dxLocalStorage", paramSQLiteDatabase.toString());
    }
  }
}
