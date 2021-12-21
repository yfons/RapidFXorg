package de.github.yfons.rapidfx.rapidFX.simple;

import de.github.yfons.rapidfx.rapidFX.annotation.RapidFXautoGenerate;
import de.github.yfons.rapidfx.rapidFX.core.RapidFX;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidController;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidFXComponent;

public abstract class RapidSimple implements RapidFXComponent
{
	/**
	 * Should only be used on Classes which are not affected by the
	 * {@link RapidFX#rapidGenerate(RapidController) rapidGeneration} but still want
	 * the {@link RapidFX#setUp(RapidFXComponent...) auto Generation } for
	 * initializing their fields which are tagged as {@link RapidFXautoGenerate }
	 */
	public final void rapidFXSetUpMe()
	{
		RapidFXComponent.RapidFXSetUP(this);
	}

	/**
	 * searches a resource in the Same path as the Calling Class is in, if the class
	 * is not "open PACKAGENAME" then this will result in an Errror
	 * 
	 * @param resourceName
	 * @return
	 */
	public final String getResource(String resourceName)
	{
		try
		{
			return getClass().getResource(resourceName).toString();
		} catch (NullPointerException e)
		{
			throw new NullPointerException("The Resource::  " + resourceName + "\nwas not found in:: " + this.getClass()
					+ " :: with the Name :: \nThis can be caused by not having \"opens PACKAGENAME\" in the module info so it's not accessible\nNull Pointer Exception occured::\n"
					+ e.getMessage());
		}
	}
}
