package zork;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.*;


public class Joueur extends Container<ObjetZork>
{
	/*@
	  @invariant getPieceCourante() != null;
	  @invariant getPoidsCourant() >= 0;
	  @invariant getNbChiens() >= 0;
	  @invariant getNbBoitesMagiques() >= 0;
	  @invariant getNbBoitesPoison() >= 0;
	  @*/
	/**
	* @pure
	*/

	private Direction futur_dplct;
	private Piece pieceCourante;
	private ArrayList<Chien> chiens;
	private int poidsCourant;
	private int nbChiens;
	private int nbBoitesMagiques;
	private int nbBoitesPoison;
	private Chien chienAdoptableVoulu;
	private Chien chienLiberable;
	public static final int max_poids_total = 700;

/*=====================================CONSTRUCTEURS========================================*/

	/**
	* Initialise un joueur dans la piece specifiée
	*
	* @param pieceCourante La piece ou on veut placer le joueur
	*
	* @throws NullPointerException si la piece specifiee vaut null;
	*/
	/*@
	  @requires pieceCourante != null;
	  @ensures getPieceCourante().equals(pieceCourante);
	  @ensures getChiens().isEmpty();
	  @*/
	/**
	* @pure
	*/
	public Joueur(Piece pieceCourante)
	{
		super();
		this.pieceCourante = pieceCourante;
		chiens = new ArrayList<Chien>();
	}

	public Joueur(){
		super();
		chiens = new ArrayList<Chien>();
	}

/*=====================================GETTERS/SETTERS========================================*/

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
	public int getPoidsCourant()
	{
		return poidsCourant;
	}

	/**
	* 
	* @pure
	*/
	public int getNbChiens()
	{
		return nbChiens;
	}

	/**
	* 
	* @pure
	*/
	public ArrayList<Chien> getChiens()
	{
		return chiens;
	}

	/**
	* 
	* @pure
	*/
	public Direction getFuturDplct()
	{
		return futur_dplct;
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
	public void setFuturDplct(Direction d)
	{
		futur_dplct = d;
	}

	/**
	* 
	* @pure
	*/
	public int getNbBoitesMagiques()
	{
		return nbBoitesMagiques;
	}

	/**
	* 
	* @pure
	*/
	public int getNbBoitesPoison()
	{
		return nbBoitesPoison;
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
	public void setPlusNbBoitesMagiques(int v)
	{
		nbBoitesMagiques += v;
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
	public void setPlusNbBoitesPoison(int v)
	{
		nbBoitesPoison += v;
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
	public void setMoinsNbBoitesMagiques(int v)
	{
		nbBoitesMagiques -= v;
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
	public void setMoinsNbBoitesPoison(int v)
	{
		nbBoitesPoison -= v;
		return;
	}

	/**
	* 
	* @pure
	*/
	public Chien getChienAdoptableVoulu()
	{
		return chienAdoptableVoulu;
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
	public void setChienAdoptableVoulu(Chien ch)
	{
		chienAdoptableVoulu = ch;
		return;
	}

	/**
	* 
	* @pure
	*/
	public Chien getChienLiberable()
	{
		return chienLiberable;
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
	public void setChienLiberable(Chien ch)
	{
		chienLiberable = ch;
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
	public void setPieceCourante(Piece p)
	{
		pieceCourante = p;
		return;
	}

/*=====================================PRISE ET POSE D'OBJETS========================================*/


	/**
	* 
	*/
	/*@
	  @
	  @*/
	/**
	* @pure
	*/
	@Override
	public boolean ajoutPossible(ObjetZork oz)
	{
		if(((getPoidsCourant() + oz.getPoids()) > max_poids_total))
			System.out.println("Le poids total de vos objets a excede 700 grammes ! Veuillez poser une boite a effet neutre avant de prendre cet objet.\n");
		if((!oz.estTransportable()))
			System.out.println("Votre objet est trop lourd ! Le poids ne doit pas exceder 100 grammes");
		return (oz != null)
                && oz.estTransportable()
                && ((getPoidsCourant() + oz.getPoids()) <= max_poids_total);
	}

	/**
	* 
	* @pure
	*/
	@Override
	public void afficherObjets()
	{
		System.out.println("Si vous en portez, voici vos boites : ");
		if(!(getContenu().isEmpty()))
		{
			for(ObjetZork oz : getContenu())
			{
				System.out.println("Nom : "+oz.getNom()+"; effet : "+oz.getEffet()+"; poids : "+oz.getPoids());
			}
		}
		System.out.println();
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
	public boolean prendreObjet(ObjetZork oz)
	{
		if(ajoutPossible(oz)){
			ajouter(oz);
			poidsCourant += oz.getPoids();
			setPlusNbObjets(1);
			if(oz.getEffet().equals(EffetObjetZork.MAGIQUE))
			{
				System.out.println("Ouf ! Vous venez de prendre une boite magique !");
				nbBoitesMagiques++;
			}
			else if(oz.getEffet().equals(EffetObjetZork.TUEUR))
			{
				System.out.println("Oups... Vous venez de prendre une boite poison.");
				nbBoitesPoison++;
			}
			else
				System.out.println("Boite neutre ! Rien a signaler.");
			return true;
		}
		else
		{
			System.out.println("La prise d'objet a echoue.");
			return false;
		}
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
	public boolean poserObjet(ObjetZork oz)
	{
		if((contient(oz)) && oz.getEffet().equals(EffetObjetZork.NEUTRE))
		{
			poidsCourant -= oz.getPoids();
			setMinusNbObjets(1);
			retirer(oz);
			return true;
		}
		else
		{
			System.out.println("Attention. Verifiez que vous portez bien cet objet ET que l'effet de cet objet est neutre.");
		}
		return false;
	}


/*=====================================CHIENS ADOPTES========================================*/

	/**
	* 
	*/
	/*@
	  @
	  @*/
	/**
	* @pure
	*/
	public boolean adopterChien(Chien chien)
	{
		if(pieceCourante.contientAnimal(chien))
		{
			nbChiens++;
			chiens.add(chien);
			chien.adoption();
			//pieceCourante.getAnimaux().remove(chien);
			chien.setJoueur(this);
			return true;
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
	public boolean libererChien(Chien chien)
	{
		if(chiens.contains(chien))
		{
			nbChiens--;
			chiens.remove(chien);
			chien.liberation();
			//pieceCourante.getAnimaux().add(chien);
			chien.setJoueur(null);
			return true;
		}
		return false;
	}

	/**
	* 
	* @pure
	*/
	public void afficherChiens()
	{
		System.out.println("Si vous en avez, voici vos chiens :");
		for(int i = 0 ; i < nbChiens ; i++){
			if(chiens.get(i).estVivant())
				System.out.println(chiens.get(i).getNom() + "  "); 
		}
		System.out.println();
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
	public boolean contientChienDeCeNom(Chien ch)
	{
		for(Chien chien : chiens)
		{
			if(chien.getNom().equals(ch.getNom()))
				return true;
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
	@Override
	public boolean retraitPossible(ObjetZork oz)
	{
		if(contient(oz) && oz.estTransportable())
			return true;
		return false;
	}














}