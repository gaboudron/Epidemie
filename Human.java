import java.util.Objects;

enum Status
{
	S(0),E(1),I(2),R(3);
	private final int valeur;

	private Status(int v)
	{
		valeur = v;
	}

	public int getValeur()
	{
		return valeur;
	}
}

public final class Human
{
	protected Status status;
	private double dE;
	private double dI;
	private double dR;
	private double posX;
	private double posY;

	Twister mtRandom = new Twister();
	public int cpt_etat=0;	

	public Human(Status status){
		this.status = status;
		this.dE = mtRandom.negExp(3);
		this.dI = mtRandom.negExp(7);
		this.dR = mtRandom.negExp(365);
		this.posX = mtRandom.genrand_int32()%300;
		this.posY = mtRandom.genrand_int32()%300;
	}
	
	public void resetCompteurEtat(){
		this.cpt_etat = 0;
	}
	
	public Status getStatus(){
        return this.status;
    }

	public void setStatus(Status s){
        this.status = s;
    }
	
	public void incrementCpt(){
		this.cpt_etat++;
	}
	
	public double getPosX(){
		return this.posX;
	}
	
	public double getPosY(){
		return this.posY;
	}
	
	public void move(double posX, double posY){
		this.posX = posX;
		this.posY = posY;
	}
	
	public void infection(){
		this.status = Status.I;
		this.cpt_etat = 0; 
	}
	
	public int getCpt(){
		return this.cpt_etat;
	}
}
