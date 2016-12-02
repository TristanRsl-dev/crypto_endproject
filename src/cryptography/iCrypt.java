package cryptography;

public interface iCrypt {
	public void crypt();
	public void decrypt();
	
	public void setMsg(String message);
	public String getMsg();
}
