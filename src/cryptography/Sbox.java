package cryptography;

public class Sbox {
	private String message;
	private char[][] matrix;
	
	public Sbox(String message, char[][] matrix) {
		this.message = message;
		this.matrix = matrix;
	}
	
	public String crypt(){
		String res="";
		char l,r;
		int i=0;
		if(message.length() %2!=0) message+="X"; //Si le message est impair, on rajoute un X de bourrage
		for(i=0;i<message.length();i+=2){
			l = message.charAt(i);
			r = message.charAt(i+1);
			res+=feistel_crypt(l, r);
		}
		return res;
	}
	
	public String decrypt(){
		String res="";
		String l,r;
		int i=0;
		while(message.length() %10!=0) message+="0"; //Si le message est impair, on rajoute un 0 de bourrage ? / casser ?
		for(i=0;i<message.length();i+=10){
			l = (String) message.subSequence(i,i+5);
			r = (String) message.subSequence(i+5,i+10);
			res+=feistel_decrypt(l,r);
		}
		return res;
	}
	
	
	private String toBit(int i){
		String res=Integer.toBinaryString(i);
		while(res.length()<5)
			res ="0"+res;
		return res;
	}
	
	private String feistel_crypt(char l,char r){
		String res="";
		int i=0, l2 = r % 65, r2=(l % 65) ^ confusion(r%65);
		
		for(i=0;i<=1;i++){
			int tmp = l2,tmp2=r2;
			l2 = r2;
			r2 = tmp ^ confusion(r2);
		}
		res+=toBit(l2);
		res+=toBit(r2);		
		return res;
	}
	
	private String feistel_decrypt(String l,String r){
		String res="";
		int i=0, r2=Integer.parseInt(l,2), l2=Integer.parseInt(r,2) ^ confusion(r2);

		for(i=0;i<=1;i++){
			int tmp = l2, tmp2=r2;
			l2 = r2 ^ confusion(l2);
			r2 = tmp;
		}
		res+=toBit(l2);
		res+=toBit(r2);
		return res;
	}
	
	private int confusion(int r){
		int ligne =r & 0b10001 
		,colonne = (r & 0b01110)>>1  ; //ne garder que les 3 bits du milieu et décalage pour remettre le poid des bits
		
		switch(ligne){
		default:
			break; // si ligne vaut 0 ou 1, on ne touche pas.
			
		case 16:
			ligne = 2;
			break;
			
		case 17:
			ligne = 3;
			break;
		}
		return (int) matrix[ligne][colonne]%65;
	}

	
	
	
}
