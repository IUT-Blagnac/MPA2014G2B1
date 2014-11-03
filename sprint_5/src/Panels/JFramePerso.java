package Panels;
import javax.swing.JFrame;


public abstract class JFramePerso extends JFrame{

	/**
	 * Constructeur paramètré.
	 * 
	 * @param title
	 * 		Le tire de la fenêtre.
	 * 
	 */
	public JFramePerso(String title) {
		super(title);
	}
	
	public abstract Database getBaseDonnees() ;
	
}
