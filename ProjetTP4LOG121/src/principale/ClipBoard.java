/******************************************************
Cours:  LOG121
Projet: Laboratoire 4
Nom du fichier: ClipBoard.java
Date cr��: 2013-11-22
 *******************************************************
Historique des modifications
 *******************************************************
 *@author Shaun-David Sauro, Gabriel St-Hilaire, Fr�d�ric Gascon
 *@date 2013-11-22
 *******************************************************/
package principale;

import models.ModelState;

public class ClipBoard {
	
	/**
	 * Permet d'instancier le ClipBoard
	 * */
	private static final ClipBoard instance = new ClipBoard();
	
	/**
	 * Retourne l'instance du ClibBoard
	 * */
	public static ClipBoard getInstance() {
		return instance;
	}
	
	private ModelState content;
	
	/**
	 * Retourne le ModelState(�tat du model) du ClipBoard
	 * */
	public ModelState getContent() {
		return content;
	}
	
	/**
	 * Assigne un ModelState(�tat du model) au ClibBoard
	 * */
	public void setContent(ModelState value) {
		content = value;
	}
}