package zork;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.*;

public class Chevre extends AbstractAnimal
{
	/**
	* Initialise une chevre avec le nom spécifié
	*
	* @param nom Le nom de la chevre
	*
	* @throws NullPointerException si le nom de la chevre est null
	*/
	/*@
	  @also
	  @ensures getRegimeAlimentaire().size() == 1;
	  @ensures getRegimeAlimentaire().contains(VEGETAL);
	  @ensures estMangeable();
	  @*/
	/**
	* @pure
	*/
	public Chevre(String nom)
	{
		super(nom);
		addToRegimeAlimentaire(TypeAliment.VEGETAL);
		setMangeable(true);
		setValeurNutritive(7);
	}

	/**
	* Initialise une chevre avec le nom spécifié
	*
	* @param nom 		Le nom de la chevre
	* @param reserve 	La reserve d'aliments de la chevre
	*
	* @throws NullPointerException si le nom de la chevre est null
	*/
	/*@
	  @also
	  @ensures getRegimeAlimentaire().size() == 1;
	  @ensures getRegimeAlimentaire().contains(VEGETAL);
	  @ensures estMangeable();
	  @*/
	/**
	* @pure
	*/
	public Chevre(String nom, ArrayList<Aliment> reserve)
	{
		super(nom, reserve);
		addToRegimeAlimentaire(TypeAliment.VEGETAL);
		setMangeable(true);
		setValeurNutritive(1);
	}

	/**
	* Initialise une nouvelle chevre avec le nom spécifié dans la piece spécifiée
	*
	* @param nom 				Le nom de la chevre;
	* @param pieceCourante 		La piece dans laquelle on veut placer la chevre;
	*
	* @throws NullPointerException si nom vaut null;
	* @throws NullPointerException si pieceCourante vaut null;
	*/
	/*@
	  @also
	  @ensures getRegimeAlimentaire().size() == 1;
	  @ensures getRegimeAlimentaire().contains(VEGETAL);
	  @ensures estMangeable();
	  @*/
	/**
	* @pure
	*/
	public Chevre(String nom, Piece pieceCourante)
	{
		super(nom, pieceCourante);
		addToRegimeAlimentaire(TypeAliment.VEGETAL);
		setMangeable(true);
		setValeurNutritive(1);
	}

}