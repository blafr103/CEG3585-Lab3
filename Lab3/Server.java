import java.net.*;
import java.io.*;
public class Server {
    public static void main(String[] args){
        try{
            int port=4444;
            ServerSocket socket=new ServerSocket(port);
            Socket sSocket=socket.accept();
            DataInputStream data=new DataInputStream(sSocket.getInputStream());
            DataOutputStream dataout=new DataOutputStream(sSocket.getOutputStream());
            while(true){
                    String tmp= (String)data.readUTF();
                     dataout.writeUTF(tmp);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public String decodageB8ZS(String chaine){
        
        StringBuilder decoded = new StringBuilder();
        boolean zero8 = false; 
        int zeros = 0;
        StringBuilder OOOB = new StringBuilder("000+-0-+000+-0-+");
        StringBuilder polarity = new StringBuilder("+");
        
        decoded.append('1');
        
        for (int i = 1 ; i<chaine.length() ; i++){
        
      
                       
            
        }
    
    }
    
        
    
    
}
