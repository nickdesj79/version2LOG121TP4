/******************************************************
Cours:  LOG121
Projet: Laboratoire 4
Nom du fichier: Perspective.java
Date créé: 2013-11-22
 *******************************************************
Historique des modifications
 *******************************************************
 *@author Shaun-David Sauro, Gabriel St-Hilaire, Frédéric Gascon
 *@date 2013-11-22
 *******************************************************/
package models;

import java.util.Observable;

import principale.Coordonnee;


/**
 * 
 */
public class Perspective extends Observable implements Model {
	
	private final Coordonnee coordonnee ;
	
	private float zoom;
	
	public Perspective() {
		coordonnee = new Coordonnee(0,0);
		zoom = 1;
	}
	
	/**
	 * 
	 * @return
	 */
	public float getZoom() {
		return zoom;
	}
	
	public void setZoom(float unZoom){
		zoom = unZoom;
		setChanged();
		notifyObservers();
	}
	
	public void setPosition(Coordonnee position){
		coordonnee.setX(position.getX());
		coordonnee.setY(position.getY());
		setChanged();
		notifyObservers();
	}
	
	/**
	 * 
	 * @return
	 */
	public Coordonnee getPosition() {
		return coordonnee.copy();
	}

	public ModelState saveState() {
		return new PerspectiveModelState();
	}

	public void restoreState(ModelState state) {
		if(state instanceof PerspectiveModelState) {
			PerspectiveModelState perspectiveModelState = (PerspectiveModelState) state;
			perspectiveModelState.restore(this);
			setChanged();
			notifyObservers();
		}
	}
	
	public String serialize() {
		return Integer.toString(coordonnee.getX())
				.concat(":")
				.concat(Integer.toString(coordonnee.getY()))
				.concat(":")
				.concat(Float.toString(zoom));
	}
	
	public void unserialize(String state) throws Exception {
		String[] stateArray = state.split(":");
		if(stateArray.length != 3) {
			throw new Exception("Can't serialize Perspective: invalid state." + stateArray.length);
		} else {
			coordonnee.setX(Integer.parseInt(stateArray[0]));
			coordonnee.setY(Integer.parseInt(stateArray[1]));
			zoom = Float.parseFloat(stateArray[2]);
			setChanged();
			notifyObservers();
		}
	}
	
	private class PerspectiveModelState implements ModelState {

		private int savedXPosition;
		private int savedYPosition;
		private float savedZoom;
		
		public PerspectiveModelState() {
			savedXPosition = coordonnee.getX();
			savedYPosition = coordonnee.getY();
			savedZoom = zoom;
		}
		
		public void restore(Perspective unePerspective) {
			unePerspective.coordonnee.setX(savedXPosition);
			unePerspective.coordonnee.setY(savedYPosition);
			unePerspective.zoom = savedZoom;
		}
	}
}
