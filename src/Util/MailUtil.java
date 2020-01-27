package Util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import Model.StudentBean;

public class MailUtil
{
    private static String FROM = "sheridancarpool@gmail.com"; 
	private static String FROM_PASSWORD = "Carpool123";
	private static String SUBJECT = "A user has requested a ride!"; //need to add information
	private static String HOST = "smtp.gmail.com";
	
	public static void sendMail(StudentBean student) throws MessagingException
	{
		
		Authenticator authenticator = new Authenticator() {
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication(FROM, FROM_PASSWORD);
		    }
		};
		
		String toUser = student.getEmail();
		
		//1 Set properties and get mail session
		Properties props = new Properties();
//		props.put("mail.transport.protocol","smtps");
//		props.put("mail.smtps.host", HOST);
//		props.put("mail.smtps.port", "465");
//        props.put("mail.smtps.auth", "true");
//        props.put("mail.smtps.quitwait", "false");

        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
		Session ses = Session.getDefaultInstance(props, authenticator);
		ses.setDebug(true);
		
		//2 Creating the message
		Message msg = new MimeMessage(ses);
		msg.setSubject(SUBJECT);
		msg.setText("The user who requested your rider is: " + student.getFirstName() + " " + student.getLastName()
					+ ". \nTheir contact information: "
					+ "\nEmail: " + student.getEmail() 
					+ "\nPhone: " + student.getPhone());
		
		//3 Addressing the message
		Address fromAddress = new InternetAddress(FROM);
		Address toAddress = new InternetAddress(toUser);
		msg.setFrom(fromAddress);
		msg.setRecipient(Message.RecipientType.TO,toAddress);
		
		//4 sending the message
		try
		{
			Transport transport =  ses.getTransport();
			transport.connect(HOST, FROM, FROM_PASSWORD);
			transport.send(msg);
			transport.close();
		}
		catch(MessagingException e)
		{
			e.printStackTrace();
		}
	}
	
}