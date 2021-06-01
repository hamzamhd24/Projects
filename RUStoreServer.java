package com.RUStore;

/* any necessary Java packages here */

import java.net.*;
import java.util.HashMap;
import java.io.*;



public class RUStoreServer {

	/* any necessary class members here */
	static ServerSocket listener;
	static Socket client;
	static BufferedReader inp;
	static PrintWriter out;
	static int mythread;

	/* any necessary helper methods here */

	/**
	 * RUObjectServer Main(). Note: Accepts one argument -> port number
	 * @throws IOException 
	 */
	public static void main(String args[]) throws IOException{

		// Check if at least one argument that is potentially a port number
		if(args.length != 1) {
			System.out.println("Invalid number of arguments. You must provide a port number.");
			return;
		}

		// Try and parse port # from argument
		int port = Integer.parseInt(args[0]);


		// Implement here //
		listener = new ServerSocket(port);
		
		System.out.println("[SERVER] waiting for a connection with client..");
		
		client = listener.accept();
		
		System.out.println("[SERVER] Successfully connected to client...");
		
		ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream()); 
		
		//ObjectInputStream  dps = new ObjectInputStream(client.getInputStream());
		
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		
		try
		{
			File f = new File("./inputfiles/lofi.txt");
		
			FileInputStream fis = new FileInputStream("./inputfiles/lofi.txt");
		
			String fname = f.getName();
		
			byte[] bytes = fname.getBytes();
		
			byte[] cont = new byte[fname.length()];
		
			fis.read(cont);
			
			dos.writeInt(bytes.length);
			dos.write(bytes);
			
			dos.writeInt(cont.length);
			dos.write(cont);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		
		
		
		
		

	
	}
}

