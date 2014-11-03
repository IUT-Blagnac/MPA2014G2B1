package Objets;
/**
 * Classe objet des sujets
 */
public class Sujet {

	private String id;
	private String nom;
	private String titre;
	private Projet projet;
	
	/**
	 * Constructeur paramètré.
	 * 
	 * @param pID
	 * 		L'ID du sujet.
	 * @param pNOM
	 * 		Le nom.
	 * @param pTITRE
	 * 		Le titre du sujet.
	 * @param pProjet
	 * 		Le projet auquel est rattaché le sujet.
	 */
	public Sujet(String pID, String pNOM, String pTITRE, Projet pProjet) {
		id = pID;
		nom = pNOM;
		titre = pTITRE;
		projet = pProjet;
	}
	/**
	 * Constructeur paramètré (sujet non rattaché à un projet).
	 * 
	 * @param pID
	 * 		L'ID du sujet.
	 * @param pNOM
	 * 		Le nom.
	 * @param pTITRE
	 * 		Le titre du sujet.
	 */
	public Sujet(String pID, String pNOM, String pTITRE) {
		id = pID;
		nom = pNOM;
		titre = pTITRE;
	}
	
	/**
	 * Permet d'attribuer le sujet à un projet.
	 * 
	 * @param pProjet
	 * 		Le projet auquel on souhaite rattacher le sujet.
	 */
	public void setProjet(Projet pProjet) {
		projet = pProjet;
	}
	
	/**
	 * Supprime le projet auquel est rattaché le sujet.
	 */
	public void removeProjet() {
		projet=null;
	}
	
	/**
	 * Permet de gérer l'affichage des objets de type Sujet.
	 */
	public String toString() {
		String chaine;
		chaine = "Numéro du sujet : "+this.getID()+"\nNom du sujet : "+this.getNom()+"\nDescriptif : "+this.getTitre();
		return chaine;
	}
	
	/**
	 * 
	 * @return L'ID du sujet.
	 */
	public String getID() {
		return this.id;
	}
	
	/**
	 * 
	 * @return Le nom du sujet.
	 */
	public String getNom() {
		return this.nom;
	}
	
	/**
	 * 
	 * @return Le titre du sujet.
	 */
	public String getTitre() {
		return this.titre;
	}
	
	/**
	 * 
	 * @return Le projet auquel est rattaché le sujet.
	 */
	public Projet getProjet() {
		return this.projet;
	}
	
	/**
	 * Permet d'ajouter le projet auquel est rattaché le sujet.
	 * @param pProjet
	 */
	public void addProjet(Projet pProjet) {
		this.projet = pProjet;
	}
	
	public String toCSV() {
		return id+";"+nom+";"+titre+"\n";
	}
	
}
