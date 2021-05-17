package zork;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.*;

public class ObjetZork
{
	/**
	*
	*/
	/*@
	  @invariant getNom() != null;
	  @invariant getPoids() > 0;
	  @invariant estTransportable() ==> (!(this instanceof(Aliment)) && getPoids() < 190);
	  @*/
	/**
	* @pure
	*/

	private String nom;
	private EffetObjetZork effet;
	private boolean transportable;
	private int poids;
	private Piece pieceCourante;
	public static final int max_poids_objet = 100;

	/**
	* Initialise un objet sans specifications
	*/
	public ObjetZork(){}

	/**
	* Initialise un ObjetZork avec le nom et effet spécifiés
	*
	* @param nom	Le nom de l'objet
	* @param effet 	L'effet de l'objet sur le joueur (boite magique, boite tuante, aliment neutre)
	*
	* @throws NullPointerException si le nom est null;
	* @throws NullPointerException si l'effet est null;
	* @throws NullPointerException si la piece est null;
	* @throws IllegalArgumentException si le poids est plus petit ou egal a 0
	*/
	/*@
	  @requires nom != null;
	  @requires effet != null;
	  @requires poids > 0;
	  @requires piece != null;
	  @ensures this.getNom().equals(nom);
	  @ensures this.getEffet().equals(effet);
	  @ensures this.getPoids() == poids;
	  @ensures this.getPieceCourante().equals(pieceCourante);
	  @ensures getPoids() < 215 ==> estTransportable() == true;
	  @ensures getPoids() >= 215 ==> estTransportable() == false;
	  @*/
	/**
	* @pure
	*/

	public ObjetZork(String nom, EffetObjetZork effet, int poids, Piece pieceCourante)
	{
		if(nom == null || effet == null || pieceCourante == null)
			throw new NullPointerException("Aucun argument ne doit valoir null");
		if(poids <= 0)
			throw new IllegalArgumentException("Le poids doit etre strictement superieur a zero");
		this.nom = nom;
		this.effet = effet;
		this.poids = poids;
		this.pieceCourante = pieceCourante;
	}

	/**
	* Initialise un objet avec le nom et la piece spécifiés. Ce constructeur servira pour les aliments
	*
	* @param nom 			Le nom de cet objet
	* @param pieceCourante 	La piece dans laquelle se trouvera cet objet
	*
	* @throws NullPointerException si le nom est null;
	* @throws NullPointerException si la piece est null
	*/
	/*@
	  @requires nom != null;
	  @requires pieceCourante != null;
	  @ensures this.nom.equals(nom);
	  @ensures this.pieceCourante.equals(pieceCourante);
	  @*/
	/**
	* @pure
	*/
	public ObjetZork(String nom, Piece pieceCourante)
	{
		if(nom == null || pieceCourante == null)
			throw new NullPointerException("Aucun argument ne doit valoir null");
		this.nom = nom;
		this.pieceCourante = pieceCourante;
		transportable = false;
		poids = 5;
		effet = EffetObjetZork.NEUTRE;
	}

	/**
	* 
	* @pure
	*/
	public String getNom()
	{
		return nom;
	}

	/**
	* 
	* @pure
	*/
	public EffetObjetZork getEffet()
	{
		return effet;
	}

	/**
	* 
	* @pure
	*/
	public int getPoids()
	{
		return poids;
	}

	/**
	* 
	* @pure
	*/
	public Piece getPieceCourante()
	{
		return pieceCourante;
	}

	/**
	* 
	* @pure
	*/
	public boolean estTransportable()
	{
		if(getPoids() <= max_poids_objet)
			return true;
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
	public void setPoids(int newPoids)
	{
		poids = newPoids;
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
	public void setEffet(EffetObjetZork e)
	{
		effet = e;
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
	public void setTransportable(boolean b)
	{
		transportable = b;
		return;
	}













}