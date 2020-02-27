/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailapp;

import java.util.Date;
import javax.mail.Address;

/**
 *
 * @author hp
 */
public class Mail {
 int N;
 Date date;
 Address sender;
 String subject;
    public Mail(int N, Date date, Address ender, String subject){
       this.N=N;
       this.date=date;
       this.sender=sender;
       this.subject=subject;
    }
}
