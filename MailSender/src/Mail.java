import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class Mail {
	public static void main(String[] args){
		Logger logger = LogManager.getLogger("MailSender");
		PropertyConfigurator.configure("configuration/log4j.properties");
		
		MailSender mailSender = new MailSender("smtp.sohu.com", "good_bull@sohu.com", "******", logger);
		String[] filenames = {"files/1.sql", "files/2.sql", "files/3.sql"};
	
		MailSender.MailWithFiles mail = mailSender.createMail("good_bull@sohu.com", "good_bull@sohu.com",
			"test", "Just for test little tool", filenames);
		logger.info("before sendMail()");
		mailSender.sendMail(mail);
		logger.info("send success");
	}

}
