package thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Deadlock {

	public static void main(String []arg){
		Lock lock1 = new ReentrantLock();
		Lock lock2 = new ReentrantLock();
		
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				lock1.lock();
				System.out.println("Acquired Lock1");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("waiting for second lock");
				lock2.lock();
				System.out.println("Acquired Lock2");
				lock2.unlock();
				lock1.unlock();
			}
		});
		
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				lock2.lock();
				System.out.println("Acquired Lock2");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("waiting for First lock");
				lock1.lock();
				System.out.println("Acquired Lock1");
				lock1.unlock();
				lock2.unlock();
			}
			
		});
		
		t1.start();
		t2.start();
	}
}
