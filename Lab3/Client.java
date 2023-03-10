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
      int spot = 0;
      int ones = 0;
      StringBuilder OOOBpos = new StringBuilder("000+-0-+000+-0-+000+-");
      StringBuilder OOOBneg = new StringBuilder("000-+0+-000-+0+-000-+");
      StringBuilder polarity = new StringBuilder("+");
      
      encoded.append('+');
      
      
      for (int i = 0 ; i<chaine.length()-1 ; i++){
         
         if(zeros>=8){zero8=true;}
         
         if (chaine.charAt(i) == '0'){
            zeros++;
         }else if (i!=0){
            
            if (zero8){
               for (int j = 0; j<zeros; j++){
                  if (polarity.charAt(0)=='+'){
                     encoded.append(OOOBpos.charAt(j+spot));
                  }else{
                     encoded.append(OOOBneg.charAt(j+spot));
                  }
               }
               if (zeros!=0){spot=zeros-1;}
               if (OOOBpos.charAt(spot)=='+'){
                  polarity.replace(0, 1, "-");   
               }else{
                  polarity.replace(0, 1, "+");
               }
            }else{
               for (int x = 0; x< zeros; x++){
                  encoded.append("0");
               }              
            } 
            if (polarity.charAt(0)=='+'){
               encoded.append("-");
               polarity.replace(0, 1, "-");
            }else{
               encoded.append("+");
               polarity.replace(0, 1, "+");
            }
            zeros = 0;
            ones++;
            zero8 = false;
         }         
      }
      if (zeros!=0){
         for (int y = 0; y< zeros; y++){
            encoded.append("0");
         }   
      }
      return encoded.toString();
   }
}

