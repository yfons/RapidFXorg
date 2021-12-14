package rapidFX.interfaces;

import java.io.InputStream;
import java.net.URL;

import javafx.scene.layout.Pane;

public interface RapidView<T extends Pane> extends RapidFXComponent
{
	T getRootPane();

}
