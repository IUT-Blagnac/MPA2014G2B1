package Librairies;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Objets.Etudiant;
import Objets.Groupe;
import Objets.Intervenant;
import Objets.Projet;
import Objets.Sujet;
import Panels.Database;

/**
 * Librairie permettant de créer les objets
 * 
 * @author Groupe2B1
 * @version 3.0
 */
public class ChargerObjet {


	  /**
	   * Permet de créer les objets Sujet, Projet, Etudiant, Intervenant à partir de
	   * leur csv respectifs. Permet aussi de créer les objets Groupe en partant du csv
	   * des étudiants.
	   * 
	   * @param typeObjet
	   * 		String de l'objet à charger
	   * 
	   * @param fileSelected
	   * 		Le fichier CSV source à charger
	   * 
	   * @param listeGroupes
	   * 		la liste des groupes
	   * 
	   * @param listeSujets
	   * 		la liste des sujets
	   * 
	   * @param listeProjets
	   * 		la liste des projets
	   * 
	   * @param DB
	   * 		la database
	   * 
	   * @return liste
	   * 			la liste des objets créé
	   * @throws IOException
	   */
	public static ArrayList<Object> creerObjet(String typeObjet,
			File fileSelected, List<Object> listeGroupes,
			List<Object> listeSujets, List<Object> listeProjets, Database DB)
			throws IOException {
		ArrayList<Object> liste = new ArrayList<Object>();
		List<String> texte = new ArrayList<String>();
		texte = CsvLib.readFile(fileSelected);

		String[] attributs;
		String tempLigne = "";

		switch (typeObjet) {
		case "Etudiant":
			for (int i = 1; i < texte.size(); i++) {
				tempLigne = texte.get(i);
				attributs = tempLigne.split(";");
				switch (attributs[0]) {
				case "A":
					liste.add(new Etudiant(attributs[1], attributs[2], DB));

					break;
				case "B":
					liste.add(new Etudiant(attributs[1], attributs[2], DB));
					break;
				case "C":
					liste.add(new Etudiant(attributs[1], attributs[2], DB));
					break;
				case "D":
					liste.add(new Etudiant(attributs[1], attributs[2], DB));
					break;
				case "E":
					liste.add(new Etudiant(attributs[1], attributs[2], DB));
					break;
				case "F":
					liste.add(new Etudiant(attributs[1], attributs[2], DB));
					break;
				case "G":
					liste.add(new Etudiant(attributs[1], attributs[2], DB));
					break;
				case "H":
					liste.add(new Etudiant(attributs[1], attributs[2], DB));
					break;
				case "I":
					liste.add(new Etudiant(attributs[1], attributs[2], DB));
					break;
				case "J":
					liste.add(new Etudiant(attributs[1], attributs[2], DB));
					break;
				case "K":
					liste.add(new Etudiant(attributs[1], attributs[2], DB));
					break;
				case "L":
					liste.add(new Etudiant(attributs[1], attributs[2], DB));
					break;
				case "M":
					liste.add(new Etudiant(attributs[1], attributs[2], DB));
					break;
				case "N":
					liste.add(new Etudiant(attributs[1], attributs[2], DB));
					break;
				case "O":
					liste.add(new Etudiant(attributs[1], attributs[2], DB));
					break;
				case "P":
					liste.add(new Etudiant(attributs[1], attributs[2], DB));
					break;
				case "Q":
					liste.add(new Etudiant(attributs[1], attributs[2], DB));
					break;
				case "R":
					liste.add(new Etudiant(attributs[1], attributs[2], DB));
					break;
				case "S":
					liste.add(new Etudiant(attributs[1], attributs[2], DB));
					break;
				case "T":
					liste.add(new Etudiant(attributs[1], attributs[2], DB));
					break;
				case "U":
					liste.add(new Etudiant(attributs[1], attributs[2], DB));
					break;
				case "V":
					liste.add(new Etudiant(attributs[1], attributs[2], DB));
					break;
				case "W":
					liste.add(new Etudiant(attributs[1], attributs[2], DB));
					break;
				case "X":
					liste.add(new Etudiant(attributs[1], attributs[2], DB));
					break;
				case "Y":
					liste.add(new Etudiant(attributs[1], attributs[2], DB));
					break;
				case "Z":
					liste.add(new Etudiant(attributs[1], attributs[2], DB));
					break;
				default:// never
					break;
				}
			}
			break;
		case "Intervenant":
			for (int i = 1; i < texte.size(); i++) {
				tempLigne = texte.get(i);
				attributs = tempLigne.split(";");
				liste.add(new Intervenant(attributs[0], attributs[1]));
			}
			break;
		case "Projet":
			for (int i = 1; i < texte.size(); i++) {
				tempLigne = texte.get(i);
				attributs = tempLigne.split(";");
				int patateChaude = -1;

				switch (attributs[0]) {
				case "A":
					((Projet) listeProjets.get(0))
							.setGroupe((Groupe) listeGroupes.get(0));
					patateChaude = 0;
					break;
				case "B":
					((Projet) listeProjets.get(1))
							.setGroupe((Groupe) listeGroupes.get(1));
					patateChaude = 1;

					break;
				case "C":
					((Projet) listeProjets.get(2))
							.setGroupe((Groupe) listeGroupes.get(2));
					patateChaude = 2;

					break;
				case "D":
					((Projet) listeProjets.get(3))
							.setGroupe((Groupe) listeGroupes.get(3));
					patateChaude = 3;

					break;
				case "E":
					((Projet) listeProjets.get(4))
							.setGroupe((Groupe) listeGroupes.get(4));
					patateChaude = 4;

					break;
				case "F":
					((Projet) listeProjets.get(5))
							.setGroupe((Groupe) listeGroupes.get(5));
					patateChaude = 5;

					break;
				case "G":
					((Projet) listeProjets.get(6))
							.setGroupe((Groupe) listeGroupes.get(6));
					patateChaude = 6;

					break;
				case "H":
					((Projet) listeProjets.get(7))
							.setGroupe((Groupe) listeGroupes.get(7));
					patateChaude = 7;

					break;
				case "I":
					((Projet) listeProjets.get(8))
							.setGroupe((Groupe) listeGroupes.get(8));
					patateChaude = 8;

					break;
				case "J":
					((Projet) listeProjets.get(9))
							.setGroupe((Groupe) listeGroupes.get(9));
					patateChaude = 9;

					break;
				case "K":
					((Projet) listeProjets.get(10))
							.setGroupe((Groupe) listeGroupes.get(10));
					patateChaude = 10;

					break;
				case "L":
					((Projet) listeProjets.get(11))
							.setGroupe((Groupe) listeGroupes.get(11));
					patateChaude = 11;

					break;
				case "M":
					((Projet) listeProjets.get(12))
							.setGroupe((Groupe) listeGroupes.get(12));
					patateChaude = 12;

					break;
				case "N":
					((Projet) listeProjets.get(13))
							.setGroupe((Groupe) listeGroupes.get(13));
					patateChaude = 13;

					break;
				case "O":
					((Projet) listeProjets.get(14))
							.setGroupe((Groupe) listeGroupes.get(14));
					patateChaude = 14;

					break;
				case "P":
					((Projet) listeProjets.get(15))
							.setGroupe((Groupe) listeGroupes.get(15));
					patateChaude = 15;

					break;
				case "Q":
					((Projet) listeProjets.get(16))
							.setGroupe((Groupe) listeGroupes.get(16));
					patateChaude = 16;

					break;
				case "R":
					((Projet) listeProjets.get(17))
							.setGroupe((Groupe) listeGroupes.get(17));
					patateChaude = 17;

					break;
				case "S":
					((Projet) listeProjets.get(18))
							.setGroupe((Groupe) listeGroupes.get(18));
					patateChaude = 18;

					break;
				case "T":
					((Projet) listeProjets.get(19))
							.setGroupe((Groupe) listeGroupes.get(19));
					patateChaude = 19;

					break;
				case "U":
					((Projet) listeProjets.get(20))
							.setGroupe((Groupe) listeGroupes.get(20));
					patateChaude = 20;

					break;
				case "V":
					((Projet) listeProjets.get(21))
							.setGroupe((Groupe) listeGroupes.get(21));
					patateChaude = 21;

					break;
				case "W":
					((Projet) listeProjets.get(22))
							.setGroupe((Groupe) listeGroupes.get(22));
					patateChaude = 22;

					break;
				case "X":
					((Projet) listeProjets.get(23))
							.setGroupe((Groupe) listeGroupes.get(23));
					patateChaude = 23;

					break;
				case "Y":
					((Projet) listeProjets.get(24))
							.setGroupe((Groupe) listeGroupes.get(24));
					patateChaude = 24;

					break;
				case "Z":
					((Projet) listeProjets.get(25))
							.setGroupe((Groupe) listeGroupes.get(25));
					patateChaude = 25;

					break;

				default:
					break;
				}

				switch (attributs[1]) {
				case "1":
					((Projet) listeProjets.get(patateChaude))
							.setSujet((Sujet) listeSujets.get(0));
					break;
				case "2":
					((Projet) listeProjets.get(patateChaude))
							.setSujet((Sujet) listeSujets.get(1));
					break;

				case "3":
					((Projet) listeProjets.get(patateChaude))
							.setSujet((Sujet) listeSujets.get(2));
					break;
				case "4":
					((Projet) listeProjets.get(patateChaude))
							.setSujet((Sujet) listeSujets.get(3));
					break;
				case "5":
					((Projet) listeProjets.get(patateChaude))
							.setSujet((Sujet) listeSujets.get(4));
					break;
				case "6":
					((Projet) listeProjets.get(patateChaude))
							.setSujet((Sujet) listeSujets.get(5));
					break;
				case "7":
					((Projet) listeProjets.get(patateChaude))
							.setSujet((Sujet) listeSujets.get(6));
					break;
				case "8":
					((Projet) listeProjets.get(patateChaude))
							.setSujet((Sujet) listeSujets.get(7));
					break;
				case "9":
					((Projet) listeProjets.get(patateChaude))
							.setSujet((Sujet) listeSujets.get(8));
					break;
				case "10":
					((Projet) listeProjets.get(patateChaude))
							.setSujet((Sujet) listeSujets.get(9));
					break;
				case "11":
					((Projet) listeProjets.get(patateChaude))
							.setSujet((Sujet) listeSujets.get(10));
					break;
				case "12":
					((Projet) listeProjets.get(patateChaude))
							.setSujet((Sujet) listeSujets.get(11));
					break;
				case "13":
					((Projet) listeProjets.get(patateChaude))
							.setSujet((Sujet) listeSujets.get(12));
					break;
				case "14":
					((Projet) listeProjets.get(patateChaude))
							.setSujet((Sujet) listeSujets.get(13));
					break;
				case "15":
					((Projet) listeProjets.get(patateChaude))
							.setSujet((Sujet) listeSujets.get(14));
					break;
				case "16":
					((Projet) listeProjets.get(patateChaude))
							.setSujet((Sujet) listeSujets.get(15));
					break;
				case "17":
					((Projet) listeProjets.get(patateChaude))
							.setSujet((Sujet) listeSujets.get(16));
					break;
				case "18":
					((Projet) listeProjets.get(patateChaude))
							.setSujet((Sujet) listeSujets.get(17));
					break;
				case "19":
					((Projet) listeProjets.get(patateChaude))
							.setSujet((Sujet) listeSujets.get(18));
					break;
				case "20":
					((Projet) listeProjets.get(patateChaude))
							.setSujet((Sujet) listeSujets.get(19));
					break;
				case "21":
					((Projet) listeProjets.get(patateChaude))
							.setSujet((Sujet) listeSujets.get(20));
					break;
				case "22":
					((Projet) listeProjets.get(patateChaude))
							.setSujet((Sujet) listeSujets.get(21));
					break;
				case "23":
					((Projet) listeProjets.get(patateChaude))
							.setSujet((Sujet) listeSujets.get(22));
					break;
				case "24":
					((Projet) listeProjets.get(patateChaude))
							.setSujet((Sujet) listeSujets.get(23));
					break;
				case "25":
					((Projet) listeProjets.get(patateChaude))
							.setSujet((Sujet) listeSujets.get(24));
					break;
				case "26":
					((Projet) listeProjets.get(patateChaude))
							.setSujet((Sujet) listeSujets.get(25));
					break;

				default:
					break;
				}

			}
			return (ArrayList<Object>) listeProjets;
		case "Sujet":
			for (int i = 1; i < texte.size(); i++) {
				tempLigne = texte.get(i);
				attributs = tempLigne.split(";");
				liste.add(new Sujet(attributs[0], attributs[1], attributs[2]));
			}
			break;

		default:
			break;
		}

		return liste;
	}

	
	/**
	 * Permet d'ajouter les étudiants dans leurs groupes respectifs
	 * @param List<Object> pListeGroupes
	 * 						liste des groupes
	 * 
	 * @param File csvEtudiants
	 * 				le csv des étudiants
	 * @param List<Object> listeEtudiants
	 * 						liste des étudiants
	 * */
	public static List<Object> addEtudiantsInGroupe(List<Object> pListeGroupes,
			File csvEtudiants, List<Object> listeEtudiants) {

		List<Object> listeGroupe = pListeGroupes;
		List<String> texte = null;
		try {
			texte = CsvLib.readFile(csvEtudiants);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 1; i < texte.size(); i++) {
			String tempLigne = texte.get(i);
			String attributs[] = tempLigne.split(";");
			for (int j = 0; j < pListeGroupes.size(); j++) {
				if (attributs[0].equals(((Groupe) listeGroupe.get(j)).getID())) {
					((Groupe) listeGroupe.get(j))
							.addEtudiant((Etudiant) listeEtudiants.get(i - 1));
				}
			}
		}
		
		

		return listeGroupe;
	}
}
