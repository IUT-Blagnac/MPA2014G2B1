import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.swing.*;

import AbstractTableModel.AbstractTableModelEtudiant;
import AbstractTableModel.AbstractTableModelIntervenant;
import AbstractTableModel.AbstractTableModelProjet;
import AbstractTableModel.AbstractTableModelSujet;
import JTables.JTableEtudiant;
import JTables.JTableIntervenant;
import JTables.JTableProjet;
import JTables.JTableSujet;
import Librairies.CsvLib;
import Librairies.MonFiltre;
import Panels.Database;
import Panels.JFramePerso;
import Panels.PanelEtudiant;
import Panels.PanelGroupe;
import Panels.PanelIntervenant;
import Panels.PanelProjet;
import Panels.PanelSujet;

public class InterfaceGraphique extends JFramePerso {

	/**
	 * Interface de l'application
	 * 
	 * @author Groupe2B1
	 * @version Sprint 3.0
	 */

	private static final long serialVersionUID = 1L;

	int tour = 0;

	private JTable tableATM = null;
	@SuppressWarnings("unused")
	private JPanel panelATM = null;
	private File fileSelected = null;
	private JScrollPane panelScroll = null;

	private JMenu afficher;

	private JMenuItem afficherEtu;
	private JMenuItem afficherProj;
	private JMenuItem afficherSuj;
	private JMenuItem afficherInterv;
	private JMenuItem afficherGroupe;

	private JMenuItem enregistrer;
	private JMenuItem aPropos;
	private JMenuItem quitter;

	private FileReader fr = null;
	BufferedReader br;
	Database BD;

	/**
	 * Constructeur par défaut de la classe InterfaceGraphique.
	 */
	public InterfaceGraphique() {
		super("Projet OPTI");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		setMenu();
		this.setSize(1000, 700);
		setResizable(false);
		this.setLocationRelativeTo(null);

		createBD();
	}

	
	/**
	 * Créer la base de données façon "java"
	 */
	public void createBD() {
		// Demande des fichiers
		JOptionPane
				.showMessageDialog(
						this,
						"Veuillez saisir les .csv à importer dans cet ordre  : etudiants ,intervenants  , projets , sujets\n");
		File etudiants = CsvLib.parcourir();
		File intervenants = CsvLib.parcourir();
		File projets = CsvLib.parcourir();
		File sujets = CsvLib.parcourir();

		BD = new Database(intervenants, projets, etudiants, sujets);

	}

	/**
	 * Cr�er un ScrollPanel � partir du JTextArea
	 */
	public void setScrollPanel(JPanel toAdd) {
		panelScroll = new JScrollPane(toAdd);
		this.add(panelScroll);
	}

	/**
	 * M�thode qui permet de faire appara�tre une bo�te de dialogue demandant
	 * confirmation avant de quitter
	 */
	public void quitter() {
		int confirmation = JOptionPane.showConfirmDialog(this,
				"Voulez-vous vraiment quitter ?", "Attention!",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (confirmation == JOptionPane.YES_OPTION) {
			this.dispose();
		}
	}

	/**
	 * M�thode qui d�finie la fen�tre 'A Propos' qui appara�t apr�s l'appui sur
	 * le bouton
	 */
	public void fenetreAPropos() {
		JOptionPane
				.showMessageDialog(
						this,
						"Groupe 2B1: \nBOUSQUET Cl�ment, DAZAYOUS Tim, GAMEZ Lucas, HERMET Corentin, LIEU Alexis, POUX Mathieu, SLAMNIA Mehdi"
								+ "\nIUT de Blagnac    DUT INFOS3/Module MPA"
								+ "\nProjet OPTI" + "\nSprint 3");
	}

	/**
	 * M�thode qui permet de créer le menu de l'application
	 */
	private void setMenu() {

		JMenuBar barDeMenu = new JMenuBar();
		barDeMenu.setLayout(new FlowLayout(0, 0, 0));

		this.add(barDeMenu, BorderLayout.NORTH);

		afficher = new JMenu("Afficher");

		afficherEtu = new JMenuItem("Etudiants");
		afficherProj = new JMenuItem("Projets");
		afficherSuj = new JMenuItem("Sujets");
		afficherInterv = new JMenuItem("Intervenants");
		afficherGroupe = new JMenuItem("Groupes");

		afficher.add(afficherEtu);
		afficher.add(afficherProj);
		afficher.add(afficherSuj);
		afficher.add(afficherInterv);
		afficher.add(afficherGroupe);

		enregistrer = new JMenuItem("Enregistrer");
		aPropos = new JMenuItem("A propos");
		quitter = new JMenuItem("Quitter");

		barDeMenu.add(afficher);
		barDeMenu.add(enregistrer);
		barDeMenu.add(aPropos);
		barDeMenu.add(quitter);

		setListeners();

	}

	/**
	 * M�thode qui permet d'ajouter tous les listeners associ�s aux boutons du
	 * Menu
	 */
	private void setListeners() {

		afficherEtu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {

					if (panelScroll != null) {

						remove(panelScroll);

						// On enlève l'ancien
						panelScroll = null;
						panelATM = null;
						tableATM = null;

					}
					PanelEtudiant panelEtudiant = new PanelEtudiant(
							getBaseDonnees().getFileEtu(), getThis());
					tableATM = panelEtudiant.getTable();
					panelATM = panelEtudiant;
					setScrollPanel(panelEtudiant);
					revalidate();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			};
		});

		afficherSuj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					if (panelScroll != null) {

						remove(panelScroll);

						// On enlève l'ancien
						panelScroll = null;
						panelATM = null;
						tableATM = null;

					}

					PanelSujet panelSujet = new PanelSujet(getBaseDonnees()
							.getFileSuj(), getThis());
					tableATM = panelSujet.getTable();
					panelATM = panelSujet;
					setScrollPanel(panelSujet);
					revalidate();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			};
		});

		afficherProj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					if (panelScroll != null) {

						remove(panelScroll);

						// On enlève l'ancien
						panelScroll = null;
						panelATM = null;
						tableATM = null;

					}

					PanelProjet panelProjet = new PanelProjet(getBaseDonnees()
							.getFileProj(), getThis());
					tableATM = panelProjet.getTable();
					panelATM = panelProjet;
					setScrollPanel(panelProjet);
					revalidate();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			};
		});

		afficherInterv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					if (panelScroll != null) {

						remove(panelScroll);

						// On enlève l'ancien
						panelScroll = null;
						panelATM = null;
						tableATM = null;

					}

					PanelIntervenant panelIntervenant = new PanelIntervenant(
							getBaseDonnees().getFileInterv(), getThis());
					tableATM = panelIntervenant.getTable();
					panelATM = panelIntervenant;
					setScrollPanel(panelIntervenant);
					revalidate();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			};
		});

		afficherGroupe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (panelScroll != null) {

					remove(panelScroll);

					// On enlève l'ancien
					panelScroll = null;
					panelATM = null;
					tableATM = null;

				}

				PanelGroupe panelGroupe = new PanelGroupe(getThis());
				//getThis().add(panelGroupe);
				setScrollPanel(panelGroupe);
				tableATM = null;
				panelATM = panelGroupe;
				revalidate();
			};
		});

		enregistrer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) { // Si enregistrement
															// des étudiants

				if (tableATM instanceof JTableEtudiant) {
					JTableEtudiant temp = (JTableEtudiant) tableATM;
					AbstractTableModelEtudiant modeleTEMP = temp.modele;

					File choix = CsvLib.parcourir();
					try {
						CsvLib.saveIn(choix, modeleTEMP.getTexte());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				else if (tableATM instanceof JTableProjet) { // Si
																// enregistrement
																// des projets

					JTableProjet temp = (JTableProjet) tableATM;
					AbstractTableModelProjet modeleTEMP = temp.modele;

					File choix = CsvLib.parcourir();
					try {
						CsvLib.saveIn(choix, modeleTEMP.getTexte());
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (tableATM instanceof JTableIntervenant) { // Si
																	// enregistrement
																	// des
																	// intervenants
					JTableIntervenant temp = (JTableIntervenant) tableATM;
					AbstractTableModelIntervenant modeleTEMP = temp.modele;

					File choix = CsvLib.parcourir();
					try {
						CsvLib.saveIn(choix, modeleTEMP.getTexte());
					} catch (IOException e) {
						e.printStackTrace();
					}

				} else { // Si enregistrement des sujets
					JTableSujet temp = (JTableSujet) tableATM;
					AbstractTableModelSujet modeleTEMP = temp.modele;

					File choix = CsvLib.parcourir();
					try {
						CsvLib.saveIn(choix, modeleTEMP.getTexte());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		});

		aPropos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fenetreAPropos();
			}
		});

		quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quitter();
			}
		});
	}
	/**
	 * Retourne la frame de l'application
	 */
	public JFramePerso getThis() {
		return this;
	}

	/**
	 * Retourne la base de données de l'application
	 */
	@Override
	public Database getBaseDonnees() {
		return BD;
	}

}
