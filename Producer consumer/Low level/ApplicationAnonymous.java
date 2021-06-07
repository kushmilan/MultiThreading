public class ApplicationAnonymous {

    public static void main(String[] args) {
        ProdConsumer pd = new ProdConsumer();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    pd.producer();
                }catch(InterruptedException e){
                    
                }
                
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    pd.consumer();
                }catch(InterruptedException e){
                    
                }                
            }
        });
        
        thread1.start();
        thread2.start();
    }

}