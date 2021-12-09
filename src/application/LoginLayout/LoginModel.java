package application.LoginLayout;

import javafx.beans.property.ObjectProperty;
import rapidFX.RautoGenerate;
import rapidFX.interfaces.RapidModel;

public class LoginModel implements RapidModel
{
	@RautoGenerate
	private ObjectProperty<String> titleText;
	
	@Override
	public void setUpModel()
	{
		titleText.set("Welcome");
		
	}
	public void login()
	{
		// "Buisness Logic" -> Everything that only needs Properties and backend to work
		titleText.set("You are logged in");
	}
	
}
