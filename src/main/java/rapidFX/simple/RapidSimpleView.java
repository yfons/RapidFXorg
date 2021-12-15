package rapidFX.simple;

import javafx.scene.layout.Pane;
import rapidFX.interfaces.RapidView;

public abstract class RapidSimpleView<rootPane extends Pane> implements RapidView<rootPane>
{
	protected rootPane root;

	@Override
	public rootPane getRootPane()
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
