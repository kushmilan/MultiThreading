import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ProdConsumer{
    
    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object arr[] = new Object[100];
    int put, take, count;
    Object fill = new Object();

    public void producer()throws InterruptedException{
        while(true){
            lock.lock();        
            try{
                while(count == arr.length){
                    notFull.await();
                }
                arr[put++] = fill;
                if(put == arr.length) put = 0;
                count++;
                notEmpty.signalAll();
            }finally{
                lock.unlock();
            }
        }                
    }

    public void consumer()throws InterruptedException{
        while(true){
            lock.lock();        
            try{
                while(count == 0){
                    notEmpty.await();
                }
                Thread.sleep(1000);
                Object x = arr[take++];
                System.out.println(x.toString() + " " + count);
                if(take == arr.length) take = 0;
                count--;
                notFull.signalAll();
            }finally{
                lock.unlock();
            }    
        }        
    }
}