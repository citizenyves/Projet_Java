package Project;

import java.util.UUID;

public class User {
	public String name ;
	public String password ;
	public UUID id ;
	
	public User(String name, String password) {
		this.name = name ;
		this.password = password ;
		this.id = UUID.randomUUID() ;
	}	
	public String getName() {
		return this.name ;
	}
	public String getPassword() {
		return this.password ;
	}
	public UUID getId() {
		return this.id ;
	}
	public String toString() {
		return "[" + this.name + "]: " + this.name + " ( " + this.password + " )" ;
	}
}
