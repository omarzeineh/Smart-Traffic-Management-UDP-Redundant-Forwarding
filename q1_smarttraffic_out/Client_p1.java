package smarttraffic.q1;
import java.net.*;
import java.util.*;
import java.io.*;

public class Client_p1  {
    public static void main(String[] args) throws Exception {
        DatagramSocket aSocket = null;
        try {
        Scanner input = new Scanner(System.in);
        Sensor sensor = new Sensor(1);
        InetAddress forwarderAddress = InetAddress.getByName("localhost");
        int forwarder1Port = 9100;
        int forwarder2Port = 9101;
        aSocket = new DatagramSocket();
        
        System.out.print("Please Enter the Sensor ID: ");
        sensor.setID(input.nextInt());
        System.out.println("");
        
        while(true) {
            sensor.incCount();
            Thread.sleep(5000);
            
            DatagramPacket request1 = new DatagramPacket(sensor.toString().getBytes(), sensor.toString().length(), forwarderAddress, forwarder1Port);
            try {
			aSocket.send(request1);
		} catch (IOException e) { e.printStackTrace();}	
                        DatagramPacket request2 = new DatagramPacket(sensor.toString().getBytes(), sensor.toString().length(), forwarderAddress, forwarder2Port);
            try {
			aSocket.send(request2);
		} catch (IOException e) { e.printStackTrace();}	
            System.out.println("Traffic Data Sent: " + sensor.toString());
            System.out.println("Forwarded traffic data to: local host on port 9100");
            System.out.println("Forwarded traffic data to: local host on port 9101");
            
        }
       }catch (SocketException e){System.out.println("Error Socket: " + e.getMessage());
       }catch (IOException e){System.out.println("Error IO: " + e.getMessage());
       }finally { 
    	   if(aSocket != null) aSocket.close();
       }
    }
}
