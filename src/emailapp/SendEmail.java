/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailapp;

import java.security.GeneralSecurityException;
import static java.util.Date.from;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author hp
 */
public class SendEmail {
    public static boolean sendmail(String user, String password,String Subject, String Message, String To[]){
       String host = "smtp.gmail.com";
        Properties pro= System.getProperties();
        pro.put("mail.smtp.starttls.enable", "true");
        pro.put("mail.smtp.host", host);
        pro.put("mail.smtp.user", user);
        pro.put("mail.smtp.password", password);
        pro.put("mail.smtp.port", 587);
        
        pro.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(pro, null);
        MimeMessage mimemessage = new MimeMessage(session);
       
        try{
            mimemessage.setFrom(new InternetAddress(user));
            InternetAddress [] ToAdress = new InternetAddress[To.length];
            for(int i=0; i<To.length;i++){
                ToAdress[i]= new InternetAddress(To[i]);
                            }
            for(int i=0; i<ToAdress.length;i++){
                mimemessage.addRecipient(RecipientType.TO, ToAdress[i]);
            }
          //  mimemessage.setSubject("mail using java mail");
            mimemessage.setText(Message);
            mimemessage.setSubject(Subject);
            Transport transport = session.getTransport("smtp");
            transport.connect(host,user,password);
            transport.sendMessage(mimemessage,mimemessage.getAllRecipients());
            transport.close();
            return true;
        }
        catch (MessagingException ex){
        ex.printStackTrace();
    }
        return false;
        
    }
    public static void main(String ...args ) throws MessagingException, GeneralSecurityException{
     
    }
    }
    

