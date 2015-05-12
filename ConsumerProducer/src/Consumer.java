public class Consumer implements Runnable{
	
	private MessageStore ms;
	
	public Consumer(MessageStore ms){
		this.ms = ms;
	}
	public void run(){
		while(true){
			String msg = ms.take();
			System.out.println(msg);
			if(msg == "done"){
				System.out.println("comsumer exit");
				return;
			}
		}
	}
}
