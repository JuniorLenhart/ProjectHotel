package hotel.support;

import java.util.Properties;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class ConnectionEmail {

    private static Session session = null;

    public ConnectionEmail() {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("hotelintegrad@gmail.com", "hotelintegrador");
                }
            });

            session.setDebug(true);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static Session getInstance() {
        if (session == null) {
            new ConnectionEmail();
        }
        return session;
    }
}
