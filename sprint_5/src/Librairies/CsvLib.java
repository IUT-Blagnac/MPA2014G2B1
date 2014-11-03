package Librairies;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;

/**
 * Librairie pour �diter/cr�er/lire les fichiers CSV
 * 
 * @author Groupe2B1 (Corentin)
 * @version 1.0
 */
public class CsvLib {

	/**
	 * Permet d'obtenir le chemin du fichier csv
	 * 
	 * @param fileName
	 *            Le nom de fichier qu'on veut cr�er (String)
	 * @return Le chemin absolu du fichier cr�� (String)
	 * 
	 */
	public static String getResourcePath(String fileName) {
		final File f = new File("");
		final String fichierPath = f.getAbsolutePath() + File.separator
				+ fileName;
		return fichierPath;
	}

	/**
	 * Permet de r�cup�rer un fichier csv
	 * 
	 * @param fileName
	 *            Le nom du fichier qu'on veut retrouver (String)
	 * @return Le fichier qu'on a voulu (File)
	 */
	public static File getResource(String fileName) {
		final String completeFileName = getResourcePath(fileName);
		File file = new File(completeFileName);
		return file;
	}

	/**
	 * Permet de récupérer le contenu d'un fichier
	 * 
	 * @param file
	 *            Le fichier qu'on veut lire (File)
	 * @return Le contenu d'un fichier sous la forme d'une ArrayList<String>()
	 * @throws IOException
	 */
	public static List<String> readFile(File file) throws IOException {

		List<String> result = new ArrayList<String>();

		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);

		for (String line = br.readLine(); line != null; line = br.readLine()) {
			result.add(line);
		}

		br.close();
		fr.close();

		return result;
	}

	/**
	 * Permet d'�crire dans un fichier
	 * 
	 * @throws IOException
	 * @see FileWriter
	 */
	public static void writeIn() throws IOException {
		System.out.println("Entrez le nom du fichier");
		Scanner lecteur = new Scanner(System.in);
		String tempName = lecteur.next();

		FileWriter fw = new FileWriter(tempName + ".csv", true);

		System.out.println("Combien de lignes souhaitez-vous rentrer ?");
		int nbLigne = lecteur.nextInt();
		String tempLine = lecteur.nextLine();

		for (int cpt = 0; cpt < nbLigne; cpt++) {
			if (cpt >= 1) {
				fw.write("\n"); // forcer le passage � la ligne
			}
			System.out.println("Entrez la ligne " + cpt + " ");
			System.out.flush();
			tempLine = lecteur.nextLine();
			fw.write(tempLine);
			fw.write("\n"); // forcer le passage � la ligne

		}

		fw.close(); // fermer le fichier � la fin des traitements

	}

	/**
	 * Permet de lire un mot pr�cis dans la Liste tir�e du fichier csv en
	 * fournissant la ligne et la colonne
	 * 
	 * @param ligne
	 *            La ligne en question (int)
	 * @param colonne
	 *            La colonne en question (int)
	 * @param liste
	 *            La liste de donn�es du csv (List<String>)
	 * @return Le mot � retourner (String)
	 */
	public static String getMot(int ligne, int colonne, List<String> liste) {
		String mot;
		String ligneATM[] = liste.get(ligne).split(" ");
		mot = ligneATM[colonne];
		return mot;
	}

	/**
	 * Permet d'enregistrer du texte dans un fichier csv choisi par l'utilisateur
	 * 
	 * @param file
	 *            Le fichier dans lequel on choisit d'enregistrer (File)
	 * 
	 * @throws IOException
	 */

	public static void saveIn(File file, String texte) throws IOException {
		String tempName = "";

		tempName = file.getAbsolutePath();

		FileWriter fw;

		fw = new FileWriter(tempName + ".csv", true);

		try {
			fw.write(texte);
		} catch (IOException e) {
			e.printStackTrace();
		}

		fw.close(); // fermer le fichier � la fin des traitements

	}
	
	/**
	 * Permet récupérer un fichier d'une extension .csv choisi par l'utilisateur
	 * � l'aide d'une fen�tre 'Parcourir'
	 * 
	 * @return le fichier choisi par l'utilisateur
	 */
	public static File parcourir() {
		File fileSelected;
		MonFiltre mfc = new MonFiltre(new String[] { "csv" },
				"les fichiers de configuration (*.csv)");

		JFileChooser fc = new JFileChooser(
				"C:/Users/Chuck/Documents/data");

		fc.setAcceptAllFileFilterUsed(false);
		fc.addChoosableFileFilter(mfc);

		int returnVal = fc.showDialog(fc, "Valider");
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			fileSelected = fc.getSelectedFile();
		} else {
			fileSelected = null;
		}
		return fileSelected;
	}


}
