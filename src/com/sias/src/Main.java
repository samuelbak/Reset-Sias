package com.sias.src;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import com.sias.net.ListeningSocket;
import com.sias.util.ComPort;

public class Main {

	public static void main(String[] args) {
		if(args.length==0){
			System.out.println(
					"Acciaieria Arvedi Sias reset tool Samuele Bacchetta\n"
					+ "Usage:\n"
					+ "-client         Running as client\n"
					+ "-server[port]   Running as Server, [port] specify listenig port"
					);
		}
		else{
			if (args[0].contains("-server")){
				String[] port = args[0].split("-server");
				PrintWriter logFile;
				try {
					logFile = new PrintWriter("sias_reset.log", "UTF-8");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					return;
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					return;
				}
				
				ListeningSocket server = new ListeningSocket(Integer.valueOf(port[1]).intValue(), "GET", logFile);
				server.start();
			}
			if (args[0].contains("-client")){
				System.out.println("Not implemented yet");
				System.out.println(ComPort.getComs());
			}
		}
	}

}
