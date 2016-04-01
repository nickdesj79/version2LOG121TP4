/******************************************************
Cours:  LOG121
Projet: Laboratoire 4
Nom du fichier: Coordonnee.java
Date cr��: 2013-11-22
 *******************************************************
Historique des modifications
 *******************************************************
 *@author Shaun-David Sauro, Gabriel St-Hilaire, Fr�d�ric Gascon
 *@date 2013-11-22
 *******************************************************/
package principale;

import java.awt.Point;

/**
 * Coordonn�e cart�sienne
 */
public class Coordonnee
{
	
	/**
	 * Position en X de la coodonn�e.
	 */
	private int positionX;
	
	/**
	 * Position en Y de la coodonn�e.
	 */
	private int positionY;
	
	/**
	 * Constructeur de la coordonn�e.
	 */
	public Coordonnee(int positionX, int positionY){
		this.positionX = positionX;
		this.positionY = positionY;
	}
	
	/**
	 * Constructeur � partir d'un point
	 */
	public Coordonnee(Point point){
		this.positionX = (int) point.getX();
		this.positionY = (int) point.getY();
	}

	/**
	 * @return La position en X de la coordonn�e
	 */
	public int getX() {
		return positionX;	
	}
	
	/**
	 * @return La position en Y de la coordonn�e
	 */
	public int getY() {
		return positionY;	
	}
	
	/**
	 * Modifie la position en X de la coordonn�e.
	 * @param value Nouvelle valeur en X
	 */
	public void setX(int value) {
		positionX = value;
	}
	
	/**
	 * Modifie la position en Y de la coordonn�e.
	 * @param value Nouvelle valeur en Y
	 */
	public void setY(int value) {
		positionY = value;
	}
	
	/**
	 * Cr�e une nouvelle coordonn�e identique � la coordonn�e courante.
	 * @return Une copie de la coordonn�e
	 */
	public Coordonnee copy() {
		return new Coordonnee(positionX, positionY);
	}
	
	/**
	 * Diff�rence entre 2 coordon�es
	 * @param otherPoint Autre point 
	 * @return Une Coordonnee repr�sentant la diff�rence entre les deux points
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