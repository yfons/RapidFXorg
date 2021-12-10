package rapidFX.interfaces;

import javafx.beans.property.ObjectProperty;

public interface RapidFactory extends RapidFXComponent
{
	ObjectProperty<? extends RapidController> newController();
	void setUpFactory();
}
