package org.cocos2dx.package_1;

import lombok.eclipse.handlers.http.AsyncHttpResponseHandler;
import lombok.eclipse.handlers.http.RequestHandle;

class Page
{
  long b;
  long base;
  long index;
  AsyncHttpResponseHandler name = null;
  RequestHandle status = null;
  byte[] type;
  
  Page()
  {
    reset();
  }
  
  void reset()
  {
    b = 0L;
    base = 0L;
    index = 0L;
    type = null;
  }
}
