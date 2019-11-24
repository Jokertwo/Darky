package my.zabiju.diblu;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class EmailSender {

    private static final String FROM = "p.lastovka@seznam.cz";
    private Session session;


    public EmailSender() {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "false");
        prop.put("mail.smtp.host", "smtp.seznam.cz");
        prop.put("mail.smtp.port", "25");

        session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("p.lastovka", "Joker-two");
            }
        });
    }


    public void sendEmail(ClenRodiny clen) {
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(FROM));

            message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(clen.getEmail()));
            message.setSubject("Ježíšek 2018");

            String msg = "<html><h2>Ahoj,</h2> tady je letošní ježíšek. Protože mám spoustu práce tak budeš místo mě dávat letos dárek <b>"
                    + clen.getObdarovany().name() + "</b> ty!</html>";

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html; charset=UTF-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart, "UTF-8");

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}
