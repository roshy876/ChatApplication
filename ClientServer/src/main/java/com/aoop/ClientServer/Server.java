package com.aoop.ClientServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	private ObjectOutputStream out;
	private ObjectInputStream in;
	public static void main(String args[]) throws IOException, ClassNotFoundException {
		new Server();

	}

	public Server() throws IOException, ClassNotFoundException {
		ServerSocket ss = new ServerSocket(4444);
		Socket socket = ss.accept();
		System.out.println("Server Started......");
		in = new ObjectInputStream(socket.getInputStream());
		out = new ObjectOutputStream(socket.getOutputStream());
		
		DataPacket data = (DataPacket)in.readObject();
		System.out.println(data);
		
		DataPacket rPacket = new DataPacket();
		rPacket.setMessage("Hello .. "+data.getMessage());
		out.writeObject(rPacket);
		
		socket.close();
		ss.close();
	}
}
