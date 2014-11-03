package Panels;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Librairies.ChargerObjet;
import Librairies.CsvLib;
import Objets.Etudiant;
import Objets.Sujet;
import Objets.Projet;
import Objets.Groupe;

/**
 * 
 * Base de données (façon Java) de l'application
 *
 */
public class Database {

	List<Object> listeEtudiants = null;
	List<Object> listeIntervenants = null;
	List<Object> listeSujets = null;
	List<Object> listeProjets = null;
	List<Object> listeGroupes = null;
	List<Object> listeVoeux = null;

	File intervenants;
	File projets;
	File etudiants;
	File sujets;
	File voeux;

	/**
	 * Constructeur avec paramètres.
	 * 
	 * @param pIntervenants
	 * 		Le fichier contenant les intervenants.
	 * @param pProjets
	 * 		Le fichier comportant les projets.
	 * @param pEtudiants
	 * 		Le fichier comprotant les étudiants.
	 * @param pSujets
	 * 		Le fichier contenant les sujets.
	 */
	public Database(File pIntervenants, File pProjets, File pEtudiants,
			File pSujets) {

		intervenants = pIntervenants;
		projets = pProjets;
		etudiants = pEtudiants;
		sujets = pSujets;

		// Création des groupes + ajout dans une liste de groupe
		creerGroupe();

		// Création des étudiants
		creerEtudiants();

		// Ajout des étudiants dans les groupes
		ajoutEtudiants();

		// Construction de la liste des intervenants(sans lien avec projet)
		creerIntervenants();

		// Construction de la liste des sujets (sans lien avec les projets)
		creerSujets();

		// Pré-construction de la liste des projets, puis construction
		creerProjets();

		// Ajout du lien projet-sujet dans les sujets et projet-sujet dans les
		// groupes
		addLinks();

		// Création des voeux contenant un groupe et un sujet
		creerVoeux();

		// Création d'Encadrer qui fait le lien entre intervenants et projets

	}
	/**
	 * Fonction permmettant de connaitre le groupe auquel appartient un étudiant.
	 * 
	 * @param etu
	 * 		L'étudiant dont on souhaite connaitre le groupe.
	 * @return Le groupe auquel appartient l'étudiant.
	 * 
	 */
	public Groupe getGroupeEtudiant(Etudiant etu) {
		Groupe groupe = null;
		for (int i = 0; i < listeGroupes.size(); i++) {
			Groupe tempGroupe = (Groupe) listeGroupes.get(i);
			for (int j = 0; j < tempGroupe.getListeEtudiants().size(); j++) {
				Etudiant tempEtudiant = tempGroupe.getListeEtudiants().get(j);
				if (tempEtudiant.equals(etu)) {
					return groupe = ((Groupe) listeGroupes.get(i));
				}

			}
		}
		return groupe;
	}

	/**
	 * Permet d'obtenir un groupe correspondant au String lettre
	 * 
	 * @param lettre
	 *            La lettre définissant le groupe souhaité (String).
	 * @return Le groupe ayant pour identifiant la lettre donnée en
	 *         paramètre(Groupe).
	 * 
	 */
	public Groupe getGroupe(String lettre) {

		for (int i = 0; i < listeGroupes.size(); i++) {
			if (((Groupe) listeGroupes.get(i)).getID().equals(lettre)) {
				return (Groupe) listeGroupes.get(i);
			}
		}

		return null; // si le groupe n'existe pas

	}

	public Sujet getSujet(String numSujet) {
		for (int i = 0; i < listeSujets.size(); i++) {
			if (((Sujet) listeSujets.get(i)).getID().equals(numSujet)) {
				return (Sujet) listeSujets.get(i);
			}
		}
		return (Sujet) listeSujets.get(0); // n'y arrive jamais
	}

	/**
	 * Permet de creer la liste des groupes (sans étudiants).
	 * 
	 */
	public void creerGroupe() {

		listeGroupes = new ArrayList<Object>();
		String tabLettre[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
				"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
				"V", "X", "Y", "Z" };

		List<String> countLignes = null;
		int nbProjets = 0;
		try {
			countLignes = CsvLib.readFile(projets);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		nbProjets = countLignes.size() - 1; // car l'entête n'est pas un groupe

		int z = 0;
		for (String lettre : tabLettre) {
			// On créé exactement le même nombre de groupes qu'il y en a de
			// recensé dans le csv Projet
			if (z < nbProjets) {
				listeGroupes.add(new Groupe(lettre));
				z++;
			}
		}
	}

	/**
	 * Permet de créer la liste des étudiants.
	 * 
	 */
	public void creerEtudiants() {
		try {
			listeEtudiants = ChargerObjet.creerObjet("Etudiant", etudiants,
					listeGroupes, listeSujets, listeProjets, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Permet d'ajouter les étudiants à leur groupe respectifs Exemple : si le
	 * groupe de l'étudiant est A, ajout de l'étudiant dans le groupe A.
	 * 
	 */
	public void ajoutEtudiants() {
		listeGroupes = ChargerObjet.addEtudiantsInGroupe(listeGroupes,
				etudiants, listeEtudiants);
	}

	/**
	 * Permet de creer la liste des intervenants.
	 */
	public void creerIntervenants() {
		try {
			listeIntervenants = ChargerObjet
					.creerObjet("Intervenant", intervenants, listeGroupes,
							listeSujets, listeProjets, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Permet de creer la liste des projets avec les sujets et les groupes Cette
	 * version ne fait pas le lien avec les intervenants.
	 * 
	 */
	public void creerProjets() {
		listeProjets = new ArrayList<Object>();

		List<String> countLignes = null;
		int nbProjets = 0;
		try {
			countLignes = CsvLib.readFile(projets);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		nbProjets = countLignes.size() - 1; // car l'entête n'est pas un groupe

		// On créé exactement le même nombre de projet qu'il y a de projets
		// recensé dans le csv
		for (int i = 0; i < nbProjets; i++) {
			listeProjets.add(new Projet());
		}

		// Construction de la liste des projets
		try {
			listeProjets = ChargerObjet.creerObjet("Projet", projets,
					listeGroupes, listeSujets, listeProjets, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Permet de creer la liste des sujets.
	 * 
	 */
	public void creerSujets() {
		try {
			listeSujets = ChargerObjet.creerObjet("Sujet", sujets,
					listeGroupes, listeSujets, listeProjets, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Permet d'ajouter les liens dans les classes Groupe et Sujet, suite à la
	 * création des projets Méthode générale : on regarde quel groupe est
	 * associé au projet N, puis ajoute ce projet au groupe correspondant Idem
	 * pour les sujets.
	 * 
	 */
	public void addLinks() {

		// parcours de la liste des projets
		for (int i = 0; i < listeProjets.size(); i++) {
			Projet temp = (Projet) listeProjets.get(i);

			// parcours de la liste des groupes
			// si la lettre du groupe j correspond à la lettre du groupe i,
			// alors on ajoute le projet dans le groupe j
			for (int j = 0; j < listeGroupes.size(); j++) {
				if (((Groupe) listeGroupes.get(j)).getID().equals(
						temp.getGroupe().getID())) {
					((Groupe) listeGroupes.get(j))
							.addProjet((Projet) listeProjets.get(i));
				}
			}

			// parcours de la liste des sujets
			// si l'id du sujet contenant dans le projet i correspond à
			// l'identifiant du sujet j,
			// alors on ajoute dans le sujet j le projet i
			for (int j = 0; j < listeSujets.size(); j++) {
				if (((Sujet) listeSujets.get(j)).getID().equals(
						temp.getSujet().getID())) {
					((Sujet) listeSujets.get(j))
							.addProjet((Projet) listeProjets.get(i));
				}
			}

		}

	}
	/**
	 * Permet d'jouter un étudiant dans un groupe.
	 * 
	 * @param etu
	 * 		L'étudiant à ajouter.
	 * @param lettreGroupe
	 * 		La lettre du groupe dans lequel on souhaite ajouter l'étudiant.
	 * 
	 */
	public void addEtudiantGroupe(Etudiant etu, String lettreGroupe) {

		// On regarde si le groupe existe
		Groupe groupeEtu = getGroupe(lettreGroupe);

		// si = null, alors on le créé
		// ATTENTION, la lettre du groupe doit être la plus proche de la
		// dernière utilisée
		// ex : si le dernier groupe = Q, le groupe doit être R
		if (groupeEtu == null) {
			groupeEtu = new Groupe(lettreGroupe);
			listeGroupes.add(groupeEtu);
		}

		// et dans tous les cas on finit par l'ajoute dans son groupe
		// parcours de la liste des groupes, si le groupe i est = au groupe de
		// l'étudiant, alors on ajoute l'étudiant au groupe
		for (int i = 0; i < listeGroupes.size(); i++) {
			if (((Groupe) listeGroupes.get(i)).getID().equals(lettreGroupe)) {
				((Groupe) listeGroupes.get(i)).addEtudiant(etu);
			}
		}

	}

	/**
	 * Permet de soustraire un étudiant à son groupe.
	 * 
	 * @param etu
	 * 		L'étudiant à soustraire du groupe.
	 * 
	 */
	public void removeEtudiantGroupe(Etudiant etu) {

		// parcours de la liste des groupes
		for (int i = 0; i < listeGroupes.size(); i++) {
			// parcours de la liste des étudiants du groupe
			for (int j = 0; j < ((Groupe) listeGroupes.get(i))
					.getListeEtudiants().size(); j++) {
				// si l'étudiant j est identique à celui qu'on veut supprimer,
				// on le fait
				if (((Groupe) listeGroupes.get(i)).getListeEtudiants().get(j)
						.equals(etu)) {
					((Groupe) listeGroupes.get(i)).getListeEtudiants()
							.remove(j);

				}
			}
		}

	}
	
	/**
	 * Permet de retirer un projet à tous les groupes.
	 * 
	 * @param proj
	 * 		Le projet à retirer des groupes.
	 * 
	 */
	public void removeProjetGroupe (Projet proj) {
		//parcours de la liste des groupes, quand le projet est trouvé dans un groupe on l'enlève
		for (int i = 0; i < listeGroupes.size(); i++) {
			if (((Groupe) listeGroupes.get(i)).getProjet().equals(proj)) {
				((Groupe) listeGroupes.get(i)).removeProjet();
			}
		}
	}
	
	/**
	 * Permet de retirer un sujet dans tous les projets.
	 * 
	 * @param proj
	 * 		Le projet à retirer des projets.
	 * 
	 */
	public void removeProjetSujet(Projet proj) {
		//parcours de la liste des sujets, quand le projet est trouvé dans un sujet on l'enlève
				for (int i = 0; i < listeSujets.size(); i++) {
					if (((Sujet) listeSujets.get(i)).getProjet().equals(proj)) {
						((Sujet) listeSujets.get(i)).removeProjet();
					}
				}
	}
	
	/**
	 * Constructeur d'un voeux
	 */
	public void creerVoeux() {
			
	}

	/**
	 * 
	 * @return La liste des étudiants.
	 */
	public List<Object> getListeEtudiants() {
		return listeEtudiants;
	}

	/**
	 * 
	 * @return La liste des sujets.
	 */
	public List<Object> getListeSujets() {
		return listeSujets;
	}

	/**
	 * 
	 * @return La liste des projets.
	 */
	public List<Object> getListeProjets() {
		return listeProjets;
	}

	/**
	 * 
	 * @return La liste des intervenants.
	 */
	public List<Object> getListeIntervenants() {
		return listeIntervenants;
	}

	/**
	 * 
	 * @return La liste des groupes.
	 */
	public List<Object> getListeGroupes() {
		return listeGroupes;
	}

	public File getFileEtu() {
		return etudiants;
	}

	public File getFileSuj() {
		return sujets;
	}

	public File getFileInterv() {
		return intervenants;
	}

	public File getFileProj() {
		return projets;
	}

	public void setListeEtudiants(List<Object> pListeEtudiants) {
		listeEtudiants = pListeEtudiants;
	}

	public void setListeProjets(List<Object> pListeProjets) {
		listeProjets = pListeProjets;
	}

	public void setListeSujets(List<Object> pListeSujets) {
		listeSujets = pListeSujets;
	}

	public void setListeIntervenants(List<Object> pListeIntervenants) {
		listeEtudiants = pListeIntervenants;
	}

}
