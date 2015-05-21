import java.util.*;
import java.io.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import org.apache.log4j.*;

class MailSender{
	private Session session = null;
	private Logger logger = null;
	private String host;
	private String username;
	private String password;
	
	MailSender(String host, String username, String password, Logger logger){
		this.host = host;
		this.username = username;
		this.password = password;
		this.logger = logger;
		
		// create some properties and get the default Session
    	Properties props = System.getProperties();
    	props.put("mail.smtp.host", host);
    	props.put("mail.smtp.auth",true);  
	
    	session = Session.getInstance(props, null);
    	
    	boolean debug = false;
    	session.setDebug(debug);
	}
	
	public MailWithFiles createMail(String to, String from, String subject, String msgText, String[] filenamesWithPath){
		MailWithFiles mail = new MailWithFiles(to, from, subject, msgText, filenamesWithPath);
		return mail;
	}	
	
	class MailWithFiles{
		private MimeMessage msg;
		private MailWithFiles(String to, String from, String subject, String msgText, String[] filenamesWithPath){
			try {
	    		// create a message
	    		msg = new MimeMessage(session);
	    		msg.setFrom(new InternetAddress(from));
	    		InternetAddress[] address = {new InternetAddress(to)};
	    		msg.setRecipients(Message.RecipientType.TO, address);
	    		msg.setSubject(subject);

	    		// create the Multipart and add its parts to it
	    		Multipart mp = new MimeMultipart();
	    		
	    		// create and fill the first message part
	    		MimeBodyPart mbp1 = new MimeBodyPart();
	    		mbp1.setText(msgText);
	    		mp.addBodyPart(mbp1);
	    		
	    		for(int i = 0; i < filenamesWithPath.length; i++){
	    			String filename = filenamesWithPath[i];
	    			// create message part
	    			MimeBodyPart mbp = new MimeBodyPart();
	    			// attach file to the message
	    			mbp.attachFile(filename);
	    			mp.addBodyPart(mbp);
	    		}
	    		
	    		// add the Multipart to the message
	    		msg.setContent(mp);

	    		// set the Date: header
	    		msg.setSentDate(new Date());

	    		/*
	    		 * If you want to control the Content-Transfer-Encoding
	    		 * of the attached file, do the following.  Normally you
	    		 * should never need to do this.
	    		 *
		    	msg.saveChanges();
		    	mbp2.setHeader("Content-Transfer-Encoding", "base64");
	    		 */
			}catch(MessagingException e){
				logger.warn("construct message failed");
				logger.warn("" + e);
			}catch(IOException e){
				logger.warn("construct message failed");
				logger.warn("" + e);
			}
		}
		
	}
	
	public void sendMail(MailWithFiles mail){
		MimeMessage msg = mail.msg;
		
		try{
			Transport transport = session.getTransport("smtp");  
			transport.connect(host, username, password);
		
			transport.sendMessage(msg, msg.getAllRecipients()); //use sendMessage() instead of send(), succeed.
			//transport.send(msg); //if using this statement, failed .
			
		}catch(MessagingException e){
			logger.warn("got exception when send message");
			logger.warn("" + e);
		}
		
	}
	
}


