package Utils;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.util.Random;

public class EmailSender {

    static private String FromEmail = "companysf6@gmail.com";
    static private String FromEmailPassword = "xldcpqzsfghvygll";
    static private String Subjects;
    static public int code;

    public static void sendCode(String toEmail , String subject) throws MessagingException {
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
        code = new Random().nextInt(9999999);
        message.setText("Your verification code is " + code);
        Transport.send(message);

    }
}
