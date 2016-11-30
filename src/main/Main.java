package main;

import java.io.BufferedReader;

import cryptography.Playfair;
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
		System.out.println(playfair.crypt());
	}
}