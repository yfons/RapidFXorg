package examples.HelloWorld;

import javafx.beans.property.StringProperty;
import rapidFX.annotation.RapidFXautoGenerate;
import rapidFX.interfaces.RapidModel;
//"Buisness Logic" -> Everything that only needs Properties and backend to work
// no Eventhandlers, no Nodes here
public class LoginModel implements RapidModel
{
	@RapidFXautoGenerate
	private StringProperty titleTextProperty;
	// all Properties with the Name SimpleXXXProperty can get Autogenerated, eg ObjectProperty, StringProperty,DoubleProperty....
	@RapidFXautoGenerate
	private StringProperty greetingsProperty;

	public void setUpModel()
	{
		titleTextProperty.set("Hello World");
	}
	public void login()
	{
		titleTextProperty.set("You are logged in");
		System.out.println("Hello from the Model");
	}

}
