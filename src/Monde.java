package src;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Monde
{
	private int[][] carteNbInfecte;

	int nbS = 0; // Nombre de susceptible
	int nbE = 0; // Nombre d'exposé
	int nbI = 0; // Nombre d'infecté
	int nbR = 0; // Nombre de récupéré
	Twister mtRandom;
	List<Human> listeHumain;
	List<Human> listeInfected;
	
	public Monde(Twister random)
	{
		carteNbInfecte = new int[300][300];

		// Initialize the matrix with 0
		for (int i = 0; i < 300; i++) 
		{
			for (int j = 0; j < 300; j++) 
			{
				carteNbInfecte[i][j] = 0;
			}
		}

		listeHumain = new ArrayList<Human>();
		for (int i = 0; i < 19980; i++)
		{
			listeHumain.add(new Human(Status.S));
			this.nbS++;
		}
		for (int i = 0; i < 20; i++)
		{
			listeHumain.add(new Human(Status.I));
			this.nbI++;
		}
		this.mtRandom = random;
		
		for (Human h : listeHumain)
		{
			if (h.getStatus() == Status.I)
			{
				carteNbInfecte[toricEspace(h.getPosX())][toricEspace(h.getPosY())]++;
			}
		}
	}
	
	public void runSimulation(int numFichier)
	{
		//0 - Créer un fichier avec numFichier
		String folderpath = "resultats/";
		String filename = folderpath + "resultat" + numFichier + ".csv";

		try (FileWriter writer = new FileWriter(filename)) 
		{
			for (int i = 0; i < 730; i++)
			{
				//1 - Sauvegarder nbS, nbE, nbI, nbR dans fichier resultat[numFichier].csv

				// Writing values to CSV
				writer.write(this.nbS + "," + this.nbE + "," + this.nbI + "," + this.nbR + "\n");
				
				//2 - Parcourir les humains pour les traiter.

				// Mélanger la liste d'humain
				Collections.shuffle(listeHumain, mtRandom);

				for(Human h : listeHumain)
				{
					Status status = h.getStatus();

					switch (status) 
					{
						case S:
						handleSusceptibleHuman(h);
						break;
						case E:
						handleExposedHuman(h);
						break;
						case I:
						handleInfectedHuman(h);
						break;
						case R:
						handleRecoveredHuman(h);
						break;
						default:
						System.out.println("Unknown status");
						break;
					}
				}	
			}
			
			// Close the writer
			writer.close();

		} catch (IOException e) 
		{
			System.err.println("Error writing to file: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void handleSusceptibleHuman(Human h)
	{
		int randomX = (int) (mtRandom.genrand_int32() % 300);
		int randomY = (int) (mtRandom.genrand_int32() % 300);
		h.move(randomX, randomY);
		contamine(h);
	}
	
	public void handleExposedHuman(Human h)
	{
		int randomX = (int) (mtRandom.genrand_int32() % 300);
		int randomY = (int) (mtRandom.genrand_int32() % 300);
		h.move(randomX, randomY);
		incrementState(h);
	}
	
	public void handleInfectedHuman(Human h)
	{
		carteNbInfecte[toricEspace(h.getPosX())][toricEspace(h.getPosY())]--;
		int randomX = (int) (mtRandom.genrand_int32() % 300);
		int randomY = (int) (mtRandom.genrand_int32() % 300);
		h.move(randomX, randomY);
		incrementState(h);
		if (h.getStatus() == Status.I)
		{
			carteNbInfecte[toricEspace(h.getPosX())][toricEspace(h.getPosY())]++;
		}
	}
	
	public void handleRecoveredHuman(Human h)
	{
		int randomX = (int) (mtRandom.genrand_int32() % 300);
		int randomY = (int) (mtRandom.genrand_int32() % 300);
		h.move(randomX, randomY);
		incrementState(h);
	}
	
	public void contamine(Human h)
	{
		int nbInfecteVoisinage = 0;
		
		for (int i = h.getPosX() - 1; i < h.getPosX() + 1; i++)
		{
			for (int j = h.getPosY() - 1; j < h.getPosY() + 1; j++)
			{
				nbInfecteVoisinage += carteNbInfecte[toricEspace(i)][toricEspace(j)];
			}
		}
		
		double proba = 1 - Math.exp(-0.5 * nbInfecteVoisinage);
		double random = mtRandom.genrand_real1();
		if(random < proba)
		{
			h.infection();
			this.nbS--;
			this.nbE++;
		}
	}
	
	public int toricEspace(int index)
	{
		final int maxIndex = 300;
		if (index < 0)
		{
			return maxIndex + index;
		}
		if (index >= maxIndex)
		{
			return index - maxIndex;
		}
		else
		{
			return index;
		}
	}
	
	public void incrementState(Human h)
	{
		if (h.getStatus() == Status.E)
		{
			if (h.getCpt() > h.getdE())
			{
				h.setStatus(Status.I);
				this.nbE--;
				this.nbI++;
				h.resetCompteurEtat();
			}
			h.incrementCpt();
		}

		if (h.getStatus() == Status.I)
		{
			if (h.getCpt() > h.getdI())
			{
				h.setStatus(Status.R);
				this.nbI--;
				this.nbR++;
				h.resetCompteurEtat();
			}
			h.incrementCpt();
		}
		
		if (h.getStatus() == Status.R)
		{
			if (h.getCpt() > h.getdR())
			{
				h.setStatus(Status.S);
				this.nbR--;
				this.nbS++;
				h.resetCompteurEtat();
			}
			h.incrementCpt();
		}
	}
}