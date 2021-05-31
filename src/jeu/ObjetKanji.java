package jeu;

public class ObjetKanji {

	private String nom;
	private EffetObjetKanji effet;
	private boolean transportable;
	private int poids;
	private Piece pieceCourante;
	public static final int max_poids_objet = 100;

	public ObjetKanji(){}

	public ObjetKanji(String nom, EffetObjetKanji effet, int poids, Piece pieceCourante) {
		if (nom == null || effet == null || pieceCourante == null)
			throw new NullPointerException("Aucun argument ne doit valoir null");
		if (poids <= 0)
			throw new IllegalArgumentException("Le poids doit etre strictement superieur a zero");
		this.nom = nom;
		this.effet = effet;
		this.poids = poids;
		this.pieceCourante = pieceCourante;
	}

	public ObjetKanji(String nom, Piece pieceCourante) {
		if (nom == null || pieceCourante == null)
			throw new NullPointerException("Aucun argument ne doit valoir null");
		this.nom = nom;
		this.pieceCourante = pieceCourante;
		transportable = false;
		poids = 5;
		effet = EffetObjetKanji.NEUTRE;
	}

	public String getNom() {
		return nom;
	}

	public EffetObjetKanji getEffet() {
		return effet;
	}

	public int getPoids() {
		return poids;
	}

	public Piece getPieceCourante() {
		return pieceCourante;
	}

	public boolean estTransportable() {
		return getPoids() <= max_poids_objet;
	}

	public void setPoids(int newPoids) {
		poids = newPoids;
	}

	public void setEffet(EffetObjetKanji e) {
		effet = e;
	}

	public void setTransportable(boolean b) {
		transportable = b;
	}













}