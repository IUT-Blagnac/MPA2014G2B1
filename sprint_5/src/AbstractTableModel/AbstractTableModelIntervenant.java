package AbstractTableModel;

import java.io.File;
import java.io.IOException;


import javax.swing.table.AbstractTableModel;


import Objets.Intervenant;
import Panels.JFramePerso;

/**
 * Classe qui étend AbstractTableModelPerso Permet d'adapter le composant JTable
 * à nos besoins, c'est à dire à l'objet Intervenant
 * 
 * @author Groupe2B1
 * @version 3.0
 */
@SuppressWarnings("serial")
public class AbstractTableModelIntervenant extends AbstractTableModel {

	private final String[] entete = { "prénom", "nom" };
	private File fileSelected;
	private JFramePerso mainFrame;

	public AbstractTableModelIntervenant(File pFile, JFramePerso pmainFrame) throws IOException {
		super();
		fileSelected = pFile;
		mainFrame = pmainFrame;

	}

	/**
	 * Fonction qui permet de retourner la longueur de l'entête
	 * 
	 * @return la longueur de l'entête
	 */
	@Override
	public int getColumnCount() {
		return entete.length;
	}

	

	/**
	 * Fonction qui permet de retourner la longueur de la liste des étudiants
	 * 
	 * @return la longueur de la liste des étudiants
	 */
	@Override
	public int getRowCount() {
		return mainFrame.getBaseDonnees().getListeIntervenants().size();
	}

	
	/**
	 * Fonction utilisée automatiquement par la JTable
	 * Lui permet de savoir quel est le texte à mettre dans quelle case
	 * 
	 * @param int row
	 * 			La ligne 
	 * @param int col
	 * 			La colonne
	 */
	@Override
	public Object getValueAt(int row, int col) {
		switch (col) {
		case 0:
			return ((Intervenant) mainFrame.getBaseDonnees().getListeIntervenants().get(row)).getPrenom();
		case 1:
			return ((Intervenant) mainFrame.getBaseDonnees().getListeIntervenants().get(row)).getNom();
		default:
			return null;
		}
	}

	public void addIntervenant(Intervenant interv) {
		mainFrame.getBaseDonnees().getListeIntervenants().add(interv);
		fireTableRowsInserted(mainFrame.getBaseDonnees().getListeIntervenants().size() - 1,
				mainFrame.getBaseDonnees().getListeIntervenants().size() - 1);
	}

	public void removeIntervenant(int rowIndex) {
		mainFrame.getBaseDonnees().getListeIntervenants().remove(rowIndex);

		fireTableRowsDeleted(rowIndex, rowIndex);
	}
	/**
	 * Fonction retournant la liste des données contenues dans la JTable
	 * Au format String et formatée pour écrire dans un CSV
	 * 
	 * @return String fullTexte
	 * 			la chaîne de caractères contenant les données de la JTable
	 */
	public String getTexte() {
		String fulltexte = "prenom;nom" + "\n";
		for (int i = 0; i < mainFrame.getBaseDonnees().getListeIntervenants().size(); i++) {
			Intervenant temp = (Intervenant) mainFrame.getBaseDonnees().getListeIntervenants().get(i);
			fulltexte += temp.toCSV();
		}
		return fulltexte;
	}

}
