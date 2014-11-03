package JTables;
import javax.swing.JTable;

import AbstractTableModel.AbstractTableModelEtudiant;

/**
 * Classe rédéfinissant (extends) la classe JTable pour l'adapter à nos objets Etudiant
 * 
 * 
 * @author Groupe2B1
 * @version 3.0
 */


@SuppressWarnings("serial")
public class JTableEtudiant extends JTable {

	public AbstractTableModelEtudiant modele;

	
	/**
	 * Constructeur
	 * @param AbstractTableModelEtudiant pmodele
	 * 			le modèle correspondant aux étudiants
	 */
	public JTableEtudiant(AbstractTableModelEtudiant pmodele) {
		super(pmodele);
		modele = pmodele;

	}

}
