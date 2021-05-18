package jeu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AnalyseurSyntaxique {

	private final MotCleCommande commandes;

	public AnalyseurSyntaxique() {
		commandes = new MotCleCommande();
	}

	public Commande getCommande() {
		// pour memoriser la ligne entree par l'utilisateur
		String ligneEntree = "";
		String mot1;
		String mot2;
        String mot3;

		// affiche l'invite de commande
		System.out.print("> ");

		BufferedReader reader = new BufferedReader(new InputStreamReader(
			System.in));
		try {
			ligneEntree = reader.readLine();
		} catch (java.io.IOException exc) {
			System.out.println("Une erreur est survenue pendant la lecture de votre commande: "
				 + exc.getMessage());
		}

		StringTokenizer tokenizer = new StringTokenizer(ligneEntree);

		if (tokenizer.hasMoreTokens()) {
			// recuperation du permier mot (le mot commande)
			mot1 = tokenizer.nextToken();
		} else {
			mot1 = null;
		}
		if (tokenizer.hasMoreTokens()) {
			// recuperation du second mot
			mot2 = tokenizer.nextToken();
		} else {
			mot2 = null;
		}
        if (tokenizer.hasMoreTokens()) {
			// recuperation du troisieme mot
			mot3 = tokenizer.nextToken();
		} else {
			mot3 = null;
		}

		// note: le reste de la ligne est ignore.

		// Teste si le permier mot est une commande valide, si ce n'est pas
		// le cas l'objet renvoye l'indique
		if (commandes.estCommande(mot1)) {
			return new Commande(mot1, mot2);
		} else {
			return new Commande(null, mot2);
		}
	}

	public void afficherToutesLesCommandes() {
		System.out.println("Commandes possibles :");
		commandes.afficherToutesLesCommandes();
		System.out.println("Tapez 'aide' suivi de la commande qui vous pose probleme si vous avez besoin d'aide.");
		System.out.println();
	}
}

