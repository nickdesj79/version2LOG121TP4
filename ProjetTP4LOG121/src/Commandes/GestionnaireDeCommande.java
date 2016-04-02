/******************************************************
Cours:  LOG121
Projet: Laboratoire 4
Nom du fichier: CommandManager.java
Date créé: 2013-11-22
 *******************************************************
Historique des modifications
 *******************************************************
 *@author Shaun-David Sauro, Gabriel St-Hilaire, Frédéric Gascon
 *@date 2013-11-22
 *******************************************************/
package Commandes;

import java.util.Stack;

public class GestionnaireDeCommande
{
	
	private static final GestionnaireDeCommande instance = new GestionnaireDeCommande();
	
	public static GestionnaireDeCommande getInstance() {
		return instance;
	}
	
	private final Stack<Commande> commandsDone = new Stack<Commande>();
	
	private final Stack<Commande> commandsUndone = new Stack<Commande>();
	
	public void execute(Commande command) {
		command.execute();
		commandsDone.push(command);
	}
	
	public boolean canUndo() {
		return !commandsDone.empty();
	}
	
	public boolean canRedo() {
		return !commandsUndone.empty();
	}
	
	public void undo() {
		if(!commandsDone.empty()) {
			Commande lastCommand = commandsDone.pop();
			lastCommand.unexecute();
			commandsUndone.push(lastCommand);
		}
	}
	
	public void redo() {
		if(!commandsUndone.empty()) {
			Commande lastCommand = commandsUndone.pop();
			lastCommand.execute();
			commandsDone.push(lastCommand);
		}
	}
}