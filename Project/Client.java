/**
 * A client that creates a new person
 */
package Project;
import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;

public class Client {
	
	public static final String SERVER = "127.0.0.1" ;
	public static final int PORT = 12345;

	public static void main(String arg[]) {
//		Client C = new Client();
		try {	
			// ID & Password input
			String name = arg[0];
			String password = arg[1];
			
			// Connection
			// s_client : server connected with the client
			Socket s_client = new Socket(SERVER, PORT);
			System.out.println("Connection [OK]");
			
			// Creating OutputStreams to send data
			ObjectOutputStream output = new ObjectOutputStream(s_client.getOutputStream());
			// Creating InputStreams to receive data
			ObjectInputStream input = new ObjectInputStream(s_client.getInputStream()) ;
			
			// Sending a data
			output.writeObject(name) ;
			output.writeObject(password) ;
			
			// Receving the result
			boolean exist = (boolean) input.readObject();
			// (1) Create an account
			if (exist == false) {
				UUID uuid = (UUID) input.readObject() ;
				System.out.println("[CREATED] id: " + uuid);
			}
			// (2) Login
			else {
				System.out.println("[LOGIN SUCCEED]\nid: " + name + "\npw: " + password) ;
			}
			
			s_client.close();
			
		}
		catch (Exception E) {
			E.printStackTrace();
		}
	}
	
}