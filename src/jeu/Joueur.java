package jeu;

import animal.Chien;

import java.util.ArrayList;

public class Joueur extends Container<ObjetZork> {

	private Direction futur_dplct;
	private Piece pieceCourante;
	private final ArrayList<Chien> chiens;
	private int poidsCourant;
	private int nbChiens;
	private int nbBoitesMagiques;
	private int nbBoitesPoison;
	private Chien chienAdoptableVoulu;
	private Chien chienLiberable;
	public static final int max_poids_total = 700;

/*=====================================CONSTRUCTEURS========================================*/

	public Joueur(){
		super();
		chiens = new ArrayList<>();
	}

/*=====================================GETTERS/SETTERS========================================*/

	public Piece getPieceCourante() {
		return pieceCourante;
	}

	public int getPoidsCourant() {
		return poidsCourant;
	}

	public int getNbChiens() {
		return nbChiens;
	}

	public ArrayList<Chien> getChiens() {
		return chiens;
	}

	public Direction getFuturDplct() {
		return futur_dplct;
	}

	public void setFuturDplct(Direction d) {
		futur_dplct = d;
	}

	public int getNbBoitesMagiques() {
		return nbBoitesMagiques;
	}

	public int getNbBoitesPoison() {
		return nbBoitesPoison;
	}

	public void setPlusNbBoitesMagiques(int v) {
		nbBoitesMagiques += v;
	}

	public void setPlusNbBoitesPoison(int v) {
		nbBoitesPoison += v;
	}

	public void setMoinsNbBoitesMagiques(int v) {
		nbBoitesMagiques -= v;
	}

	public void setMoinsNbBoitesPoison(int v) {
		nbBoitesPoison -= v;
	}

	public Chien getChienAdoptableVoulu() {
		return chienAdoptableVoulu;
	}

	public void setChienAdoptableVoulu(Chien ch) {
		chienAdoptableVoulu = ch;
	}

	public Chien getChienLiberable() {
		return chienLiberable;
	}

	public void setChienLiberable(Chien ch) {
		chienLiberable = ch;
	}

	public void setPieceCourante(Piece p) {
		pieceCourante = p;
	}

/*=====================================PRISE ET POSE D'OBJETS========================================*/

	@Override
	public boolean ajoutPossible(ObjetZork oz) {
		if (((getPoidsCourant() + oz.getPoids()) > max_poids_total))
			System.out.println("Le poids total de vos objets a excede 700 grammes ! Veuillez poser une boite a effet neutre avant de prendre cet objet.\n");
		if ((!oz.estTransportable()))
			System.out.println("Votre objet est trop lourd ! Le poids ne doit pas exceder 100 grammes");
		return oz.estTransportable() && getPoidsCourant() + oz.getPoids() <= max_poids_total;
	}

	@Override
	public void afficherObjets() {
		System.out.println("Si vous en portez, voici vos boites : ");
		if (!(getContenu().isEmpty())) {
			for(ObjetZork oz : getContenu()) {
				System.out.println("Nom : " + oz.getNom() + "; effet : " + oz.getEffet() + "; poids : " + oz.getPoids());
			}
		}
		System.out.println();
	}

	public boolean prendreObjet(ObjetZork oz) {
		if (ajoutPossible(oz)){
			ajouter(oz);
			poidsCourant += oz.getPoids();
			setPlusNbObjets(1);
			if (oz.getEffet().equals(EffetObjetZork.MAGIQUE)) {
				System.out.println("Ouf ! Vous venez de prendre une boite magique !");
				nbBoitesMagiques++;
			} else if(oz.getEffet().equals(EffetObjetZork.TUEUR)) {
				System.out.println("Oups... Vous venez de prendre une boite poison.");
				nbBoitesPoison++;
			} else
				System.out.println("Boite neutre ! Rien a signaler.");
			return true;
		} else {
			System.out.println("La prise d'objet a echoue.");
			return false;
		}
	}

	public boolean poserObjet(ObjetZork oz) {
		if ((contient(oz)) && oz.getEffet().equals(EffetObjetZork.NEUTRE)) {
			poidsCourant -= oz.getPoids();
			setMinusNbObjets(1);
			retirer(oz);
			return true;
		} else {
			System.out.println("Attention. Verifiez que vous portez bien cet objet ET que l'effet de cet objet est neutre.");
		}
		return false;
	}


/*=====================================CHIENS ADOPTES========================================*/

	public boolean adopterChien(Chien chien) {
		if (pieceCourante.contientAnimal(chien)) {
			nbChiens++;
			chiens.add(chien);
			chien.adoption();
			//pieceCourante.getAnimaux().remove(chien);
			chien.setJoueur(this);
			return true;
		}
		return false;
	}

	public boolean libererChien(Chien chien) {
		if (chiens.contains(chien)) {
			nbChiens--;
			chiens.remove(chien);
			chien.liberation();
			//pieceCourante.getAnimaux().add(chien);
			chien.setJoueur(null);
			return true;
		}
		return false;
	}

	public void afficherChiens() {
		System.out.println("Si vous en avez, voici vos chiens :");
		for (int i = 0 ; i < nbChiens ; i++){
			if(chiens.get(i).estVivant())
				System.out.println(chiens.get(i).getNom() + "  "); 
		}
		System.out.println();
	}

	public boolean contientChienDeCeNom(Chien ch) {
		for (Chien chien : chiens) {
			if (chien.getNom().equals(ch.getNom()))
				return true;
		}
		return false;
	}

	@Override
	public boolean retraitPossible(ObjetZork oz) {
		return contient(oz) && oz.estTransportable();
	}














}