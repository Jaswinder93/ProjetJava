package zork;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.lang.Math; 
import java.util.*;
import java.lang.Object;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.concurrent.CopyOnWriteArraySet;


public abstract class AbstractAnimal implements Animal
{
	/*@
	  @invariant getNom() != null;
	  @invariant getPieceCourante() != null;
	  @invariant getNbAliments() >= 0;
	  @invariant getValeurNutritive() > 0 ==> estMangeable();
	  @invariant getValeurNutritive <= 0 ==> !estMangeable();
	  @*/
	/**
	* @pure
	*/

	private String nom;
	private int capitalVie;
	private Piece pieceCourante = new Piece();
	private ArrayList<Aliment> reserve = new ArrayList<Aliment>();
	private int nbAliments;
	private TreeSet<TypeAliment> regimeAlimentaire = new TreeSet<TypeAliment>();
	private Aliment aliment = new Aliment();
	private int valeur;
	private boolean mangeable;
	public static final int maxCapitalVie = 20;
	public static final int coutRamasser = 2;
	public static final int coutDeplacement = 3;

	/**
	* Initialise un animal avec le nom spécifié
	* @param nom 	Le nom de l'animal;
	*
	* @throws NullPointerException si le nom est null;
	*/
	/*@
	  @requires (nom != null);

	  @ensures getNom().equals(nom);
	  @ensures reserveEstVide();
	  @ensures getRegimeAlimentaire().isEmpty();
	  @ensures getCapitalVie() == maxCapitalVie;
	  @*/
	/**
	* @pure
	*/

	public AbstractAnimal(String nom)
	{
		if(nom == null)
			throw new NullPointerException("Le nom ne doit pas etre null");
		this.nom = nom;
		ArrayList<Aliment> reserve = new ArrayList<Aliment>();
		TreeSet<TypeAliment> regimeAlimentaire = new TreeSet<TypeAliment>();
		capitalVie = maxCapitalVie;
	}

	/**
	* Initialise un animal avec le nom spécifié
	* @param nom 		Le nom de l'animal;
	* @param reserve 	La reserve d'aliments de l'animal
	*
	* @throws NullPointerException si le nom est null;
	* @throws NullPointerException si la reserve est null;
	*/
	/*@
	  @requires (nom != null);
	  @requires !(reserve.isEmpty())

	  @ensures getNom().equals(nom);
	  @ensures getRegimeAlimentaire().isEmpty();
	  @ensures (\for all Aliment al : reserve ; this.getReserve().contient(al));
	  @ensures getCapitalVie() == maxCapitalVie;
	  @*/
	/**
	* @pure
	*/

	public AbstractAnimal(String nom, ArrayList<Aliment> reserve)
	{
		if(nom == null || reserve == null)
			throw new NullPointerException("Aucun argument ne doit etre null");
		this.nom = nom;
		this.reserve = new ArrayList<Aliment>();
		for(Aliment al : reserve)
		{
			this.reserve.add(al);
		}
		capitalVie = maxCapitalVie;
		TreeSet<TypeAliment> regimeAlimentaire = new TreeSet<TypeAliment>();
	}

	/**
	* Initialise un animal avec le nom spécifié, se trouvant dans la piece spécifiée,
	* ayant pour régime alimentaire le régime spécifié.
	*
	* @param nom 				Le nom de l'animal;
	* @param pieceCourante		La piece courante de l'animal;
	*
	* @throws NullPointerException si le nom est null;
	* @throws NullPointerException si la piece courante est null;
	*/
	/*@
	* @requires (nom != null);
	  @requires (pieceCourante != null);

	  @ensures getNom().equals(nom);
	  @ensures getPieceCourante().equals(pieceCourante);
	  @ensures getCapitalVie() == maxCapitalVie;
	  @*/
	/**
	* @pure
	*/

	public AbstractAnimal(String nom, Piece pieceCourante)
	{
		if(nom == null || pieceCourante == null)
			throw new NullPointerException("Aucun argument ne doit etre null");
		this.nom = nom;
		this.pieceCourante = pieceCourante;
		TreeSet<TypeAliment> regimeAlimentaire = new TreeSet<TypeAliment>();
		this.reserve = new ArrayList<Aliment>();
		capitalVie = maxCapitalVie;
	}

	/* ================================== METHODES DECLAREES DANS L INTERFACE ANIMAL ==================================*/

	public Iterator<Aliment> iterator()
	{
		Iterator<Aliment> iter = iterator();
		return iter;
	}

	public String getNom()
	{
		return this.nom;
	}

	public String descriptionCourte()
	{
		String descriptionCourte = "L'animal "+this.getNom();
		return descriptionCourte;
	}

	public String descriptionLongue()
	{
		String descriptionLongue = "Cet animal s'appelle "+this.getNom()+". Voici les aliments qu'il possede :"+chaineAliments()+"\n";
		return descriptionLongue;
	}

	public int getCapitalVie()
	{
		return this.capitalVie;
	}

	public int getMaxCapitalVie()
	{
		return maxCapitalVie;
	}

	public boolean estVivant()
	{
		if(capitalVie <= 0)
			return false;
		return true;
	}

	public void mourirDans(Piece p)
	{
		if(p == null)
			throw new NullPointerException("Votre piece ne doit pas etre null\n");
		p = pieceCourante;
		p.getAliments().addAll(this.reserve);
		p.setPlusNbObjets(p.getNbAliments());
		reserve.removeAll(p.getAliments());
		nbAliments = 0;
		capitalVie = 0;
		System.out.println(descriptionCourte()+" vient de mourir.");
	}

	public int getValeurNutritive()
	{
		return this.valeur;
	}

	public boolean peutManger(Aliment al)
	{
		if(getRegimeAlimentaire().contains(al.getType()))
			return true;
		return false;
	}

	public int getNbAliments()
	{
		return nbAliments;
	}

	public int getNbOccurences(Object obj)
	{
		int n = 0;
		if(obj instanceof Aliment)
		{
			Aliment rech = (Aliment) obj;
			for(Aliment al : reserve)
			{
				if(rech.equals(al))
					n++;
			}
		}
		return n;
	}

	public boolean contient(Object obj)
	{
		if(!(obj instanceof Aliment))
			return false;
		Aliment rech = (Aliment) obj;
		return reserve.contains(rech);
	}

	public boolean reserveEstVide()
	{
		if(getNbAliments() != 0)
			return false;
		return true;
	}

	public Aliment choisirAliment(Piece p)
	{
		if(p == null)
			throw new NullPointerException("Votre piece ne doit pas etre null\n");
		if(!(p.getAliments().isEmpty()))
		{
			for(Aliment ali : p.getAliments())
			{
				if(getRegimeAlimentaire().contains(ali.getType()))
					return ali;
			}
		}	
		return null;
	}

	public int getCoutRamasser()
	{
		return coutRamasser;
	}

	public Aliment ramasserDans(Piece p)
	{
		if(p == null)
			throw new NullPointerException("Votre piece ne doit pas etre null\n");
		if(!(estVivant()))
			throw new IllegalStateException("Votre animal doit etre vivant\n");
		if(estVivant())
		{
			Aliment al = choisirAliment(p);
			if(al != null)
			{
				reserve.add(al);
				nbAliments++;
				capitalVie -= coutRamasser;
				if(!(estVivant()))
				{
					mourirDans(p);
					return null;
				}
				p.retirer(al);
				p.setMinusNbObjets(1);
				System.out.print(descriptionCourte()+" vient de ramasser un(e) "+al.getNom());
			}
			else
				System.out.print(descriptionCourte()+" n'a rien trouve a manger.\n");
			return al;
		}
		return null;
	}

	public int getCoutDeplacement()
	{
		return coutDeplacement;
	}

	public Piece deplacerDepuis(Piece p)
	{
		if(p == null)
			throw new NullPointerException("Votre piece ne doit pas etre null\n");
		if(!estVivant())
			throw new IllegalStateException("L'animal doit etre vivant\n");
		if(!(p.contientAnimal(this)))
			throw new IllegalArgumentException("L'animal ne se trouve pas dans la piece spécifiée.\n Piece specifiee : "+p.getNom()+" Piece du chien : "+this.getPieceCourante().getNom());
		if(choisirSortie(p) == null)
			return null;
		if(!((p != null) && (estVivant()) && (p.contientAnimal(this))))
			return null;
		pieceCourante.getAnimaux().remove(this);
		pieceCourante = p.pieceSuivante(choisirSortie(p));
		pieceCourante.getAnimaux().add(this);
		capitalVie -= coutDeplacement;
		System.out.println(getNom()+" vient de se déplacer de '"+p.getNom()+"' à '"+pieceCourante.getNom()+"'.\n ");
		if(capitalVie <= 0)
			mourirDans(pieceCourante);
		return pieceCourante;
	}

	public Direction choisirSortie(Piece p)
	{
		if(p == null)
			throw new NullPointerException("Votre piece ne doit pas etre null\n");
		int d = 2;
		Direction direction = Direction.SUD;
		if(p.equals(pieceCourante))
		{
			do{
				d = (int) (Math.random() * 4);
				if(d == 0)
					direction = Direction.NORD;
				if(d == 1)
					direction = Direction.EST;
				if(d == 2)
					direction = Direction.SUD;
				if(d == 3)
					direction = Direction.OUEST;
			} while(p.getSorties().containsKey(direction) == false);
			return direction;
		}
		else
			System.out.println("La piece passee en parametre ne correspond pas a la piece courante de l'animal.");
		return null;
	}

	public Set<TypeAliment> getRegimeAlimentaire()
	{
		return regimeAlimentaire;
	}

	public Aliment getAliment()
	{
		if(reserveEstVide())
			return null;
		return reserve.get(reserve.size()-1); // renvoie le dernier aliment de la reserve de l'animal.
	}

	public Aliment manger()
	{
		if(!estVivant())
			return null;
		Aliment al = getAliment();
		if(al != null)
		{
			reserve.remove(al);
			capitalVie += al.getValeurNutritive();
			nbAliments--;
			getPieceCourante().getAliments().remove(al);
			System.out.println(" et l'a mangé. ");
		}
		return al;
	}

	/* ================================== METHODES AJOUTEES ================================== */

	/**
	* Renvoie une chaine de caractères contenant les aliments de la reserve de cet animal.
	* On utilisera cette méthode pour la méthode descriptionLongue()
	*
	* @return Une chaine de caractère contenant le slaiments de la reserve de l'animal
	* @pure
	*/
	public String chaineAliments()
	{
		String lesAliments = " ";
		for(Aliment a : this.reserve)
		{
			lesAliments += a.getNom()+"\n";
		}
		return lesAliments;
	}

	/**
	* Cette méthode nous donne la liste des aliments que contient cet animal
	*
	* @return La liste des laiments que contient l'animal
	*
	* @pure
	*/
	public ArrayList<Aliment> getReserve()
	{
		return this.reserve;
	}

	/**
	* Renvoie un boolean nous indiquant si cet animal est mangeable ou non
	*
	* @return true si cet animal est mangeable, false sinon
	* @pure
	*/
	public boolean estMangeable()
	{
		return mangeable;
	}

	/**
	* Affiche les aliments portes par cet animal
	*
	* @pure
	*/
	public void afficherAliments()
	{
		System.out.println(chaineAliments());
		System.out.println("\n");
	}

	/**
	* Ajoute au regime alimentaire de cet animal le type d'aliment passé en paramètre
	*
	* @param type Le type d'aliment qu'on veut ajouter au regime alimentaire de l'animal
	*
	*/
	/*@
	  @requires getType() != null;
	  @ensures getRegimeAlimentaire().contains(type)
	  @*/
	/**
	* @pure
	*/
	public void addToRegimeAlimentaire(TypeAliment type)
	{
		regimeAlimentaire.add(type);
		return;
	}

	/**
	* Nous indique si un animal est mangeable ou non par un autre.
	*
	* @param b Le booleen qu'on veut attribuer à mangeable
	*/
	/*@
	  @requires (b == true || b == false);
	  @ensures (estMangeable() == b);
	  @*/
	/**
	* @pure
	*/
	public void setMangeable(boolean b)
	{
		mangeable = b;
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
	public void setValeurNutritive(int v)
	{
		valeur = v;
		return;
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
	*/
	/*@
	  @
	  @*/
	/**
	* @pure
	*/
	public void setMinusCapitalVie(int v)
	{
		capitalVie -= v;
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
	public void setPlusCapitalVie(int v)
	{
		capitalVie += v;
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
	public void setPieceCourante(Piece newPiece)
	{
		pieceCourante = newPiece;
		return;
	}





}