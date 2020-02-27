/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailapp;
import com.sun.mail.util.MailSSLSocketFactory;
import java.io.*;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.*;


/**
 *
 * @author hp
 */
public class Mailer {

    
    public static ArrayList ReceiveMail(String username , String password) throws MessagingException, GeneralSecurityException, IOException {
      ArrayList <Mail> list = new ArrayList<Mail>();
        try{
          
         Properties properties = new Properties();
         properties.setProperty("mail.store.protocole", "imaps");
         properties.put("mail.imap.ssl.trust", "*");
MailSSLSocketFactory socketFactory= new MailSSLSocketFactory();
socketFactory.setTrustAllHosts(true);
properties.put("mail.imap.ssl.socketFactory", socketFactory);
         Session emailSession= Session.getDefaultInstance(properties);
         Store emailStore = emailSession.getStore("imaps");
         emailStore.connect("imap.gmail.com",username, password);
         Folder emailFolder=emailStore.getFolder("INBOX");
         emailFolder.open(Folder.READ_ONLY);
         Message messages[] = emailFolder.getMessages();
         
         for (int i=messages.length - 3; i<messages.length;i++){
             Message message= messages[i];
             Mail M= new Mail(i+1, message.getSentDate(),message.getFrom()[0], message.getSubject() );
             list.add(M);
             System.out.println("message number : " +i+1);
             System.out.println("Sujet : " +message.getSubject());
             System.out.println("message number : " +i);
             System.out.println("sender : " + message.getFrom()[0]);
             System.out.println("sent date : " +message.getSentDate());
            System.out.println( "message" + message.getContent());
            
         }
         
         emailFolder.close(false);
         emailStore.close();
         
      }
      
      catch(NoSuchProviderException nspe){
          nspe.printStackTrace();
      }
       catch(MessagingException nspe){
          nspe.printStackTrace();
      }
        return list;
      
    }
     public static void main(String ...args ) throws MessagingException, GeneralSecurityException, IOException{
        
     }
}
