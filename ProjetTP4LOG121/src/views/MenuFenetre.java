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

import principale.Controller;
import principale.LangueConfig;


/**
 * Crée le menu de la fenêtre de l'application.
 */
public class MenuFenetre extends JMenuBar {
	
	private static final long serialVersionUID = 1536336192561843187L;
	
	//private static final int DELAI_QUITTER_MSEC = 200;
	
	private final Controller controller;
	
	/**
	 * Constructeur
	 */
	public MenuFenetre(Controller controller) {
		this.controller = controller;
		addMenuFichier();
		addMenuEdit();
		addMenuAide();
	}
	
	/**
	 * Créer le menu "Fichier".
	 */
	protected void addMenuFichier() {
		
		final JFileChooser dialog = new JFileChooser();
		
		Dropdown dropdown = new Dropdown("app.frame.menus.file");
		add(dropdown.getMenu());
		
		dropdown.addItem(new MenuItem("charge") {
			
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
						JOptionPane.showMessageDialog(MenuFenetre.this.getParent(), "Failed to open file.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		dropdown.addItem(new MenuItem("import") {
			
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
						JOptionPane.showMessageDialog(MenuFenetre.this.getParent(), "Failed to open file.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		dropdown.addItem(new MenuItem("save") {
			
			public KeyStroke shortcut() {
				return KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK);
			}
			
			public void actionPerformed(ActionEvent event) {
				int result = dialog.showSaveDialog(MenuFenetre.this.getParent());
				if(result == JFileChooser.APPROVE_OPTION) {
					try {
						controller.save(dialog.getSelectedFile());
					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(MenuFenetre.this.getParent(), "Failed to save file.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		dropdown.addItem(new MenuItem("quitter") {
			
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
		Dropdown dropdown = new Dropdown("app.frame.menus.edit");
		add(dropdown.getMenu());
		
		dropdown.addItem(new MenuItem("defaire") {
			
			public KeyStroke shortcut() {
				return KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK);
			}
			
			public void actionPerformed(ActionEvent event) {
				controller.undo();
			}
		});
		
		dropdown.addItem(new MenuItem("refaire") {
			
			public KeyStroke shortcut() {
				return KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK);
			}
			
			public void actionPerformed(ActionEvent event) {
				controller.redo();
			}
		});
	}
	
	/**
	 * Créer le menu "Help".
	 */
	private void addMenuAide() {
		Dropdown dropdown = new Dropdown("app.frame.menus.help");
		add(dropdown.getMenu());
		
		dropdown.addItem(new MenuItem("about") {
			
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(null,
						LangueConfig.getResource("app.frame.dialog.about"),
						LangueConfig.getResource("app.frame.menus.help.about"),
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}
	
	private class Dropdown {
		
		private JMenu menu;
		private String menuName;
		
		public Dropdown(String name) {
			menuName = name + ".";
			menu = new JMenu(LangueConfig.getResource(menuName + "title"));
		}
		
		public void addItem(MenuItem itemData) {
			String text = LangueConfig.getResource(menuName + itemData.getName());
			JMenuItem menuItem = new JMenuItem(text);
			menuItem.addActionListener(itemData);
			KeyStroke shortcut = itemData.shortcut();
			if(shortcut != null) {
				menuItem.setAccelerator(shortcut);
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