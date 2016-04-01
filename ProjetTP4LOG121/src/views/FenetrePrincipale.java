/******************************************************
Cours:  LOG121
Projet: Laboratoire 4
Nom du fichier: FenetrePrincipale.java
Date créé: 2013-11-22
 *******************************************************
Historique des modifications
 *******************************************************
 *@author Shaun-David Sauro, Gabriel St-Hilaire, Frédéric Gascon
 *@date 2013-11-22
 *******************************************************/
package views;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import principale.Controller;


/**
 * 
 */
public class FenetrePrincipale extends JFrame
{
	
	private static final long serialVersionUID = 11L;
	
	private static final int DEFAULT_WIDTH = 750;
	private static final int DEFAULT_HEIGHT = 500;
	
	private MenuFenetre menu;
	private JPanel viewsContainer;
	
	public FenetrePrincipale(Controller controller){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Default Frame");
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setLayout(new BorderLayout());
		
		menu = new MenuFenetre(controller);
		this.add(menu, BorderLayout.NORTH);
		
		viewsContainer = new JPanel();
		viewsContainer.setLayout(null);
		this.add(viewsContainer, BorderLayout.CENTER);
	}
	
	public void addToContainer(JComponent view) {
		viewsContainer.add(view);
	}
}