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

  /** The message. */
  private final RMessage<Account> message;

  /**
   * Instantiates a new login model.
   *
   * @param message the message
   */
  public LoginModel(RMessage<Account> message) {
    this.message = message;
  }

  /**
   * Login.
   *
   * @return true, if successful
   */
  public boolean login() {
    System.out.println(message.getContent(Account.NAME));
    System.out.println(message.getContent(Account.PASSWORD));
    return true;
  }
}
