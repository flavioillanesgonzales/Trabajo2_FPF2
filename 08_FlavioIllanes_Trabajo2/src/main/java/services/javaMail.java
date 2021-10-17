
package services;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class javaMail {

    public static void enviarConGMail(String destinatario, String asunto, String cuerpo) {
        String remitente = "trabajo2faig@gmail.com";
        String clave = "pruebita123";

        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", clave);
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try { 
            message.setFrom(new InternetAddress(remitente));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            message.setSubject(asunto);           

            BodyPart texto = new MimeBodyPart();
            texto.setText(cuerpo);

            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            message.setContent(multiParte);

            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, clave);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException me) {
            me.printStackTrace();   //Si se produce un error
            System.out.println("Errorcito en: " + me.getMessage());
        }
    }
    
    public static void main(String[] args) {
//        Date fecha = new Date();
//        DateTime dateTime = new DateTime(fecha);
//        int mes = dateTime.getMonthOfYear();
//        System.out.println("Mes: " + mes);
        String destinatario = "faigalonso@gmail.com";
        String asunto = "Hola mundo";
        String cuerpo = "Hee hola xd";
        enviarConGMail(destinatario, asunto, cuerpo);
    }
}
