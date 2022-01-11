/*
 * 
 */
package de.github.yfons.rapidfx.examples.advanced;

import de.github.yfons.rapidfx.premade.network.RMessage;
import de.github.yfons.rapidfx.rapidFX.simple.RapidSimpleModel;

/**
 * The Class LoginModel.
 */
public class LoginModel extends RapidSimpleModel {

  /**
   * Login.
   *
   * @return true, if successful
   */
  public boolean login(RMessage<Account> message) {
    if(message != null) 
    {
      System.out.println(message.getContent(Account.NAME));
      System.out.println(message.getContent(Account.PASSWORD));
      return true;
    }
    return false;
    
  }
}
