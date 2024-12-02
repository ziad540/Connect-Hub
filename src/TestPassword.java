public class TestPassword {
     public Boolean testPassword(String password) {
        if (password.length() < 8) {
            return false;
        }
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) == ' ') {
                return false;
            }
        }
        return true;
    }
}
