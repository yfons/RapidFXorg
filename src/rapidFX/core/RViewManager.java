package rapidFX.core;

import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.Scene;
import rapidFX.interfaces.RapidFactory;

public class RViewManager<keyValue>
{
	private final ObservableMap<keyValue, RapidFactory> factoryMap = FXCollections.observableHashMap();
	private Scene scene;
	public RViewManager(Scene scene){
		this.scene = scene;
	}
	public void appendFactory(keyValue key,RapidFactory factory)
	{
		factoryMap.put(key,factory);
	}
	public void removeFactory(keyValue key) {
		factoryMap.remove(key);
		
	}
	public ObservableMap<keyValue, RapidFactory> getMap() {
		return factoryMap;
	}
	public Set<keyValue> getKeys() {
		return factoryMap.keySet();
	}

	public void swapToView(keyValue key) {
		scene.setRoot(factoryMap.get(key).newController().getView().getRootPane());
	}
}
