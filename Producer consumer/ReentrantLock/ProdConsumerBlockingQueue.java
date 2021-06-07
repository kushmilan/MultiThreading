import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ProdConsumerBlockingQueue{

    private int size;
    private Queue<Integer> queue;
    
    Random r = new Random();

    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    public void initQueue(int size){
        queue = new LinkedList<>();
        this.size = size;
    }

    public void producer()throws InterruptedException{
        while(true){
            lock.lock();        
            try{
                while(queue.size() == size){
                    notEmpty.await();
                }
                queue.add(r.nextInt(100));
                notFull.signalAll();
            }finally{
                lock.unlock();
            }
        }                
    }

    public void consumer()throws InterruptedException{
        while(true){
            lock.lock();        
            try{
                while(queue.size() == 0){
                    notFull.await();
                }                
                Thread.sleep(100);
                int i = queue.remove();
                System.out.println("Value: " + i + " size: " + queue.size());
                notEmpty.signalAll();
            }finally{
                lock.unlock();
            }
        }            
    }
}