package rapidFX.abstracts;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import rapidFX.interfaces.RapidController;
import rapidFX.interfaces.RapidFX;
import rapidFX.interfaces.RapidFactory;

public abstract class RapidSimpleFactory<ControllerClass extends RapidController> implements RapidFactory
{
	protected final ObjectProperty<ControllerClass> controllerProperty = new SimpleObjectProperty<>();

	@Override
	public abstract ObjectProperty<ControllerClass> setNewController();

	public final ObjectProperty<ControllerClass> getControllerProperty()
	{
		return controllerProperty;
	}
	protected final void setController(ControllerClass controller)
	{
		this.controllerProperty.set(controller);
	}
	protected final void RapidFXNewController(ControllerClass newController)
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
	public abstract void setUpFactory();

}
