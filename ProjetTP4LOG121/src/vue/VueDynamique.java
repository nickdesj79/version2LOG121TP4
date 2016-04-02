/******************************************************
Cours:  LOG121
Projet: Laboratoire 4
Nom du fichier: PerspectiveView.java
Date créé: 2013-11-22
 *******************************************************
Historique des modifications
 *******************************************************
 *@author Shaun-David Sauro, Gabriel St-Hilaire, Frédéric Gascon
 *@date 2013-11-22
 *******************************************************/
package vue;

import java.awt.Graphics;
import java.util.Observable;

import modele.Perspective;
import principale.Coordonnee;


/**
 * 
 */
public class VueDynamique extends VueStatique
{
	
	private static final long serialVersionUID = 13L;

	private Coordonnee position;
	
	private float zoom = 1;
	
	/**
	 * 
	 * @param unePosX
	 * @param unePosY
	 * @param uneWidth
	 * @param uneHeight
	 */
	public VueDynamique(int unePosX, int unePosY, int uneWidth, int uneHeight) {
		super(unePosX, unePosY, uneWidth, uneHeight);
		position = new Coordonnee(0, 0);
	}
	
	/**
	 * 
	 */
	public void setPosition(Coordonnee position) {
		this.position = position;
		repaint();
	}
	
	/**
	 * 
	 */
	public void setZoom(float zoom) {
		this.zoom = zoom;
		repaint();
	}

	/**
	 * 
	 */
	@Override
	public void update(Observable observable, Object objet) {
		if(observable instanceof Perspective) {
			Perspective perspective = (Perspective) observable;
			setZoom(perspective.getZoom());
			setPosition(perspective.getPosition());
		} else {
			super.update(observable, objet);
		}
	}

	/**
	 * 
	 */
	@Override
	protected void drawImage(Graphics graphics) {
		
		graphics.drawImage(image, position.getX(), position.getY(), (int)(this.getWidth()*zoom), (int)(this.getHeight()*zoom), null);
	}
}