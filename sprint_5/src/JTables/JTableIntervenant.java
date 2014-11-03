package JTables;
import javax.swing.JTable;

import AbstractTableModel.AbstractTableModelIntervenant;

/**
 * Classe rédéfinissant (extends) la classe JTable pour l'adapter à nos objets Intervenants
 * 
 * 
 * @author Groupe2B1
 * @version 3.0
 */

@SuppressWarnings("serial")
public class JTableIntervenant extends JTable {

	public AbstractTableModelIntervenant modele;

	
	/**
	 * Constructeur
	 * @param AbstractTableModelEtudiant pmodele
	 * 			le modèle correspondant aux intervenants
	 */
	public JTableIntervenant(AbstractTableModelIntervenant pmodele) {
		super(pmodele);
		modele = pmodele;

	}

}
