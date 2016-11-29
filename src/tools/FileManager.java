package tools;

import java.io.BufferedReader;
import java.io.FileReader;

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
}
