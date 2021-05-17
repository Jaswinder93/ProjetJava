package zork;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.*;

public class Jeu
{
	private AnalyseurSyntaxique AnalyseurSyntaxique;
	private Map<Animal, Piece> pieceAnimal = new HashMap<Animal, Piece>();
	private Piece pieceCourante;
	private Joueur joueur;
	private Chien chien1;
	private Chien chien2;
	private Chien chien3;
	private Loup loup1;
	private Chevre chevre1;
	private Chevre chevre2;
	private ArrayList<Chien> chiensJeu = new ArrayList<Chien>();
	private ArrayList<Piece> piecesJeu = new ArrayList<Piece>();
	private ArrayList<Animal> animauxJeu = new ArrayList<Animal>();
	private ArrayList<Loup> loupsJeu = new ArrayList<Loup>();
	private int a; // variable de fin de jeu

	public Jeu()
	{
		creerPieceEtAnimaux();
		AnalyseurSyntaxique = new AnalyseurSyntaxique();
		joueur = new Joueur();
		a = -1;
	}

	public void creerPieceEtAnimaux()
	{
		
		// creation piece premiere etape
		Piece clairiereOuest = new Piece();
		Piece lacSud = new Piece();
		Piece marecagesNord = new Piece();
		Piece riviere = new Piece();
		Piece marecagesSud = new Piece();
		Piece clairiereNord = new Piece();
		Piece zonePuits = new Piece();
		Piece entreeForet = new Piece();
		Piece lacNord = new Piece();
		Piece precipitations = new Piece();
		Piece cabane = new Piece();
		Piece clairiereEst = new Piece();
		Piece grandChene = new Piece();

		// creation des boites magiques
		ObjetZork reve = new ObjetZork("reve", EffetObjetZork.MAGIQUE, 100, marecagesSud);
		ObjetZork intuition = new ObjetZork("intuition", EffetObjetZork.MAGIQUE, 100, zonePuits);
		ObjetZork secret = new ObjetZork("secret", EffetObjetZork.MAGIQUE, 100, cabane);
		ObjetZork morphee = new ObjetZork("morphee", EffetObjetZork.MAGIQUE, 100, clairiereOuest);
		ObjetZork grace = new ObjetZork("grace", EffetObjetZork.MAGIQUE, 100, marecagesNord);
		ObjetZork magie = new ObjetZork("magie", EffetObjetZork.MAGIQUE, 100, lacNord);
		ObjetZork diamant = new ObjetZork("diamant", EffetObjetZork.MAGIQUE, 200, clairiereEst);
		ObjetZork joie = new ObjetZork("joie", EffetObjetZork.TUEUR, 100, lacSud);
		ObjetZork purete = new ObjetZork("purete", EffetObjetZork.TUEUR, 100, riviere);
		ObjetZork eclat = new ObjetZork("eclat", EffetObjetZork.TUEUR, 100, clairiereNord);
		ObjetZork faveur = new ObjetZork("faveur", EffetObjetZork.TUEUR, 100, precipitations);
		ObjetZork aisance = new ObjetZork("aisance", EffetObjetZork.TUEUR, 100, grandChene);
		ObjetZork legerete = new ObjetZork("legerete", EffetObjetZork.NEUTRE, 100, lacSud);
		ObjetZork fraicheur = new ObjetZork("fraicheur", EffetObjetZork.NEUTRE, 100, marecagesNord);
		ObjetZork flora = new ObjetZork("flora", EffetObjetZork.NEUTRE, 100, riviere);
		ObjetZork areline = new ObjetZork("areline", EffetObjetZork.NEUTRE, 100, clairiereNord);
		ObjetZork honneur = new ObjetZork("honneur", EffetObjetZork.NEUTRE, 200, cabane);
		ObjetZork titiana = new ObjetZork("titiana", EffetObjetZork.NEUTRE, 200, clairiereEst);
		ObjetZork roselia = new ObjetZork("roselia", EffetObjetZork.NEUTRE, 200, grandChene);

		ArrayList<ObjetZork> objets_clairiereOuest = new ArrayList<ObjetZork>();
		ArrayList<ObjetZork> objets_lacSud = new ArrayList<ObjetZork>();
		ArrayList<ObjetZork> objets_marecagesNord = new ArrayList<ObjetZork>();
		ArrayList<ObjetZork> objets_riviere = new ArrayList<ObjetZork>();
		ArrayList<ObjetZork> objets_marecagesSud = new ArrayList<ObjetZork>();
		ArrayList<ObjetZork> objets_clairiereNord = new ArrayList<ObjetZork>();
		ArrayList<ObjetZork> objets_zonePuits = new ArrayList<ObjetZork>();
		ArrayList<ObjetZork> objets_entreeForet = new ArrayList<ObjetZork>();
		ArrayList<ObjetZork> objets_lacNord = new ArrayList<ObjetZork>();
		ArrayList<ObjetZork> objets_precipitations = new ArrayList<ObjetZork>();
		ArrayList<ObjetZork> objets_cabane = new ArrayList<ObjetZork>();
		ArrayList<ObjetZork> objets_clairiereEst = new ArrayList<ObjetZork>();
		ArrayList<ObjetZork> objets_grandChene = new ArrayList<ObjetZork>();

		objets_clairiereOuest.add(morphee);
		objets_lacSud.add(joie);
		objets_lacSud.add(legerete);
		objets_marecagesNord.add(grace);
		objets_marecagesNord.add(fraicheur);
		objets_riviere.add(purete);
		objets_riviere.add(flora);
		objets_marecagesSud.add(reve);
		objets_clairiereNord.add(eclat);
		objets_clairiereNord.add(areline);
		objets_zonePuits.add(intuition);
		objets_lacNord.add(magie);
		objets_precipitations.add(faveur);
		objets_cabane.add(secret);
		objets_cabane.add(honneur);
		objets_clairiereEst.add(diamant);
		objets_clairiereEst.add(titiana);
		objets_grandChene.add(aisance);
		objets_grandChene.add(roselia);

		// creation des animaux

		chien1 = new Chien("chien1");
		chien2 = new Chien("chien2");
		chien3 = new Chien("chien3");
		loup1 = new Loup("loup1");
		chevre1 = new Chevre("chevre1");
		chevre2 = new Chevre("chevre2");

		chiensJeu.add(chien1);
		chiensJeu.add(chien2);
		chiensJeu.add(chien3);
		animauxJeu.add(chien1);
		animauxJeu.add(chien2);
		animauxJeu.add(chien3);
		animauxJeu.add(chevre1);
		animauxJeu.add(chevre2);
		animauxJeu.add(loup1);

		ArrayList<Animal> animaux_lacSud = new ArrayList<Animal>();
		ArrayList<Animal> animaux_marecagesNord = new ArrayList<Animal>();
		ArrayList<Animal> animaux_riviere = new ArrayList<Animal>();
		ArrayList<Animal> animaux_zonePuits = new ArrayList<Animal>();
		ArrayList<Animal> animaux_lacNord = new ArrayList<Animal>();
		ArrayList<Animal> animaux_precipitations = new ArrayList<Animal>();
		ArrayList<Animal> animaux_clairiereEst = new ArrayList<Animal>();
		ArrayList<Animal> animaux_clairiereOuest = new ArrayList<Animal>();
		ArrayList<Animal> animaux_marecagesSud = new ArrayList<Animal>();
		ArrayList<Animal> animaux_clairiereNord = new ArrayList<Animal>();
		ArrayList<Animal> animaux_entreeForet = new ArrayList<Animal>();
		ArrayList<Animal> animaux_cabane = new ArrayList<Animal>();
		ArrayList<Animal> animaux_grandChene = new ArrayList<Animal>();

		animaux_marecagesNord.add(chevre2);
		animaux_riviere.add(chien3);
		animaux_zonePuits.add(chien1);
		animaux_lacNord.add(chien2);
		animaux_precipitations.add(chevre1);
		//animaux_clairiereEst.add(loup1);
		animaux_precipitations.add(loup1);

		// creation des aliments
		Aliment oeuf = new Aliment("oeuf", clairiereOuest, 6, TypeAliment.ANIMAL);
		Aliment carcasse = new Aliment("carcasse", lacSud, 7, TypeAliment.ANIMAL);
		Aliment poulet = new Aliment("poulet", marecagesNord, 8, TypeAliment.ANIMAL);
		Aliment oiseau = new Aliment("oiseau", riviere, 6, TypeAliment.ANIMAL);
		Aliment ecureuil = new Aliment("ecureuil", marecagesSud, 7, TypeAliment.ANIMAL);
		Aliment insectes = new Aliment("insectes", clairiereNord, 8, TypeAliment.ANIMAL);
		Aliment cadavre = new Aliment("cadavre", zonePuits, 6, TypeAliment.ANIMAL);
		Aliment charogne = new Aliment("charogne", lacNord, 7, TypeAliment.ANIMAL);
		Aliment chair = new Aliment("chair", precipitations, 8, TypeAliment.ANIMAL);
		Aliment os = new Aliment("os", cabane, 6, TypeAliment.ANIMAL);
		Aliment foin = new Aliment("foin", clairiereEst, 7, TypeAliment.VEGETAL);
		Aliment choux = new Aliment("choux", grandChene, 8, TypeAliment.VEGETAL);
		Aliment cereales = new Aliment("cereales", clairiereOuest, 6, TypeAliment.VEGETAL);
		Aliment herbes = new Aliment("herbes", lacSud, 7, TypeAliment.VEGETAL);
		Aliment tomates = new Aliment("tomates", marecagesNord, 8, TypeAliment.VEGETAL);
		Aliment noix = new Aliment("noix", riviere, 6, TypeAliment.VEGETAL);
		Aliment faines = new Aliment("faines", marecagesSud, 7, TypeAliment.VEGETAL);
		Aliment glands = new Aliment("glands", clairiereNord, 8, TypeAliment.VEGETAL);
		Aliment pignons = new Aliment("pignons", zonePuits, 6, TypeAliment.VEGETAL);
		Aliment chataignes = new Aliment("chataignes", lacNord, 7, TypeAliment.VEGETAL);
		Aliment graines = new Aliment("graines", precipitations, 8, TypeAliment.VEGETAL);
		Aliment champignons = new Aliment("champignons", cabane, 6, TypeAliment.VEGETAL);
		Aliment amanite = new Aliment("amanite", clairiereEst, 7, TypeAliment.VEGETAL);
		Aliment bolet = new Aliment("bolet", grandChene, 8, TypeAliment.VEGETAL);
		Aliment framboise = new Aliment("framboise", zonePuits, 6, TypeAliment.VEGETAL);
		Aliment polypore = new Aliment("polypore", riviere, 6, TypeAliment.VEGETAL);

		ArrayList<Aliment> aliments_clairiereOuest = new ArrayList<Aliment>();
		ArrayList<Aliment> aliments_lacSud = new ArrayList<Aliment>();
		ArrayList<Aliment> aliments_marecagesNord = new ArrayList<Aliment>();
		ArrayList<Aliment> aliments_riviere = new ArrayList<Aliment>();
		ArrayList<Aliment> aliments_marecagesSud = new ArrayList<Aliment>();
		ArrayList<Aliment> aliments_clairiereNord = new ArrayList<Aliment>();
		ArrayList<Aliment> aliments_zonePuits = new ArrayList<Aliment>();
		ArrayList<Aliment> aliments_entreeForet = new ArrayList<Aliment>();
		ArrayList<Aliment> aliments_lacNord = new ArrayList<Aliment>();
		ArrayList<Aliment> aliments_precipitations = new ArrayList<Aliment>();
		ArrayList<Aliment> aliments_cabane = new ArrayList<Aliment>();
		ArrayList<Aliment> aliments_clairiereEst = new ArrayList<Aliment>();
		ArrayList<Aliment> aliments_grandChene = new ArrayList<Aliment>();

		aliments_clairiereOuest.add(oeuf);
		aliments_clairiereOuest.add(cereales);
		aliments_lacSud.add(carcasse);
		aliments_lacSud.add(herbes);
		aliments_marecagesNord.add(poulet);
		aliments_marecagesNord.add(tomates);
		aliments_riviere.add(oiseau);
		aliments_riviere.add(noix);
		aliments_riviere.add(polypore);
		aliments_marecagesSud.add(ecureuil);
		aliments_marecagesSud.add(faines);
		aliments_clairiereNord.add(insectes);
		aliments_clairiereNord.add(glands);
		aliments_zonePuits.add(cadavre);
		aliments_zonePuits.add(pignons);
		aliments_zonePuits.add(framboise);
		aliments_lacNord.add(charogne);
		aliments_lacNord.add(chataignes);
		aliments_precipitations.add(chair);
		aliments_precipitations.add(graines);
		aliments_cabane.add(os);
		aliments_cabane.add(champignons);
		aliments_clairiereEst.add(foin);
		aliments_clairiereEst.add(amanite);
		aliments_grandChene.add(choux);
		aliments_grandChene.add(bolet);

		// creation pieces deuxieme etape
		clairiereOuest = new Piece("clairiereOuest", animaux_clairiereOuest, objets_clairiereOuest, aliments_clairiereOuest);
		lacSud = new Piece("lacSud", animaux_lacSud, objets_lacSud, aliments_lacSud);
		marecagesNord = new Piece("marecagesNord", animaux_marecagesNord, objets_marecagesNord, aliments_marecagesNord);
		riviere = new Piece("riviere", animaux_riviere, objets_riviere, aliments_riviere);
		marecagesSud = new Piece("marecagesSud", animaux_marecagesSud, objets_marecagesSud, aliments_marecagesSud);
		clairiereNord = new Piece("clairiereNord", animaux_clairiereNord, objets_clairiereNord, aliments_clairiereNord);
		zonePuits = new Piece("zonePuits", animaux_zonePuits, objets_zonePuits, aliments_zonePuits);
		entreeForet = new Piece("entreeForet", animaux_entreeForet, objets_entreeForet, aliments_entreeForet);
		lacNord = new Piece("lacNord", animaux_lacNord, objets_lacNord, aliments_lacNord);
		precipitations = new Piece("precipitations", animaux_precipitations, objets_precipitations, aliments_precipitations);
		cabane = new Piece("cabane", animaux_cabane, objets_cabane, aliments_cabane);
		clairiereEst = new Piece("clairiereEst", animaux_clairiereEst, objets_clairiereEst, aliments_clairiereEst);
		grandChene = new Piece("grandChene", animaux_grandChene, objets_grandChene, aliments_grandChene);


		//piece courante des animaux
		chien1.setPieceCourante(zonePuits);
		chien2.setPieceCourante(lacNord);
		chien3.setPieceCourante(riviere);
		loup1.setPieceCourante(precipitations);
		chevre1.setPieceCourante(precipitations);
		chevre2.setPieceCourante(marecagesNord);

		loupsJeu.add(loup1);

		// initialise les sorties des pieces
		clairiereOuest.setSorties(null, riviere, lacSud, null);
		marecagesNord.setSorties(null, clairiereNord, riviere, null);
		riviere.setSorties(marecagesNord, zonePuits, marecagesSud, clairiereOuest);
		marecagesSud.setSorties(riviere, entreeForet, null, lacSud);
		clairiereNord.setSorties(null, lacNord, zonePuits, marecagesNord);
		zonePuits.setSorties(clairiereNord, precipitations, entreeForet, riviere);
		entreeForet.setSorties(zonePuits, cabane, null, marecagesSud);
		lacNord.setSorties(null, null, precipitations, clairiereNord);
		precipitations.setSorties(lacNord, clairiereEst, cabane, zonePuits);
		cabane.setSorties(precipitations, grandChene, null, entreeForet);
		clairiereEst.setSorties(null, null, grandChene, precipitations);
		grandChene.setSorties(clairiereEst, null, null, cabane);
		lacSud.setSorties(clairiereOuest, marecagesSud, null, null);

		piecesJeu.add(lacSud);
		piecesJeu.add(clairiereOuest);
		piecesJeu.add(marecagesNord);
		piecesJeu.add(riviere);
		piecesJeu.add(marecagesSud);
		piecesJeu.add(clairiereNord);
		piecesJeu.add(zonePuits);
		piecesJeu.add(entreeForet);
		piecesJeu.add(lacNord);
		piecesJeu.add(precipitations);
		piecesJeu.add(cabane);
		piecesJeu.add(clairiereEst);
		piecesJeu.add(grandChene);

		setPieceAnimal(chevre2, marecagesNord);
		setPieceAnimal(chien3, riviere);
		setPieceAnimal(chien1, zonePuits);
		setPieceAnimal(chien2, lacNord);
		setPieceAnimal(chevre1, precipitations);
		setPieceAnimal(loup1, precipitations);

		pieceCourante = entreeForet;
		joueur = new Joueur();

	}

	public final static void clearConsole(){
 		for(int i=0;i<100;i++)
    		System.out.println();
	}	

	public void jouer() {
		clearConsole();
		afficherMsgBienvennue();
		System.out.println(pieceCourante.descriptionSorties());
		System.out.println("----------------------------------------------------");
		Commande commande = AnalyseurSyntaxique.getCommande();


		// Entrée dans la boucle principale du jeu. Cette boucle lit
		// et exécute les commandes entrées par l'utilisateur, jusqu'a
		// ce que la commande choisie soit la commande "quitter"
		boolean termine = false;
		Joueur joueur = new Joueur();
		while (!termine) {
			clearConsole();
			termine = traiterCommande(commande, joueur);
			if(termine == true){
				return;
			}
			//pieceCourante.afficherPlan();
			System.out.println("=============================================================");
			pieceCourante.afficherObjets();
			pieceCourante.afficherChiens(joueur);
			System.out.println("=============================================================");
			joueur.afficherObjets();
			joueur.afficherChiens();
			System.out.println("=============================================================");
			AnalyseurSyntaxique.afficherToutesLesCommandes();
			System.out.println("Vous etes dans la piece "+pieceCourante.getNom());
			System.out.println(pieceCourante.descriptionSorties());
			System.out.println("A vos calculs ! \n");
			commande = AnalyseurSyntaxique.getCommande();
		}
		return;
	}

	public void afficherMsgBienvennue()
	{
		System.out.println();
		System.out.println("Bienvenue dans la foret !");
		System.out.println("Aujourd'hui vous allez devoir faire preuve de courage...");
		System.out.println("... car a tout moment, vous pouvez mourir !");
		System.out.println("En vous balandant dans la foret, vous serez amenes a trouver des boites secretes.");
		System.out.println("Celles-ci peuvent vous faire gagner (boites magiques), ou vous faire perdre (boites poison).");
		System.out.println("C'est vous et votre chance !");
		System.out.println("Pour gagner, il vous suffit de ramasser trois boites magiques gagnantes.");
		System.out.println("De la meme maniere, si vous ramassez trois boites contenant du poison, vous perdez.");
		System.out.println("Attention : Seules les boites à effet NEUTRE peuvent etre redéposées dans les pieces.");
		System.out.println("On vous indiquera les effets des boites a chaque fois.");
		System.out.println();
		System.out.println("Certaines boites sont trop lourdes pour vous, ce sont celles qui pesent");
		System.out.println("plus de 100 grammes. Malheureusement, vous ne connaitrez jamais leur effet.");
		System.out.println("De plus, vous avez une certaine masse totale à ne pas depasser.");
		System.out.println("Vous ne pouvez porter plus de 700 grammes. A un certain stade du jeu, il faudra");
		System.out.println("faire des choix de boites (en poser certaines pour en prendre d'autres).");
		System.out.println();
		System.out.println("Vous pouvez adopter des chiens pour qu'ils vous tiennent compagnie.");
		System.out.println("De meme, vous pouvez les liberer si vous en etes lassé.\n");
		AnalyseurSyntaxique.afficherToutesLesCommandes();
		System.out.println("ATTENTION : Agrandissez la fenetre de votre Terminal pour une vision optimale du jeu et du plan de la foret obtenu grace a la commande 'plan'.");
		pieceCourante.afficherPlan();
		System.out.println("Vous vous trouvez actuellement a l'entree.");
		System.out.println("A vous de jouer !");
	}

	/**
	* 
	*/
	/*@
	  @
	  @*/
	/**
	* @pure
	*/
	public boolean traiterCommande(Commande commande, Joueur joueur)
	{
		if (commande.estInconnue()) {
			System.out.println("Je ne comprends pas ce que vous voulez... Attention a la syntaxe de vos commandes.");
			return false;
		}
		String motCommande = commande.getMotCommande();
		if (motCommande.equals("aide"))
			commandeAide(commande, motCommande);
		else if (motCommande.equals("aller"))
			commandeAller(commande, joueur);
		else if (motCommande.equals("plan"))
			pieceCourante.afficherPlan();
		else if (motCommande.equals("quitter"))
		{
			if (commande.aSecondMot())
				System.out.println("Quitter quoi ?");
			else{
				System.out.println("Merci d'avoir joue.  Au revoir.");
				return true;
			}
		}
		else if (motCommande.equals("prendre"))
			commandePrendre(commande, joueur, pieceCourante);
			if(a == 1 || a == 0) // variable de fin de jeu
				return true;
		else if (motCommande.equals("poser"))
			commandePoser(commande, joueur, pieceCourante);
		else if (motCommande.equals("adopter"))
		{
			commandeAdopter(commande, joueur, pieceCourante);
		}
		else if (motCommande.equals("liberer"))
			commandeLiberer(commande, joueur, pieceCourante);
		return false;
	}

	/**
	* 
	*/
	/*@
	  @
	  @*/
	/**
	* @pure
	*/
	public boolean deplacerVersAutrePiece(Commande commande, Joueur joueur)
	{
		if (!commande.aSecondMot())
		{
			// si la commande ne contient pas de second mot, nous ne
			// savons pas ou aller..
			System.out.println("Aller où ? Merci de respecter la syntaxe suivante: aller + direction");
			return false;
		}

		String direction = commande.getSecondMot();
		Direction d = null;
		try
		{
		    d = Direction.valueOf(direction);
		}
		catch (IllegalArgumentException e)
		{
		    System.out.println("Pour indiquer une direction entrez une des chaînes de caractères suivantes:");
		    for (Direction dok : Direction.values())
		    {
		      System.out.println("-> \"" + dok + "\"");
		    }
		    return false;
		}

		// Tentative d'aller dans la direction indiquée.
		Piece pieceSuivante = pieceCourante.pieceSuivante(d);

		if (pieceSuivante == null) {
			System.out.println("Il n'y a pas de piece dans cette direction!");
			return false;
		} else {
			joueur.setFuturDplct(d);
			if(!(joueur.getChiens().isEmpty()))
			{
				for(Chien ch : joueur.getChiens())
				{
					ch.setPieceAQuitter(pieceCourante);
					ch.proposerSortie(pieceCourante, d);
				}
			}
			joueur.setPieceCourante(pieceSuivante);
			pieceCourante = joueur.getPieceCourante();
			if(!(joueur.getChiens().isEmpty()))
			{
				Piece newPiece = new Piece();
				for(Chien ch : joueur.getChiens())
				{
					newPiece = ch.deplacerDepuis(ch.getPieceCourante());
					setPieceAnimal(ch, newPiece);
					pieceCourante.retirerChien(ch);
				}
			}
			if(!(joueur.getChiens().isEmpty()))
			{
				for(Chien ch : joueur.getChiens())
				{
					pieceCourante.ajouterChien(ch);
				}
			}
		}
		return true;
	}

	/* ============================================== METHODES AJOUTEES ==============================================*/

	/* ======================================== RELATIVES AUX COMMANDES DU JOUEUR ====================================*/

	/**
	* 
	* @pure
	*/
	public void suiteActionJoueur()
	{	
		Piece newPiece = new Piece();
		for(Animal ani : animauxJeu)
		{
			if (!(ani instanceof Loup) && ani.estVivant())
			{
				ani.choisirAliment(pieceAnimal.get(ani));
				ani.ramasserDans(pieceAnimal.get(ani));
				ani.manger();
				if(ani.estVivant())
				{
					newPiece = ani.deplacerDepuis(pieceAnimal.get(ani));
					setPieceAnimal(ani, newPiece);
				}
			}
		}
		for(Loup l : loupsJeu)
		{
			if(l.estVivant())
			{
				Animal chasse = l.chasser(pieceAnimal.get(l));
				if(chasse == null)
				{
					l.choisirAliment(pieceAnimal.get(l));
					l.ramasserDans(pieceAnimal.get(l));
					l.manger();
					if(l.estVivant())
					{
						newPiece = l.deplacerDepuis(pieceAnimal.get(l));
						setPieceAnimal(l, newPiece);
					}
				}
			}
		}
	}

	/**
	* 
	*/
	/*@
	  @
	  @*/
	/**
	* @pure
	*/
	public void commandeAide(Commande commande, String motCommande)
	{
		if(motCommande.equals("aide") && commande.getSecondMot().equals("aller")){
			System.out.println("La commande 'aller' vous permet de vous deplacer dans la foret. Il faut la faire suivre d'une direction 'NORD' ou 'EST' ou 'SUD' ou 'OUEST' en majuscule pour pouvoir se deplacer.");
		} else if(motCommande.equals("aide") && commande.getSecondMot().equals("quitter")){
			System.out.println("La commande 'quitter' vous permet de quitter le jeu. Elle s'utilise seule.");
		} else if(motCommande.equals("aide") && commande.getSecondMot().equals("aide")){
			System.out.println("La commande 'aide' vous eclaire quant aux differentes commandes disponibles dans le jeu. Elle s'utilise seule");
		} else if(motCommande.equals("aide") && commande.getSecondMot().equals("plan")){
			System.out.println("La commande 'plan' vous permet d'avoir le plan de la foret partout avec vous. Elle s'utilise seule.");
		} else if(motCommande.equals("aide") && commande.getSecondMot().equals("prendre")){
			System.out.println("La commande 'prendre' vous permet de prendre une boite. Elle su'ilise comme suit : prendre boite_que_vous_voulez");
		} else if(motCommande.equals("aide") && commande.getSecondMot().equals("poser")){
			System.out.println("La commande 'poser' vous permet de prendre une boite du joueur et de la poser dans la piece ou vous vous trouvez. Elle s'utilise comme suit : poser boite_que_vous_voulez_poser");
		} else if(motCommande.equals("aide") && commande.getSecondMot().equals("adopter")){
			System.out.println("La commande 'adopter' vous permet d'adopter le chien qui se trouve dans la piece ou vous etes. Elle s'utilise comme suit : adopter chien_que_vous_souhaitez");
		} else if(motCommande.equals("aide") && commande.getSecondMot().equals("liberer")){
			System.out.println("La commande 'liberer' vous permet de liberer un chien que vous possedez, dans la piece ou vous vous trouvez. La commande s'utilise comme suit : liberer chien_que_vous_souhaitez");
		} else
			System.out.println("Tapez 'aide' suivi de la commande qui vous pose probleme si vous avez besoin d'aide.");
	}

	/**
	* 
	*/
	/*@
	  @
	  @*/
	/**
	* @pure
	*/
	public void commandeAller(Commande commande, Joueur joueur)
	{
		if (deplacerVersAutrePiece(commande, joueur) == true){
			suiteActionJoueur();
		}
		return;
	}

	/**
	* 
	*/
	/*@
	  @
	  @*/
	/**
	* @pure
	*/
	public void commandePrendre(Commande commande, Joueur joueur, Piece pieceCourante)
	{
		boolean entreeFor = false;
		boolean succes = false;
		ajouterChiensDuJoueur(joueur, pieceCourante);
		for(ObjetZork oz : pieceCourante.getContenu())
		{
			entreeFor = true;
			if(commande.getSecondMot().equals(oz.getNom()))
			{
				succes = joueur.prendreObjet(oz);
				if(succes == true)
				{
					pieceCourante.setMinusNbObjets(1);
					pieceCourante.retirer(oz);
					System.out.println("Boite prise avec succes");
					if(joueur.getNbBoitesMagiques() == 3)
					{
						System.out.println("Bravo !!! Vous avez ramasse trois boites magiques. Vous avez gagne.");
						a = 1;
						return;
					}
					if(joueur.getNbBoitesPoison() == 3)
					{
						System.out.println("Non !!! Vous avez ramassé trois boites poison. Vous avez perdu.");
						a = 0;
						return;
					}
					suiteActionJoueur();
					retirerChiensDuJoueur(joueur, pieceCourante);
				}
				return;
			}
		}
		if(entreeFor == false)
			System.out.println("Veuillez indiquer correctement le nom de la boite que vous souhaitez reposer.");
		System.out.println("L'objet n'a pas pu etre pris");
		retirerChiensDuJoueur(joueur, pieceCourante);
	}

	/**
	* 
	*/
	/*@
	  @
	  @*/
	/**
	* @pure
	*/
	public void commandePoser(Commande commande, Joueur joueur, Piece pieceCourante)
	{
		ajouterChiensDuJoueur(joueur, pieceCourante);
		boolean entreeFor = false;
		boolean succes = false;
		for(ObjetZork oz : joueur.getContenu())
		{
			entreeFor = true;
			if(commande.getSecondMot().equals(oz.getNom()))
			{
				succes = joueur.poserObjet(oz);
				if(succes == true)
				{
					pieceCourante.setPlusNbObjets(1);
					pieceCourante.ajouter(oz);
					suiteActionJoueur();
					retirerChiensDuJoueur(joueur, pieceCourante);
					return;
				}
			}
		}
		if(entreeFor == false)
			System.out.println("Veuillez indiquer correctement le nom de la boite que vous souhaitez reposer.");
		retirerChiensDuJoueur(joueur, pieceCourante);
	}

	/**
	* 
	*/
	/*@
	  @
	  @*/
	/**
	* @pure
	*/
	public void commandeAdopter(Commande commande, Joueur joueur, Piece pieceCourante)
	{
		int a = 0; // variable de succes
		for(Chien ch : chiensJeu)
		{
			// Si le chien passe en commande existe bien dans le jeu
			// Que le chien voulu est bien dans la piece courante du joueur
			// Et que le chien demande n'appartient pas deja au joueur
			if(ch.getNom().equals(commande.getSecondMot()))
			{
				joueur.setChienAdoptableVoulu(ch);
				if(joueur.getChienAdoptableVoulu().getPieceCourante().equals(joueur.getPieceCourante()) && !(joueur.contientChienDeCeNom(joueur.getChienAdoptableVoulu())))
				{
					joueur.adopterChien(joueur.getChienAdoptableVoulu());
					joueur.setChienAdoptableVoulu(null);
					System.out.println("Chien adopte avec succes.");
					a = 1;
					ajouterChiensDuJoueur(joueur, pieceCourante);
					suiteActionJoueur();
				}

			}			
		}
		if(a == 0)
		{
			System.out.println("Le chien demande n'a pas pu etre adopte.");
			return;
		}
	}

	/**
	* 
	*/
	/*@
	  @
	  @*/
	/**
	* @pure
	*/
	public void commandeLiberer(Commande commande, Joueur joueur, Piece pieceCourante)
	{
		int a = 0; // variable de succes
		for(Chien ch : joueur.getChiens())
		{
			// Si le chien qu'on veut liberer appartient au joueur
			if(ch.getNom().equals(commande.getSecondMot()))
			{
				pieceCourante.getAnimaux().remove(ch);
				joueur.setChienLiberable(ch);
				if(joueur.getChienLiberable().getPieceCourante().equals(joueur.getPieceCourante()) && (joueur.contientChienDeCeNom(joueur.getChienLiberable())))
				{
					ajouterChiensDuJoueur(joueur, pieceCourante);
					joueur.libererChien(joueur.getChienLiberable());
					joueur.setChienLiberable(null);
					System.out.println("Chien libere avec succes.");
					a = 1;
					suiteActionJoueur();	
					return;
				}
				else
					System.out.println("Le joueur ne contient pas de chien de ce nom");
			}
		}
		if(a == 0)
		{
			System.out.println("Le chien demande n'a pas pu etre libere.");
			return;
		}
	}

	/* ======================== METHODES RELATIVES A LA HASHMAP ASSOCIANT UNE PIECE A UN CHIEN =============================*/

	/**
	* 
	*/
	/*@
	  @
	  @*/
	/**
	* @pure
	*/
	public void setPieceAnimal(Animal ani, Piece p) {
			pieceAnimal.put(ani, p);
	}

	/**
	* 
	*/
	/*@
	  @
	  @*/
	/**
	* @pure
	*/
	public void ajouterChiensDuJoueur(Joueur joueur, Piece pieceCourante)
	{
		if(!(joueur.getChiens().isEmpty()))
			{
				for(Chien ch : joueur.getChiens())
				{
					pieceCourante.ajouterChien(ch);
					setPieceAnimal(ch, pieceCourante);
				}
			}
	}

	/**
	* 
	*/
	/*@
	  @
	  @*/
	/**
	* @pure
	*/
	public void retirerChiensDuJoueur(Joueur joueur, Piece pieceCourante)
	{
		if(!(joueur.getChiens().isEmpty()))
			{
				for(Chien ch : joueur.getChiens())
				{
					pieceCourante.retirerChien(ch);
					pieceAnimal.remove(ch, pieceCourante);
				}
			}
	}


}