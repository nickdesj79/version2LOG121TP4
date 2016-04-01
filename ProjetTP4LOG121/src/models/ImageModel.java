/******************************************************
Cours:  LOG121
Projet: Laboratoire 4
Nom du fichier: ImageModel.java
Date créé: 2013-11-22
 *******************************************************
Historique des modifications
 *******************************************************
 *@author Shaun-David Sauro, Gabriel St-Hilaire, Frédéric Gascon
 *@date 2013-11-22
 *******************************************************/
package models;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;

import javax.imageio.ImageIO;



/**
 * 
 */
public class ImageModel extends Observable implements Model {
	
	/**
	 * 
	 */
	private Image image;
	
	/**
	 * 
	 */
	private File imageFile;
	
	/**
	 * 
	 */
	public ImageModel(){
		imageFile = null;
		image = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
	}
	
	/**
	 * 
	 * @param file le fichier à charger
	 * @throws IOException erreur durant le chargement de l'image
	 */
	public void load(File file) throws IOException {
		imageFile = file;
		if(imageFile == null) {
			image = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
		} else {
			image = ImageIO.read(file);
		}
		setChanged();
		notifyObservers();
	}
	
	/**
	 * 
	 * @return
	 */
	public Image getImage() {
		return image;
	}
	
	public ModelState saveState() {
		return new ImageModelState();
	}
	
	public void restoreState(ModelState state) {
		if(state instanceof ImageModelState) {
			ImageModelState imageModelState = (ImageModelState) state;
			imageModelState.restore();
			setChanged();
			notifyObservers();
		}
	}
	
	public String serialize() {
		return imageFile.getAbsolutePath();
	}
	
	public void unserialize(String state) throws Exception {
		if(state == null) {
			load(null);
		} else if(state.equals("null")) {
			load(null);
		} else {
			load(new File(state));
		}
	}
	
	private class ImageModelState implements ModelState {
		
		public File savedFile;
		public Image savedImage;
		
		public ImageModelState() {
			savedFile = imageFile;
			savedImage = image;
		}
		
		public void restore() {
			imageFile = savedFile;
			image = savedImage;
		}
	}
}