package JTables;
import javax.swing.JTable;

import AbstractTableModel.AbstractTableModelSujet;

/**
 * Classe rédéfinissant (extends) la classe JTable pour l'adapter à nos objets Sujets
 * 
 * 
 * @author Groupe2B1
 * @version 3.0
 */

@SuppressWarnings("serial")
public class JTableSujet extends JTable {

	public AbstractTableModelSujet modele;
	
	/**
	 * Constructeur
	 * Resize la taille des colonnes de la JTable
	 * @param AbstractTableModelEtudiant pmodele
	 * 			le modèle correspondant aux sujets
	 */
	public JTableSujet(AbstractTableModelSujet pmodele) {
		super(pmodele);
		modele = pmodele;
		
		this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.getColumnModel().getColumn(0).setPreferredWidth(200);
		this.getColumnModel().getColumn(1).setPreferredWidth(200);
		this.getColumnModel().getColumn(2).setPreferredWidth(600);


	}

}
