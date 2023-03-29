// Création d'un LocalDirectory de chaque utilisateur

package Project;

import java.net.*;
import java.io.*;
import java.util.*;

class Local implements Runnable {
	private String name ;
	private String password ;
	
	
	public Local(String name, String password) {
		this.name = name ;
		this.password = password ;
	}
	
	public void run() {
		try {
			LocalDirectory localDirectory = new LocalDirectory() ;
			System.out.println(localDirectory) ;
		}
		catch (Exception E) {
			System.out.println(E);
			E.printStackTrace();
		}
	}
}