import java.util.Objects;

enum Status
{
	S(0),E(1),I(2),R(3);
	private final int valeur;

	private Status(int v)
	{
		valeur=v;
	}

	public int getValeur()
	{
		return valeur;
	}
}

public final class Human
{
	protected Status status;
	protected void 
	public int cpt_etat=0;	

	public Human(Status status,int cpt_etat,
