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
		
		General general = new General(wp.getMsg());
		general.crypt();
		
		general.setMsg(general.getMsg());
		general.decrypt();

		/*Playfair playfair = new Playfair(wp.getMsg(), "COUCOU");
		playfair.crypt();
		System.out.println("## crypt playfair: " + playfair.getMsg()+"\n");
		playfair.setMsg(playfair.getMsg());
		playfair.decrypt();
		System.out.println("## decrypt playfair: " + playfair.getMsg() + "\n");
		
		Vigenere vigenere = new Vigenere(wp.getMsg(), "COUCOU");
		vigenere.crypt();
		System.out.println("## crypt vigenere: " + vigenere.getMsg()+"\n");
		vigenere.setMsg(vigenere.getMsg());
		vigenere.decrypt();
		System.out.println("## decrypt vigenere: " + vigenere.getMsg() + "\n");

		Hill hill = new Hill(wp.getMsg());
		System.out.println(hill.crypt()+"\n");
		Hill hill2 = new Hill(hill.crypt());
		System.out.println(hill2.decrypt()+"\n");
		
		
		
		Boxes boxes = new Boxes(wp.getMsg());
		String res = boxes.crypt();
		System.out.println("crypt ="+res+"\n");

		Boxes boxes2 = new Boxes(res);
		String m = boxes2.decrypt();
		System.out.println("decrypt = "+m);*/
	}
}