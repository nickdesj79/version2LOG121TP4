/******************************************************
Cours:  LOG121
Projet: Laboratoire 4
Nom du fichier: Model.java
Date créé: 2013-11-22
 *******************************************************
Historique des modifications
 *******************************************************
 *@author Shaun-David Sauro, Gabriel St-Hilaire, Frédéric Gascon
 *@date 2013-11-22
 *******************************************************/
package models;

public interface Model {
	
	public ModelState saveState();
	
	public void restoreState(ModelState state);
	
	public String serialize();
	
	public void unserialize(String state) throws Exception;
}