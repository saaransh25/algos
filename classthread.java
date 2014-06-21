class Newthread implements Runnable {
	Thread t;
	String msg;
	public Newthread(String message) {
		t=new Thread(this,message);
		msg=message;
		System.out.println("Child thread: "+t);
		t.start();
	}
	public Newthread(String message, int p) {
		t=new Thread(this,message);
		t.setPriority(p);
		msg=message;
		System.out.println("Child thread: "+t);
		t.start();

	}

	public void run() {
		try {
			for (int i=5;i>0;i--) {
				System.out.println("Child thread: " +msg+i);
				Thread.sleep(500);
			}
		}
		catch (InterruptedException e) {
			System.out.println("Child Interrupted");
		}
		System.out.println("Exiting Child Thread");
	}
}

public class classthread {
	public static void main (String[] args) {
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		Newthread nthread1=new Newthread("First Thread", Thread.NORM_PRIORITY+2);
		Newthread nthread2=new Newthread("Second Thread", Thread.NORM_PRIORITY-3);
		Newthread nthread3=new Newthread("Third Thread");
		try {
			nthread1.t.join();
			nthread2.t.join();
			nthread3.t.join();
			/*
			for (int i=5;i>0;i--) {
				System.out.println("Main thread: " +i);
				Thread.sleep(1000);
			}
			*/

		}
		catch (InterruptedException e) {
			System.out.println("Main thread Interrupted Interrupted");
		}
		System.out.println("Exiting Main Thread");
	}
}