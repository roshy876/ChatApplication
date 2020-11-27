package com.aoop.ClientServer;

import java.awt.BorderLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp extends JFrame {
	static String username;
	private JPanel contentPane;
	

	static Socket s;
	static ServerSocket ss;
	static DataInputStream dis;
	static DataOutputStream dos;
	
	
	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerApp frame = new ServerApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		try {
		String msgin = "";
			
		
			ss = new ServerSocket(4444);
			s = ss.accept();
			dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());
			while (true)
			{
				msgin = dis.readUTF();
				textDisplay.setText(textDisplay.getText()+"\n Server:\t"+msgin); 
				s.close();
			}
			} catch (Exception e)
			{
		}
	}

	/**
	 * Create the frame.
	 */
	public ServerApp() {
		setTitle("Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 557, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JTextArea textDisplay = new JTextArea();
		textDisplay.setEditable(false);
		textDisplay.setBounds(10, 23, 398, 350);
		contentPane.add(textDisplay);
		
		final JTextArea textSendMessage = new JTextArea();
		textSendMessage.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				try {
				String message = textSendMessage.getText();
				 if(evt.getKeyCode() == KeyEvent.VK_ENTER)
				 {
					 if (message.equals(""))
					 {
						 return;
					 }
					 
					 dos.writeUTF(message);
					 textDisplay.append("Server: " + message + "\n");
					 //textSendMessage.setText("");
					
				 }
				 
					} catch (Exception e) {
						
					}
				 
			}
		});
		textSendMessage.setBounds(10, 395, 398, 37);
		contentPane.add(textSendMessage);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event)
			{
				try {
				String message = textSendMessage.getText().trim();
				if (message.equals(""))
				{
					return;
				}
				textDisplay.append("Server: "  +message+ "\n");
				dos.writeUTF("Client: " +message);
				//textSendMessage.setText("");
				
				} catch (Exception e) {
					
				}
			}
		});
		btnSend.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSend.setBounds(418, 395, 100, 37);
		contentPane.add(btnSend);
		
		textField = new JTextField();
		textField.setBounds(108, 240, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
	}
	
	
	private static javax.swing.JTextArea textDisplay;
	private static javax.swing.JTextArea textSendMessage;
	private javax.swing.JButton btnSend;
	private JTextField textField;

}
