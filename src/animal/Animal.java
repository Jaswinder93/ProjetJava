package animal;

import jeu.Aliment;
import jeu.Direction;
import jeu.Piece;
import jeu.TypeAliment;

import java.util.Set;

public interface Animal extends Iterable<Aliment> {

	String getNom();

	String descriptionCourte();

	String descriptionLongue();

	int getCapitalVie();

	int getMaxCapitalVie();

	boolean estVivant();

	void mourirDans(Piece p);

	int getValeurNutritive();

	boolean peutManger(Aliment al);

	int getNbAliments();

	int getNbOccurences(Object obj);

	boolean contient(Object obj);

	boolean reserveEstVide();

	Aliment choisirAliment(Piece p);

	int getCoutRamasser();

	Aliment ramasserDans(Piece p);

	// Gestion des déplacements

	int getCoutDeplacement();

	Piece deplacerDepuis(Piece p);

	Direction choisirSortie(Piece p);

	Set<TypeAliment> getRegimeAlimentaire();

	Aliment getAliment();

	Aliment manger();

}