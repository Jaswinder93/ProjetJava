import java.util.Set;
import java.util.ArrayList;
import java.util.*;

public class Loup extends AbstractAnimal implements Chasseur {

	ArrayList<Animal> animauxChasses = new ArrayList<>();

	public Loup(String nom) {
		super(nom);
		addToRegimeAlimentaire(TypeAliment.ANIMAL);
		setMangeable(false);
		setValeurNutritive(0);
	}

	public Loup(String nom, ArrayList<Aliment> reserve) {
		super(nom, reserve);
		addToRegimeAlimentaire(TypeAliment.ANIMAL);
		setMangeable(false);
		setValeurNutritive(0);
	}

	public Loup(String nom, Piece pieceCourante) {
		super(nom, pieceCourante);
		addToRegimeAlimentaire(TypeAliment.ANIMAL);
		setMangeable(false);
		setValeurNutritive(0);
	}

	public Animal choisirProie(Piece p) {
		if (p.equals(getPieceCourante())) {
			for (Animal an : p.getAnimaux()) {
				if(peutChasser(an))
					return an;
			}
		}
		return null;
	}

	public Set<Class<? extends Animal>> getRegimeAlimentaireChasse() {
		HashSet<Class<? extends Animal>> regimeAlimentaireChasse = new HashSet<>();
		regimeAlimentaireChasse.add(Chevre.class);
		return regimeAlimentaireChasse;
	}

	public boolean peutChasser(Animal ani) {
		return ani != null && getRegimeAlimentaireChasse().contains(ani.getClass());
	}

	public Animal chasser(Piece p) {
		if (p == null)
			throw new NullPointerException("La piece ne doit pas etre null\n");
		if (!estVivant())
			throw new IllegalStateException("L'animal doit etre vivant\n");
		if (estVivant() && p.equals(getPieceCourante())) {
			Animal victime = choisirProie(p);
			if (victime != null) {
				if (victime.estVivant()) {
					victime.mourirDans(p);
					setMinusCapitalVie(getCoutChasse());
					animauxChasses.add(victime);
					System.out.println("Le loup "+getNom()+" vient de chasser la chevre "+victime.getNom());
					if(getCapitalVie() <= 0) {
						mourirDans(p);
						return victime;
					}
				} else {
					if (!(animauxChasses.contains(victime))) {
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

	public int getCoutChasse() {
		return (2 * getCoutDeplacement());
	}

}









