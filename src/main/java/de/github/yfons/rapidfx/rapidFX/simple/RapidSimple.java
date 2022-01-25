/*
 * 
 */
package de.github.yfons.rapidfx.rapidFX.simple;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import de.github.yfons.rapidfx.rapidFX.core.Rapidfx;
import de.github.yfons.rapidfx.rapidFX.core.RapidfxRuntimeException;
import de.github.yfons.rapidfx.rapidFX.core.helper.RmBuilder;

/**
 * The Class RapidSimple.
 */
public abstract class RapidSimple {

  /**
   * searches a resource in the Same path as the Calling Class is in, if the class
   * is not "open PACKAGENAME" then this will result in a
   * {@link NullPointerException}.
   *
   * @param resourceName the resource name
   * @return the resource
   */
  protected final String getResource(String resourceName) {
    return getResourceURL(resourceName).toString();
  }

  /**
   * searches a resource in the Same path as the Calling Class is in, if the class
   * is not "open PACKAGENAME" then this will result in a
   * {@link NullPointerException}.
   *
   * @param resourceName the resource name
   * @return the resource
   */
  protected final URL getResourceURL(String resourceName) {
    URL url = getClass().getResource(resourceName);
    if (url == null) {
      throw new RapidfxRuntimeException("\nThe Resource was not found."
          + "\nThis can be caused by not having \"opens PACKAGENAME\" in the module info"
          + " so it's not accessible" + RmBuilder.clazz(this.getClass())
          + RmBuilder.build(resourceName, "RESOURCE"));
    }
    return url;
  }

  /**
   * searches a resource in the Same path as the Calling Class is in, if the class
   * is not "open PACKAGENAME" then this will result in a
   * {@link NullPointerException}.
   *
   * @param resourceName the resource name
   * @return the File
   */
  protected final File getFile(String resourceName) {
    try {
      return new File(getResourceURL(resourceName).toURI());
    } catch (URISyntaxException e) {
      throw new RapidfxRuntimeException(e.getMessage());
    }
  }

  /**
   * Generates all Properties which are Tagged with \@RapidAutoGenerate on this
   * Object.
   */
  public void rapidfxMe() {
    Rapidfx.createAny(this)
        .generate(this);
  }
}
