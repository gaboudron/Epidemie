package src;

class Test
{
    public static void main(String[] args)
    {
        int[] init = {0x123, 0x234, 0x345, 0x456};
        
        Twister instanceRandom = new Twister();
        instanceRandom.init_by_array(init);
        
        for(int i = 0; i < 100; i++){
            Monde m = new Monde(instanceRandom);
            m.runSimulation(i);
        }  
    }
}