/******************************************************
Cours:  LOG121
Projet: Laboratoire 4
Nom du fichier: ImagineView.java
Date créé: 2013-11-22
 *******************************************************
Historique des modifications
 *******************************************************
 *@author Shaun-David Sauro, Gabriel St-Hilaire, Frédéric Gascon
 *@date 2013-11-22
 *******************************************************/
package vue;

import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import modele.ImageModel;

import java.awt.*;

@SuppressWarnings("serial")
public class VueStatique extends JPanel implements Observer {
	
	
	protected Image image;
	
	public VueStatique(int position_x, int position_y, int width, int height) {
		
		this.setLayout(null);
		this.setBounds(position_x, position_y, width, height);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		this.setBackground(new Color(220,220,220));
	}
	
	public void setImage(Image image) {
		this.image = image;
		repaint();
	}
	
	public void update(Observable observable, Object objet) {
		if(observable instanceof ImageModel) {
			ImageModel imageModel = (ImageModel) observable;
			setImage(imageModel.getImage());
		}
	}
	
	protected void drawImage(Graphics graphics) {
		graphics.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		drawImage(graphics);
	}
}