package Librairies;



import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * Classe permettant de choisir exclusivement des fichiers d'une seule extension
 * 
 * @author Groupe2B1
 * @version 1.0
 */
public class MonFiltre extends FileFilter {

	String [] lesSuffixes;
	   String  laDescription;
	   
	   /**
	    * Constructeur avec en param�tres le nom et l'extension d'un fichier.
	    * @param lesSuffixes
	    * Le tableau de toutes les extensions disponibles
	    * @param laDescription
	    * La description d'un type de fichier
	    */
	   public MonFiltre(String []lesSuffixes, 
	                         String laDescription){
	      this.lesSuffixes = lesSuffixes;
	      this.laDescription = laDescription;
	   }
	   
	   /**
	    * M�thode permettant de v�rifier si l'extension choisi est disponible
	    * @param suffixe
	    * @return 
	    * Retourne true si l'extension existe, sinon retourne false.
	    */
	   boolean appartient( String suffixe ){
	      for( int i = 0; i<lesSuffixes.length; ++i)
	         if(suffixe.equals(lesSuffixes[i]))
	            return true;
	         return false;
	   }
	   public boolean accept(File f) {
	      if (f.isDirectory())  return true;
	      String suffixe = null;
	      String s = f.getName();
	      int i = s.lastIndexOf('.');
	      if(i > 0 &&  i < s.length() - 1)
	         suffixe=s.substring(i+1).toLowerCase();
	      return suffixe!=null&&appartient(suffixe);
	   }
	  
	   /**
	    * M�thode permettant d'obtenir la description d'un fichier
	    * @return 
	    * La description d'un fichier
	    */
	   public String getDescription() {
	      return laDescription;
	   }
	
}
