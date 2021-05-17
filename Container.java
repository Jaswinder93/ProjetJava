package zork;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.*;


public abstract class Container<ObjetZork>
{
	private ArrayList<ObjetZork> contenu = new ArrayList<ObjetZork>();
	private int nbObjets;

/* =================================CONSTRUCTEURS====================================*/
	/**
	* Initialise un conteneur sans specificites
	*/
	/*@
	  @ensures getContenu().isEmpty();
	  @ensures getNbObjets() == 0;
	  @*/
	/**
	* @pure
	*/
	public Container()
	{
		contenu = new ArrayList<ObjetZork>();
		nbObjets = 0;
	}

	/**
	* Initialise un conteneur avec la liste d'objets specifiee.
	*
	* @param lesObjets La liste d'objets que le conteneur contiendra
	*
	* @throws NullPointerException si la liste d'objets est null
	*/
	/*@
	  @requires lesObjets != null;
	  @ensures (\for all ObjetZork oz : lesObjets ; this.getContenu().contains(oz));
	  @ensures getContenu().size() == lesObjets.size();
	  @ensures nbObjets == 0;
	  @*/
	/**
	* @pure
	*/
	public Container(ArrayList<ObjetZork> lesObjets)
	{
		contenu = new ArrayList<ObjetZork>();
		contenu.addAll(lesObjets);
		nbObjets = 0;
	}

/* =================================GETTERS/SETTERS====================================*/

	/**
	* Renvoie le contenu porté par l'instance de cette classe
	*
	* @return Le contenu d'ObjetZork de cette instance
	* @pure
	*/
	public ArrayList<ObjetZork> getContenu()
	{
		return contenu;
	}

	/**
	* Cette methode nous donne l'information du nombre d'objets contenus dans la reserve de cette instance
	*
	* @return Le nombre d'objets contenus dans l'inventaire de cette instance
	* @pure
	*/
	public int getNbObjets()
	{
		return nbObjets;
	}

	/**
	* Permet d'additionner le nombre d'objets contenus dans l'inventaire de l'instance au nombre passe en parametre
	*
	* @param newNbObjets Le nombre d'bjets à ajouter dans la liste d'objets de l'instance de cette classe
	*/
	/*@
	  @requires newNbObjets >= 0;
	  @*/
	/**
	* @pure
	*/
	public void setPlusNbObjets(int newNbObjets)
	{
		
		nbObjets+=newNbObjets;
		return;
	}

	/**
	* 
	*/
	/*@
	  @
	  @*/
	/**
	* @pure
	*/
	public void setMinusNbObjets(int newNbObjets)
	{
		nbObjets-=newNbObjets;
		return;
	}



/* =================================METHODES====================================*/

	/**
	* 
	*/
	/*@
	  @
	  @*/
	/**
	* @pure
	*/
	public boolean contient(ObjetZork oz)
	{
		return contenu.contains(oz);
	}

	/**
	* 
	*/
	/*@
	  @
	  @*/
	/**
	* @pure
	*/
	public void ajouter(ObjetZork oz)
	{
		if(ajoutPossible(oz))
			contenu.add(oz);
	}

	/**
	* 
	*/
	/*@
	  @
	  @*/
	/**
	* @pure
	*/
	public boolean retirer(ObjetZork oz)
	{
		if(retraitPossible(oz))
		{
			return contenu.remove(oz);
		}
		return false;
	}

	/**
	* 
	*/
	/*@
	  @
	  @*/
	/**
	* @pure
	*/
	abstract boolean retraitPossible(ObjetZork oz);
	

	/**
	* 
	*/
	/*@
	  @
	  @*/
	/**
	* @pure
	*/abstract boolean ajoutPossible(ObjetZork oz);
	

	abstract void afficherObjets();















}