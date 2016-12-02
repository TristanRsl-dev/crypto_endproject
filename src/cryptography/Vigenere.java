package cryptography;

public class Vigenere implements iCrypt {
	private String message;
	private String key;
	
	public Vigenere(String message, String key) {
		this.message = message;
		this.key = key;
	}
	
	@Override
	public void crypt() {
		String res = "";
		for (int i = 0; i < message.length(); ++i) {
			int c = message.charAt(i) - 65;
			int c_key = key.charAt(i % key.length()) - 65;
			res += String.valueOf((char) (modulo(c + c_key, 26) + 65));
		}
		this.setMsg(res);
	}

	@Override
	public void decrypt() {
		String res = "";
		for (int i = 0; i < message.length(); ++i) {
			int c = message.charAt(i) - 65;
			int c_key = key.charAt(i % key.length()) - 65;
			res += String.valueOf((char) (modulo(c - c_key, 26) + 65));
		}
		this.setMsg(res);
	}

	@Override
	public void setMsg(String message) {
		this.message = message;		
	}

	@Override
	public String getMsg() {
		return message;
	}
	
	private int modulo(int a, int mod) {
		if (a < 0)
			return mod + a;
		return a % mod;
	}

}
