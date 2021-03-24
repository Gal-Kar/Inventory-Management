package Model;


public class GeneralFuncs {
    public static Boolean checkStringIfOnlyNumbers(String str){
        if(str==null)
            return false;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)<'0'||str.charAt(i)>'9')
                return false;
        }
        return true;
    }

    public static String removeSpaces(String str){

        str = str.replaceAll("\\s+","");
        return str;
    }
}
