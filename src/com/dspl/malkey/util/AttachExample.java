package com.dspl.malkey.util;

import java.util.Properties; 
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;
public class AttachExample { 
   public static void main (String args[]) throws Exception 
   { 
      System.getProperties().put("proxySet","true");
      System.getProperties().put("socksProxyPort","1080");
      System.getProperties().put("socksProxyHost","192.168.155.1");
      Properties props = System.getProperties(); 
     
      String from = "sankadil@gmail.com"; 
      String to = "sankadil@gmail.com";
      String filename = "AttachExample.java";
   
   
   // Get system properties 
      final String username = "sankadil@gmail.com";
      final String password = "niduk123";
   
      props.put("mail.user", username);
      props.put("mail.host", "mail.spymac.com");
      props.put("mail.debug", "true");
      props.put("mail.store.protocol", "pop3");
      props.put("mail.transport.protocol", "smtp");
   
   
      Session session = Session.getDefaultInstance(props, 
                           new Authenticator(){
                              protected PasswordAuthentication getPasswordAuthentication() {
                                 return new PasswordAuthentication(username, password);
                              }});
   
   // Define message 
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(from));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); 
      message.setSubject("Hello JavaMail Attachment"); 
   // Create the message part 
      BodyPart messageBodyPart = new MimeBodyPart(); 
   // Fill the message 
      messageBodyPart.setText("Here's the file"); 
   // Create a Multipart 
      Multipart multipart = new MimeMultipart();
   // Add part one
      multipart.addBodyPart(messageBodyPart); 
   // // Part two is attachment // // Create second body part 
      messageBodyPart = new MimeBodyPart(); 
   // Get the attachment 
      DataSource source = new FileDataSource(filename); 
   // Set the data handler to the attachment 
      messageBodyPart.setDataHandler(new DataHandler(source));
   // Set the filename
      messageBodyPart.setFileName(filename); 
   // Add part two 
      multipart.addBodyPart(messageBodyPart); 
   // Put parts in message
      message.setContent(multipart);
   // Send the message 
      Transport.send(message);
   } 
}