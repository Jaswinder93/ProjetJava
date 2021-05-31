package jeu;

public class Aliment extends ObjetKanji {

	private final int valeur;
	private TypeAliment type;

	public Aliment(String nom, Piece pieceCourante, int valeur, TypeAliment type) {
		super(nom, pieceCourante);
		if (type == null)
			throw new NullPointerException("Aucun argument ne doit valoir null");
		if (valeur <= 0)
			throw new IllegalArgumentException("La valeur nutritive de l'aliment doit etre superieure a zero");
		this.valeur = valeur;
		this.type = type;
	}

	public int getValeurNutritive() {
		return valeur;
	}

	public TypeAliment getType() {
		return type;
	}









}