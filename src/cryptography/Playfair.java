package cryptography;

import tools.Tuple;

import java.util.ArrayList;
import java.util.Arrays;

public class Playfair {
	private String message;
	private String key;

	private static final ArrayList<String> alph = new ArrayList<>(Arrays.asList("A",
			"B", "C", "D", "E", "F", "G", "H",
			"I", "K", "L", "M", "N", "O", "P", "Q",
			"R", "S", "T", "U", "V", "W", "X", "Y", "Z"));

	public Playfair(String message, String key) {
		this.message = message;
		this.key = key;
	}
	
	public String crypt() {
		ArrayList<ArrayList<String>> matrix = defineMatrix();
		String res = "";
		for (int i = 0; i < message.length(); i += 2) {
			if (i + 1 == message.length())
				message += ((ArrayList<String>)matrix.get(0)).get(0);
			char a = message.charAt(i);
			char b = message.charAt(i + 1);
			if (a == b) {
				b = 'X';
				i--;
			}
			res += cryptCouple(String.valueOf(a) + b, matrix);
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
	
	private String cryptCouple(String couple, ArrayList<ArrayList<String>> matrix) {
		String a = String.valueOf(couple.charAt(0));
		String b = String.valueOf(couple.charAt(1));
		Tuple posa = new Tuple(0, 0);
		Tuple posb = new Tuple(0, 0);
		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 5; ++j) {
				if (((ArrayList<String>)matrix.get(i)).get(j).equals(a)) {
					posa.setX(j);
					posa.setY(i);
				}
				if (((ArrayList<String>)matrix.get(i)).get(j).equals(b)) {
					posb.setX(j);
					posb.setY(i);
				}
			}
		}
		
		//Check if it's on the same line
		if (posa.getX() == posb.getX())
			return ((ArrayList<String>)matrix.get((posa.getY() + 1)%5)).get(posa.getX()) +
					((ArrayList<String>)matrix.get((posb.getY() + 1)%5)).get(posb.getX());
		//Check if it's on the same column
		else if (posa.getY() == posb.getY())
			return ((ArrayList<String>)matrix.get(posa.getY())).get((posa.getX() + 1)%5) +
					((ArrayList<String>)matrix.get(posb.getY())).get((posb.getX() + 1)%5);
		else
			return ((ArrayList<String>)matrix.get(posa.getY())).get(posb.getX()) +
					((ArrayList<String>)matrix.get(posb.getY())).get(posa.getX());
	}
}
