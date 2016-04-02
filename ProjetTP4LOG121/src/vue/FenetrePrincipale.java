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
package vue;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import principale.Controlleur;


/**
 * 
 */
public class FenetrePrincipale extends JFrame
{
	
	private static final long serialVersionUID = 11L;
	
	private static final int WIDTH = 1200;
	private static final int HEIGHT = 800;
	
	private MenuFenetre menu;
	private JPanel contenant;
	
	public FenetrePrincipale(Controlleur controller){
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT);
		this.setLayout(new BorderLayout());
		
		
		menu = new MenuFenetre(controller);
		this.add(menu, BorderLayout.NORTH);
		
		contenant = new JPanel();
		contenant.setLayout(null);
		this.add(contenant, BorderLayout.CENTER);
	}
	
	public void addToContainer(JComponent view) {
		contenant.add(view);
	}
}