//Parth Shah
//CS 417 Project 1 
//Date: 03/15/2021

package com.RUStore;

/* any necessary Java packages here */
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class RUStoreClient 
{

	/* any necessary class members here */
	
	private String host;
	
	private int port;
	
	private Socket socket = null;
	
	private BufferedReader inp;
	
	private DataOutputStream out;
	
	private DataInputStream in;
	
	private FileOutputStream fos;
	
	private FileInputStream fis;
	
	private ObjectOutputStream os;
	
	private ObjectInputStream is;
	
	private BufferedInputStream bs;
	
	
	private String putData;

	private Object put;
	

	/**
	 * RUStoreClient Constructor, initializes default values
	 * for class members
	 *
	 * @param host	host url
	 * @param port	port number
	 */
	public RUStoreClient(String host, int port) 
	{

		// Implement here
		this.host = host;
		
		this.port = port;
		

	}

	/**
	 * Opens a socket and establish a connection to the object store server
	 * running on a given host and port.
	 *
	 * @return		n/a, however throw an exception if any issues occur
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public void connect() throws IOException 
	{
		// Implement here
		
		this.socket = new Socket(host, port);
		
		this.out = new DataOutputStream(socket.getOutputStream());
				
		this.inp = new BufferedReader(new InputStreamReader(socket.getInputStream()));	
		
		
	}
	
	
	/**
	 * Sends an arbitrary data object to the object store server. If an 
	 * object with the same key already exists, the object should NOT be 
	 * overwritten
	 * 
	 * @param key	key to be used as the unique identifier for the object
	 * @param data	byte array representing arbitrary data object
	 * 
	 * @return		0 upon success
	 *        		1 if key already exists
	 *        		Throw an exception otherwise
	 * @throws IOException 
	 */
	public int put(String key, byte[] data) throws IOException 
	{
		this.out = new DataOutputStream(new ByteArrayOutputStream());
		
		this.in = new DataInputStream(new ByteArrayInputStream(data));
		
		
		out.writeBytes(key);
		
		putData = new String(data);
		
		out.writeBytes(putData);
		
		Object tc = in.readByte();
		
		if(tc != null && tc != key)
		{
			return 0;	
		}
		else if(tc == key)
		{
			return 1;
		}
		
		return -1;
		

	}

	/**
	 * Sends an arbitrary data object to the object store server. If an 
	 * object with the same key already exists, the object should NOT 
	 * be overwritten.
	 * 
	 * @param key	key to be used as the unique identifier for the object
	 * @param file_path	path of file data to transfer
	 * 
	 * @return		0 upon success
	 *        		1 if key already exists
	 *        		Throw an exception otherwise
	 * @throws IOException 
	 */
	public int put(String key, String file_path) throws IOException 
	{
		 File f = new File(file_path);
		 long length = f.length();
	     byte[] bytes = new byte[1024]; // Tried to use bytes to compare if the b
	     
	     //os.writeBytes(key);
	     
	     fis = new FileInputStream(f);
	     
	     fos = new FileOutputStream(f);
	     
	     bs = new BufferedInputStream(fis);
	     
	     
	     Object returnFile = fis.readAllBytes();
	    	 
	    	 if(returnFile != null && returnFile != key)
	    	 {
	    		 return 0;
	    	 }
		
		return -1;

		// Implement here
		

	}

	/**
	 * Downloads arbitrary data object associated with a given key
	 * from the object store server.
	 * 
	 * @param key	key associated with the object
	 * 
	 * @return		object data as a byte array, null if key doesn't exist.
	 *        		Throw an exception if any other issues occur.
	 * @throws IOException 
	 */
	public byte[] get(String key) throws IOException 
	{
		//Packet pc = new Packet(key);
		
		if(key != null)
		{
			InputStream is = socket.getInputStream();
			
			ByteArrayOutputStream bao = new ByteArrayOutputStream();
			
			byte[] find = new byte[1024];
			
			int readData = 0;
			
			//do
			//{
				bao.writeBytes(find);
				out.writeBytes(key);
			//}while((readData = is.read(find)) != 0);
			
			
		}
			
			return null;

	}

	/**
	 * Downloads arbitrary data object associated with a given key
	 * from the object store server and places it in a file. 
	 * 
	 * @param key	key associated with the object
	 * @param	file_path	output file path
	 * 
	 * @return		0 upon success
	 *        		1 if key doesn't exist
	 *        		Throw an exception otherwise
	 * @throws IOException 
	 */
	public int get(String key, String file_path) throws IOException
	{
	//	Packet pc = new Packet();
		
		//Object k = pc.getKey();
		
		in = new DataInputStream(socket.getInputStream());
		
		int length = in.readInt();
		
		if(length > 0)
		{
			byte[] fileByte = new byte[length];
			in.readFully(fileByte, 0, fileByte.length);
			String name = new String(fileByte);
			
			int cont = in.readInt();
			
			if(cont > 0)
			{
				byte[] bytes = new byte[cont];
				
				in.readFully(bytes, 0, cont);
				
			}
			
		}
		
		
			
		return 1;
	}

	/**
	 * Removes data object associated with a given key 
	 * from the object store server. Note: No need to download the data object, 
	 * simply invoke the object store server to remove object on server side
	 * 
	 * @param key	key associated with the object
	 * 
	 * @return		0 upon success
	 *        		1 if key doesn't exist
	 *        		Throw an exception otherwise
	 */
	public int remove(String key) 
	{
		
		try
		{
			synchronized(this)
		
	    {
	        RUStoreClient rc;
	        int i;
	        
	        ArrayList<RUStoreClient> rem = new ArrayList<>();
	        for(i = 0; i < rem.size();i++)
	        {
	            rc = rem.get(i);
	            if((rc.put.equals(key)))
	            {
	               rem.remove(i);
	               return 0;
	            }
	        }
	        
	    }
		}
		catch(Exception e)
		{
			System.out.println("Error occoured while removing.");
		}
		// Implement here
		return 1;

	}

	/**
	 * Retrieves of list of object keys from the object store server
	 * 
	 * @return		List of keys as string array, null if there are no keys.
	 *        		Throw an exception if any other issues occur.
	 */
	public String[] list() 
	{
		

		// Implement here
		return null;

	}

	/**
	 * Signals to server to close connection before closes 
	 * the client socket.
	 * 
	 * @return		n/a, however throw an exception if any issues occur
	 */
	public void disconnect() {

		// Implement here
		
		try
		{
			if(inp != null)
			{
				inp.close();
			}
		}
		catch(Exception e)
		{
			
		}
		try
		{
			if(out != null)
			{
				out.close();
			}
		}
		catch(Exception e)
		{
			
		}
		try
		{
			if(socket != null)
			{
				socket.close();
			}
		}
		catch(Exception e)
		{
			
		}

	}

}
