public class MotCleCommande {

	private final static String[] commandesValides = {"aller", "quitter", "aide", "plan", "prendre", "poser", "adopter", "liberer"};

	public MotCleCommande() { }

	public boolean estCommande(String aString) {
		for (String commandesValide : commandesValides) {
			if (commandesValide.equals(aString)) {
				return true;
			}
		}
		return false;
	}

	public void afficherToutesLesCommandes() {
		for (String commandesValide : commandesValides) {
			System.out.print(".) " + commandesValide + "\n");
		}
		System.out.println();
	}
}

