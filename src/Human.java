package src;
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
	private int dE;
	private int dI;
	private int dR;
	private int posX;
	private int posY;

	Twister mtRandom = new Twister();
	public int cpt_etat=0;	

	public Human(Status s){
		this.status = s;
		this.dE = (int) (mtRandom.negExp(3));
		this.dI = (int) (mtRandom.negExp(7));
		this.dR = (int) (mtRandom.negExp(365));
		this.posX = (int) (mtRandom.genrand_int32()%300);
		this.posY = (int) (mtRandom.genrand_int32()%300);
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
	
	public int getPosX(){
		return this.posX;
	}
	
	public int getPosY(){
		return this.posY;
	}

	public double getdE() {
		return dE;
	}

	public double getdI() {
		return dI;
	}

	public double getdR() {
		return dR;
	}
	
	public void move(int posX, int posY){
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
