/******************************************************
Cours:  LOG121
Projet: Laboratoire 4
Nom du fichier: Application.java
Date créé: 2013-11-22
 *******************************************************
Historique des modifications
 *******************************************************
 *@author Shaun-David Sauro, Gabriel St-Hilaire, Frédéric Gascon
 *@date 2013-11-22
 *******************************************************/

package principale;

import views.FenetrePrincipale;
import views.VueStatique;
import views.VueDynamique;

/**
 * Classe principale
 */
public class Application
{
	
	public static void main(String[] args) {
		Application application = new Application();
	}
	
	private FenetrePrincipale fenetrePrincipale;
	private Controlleur controller;
	
	/**
	 * Constructeur de l'application. Affiche la fenêtre principale.
	 */
	public Application(){
		controller = new Controlleur(2);
		fenetrePrincipale = new FenetrePrincipale(controller);
		fenetrePrincipale.setVisible(true);
		
		VueStatique vue1 = new VueStatique(10, 10, 1160, 300);
		fenetrePrincipale.addToContainer(vue1);
		controller.observeImage(vue1);
		
		 VueDynamique vue2 = new VueDynamique(10, 320, 590, 410);
		fenetrePrincipale.addToContainer(vue2);
		controller.observeImage(vue2);
		controller.observePerspective(vue2);
		
		VueDynamique vue3 = new VueDynamique(610, 320, 560, 410);
		fenetrePrincipale.addToContainer(vue3);
		controller.observeImage(vue3);
		controller.observePerspective(vue3);
		
	}
	
	/**
	 * Méthode servant à retourner le controleur
	 * */
	public Controlleur getController() {
		return controller;
	}
}