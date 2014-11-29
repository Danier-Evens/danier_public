package com.danier.code.io.multiplethread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
/**
 * 多线程socket通信 客户端
 * @author Danier
 * @date 2014-11-4
 */
public class MyClient {
	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("localhost", 10000);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(socket.getOutputStream());
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			String msg = reader.readLine();
			out.println(msg);
			out.flush();
			if (msg.equals("bye")) {
				break;
			}
			System.out.println(in.readLine());
		}
		socket.close();
	}
}
