/******************************************************
Cours:  LOG121
Projet: Laboratoire 4
Nom du fichier: TranslationCommand.java
Date créé: 2013-11-22
 *******************************************************
Historique des modifications
 *******************************************************
 *@author Shaun-David Sauro, Gabriel St-Hilaire, Frédéric Gascon
 *@date 2013-11-22
 *******************************************************/
package commands;

import models.Perspective;
import principale.Coordonnee;

/**
 * 
 */
public class TranslationCommand implements Command
{
	
	private Coordonnee currentCoo;
	private Coordonnee deplacement;
	private Perspective unePerspective;
	
	/**
	 * @param perspective perspective sur laquelle appliquer la translation
	 * @param coordonnee 
	 */
	public TranslationCommand(Perspective perspective, Coordonnee deplacement) {
		unePerspective = perspective;
		currentCoo = perspective.getPosition();
		this.deplacement = deplacement;
	}

	/**
	 * Applique la translation sur la perspective
	 */
	public void execute() {
		Coordonnee newPosition = currentCoo.add(deplacement);
		unePerspective.setPosition(newPosition);
	}
	
	/**
	 * Annule la translation sur la perspective
	 */
	public void unexecute() {
		unePerspective.setPosition(currentCoo);	
	}
}