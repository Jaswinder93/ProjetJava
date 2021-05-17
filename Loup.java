package zork;


import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.*;
import java.lang.Object;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.concurrent.CopyOnWriteArraySet;

public class Loup extends AbstractAnimal implements Chasseur
{

	ArrayList<Animal> animauxChasses = new ArrayList<Animal>();
	/**
	* Initialise un loup avec le nom spécifié
	*
	* @param nom Le nom du loup
	*
	* @throws NullPointerException si le nom du loup est null
	*/
	/*@
	  @also
	  @ensures getRegimeAlimentaire().size() == 1;
	  @ensures getRegimeAlimentaire().contains(ANIMAL);
	  @ensures !estMangeable();
	  @*/
	/**
	* @pure
	*/
	public Loup(String nom)
	{
		super(nom);
		addToRegimeAlimentaire(TypeAliment.ANIMAL);
		setMangeable(false);
		setValeurNutritive(0);
	}

	/**
	* Initialise un loup avec le nom spécifié
	*
	* @param nom 		Le nom du loup
	* @param reserve 	La reserve d'aliments du loup
	*
	* @throws NullPointerException si le nom du loup est null
	*/
	/*@
	  @also
	  @ensures getRegimeAlimentaire().size() == 1;
	  @ensures getRegimeAlimentaire().contains(ANIMAL);
	  @ensures !estMangeable();
	  @*/
	/**
	* @pure
	*/
	public Loup(String nom, ArrayList<Aliment> reserve)
	{
		super(nom, reserve);
		addToRegimeAlimentaire(TypeAliment.ANIMAL);
		setMangeable(false);
		setValeurNutritive(0);
	}

	/**
	* Initialise un nouveau loup avec le nom spécifié dans la piece spécifiée
	*
	* @param nom 				Le nom du loup;
	* @param pieceCourante 		La piece dans laquelle on veut placer le loup;
	*
	* @throws NullPointerException si nom vaut null;
	* @throws NullPointerException si pieceCourante vaut null;
	*/
	/*@
	  @also
	  @ensures getRegimeAlimentaire().size() == 1;
	  @ensures getRegimeAlimentaire().contains(ANIMAL);
	  @ensures !estMangeable();
	  @*/
	/**
	* @pure
	*/
	public Loup(String nom, Piece pieceCourante)
	{
		super(nom, pieceCourante);
		addToRegimeAlimentaire(TypeAliment.ANIMAL);
		setMangeable(false);
		setValeurNutritive(0);
	}

	public Animal choisirProie(Piece p)
	{
		if(p.equals(getPieceCourante()))
		{
			for(Animal an : p.getAnimaux())
			{
				if(peutChasser(an))
					return an;
			}
		}
		return null;
	}

	public Set<Class<? extends Animal>> getRegimeAlimentaireChasse()
	{
		HashSet<Class<? extends Animal>> regimeAlimentaireChasse = new HashSet<Class<? extends Animal>>();
		regimeAlimentaireChasse.add(Chevre.class);
		return regimeAlimentaireChasse;
	}

	public boolean peutChasser(Animal ani)
	{
		if(ani == null || !(getRegimeAlimentaireChasse().contains(ani.getClass())))
			return false;
		return true;
	}

	public Animal chasser(Piece p)
	{
		if(p == null)
			throw new NullPointerException("La piece ne doit pas etre null\n");
		if(!estVivant())
			throw new IllegalStateException("L'animal doit etre vivant\n");
		if(estVivant() && p.equals(getPieceCourante()))
		{
			Animal victime = choisirProie(p);
			if(victime != null)
			{
				if(victime.estVivant())
				{
					victime.mourirDans(p);
					setMinusCapitalVie(getCoutChasse());
					animauxChasses.add(victime);
					System.out.println("Le loup "+getNom()+" vient de chasser la chevre "+victime.getNom());
					if(getCapitalVie() <= 0)
					{
						mourirDans(p);
						return victime;
					}
				}
				else
				{
					if(!(animauxChasses.contains(victime)))
					{
						animauxChasses.add(victime);
						System.out.println("Le loup "+getNom()+" vient de chasser la chevre "+victime.getNom());
					}
				}
				setPlusCapitalVie(victime.getValeurNutritive());
			}
			return victime;
		}
		return null;
	}

	public int getCoutChasse()
	{
		return (2 * getCoutDeplacement());
	}

}









