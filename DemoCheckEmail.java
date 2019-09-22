import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
public class DemoCheckEmail{
   public static void main(String[] args) {
     //Set mail properties and configure accordingly
      String hostval = "pop.gmail.com";
      String mailStrProt = "pop3";
      String uname = "whatodo12345@gmail.com";
      String pwd = "Wakandaforever";
    // Calling checkMail method to check received emails
      checkMail(hostval, mailStrProt, uname, pwd);
   }
   public static void checkMail(String hostval, String mailStrProt, String uname,String pwd) 
   {
      try {
      //Set property values
      Properties propvals = new Properties();
      propvals.put("mail.pop3.host", hostval);
      propvals.put("mail.pop3.port", "995");
      propvals.put("mail.pop3.starttls.enable", "true");
      Session emailSessionObj = Session.getDefaultInstance(propvals);  
      //Create POP3 store object and connect with the server
      Store storeObj = emailSessionObj.getStore("pop3s");
      storeObj.connect(hostval, uname, pwd);
      //Create folder object and open it in read-only mode
      Folder emailFolderObj = storeObj.getFolder("INBOX");
      emailFolderObj.open(Folder.READ_ONLY);
      //Fetch messages from the folder and print in a loop
      Message[] messageobjs = emailFolderObj.getMessages(); 
 
      for (int i = 0, n = messageobjs.length; i < n; i++) {
         Message indvidualmsg = messageobjs[i];
         System.out.println("Printing individual messages");
         System.out.println("No# " + (i + 1));
         System.out.println("Email Subject: " + indvidualmsg.getSubject());
         System.out.println("Sender: " + indvidualmsg.getFrom()[0]);
         System.out.println("Content: " + indvidualmsg.getContent().toString());

      }
      //Now close all the objects
      emailFolderObj.close(false);
      storeObj.close();
      } catch (NoSuchProviderException exp) {
         exp.printStackTrace();
      } catch (MessagingException exp) {
         exp.printStackTrace();
      } catch (Exception exp) {
         exp.printStackTrace();
      }
   }
}
