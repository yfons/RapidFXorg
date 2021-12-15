package rapidFX.interfaces;

import javafx.scene.layout.Pane;
import rapidFX.core.RapidFX;

public interface RapidController extends RapidFXComponent
{
	RapidView<?> getView();
	RapidModel getModel();
	Pane getRootPane();
	default void rapidFXgenerateMe()
	{
		try
		{
			RapidFX.rapidGenerate(this);
		} catch (IllegalAccessException | IllegalArgumentException | SecurityException e)
		{
			e.printStackTrace();
		}
	}
}
