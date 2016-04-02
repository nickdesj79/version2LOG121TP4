/******************************************************
Cours:  LOG121
Projet: Laboratoire 4
Nom du fichier: Model.java
Date cr��: 2013-11-22
 *******************************************************
Historique des modifications
 *******************************************************
 *@author Shaun-David Sauro, Gabriel St-Hilaire, Fr�d�ric Gascon
 *@date 2013-11-22
 *******************************************************/
package modele;

public interface Modele {
	
	
	public String enregistrer();
	public void restaurerEtat(EtatDuModele state);
	public EtatDuModele sauvegarderEtat();
	public void enleverEnregistrement(String state) throws Exception;
}