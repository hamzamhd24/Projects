package com.RUStore;

import java.io.*;
import java.util.*;
import java.net.*;

public class TestClient 
{
	
	 
	public TestClient(String host, int port) 
	{
		run(host,port);
	}
	
    public void run(String host, int port) 
    {
    	String hostname = host;
 
        try (Socket socket = new Socket(hostname, port)) {
 
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            
            PrintWriter writer = new PrintWriter(output, true);
            
 
            Scanner inp = new Scanner(System.in);
            HashMap txt = new HashMap();
            int i = 1;
       
            do {
            	System.out.println("Sending request please enter text below.");
            	System.out.print("Enter Text: ");
                txt.put(i, inp.nextLine());               
                writer.println(txt);
                i++;
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
 
                String time = reader.readLine();
 
                System.out.println(time);
 
            } while (!txt.containsValue("bye"));
            output.writeObject(txt);
            socket.close();
 
        } catch (Exception ex) {
 
            System.out.println("Server not found: " + ex.getMessage());
 
        } 
    }
}