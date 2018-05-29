package laobing.concurrency.chapter10_UnReentrant;


public class UnReentrant{
    Lock lock = new Lock();
    public void outer(){
        try {
			lock.lock();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        inner();
        lock.unlock();
    }
    public void inner(){
        try {
			lock.lock();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //do something
        lock.unlock();
    }
}

class Lock{  
    private boolean isLocked = false;  
    public synchronized void lock()  
        throws InterruptedException{  
        while(isLocked){  
            wait();  
        }  
        isLocked = true;  
    }  

    public synchronized void unlock(){  
        isLocked = false;  
        notify();  
    } 
}