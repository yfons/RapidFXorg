package premade;

import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.Scene;
import rapidFX.interfaces.RapidController;

public class RViewManager<keyValue>
{
	private final ObservableMap<keyValue, RapidController> factoryMap = FXCollections.observableHashMap();
	private Scene scene;
	public RViewManager(Scene scene){
		this.scene = scene;
	}
	public void appendFactory(keyValue key,RapidController factory)
	{
		factoryMap.put(key,factory);
	}
	public void removeFactory(keyValue key) {
		factoryMap.remove(key);
	}
	public ObservableMap<keyValue, RapidController> getMap() {
		return factoryMap;
	}
	public Set<keyValue> getKeys() {
		return factoryMap.keySet();
	}

	public void swapToView(keyValue key) {
		scene.setRoot(factoryMap.get(key).getView().getRootPane());
	}
}
