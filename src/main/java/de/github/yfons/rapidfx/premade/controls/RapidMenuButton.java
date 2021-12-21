package de.github.yfons.rapidfx.premade.controls;

import de.github.yfons.rapidfx.rapidFX.simple.RapidSimpleLayout;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;

public class RapidMenuButton extends RapidSimpleLayout<MenuButton>
{
	private Image image;

	public RapidMenuButton(String text)
	{
		layout = new MenuButton(image+"");
		layout.textProperty().bind(Bindings.when(layout.hoverProperty()).then(image+text).otherwise(image+""));
	}
}
