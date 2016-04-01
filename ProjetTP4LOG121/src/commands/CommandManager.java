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
package commands;

import java.util.Stack;


/**
 * 
 */
public class CommandManager
{
	
	private static final CommandManager instance = new CommandManager();
	
	/**
	 * 
	 */
	public static CommandManager getInstance() {
		return instance;
	}
	
	/**
	 * 
	 */
	private final Stack<Command> commandsDone = new Stack<Command>();
	
	/**
	 * 
	 */
	private final Stack<Command> commandsUndone = new Stack<Command>();
	
	/**
	 * 
	 */
	public CommandManager(){
		
	}
	
	public void execute(Command command) {
		command.execute();
		commandsDone.push(command);
	}
	
	/**
	 * 
	 */
	public boolean canUndo() {
		return !commandsDone.empty();
	}
	
	/**
	 * 
	 */
	public boolean canRedo() {
		return !commandsUndone.empty();
	}
	
	/**
	 * 
	 */
	public void undo() {
		if(!commandsDone.empty()) {
			Command lastCommand = commandsDone.pop();
			lastCommand.unexecute();
			commandsUndone.push(lastCommand);
		}
	}
	
	/**
	 * 
	 */
	public void redo() {
		if(!commandsUndone.empty()) {
			Command lastCommand = commandsUndone.pop();
			lastCommand.execute();
			commandsDone.push(lastCommand);
		}
	}
}