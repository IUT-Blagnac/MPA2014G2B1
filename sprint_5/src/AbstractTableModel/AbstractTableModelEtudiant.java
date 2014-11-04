package AbstractTableModel;
import java.io.File;
import java.io.IOException;

import javax.swing.table.AbstractTableModel;

import Objets.Etudiant;
import Panels.JFramePerso;


/**
 * Classe qui étend AbstractTableModelPerso Permet d'adapter le composant JTable
 * à nos besoins, c'est à dire à l'objet Etudiant
 * 
 * @author Groupe2B1
 * @version 3.0
 */
@SuppressWarnings("serial")
public class AbstractTableModelEtudiant extends AbstractTableModel{

	private final String[] entete = { "groupe", "nom", "prenom" };

	@SuppressWarnings("unused")
	private File fileSelected;
	private JFramePerso mainFrame;
	
	
	/**
	 * Constructeur
	 * 
	 * @param File pFile, 
	 *  		fichier CSV source des étudiants
	 *  @param JFramePerso pmainFrame
	 *  		Frame principale de l'application
	 */ 
	public AbstractTableModelEtudiant(File pFile, JFramePerso pmainFrame) throws IOException {
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
		return mainFrame.getBaseDonnees().getListeEtudiants().size();
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
			Etudiant temp = (Etudiant) mainFrame.getBaseDonnees().getListeEtudiants().get(row);
			return  mainFrame.getBaseDonnees().getGroupeEtudiant(temp).getID(); 
		case 1:
			return ((Etudiant) mainFrame.getBaseDonnees().getListeEtudiants().get(row)).getNom();
		case 2:
			return ((Etudiant) mainFrame.getBaseDonnees().getListeEtudiants().get(row)).getPrenom();
		default:
			return null;
		}
	}

	
	/**
	 * Fonction qui permet d'ajouter un étudiant à un groupe
	 * 
	 * @param Etudiant etu
	 * 			L'étudiant à ajouter
	 * @param String lettreGroupe
	 * 			La lettre du groupe à ajouter
	 */
	public void addEtudiant(Etudiant etu,String lettreGroupe) {
		
		//Ajout de l'étudiant dans la liste des étudiants
		mainFrame.getBaseDonnees().getListeEtudiants().add(etu);
		//Ajout de l'étudiant dans le groupe
		//ajout de l'étudiant dans le groupe
		mainFrame.getBaseDonnees().addEtudiantGroupe(etu, lettreGroupe);
		//ajout visuel
		fireTableRowsInserted(mainFrame.getBaseDonnees().getListeEtudiants().size() - 1,
				mainFrame.getBaseDonnees().getListeEtudiants().size() - 1);
	}

	/**
	 * Fonction qui permet de supprimer un étudiant
	 * 
	 * @param int rowIndex
	 * 			l'indice (dans la liste des étudiants) de l'étudiant à retirer
	 */
	public void removeEtudiant(int rowIndex) {
		
		// on récupère l'étudiant
		Etudiant etu = 	(Etudiant) mainFrame.getBaseDonnees().getListeEtudiants().get(rowIndex);
		
		//suppression de/des étudiant de son groupe
		mainFrame.getBaseDonnees().removeEtudiantGroupe(etu);
		
		// suppression de l'étudiant de la liste des étudiants
		mainFrame.getBaseDonnees().getListeEtudiants().remove(rowIndex);

		//delete visuel
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
		String fulltexte = "groupe;nom;prenom" + "\n";
		for (int i = 0; i < mainFrame.getBaseDonnees().getListeEtudiants().size() ; i++) {
			Etudiant temp = (Etudiant) mainFrame.getBaseDonnees().getListeEtudiants().get(i);
			fulltexte += temp.toCSV();
		}
		return fulltexte;
	}

}
