/******************************************************
Cours:  LOG121
Projet: Laboratoire 4
Nom du fichier: Controller.java
Date cr��: 2013-11-22
 *******************************************************
Historique des modifications
 *******************************************************
 *@author Shaun-David Sauro, Gabriel St-Hilaire, Fr�d�ric Gascon
 *@date 2013-11-22
 *******************************************************/
package principale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import commands.*;
import models.*;
import views.ImageView;
import views.PerspectiveView;


/**
 * 
 */
public class Controller
{
	
	/**
	 * 
	 */
	private final ImageModel imageModel;
	
	/**
	 * 
	 */
	private final Perspective perspective;
	
	/**
	 * Constructeur du controlleur
	 * @param nbPerspective Le nombre de perspective qu'il y aura dans l'application
	 */
	public Controller(int nbPerspective){
		imageModel = new ImageModel();
		perspective = new Perspective();
	}
	
	/**
	 * Ajoute un Observer a une vue
	 * @param view La vue � ajouter l'observer
	 * */
	public void observeImage(ImageView view) {
		imageModel.addObserver(view);
	}
	
	/**
	 * Ajoute un Observer a une perspective et les listeners appropri�s
	 * @param view La vue � ajouter l'observer
	 * */
	public void observePerspective(PerspectiveView view) {
		perspective.addObserver(view);
		MouseControl mouseControl = new MouseControl(perspective);
		view.addMouseListener(mouseControl);
		view.addMouseMotionListener(mouseControl);
		view.addMouseWheelListener(mouseControl);
	}
	
	/**
	 * M�thode permettant de charger l'image dans l'application
	 * @param L'image a charger
	 */
	public void loadImage(File file) throws IOException {
		imageModel.load(file);
	}
	
	/**
	 * Sert a canceller la commande fait pr�c�demment
	 */
	public void undo() {
		CommandManager commandManager = CommandManager.getInstance();
		if(commandManager.canUndo()) {
			commandManager.undo();
		}
	}
	
	/**
	 * Sert a refaire la commance cancell�e pr�c�demment
	 */
	public void redo() {
		CommandManager commandManager = CommandManager.getInstance();
		if(commandManager.canRedo()) {
			commandManager.redo();
		}
	}
	
	/**
	 * Copy les param�tres de la photo dans le ClipBoard
	 */
	public void copy() {
		CopyCommand copy = new CopyCommand(perspective);
		CommandManager.getInstance().execute(copy);
	}
	
	/**
	 * Colle les param�tres de la photo dans le ClipBoard
	 */
	public void paste() {
		PasteCommand paste = new PasteCommand(perspective);
		CommandManager.getInstance().execute(paste);
	}
	
	public void pasteEchelle() {
		PasteEchelle pasteEchelle = new PasteEchelle(perspective);
		CommandManager.getInstance().execute(pasteEchelle);
	}
	
	/**
	 * Permet d'�ffectuer la transtlation de la photo
	 */
	public void translate(Coordonnee distance) {
		TranslationCommand translation = new TranslationCommand(perspective, distance);
		CommandManager commandManager = CommandManager.getInstance();
		commandManager.execute(translation);
	}
	
	/**
	 * Permet de Zoomer sur la photo
	 */
	public void zoom(float unZoom) {
		//Perspective perspective = perspectives.get(perspectiveIndex);
		ZoomCommand zoom = new ZoomCommand(perspective, unZoom);
		CommandManager commandManager = CommandManager.getInstance();
		commandManager.execute(zoom);
	}
	
	/**
	 * Permet de sauvegarder l'�tat de la photo pour une utilisation ult�rieure
	 * @throws IOException 
	 */
	public void save(File file) throws IOException {
		String content = imageModel.serialize();
		content += ";" + perspective.serialize();
		FileAccess.writeFile(file, content);
	}
	
	/**
	 * Sert a ouvrir une Photo
	 * @param file Photo a ouvrire
	 * @throws Exception 
	 */
	public void open(File file) throws Exception {
		String content = FileAccess.readFile(file);
		String[] contentParts = content.split(";");
		if(contentParts.length != 2) {
			throw new Exception("Invalid file format");
		}
		imageModel.unserialize(contentParts[0]);
		perspective.unserialize(contentParts[1]);
	}
	
	private class MouseControl extends MouseAdapter {
		
		private Perspective perspective;
		
		private Coordonnee translationBegin;
		
		public MouseControl(Perspective perspective) {
			this.perspective = perspective;
		}
		
		public void mouseClicked(MouseEvent event) {
			
			if (event.getButton() == 3){
				JPopupMenu popMenu = new JPopupMenu();
				JMenuItem zoom = new JMenuItem("Zoom 10%");
				
				zoom.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						zoom(1.1f);
					}
				});
				popMenu.add(zoom);
				
				JMenuItem unZoom = new JMenuItem("Unzoom 10%");
				unZoom.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						zoom(1/1.1f);
					}
				});
				popMenu.add(unZoom);
				
				JMenuItem copy = new JMenuItem("Copy");
				copy.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						copy();
					}
				});
				popMenu.add(copy);
				
				JMenuItem paste = new JMenuItem("Paste");
				paste.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						paste();
					}
				});
				popMenu.add(paste);
				
				JMenuItem pasteFacteurEchelle = new JMenuItem("Coller Facteur d'�chelle");
				pasteFacteurEchelle.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						pasteEchelle();
					}
				});
				popMenu.add(pasteFacteurEchelle);
				
				JMenuItem pasteTranslation = new JMenuItem("Coller Translation");
				pasteTranslation.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						pasteEchelle();
					}
				});
				popMenu.add(pasteTranslation);
				
				popMenu.show(event.getComponent(), event.getX(), event.getY());
				
								
			}
		}
		
		
		public void mouseReleased(MouseEvent event) {
			if(translationBegin != null) {
				Coordonnee translationEnd = new Coordonnee(event.getPoint());
				Coordonnee dragDistance = translationEnd.diff(translationBegin);
				if(dragDistance.getX() != 0 && dragDistance.getY() != 0) {
					translate(dragDistance);
				}
				translationBegin = null;
			}
		}
		
		public void mousePressed(MouseEvent event) {
			translationBegin = new Coordonnee(event.getPoint());
		}
		
		public void mouseDragged(MouseEvent event) {
			if(event.getSource() instanceof PerspectiveView) {
				PerspectiveView view = (PerspectiveView) event.getSource();
				if(translationBegin != null) {
					Coordonnee currentPosition = new Coordonnee(event.getPoint());
					Coordonnee dragDistance = currentPosition.diff(translationBegin);
					view.setPosition(perspective.getPosition().add(dragDistance));
				}
			}
		}
		
		/**
		 * Lorsque la molette de la souris vas vers l'avant l'image s'aggrandit
		 * Vers l'arri�re elle diminue
		 */
		public void mouseWheelMoved(MouseWheelEvent event) {
			 int rotation = event.getWheelRotation();
			 
			 if (rotation < 0)
				 zoom(1f/0.9f);
			 if (rotation > 0)
				 zoom(0.9f);
		}
	}
}