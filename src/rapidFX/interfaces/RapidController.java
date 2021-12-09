package rapidFX.interfaces;

public interface RapidController extends RapidFXComponent
{
	RapidView getView();
	RapidModel getModel();
	void setUpController();
}
