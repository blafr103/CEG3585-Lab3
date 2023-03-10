import java.util.*;
import java.net.*;
import java.io.*;


public class Client{
   

   public static void main(String args[]){
      Scanner s =new Scanner(System.in);
      System.out.println("enter code");
      Socket cSocket=new Socket();
      //create client server relation
      try{
         String address="localhost";
         int port=4444;
         cSocket=new Socket(address,port);
         DataOutputStream data=new DataOutputStream(cSocket.getOutputStream());
         DataInputStream datain=new DataInputStream(cSocket.getInputStream());
         String t="";
         boolean empty=true;
         while(empty){
            String tmp=s.nextLine();
            if(tmp != null){
               t=t+tmp;
            }else{
               empty=false;
            }
         }
      //Le programme d'encodage doit envoyer un message « demande d’envoi » au programme de
      //décodage et attendre la réponse « prêt à recevoir » du programme de décodage, avant de l'envoyer
      data.writeUTF(t);
      data.flush();
           
      //wait for response
      System.out.println(datain.readUTF());
      }
         catch(Exception e){
         System.out.println(e);
      }

      //close streams and sockets
      cSocket.close();
      s.close();
   }      
   
   
   public String encodageB8ZS(String chaine){
      
      StringBuilder encoded = new StringBuilder();
      boolean zero8 = false; 
      int zeros = 0;
      StringBuilder OOOB = new StringBuilder("000+-0-+000+-0-+");
      
      for (int i = 0 ; i<chaine.length() ; i++){
         
         if(zeros==8){zero8=true;}
         
         if (chaine.charAt(i) == 0){
            zeros++;
         }else{
            
            if (zero8){
               
               for (int j = 0; j<zeros; j++){
               
                  encoded.append(OOOB.charAt(j));
               
               }
               
            }else{
               for (int x = 0; x< zeros; x++){
                  encoded.append("0");
               }              
            }
            
            encoded.append("1");
            zeros = 0;
         }         
      }
      return encoded.toString();
   }
}

