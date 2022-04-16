package it.xpug.kata.birthday_greetings.infrastructure;

import it.xpug.kata.birthday_greetings.hexagone.NotificationProvider;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by podisto on 15/04/2022.
 */
public class EmailServiceAdapter implements NotificationProvider {

    private static final Logger logger = Logger.getLogger("EmailServiceAdapter");

    private final MailProperties mailProperties;

    public EmailServiceAdapter(MailProperties mailProperties) {
        this.mailProperties = mailProperties;
    }

    @Override
    public void send(String recipient, String subject, String body) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", mailProperties.getHost());
            props.put("mail.smtp.port", "" + mailProperties.getPort());
            Session session = Session.getInstance(props, null);

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(mailProperties.getSender()));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            msg.setSubject(subject);
            msg.setText(body);

            Transport.send(msg);
        } catch (MessagingException e) {
            logger.log(Level.WARNING, "Impossible denvoyer la notification {}", e.getMessage());
        }
    }
}
