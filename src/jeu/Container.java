package jeu;

import java.util.ArrayList;

public abstract class Container<ObjetKanji> {

	private final ArrayList<ObjetKanji> contenu;
	private int nbObjets;

/* =================================CONSTRUCTEURS====================================*/

	public Container() {
		contenu = new ArrayList<>();
		nbObjets = 0;
	}

	public Container(ArrayList<ObjetKanji> lesObjets) {
		contenu = new ArrayList<>();
		contenu.addAll(lesObjets);
		nbObjets = 0;
	}

/* =================================GETTERS/SETTERS====================================*/

	public ArrayList<ObjetKanji> getContenu() {
		return contenu;
	}

	public void setPlusNbObjets(int newNbObjets) {
		
		nbObjets+=newNbObjets;
	}

	public void setMinusNbObjets(int newNbObjets) {
		nbObjets-=newNbObjets;
	}

/* =================================METHODES====================================*/

	public boolean contient(ObjetKanji oz) {
		return contenu.contains(oz);
	}

	public void ajouter(ObjetKanji oz) {
		if (ajoutPossible(oz))
			contenu.add(oz);
	}

	public void retirer(ObjetKanji oz) {
		if (retraitPossible(oz)) {
			contenu.remove(oz);
		}
	}

	abstract boolean retraitPossible(ObjetKanji oz);

	abstract boolean ajoutPossible(ObjetKanji oz);

	abstract void afficherObjets();















}