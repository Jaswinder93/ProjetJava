package zork;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.*;

public class Aliment extends ObjetZork
{
	/**
	*
	*/
	/*@
	  @invariant getValeur() > 0;
	  @invariant getTransp() == false;
	  @invariant getPoids() == 5;
	  @*/
	/**
	* @pure
	*/

	private int valeur;
	private TypeAliment type;

	public Aliment()
	{
		super();
		valeur = 1;
		setPoids(200);
		setEffet(EffetObjetZork.NEUTRE);
		setTransportable(false);
	}

	/**
	* Initialise un aliment avec le nom, la piece, a valeur et le type mentionnes
	*
	* @param nom 			Le nom de l'aliment
	* @param pieceCourante 	La piece ou se trouve l'aliment a sa creaiton
	* @param valeur 		La valeur nutritive de l'aliment
	* @param type 			Le type de cet aliment
	*
	* @throws NullPointerException si le nom vaut null;
	* @throws NullPointerException si la piece vaut null;
	* @throws NullPointerException si le type vaut null;
	* @throws IllegalArgumentException si la valeur nutrituve est inferieur ou egale a zero
	*/
	/*@
	  @requires nom != null;
	  @requires pieceCourante != null;
	  @requires valeur > 0;
	  @requires type != null;
	  @ensures this.getNom().equals(nom);
	  @ensures this.getPieceCourante().equals(pieceCourante);
	  @ensures this.getValeur() == valeur;
	  @ensures this.getType().equals(type);
	  @*/
	/**
	* @pure
	*/
	public Aliment(String nom, Piece pieceCourante, int valeur, TypeAliment type)
	{
		super(nom, pieceCourante);
		if(type == null)
			throw new NullPointerException("Aucun argument ne doit valoir null");
		if(valeur <= 0)
			throw new IllegalArgumentException("La valeur nutritive de l'aliment doit etre superieure a zero");
		this.valeur = valeur;
		this.type = type;
	}

	/**
	* 
	* @pure
	*/
	public int getValeurNutritive()
	{
		return valeur;
	}

	/**
	* 
	* @pure
	*/
	public TypeAliment getType()
	{
		return type;
	}









}