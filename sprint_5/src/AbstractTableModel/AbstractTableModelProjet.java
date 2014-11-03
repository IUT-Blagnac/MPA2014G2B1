package AbstractTableModel;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Librairies.ChargerObjet;
import Objets.Projet;
import Objets.Sujet;
import Panels.JFramePerso;

/**
 * Classe qui étend AbstractTableModelPerso Permet d'adapter le composant JTable
 * à nos besoins, c'est à dire à l'objet Projet
 * 
 * @author Groupe2B1
 * @version 3.0
 */
@SuppressWarnings("serial")
public class AbstractTableModelProjet extends AbstractTableModel {

	private final String[] entete = { "groupe", "numSujet" };

	private File fileSelected;
	private JFramePerso mainFrame;

	public AbstractTableModelProjet(File pFile, JFramePerso pmainFrame)
			throws IOException {
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
		return mainFrame.getBaseDonnees().getListeProjets().size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		switch (col) {
		case 0:
			return ((Projet) mainFrame.getBaseDonnees().getListeProjets().get(row)).getGroupe().getID();
		case 1:
			Sujet suj = ((Projet) mainFrame.getBaseDonnees().getListeProjets().get(row)).getSujet();
			if (suj == null) {
				return "Aucun sujet associé";
			}
			return suj.getID();

		default:
			return null;
		}
	}

	public void addProjet(Projet proj) {
		
		//ajout dans la liste des projets
		mainFrame.getBaseDonnees().getListeProjets().add(proj);
		
		//ajout visuel
		fireTableRowsInserted(mainFrame.getBaseDonnees().getListeProjets().size() - 1, mainFrame.getBaseDonnees().getListeProjets().size() - 1);
	}

	public void removeProjet(int rowIndex) {
		
		Projet projetTemp =	(Projet) mainFrame.getBaseDonnees().getListeProjets().get(rowIndex);
		
		//remove du projet dans le sujet
		mainFrame.getBaseDonnees().removeProjetSujet(projetTemp);
		
		//remove du projet dans le groupe
		mainFrame.getBaseDonnees().removeProjetGroupe(projetTemp);

		
		//remove visuel
		fireTableRowsDeleted(rowIndex, rowIndex);
		
		//remove de la liste des sujets
		mainFrame.getBaseDonnees().getListeProjets().remove(rowIndex);
	}
	/**
	 * Fonction retournant la liste des données contenues dans la JTable
	 * Au format String et formatée pour écrire dans un CSV
	 * 
	 * @return String fullTexte
	 * 			la chaîne de caractères contenant les données de la JTable
	 */
	public String getTexte() {
		String fulltexte = "groupe;sujet" + "\n";
		for (int i = 0; i < mainFrame.getBaseDonnees().getListeProjets().size(); i++) {
			Projet temp = (Projet) mainFrame.getBaseDonnees().getListeProjets().get(i);
			fulltexte += temp.toCSV();
		}
		return fulltexte;
	}

}
