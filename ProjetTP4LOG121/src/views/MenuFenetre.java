package views;
/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: MenuFenetre.java
Date crÃ©Ã©: 2013-05-03
 *******************************************************
Historique des modifications
 *******************************************************
 *@author Patrice Boucher
2013-05-03 Version initiale
 *******************************************************/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import principale.Controlleur;


/**
 * Crée le menu de la fenêtre de l'application.
 */
@SuppressWarnings("serial")
public class MenuFenetre extends JMenuBar {
	
	
	private final Controlleur controller;
	
	/**
	 * Constructeur
	 */
	public MenuFenetre(Controlleur controller) {
		this.controller = controller;
		addMenuFichier();
		addMenuEdit();
	}
	
	/**
	 * Créer le menu "Fichier".
	 */
	protected void addMenuFichier() {
		
		final JFileChooser dialog = new JFileChooser();
		
		Dropdown dropdown = new Dropdown("Fichier");
		add(dropdown.getMenu());
		
		dropdown.addItem(new MenuItem("Load image") {
			
			public KeyStroke shortcut() {
				return KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK);
			}
			
			public void actionPerformed(ActionEvent event) {
				int result = dialog.showOpenDialog(MenuFenetre.this.getParent());
				if(result == JFileChooser.APPROVE_OPTION) {
					try {
						controller.loadImage(dialog.getSelectedFile());
					} catch (Exception exception) {
						exception.printStackTrace();
					}
				}
			}
		});
		
		dropdown.addItem(new MenuItem("Importer") {
			
			public KeyStroke shortcut() {
				return KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK);
			}
			
			public void actionPerformed(ActionEvent event) {
				int result = dialog.showOpenDialog(MenuFenetre.this.getParent());
				if(result == JFileChooser.APPROVE_OPTION) {
					try {
						controller.open(dialog.getSelectedFile());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		dropdown.addItem(new MenuItem("Sauvegarder") {
			
			public KeyStroke shortcut() {
				return KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK);
			}
			
			public void actionPerformed(ActionEvent event) {
				int result = dialog.showSaveDialog(MenuFenetre.this.getParent());
				if(result == JFileChooser.APPROVE_OPTION) {
					try {
						controller.sauvegarder(dialog.getSelectedFile());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		dropdown.addItem(new MenuItem("Quitter") {
			
			public KeyStroke shortcut() {
				return KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK);
			}
			
			public void actionPerformed(ActionEvent event) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.exit(0);
			}
		});
	}
	
	/**
	 * Créer le menu "Edit".
	 */
	protected void addMenuEdit() {
		Dropdown dropdown = new Dropdown("Editer");
		add(dropdown.getMenu());
		
		dropdown.addItem(new MenuItem("Undo") {
			
			public KeyStroke shortcut() {
				return KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK);
			}
			
			public void actionPerformed(ActionEvent event) {
				controller.undo();
			}
		});
		
		dropdown.addItem(new MenuItem("Redo") {
			
			public KeyStroke shortcut() {
				return KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK);
			}
			
			public void actionPerformed(ActionEvent event) {
				controller.redo();
			}
		});
	}
	
	private class Dropdown {
		
		private JMenu menu;
		private String nom_menu;
		
		public Dropdown(String name) {
			this.nom_menu = name;
			menu = new JMenu(name);
		}
		
		public void addItem(MenuItem itemData) {
			JMenuItem menuItem = new JMenuItem(itemData.getName());
			menuItem.addActionListener(itemData);
			KeyStroke raccourcis = itemData.shortcut();
			if(raccourcis != null) {
				menuItem.setAccelerator(raccourcis);
			}
			menu.add(menuItem);
		}
		
		public JMenu getMenu() {
			return menu;
		}
	}
	
	private abstract class MenuItem implements ActionListener {
		
		private String name;
		
		public MenuItem(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
		
		public KeyStroke shortcut() {
			return null;
		}
	}
}