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



public class ImageModel extends Observable implements Modele {
	
	private Image image;
	
	private File imageFile;
	
	public ImageModel(){
		imageFile = null;
		image = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
	}
	
	/**
	 * 
	 * @param file le fichier à charger
	 * @throws IOException erreur durant le chargement de l'image
	 */
	public void charger(File file) throws IOException {
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
	
	public EtatDuModele sauvegarderEtat() {
		return new EtatImageModele();
	}
	
	public void restaurerEtat(EtatDuModele state) {
		if(state instanceof EtatImageModele) {
			EtatImageModele imageModelState = (EtatImageModele) state;
			imageModelState.restore();
			setChanged();
			notifyObservers();
		}
	}
	
	public String enregistrer() {
		return imageFile.getAbsolutePath();
	}
	
	public void enleverEnregistrement(String state) throws Exception {
		if(state == null) {
			charger(null);
		} else if(state.equals("null")) {
			charger(null);
		} else {
			charger(new File(state));
		}
	}
	
	private class EtatImageModele implements EtatDuModele {
		
		public File fichierSauvegarder;
		public Image imageSauvegarder;
		
		public EtatImageModele() {
			fichierSauvegarder = imageFile;
			imageSauvegarder = image;
		}
		
		public void restore() {
			imageFile = fichierSauvegarder;
			image = imageSauvegarder;
		}
	}
}