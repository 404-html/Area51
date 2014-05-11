/**
 * 
 */
package javaMeasure.control.interfaces;

import javaMeasure.view.interfaces.ILoginGui;

/**
 * @author Christian Budtz, Runi Vørmadal
 *
 */
public interface ILoginController {
	void setGui(ILoginGui Gui);
	void showGui(boolean b);
	void bthLoginPressed();
	void btnNewUserPressed();
}
