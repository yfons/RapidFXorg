package rapidFX.interfaces;

import rapidFX.core.RapidFX;

public interface RapidController extends RapidFXComponent
{
	RapidView getView();
	RapidModel getModel();
	<T extends RapidController> T setUpController();
	default void RapidFXgenerateMe()
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
