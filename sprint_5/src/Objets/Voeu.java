package Objets;
/**
 * Classe objet des voeux
 */
public class Voeu {

		String numero;
		Groupe groupe;
		Sujet sujet;
		/**
		 * Constructeur paramètré.
		 * 
		 * @param pNumero
		 * 		Le numéro du voeu.
		 * @param pGroupe
		 * 		Le groupe ayant formulé le voeu.
		 * @param pSujet
		 * 		Le sujet auquel correspond le voeu.
		 */
		public Voeu(String pNumero, Groupe pGroupe, Sujet pSujet) {
			numero = pNumero;
			groupe = pGroupe ;
			sujet = pSujet ;
		}
		
		/**
		 * 
		 * @return Le groupe ayant formulé le voeu.
		 */
		public Groupe getGroupe() {
			return this.groupe;
		}
	
		/**
		 * 
		 * @return Le sujet correspondant au voeu.
		 */
		public Sujet getSujet() {
			return this.sujet;
		}
		
		/**
		 * 
		 * @return Le numéro du voeu.
		 */
		public String getNumero() {
			return this.numero;
		}
}
