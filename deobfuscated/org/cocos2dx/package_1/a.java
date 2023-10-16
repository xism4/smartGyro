package org.cocos2dx.package_1;

import android.util.Log;
import cz.msebera.android.http.Header;
import lombok.eclipse.handlers.http.AsyncHttpResponseHandler;

class a
  extends AsyncHttpResponseHandler
{
  String a;
  String b;
  int c;
  String i;
  private Cocos2dxDownloader s;
  
  public a(Cocos2dxDownloader paramCocos2dxDownloader, int paramInt, String paramString1, String paramString2, String paramString3)
  {
    s = paramCocos2dxDownloader;
    c = paramInt;
    i = paramString1;
    a = paramString2;
    b = paramString3;
  }
  
  void a(String paramString)
  {
    Log.d("Cocos2dxDownloader", paramString);
  }
  
  public void close(int paramInt, Header[] paramArrayOfHeader, byte[] paramArrayOfByte, Throwable paramThrowable)
  {
    paramArrayOfByte = new StringBuilder();
    paramArrayOfByte.append("onFailure(code:");
    paramArrayOfByte.append(paramInt);
    paramArrayOfByte.append(" headers:");
    paramArrayOfByte.append(paramArrayOfHeader);
    paramArrayOfByte.append(" throwable:");
    paramArrayOfByte.append(paramThrowable);
    paramArrayOfByte.append(" id:");
    paramArrayOfByte.append(c);
    a(paramArrayOfByte.toString());
    if (paramThrowable != null) {
      paramArrayOfHeader = paramThrowable.toString();
    } else {
      paramArrayOfHeader = "";
    }
    s.onFinish(c, paramInt, paramArrayOfHeader, null);
  }
  
  public void read()
  {
    s.runNextTaskIfExists();
  }
  
  public void read(int paramInt, Header[] paramArrayOfHeader, byte[] paramArrayOfByte)
  {
    paramInt = 0;
    Boolean localBoolean = Boolean.valueOf(false);
    for (;;)
    {
      paramArrayOfByte = localBoolean;
      if (paramInt >= paramArrayOfHeader.length) {
        break;
      }
      paramArrayOfByte = paramArrayOfHeader[paramInt];
      if (paramArrayOfByte.getName().equals("Accept-Ranges"))
      {
        paramArrayOfByte = Boolean.valueOf(paramArrayOfByte.getValue().equals("bytes"));
        break;
      }
      paramInt += 1;
    }
    Cocos2dxDownloader.setResumingSupport(i, paramArrayOfByte);
    Cocos2dxDownloader.createTask(s, c, a, b);
  }
}
