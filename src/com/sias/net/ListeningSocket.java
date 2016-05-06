package com.sias.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.sias.util.TimeFormat;

public class ListeningSocket implements Runnable{
	
	private Thread t;
	private int port;
	private ServerSocket serverSocket;
	private int connCount;
	private String passKey;
	private PrintWriter log;
	
	public ListeningSocket(int port, String passKey, PrintWriter logFile) {
		this.port = port;
		this.passKey = passKey;
		connCount = 0;
	}
	
	public void run(){
		try {
			serverSocket = new ServerSocket(this.port);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		while(true){
			Socket connection;
			BufferedReader in;
			try {
				connection  = serverSocket.accept();
				in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputString;
				while((inputString = in.readLine())!=null){
					if(inputString.contains(passKey)){
						log.write(TimeFormat.getDateTimeForLog()+connection.getInetAddress()+" "+connection.getPort()+" "+Integer.valueOf(connCount++)+"\n");
						System.out.println();
						
					}
				}
				//connection.close();
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
			
		}
	}
	
	public void start(){
		if (t==null){
			t = new Thread(this);
			t.start();
		}
	}
	
}
