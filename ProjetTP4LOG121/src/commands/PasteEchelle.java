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
package commands;

import principale.ClipBoard;
import models.Model;
import models.ModelState;

public class PasteEchelle implements Command {
	
	private Model model;
	private ModelState previousState;
	
	public PasteEchelle(Model model){
		this.model = model;		
	}
	
	/**
	 * Ceci vas refaire la prochaine commande
	 */
	public void execute() {
		ModelState stateInClipBoard = ClipBoard.getInstance().getContent();
		if(stateInClipBoard != null) {
			previousState = model.saveState();
			model.restoreState(stateInClipBoard);
		}
	}
	
	/**
	 * Ceci annule la dernière commande
	 */
	public void unexecute(){
		ModelState stateInClipBoard = ClipBoard.getInstance().getContent();
		if(stateInClipBoard != null) {
			model.restoreState(previousState);
		}
	}
	
}