package Tests;
import java.util.List;
import java.io.*;
import java.util.ArrayList;

import Librairies.CsvLib;




import junit.framework.TestCase;
import junit.framework.TestSuite;


public class TestcsvLib extends TestCase{

	
	
	
	static String programmeATester = "CsvLib" ;  
	Process executionProgrammeATester ;                          
	BufferedReader ecranProgrammeATester ;                       
	BufferedWriter clavierProgrammeATester ;                     

	String finDeLigne = System.getProperty("line.separator") ;
	  
	  
	  
	public static void main(String[] args) throws Exception{
		System.out.println("Tests du programme : " + programmeATester);
	    junit.textui.TestRunner.run(new TestSuite(TestcsvLib.class));
		
	}

	protected void setUp() throws IOException {
		 executionProgrammeATester = Runtime.getRuntime().exec("java -cp .;./bin " + programmeATester); 
		 ecranProgrammeATester = new BufferedReader(new  InputStreamReader( executionProgrammeATester.getInputStream() )); 
		 clavierProgrammeATester  = new BufferedWriter(new OutputStreamWriter( executionProgrammeATester.getOutputStream() )); 
	}
	 
	 
	 
	public void testGetResourcePath(){
		File f =new File("");
		TestCase.assertEquals("Addresse Absolue du fichier", f.getAbsolutePath() + File.separator
				+"./src/tests/test", CsvLib.getResourcePath("./src/tests/test"));
	}
	
	public void testGetResource(){
		File f =new File("./src/tests/test");
		f =new File(f.getAbsolutePath() + File.separator);
		TestCase.assertEquals("fichier cr��", f, CsvLib.getResource("./src/tests/test"));
	}
	
	public void testReadFile(){
		File file = new File("./bin/tests/test.csv");
		List<String> liste = new ArrayList<String>();
		liste.add("une ligne");
		liste.add("une autre");
		try{
		TestCase.assertEquals("Lecture dans un fichier", liste, CsvLib.readFile(file));
		}catch(IOException e){
			TestCase.fail("Probl�me: fichier non trouv�");
		}
	}
	
	/*M�thode non utilis�e
	 
	  public void testWriteIn() throws InterruptedException{
		List<String> liste = new ArrayList<String>();
		liste.add("une ligne");
		liste.add("une autre");
		liste.add("une troisi�me");
		liste.add("une quatri�me");
		try{ 
			TestCase.assertEquals("Attente de \"Entrez le nom du fichier\"", "Entrez le nom du fichier",ecranProgrammeATester.readLine());
			clavierProgrammeATester.write("test"+finDeLigne);
			clavierProgrammeATester.flush();
			TestCase.assertEquals("Attente de \"Combien de lignes souhaitez-vous rentrer ?\"", "Combien de lignes souhaitez-vous rentrer ?",ecranProgrammeATester.readLine());
			clavierProgrammeATester.write(2 + finDeLigne);
			clavierProgrammeATester.flush();
			TestCase.assertEquals("Attente de \"Entrez la ligne 0\"", "Entrez la ligne 0 ",ecranProgrammeATester.readLine());
			clavierProgrammeATester.write("une troisi�me"+finDeLigne);
			clavierProgrammeATester.flush();
			TestCase.assertEquals("Attente de \"Entrez la ligne 1\"", "Entrez la ligne 1 ",ecranProgrammeATester.readLine());
			clavierProgrammeATester.write("une quatri�me"+finDeLigne);
			clavierProgrammeATester.flush();
		}catch(IOException e){
			TestCase.fail("Probl�me: fichier non trouv�");
		}
		try{
			for(int i = 0; i < liste.size(); i++)
			TestCase.assertEquals("Lecture dans un fichier", liste.get(i), csvLib.readFile(csvLib.getResource("test.csv")).get(i));
			}catch(IOException e){
				TestCase.fail("Teste mal cod�");
			}
		
	}*/
}
