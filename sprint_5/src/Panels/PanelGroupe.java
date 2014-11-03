package Panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Objets.Groupe;
/**
 * Classe qui étend JPanelPerso. Créer un panel contenant la JTable + scrollPane.
 * 
 * @author groupe 2B1
 *
 */
@SuppressWarnings("serial")
public class PanelGroupe extends JPanel {

	JPanel panelOuest;
	JPanel panelCenter;
	JTextArea texte;
	JFramePerso mainFrame;
	JButtonPerso tempBouton;
	List<JButtonPerso> tabBoutons;
	
	/**
	 * Constructeur paramètré.
	 * 
	 * @param fileSelected
	 * 		Le fichier sélectionné (Etudiant, Groupe, Intervenant, Projet, Sujet).
	 * @param pmainFrame
	 * 		La JFramePerso (JFrame principale de l'application).
	 * @throws IOException
	 */
	public PanelGroupe(JFramePerso pmainFrame) {
		super(new BorderLayout());

		mainFrame = pmainFrame;
		
		tabBoutons = new ArrayList<JButtonPerso>();

		creerOuest();
		creerEst();

	}

	/**
	 * Créer la partie "Ouest" (gauche).
	 */
	public void creerOuest() {
		List<Object> listeGroupe = mainFrame.getBaseDonnees().getListeGroupes();

		panelOuest = new JPanel(new GridLayout(listeGroupe.size(), 1));

		for (int i = 0; i < listeGroupe.size(); i++) {
			tempBouton = new JButtonPerso(
					((Groupe) listeGroupe.get(i)).getID(), i);
			tempBouton.setPreferredSize(new Dimension(200, 50));
			tabBoutons.add(tempBouton);
			
			tempBouton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int indice = ((JButtonPerso) e.getSource()).getIndice();
					String texteToPut = (mainFrame.getBaseDonnees().getListeGroupes().get(indice)).toString();
					texte.setText(texteToPut);
				}
			});
			
			panelOuest.add(tempBouton);
		}

		JScrollPane scrollPanel = new JScrollPane(panelOuest);
		scrollPanel.setPreferredSize(new Dimension(225, 400));
		this.add(scrollPanel, BorderLayout.WEST);

	}

	/**
	 * Créer la partie "Est" (droite).
	 */
	public void creerEst() {
		panelCenter = new JPanel(new BorderLayout());
		texte = new JTextArea();
		texte.setEditable(false);
		panelCenter.add(texte);

		this.add(panelCenter, BorderLayout.CENTER);
	}

}
