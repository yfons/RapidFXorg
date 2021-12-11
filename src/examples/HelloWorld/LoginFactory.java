package examples.HelloWorld;

import rapidFX.simple.RapidSimpleController;
import rapidFX.simple.RapidSimpleFactory;

public class LoginFactory extends RapidSimpleFactory<Login>
{

	@Override
	public Login newController()
	{

		return super.RapidFXController(new Login()).setUpController();
	}

	@Override
	public void setUpFactory()
	{
		// TODO Auto-generated method stub
		
	}

}
