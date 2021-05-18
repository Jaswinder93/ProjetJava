package jeu;

import animal.Animal;
import animal.Chien;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class Piece extends Container<ObjetKanji> {

	private String nom;
	private ArrayList<Animal> animaux = new ArrayList<>();
	private final Map<Direction, Piece> sorties = new HashMap<>();
	private ArrayList<Aliment> aliments = new ArrayList<>();

	public Piece() {
		super();
	}

	public Piece(String nom, ArrayList<Animal> animaux, ArrayList<ObjetKanji> contenu, ArrayList<Aliment> aliments) {
		super(contenu);
		if (nom == null || animaux == null || aliments == null)
			throw new NullPointerException("Aucun argument ne doit etre null");
		this.nom = nom;
		this.animaux = new ArrayList<Animal>();
		this.aliments = new ArrayList<Aliment>();
		this.animaux.addAll(animaux);
		this.aliments.addAll(aliments);
	}

	public ArrayList<Animal> getAnimaux() {
		return animaux;
	}

	public String getNom() {
		return nom;
	}

	public boolean contientAnimal(Animal al) {
		return animaux.contains(al);
	}

	public Piece pieceSuivante(Direction d) {
		if (d==null)
			throw new NullPointerException("La direction doit etre non null");
		return sorties.get(d);
	}

	public ArrayList<Aliment> getAliments() {
		return aliments;
	}

	public int getNbAliments() {
		return getAliments().size();
	}

	public int getNbAnimaux() {
		return animaux.size();
	}

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

	public String descriptionSorties() {
		StringBuilder resulString = new StringBuilder("Sorties:");
		for (Direction sortie :  sorties.keySet()) {
			resulString.append(" ").append(sortie);
		}
		return resulString.toString();
	}

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

	@Override
	public boolean ajoutPossible(ObjetKanji oz) {
		return true;
	}

	@Override
	public void afficherObjets() {
		System.out.println("S'il y en a, voici les boites magiques disponibles : ");
		if (!(getContenu().isEmpty())) {
			for (ObjetKanji oz : getContenu()) {
				System.out.println("Nom : "+oz.getNom()+"; poids : "+oz.getPoids());
			}
		}
		System.out.println();
	}

	public Map<Direction, Piece> getSorties() {
		return sorties;
	}

	public void afficherChiens(Joueur joueur) {
		System.out.println("S'il y en a, voici les chiens disponibles :");
		for (Animal ani : getAnimaux()) {
			if (ani instanceof Chien && !(joueur.getChiens().contains(ani)) && ani.estVivant())
				System.out.println(ani.getNom()+" ");
		}
		System.out.println();
	}

	@Override
	public boolean retraitPossible(ObjetKanji oz) {
		return contient(oz);
	}

	public void retirerChien(Animal ch) {
		animaux.remove(ch);
	}

	public void ajouterChien(Animal ch) {
		animaux.add(ch);
	}










}