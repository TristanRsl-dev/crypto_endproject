package cryptography;

public class Boxes {
	private String message;
	private int key[]={4, 0, 1, 3, 2}; //Pbox
	private int inv_key[]={1, 2, 4, 3, 0}; //Pbox
	private char[][] matrix = {{'L','B','U','T','P','Y','P','K'},
			{'O','B','D','Z','S','C','N','J'},
			{'W','P','V','H','A','M','X','F'},
			{'G','E','I','Q','Q','W','R','A'}}; //Matrice 4*16 de Sbox
	
	
	public Boxes(String message){
		this.message=message;
	}
	
	public String crypt(){
		int i=0;
		String res = "",m="";
		for(i=0;i<=2;i++){
			Sbox sbox = new Sbox((i==0)?message:toChar(m), matrix);
			Pbox pbox = new Pbox(sbox.crypt(),key);
			m = pbox.crypt();
		}
		res+=m;
		
		return res;
	}
	
	public String decrypt(){
		String res="",m="";
		int i=0;
		for(i=0;i<=2;i++){
			Pbox pbox = new Pbox((i==0)?message:m,inv_key);
			Sbox sbox = new Sbox(pbox.decrypt(),matrix);
			m = sbox.decrypt();
		}
		res+=toChar(m);
		return res;
	}
	
	 public String toChar(String m){//Passage d'une chaine de 5 bits à un caractère
		String res="",l;
		int i=0;
		while(m.length() %5!=0) m+="0"; //Si le message est impair, on rajoute un 0 de bourrage ? / casser ?
		for(i=0;i<m.length();i+=5){
			l = (String) m.subSequence(i,i+5);
			res+=(char) (Integer.parseInt(l,2)+65);
		}
		return res;
	}
	
}
