import java.net.*;
import java.io.*;
import java.util.Enumeration;

public class UDPServer
{
		public static void main(String args[])
	    {
			Enumeration enumer;
			try {
				enumer = NetworkInterface.getNetworkInterfaces();
				while(enumer.hasMoreElements())
				{
				    NetworkInterface n = (NetworkInterface) enumer.nextElement();
				    Enumeration ee = n.getInetAddresses();
				    while (ee.hasMoreElements())
				    {
				        InetAddress i = (InetAddress) ee.nextElement();
				        System.out.println(i.getHostAddress());
				    }
				}
			} catch (SocketException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
	        DatagramSocket sock = null;
	         
	        try
	        {
	            //1. creating a server socket, parameter is local port number
	            sock = new DatagramSocket(7777);
	             
	            //buffer to receive incoming data
	            byte[] buffer = new byte[65536];
	            DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
	             
	            //2. Wait for an incoming data
	            System.out.println("Server socket created. Waiting for incoming data...");
	             
	            //communication loop
	            while(true)
	            {
	                sock.receive(incoming);
	                byte[] data = incoming.getData();
	                String s = new String(data, 0, incoming.getLength());
	                 
	                //echo the details of incoming data - client ip : client port - client message
	                System.out.println(incoming.getAddress().getHostAddress() + " : " + incoming.getPort() + " - " + s);
	                 
	                s = "OK : " + s;
	                //DatagramPacket dp = new DatagramPacket(s.getBytes() , s.getBytes().length , incoming.getAddress() , incoming.getPort());
	                //sock.send(dp);
	            }
	        }
	         
	        catch(IOException e)
	        {
	            System.err.println("IOException " + e);
	        }
	    }
	     
	    //simple function to echo data to terminal
	    public static void echo(String msg)
	    {
	        System.out.println(msg);
	    }
}
