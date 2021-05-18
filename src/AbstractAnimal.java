import java.util.Set;
import java.util.Iterator;
import java.util.ArrayList;
import java.lang.Math; 
import java.util.*;
import java.lang.Object;

public abstract class AbstractAnimal implements Animal {

	private final String nom;
	private int capitalVie;
	private Piece pieceCourante = new Piece();
	private ArrayList<Aliment> reserve = new ArrayList<>();
	private int nbAliments;
	private final TreeSet<TypeAliment> regimeAlimentaire = new TreeSet<>();
	private int valeur;
	private boolean mangeable;
	public static final int maxCapitalVie = 20;
	public static final int coutRamasser = 2;
	public static final int coutDeplacement = 3;

	public AbstractAnimal(String nom) {
		if (nom == null)
			throw new NullPointerException("Le nom ne doit pas etre null");
		this.nom = nom;
		capitalVie = maxCapitalVie;
	}

	public AbstractAnimal(String nom, ArrayList<Aliment> reserve) {
		if (nom == null || reserve == null)
			throw new NullPointerException("Aucun argument ne doit etre null");
		this.nom = nom;
		this.reserve = new ArrayList<>();
		this.reserve.addAll(reserve);
		capitalVie = maxCapitalVie;
	}

	public AbstractAnimal(String nom, Piece pieceCourante) {
		if (nom == null || pieceCourante == null)
			throw new NullPointerException("Aucun argument ne doit etre null");
		this.nom = nom;
		this.pieceCourante = pieceCourante;
		this.reserve = new ArrayList<>();
		capitalVie = maxCapitalVie;
	}

	/* ================================== METHODES DECLAREES DANS L INTERFACE ANIMAL ==================================*/

	public Iterator<Aliment> iterator() {
		return iterator();
	}

	public String getNom() {
		return this.nom;
	}

	public String descriptionCourte() {
		return "L'animal " + this.getNom();
	}

	public String descriptionLongue() {
		return "Cet animal s'appelle " + this.getNom() + ". Voici les aliments qu'il possede :" + chaineAliments() + "\n";
	}

	public int getCapitalVie() {
		return this.capitalVie;
	}

	public int getMaxCapitalVie() {
		return maxCapitalVie;
	}

	public boolean estVivant() {
		return capitalVie > 0;
	}

	public void mourirDans(Piece p) {
		if (p == null)
			throw new NullPointerException("Votre piece ne doit pas etre null\n");
		p = pieceCourante;
		p.getAliments().addAll(this.reserve);
		p.setPlusNbObjets(p.getNbAliments());
		reserve.removeAll(p.getAliments());
		nbAliments = 0;
		capitalVie = 0;
		System.out.println(descriptionCourte() + " vient de mourir.");
	}

	public int getValeurNutritive() {
		return this.valeur;
	}

	public boolean peutManger(Aliment al) {
		return getRegimeAlimentaire().contains(al.getType());
	}

	public int getNbAliments() {
		return nbAliments;
	}

	public int getNbOccurences(Object obj) {
		int n = 0;
		if (obj instanceof Aliment) {
			Aliment rech = (Aliment) obj;
			for(Aliment al : reserve) {
				if (rech.equals(al))
					n++;
			}
		}
		return n;
	}

	public boolean contient(Object obj) {
		if (!(obj instanceof Aliment))
			return false;
		Aliment rech = (Aliment) obj;
		return reserve.contains(rech);
	}

	public boolean reserveEstVide() {
		return getNbAliments() == 0;
	}

	public Aliment choisirAliment(Piece p) {
		if (p == null)
			throw new NullPointerException("Votre piece ne doit pas etre null\n");
		if (!(p.getAliments().isEmpty())) {
			for (Aliment ali : p.getAliments()) {
				if (getRegimeAlimentaire().contains(ali.getType()))
					return ali;
			}
		}	
		return null;
	}

	public int getCoutRamasser() {
		return coutRamasser;
	}

	public Aliment ramasserDans(Piece p) {
		if (p == null)
			throw new NullPointerException("Votre piece ne doit pas etre null\n");
		if (!(estVivant()))
			throw new IllegalStateException("Votre animal doit etre vivant\n");
		if (estVivant()) {
			Aliment al = choisirAliment(p);
			if(al != null) {
				reserve.add(al);
				nbAliments++;
				capitalVie -= coutRamasser;
				if (!(estVivant())) {
					mourirDans(p);
					return null;
				}
				p.retirer(al);
				p.setMinusNbObjets(1);
				System.out.print(descriptionCourte() + " vient de ramasser un(e) " + al.getNom());
			}
			else
				System.out.print(descriptionCourte() + " n'a rien trouve a manger.\n");
			return al;
		}
		return null;
	}

	public int getCoutDeplacement() {
		return coutDeplacement;
	}

	public Piece deplacerDepuis(Piece p) {
		if (p == null)
			throw new NullPointerException("Votre piece ne doit pas etre null\n");
		if (!estVivant())
			throw new IllegalStateException("L'animal doit etre vivant\n");
		if (!(p.contientAnimal(this)))
			throw new IllegalArgumentException("L'animal ne se trouve pas dans la piece spécifiée.\n Piece specifiee : " + p.getNom() + " Piece du chien : " + this.getPieceCourante().getNom());
		if (choisirSortie(p) == null)
			return null;
		if (!(estVivant() && p.contientAnimal(this)))
			return null;
		pieceCourante.getAnimaux().remove(this);
		pieceCourante = p.pieceSuivante(choisirSortie(p));
		pieceCourante.getAnimaux().add(this);
		capitalVie -= coutDeplacement;
		System.out.println(getNom() + " vient de se déplacer de '" + p.getNom() + "' à '" + pieceCourante.getNom() + "'.\n ");
		if (capitalVie <= 0)
			mourirDans(pieceCourante);
		return pieceCourante;
	}

	public Direction choisirSortie(Piece p) {
		if (p == null)
			throw new NullPointerException("Votre piece ne doit pas etre null\n");
		int d;
		Direction direction = Direction.SUD;
		if (p.equals(pieceCourante)) {
			do {
				d = (int) (Math.random() * 4);
				if (d == 0)
					direction = Direction.NORD;
				if (d == 1)
					direction = Direction.EST;
				if (d == 2)
					direction = Direction.SUD;
				if (d == 3)
					direction = Direction.OUEST;
			} while (!p.getSorties().containsKey(direction));
			return direction;
		}
		else
			System.out.println("La piece passee en parametre ne correspond pas a la piece courante de l'animal.");
		return null;
	}

	public Set<TypeAliment> getRegimeAlimentaire() {
		return regimeAlimentaire;
	}

	public Aliment getAliment() {
		if (reserveEstVide())
			return null;
		return reserve.get(reserve.size() - 1); // renvoie le dernier aliment de la reserve de l'animal.
	}

	public Aliment manger() {
		if (!estVivant())
			return null;
		Aliment al = getAliment();
		if (al != null) {
			reserve.remove(al);
			capitalVie += al.getValeurNutritive();
			nbAliments--;
			getPieceCourante().getAliments().remove(al);
			System.out.println(" et l'a mangé. ");
		}
		return al;
	}

	/* ================================== METHODES AJOUTEES ================================== */

	public String chaineAliments() {
		StringBuilder lesAliments = new StringBuilder(" ");
		for(Aliment a : this.reserve) {
			lesAliments.append(a.getNom()).append("\n");
		}
		return lesAliments.toString();
	}

	public void afficherAliments() {
		System.out.println(chaineAliments());
		System.out.println("\n");
	}

	public void addToRegimeAlimentaire(TypeAliment type) {
		regimeAlimentaire.add(type);
	}

	public void setMangeable(boolean b) {
		mangeable = b;
	}

	public void setValeurNutritive(int v) {
		valeur = v;
	}

	public Piece getPieceCourante() {
		return pieceCourante;
	}

	public void setMinusCapitalVie(int v) {
		capitalVie -= v;
	}

	public void setPlusCapitalVie(int v) {
		capitalVie += v;
	}

	public void setPieceCourante(Piece newPiece) {
		pieceCourante = newPiece;
	}





}