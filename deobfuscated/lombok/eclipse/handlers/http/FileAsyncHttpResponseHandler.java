package lombok.eclipse.handlers.http;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpEntity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;

public abstract class FileAsyncHttpResponseHandler
  extends AsyncHttpResponseHandler
{
  protected final boolean append;
  protected final File file;
  protected File frontendFile;
  protected final boolean renameIfExists;
  
  public FileAsyncHttpResponseHandler(File paramFile, boolean paramBoolean)
  {
    this(paramFile, paramBoolean, false);
  }
  
  public FileAsyncHttpResponseHandler(File paramFile, boolean paramBoolean1, boolean paramBoolean2)
  {
    this(paramFile, paramBoolean1, paramBoolean2, false);
  }
  
  public FileAsyncHttpResponseHandler(File paramFile, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    super(paramBoolean3);
    if (paramFile != null) {
      paramBoolean3 = true;
    } else {
      paramBoolean3 = false;
    }
    Utils.asserts(paramBoolean3, "File passed into FileAsyncHttpResponseHandler constructor must not be null");
    if ((!paramFile.isDirectory()) && (!paramFile.getParentFile().isDirectory())) {
      Utils.asserts(paramFile.getParentFile().mkdirs(), "Cannot create parent directories for requested File location");
    }
    if ((paramFile.isDirectory()) && (!paramFile.mkdirs())) {
      AsyncHttpClient.log.d("FileAsyncHttpRH", "Cannot create directories for requested Directory location, might not be a problem");
    }
    file = paramFile;
    append = paramBoolean1;
    renameIfExists = paramBoolean2;
  }
  
  public abstract void b(int paramInt, Header[] paramArrayOfHeader, File paramFile);
  
  public final void close(int paramInt, Header[] paramArrayOfHeader, byte[] paramArrayOfByte, Throwable paramThrowable)
  {
    write(paramInt, paramArrayOfHeader, paramThrowable, getTargetFile());
  }
  
  protected File getOriginalFile()
  {
    boolean bool;
    if (file != null) {
      bool = true;
    } else {
      bool = false;
    }
    Utils.asserts(bool, "Target file is null, fatal!");
    return file;
  }
  
  protected byte[] getResponseData(HttpEntity paramHttpEntity)
  {
    if (paramHttpEntity != null)
    {
      InputStream localInputStream = paramHttpEntity.getContent();
      long l = paramHttpEntity.getContentLength();
      paramHttpEntity = new FileOutputStream(getTargetFile(), append);
      if (localInputStream != null) {
        try
        {
          byte[] arrayOfByte = new byte['?'];
          int i = 0;
          for (;;)
          {
            int j = localInputStream.read(arrayOfByte);
            if (j == -1) {
              break;
            }
            boolean bool = Thread.currentThread().isInterrupted();
            if (bool) {
              break;
            }
            i += j;
            paramHttpEntity.write(arrayOfByte, 0, j);
            sendProgressMessage(i, l);
          }
          AsyncHttpClient.silentCloseInputStream(localInputStream);
          paramHttpEntity.flush();
          AsyncHttpClient.silentCloseOutputStream(paramHttpEntity);
        }
        catch (Throwable localThrowable)
        {
          AsyncHttpClient.silentCloseInputStream(localInputStream);
          paramHttpEntity.flush();
          AsyncHttpClient.silentCloseOutputStream(paramHttpEntity);
          throw localThrowable;
        }
      }
    }
    return null;
  }
  
  public File getTargetFile()
  {
    if (frontendFile == null)
    {
      File localFile;
      if (getOriginalFile().isDirectory()) {
        localFile = getTargetFileByParsingURL();
      } else {
        localFile = getOriginalFile();
      }
      frontendFile = localFile;
    }
    return frontendFile;
  }
  
  protected File getTargetFileByParsingURL()
  {
    Utils.asserts(getOriginalFile().isDirectory(), "Target file is not a directory, cannot proceed");
    boolean bool;
    if (getRequestURI() != null) {
      bool = true;
    } else {
      bool = false;
    }
    Utils.asserts(bool, "RequestURI is null, cannot proceed");
    Object localObject1 = getRequestURI().toString();
    Object localObject2 = ((String)localObject1).substring(((String)localObject1).lastIndexOf('/') + 1, ((String)localObject1).length());
    localObject1 = new File(getOriginalFile(), (String)localObject2);
    if ((((File)localObject1).exists()) && (renameIfExists))
    {
      if (!((String)localObject2).contains("."))
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append((String)localObject2);
        ((StringBuilder)localObject1).append(" (%d)");
      }
      else
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append(((String)localObject2).substring(0, ((String)localObject2).lastIndexOf('.')));
        ((StringBuilder)localObject1).append(" (%d)");
        ((StringBuilder)localObject1).append(((String)localObject2).substring(((String)localObject2).lastIndexOf('.'), ((String)localObject2).length()));
      }
      localObject1 = ((StringBuilder)localObject1).toString();
      int i = 0;
      for (;;)
      {
        localObject2 = new File(getOriginalFile(), String.format((String)localObject1, new Object[] { Integer.valueOf(i) }));
        if (!((File)localObject2).exists()) {
          return localObject2;
        }
        i += 1;
      }
    }
    return localObject1;
  }
  
  public final void read(int paramInt, Header[] paramArrayOfHeader, byte[] paramArrayOfByte)
  {
    b(paramInt, paramArrayOfHeader, getTargetFile());
  }
  
  public abstract void write(int paramInt, Header[] paramArrayOfHeader, Throwable paramThrowable, File paramFile);
}
