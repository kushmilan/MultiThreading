class App{
    public static void main(String args[]) throws InterruptedException{
        //Normal prod consumer with Objects

        final ProdConsumer p = new ProdConsumer();

        Thread t1 = new Thread(new Runnable(){
            public void run(){
                try{
                    p.producer();
                }catch(InterruptedException e){}                
            }
        });

        Thread t2 = new Thread(new Runnable(){
            public void run(){
                try{
                    p.consumer();
                }catch(InterruptedException e){}                
            }
        });
        
        t1.start();
        t2.start();
        

        /*

        //Blocking Queue

        final ProdConsumerBlockingQueue pb = new ProdConsumerBlockingQueue();

        pb.initQueue(50);

        Thread t1 = new Thread(new Runnable(){
            public void run(){
                try{
                    pb.producer();
                }catch(InterruptedException e){}                
            }
        });

        Thread t2 = new Thread(new Runnable(){
            public void run(){
                try{
                    pb.consumer();
                }catch(InterruptedException e){}                
            }
        });
        
        t1.start();
        t2.start();
        */
    }
}