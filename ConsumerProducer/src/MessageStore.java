
public class MessageStore {
	String message;
	boolean empty = true;
	
	public synchronized String take(){
		while(empty){
			try{
				wait();
			}catch(InterruptedException e){
				
			}
		}
		empty = true;
		notifyAll();
		return message;
	}

	public synchronized void put(String message){
		while(!empty){
			try{
				wait();
			}catch(InterruptedException e){ }
		}
		empty = false;
		this.message = message;
		notifyAll();	
	}
	
	public static void main(String[] args) throws InterruptedException{
		MessageStore ms = new MessageStore();
		Thread t1 = new Thread(new Producer(ms));
		Thread t2 = new Thread(new Consumer(ms));
		t1.start();
		t2.start();
		t1.join();
		 
		t2.join();
		
		System.out.println("main program terminated");
	}
}
