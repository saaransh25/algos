

public class threadcomm {
	public static void main (String [] args) {
		Q q2=new Q();
		new Producer(q2);
		new Consumer(q2);
	}
}


class Q {
	int n;
	boolean valueset=false;



	synchronized public int get () {
		while (!valueset) {
			try {
				wait();
			}
			catch (InterruptedException e) {
				System.out.println("InterruptedException caught");
			}
		}

		System.out.println("Got: "+n);
		valueset=false;
		notify();
		return n;

	}

	synchronized public void put(int k) {
		while (valueset) {
			try {
				wait();
			}
			catch (InterruptedException e) {
				System.out.println("InterruptedException caught");
			}
		}
		n=k;
		System.out.println("Put: "+n);
		valueset=true;
		notify();
	}

}

class Producer implements Runnable {
	Q q;
	Thread t;
	public Producer(Q q1) {
		q=q1;
		t=new Thread(this, "Put Thread");
		t.start();

	}

	public void run() {
		int i=0;
		while (true) {
			q.put(i++);
		}		
	}
}


class Consumer implements Runnable {
	Q q;
	Thread t;
	public Consumer(Q q1) {
		q=q1;
		t=new Thread(this, "Get Thread");
		t.start();
	}

	public void run() {
		while (true) {
			q.get();
		}
	}
}



