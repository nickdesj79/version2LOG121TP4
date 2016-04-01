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

import java.io.File;
import java.io.IOException;

import views.*;
import views.FenetrePrincipale;
import views.ImageView;
import views.PerspectiveView;

/**
 * Classe principale
 */
public class Application
{
	
	/**
	 * 
	 * @param args arguments reçu, peut recevoir en paramètre une image à charger
	 */
	public static void main(String[] args) {
		Application application = new Application();
		
		if(args.length >= 1) {
			try {
				application.getController().loadImage(new File(args[0]));
			} catch (IOException e) {
				System.out.println("Failed to load image.");
				System.exit(1);
			}
		}
		
		application.setWindowVisible(true);
	}
	
	private FenetrePrincipale fenetrePrincipale;
	private Controller controller;
	
	/**
	 * Constructeur de l'application. Affiche la fenêtre principale.
	 */
	public Application(){
		controller = new Controller(2);
		fenetrePrincipale = new FenetrePrincipale(controller);
		
		this.creerPerspectiveView(10, 10, 350, 350);
		this.creerPerspectiveView(375, 10, 350, 350);
		this.creerView(328, 362, 75, 75);
	}
	
	/**
	 * Constructeur de la view 
	 * @param posX Position X de la view
	 * @param posY Position Y de la view
	 * @param width Largeur de la view
	 * @param height Hauteur de la view
	 */
	private void creerView(int posX, int posY, int width, int height) {
		ImageView view = new ImageView(posX, posY, width, height);
		fenetrePrincipale.addToContainer(view);
		controller.observeImage(view);
	}
	
	/**
	 * Constructeur de la perspective
	 * @param posX Position X de la view
	 * @param posY Position Y de la view
	 * @param width Largeur de la view
	 * @param height Hauteur de la view
	 */
	private void creerPerspectiveView(int posX, int posY, int width, int height) {
		PerspectiveView view = new PerspectiveView(posX, posY, width, height);
		fenetrePrincipale.addToContainer(view);
		controller.observeImage(view);
		controller.observePerspective(view);
	}
	
	
	/**
	 * Méthode servant à afficher la fenêtre principale
	 * */
	public void setWindowVisible(boolean visible) {
		fenetrePrincipale.setVisible(visible);
	}
	
	/**
	 * Méthode servant à retourner le controleur
	 * */
	public Controller getController() {
		return controller;
	}
}