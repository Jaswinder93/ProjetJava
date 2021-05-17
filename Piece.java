package zork;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.*;

public class Piece extends Container<ObjetZork>
{
	/*@
	  @invariant getNom() != null;
	  @*/
	 /**
	 * @pure
	 */

	private String nom;
	private ArrayList<Animal> animaux = new ArrayList<Animal>();
	private Map<Direction, Piece> sorties = new HashMap<Direction, Piece>();
	private ArrayList<Aliment> aliments = new ArrayList<Aliment>();

	public Piece()
	{
		super();
	}

	/**
	* Initialise une nouvelle piece avec le nom et les listes specifiees
	*
	* @param nom 		Le nom de la piece
	* @param animaux 	Les animaux contenus dans la piece
	* @param contenu 	Les objets contenus dans la piece
	* @param aliments 	Les aliments contenus dans la piece
	*
	* @throws NullPointerException si le nom est null
	* @throws NullPointerException si la liste d'animaux est null
	* @throws NullPointerException si la liste d'objets est null
	* @throws NullPointerException si la liste d'aliments est null
	*/
	/*@
	  @also
	  @requires nom != null;
	  @requires animaux != null;
	  @requires aliments != null;
	  @ensures getNom().equals(nom);
	  @ensures (\for all Aliment al : aliments ; this.getAliments().contains(al));
	  @ensures (\for all Animal ani : animaux ; this.getAnimaux().contientAnimal(ani));
	  @ensures getAnimaux().size() == animaux.size();
	  @ensures getAliments().size() == aliments.size();
	  @*/
	  /**
	  * @pure
	  */
	public Piece(String nom, ArrayList<Animal> animaux, ArrayList<ObjetZork> contenu, ArrayList<Aliment> aliments)
	{
		super(contenu);
		if(nom == null || animaux == null || aliments == null)
			throw new NullPointerException("Aucun argument ne doit etre null");
		this.nom = nom;
		this.animaux = new ArrayList<Animal>();
		this.aliments = new ArrayList<Aliment>();
		this.animaux.addAll(animaux);
		this.aliments.addAll(aliments);
	}

	/**
	* 
	* @pure
	*/
	public ArrayList<Animal> getAnimaux()
	{
		return animaux;
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
	*/
	/*@
	  @
	  @*/
	/**
	* @pure
	*/
	public boolean contientAnimal(Animal al)
	{
		if(animaux.contains(al))
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
	public Piece pieceSuivante(Direction d)
	{
		if(d==null)
			throw new NullPointerException("La direction doit etre non null");
		return sorties.get(d);
	}

	public ArrayList<Aliment> getAliments()
	{
		return aliments;
	}

	/**
	* 
	* @pure
	*/
	public int getNbAliments()
	{
		return getAliments().size();
	}

	/**
	* 
	* @pure
	*/
	public int getNbAnimaux()
	{
		return animaux.size();
	}

	/**
	 *  Définie les sorties de cette piece. A chaque direction correspond ou bien
	 *  une piece ou bien la valeur null signifiant qu'il n'y a pas de sortie dans
	 *  cette direction.
	 *
	 * @param  nord   La sortie nord
	 * @param  est    La sortie est
	 * @param  sud    La sortie sud
	 * @param  ouest  La sortie ouest
	 */
	public void setSorties(Piece nord, Piece est, Piece sud, Piece ouest) {
		if (nord != null)
			sorties.put(Direction.NORD, nord);
		if (est != null)
			sorties.put(Direction.EST, est);
		if (sud != null)
			sorties.put(Direction.SUD, sud);
		if (ouest != null)
			sorties.put(Direction.OUEST, ouest);
	}

	/**
	 *  Renvoie une description des sorties de cette piece
	 *
	 * @return    Une description des sorties de cette pièce.
	 */

	public String descriptionSorties() {
		String resulString = "Sorties:";
		for (Direction sortie :  sorties.keySet()) {
			resulString += " " + sortie;
		}
		return resulString;
	}

	/**
	* 	Affiche le plan de la foret
	*
	*/

	public void afficherPlan(){
		System.out.println("Plan de la foret :");
		System.out.println("--------------------------------------------------------------------------------------");
		System.out.println("|                | marecagesNord  |  clairiereNord |    lacNord     |                |");
		System.out.println("--------------------------------------------------------------------------------------");
		System.out.println("| clairiereOuest |     riviere    |   zonePuits    | precipitations |  clairiereEst  |");
		System.out.println("--------------------------------------------------------------------------------------");
		System.out.println("|     lacSud     |  marecagesSud  |  entreeForet   |     cabane     |   grandChene   |");
		System.out.println("--------------------------------------------------------------------------------------");
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
	@Override
	public boolean ajoutPossible(ObjetZork oz)
	{
		return true;
	}

	/**
	* 
	* @pure
	*/
	@Override
	public void afficherObjets()
	{
		System.out.println("S'il y en a, voici les boites magiques disponibles : ");
		if(!(getContenu().isEmpty()))
		{
			for(ObjetZork oz : getContenu())
			{
				System.out.println("Nom : "+oz.getNom()+"; poids : "+oz.getPoids());
			}
		}
		System.out.println();
	}

	public Map<Direction, Piece> getSorties()
	{
		return sorties;
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
	public void afficherChiens(Joueur joueur)
	{
		System.out.println("S'il y en a, voici les chiens disponibles :");
		for(Animal ani : getAnimaux())
		{
			if(ani instanceof Chien && !(joueur.getChiens().contains(ani)) && ani.estVivant())
				System.out.println(ani.getNom()+" ");
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
	@Override
	public boolean retraitPossible(ObjetZork oz)
	{
		if(contient(oz))
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
	public void retirerChien(Animal ch)
	{
		animaux.remove(ch);
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
	public void ajouterChien(Animal ch)
	{
		animaux.add(ch);
	}










}