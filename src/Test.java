package src;

class Test
{
    public static void main(String[] args)
    {
        // l'argument 'true' permet d'agir comme dans le code original en C
        Twister instanceRandom = new Twister(true);

        for(int i = 0; i < 100; i++){
            Monde m = new Monde(instanceRandom);
            m.runSimulation(i);
        }  
    }
}