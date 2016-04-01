/******************************************************
Cours:  LOG121
Projet: Laboratoire 4
Nom du fichier: Coordonnee.java
Date créé: 2013-11-22
 *******************************************************
Historique des modifications
 *******************************************************
 *@author Shaun-David Sauro, Gabriel St-Hilaire, Frédéric Gascon
 *@date 2013-11-22
 *******************************************************/
package principale;

import java.awt.Point;

/**
 * Coordonnée cartésienne
 */
public class Coordonnee
{
	
	/**
	 * Position en X de la coodonnée.
	 */
	private int positionX;
	
	/**
	 * Position en Y de la coodonnée.
	 */
	private int positionY;
	
	/**
	 * Constructeur de la coordonnée.
	 */
	public Coordonnee(int positionX, int positionY){
		this.positionX = positionX;
		this.positionY = positionY;
	}
	
	/**
	 * Constructeur à partir d'un point
	 */
	public Coordonnee(Point point){
		this.positionX = (int) point.getX();
		this.positionY = (int) point.getY();
	}

	/**
	 * @return La position en X de la coordonnée
	 */
	public int getX() {
		return positionX;	
	}
	
	/**
	 * @return La position en Y de la coordonnée
	 */
	public int getY() {
		return positionY;	
	}
	
	/**
	 * Modifie la position en X de la coordonnée.
	 * @param value Nouvelle valeur en X
	 */
	public void setX(int value) {
		positionX = value;
	}
	
	/**
	 * Modifie la position en Y de la coordonnée.
	 * @param value Nouvelle valeur en Y
	 */
	public void setY(int value) {
		positionY = value;
	}
	
	/**
	 * Crée une nouvelle coordonnée identique à la coordonnée courante.
	 * @return Une copie de la coordonnée
	 */
	public Coordonnee copy() {
		return new Coordonnee(positionX, positionY);
	}
	
	/**
	 * Différence entre 2 coordonées
	 * @param otherPoint Autre point 
	 * @return Une Coordonnee représentant la différence entre les deux points
	 */
	public Coordonnee diff(Coordonnee otherPoint) {
		return new Coordonnee(getX() - otherPoint.getX(), getY() - otherPoint.getY());
	}
	
	public Coordonnee add(Coordonnee otherPoint) {
		return new Coordonnee(getX() + otherPoint.getX(), getY() + otherPoint.getY());
	}
	
	public String toString() {
		return getX()+","+getY();
	}
}