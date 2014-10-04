package forum.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Created by Макс on 02.09.14.
 */
public class MailUtil {
    public static boolean sendMail(String recipientEmail, String subject, String message){
        // SSL // I USED THIS METHOD
        Properties propsSSL = new Properties();

        // EVEN IF YOU SKIP THESE TWO PROP IT WOULD WORK
        propsSSL.put("mail.transport.protocol", "smtps");
        propsSSL.put("mail.smtps.host", "smtp.gmail.com");

        // THIS IS THE MOST IMPORTANT PROP --> "mail.smtps.auth"
        propsSSL.put("mail.smtps.auth", "true");

        Session sessionSSL = Session.getInstance(propsSSL);
        sessionSSL.setDebug(true);

        Message messageSSL = new MimeMessage(sessionSSL);


        Transport transportSSL = null;
        try {

            messageSSL.setFrom(new InternetAddress("kmetsdevelop@gmail.com"));
            messageSSL.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail)); // real recipient
            messageSSL.setSubject(subject);
            messageSSL.setContent(message,"text/html; charset = utf-8");
            messageSSL.setText(message);
            transportSSL = sessionSSL.getTransport();
            transportSSL.connect("smtp.gmail.com", 465, "kmetsdevelop@gmail.com", "wqeqwe"); // account used

            transportSSL.sendMessage(messageSSL, messageSSL.getAllRecipients());
            transportSSL.close();
        } catch (NoSuchProviderException e) {
            return false;
        } catch (MessagingException e) {
            return false;
        }
        // EVEN IF YOU SKIP PORT NUMBER , IT WOULD WORK

        return true;
    }

}
