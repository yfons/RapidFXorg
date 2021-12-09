package rapidFX.interfaces;

import javafx.beans.property.ObjectProperty;

public interface RapidFactory extends RapidFXComponent
{
	ObjectProperty<? extends RapidController> setNewController();
	void setUpFactory();
}
