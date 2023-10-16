package org.cocos2dx.package_1;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.message.BasicHeader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lombok.eclipse.handlers.http.AsyncHttpClient;
import lombok.eclipse.handlers.http.RequestHandle;

final class Channel
  implements Runnable
{
  Channel(String paramString1, Cocos2dxDownloader paramCocos2dxDownloader, int paramInt, String paramString2) {}
  
  public void run()
  {
    Page localPage = new Page();
    if (image.length() == 0)
    {
      name = new BinDecorator(this$0, d);
      status = Cocos2dxDownloader.access$100(this$0).get(Cocos2dxHelper.getActivity(), id, name);
    }
    Object localObject1;
    if (image.length() != 0)
    {
      for (localObject1 = id;; localObject1 = Cocos2dxDownloader.access$100(this$0).get(Cocos2dxHelper.getActivity(), id, (Header[])localObject1, null, name))
      {
        Object localObject3;
        try
        {
          localObject1 = new URI((String)localObject1).getHost();
          localObject3 = localObject1;
          if (((String)localObject1).startsWith("www.")) {
            localObject3 = ((String)localObject1).substring(4);
          }
          localObject1 = Boolean.valueOf(false);
          localObject4 = Boolean.valueOf(true);
          if (Cocos2dxDownloader.access$200().containsKey(localObject3))
          {
            localObject1 = (Boolean)Cocos2dxDownloader.access$200().get(localObject3);
            localObject4 = Boolean.valueOf(false);
          }
          if (((Boolean)localObject4).booleanValue())
          {
            name = new a(this$0, d, (String)localObject3, id, image);
            localObject1 = Cocos2dxDownloader.access$100(this$0).delete(Cocos2dxHelper.getActivity(), id, null, null, name);
            status = ((RequestHandle)localObject1);
          }
          else
          {
            localObject3 = new StringBuilder();
            ((StringBuilder)localObject3).append(image);
            ((StringBuilder)localObject3).append(Cocos2dxDownloader.access$300(this$0));
            localObject4 = new File(((StringBuilder)localObject3).toString());
            if (!((File)localObject4).isDirectory())
            {
              localObject3 = ((File)localObject4).getParentFile();
              if ((((File)localObject3).isDirectory()) || (((File)localObject3).mkdirs()))
              {
                localObject3 = new File(image);
                if (!((File)localObject3).isDirectory())
                {
                  name = new ByteVector(this$0, d, (File)localObject4, (File)localObject3);
                  localObject3 = null;
                  long l = ((File)localObject4).length();
                  if ((((Boolean)localObject1).booleanValue()) && (l > 0L))
                  {
                    localObject1 = new ArrayList();
                    localObject3 = new StringBuilder();
                    ((StringBuilder)localObject3).append("bytes=");
                    ((StringBuilder)localObject3).append(l);
                    ((StringBuilder)localObject3).append("-");
                    ((List)localObject1).add(new BasicHeader("Range", ((StringBuilder)localObject3).toString()));
                    localObject1 = (Header[])((List)localObject1).toArray(new Header[((List)localObject1).size()]);
                  }
                  else
                  {
                    localObject1 = localObject3;
                    if (l <= 0L) {}
                  }
                }
              }
            }
          }
        }
        catch (URISyntaxException localURISyntaxException)
        {
          Object localObject4;
          label495:
          for (;;) {}
        }
        try
        {
          localObject1 = new PrintWriter((File)localObject4);
          ((PrintWriter)localObject1).print("");
          ((PrintWriter)localObject1).close();
          localObject1 = localObject3;
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          Object localObject2 = localObject3;
          break label495;
        }
      }
    }
    else
    {
      if (status == null)
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("Can't create DownloadTask for ");
        ((StringBuilder)localObject1).append(id);
        Cocos2dxHelper.runOnGLThread(new SelectAction(this, ((StringBuilder)localObject1).toString()));
        return;
      }
      Cocos2dxDownloader.access$400(this$0).put(Integer.valueOf(d), localPage);
      return;
    }
  }
}
