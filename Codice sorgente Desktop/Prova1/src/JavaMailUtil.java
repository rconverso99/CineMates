import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailUtil {
	public static void sendMail(String recepient , String titolo_film) throws MessagingException{
		System.out.println("Preparing to send email");
		Properties properties = new Properties();
		
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		String myAccountEmail = "progettoingegneria2021@gmail.com";
		String password = "riccardoalessio2021";
		
		Session session = Session.getInstance(properties, new Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(myAccountEmail,password);
				
				
			}
				
		});
		
		
		Message message = prepareMessage(session , myAccountEmail ,recepient, titolo_film);
		Transport.send(message);
		System.out.println("Message sent succesfully");
	
	}
	private static Message prepareMessage(Session session , String myAccountEmail , String recepient , String titolo_film){

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("CineMates ti consiglia (;");
			String htmlCode ="<h1> CineMates20 </h1> <br/> <h2><b>Ciao, ti consigliamo di vedere "+titolo_film+" </b></h2>";
			message.setContent(htmlCode,"text/html");
			//message.setText("Se vedi questa mail, \n allora ha funzionato");
			return message;
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void sendMailPromozionale(String recepient ) throws MessagingException{
		System.out.println("Preparing to send email");
		Properties properties = new Properties();
		
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		String myAccountEmail = "progettoingegneria2021@gmail.com";
		String password = "riccardoalessio2021";
		
		Session session = Session.getInstance(properties, new Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(myAccountEmail,password);
				
				
			}
				
		});
		
		
		Message message = scriviMessaggio(session , myAccountEmail ,recepient);
		Transport.send(message);
		System.out.println("Message sent succesfully");
	
	}
	
	private static Message scriviMessaggio(Session session , String myAccountEmail , String recepient ){

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("Email promozionale - CineMates20");
			String htmlCode ="<h1> CineMates20 </h1> <br/> <h2><b>Ciao, ti ringraziamo per il supporto che ci dai ogni giorno </b>se ti piace CineMates20 valuta la nostra app o consigliala ad un amico (;</h2>";
			message.setContent(htmlCode,"text/html");
			//message.setText("Se vedi questa mail, \n allora ha funzionato");
			return message;
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
