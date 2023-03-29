/**
 * The server implements a protocol
 * 1. It waits for the name of the User
 * 2. Then it expects the date birth
 * 3. It registers the User and returns a (unique) identifier
 */
package Project;
import java.net.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	public static List<User> database = new ArrayList<>() ;
	public static final int NUMPROCS = 5 ;
	public static final int PORT = 12345;
	private ServerSocket server;
	private ExecutorService executor ;
	
	public Server() throws IOException {
		this.server = new ServerSocket(Server.PORT);
		this.executor = Executors.newFixedThreadPool(Server.NUMPROCS);
	}
	
	
	public static void main(String arg[]) throws ClassNotFoundException, IOException {
//		ServerSocket server ;
		Server S = new Server() ;
		
		try {	
//			S.server = new ServerSocket(12345) ;
			System.out.println("Waiting for connections..") ;
			while(true) {
				// client : a client accepted by the server
				Socket client = S.server.accept();
				System.out.println("Connection [OK]") ;

				CreationLogin T = new CreationLogin(client) ;
				
				ObjectInputStream input_client = new ObjectInputStream(client.getInputStream()) ;
				ObjectOutputStream output_client = new ObjectOutputStream(client.getOutputStream()) ;
				
				// 1. Receive the infos
				String name = (String) input_client.readObject();
				String password = (String) input_client.readObject();
				User U = new User(name, password) ;
				
				// Create or Login
				String login = T.CreateOrLogin(U, output_client);
				
				// Local Directory
				if (login == "true") {
					Local L = new Local(name, password);
					S.executor.execute(L);
				}
			}
		}
		catch (IOException E) {
			E.printStackTrace();
		}
	}
}