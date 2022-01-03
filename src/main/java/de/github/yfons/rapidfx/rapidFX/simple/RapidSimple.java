package de.github.yfons.rapidfx.rapidFX.simple;

import java.net.URL;

import de.github.yfons.rapidfx.rapidFX.core.RapidFX;
import de.github.yfons.rapidfx.rapidFX.core.RapidFXRuntimeException;

public abstract class RapidSimple
{
	protected final void RapidFXSetUPMe()
	{
			RapidFX.setUp(this);
	}
	/**
	 * searches a resource in the Same path as the Calling Class is in, if the class
	 * is not "open PACKAGENAME" then this will result in a NPE
	 *
	 * @param resourceName
	 * @return
	 */
	protected final String getResource(String resourceName)
	{
			return getResourceURI(resourceName).toString();
	}

	protected final URL getResourceURI(String resourceName)
	{
		URL url = getClass().getResource(resourceName);
		if (url == null) {
			throw new RapidFXRuntimeException("\nThe Resource was not found.\nThis can be caused by not having \"opens PACKAGENAME\" in the module info so it's not accessible"
			+ "\n\t=> CLASS => "+ this.getClass()
			+ "\n\t=> RESOURCE => " + resourceName+"\n");
			}
		return url;
	}
}
