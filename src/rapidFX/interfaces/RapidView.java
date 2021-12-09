package rapidFX.interfaces;

import javafx.scene.layout.Pane;

public interface RapidView extends RapidFXComponent
{
	Pane getRootPane();
	void setUpView();
}
