package rapidFX.abstracts;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import rapidFX.interfaces.RapidController;
import rapidFX.interfaces.RapidFactory;
import rapidFX.interfaces.RapidFX;

public abstract class RapidSimpleFactory<ControllerClass extends RapidController> implements RapidFactory
{
	protected final ObjectProperty<ControllerClass> controllerProperty = new SimpleObjectProperty<>();
	
	public abstract ObjectProperty<ControllerClass> setNewController();
	
	public final ObjectProperty<ControllerClass> getControllerProperty()
	{
		return controllerProperty;
	}
	
	protected void RapidFXNewController(ControllerClass newController)
	{
		try
		{
			RapidFX.rapidGenerate(newController).setUpController();
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public abstract RapidSimpleFactory<ControllerClass> setUpFactory();
	
}
