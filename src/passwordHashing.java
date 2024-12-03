import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class passwordHashing {
    public static String hashpassword(String password) {
        try{
            MessageDigest MD=MessageDigest.getInstance("SHA-256");//create instance of SHA-256 by using MessageDigest
            byte[] hashedBytes = MD.digest(password.getBytes());
            StringBuilder hash = new StringBuilder();
            for (byte b : hashedBytes) {
                hash.append(String.format("%02x", b));
            }
            return hash.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
