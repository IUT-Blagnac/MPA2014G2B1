package Tests;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class TestChargerObjet {

	
	public static void main(String[] args){
		TestSuite suite = new TestSuite("Tests de ChargerObjet");
		suite.addTestSuite(TestChargerObjet.class);
		junit.textui.TestRunner.run(suite);
	}
	
	public void testCreerObjet(){
	//	TestCase.assertEquals("Teste de a structure de retour", true, );
	}
}
