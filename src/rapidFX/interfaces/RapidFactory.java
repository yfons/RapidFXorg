package rapidFX.interfaces;

import javafx.beans.property.ObjectProperty;

public interface RapidFactory
{
	ObjectProperty<? extends RapidController> setNewController();
	RapidFactory setUpFactory();
}
