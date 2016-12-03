package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileManager {
	private String filename;

	public FileManager(String filename) {
		this.filename = filename;
	}

	public BufferedReader open() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			return br;
		} catch (Exception e) {
			System.err.println("Impossible to open your file: " + e.toString());
			return null;
		}
	}

	public String read(BufferedReader br) {
		try {
			String res = "";
			String line;
			while ((line = br.readLine()) != null)
				res += line + "\n";
			return res;
		} catch (Exception e) {
			System.err.println("Impossible to read your file: " + e.toString());
			return "";
		}
	}
	
	public void write(String s) {
		File f = new File(filename);
		try {
			FileWriter fw = new FileWriter(f);
			fw.write(s);
			fw.close();
		} catch (Exception e) {
			System.err.println("Impossible to write or open your file: " + e.toString());
		}
	}
}
