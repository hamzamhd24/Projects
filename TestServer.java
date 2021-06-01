package com.RUStore;

import java.io.*;
import java.net.*;
import java.util.*;

//Creating a test class for sending message and storing keys
public class TestServer 
{
	int i;
	
	Socket socket;
	
	BufferedReader br;
	
	ServerSocket ss;
	
	InputStream inp;
	
	OutputStream out;
	
	HashMap hm;
	
	PrintWriter pw;
		
		public TestServer()
		{
			i = 1;
		}
		public TestServer(String str) throws IOException 
		{
			store(str);
		}
	 
	    public void store(String str) throws IOException 
	    {
	        if (str == null) 
	        {
	        	return;
	        }
	 
	        int port = Integer.parseInt(str);
	 
	        ss = new ServerSocket(port);
	        
	        socket = ss.accept();
	        System.out.println("New Server please send request");
	        inp = socket.getInputStream();
	        br = new BufferedReader(new InputStreamReader(inp));
	        out = socket.getOutputStream();
	        PrintWriter writer = new PrintWriter(out, true);
	        hm = new HashMap();
	        while (!hm.containsValue("quit"))
	        {
	        	hm.clear();
	            hm.put(i, br.readLine());       
	            writer.println("Server: " + hm.toString());
	            i++;
	         } 
	                socket.close();
	            }
	 
	        
	    }
	


