package cryptography;

public class Pbox {
	
	private String message;
	private int key[];
	
	public Pbox(String message, int key[]){
		this.message = message;
		this.key = key;
	}
	
	public String crypt(){
		String res="";
		int i=0;
		
		while(message.length() %5!=0) message+="0"; //Si le message est impair, on rajoute un 0 de bourrage ? / casser ?
		for(i=0;i<=message.length()-1;i+=5){
			String m = (String) message.subSequence(i,i+5);
			int j=0;
			for(j=0;j<=m.length()-1;j++)
				res+=m.charAt(key[j]);
		}
		return res;
	}
	
	public String decrypt(){
		String res="";
		int i=0;

		while(message.length() %5!=0) message+="0"; //Si le message est impair, on rajoute un 0 de bourrage ? / casser ?
		for(i=0;i<=message.length()-1;i+=5){
			String m = (String) message.subSequence(i,i+5);
			int j=0;
			for(j=0;j<=m.length()-1;j++) 
				res+=m.charAt(key[j]);
		}		
		return res;
	}
}
