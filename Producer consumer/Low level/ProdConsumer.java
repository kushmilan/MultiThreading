import java.util.LinkedList;

public class ProdConsumer{
    private LinkedList<Integer> list = new LinkedList<>();
    private final int Limit = 10;
    private Object lock = new Object();
    
    public void producer()throws InterruptedException{
        while(true){
            synchronized(lock){
                int temp = 0;
            
                while(list.size() == Limit){
                    lock.wait();
                }
                
                list.add(temp++);
                lock.notify();
            }
        }                
    }
    
    public void consumer()throws InterruptedException{
        while(true){
            synchronized(lock){
                while(list.size() == 0){
                    lock.wait();
                }
                int element = list.removeFirst();
                System.out.println("element removed : " + element + " List size : " + list.size());
                lock.notify();
                Thread.sleep(1000);
            }
        }                
    }
}