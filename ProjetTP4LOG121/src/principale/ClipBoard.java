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

import modele.EtatDuModele;

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
	
	private EtatDuModele content;
	
	/**
	 * Retourne le ModelState(�tat du model) du ClipBoard
	 * */
	public EtatDuModele getContent() {
		return content;
	}
	
	/**
	 * Assigne un ModelState(�tat du model) au ClibBoard
	 * */
	public void setContent(EtatDuModele value) {
		content = value;
	}
}