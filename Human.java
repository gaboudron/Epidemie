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
	private char status;
	private int dE;
	private int dI;
	private int dR;
	private int posX;
	private int poxY;
	
	public int cpt_etat=0;	

	public Human(char status){
		this.status = status;
		this.dE = mt.negExp(3);
		this.dI = mt.negExp(7);
		this.dR = mt.negExp(365);
		this.posX = mt.genrand_int32()%300;
		this.posY = mt.genrand_int32()%300;
		
	
	}
	
	public void resetCompteurEtat(){
		this.cpt_etat = 0;
	}
	
	
	public char getStatus(){
		return this.status;
	}
	
	public char setStatus(char s){
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
	
	
	public void move(int posX, int posY){
		this.posX = posX;
		this.posY = posY;
	}
	
	public void infection(){
		this.status = 'I';
		this.cpt_etat = 0; 
	}
	
	public int getCpt(){
		return this.cpt_etat;
	}
	
	
	
	
}
