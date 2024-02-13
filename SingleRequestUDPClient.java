/**
 * Title: COMP4635 Task 2. Basic Socket Communication. Clients and Servers
 * Usage: java SingleRequestUDPClient [host] [port] [request]
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.net.*;

public class SingleRequestUDPClient {
	private static final String USAGE = 
	"java SingleRequestUDPClient [host] [port] [request]";
	private static final int BUFFER_LIMIT = 1000;

	public static void main(String[] args) throws IOException {
        if (args.length != 3) {
			System.out.println(USAGE);
			System.exit(1);
		}

        try {
        	String host = args[0];
        	int port = Integer.parseInt(args[1]);
        	String request = args[2];
        
			System.out.println("\nSending the request: " 
					+ request + " to the server!" );
   
	        // get a datagram socket
	        DatagramSocket socket = new DatagramSocket();
	
	        // send request
	        byte[] requestBuf = new byte[BUFFER_LIMIT];
	        requestBuf = request.getBytes();
	        
	        InetAddress address = InetAddress.getByName(host);
	        DatagramPacket packet = new DatagramPacket(requestBuf, requestBuf.length, address, port);
	        socket.send(packet);
	    
	        // get response
			byte[] responseBuf = new byte[BUFFER_LIMIT];
	        packet = new DatagramPacket(responseBuf, responseBuf.length);
	        socket.receive(packet);
	
		    // display response
	        System.out.println("p l: " + packet.getLength());
	        String received = new String(packet.getData(), 0, packet.getLength());
	        System.out.println("Current server time: " + received);
	    
	        socket.close();
        } catch (NumberFormatException e) {
			System.err.println("Invalid port number: " + args[1] + ".");
			System.exit(1);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
}
