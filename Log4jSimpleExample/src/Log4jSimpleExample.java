import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

class MyLogger{
	static Logger logger =  LogManager.getLogger("Application Name");
	//static Logger logger1 =  LogManager.getLogger(Log4jSimpleExample.class);
	//static Logger logger =  Logger.getLogger(Log4jSimpleExample.class);
	//static Logger logger2 =  Logger.getLogger("Application Name");
}

public class Log4jSimpleExample {
	
	void hello(){
		MyLogger.logger.info("in hello()");
	}
	
	int sum(int a, int b){
		MyLogger.logger.info(" a = " + a);
		MyLogger.logger.info(" b = " + b);
		int ret = a + b;
		MyLogger.logger.info(" ret = " + ret);
		MyLogger.logger.warn("try warn, ret = " + ret);
		return ret;
	}
	
	public static void main(String[] args){
		Log4jSimpleExample example = new Log4jSimpleExample();
		PropertyConfigurator.configure("configuration/Log4j.properties");
		Apple apple = new Apple();
		apple.hello();
		example.hello();
		example.sum(5,6);
		
		Thread thread = new Thread(new MyWorker());
		thread.start();
	}
}

class Apple{
	void hello(){
		MyLogger.logger.info("I am a big apple");
	}
}

class MyWorker implements Runnable{
	public void run(){
		MyLogger.logger.info("another thread");
	}
}