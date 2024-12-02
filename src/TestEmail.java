public class TestEmail {
    Boolean checkEmail(String  email){
        if(email.isEmpty()) {
        return false;
    }
     if (!email.contains(Character.toString('@'))){
        return false;
    }
     if (email.endsWith("@") || email.endsWith(".")) {
            return false;
     }
     int atindex = email.indexOf('@');
     if(atindex==email.length()-1){
         return false;
     }
     int dotindex=0;

    for(int i=0;i<email.length();i++){
        if (email.charAt(0)=='@'||email.charAt(0)=='.'){
                return false;
        }
        if(email.charAt(i)=='#'||email.charAt(i)=='!'||email.charAt(i)==' '){
                return false;
        }
        if (i<email.length()-1&&email.charAt(i)=='.'&&email.charAt(i+1)=='.'){
                return false;
        }
        if(email.charAt(i)=='.'){
            dotindex=i;
        }
    }
    if(dotindex==0||dotindex==email.length()-1||dotindex<atindex){
        return false;
    }
    if(dotindex!=0){
        String domain = email.substring(dotindex+1);
        if(domain.length()<3||domain.length()>6){
            return false;
        }
    }
    return true;
    }
}
