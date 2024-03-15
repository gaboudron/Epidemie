import java.util.ArrayList;
import java.util.Collections;

class Monde
{
	private int[][] carteNbInfecte = new int[300][300];

	// Initialize the matrix with 0
	for (int i = 0; i < 300; i++) {
	    for (int j = 0; j < 300; j++) {
		carteNbInfecte[i][j] = 0;
	    }
	}

	int nbS = 0;
	int nbE = 0;
	int nbI = 0;
	int nbR = 0;
	Twister mtRandom;
	List<Human> listeHumain;
	List<Human> listeInfected;
	
	public Monde(Twister random)
	{
		listeHumain = new ArrayList<Human>();
		for(int i = 0; i<19980; i++){
			listeHumain.add(new Human('S'));
			this.nbS++;
		}
		for(int i = 0; i<20; i++){
			listeHumain.add(new Human('I'));
			this.nbI++;
		}
		this.mtRandom = random;
		
		for(Human h : listeHumain){
			if(h.getStatus() == 'I'){
				carteNbInfecte[h.getPosX()][h.getPosY()]++;
			}
		}
	}
	
	public void runSimulation(int numFichier){
		
		//0 - Créer un fichier avec numFichier
		String filename = "resultat" + numFichier + ".csv";

		FileWriter writer = new FileWriter(filename);
		
		for(int i = 0; i < 730; i++){
			//1 - Sauvegarder nbS, nbE, nbI, nbR dans fichier resultat[numFichier].csv

			// Writing values to CSV
			writer.write(this.nbS + "," + this.nbE + "," + this.nbI + "," + this.nbR + "\n");
			
			//2 - Parcourir les humains pour les traiter.
			//shuffle(listeHumain, mtRandom);
			for(Human h : listeHumain){
				char status = h.getStatus();

				switch (status) {
				    case 'S':
					handleSusceptibleHuman(h);
					break;
				    case 'E':
					handleExposedHuman(h);
					break;
				    case 'I':
					handleInfectedHuman(h);
					break;
				    case 'R':
					handleRecoveredHuman(h);
					break;
				    default:
					System.out.println("Unknown status");
					// Add code to handle other statuses if needed
					break;
			}	
			
			
			
		} 
	
	
	}
	
	public void handleSusceptibleHuman(){
		h.move(this.mtRandom.genrand_int32()%300,this.mtRandom.genrand_int32()%300);
		contamine(h);
		
	}
	
	public void handleExposedHuman(){
		h.move(this.mtRandom.genrand_int32()%300,this.mtRandom.genrand_int32()%300);
		h.incrementState();
	
	}
	
	public void handleInfectedHuman(){
		carteNbInfecte[h.getPosX()][h.getPosY()]--;
		h.move(this.mtRandom.genrand_int32()%300,this.mtRandom.genrand_int32()%300);
		h.incrementState();
		if(h.getStatus == 'I'){
			carteNbInfecte[h.getPosX()][h.getPosY()]++;
		}
		

	}
	
	public void handleRecoveredHuman(){
		h.move(this.mtRandom.genrand_int32()%300,this.mtRandom.genrand_int32()%300);
		h.incrementState();
	}
	
	
	
	public void contamine(Human h){
	
		int nbInfecteVoisinage = 0;
		
		for(int i = h.getPosX() - 1; i < h.getPosX() + 1; i++){
			for(int j = h.getPosY() - 1; j < h.getPosY() + 1; j++){
				nbInfecteVoisinage += carteNbInfecte[toricEspace(i)][toricEspace(j)];
			}
		
		} 
		
		double proba = 1 - mtRandom.negExp(-0.5 * nbInfecteVoisinage);
		double random = mtRandom.genrand_real1();
		if(random < proba){
			h.infection();
			this.nbS--;
			this.nbI++;
		}
	}
	
	public int toricEspace(int index){
		if(index < 0){
			return 299;
		}
		if (index > 299){
			return 0;
		}
		else{
			return index;
		}
	}
	
	public void incrementState(Human h){
		if(h.getStatus() == 'I'){
			if(h.getCpt() > dI){
				h.setStatus('E');
				this.nbS--;
				this.nbE++;
				h.resetCompteurEtat();
			}
			h.incrementCpt()++;
		}
		if(status == 'E'){
			if(cpt_etat > dE){
				this.status = 'R';
				resetCompteurEtat();
			}
			this.cpt_etat++;
		}
		
		if(status == 'R'){
			if(cpt_etat > dR){
				this.status = 'S';
				resetCompteurEtat();
			}
			this.cpt_etat++;
		}
		
	}
	
	
	
}
