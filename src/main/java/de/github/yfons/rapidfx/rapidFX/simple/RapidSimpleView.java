package de.github.yfons.rapidfx.rapidFX.simple;

import de.github.yfons.rapidfx.rapidFX.interfaces.RapidView;
import javafx.scene.layout.Pane;

public abstract class RapidSimpleView<ROOT_PANE_TYPE extends Pane> implements RapidView<ROOT_PANE_TYPE>
{
	protected ROOT_PANE_TYPE root;

	@Override
	public ROOT_PANE_TYPE getRootPane()
	{
		return root;
	}

	/**
	 * adds to the root node which is given by {@link RapidSimpleView#root} a Style
	 * Sheet which is named after the view class name + .css <br>
	 * e.g ExamplePaneView.java -> ExamplePaneView.css
	 */
	protected final void cssStyleRoot()
	{
		root.getStylesheets().add(findCssStyleSheet());
	}

	private final String findCssStyleSheet()
	{
		return getResource(getClass().getSimpleName() + ".css");
	}

}
