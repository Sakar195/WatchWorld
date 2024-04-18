package utils;

import it.cosenonjaviste.crypto.BCrypt;

public class PasswordHash {
	public static String getPasswordHash(String password)
	{
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	 public static boolean checkPassword(String password, String hashPassword)
	 {
		 return BCrypt.checkpw(password, hashPassword);
	 }

}