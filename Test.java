

class Test
{
   public static void main(String[] args){
   	    Twister instanceRandom = new Twister(42);
        Monde m = new Monde(instanceRandom);
        for(i = 0; i< 100; i++){
            m.runSimulation(i);
        }  
   }
}
