package src;
class Test
{
    public static void main(String[] args)
    {
        for(int i = 0; i < 100; i++){
            Twister instanceRandom = new Twister(42);
            Monde m = new Monde(instanceRandom);
            m.runSimulation(i);
        }  
    }
}
