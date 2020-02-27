/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailapp;

import com.sun.mail.util.MailSSLSocketFactory;
import static emailapp.consultmail.ReceiveMail;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hp
 */
public class gestion extends javax.swing.JFrame {

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
         
         for (int i=messages.length - 10; i<messages.length;i++){
             Message message= messages[i];
             Mail M= new Mail(i+1, message.getSentDate(),message.getFrom()[0], message.getSubject() );
             list.add(M);
             System.out.println("message number : "+ M.N);
             System.out.println("Sujet : " +M.subject);
            
             System.out.println("sender : " + M.sender);
             System.out.println("sent date : " +M.date);
            System.out.println( "message" + message.getContent());
             
             //System.out.println("message number : " +i+1);
             //System.out.println("Sujet : " +message.getSubject());
             //System.out.println("message number : " +i);
             //System.out.println("sender : " + message.getFrom()[0]);
             //System.out.println("sent date : " +message.getSentDate());
            //System.out.println( "message" + message.getContent());
            
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
    
    /**
     * Creates new form gestion
     */
    public gestion() {
        initComponents();
        this.setTitle("Gestion");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Consulter mes mails");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Envoyer un nouveau mail");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(86, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Sendmail s=new Sendmail();
        s.setVisible(true);
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
consultmail c=new consultmail();
        c.setVisible(true);
         DefaultTableModel model = (DefaultTableModel) c.jTable1.getModel();
        ArrayList<Mail> list = null;
        try {
            list = ReceiveMail(Authentification.email, Authentification.pw);
        } catch (MessagingException ex) {
            Logger.getLogger(consultmail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(consultmail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(consultmail.class.getName()).log(Level.SEVERE, null, ex);
        }
        Object rowData[] = new Object[4];
        for(int i = 0; i < 10; i++)
        {
            rowData[0] = list.get(i).N;
            rowData[1] = list.get(i).date;
            rowData[2] = list.get(i).sender;
            rowData[3] = list.get(i).subject;
            model.addRow(rowData);
        // TODO add your handling code here:
        
     
        }  
        
    }//GEN-LAST:event_jButton1ActionPerformed
	
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(gestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(gestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(gestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(gestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new gestion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}