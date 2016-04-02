/******************************************************
Cours:  LOG121
Projet: Laboratoire 4
Nom du fichier: PasteManager.java
Date créé: 2013-11-22
 *******************************************************
Historique des modifications
 *******************************************************
 *@author Shaun-David Sauro, Gabriel St-Hilaire, Frédéric Gascon
 *@date 2013-11-22
 *******************************************************/
package commands;

import principale.ClipBoard;
import models.Modele;
import models.EtatDuModele;


/**
 * 
 */
public class CommandeColler implements Commande {

	private Modele modele;
	private EtatDuModele previousState;
	
	/**
	 * 
	 */
	public CommandeColler(Modele model){
		this.modele = model;
	}

	/**
	 * 
	 */
	public void execute() {
		EtatDuModele stateInClipBoard = ClipBoard.getInstance().getContent();
		if(stateInClipBoard != null) {
			previousState = modele.sauvegarderEtat();
			modele.restaurerEtat(stateInClipBoard);
		}
	}
	
	/**
	 * 
	 */
	public void unexecute() {
		if(previousState != null) {
			modele.restaurerEtat(previousState);
		}
	}
}