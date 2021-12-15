package rapidFX.simple;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import rapidFX.interfaces.RapidController;
import rapidFX.interfaces.RapidFactory;

public abstract class RapidSimpleFactory<RAPID_CONTROLLER_CLASS extends RapidController> implements RapidFactory
{
	protected final ObjectProperty<RAPID_CONTROLLER_CLASS> controllerProperty = new SimpleObjectProperty<>();
	public final ObjectProperty<RAPID_CONTROLLER_CLASS> getControllerProperty()
	{
		return controllerProperty;
	}
	protected final void setController(RAPID_CONTROLLER_CLASS controller)
	{
		this.controllerProperty.set(controller);
	}
}
