package rapidFX.interfaces;

public interface RapidFactory <T extends RapidController>extends RapidFXComponent
{
	T newController();
	void setUpFactory();
}
