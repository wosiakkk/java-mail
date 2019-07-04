package java_mail.java_mail;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
	
	private String userName = "wosidevs@gmail.com";
	private String password = "senhadoemail";
	
	@org.junit.Test
	public void testeEmail() {
		
		try {
			/*Verificar as configurações do SMTP do seu e-mail*/
			//será utilizado o GMAIL(lembrar de autorizar o acesso ao gmail por aplicativos não seguros)
			//propriedades de cofiguração do servidor do gmail
			Properties properties = new Properties();
			//inserindo as pripriedades da api javamail (presentes na documentação)
			properties.put("mail.smtp.auth", "true"); //Autorização
			properties.put("mail.smtp.starttls", "true");//Autenticação
			properties.put("mail.host", "smtp.gmail.com");//servidor do gmail Google
			properties.put("mail.smtp.port", "465");//Porta do servidor
			properties.put("mail.smtp.socketFactory.port", "465");//Expecifica a porta a ser conectada pelo socket
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");//Classe socket de conexão ao SMTP
			
			Session session = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					
					return new PasswordAuthentication(userName, password);
				}
			});
			
			//Configurando os destinatarios
			Address[] toUsers = InternetAddress.parse("brunofwosiak@gmail.com, onurb_felipe@hotmail.com, wosidevs@gmail.com");
			//Configurando a mensagem
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(userName));//Quem está enviando
			message.setRecipients(Message.RecipientType.TO, toUsers);//email de destino
			message.setSubject("Chegou o e-mail através da api javaMail");//O assunto do e-mail
			message.setText("Olá, você acaba de receber um e-mail enviado pelo JAVA!");//O corpo do e-mail
			
			//Realizando o envio
			Transport.send(message);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
}
