public interface Adoptable extends Animal {

	boolean estLibre();

	boolean adoption();

	boolean liberation();

	void proposerSortie(Piece p, Direction d);

	Direction getSortieProposee(Piece p);

	@Override
	Direction choisirSortie(Piece p);

	@Override
	Piece deplacerDepuis(Piece p);

}