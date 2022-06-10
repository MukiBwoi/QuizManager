package Utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

public class EmailSender {

    static private String FromEmail;
    static private String FromEmailPassword;
    static private String Subjects;

    public static void sendCode(String toEmail , String subject) throws MessagingException {
        Random rand = new Random();
        FromEmail = "companysf6@gmail.com";
        FromEmailPassword = "muksith987";
        Subjects = subject;
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        Session session = Session.getDefaultInstance(properties,new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(FromEmail, FromEmailPassword);
            }
        });
        System.out.println(FromEmail + "   " + FromEmailPassword);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(FromEmail));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
        message.setSubject(Subjects);
        message.setText("Your verification code is " + rand.nextInt(9999999));
        Transport.send(message);

    }
}
