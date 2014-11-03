package Objets;

import java.util.List;
/**
 * Classe objet des intervenants
 */
public class Intervenant {

	
	private String nom;
	private String prenom;
	private List<Projet> listeProjets;
	
	/**
	 * Constructeur paramètré.
	 * 
	 * @param pNOM
	 * 		Le nom de l'intervenant.
	 * @param pPRENOM
	 * 		Le prénom de l'intervenant.
	 */
	public Intervenant(String pNOM, String pPRENOM) {
		nom = pNOM;
		prenom = pPRENOM;
		listeProjets = null;
	}
	
	/**
	 * Permet de gérer l'affichage des objets de type Intervenant.
	 */
	public String toString() {
		String chaine;
		chaine = this.getNom()+" "+this.getPrenom();
		return chaine;
	}
	
	/**
	 * 
	 * @return Le nom de l'intervenant.
	 */
	public String getNom() {
		return this.nom;
	}
	
	/**
	 * 
	 * @return Le prénom de l'intervenant.
	 */
	public String getPrenom() {
		return this.prenom;
	}
	
	/**
	 * 
	 * @return La liste des projets auxquels un intervenant est rattaché.
	 */
	public List<Projet> getListeProjet() {
		return this.listeProjets;
	}
	
	/**
	 * Ajoute un projet à la liste des projets rattachés à l'intervenant.
	 * 
	 * @param projet
	 * 		Le projet à ajouter à la liste des projets (listeProjets).
	 */
	public void addProjet(Projet projet) {
		listeProjets.add(projet);
	}
	
	public String toCSV() {
		return prenom+";"+nom+"\n";
	}
	

}
