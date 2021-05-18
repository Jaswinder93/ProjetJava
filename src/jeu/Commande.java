package jeu;

import java.util.*;

public class Commande {

	private final String motCommande;
	private final String secondMot;

	public Commande(String motCommande, String secondMot) {
		this.motCommande = motCommande;
		this.secondMot = secondMot;
	}

	public String getMotCommande() {
		return motCommande;
	}

	public String getSecondMot() {
		return secondMot;
	}

	public boolean estInconnue() {
		return (motCommande == null);
	}

	public boolean aSecondMot() {
		return (secondMot != null);
	}
    
}

