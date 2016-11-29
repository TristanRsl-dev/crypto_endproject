package main;

import java.io.BufferedReader;

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
			e.printStackTrace();
		}
		
		WordProcessor wp = new WordProcessor(msg);
		wp.dltChar();
		System.out.println(wp.getMsg());
	}
}