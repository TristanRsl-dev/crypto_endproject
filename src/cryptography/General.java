package cryptography;

import tools.FileManager;

public class General implements iCrypt {
	private String message;
	
	public General(String message) {
		this.message = message;
	}

	@Override
	public void crypt() {
		int delim = message.length() / 3;
		if (delim % 2 != 0)
			delim++;
		String s1 = message.substring(0, delim);
		String s2 = message.substring(delim, delim * 2);
		String s3 = message.substring(delim * 2);
		
		Playfair playfair = new Playfair(s1, "KEYPLAYFAIR");
		playfair.crypt();
		Hill hill = new Hill(s2);
		Vigenere vigenere = new Vigenere(s3, "KEYVIGENERE");
		vigenere.crypt();
		
		String new_message = playfair.getMsg() + hill.crypt() + vigenere.getMsg();
		
		Boxes boxes = new Boxes(new_message);
		
		FileManager fm = new FileManager("resources/encryption.txt");
		
		String encryption = boxes.crypt();
		
		fm.write(encryption);
		this.setMsg(encryption);
	}

	@Override
	public void decrypt() {
		Boxes boxes = new Boxes(message);
		
		String new_message = boxes.decrypt();
		
		int delim = new_message.length() / 3;
		if (delim % 2 != 0)
			delim++;
		String s1 = new_message.substring(0, delim);
		String s2 = new_message.substring(delim, delim * 2);
		String s3 = new_message.substring(delim * 2);
	
		Playfair playfair = new Playfair(s1, "KEYPLAYFAIR");
		playfair.decrypt();
		Hill hill = new Hill(s2);
		Vigenere vigenere = new Vigenere(s3, "KEYVIGENERE");
		vigenere.decrypt();
		
		String decryption = playfair.getMsg() + hill.decrypt() + vigenere.getMsg();
		
		FileManager fm = new FileManager("resources/decryption.txt");
		fm.write(decryption);
		
		this.setMsg(decryption);
	}

	@Override
	public void setMsg(String message) {
		this.message = message;
	}

	@Override
	public String getMsg() {
		return message;
	}
}