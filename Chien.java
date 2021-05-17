package zork;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.*;

public class Chien extends AbstractAnimal implements Adoptable
{
	private boolean adoptable;
	private Joueur joueur; // son maitre s'il est adopté
	private Direction sortieProposee; // si le chien est adopté
	private Piece piece_a_quitter;

	/**
	* Initialise un chien avec le nom spécifié
	*
	* @param nom Le nom du chien
	*
	* @throws NullPointerException si le nom du chien est null
	*/
	/*@
	  @also
	  @ensures estLibre() == true;
	  @ensures getRegimeAlimentaire().size() == 2;
	  @ensures getRegimeAlimentaire().contains(VEGETAL);
	  @ensures getRegimeAlimentaire().contains(ANIMAL);
	  @ensures !estMangeable();
	  @*/
	/**
	* @pure
	*/
	public Chien(String nom)
	{
		super(nom);
		adoptable = true;
		addToRegimeAlimentaire(TypeAliment.ANIMAL);
		addToRegimeAlimentaire(TypeAliment.VEGETAL);
		setMangeable(false);
		setValeurNutritive(0);
	}

	/**
	* Initialise un chien avec le nom spécifié
	*
	* @param nom 		Le nom du chien
	* @param reserve 	La reserve d'aliments du chien
	*
	* @throws NullPointerException si le nom du chien est null
	*/
	/*@
	  @also
	  @ensures estLibre() == true;
	  @ensures getRegimeAlimentaire().size() == 2;
	  @ensures getRegimeAlimentaire().contains(VEGETAL);
	  @ensures getRegimeAlimentaire().contains(ANIMAL);
	  @ensures !estMangeable();
	  @*/
	/**
	* @pure
	*/
	public Chien(String nom, ArrayList<Aliment> reserve)
	{
		super(nom, reserve);
		adoptable = true;
		addToRegimeAlimentaire(TypeAliment.ANIMAL);
		addToRegimeAlimentaire(TypeAliment.VEGETAL);
		setMangeable(false);
		setValeurNutritive(0);
	}

	/**
	* Initialise un nouveau chien avec le nom spécifié dans la piece spécifiée
	*
	* @param nom 				Le nom du chien;
	* @param pieceCourante 		La piece dans laquelle on veut placer le chien;
	*
	* @throws NullPointerException si nom vaut null;
	* @throws NullPointerException si pieceCourante vaut null;
	*/
	/*@
	  @also
	  @ensures estLibre() == true;
	  @ensures getRegimeAlimentaire().size() == 2;
	  @ensures getRegimeAlimentaire().contains(VEGETAL);
	  @ensures getRegimeAlimentaire().contains(ANIMAL);
	  @ensures !estMangeable();
	  @*/
	/**
	* @pure
	*/
	public Chien(String nom, Piece pieceCourante)
	{
		super(nom, pieceCourante);
		adoptable = true;
		addToRegimeAlimentaire(TypeAliment.VEGETAL);
		addToRegimeAlimentaire(TypeAliment.ANIMAL);
		setMangeable(false);
		setValeurNutritive(0);
	}

	/* ======================================== METHODES De L INTERFACE ADOPTABLE ======================================== */
	
	public boolean estLibre()
	{
		if(adoptable)
			return true;
		return false;
	}

	public boolean adoption()
	{
		if(estLibre())
		{
			adoptable = false;
		}

		else
			return false;
		return adoptable;
	}

	public boolean liberation()
	{
		if(!estLibre())
			adoptable = true;
		return adoptable;
	}

	public void proposerSortie(Piece p, Direction d)
	{
		if(p == null || d == null)
			throw new NullPointerException("Vos arguments ne doivent pas etre null");
		if(p.pieceSuivante(d) == null)
			throw new IllegalArgumentException("La piece ne possede pas de sortie dans la direction donnée");
		
		/* Si les arguments ne sont pas nuls,
		s'il existe une piece dans la direction proposée
		et si la piece passée en argument correspond bien à la piece courante du chien
		*/
		if((p != null) && (d != null) && (p.pieceSuivante(d) != null) && p.equals(getPieceCourante()))
		{
			if(!estLibre())
			{
				piece_a_quitter = p;
				/* Si la direction passée en paramètre correspond à la sortie proposée par le joueur,
				on impose cette direction */
				if(d.equals(joueur.getFuturDplct()))
					sortieProposee = d;
				else
					sortieProposee = null; // Aucune sortie à proposer car le chien doit suivre son maitre
			}
			else
				sortieProposee = d;
		}
	}

	public Direction getSortieProposee(Piece p)
	{
		if(p == null)
			throw new NullPointerException("La piece ne doit pas etre null");
		/* si p correspond a la piece pour laquelle la sortie a été proposée par
		le joueur et que p correspond aussi a la piece courante */
		if(p.equals(piece_a_quitter) && p.equals(getPieceCourante()))
			return sortieProposee;
		return null;
	}

	public Direction choisirSortie(Piece p)
	{
			if(estLibre())
				return super.choisirSortie(p);
			if(!estLibre() && (p.equals(piece_a_quitter))) // Si le chien est adopte et que la piece correspond bien a celle pour laquelle une sortie a ete proposée par le joueur
				return getSortieProposee(p);
			return null;
	}

	public Piece deplacerDepuis(Piece p)
	{
		if(estVivant())
			return super.deplacerDepuis(p);
		return null;
	}

/* =============================================== METHODES AJOUTEES =============================================== */

	/**
	* Associe au chien le joueur passé en paramètre comme maître.
	* @param j Le maitre du chien
	*/
	/*@
	  @requires j != null;
	  @*/
	/**
	* @pure
	*/
	public void setJoueur(Joueur j)
	{
		joueur = j;
		return;
	}

	/**
	* Associe au chien la piece qu'il quittera prochainement. Cette fonction sera
	* appelée par le joueur quand il envisagera de se déplacer.
	*
	* @param p La piece à quitter
	*/
	/*@
	  @requires p != null;
	  @ensures getPieceAQuitter().equals(p);
	  @*/
	/**
	* @pure
	*/
	public void setPieceAQuitter(Piece p)
	{
		piece_a_quitter = p;
		return;
	}

	/**
	* Renvoie la piece que le chien quittera au prochain deplacement du joueur
	* @return La piece que le chien quittera au prochain deplacement du joueur
	* @pure
	*/
	public Piece getPieceAQuitter()
	{
		return piece_a_quitter;
	}

	/**
	* Renvoie le joueur maitre du chien
	* @return Le joueur qui a adopte le chien
	*
	* @pure
	*/
	public Joueur getJoueur()
	{
		return joueur;
	}
}

















