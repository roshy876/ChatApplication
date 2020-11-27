package com.aoop.ClientServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	ObjectOutputStream out;
	ObjectInputStream in;
	private Socket socket;
	
	public static void main(String args[]) throws Exception {
		new Client();
		
	}
	
	public Client() throws Exception {
		
		
		socket = new Socket("127.0.0.1",4444);
		out = new ObjectOutputStream(socket.getOutputStream());		
		in = new ObjectInputStream(socket.getInputStream());
				
		String message;
		Scanner reader = new Scanner(System.in);		
		message = reader.next();
		
		DataPacket data = new DataPacket();
		data.setMessage(message);
		
		//messageFromServer = socketScanner.next();
		
		out.writeObject(data);
		DataPacket srPacket = (DataPacket) in.readObject();
		System.out.println(srPacket);
		
		socket.close();
		
	}
}
