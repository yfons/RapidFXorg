package rapidFX.interfaces;

import rapidFX.annotation.RautoGenerate;
import rapidFX.core.RapidFX;

public interface RapidFXComponent
{
	/**
	 * Should only be used on Classes which are not affected by the
	 * {@link RapidFX#rapidGenerate(RapidController) rapidGeneration} but still want
	 * the {@link RapidFX#setUp(RapidFXComponent...) auto Generation } for
	 * initializing their fields which are tagged as {@link RautoGenerate }
	 * or for unit testing Controller / Model classes
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

	default String getResource(String resourceName)
	{
		try
		{
			return getClass().getResource(resourceName).toString();
		} catch (NullPointerException e)
		{
			throw new NullPointerException("The Resource::  "+resourceName +"\nwas not found in:: " + this.getClass() + " :: with the Name :: "
					+ this.getClass().getSimpleName() + ".css\nThis can be caused by not having \"opens PACKAGENAME\" in the module info so it's not accessible\nNull Pointer Exception occured::\n" + e.getMessage());
		}
	}
}
