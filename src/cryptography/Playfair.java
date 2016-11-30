package cryptography;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Playfair {
	private String message;
	private String key;

	private static final ArrayList<String> alph = new ArrayList<>(Arrays.asList("A",
			"B", "C", "D", "E", "F", "H",
			"I", "J", "K", "L", "M", "N", "O", "P", "Q",
			"R", "S", "T", "U", "V", "W", "X", "Y", "Z"));

	public Playfair(String message, String key) {
		this.message = message;
		this.key = key;
	}
	
	public String crypt() {
		ArrayList<ArrayList<String>> matrix = defineMatrix();
		String res = "";
		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 5; ++j) {
				res += ((ArrayList<String>)matrix.get(i)).get(j);
			}
			res += "###";
		}
		return res;
	}

	private ArrayList<ArrayList<String>> defineMatrix() {
		ArrayList<ArrayList<String>> matrix = new ArrayList<ArrayList<String>>(5);
		ArrayList<String> bigline = new ArrayList<String>(25);
		for (int i = 0; i < key.length(); ++i) {
			String c = String.valueOf(key.charAt(i));
			if (isInAlpha(c)) {
				bigline.add(c);
				alph.remove(c);
			}
		}
		for (int i = 0; i < alph.size(); ++i)
			bigline.add(alph.get(i));
		
		ArrayList<String> littleline = new ArrayList<String>(5); 
		for (int i = 1; i < 26; ++i) {
			littleline.add(bigline.get(i - 1));
			if (i % 5 == 0) {
				matrix.add(littleline);
				littleline = new ArrayList<String>(5);
			}
		}
		
		return matrix;
	}

	private boolean isInAlpha(String c) {
		for (int i = 0; i < alph.size(); ++i)
			if (c.equals(alph.get(i)))
				return true;
		return false;
	}
}
