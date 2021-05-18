import java.util.ArrayList;

public class Chevre extends AbstractAnimal {

	public Chevre(String nom) {
		super(nom);
		addToRegimeAlimentaire(TypeAliment.VEGETAL);
		setMangeable(true);
		setValeurNutritive(7);
	}

}