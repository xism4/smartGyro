package lombok.eclipse.handlers.http;

import java.io.Serializable;

public class RequestParams
  implements Serializable
{
  protected String getParamString()
  {
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
}
