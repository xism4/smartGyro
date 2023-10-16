package org.cocos2dx.package_1;

import java.io.File;
import java.io.FileInputStream;

public class GameControllerUtils
{
  public GameControllerUtils() {}
  
  public static void ensureDirectoryExist(String paramString)
  {
    paramString = new File(paramString);
    if (!paramString.exists()) {
      paramString.mkdirs();
    }
  }
  
  public static String readJsonFile(String paramString)
  {
    paramString = new File(paramString);
    if (!paramString.exists()) {
      return null;
    }
    try
    {
      paramString = new FileInputStream(paramString);
      int i = paramString.available();
      byte[] arrayOfByte = new byte[i];
      paramString.read(arrayOfByte);
      paramString.close();
      paramString = new String(arrayOfByte, "UTF-8");
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
}
