package Panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;
import javax.swing.AbstractAction;
import javax.swing.DefaultRowSorter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import AbstractTableModel.AbstractTableModelProjet;
import JTables.JTableProjet;
import Objets.Projet;

/**
 * Classe qui étend JPanelPerso. Créer un panel contenant la JTable + scrollPane
 * 
 * 
 * @author Groupe2B1
 * @version 1.0
 */
@SuppressWarnings("serial")
public class PanelProjet extends JPanel {

	private AbstractTableModelProjet modele;
	private JTableProjet tableau;
	private final String[] entete = { "groupe", "numSujet" };
	private JTextField filterTextField;
	private int colAFilter = 0;
	private JComboBox<String> combo;
	private JFramePerso mainFrame;

	/**
	 * Constructeur paramètré.
	 * 
	 * @param fileSelected
	 * 		Le fichier sélectionné (Etudiant, Groupe, Intervenant, Projet, Sujet).
	 * @param pmainFrame
	 * 		La JFramePerso (JFrame principale de l'application).
	 * @throws IOException
	 */
	public PanelProjet(File fileSelected, JFramePerso pmainFrame)
			throws IOException {
		super(new BorderLayout());

		mainFrame = pmainFrame;

		modele = new AbstractTableModelProjet(fileSelected, mainFrame);
		tableau = new JTableProjet(modele);
		tableau.setAutoCreateRowSorter(true);

		tableau.setEnabled(true);

		JPanel boutons = new JPanel();

		boutons.add(new JButton(new AddAction()));
		boutons.add(new JButton(new RemoveAction()));
		boutons.add(new JButton(new ModifyAction()));
		boutons.add(new JLabel("           Filtrer par : "));

		String[] tab = { "Groupe", "Numéro du sujet" };
		combo = new JComboBox<String>(tab);
		combo.addItemListener(new ItemState());

		boutons.add(combo);

		filterTextField = new JTextField();
		filterTextField.setPreferredSize(new Dimension(100, 28));
		filterTextField.getDocument().addDocumentListener(
				new DocumentListener() {

					@Override
					public void changedUpdate(javax.swing.event.DocumentEvent e) {
						applyTableFilter(filterTextField.getText());

					}

					@Override
					public void insertUpdate(javax.swing.event.DocumentEvent e) {
						applyTableFilter(filterTextField.getText());

					}

					@Override
					public void removeUpdate(javax.swing.event.DocumentEvent e) {
						applyTableFilter(filterTextField.getText());

					}

				});
		boutons.add(filterTextField);

		for (int i = 0; i < tableau.getColumnCount(); i++) {
			TableColumn column1 = tableau.getTableHeader().getColumnModel()
					.getColumn(i);

			column1.setHeaderValue(entete[i]);
		}

		this.add(tableau.getTableHeader(), BorderLayout.NORTH);
		this.add(tableau, BorderLayout.CENTER);
		this.add(boutons, BorderLayout.SOUTH);

	}

	public JPanel getThis() {
		return this;
	}

	public JTable getTable() {
		return this.tableau;
	}

	private class AddAction extends AbstractAction {
		private AddAction() {
			super("Ajouter");
		}

		public void actionPerformed(ActionEvent e) {

			String groupe = JOptionPane.showInputDialog(getThis(),
					"Entrez le groupe du sujet", "Groupe du sujet",
					JOptionPane.QUESTION_MESSAGE);
			if (!groupe.equals("")) { // Si la fenêtre se ferme et qu'il n'y
				// a rien, on suppose qu'il veut
				// annuler

				String numSujet = JOptionPane.showInputDialog(getThis(),
						"Entrez le numéro du sujet", "Numéro du sujet",
						JOptionPane.QUESTION_MESSAGE);

				modele.addProjet(new Projet(mainFrame.getBaseDonnees()
						.getGroupe(groupe), mainFrame.getBaseDonnees()
						.getSujet(numSujet), mainFrame.getBaseDonnees()
						.getListeIntervenants()));
			}
		}
	}

	private class RemoveAction extends AbstractAction {
		private RemoveAction() {
			super("Supprimmer");
		}

		public void actionPerformed(ActionEvent e) {
			int[] selection = tableau.getSelectedRows();

			for (int i = selection.length - 1; i >= 0; i--) {
				modele.removeProjet(selection[i]);
			}
		}
	}

	private class ModifyAction extends AbstractAction {

		private ModifyAction() {
			super("Modifier");
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int[] selection = tableau.getSelectedRows();
			if (selection.length == 0) {
				JOptionPane.showMessageDialog(getThis(),
						"Veuillez sélectionner un projet");
			} else if (selection.length > 1) {
				JOptionPane.showMessageDialog(getThis(),
						"Veuillez sélectionner un seul projet");
			} else {
				String groupe = JOptionPane.showInputDialog(getThis(),
						"Entrez le groupe du sujet", "Groupe du sujet",
						JOptionPane.QUESTION_MESSAGE);
				if (!groupe.equals("")) { // Si la fenêtre se ferme et qu'il n'y
											// a rien, on suppose qu'il veut
											// annuler

					String numSujet = JOptionPane.showInputDialog(getThis(),
							"Entrez le numéro du sujet", "Numéro du sujet",
							JOptionPane.QUESTION_MESSAGE);

					modele.addProjet(new Projet(mainFrame.getBaseDonnees()
							.getGroupe(groupe), mainFrame.getBaseDonnees()
							.getSujet(numSujet), mainFrame.getBaseDonnees()
							.getListeIntervenants()));
					modele.removeProjet(selection[0]);
				}

			}

		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void applyTableFilter(String filterText) {
		// On escape le texte afin que son contenu ne soit pas considéré comme
		// une regexp
		String escapedFilterText = Pattern.quote(filterText);
		// On ajoute les wildcards a gauche et a droite
		String completeFilterText = ".*" + escapedFilterText + ".*";
		// On applique le filtre a la JTable
		((DefaultRowSorter) tableau.getRowSorter()).setRowFilter(RowFilter
				.regexFilter(completeFilterText, colAFilter));
	}

	public AbstractTableModel getModel() {
		return this.modele;
	}

	// Classe interne implémentant l'interface ItemListener
	class ItemState implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			switch ((String) e.getItem()) {
			case "Groupe":
				colAFilter = 0;
				break;
			case "Numéro du sujet":
				colAFilter = 1;

				break;

			default:
				break;
			}
		}
	}
}
