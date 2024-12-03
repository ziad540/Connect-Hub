public class TestUsername implements Validation{
    public Boolean checker(String username) {
        if(username.length()<3||username.length()>16){
            return false;
        }
        for(int i=0;i<username.length();i++){
            if(username.charAt(i)==' '){
                return false;
            }
            if(username.charAt(i)=='$'||username.charAt(i)=='&'||username.charAt(i)=='@'||username.charAt(i)=='!'||username.charAt(i)=='#'){
                return false;
            }
            if(!Character.isAlphabetic(username.charAt(0))){
                return false;
            }
        }
        return true;
    }
}
