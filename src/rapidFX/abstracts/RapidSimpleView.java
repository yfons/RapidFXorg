package rapidFX.abstracts;

import javafx.scene.layout.Pane;
import rapidFX.interfaces.RapidView;

public abstract class RapidSimpleView<rootPane extends Pane> implements RapidView
{
	protected rootPane root;

	@Override
	public rootPane getRootPane()
	{
		return root;
	}

	@Override
	public abstract void setUpView();

	protected final void loadMyCss()
	{
		try {
			this.root.getStylesheets().add(this.getClass().getResource(this.getClass().getName() + ".css").toString());
		} catch (Exception e) {
			System.err.println("Could not find: " + this.getClass().getName() + ".css");
		}
	}

}
