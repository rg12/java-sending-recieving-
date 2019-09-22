//package mailsend;
import java.util.Scanner;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage.RecipientType;

import java.util.*;
public class Main {
private static String HOST = "smtp.gmail.com";
private static String USER = "whatodo12345@gmail.com";
private static String PASSWORD = "Wakandaforever";
private static String PORT = "465";
private static String FROM = "whatodo12345@gmail.com";
private static String TO = "whatodo12345@gmail.com";

private static String STARTTLS = "true";
private static String AUTH = "true";
private static String DEBUG = "true";
private static String SOCKET_FACTORY = "javax.net.ssl.SSLSocketFactory";
private static String SUBJECT = "Testing JavaMail API";
private static String TEXT = "This is a test message from my java application. Just ignore it";

public static synchronized void send() {
Properties props = new Properties();

props.put("mail.smtp.host", HOST);
props.put("mail.smtp.port", PORT);
props.put("mail.smtp.user", USER);
props.put("mail.smtp.auth", AUTH);
props.put("mail.smtp.starttls.enable", STARTTLS);
props.put("mail.smtp.debug", DEBUG);
props.put("mail.smtp.socketFactory.port", PORT);
props.put("mail.smtp.socketFactory.class", SOCKET_FACTORY);
props.put("mail.smtp.socketFactory.fallback", "false");
try {
Session session = Session.getDefaultInstance(props, null);
session.setDebug(true);
MimeMessage message = new MimeMessage(session);
message.setText(TEXT);
message.setSubject(SUBJECT);
message.setFrom(new InternetAddress(FROM));
message.addRecipient(RecipientType.TO, new InternetAddress(TO));
message.saveChanges();
Transport transport = session.getTransport("smtp");
transport.connect(HOST, USER, PASSWORD);
transport.sendMessage(message, message.getAllRecipients());
transport.close();
} catch (Exception e) {
e.printStackTrace();
}
}
public static void main(String[] args) {
Main.send();
System.out.println("Mail sent successfully!");
}
}
