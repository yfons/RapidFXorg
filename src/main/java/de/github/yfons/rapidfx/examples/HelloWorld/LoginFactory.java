package de.github.yfons.rapidfx.examples.HelloWorld;

import de.github.yfons.rapidfx.rapidFX.core.RapidFX;
import de.github.yfons.rapidfx.rapidFX.interfaces.RapidFactory;
import de.github.yfons.rapidfx.rapidFX.simple.RapidSimpleFactory;
public class LoginFactory extends RapidSimpleFactory<Login> implements RapidFactory
{
	public static Login create() {
		return RapidFX.create(Login::new);
	}
}

