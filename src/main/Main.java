package main;

import java.io.BufferedReader;

import cryptography.*;
import tools.FileManager;
import tools.WordProcessor;

public class Main {

	public static void main(String[] args) {
		int key_hill[][]={{2,7},{3,8}};
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
		
		Hill hill = new Hill(wp.getMsg(),key_hill);
		String crypt_hill = hill.crypt();
		Hill hill2 = new Hill(crypt_hill, key_hill);
	}
}