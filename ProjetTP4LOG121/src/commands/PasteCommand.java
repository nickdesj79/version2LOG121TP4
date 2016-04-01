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
import models.Model;
import models.ModelState;


/**
 * 
 */
public class PasteCommand implements Command {

	private Model model;
	private ModelState previousState;
	
	/**
	 * 
	 */
	public PasteCommand(Model model){
		this.model = model;
	}

	/**
	 * 
	 */
	public void execute() {
		ModelState stateInClipBoard = ClipBoard.getInstance().getContent();
		if(stateInClipBoard != null) {
			previousState = model.saveState();
			model.restoreState(stateInClipBoard);
		}
	}
	
	/**
	 * 
	 */
	public void unexecute() {
		if(previousState != null) {
			model.restoreState(previousState);
		}
	}
}