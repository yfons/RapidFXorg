package de.github.yfons.rapidfx.examples.advanced;

import de.github.yfons.rapidfx.premade.network.RMessage;
import de.github.yfons.rapidfx.rapidFX.simple.RapidSimpleModel;

public class LoginModel extends RapidSimpleModel
{
	private final RMessage<Account> message;

	public LoginModel(RMessage<Account> message)
	{
		this.message = message;
	}

	public boolean login()
	{
		System.out.println(message.getContent(Account.NAME));
		System.out.println(message.getContent(Account.PASSWORD));
		return true;
	}
}
