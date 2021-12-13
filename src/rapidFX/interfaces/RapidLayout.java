package rapidFX.interfaces;

import javafx.scene.Node;

public interface RapidLayout <T extends Node>  extends RapidFXComponent 
{
	T getLayout();
}
