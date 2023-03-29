package Project;
import java.io.ObjectOutputStream;
import java.net.Socket;

class CreationLogin {
	private Socket client ;
	public CreationLogin(Socket client) {
		this.client = client ;
	}
	
	public String CreateOrLogin(User U, ObjectOutputStream output_client) {
		try {
//			ObjectInputStream input_client = new ObjectInputStream(client.getInputStream()) ;
//			ObjectOutputStream output_client = new ObjectOutputStream(client.getOutputStream()) ;
//			
//			// 1. Receive the infos
//			String name = (String) input_client.readObject();
//			String password = (String) input_client.readObject();
			
			// 2. Checking information input (if it alreadt exist in the database or not)
			boolean exist = false;
			for (User User : Server.database) {
				if (U.getName().equals(User.getName()) && 
						U.getPassword().equals(User.getPassword())) {
					exist = true;
				}
			}
			
			output_client.writeObject(exist);

			// 3. Creation or Login
			// (1) creation
			if (exist == false) { 
				CreationLogin.Create(U) ;
				output_client.writeObject(U.getId());
				this.client.close() ;
				return "false";
			}
			// (2) login
			else { 
				CreationLogin.login(U) ;
				this.client.close() ;
				return "true";
			}
		}
		catch (Exception E) {
			System.out.println(E) ;
		}
		return null;
	}
	
	public static void Create(User U) {
		try {
			// Add a User in the database(List)
			Server.database.add(U) ;
			System.out.println("[CREATED] " + U);
		}
		catch (Exception E) {
			System.out.println(E) ;
		}
	}
	
	// client 
	public static String login(User U) {
		try {			
			boolean IDchecker = false ;
			boolean PWchecker = false ;
			for (User User : Server.database) {
				// ID check
				if (U.getName().equals(User.getName())) {
					IDchecker = true ;
				}
				// PW check
				if (U.getPassword().equals(User.getPassword())) {
					PWchecker = true ;
				}
			}
			if (IDchecker == true && PWchecker == true) {
				System.out.println("Login succeed") ;
				return "true";
			}
			else if (IDchecker == false){
				System.out.println("Wrong ID input !!") ;
				return "false";
			}
			else if (IDchecker == true && PWchecker == false) {
				System.out.println("Wrong PW input !!") ;
				return "false";
			}
			IDchecker = false ;
			PWchecker = false ;
		}
		catch (Exception E) {
			System.out.println(E) ;
		}
		return null;
	}
}