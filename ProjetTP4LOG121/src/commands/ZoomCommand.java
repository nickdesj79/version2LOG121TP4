/******************************************************
Cours:  LOG121
Projet: Laboratoire 4
Nom du fichier: ZoomCommand.java
Date cr��: 2013-11-22
 *******************************************************
Historique des modifications
 *******************************************************
 *@author Shaun-David Sauro, Gabriel St-Hilaire, Fr�d�ric Gascon
 *@date 2013-11-22
 *******************************************************/
package commands;

import models.Perspective;


/**
 * 
 */
public class ZoomCommand implements Command {
	
	private final float baseZoom;
	private final float zoomFactor;
	private final Perspective laPerspective;
	
	/**
	 * Cr�e un zoom � appliquer sur une perspective
	 * @param perspective perspective sur laquelle appliquer le zoom
	 * @param unZoom facteur de zoom � appliquer
	 */
	public ZoomCommand(Perspective perspective, float unZoom) {
		laPerspective = perspective;
		baseZoom = perspective.getZoom();
		zoomFactor = unZoom;
	}
	
	/**
	 * Applique le zoom sur la perspective.
	 */
	public void execute() {
		laPerspective.setZoom(baseZoom * zoomFactor);
	}
	
	/**
	 * Restore le zoom pr�c�dent de la perspective.
	 */
	public void unexecute() {
		laPerspective.setZoom(baseZoom);
	}
}