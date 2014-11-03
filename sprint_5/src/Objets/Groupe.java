package Objets;

import java.util.ArrayList;
import java.util.List;
/**
 * Classe objet des groupes
 */
public class Groupe {

	private List<Etudiant> listeEtudiant;
	private String libelle;
	private Projet projet = new Projet();

	/**
	 * Constructeur paramètré.
	 * 
	 * @param pLibelle
	 * 		Libellé du groupe.
	 * @param listeEtu
	 * 		Liste des étudiants appartenant au groupe.
	 */
	public Groupe(String pLibelle, List<Etudiant> listeEtu) {
		listeEtudiant = listeEtu;
		libelle = pLibelle;

	}

	/**
	 * Constructeur paramètré.
	 * 
	 * @param pLibelle
	 * 		Libellé du groupe.
	 */
	public Groupe(String pLibelle) {
		libelle = pLibelle;
		listeEtudiant = new ArrayList<Etudiant>();

	}

	/**
	 * 
	 * @return Le projet associé au groupe.
	 */
	public Projet getProjet() {
		return projet;
	}

	/**
	 * Retire le projet ssocié au groupe.
	 */
	public void removeProjet() {
		projet = null;
	}

	/**
	 * Ajoute un étudiant au groupe.
	 * Il est ajouté à listeEtudiant.
	 * @param etu
	 * 		L'étudiant à ajouter.
	 */
	public void addEtudiant(Etudiant etu) {
		this.listeEtudiant.add(etu);
	}

	/**
	 * 
	 * @return Le libellé du groupe.
	 */
	public String getID() {
		return this.libelle;
	}

	/**
	 * 
	 * @return La liste des étudiants du groupe (listeEtudiant).
	 */
	public List<Etudiant> getListeEtudiants() {
		return this.listeEtudiant;
	}

	/**
	 * Ajoute un projet au groupe.
	 * 
	 * @param pProjet
	 * 		Le projet à ajouter.
	 */
	public void addProjet(Projet pProjet) {
		this.projet = pProjet;
	}

	/**
	 * Permet de gérer l'affichage des objets de type Groupe.
	 */
	public String toString() {

		String chaine = "Groupe : " + this.libelle + "\n";

		chaine += "Etudiants : \n";
		for (int i = 0; i < listeEtudiant.size(); i++) {
			chaine += (listeEtudiant.get(i).toString() + "\n");
		}
		chaine += this.projet.toStringWithoutGroupe();

		return chaine;
	}

}
