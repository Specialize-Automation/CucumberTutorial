package utility;

import java.io.IOException;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.WebDriver;
//import java.util.Base64;

public class PwdEncryption 
{
	WebDriver driver1;
	public PwdEncryption(WebDriver driver)
	{
		this.driver1=driver;
	}
	public void passwordEncoder(String key)throws IOException
	{
		byte[] encodedPwdBytes = Base64.encodeBase64(key.getBytes());
		System.out.println("encodedPwdBytes :"+new String(encodedPwdBytes)); //converting the BYTES value to String
	}
	public String passwordDecoder(String key)throws IOException
	{	
		byte[] decodePwdBytes = Base64.decodeBase64(key);
		String decodedPWD = new String(decodePwdBytes);
//		System.out.println("decodePwdBytes :"+decodedPWD); ////converting the BYTES value to String
		return(decodedPWD);
	}
}
	
	
/*********************************************************************************************
 * If we use Base 64 of the Java.util.Base64 then we can use this below to encode and decode
 * byte[] encryptedPasswordBytes = Base64.getEncoder().encode(encryptedPassword);
 * byte[] decryptedPasswordBytes = Base64.getDecoder().decode(encryptedPassword);

	public static void main(String [] args) throws IOException
	{
		PwdEncryption pwd = new PwdEncryption(driver1);
		pwd.passwordEncoder("Russia@411");
		pwd.passwordDecoder("UnVzc2lhQDQxMQ==");
	}
 ********************************************************************************************/
