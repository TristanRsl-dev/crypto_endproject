package cryptography;

public class Hill {
		private String message;
		private int[][] key={{2,7},{3,8}}; // Matrice 2x2 par défaut.
		
		public Hill(String message) {
			this.message = message;
		}
		
		public String crypt(){
			String res="";
			if (message.length() %2 !=0) message+="X"; //Bourrage avec un X si le message n'est pas pair
			int i=0;
			int det =(key[0][0]*key[1][1] - key[0][1]*key[1][0])%26;
			if (det<0) det=-det;
			while(i<message.length()-1)
			{
				int char1;
				int char2;
				char1 = ((message.charAt(i) % 65) * key[0][0] + (message.charAt(i+1) % 65) * key[1][0])%26;
				char2 = ((message.charAt(i) % 65) * key[0][1] + (message.charAt(i+1) % 65) * key[1][1])%26;
				if (char1<0)
					char1+=26;
				if (char2<0)
					char2+=26;
				res = res+ (char) (char1+65) + (char) (char2+65);
				i+=2;
			}			
			return res;
		}
		
		public String decrypt(){
			String res="";
			int key_inv[][] = new int[2][2];
			int det =(key[0][0]*key[1][1] - key[0][1]*key[1][0])%26;
			if(det<0)
			{
				det=-det;
				key_inv[0][0]=-key[1][1];
				key_inv[0][1]=key[0][1];
				key_inv[1][0]=key[1][0];
				key_inv[1][1]=-key[0][0];
			}
			
			int prime[]={1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23,29,31};
			int inv_mod =0,i=0;
			while(i<prime.length-1 )
			{
				int tmp = prime[i]*det;
				if (tmp<0)
					tmp+=26;
				if (tmp%26==1)
					break;
				i++;
			}
			if (i==prime.length-1)
				System.out.println("pas trouvé d'inverse modulaire. :/");
			else inv_mod = prime[i];
			i=0;
			if(inv_mod!=0)
			{
				key_inv[0][0]=(key_inv[0][0]*inv_mod)%26;
				key_inv[0][1]=(key_inv[0][1]*inv_mod)%26;
				key_inv[1][0]=(key_inv[1][0]*inv_mod)%26;
				key_inv[1][1]=(key_inv[1][1]*inv_mod)%26;
				if (key_inv[0][1]<0)
					key_inv[0][1]+=26;
				if (key_inv[1][0]<0)
					key_inv[1][0]+=26;
				if (key_inv[1][1]<0)
					key_inv[1][1]+=26;
				if (key_inv[0][0]<0)
					key_inv[0][0]+=26;
				
				while( i<message.length()-1)
				{
					int char1;
					int char2;
					char1 = ((message.charAt(i) % 65) * key_inv[0][0] + (message.charAt(i+1) % 65) * key_inv[1][0])%26;
					char2 = ((message.charAt(i) % 65) * key_inv[0][1] + (message.charAt(i+1) % 65) * key_inv[1][1])%26;
					if (char1<0)
						char1+=26;
					if (char2<0)
						char2+=26;
					res = res+ (char) (char1+65) + (char) (char2+65);
					i+=2;
				}
				if (res.lastIndexOf('X')==res.length()-1) res=res.substring(0,res.length()-1); //Retirer le bourrage
			}
			else System.out.println("impossible d'inverser la matrice.");
			
			return res;
		}
}
