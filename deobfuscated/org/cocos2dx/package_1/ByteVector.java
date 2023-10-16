package org.cocos2dx.package_1;

import android.util.Log;
import cz.msebera.android.http.Header;
import java.io.File;
import lombok.eclipse.handlers.http.FileAsyncHttpResponseHandler;

class ByteVector
  extends FileAsyncHttpResponseHandler
{
  private Cocos2dxDownloader a;
  int b;
  private long data;
  File f;
  private long size;
  
  public ByteVector(Cocos2dxDownloader paramCocos2dxDownloader, int paramInt, File paramFile1, File paramFile2)
  {
    super(paramFile1, true);
    f = paramFile2;
    a = paramCocos2dxDownloader;
    b = paramInt;
    size = getTargetFile().length();
    data = 0L;
  }
  
  void a(String paramString)
  {
    Log.d("Cocos2dxDownloader", paramString);
  }
  
  public void b(int paramInt, Header[] paramArrayOfHeader, File paramFile)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("onSuccess(i:");
    localStringBuilder.append(paramInt);
    localStringBuilder.append(" headers:");
    localStringBuilder.append(paramArrayOfHeader);
    localStringBuilder.append(" file:");
    localStringBuilder.append(paramFile);
    a(localStringBuilder.toString());
    if (f.exists())
    {
      if (f.isDirectory()) {
        paramArrayOfHeader = new StringBuilder();
      }
      for (paramFile = "Dest file is directory:";; paramFile = "Can't remove old file:")
      {
        paramArrayOfHeader.append(paramFile);
        paramArrayOfHeader.append(f.getAbsolutePath());
        paramArrayOfHeader = paramArrayOfHeader.toString();
        break label158;
        if (f.delete()) {
          break;
        }
        paramArrayOfHeader = new StringBuilder();
      }
    }
    getTargetFile().renameTo(f);
    paramArrayOfHeader = null;
    label158:
    a.onFinish(b, 0, paramArrayOfHeader, null);
  }
  
  public void read()
  {
    a.runNextTaskIfExists();
  }
  
  public void write()
  {
    a.onStart(b);
  }
  
  public void write(int paramInt, Header[] paramArrayOfHeader, Throwable paramThrowable, File paramFile)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("onFailure(i:");
    localStringBuilder.append(paramInt);
    localStringBuilder.append(" headers:");
    localStringBuilder.append(paramArrayOfHeader);
    localStringBuilder.append(" throwable:");
    localStringBuilder.append(paramThrowable);
    localStringBuilder.append(" file:");
    localStringBuilder.append(paramFile);
    a(localStringBuilder.toString());
    if (paramThrowable != null) {
      paramArrayOfHeader = paramThrowable.toString();
    } else {
      paramArrayOfHeader = "";
    }
    a.onFinish(b, paramInt, paramArrayOfHeader, null);
  }
  
  public void write(long paramLong1, long paramLong2)
  {
    long l1 = data;
    long l2 = size;
    a.onProgress(b, paramLong1 - l1, paramLong1 + l2, paramLong2 + l2);
    data = paramLong1;
  }
}
