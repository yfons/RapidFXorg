package rapidFX.simple;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import rapidFX.core.RapidFX;
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
	protected final  ControllerClass RapidFXCurrentController()
	{
		return RapidFXController(this.controllerProperty.get());
	}
	@SuppressWarnings("unchecked")
	protected final ControllerClass RapidFXController(ControllerClass controller)
	{
			try {
				return (ControllerClass) RapidFX.rapidGenerate(controller);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
				System.exit(-1);
				return null;
			}
	}
}
