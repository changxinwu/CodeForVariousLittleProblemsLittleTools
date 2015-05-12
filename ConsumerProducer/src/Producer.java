
public class Producer implements Runnable{
	String[] msgs = {"hello,world", "welcome to beijing", "beijing is a big city"};
	private MessageStore ms;
	
	public Producer(MessageStore ms){
		this.ms = ms;
	}
	public void run(){
		for(String msg:msgs){
			ms.put(msg);
		}
		ms.put("done");
		System.out.println("Producer exit");
	}
}
