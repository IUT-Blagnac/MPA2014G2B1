package JTables;
import javax.swing.JTable;

import AbstractTableModel.AbstractTableModelProjet;

/**
 * Classe rédéfinissant (extends) la classe JTable pour l'adapter à nos objets Projets
 * 
 * 
 * @author Groupe2B1
 * @version 3.0
 */

@SuppressWarnings("serial")
public class JTableProjet extends JTable {

	public AbstractTableModelProjet modele;
	
	/**
	 * Constructeur
	 * @param AbstractTableModelEtudiant pmodele
	 * 			le modèle correspondant aux étudiants
	 */
	public JTableProjet(AbstractTableModelProjet pmodele) {
		super(pmodele);
		modele = pmodele;

	}

}
