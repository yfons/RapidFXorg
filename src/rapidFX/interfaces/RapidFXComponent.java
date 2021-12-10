package rapidFX.interfaces;

import rapidFX.annotation.RautoGenerate;
import rapidFX.core.RapidFX;

public interface RapidFXComponent
{
	/**
	 * Should only be used on Classes which are not affected by the {@link RapidFX#rapidGenerate(RapidController) rapidGeneration} but still want the {@link RapidFX#setUp(RapidFXComponent...) auto Generation }
	 * for initializing their fields which are tagged as {@link RautoGenerate }
	 */
	 default void RapidFXSetUpMe()
	{
		try
		{
			RapidFX.setUp(this);
		} catch (IllegalArgumentException | SecurityException e)
		{
			e.printStackTrace();
		} 
	}
}
