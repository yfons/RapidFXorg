package examples.HelloWorld;

import rapidFX.simple.RapidSimpleFactory;

public class LoginFactory extends RapidSimpleFactory<Login>
{

	@Override
	public Login newController()
	{

		return new Login();
	}

	@Override
	public void setUpFactory()
	{
		// TODO Auto-generated method stub

	}

}
