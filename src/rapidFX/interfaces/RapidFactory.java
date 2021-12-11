package rapidFX.interfaces;

public interface RapidFactory extends RapidFXComponent
{
	RapidController newController();
	void setUpFactory();
}
