package com.aoop.ClientServer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ChatApp extends JFrame {

	private JPanel contentPane;

	static Socket s = null;
	static DataInputStream dis;
	static DataOutputStream dos;
	static JTextArea textSendMessage = new JTextArea();
	static JTextArea textDisplay = new JTextArea();
	
	
	
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatApp frame = new ChatApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		String msgin = "";
		try {
		s = new Socket("127.0.0.1", 4444);
		dis = new DataInputStream(s.getInputStream());
		dos = new DataOutputStream(s.getOutputStream());
		while (!(msgin.equals("exit")))
		{
			msgin = dis.readUTF();
			textDisplay.setText(textDisplay.getText()+"\n Server:\t"+msgin); 
			//display.setText(textDisplay.getText() +msgin);
		}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public ChatApp() {
		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 557, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JTextArea textDisplay = new JTextArea();
		textDisplay.setEditable(false);
		textDisplay.setBounds(10, 22, 398, 350);
		contentPane.add(textDisplay);
		
		
		textSendMessage.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt)
			{
				try {
				String message = textSendMessage.getText().trim();
				 if(evt.getKeyCode() == KeyEvent.VK_ENTER)
				 {
					 if (message.equals(""))
					 {
						 return;
					 }
					 textDisplay.append("You: " + message + "\n");
					 message = textDisplay.getText();
					 dos.writeUTF("You: " +message + "\n");
					 //textSendMessage.setText("");
					 
					 	
				 }
				 } catch (Exception e)
				 {
					 e.printStackTrace();
				 }
			}
			
		});
		textSendMessage.setBounds(10, 382, 398, 37);
		contentPane.add(textSendMessage);
		
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				String message = textSendMessage.getText().trim();
				if (message.equals(""))
				{
					return;
				}
				textDisplay.append("you: "  +message+ "\n");
				dos.writeUTF("You: " +message);
				//textSendMessage.setText("");
				
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnSend.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSend.setBounds(418, 382, 100, 37);
		contentPane.add(btnSend);
	}
	
	//public static javax.swing.JTextArea textDisplay;
	//private static javax.swing.JTextArea textSendMessage;
	private javax.swing.JButton btnSend;


/*
	public static javax.swing.JTextArea getTextDisplay() {
		return textDisplay;
	}

	public static void setTextDisplay(javax.swing.JTextArea textDisplay) {
		ChatApp.textDisplay = textDisplay;
	}

	public static javax.swing.JTextArea getTextSend() {
		return textSendMessage;
	}

	public static void setTextSend(javax.swing.JTextArea textSend) {
		ChatApp.textSendMessage = textSend;
	}
	*/
	
}
