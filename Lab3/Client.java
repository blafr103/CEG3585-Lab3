import java.util.*;
import java.net.*;
import java.io.*;


public class Client{
   

    public static void main(String args[]){
        Scanner s =new Scanner(System.in);
        System.out.println("enter code");

        //create client server relation
        try{
        String address="localhost";
            int port=4444;
            Socket cSocket=new Socket(address,port);
            DataOutputStream data=new DataOutputStream(cSocket.getOutputStream());
            DataInputStream datain=new DataInputStream(cSocket.getInputStream());
            String t="";
            boolean empty=true;
            while(empty){
                String tmp=s.nextLine();
                if(tmp != null){
                    t=t+tmp;
                }
                else{
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
}

