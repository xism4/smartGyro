package org.cocos2dx.package_1;

import android.util.Log;
import cz.msebera.android.http.Header;
import lombok.eclipse.handlers.http.BinaryHttpResponseHandler;

class BinDecorator
  extends BinaryHttpResponseHandler
{
  int file;
  private long size;
  private Cocos2dxDownloader this$0;
  
  public BinDecorator(Cocos2dxDownloader paramCocos2dxDownloader, int paramInt)
  {
    super(new String[] { ".*" });
    this$0 = paramCocos2dxDownloader;
    file = paramInt;
    size = 0L;
  }
  
  public void close(int paramInt, Header[] paramArrayOfHeader, byte[] paramArrayOfByte, Throwable paramThrowable)
  {
    paramArrayOfByte = new StringBuilder();
    paramArrayOfByte.append("onFailure(i:");
    paramArrayOfByte.append(paramInt);
    paramArrayOfByte.append(" headers:");
    paramArrayOfByte.append(paramArrayOfHeader);
    paramArrayOfByte.append(" throwable:");
    paramArrayOfByte.append(paramThrowable);
    write(paramArrayOfByte.toString());
    if (paramThrowable != null) {
      paramArrayOfHeader = paramThrowable.toString();
    } else {
      paramArrayOfHeader = "";
    }
    this$0.onFinish(file, paramInt, paramArrayOfHeader, null);
  }
  
  public void read()
  {
    this$0.runNextTaskIfExists();
  }
  
  public void read(int paramInt, Header[] paramArrayOfHeader, byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("onSuccess(i:");
    localStringBuilder.append(paramInt);
    localStringBuilder.append(" headers:");
    localStringBuilder.append(paramArrayOfHeader);
    write(localStringBuilder.toString());
    this$0.onFinish(file, 0, null, paramArrayOfByte);
  }
  
  public void write()
  {
    this$0.onStart(file);
  }
  
  public void write(long paramLong1, long paramLong2)
  {
    long l = size;
    this$0.onProgress(file, paramLong1 - l, paramLong1, paramLong2);
    size = paramLong1;
  }
  
  void write(String paramString)
  {
    Log.d("Cocos2dxDownloader", paramString);
  }
}
