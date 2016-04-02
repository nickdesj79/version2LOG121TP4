/******************************************************
Cours:  LOG121
Projet: Laboratoire 4
Nom du fichier: PasteEchelle.java
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

public class UndoRedo implements Commande {
	
	private Modele model;
	private EtatDuModele previousState;
	
	public UndoRedo(Modele model){
		this.model = model;		
	}
	
	/**
	 * Ceci vas refaire la prochaine commande
	 */
	public void execute() {
		EtatDuModele stateInClipBoard = ClipBoard.getInstance().getContent();
		if(stateInClipBoard != null) {
			previousState = model.sauvegarderEtat();
			model.restaurerEtat(stateInClipBoard);
		}
	}
	
	/**
	 * Ceci annule la dernière commande
	 */
	public void unexecute(){
		EtatDuModele stateInClipBoard = ClipBoard.getInstance().getContent();
		if(stateInClipBoard != null) {
			model.restaurerEtat(previousState);
		}
	}
	
}