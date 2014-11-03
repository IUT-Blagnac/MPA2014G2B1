package Objets;

import Panels.Database;

/**
 * Classe objet des étudiants
 */

public class Etudiant extends Object{
	
	//private Groupe groupe;
	private String nom;
	private String prenom;
	Database DB;
	
	/**
	 * Constructeur des étudiants
	 * @param pNOM
	 * @param pPRENOM
	 * @param pDB
	 * 		la database
	 */
	public Etudiant(String pNOM, String pPRENOM,Database pDB) {
		nom = pNOM;
		prenom = pPRENOM;
		DB = pDB;
	}

	/**
	 * Permet de gérer l'affichage des objets de type Etudiant.
	 */
	public String toString() {
		String chaine;
		chaine = this.getNom()+" "+this.getPrenom();
		return chaine;
	}
	/*
	public Groupe getGroupe() {
		return this.groupe;
	}*/
	/*
	public String getStringGroupe() {
		return this.groupe.getID();
	}*/
	
	/**
	 * 
	 * @return le nom de l'étudiant
	 * 
	 */
	public String getNom() {
		return this.nom;
	}
	/**
	 * 
	 * @return le prénom de l'étudiant
	 */
	public String getPrenom() {
		return this.prenom;
	}
	
	/**
	 * 
	 * @return la chaine de caractères formatée pour écrire directement dans un CSV
	 */
	public String toCSV() {
		return DB.getGroupeEtudiant(this).getID()+";"+prenom+";"+nom+"\n";
	}
	
	
	
	
	
	

	
	
	

}
