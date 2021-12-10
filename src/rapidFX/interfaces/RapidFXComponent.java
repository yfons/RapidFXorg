package rapidFX.interfaces;

import rapidFX.core.RapidFX;

public interface RapidFXComponent
{
	/**
	 * Should only be used on Classes which are not affected by the RapidFX.rapidGenerate but still want the RapidFX.setUp(this)
	 * for initializing their fields which are tagged as RautoGenerate
	 */
	 default void RapidFXSetUpMe()
	{
		try
		{
			RapidFX.setUp(this);
		} catch (IllegalArgumentException | IllegalAccessException | SecurityException e)
		{
			e.printStackTrace();
		}
	}
}
