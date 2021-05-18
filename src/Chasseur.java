import java.util.Set;

public interface Chasseur extends Animal {

	public Animal choisirProie(Piece p);

	public Set<Class<? extends Animal>> getRegimeAlimentaireChasse();

	public boolean peutChasser(Animal ani);

	public Animal chasser(Piece p);

	public int getCoutChasse();

}