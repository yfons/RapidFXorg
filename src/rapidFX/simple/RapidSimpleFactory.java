package rapidFX.simple;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import rapidFX.interfaces.RapidController;
import rapidFX.interfaces.RapidFactory;

public abstract class RapidSimpleFactory<ControllerClass extends RapidController> implements RapidFactory
{
	protected final ObjectProperty<ControllerClass> controllerProperty = new SimpleObjectProperty<>();

	@Override
	public abstract  ControllerClass newController();

	@Override
	public abstract void setUpFactory();

	public final ObjectProperty<ControllerClass> getControllerProperty()
	{
		return controllerProperty;
	}
	protected final void setController(ControllerClass controller)
	{
		this.controllerProperty.set(controller);
	}
}
