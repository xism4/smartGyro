package org.cocos2dx.package_1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Cocos2dxLocalStorage {
    /* access modifiers changed from: private */
    public static String DATABASE_NAME = null;
    private static final int DATABASE_VERSION = 1;
    private static final String PAGE_KEY = "Cocos2dxLocalStorage";
    /* access modifiers changed from: private */
    public static String TABLE_NAME;
    private static SQLiteDatabase mDatabase;
    private static a mDatabaseOpenHelper;

    class a extends SQLiteOpenHelper {
        a(Context context) {
            super(context, Cocos2dxLocalStorage.DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + Cocos2dxLocalStorage.TABLE_NAME + "(key TEXT PRIMARY KEY,value TEXT);");
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            Log.w(Cocos2dxLocalStorage.PAGE_KEY, "Upgrading database from version " + i + " to " + i2 + ", which will destroy all old data");
        }
    }

    public static void clear() {
        try {
            StringBuilder $r1 = new StringBuilder();
            $r1.append("delete from ");
            $r1.append(TABLE_NAME);
            mDatabase.execSQL($r1.toString());
        } catch (Exception $r3) {
            $r3.printStackTrace();
        }
    }

    public static void destroy() {
        SQLiteDatabase $r0 = mDatabase;
        if ($r0 != null) {
            $r0.close();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        android.util.Log.e(PAGE_KEY, "The key contains more than one value.");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getItem(java.lang.String r12) {
        /*
            r0 = 0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()     // Catch:{ Exception -> 0x0045 }
            java.lang.String r2 = "select value from "
            r1.append(r2)     // Catch:{ Exception -> 0x0045 }
            java.lang.String r3 = TABLE_NAME
            r1.append(r3)     // Catch:{ Exception -> 0x0045 }
            java.lang.String r2 = " where key=?"
            r1.append(r2)     // Catch:{ Exception -> 0x0045 }
            java.lang.String r3 = r1.toString()     // Catch:{ Exception -> 0x0045 }
            android.database.sqlite.SQLiteDatabase r4 = mDatabase
            r6 = 1
            java.lang.String[] r5 = new java.lang.String[r6]
            r6 = 0
            r5[r6] = r12
            android.database.Cursor r7 = r4.rawQuery(r3, r5)     // Catch:{ Exception -> 0x0045 }
        L_0x0025:
            boolean r8 = r7.moveToNext()     // Catch:{ Exception -> 0x0045 }
            if (r8 == 0) goto L_0x0041
            if (r0 == 0) goto L_0x0035
            java.lang.String r2 = "Cocos2dxLocalStorage"
            java.lang.String r9 = "The key contains more than one value."
            android.util.Log.e(r2, r9)     // Catch:{ Exception -> 0x0045 }
            goto L_0x0041
        L_0x0035:
            java.lang.String r2 = "value"
            int r10 = r7.getColumnIndex(r2)     // Catch:{ Exception -> 0x0045 }
            java.lang.String r12 = r7.getString(r10)     // Catch:{ Exception -> 0x0045 }
            r0 = r12
            goto L_0x0025
        L_0x0041:
            r7.close()     // Catch:{ Exception -> 0x0045 }
            return r0
        L_0x0045:
            r11 = move-exception
            r11.printStackTrace()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.cocos2dx.package_1.Cocos2dxLocalStorage.getItem(java.lang.String):java.lang.String");
    }

    public static boolean init(String str, String str2) {
        if (Cocos2dxActivity.getContext() == null) {
            return false;
        }
        DATABASE_NAME = str;
        TABLE_NAME = str2;
        mDatabaseOpenHelper = new a(Cocos2dxActivity.getContext());
        mDatabase = mDatabaseOpenHelper.getWritableDatabase();
        return true;
    }

    public static void removeItem(String str) {
        try {
            StringBuilder $r2 = new StringBuilder();
            $r2.append("delete from ");
            $r2.append(TABLE_NAME);
            $r2.append(" where key=?");
            String $r0 = $r2.toString();
            mDatabase.execSQL($r0, new Object[]{str});
        } catch (Exception $r5) {
            $r5.printStackTrace();
        }
    }

    public static void setItem(String str, String str2) {
        try {
            StringBuilder $r3 = new StringBuilder();
            $r3.append("replace into ");
            $r3.append(TABLE_NAME);
            $r3.append("(key,value)values(?,?)");
            String $r1 = $r3.toString();
            mDatabase.execSQL($r1, new Object[]{str, str2});
        } catch (Exception $r6) {
            $r6.printStackTrace();
        }
    }
}
