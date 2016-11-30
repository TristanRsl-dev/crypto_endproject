package tools;

public class WordProcessor {
	private String message;
	
	public WordProcessor(String message) {
		this.message = message.toUpperCase();
	}
	
	public String getMsg() {
		return message;
	}
	
	public void setMsg(String message) {
		this.message = message;
	}

	public void dltChar() {
		String res = "";
		for (int i = 0; i < message.length(); ++i) {
			char c = message.charAt(i);
			if (c >= 65 && c <= 90)
				res += c;
			else if (c == 199 || c == 231) res += 'C'; // Les ç et Ç
			else if (c == 233 || c == 232) res += 'E'; //les é et è
			else if (c == 224) res += 'A'; //les à
		}
		this.setMsg(res);
		System.out.println(res);
	}
}
