package Smart_Traffic;
import java.net.*;
import java.util.*;
import java.io.*;


public class Forwarder_p1 {
    static DatagramSocket aSocket = null;
    public static void main(String[] args) throws Exception {
        try {
        InetAddress serverAddress = InetAddress.getByName("localhost");
        int server1Port = 9102;
        int server2Port = 9103;
        aSocket = new DatagramSocket(9100);
        byte[] buffer = new byte[1000];
        
        while(true) {
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(request);
            String msg = new String(request.getData(), 0, request.getLength());
            DatagramPacket server1Message = new DatagramPacket(msg.getBytes(), msg.length(), serverAddress, server1Port);
            DatagramPacket server2Message = new DatagramPacket(msg.getBytes(), msg.length(), serverAddress, server2Port);
            try {
		aSocket.send(server1Message);
		} catch (IOException e) { e.printStackTrace();}	
            try {
		aSocket.send(server2Message);
		} catch (IOException e) { e.printStackTrace();}	
        }
       }catch (SocketException e){System.out.println("Error Socket: " + e.getMessage());
       }catch (IOException e){System.out.println("Error IO: " + e.getMessage());
       }finally { 
    	   if(aSocket != null) aSocket.close();
       }
    }
}
