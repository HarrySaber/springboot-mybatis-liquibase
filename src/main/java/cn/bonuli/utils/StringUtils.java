package cn.bonuli.utils;


public class StringUtils {

    public static boolean notEmpty(String str) {
        if (null == str || str.trim().equals("")) {
            return false;
        }
        return true;
    }

    public static boolean isEmpty(String str) {
        return !notEmpty(str);
    }


    public static String codeFactory(int id, int count) {
        String str = Integer.toString(id);
        while (str.length() < count) {
            str = "0" + str;
        }
        return str;
    }

    public static String getLowercase(int n) {
        String str = "";
        switch (n) {
            case 1:
                str = "a";
                break;
            case 2:
                str = "b";
                break;
            case 3:
                str = "c";
                break;
            case 4:
                str = "d";
                break;
            case 5:
                str = "e";
                break;
            case 6:
                str = "f";
                break;
            case 7:
                str = "g";
                break;
            case 8:
                str = "h";
                break;
            case 9:
                str = "i";
                break;
            case 10:
                str = "j";
                break;
            case 11:
                str = "k";
                break;
            case 12:
                str = "l";
                break;
            default:
                str = "m";
                break;
        }
        return str;
    }

}
