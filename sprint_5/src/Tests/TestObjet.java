package Tests;


import java.util.ArrayList;
import java.util.List;

import Objets.Etudiant;
import Objets.Groupe;
import Objets.Intervenant;
import Objets.Projet;
import Objets.Sujet;
import Objets.Voeu;

import junit.framework.TestCase;
import junit.framework.TestSuite;


public class TestObjet extends TestCase{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestSuite suite = new TestSuite(TestObjet.class, "Test des classes objet");
		junit.textui.TestRunner.run(suite);
	}

	public void testClassEtudiant(){
		Groupe groupe = new Groupe("libell�", new ArrayList<Etudiant>());
		Etudiant etu = new Etudiant(groupe, "un nom", "un prenom");
		TestCase.assertEquals("Test de getGroupe", groupe, etu.getGroupe());
		TestCase.assertEquals("Test de getNom", "un nom", etu.getNom());
		TestCase.assertEquals("Test de getPrenom", "un prenom", etu.getPrenom());
	}
	


	public void testClassIntervenant(){
		Intervenant inter = new Intervenant("un nom", "un prenom");
		TestCase.assertEquals("Test de getNom", "un nom", inter.getNom());
		TestCase.assertEquals("Test de getPrenom", "un prenom", inter.getPrenom());
		TestCase.assertEquals("Test de toString", "un nom un prenom", inter.toString());
	}

	public void testClassProjet(){
		List<Object> listInter = new ArrayList<Object>();
		Groupe groupe = new Groupe("libel�", new ArrayList<Etudiant>());
		Sujet sujet = new Sujet("ID", "nom", "titre");
		Projet projet = new Projet(groupe, sujet, listInter);
		TestCase.assertEquals("Test de getGroupe", "un groupe", projet.getGroupe());
		TestCase.assertEquals("Test de getSujet", sujet, projet.getSujet());
		//TestCase.assertEquals("Test de getIntervenants", listInter, projet.getIntervenants());
	}
	
	public void testClassSujet(){
		Sujet sujet = new Sujet("un ID", "un nom", "titre");
		Projet projet = new Projet(new Groupe("libel�", new ArrayList<Etudiant>()), sujet, new ArrayList<Object>());
		sujet.setProjet(projet);
		TestCase.assertEquals("Test de getID",  "un ID", sujet.getID());
		TestCase.assertEquals("Test de getNom", "un nom", sujet.getNom());
		TestCase.assertEquals("Test de getTitre", "un titre", sujet.getTitre());
		TestCase.assertEquals("Test de getProjet", projet, sujet.getProjet());
	}
	
	public void testClassGroupe(){
		List<Etudiant> liste = new ArrayList<Etudiant>();
		Groupe groupe = new Groupe("un libell�");
		Etudiant etu1 = new Etudiant(groupe, "etu1","prenom1");
		groupe.addEtudiant(etu1);
		TestCase.assertEquals("Test de getLibelle", "un libelle", groupe.getID());
		TestCase.assertEquals("Test de getListeEtudiant", liste, groupe.getListeEtudiants());
	}
	
	public void testClassVoeux(){
		Sujet sujet = new Sujet("un ID", "un nom", "un titre");
		Groupe groupe = new Groupe("un libell�", new ArrayList<Etudiant>());
		Voeu voeux = new Voeu("1", groupe, sujet);
		TestCase.assertEquals("Test de getNumemro", "1", voeux.getNumero());
		TestCase.assertEquals("Test de getGroupe", groupe, voeux.getGroupe());
		TestCase.assertEquals("Test de getSujet", sujet, voeux.getSujet());
	}
	
	/*public void testClassEncadrer(){
		Intervenant inter = new Intervenant("un nom", "un prenom");
		Projet projet = new Projet("un groupe", "un nom");
		Encadrer encadrer = new Encadrer(inter, projet, );
		TestCase.assertEquals("test de getIntervenant", inter.getID(), encadrer.getIDIntervenant());
		TestCase.assertEquals("test de getProjet", projet.getID(), encadrer.getIDProjet());
		TestCase.assertEquals("test de getRole", role, encadrer.getRole());
	}*/
}
