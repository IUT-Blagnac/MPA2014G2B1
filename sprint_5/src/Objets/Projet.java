package Objets;

import java.util.ArrayList;
import java.util.List;
/**
 * Classe objet des projets
 */
public class Projet {

	private Groupe groupe;
	private Sujet sujet;
	private List<Object> listeIntervenants = new ArrayList<Object>(); 
	
	/**
	 * Constructeur paramètré.
	 * 
	 * @param pGROUPE
	 * 		Le groupe auquel le projet est affecté.
	 * @param psujet
	 * 		Le sujet rattaché au projet.
	 * @param list
	 * 		Le liste des intervenant pour ce projet.
	 */
	public Projet(Groupe pGROUPE, Sujet psujet,
			List<Object> list) {
		groupe = pGROUPE;
		sujet = psujet;
		listeIntervenants = list;
	}

	/**
	 * Constructeur par défaut.
	 */
	public Projet() {
		groupe = null;
		sujet = null;
	}
	
	public String toStringOnlyGroupe() {
		return groupe.getID();
	}

	/**
	 * Permet de gérer l'affichage des objets de type Projet.
	 */
	public String toString() {
		String chaine;
		chaine = this.getGroupe() + " " + this.getSujet();
		return chaine;
	}

	public String toStringWithoutGroupe() {
		String chaine = sujet.toString() + "\n" + "Intervenants : \n";

		for (int i = 0; i < listeIntervenants.size(); i++) {
			chaine += listeIntervenants.get(i).toString() + "\n";
		}
		return chaine;
	}

	/**
	 * 
	 * @return Le groupe responsable de ce projet.
	 */
	public Groupe getGroupe() {
		return this.groupe;
	}

	/**
	 * 
	 * @return Le sujet rattaché à ce projet.
	 */
	public Sujet getSujet() {
		return this.sujet;
	}

	public String toCSV() {
		return groupe + ";" + sujet + "\n";
	}

	/**
	 * Permet de modifier le groupe responsable de ce projet.
	 * 
	 * @param pGroupe
	 * 		Le groupe qui s'occupe du projet.
	 */
	public void setGroupe(Groupe pGroupe) {
		this.groupe = pGroupe;
	}

	/**
	 * Permet de modifier le sujet rattaché au projet.
	 * @param pSujet
	 */
	public void setSujet(Sujet pSujet) {
		this.sujet = pSujet;
	}
	
	/**
	 * Permet de supprimer le sujet rattaché au projet.
	 */
	public void removeSujet() {
		sujet = null;
	}

	

}
