package Panels;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class JButtonPerso extends JButton {

	int indice;
	
	/**
	 * Constructeur avec paramètre d'un JButtonPerso.
	 * 
	 * @param IDGroupe
	 * 		L'id du groupe qui sera l'étiquette du bouton.
	 * @param pIndice
	 * 		L'indice du bouton.
	 */
	public JButtonPerso(String IDGroupe,int pIndice) {
		super(IDGroupe);
		indice = pIndice;
	}
	
	/**
	 * 
	 * @return L'indice du JButtonPerso.
	 */
	public int getIndice() {
		return indice;
	}
}
