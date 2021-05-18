import java.util.ArrayList;

public abstract class Container<ObjetZork> {

	private ArrayList<ObjetZork> contenu;
	private int nbObjets;

/* =================================CONSTRUCTEURS====================================*/

	public Container() {
		contenu = new ArrayList<ObjetZork>();
		nbObjets = 0;
	}

	public Container(ArrayList<ObjetZork> lesObjets) {
		contenu = new ArrayList<>();
		contenu.addAll(lesObjets);
		nbObjets = 0;
	}

/* =================================GETTERS/SETTERS====================================*/

	public ArrayList<ObjetZork> getContenu() {
		return contenu;
	}

	public int getNbObjets() {
		return nbObjets;
	}

	public void setPlusNbObjets(int newNbObjets) {
		
		nbObjets+=newNbObjets;
	}

	public void setMinusNbObjets(int newNbObjets) {
		nbObjets-=newNbObjets;
	}

/* =================================METHODES====================================*/

	public boolean contient(ObjetZork oz) {
		return contenu.contains(oz);
	}

	public void ajouter(ObjetZork oz) {
		if (ajoutPossible(oz))
			contenu.add(oz);
	}

	public boolean retirer(ObjetZork oz) {
		if (retraitPossible(oz)) {
			return contenu.remove(oz);
		}
		return false;
	}

	abstract boolean retraitPossible(ObjetZork oz);

	abstract boolean ajoutPossible(ObjetZork oz);

	abstract void afficherObjets();















}