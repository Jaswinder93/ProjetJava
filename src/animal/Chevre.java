package animal;

import animal.AbstractAnimal;
import jeu.TypeAliment;

public class Chevre extends AbstractAnimal {

	public Chevre(String nom) {
		super(nom);
		addToRegimeAlimentaire(TypeAliment.VEGETAL);
		setMangeable(true);
		setValeurNutritive(7);
	}

}