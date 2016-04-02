/******************************************************
Cours:  LOG121
Projet: Laboratoire 4
Nom du fichier: CopyManager.java
Date créé: 2013-11-22
 *******************************************************
Historique des modifications
 *******************************************************
 *@author Shaun-David Sauro, Gabriel St-Hilaire, Frédéric Gascon
 *@date 2013-11-22
 *******************************************************/
package Commandes;

import modele.EtatDuModele;
import modele.Modele;
import principale.ClipBoard;

/**
 * 
 */
public class CommandeCopier implements Commande {
	
	private Modele model;
	private EtatDuModele previousState;
	
	/**
	 * 
	 */
	public CommandeCopier(Modele model){
		this.model = model;
	}
	

	/**
	 * 
	 */
	public void execute() {
		ClipBoard clipBoard = ClipBoard.getInstance();
		
		previousState = clipBoard.getContent();
		clipBoard.setContent(model.sauvegarderEtat());
	}
	
	/**
	 * 
	 */
	public void unexecute() {
		ClipBoard.getInstance().setContent(previousState);
	}
}