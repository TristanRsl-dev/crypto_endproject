package main;

import java.io.BufferedReader;

import cryptography.*;
import tools.FileManager;
import tools.WordProcessor;

public class Main {

	public static void main(String[] args) {
		String filename = "resources/message_1";
		FileManager fm = new FileManager(filename);
		BufferedReader br = fm.open();
		String msg = fm.read(br);
		try {
			br.close();
		} catch (Exception e) {
			System.err.println("Can't close the BufferedReader: " + e.toString());
		}
		
		WordProcessor wp = new WordProcessor(msg);
		wp.dltChar();

		Playfair playfair = new Playfair(wp.getMsg(), "COUCOU");
		System.out.println(playfair.crypt()+"\n");

		Hill hill = new Hill(wp.getMsg());
		System.out.println(hill.crypt()+"\n");
		Hill hill2 = new Hill(hill.crypt());
		System.out.println(hill2.decrypt()+"\n");
		
		
		
		Boxes boxes = new Boxes(wp.getMsg());
		String res = boxes.crypt();
		System.out.println("crypt ="+res+"\n");

		Boxes boxes2 = new Boxes(res);
		String m = boxes2.decrypt();
		System.out.println("decrypt = "+m);
	}
}