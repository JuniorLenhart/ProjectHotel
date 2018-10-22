package hotel.support;

import hotel.controller.LoggerController;
import java.io.File;
import java.util.List;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Email {

    public static void sendEmail(String sender, List<String> recipient, String subject, String message, List<String> attachment) {
        try {
            Address[] address = new InternetAddress[recipient.size()];
            int count = 0;
            for (String a : recipient) {
                address[count] = new InternetAddress(a);
                count++;
            }

            if (attachment != null) {
                sendEmailAttachment(sender, address, subject, message, attachment);
            } else {
                sendEmail(sender, address, subject, message);
            }
        } catch (Exception ex) {
            LoggerController.log(Email.class, ex);
        }
    }

    private static void sendEmail(String sender, Address[] recipient, String subject, String msg) throws Exception {
        Message message = new MimeMessage(ConnectionEmail.getInstance());
        message.setFrom(new InternetAddress(sender));
        message.setRecipients(Message.RecipientType.TO, recipient);
        message.setSubject(subject);
        message.setText(msg);

        Transport.send(message);
    }

    private static void sendEmailAttachment(String sender, Address[] recipient, String subject, String msg, List<String> attachment) throws Exception {
        Message message = new MimeMessage(ConnectionEmail.getInstance());
        message.setFrom(new InternetAddress(sender));
        message.setRecipients(Message.RecipientType.TO, recipient);
        message.setSubject(subject);

        BodyPart bodyPart = new MimeBodyPart();
        bodyPart.setText(msg);

        Multipart multiPart = new MimeMultipart();
        multiPart.addBodyPart(bodyPart);

        File file;
        MimeBodyPart anexo;
        for (String f : attachment) {
            file = new File(f);
            anexo = new MimeBodyPart();
            anexo.setDataHandler(new DataHandler(new FileDataSource(file)));
            anexo.setFileName(file.getName());
            multiPart.addBodyPart(anexo);
        }

        message.setContent(multiPart);

        Transport.send(message);
    }
}
