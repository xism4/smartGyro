package cz.msebera.android.http;

import java.nio.charset.CharacterCodingException;

public class MessageConstraintException
  extends CharacterCodingException
{
  private final String message;
  
  public MessageConstraintException(String paramString)
  {
    message = paramString;
  }
  
  public String getMessage()
  {
    return message;
  }
}
