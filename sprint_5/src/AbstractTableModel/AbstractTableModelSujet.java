package AbstractTableModel;

import java.io.File;
import java.io.IOException;
import javax.swing.table.AbstractTableModel;
import Objets.Projet;
import Objets.Sujet;
import Panels.JFramePerso;

/**
 * Classe qui étend AbstractTableModelPerso Permet d'adapter le composant JTable
 * à nos besoins, c'est à dire à l'objet Sujet
 * 
 * @author Groupe2B1
 * @version 3.0
 */
@SuppressWarnings("serial")
public class AbstractTableModelSujet extends AbstractTableModel{

	private final String[] entete = { "id", "nom","titre" };

	private JFramePerso mainFrame;
	private File fileSelected;

	public AbstractTableModelSujet(File pFile, JFramePerso pmainFrame) throws IOException {
		super();
		fileSelected = pFile;
		mainFrame = pmainFrame;
	}

	@Override
	public int getColumnCount() {
		return entete.length;
	}

	@Override
	public int getRowCount() {
		return mainFrame.getBaseDonnees().getListeSujets().size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		switch (col) {
		case 0:
			return ((Sujet) mainFrame.getBaseDonnees().getListeSujets().get(row)).getID();
		case 1:
			return ((Sujet) mainFrame.getBaseDonnees().getListeSujets().get(row)).getNom();
		case 2:
			return ((Sujet) mainFrame.getBaseDonnees().getListeSujets().get(row)).getTitre();
		default:
			return null;
		}
	}

	public void addSujet(Sujet sujet) {
		
		//ajout du sujet dans la liste des sujets
		mainFrame.getBaseDonnees().getListeSujets().add(sujet);
		
		//ajout visuel
		fireTableRowsInserted(mainFrame.getBaseDonnees().getListeSujets().size() - 1,
				mainFrame.getBaseDonnees().getListeSujets().size() - 1);
	}

	public void removeSujet(int rowIndex) {
		
		
		Sujet sujetTemp = (Sujet) mainFrame.getBaseDonnees().getListeSujets().get(rowIndex);
		

		//on retire le sujet de son projet
		for(int i = 0; i <mainFrame.getBaseDonnees().getListeProjets().size();i++) {
			if (( (Projet) mainFrame.getBaseDonnees().getListeProjets().get(i)).getSujet().equals(sujetTemp)) {
				((Projet) mainFrame.getBaseDonnees().getListeProjets().get(i)).removeSujet();
			}
		}
		
		//on retire le sujet de la liste des sujets
		mainFrame.getBaseDonnees().getListeSujets().remove(rowIndex);
		
		//suppression visuelle
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
		String fulltexte = "id;nom;titre" + "\n";
		for (int i = 0; i < mainFrame.getBaseDonnees().getListeSujets().size() ; i++) {
			Sujet temp = (Sujet) mainFrame.getBaseDonnees().getListeSujets().get(i);
			fulltexte += temp.toCSV();
		}
		return fulltexte;
	}

}
