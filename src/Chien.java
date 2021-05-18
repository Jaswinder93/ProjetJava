import java.util.ArrayList;

public class Chien extends AbstractAnimal implements Adoptable {
	private boolean adoptable;
	private Joueur joueur; // son maitre s'il est adopté
	private Direction sortieProposee; // si le chien est adopté
	private Piece piece_a_quitter;

	public Chien(String nom) {
		super(nom);
		adoptable = true;
		addToRegimeAlimentaire(TypeAliment.ANIMAL);
		addToRegimeAlimentaire(TypeAliment.VEGETAL);
		setMangeable(false);
		setValeurNutritive(0);
	}

	/* ======================================== METHODES De L INTERFACE ADOPTABLE ======================================== */
	
	public boolean estLibre() {
		return adoptable;
	}

	public boolean adoption() {
		if (estLibre()) {
			adoptable = false;
		} else
			return false;
		return false;
	}

	public boolean liberation() {
		if (!estLibre())
			adoptable = true;
		return adoptable;
	}

	public void proposerSortie(Piece p, Direction d) {
		if (p == null || d == null)
			throw new NullPointerException("Vos arguments ne doivent pas etre null");
		if (p.pieceSuivante(d) == null)
			throw new IllegalArgumentException("La piece ne possede pas de sortie dans la direction donnée");

		if (p.pieceSuivante(d) != null && p.equals(getPieceCourante())) {
			if (!estLibre()) {
				piece_a_quitter = p;
				/* Si la direction passée en paramètre correspond à la sortie proposée par le joueur,
				on impose cette direction */
				if(d.equals(joueur.getFuturDplct()))
					sortieProposee = d;
				else
					sortieProposee = null; // Aucune sortie à proposer car le chien doit suivre son maitre
			} else
				sortieProposee = d;
		}
	}

	public Direction getSortieProposee(Piece p) {
		if (p == null)
			throw new NullPointerException("La piece ne doit pas etre null");
		/* si p correspond a la piece pour laquelle la sortie a été proposée par
		le joueur et que p correspond aussi a la piece courante */
		if (p.equals(piece_a_quitter) && p.equals(getPieceCourante()))
			return sortieProposee;
		return null;
	}

	public Direction choisirSortie(Piece p) {
			if (estLibre())
				return super.choisirSortie(p);
			if (!estLibre() && (p.equals(piece_a_quitter))) // Si le chien est adopte et que la piece correspond bien a celle pour laquelle une sortie a ete proposée par le joueur
				return getSortieProposee(p);
			return null;
	}

	public Piece deplacerDepuis(Piece p) {
		if (estVivant())
			return super.deplacerDepuis(p);
		return null;
	}

/* =============================================== METHODES AJOUTEES =============================================== */

	public void setJoueur(Joueur j) {
		joueur = j;
	}

	public void setPieceAQuitter(Piece p) {
		piece_a_quitter = p;
	}

}

















