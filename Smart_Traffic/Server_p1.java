package Smart_Traffic;
import java.net.*;
import java.util.*;
import java.io.*;

public class Server_p1 {
    static DatagramSocket aSocket = null;
    public static void main(String[] args) throws Exception {
        try {
        InetAddress serverAddress = InetAddress.getByName("localhost");
        aSocket = new DatagramSocket(Integer.parseInt(args[0]));
        byte[] buffer = new byte[1000];
        String prevmsg = "";

        
        while(true) {
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(request);
            String msg = new String(request.getData(), 0, request.getLength());
            if(!msg.equals(prevmsg)) {
                System.out.println(msg);
            }
            prevmsg = msg;
        }
       }catch (SocketException e){System.out.println("Error Socket: " + e.getMessage());
       }catch (IOException e){System.out.println("Error IO: " + e.getMessage());
       }finally { 
    	   if(aSocket != null) aSocket.close();
       }
    }
}
