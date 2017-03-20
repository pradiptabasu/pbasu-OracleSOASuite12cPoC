package staticproperties;

public class StaticPropertyClass {
    static int num1=8;
    static String s="aaa";

    public static void setNum1(int num1) {
        StaticPropertyClass.num1 = num1;
    }

    public static int getNum1() {
        return num1;
    }

    public static void setS(String s) {
        StaticPropertyClass.s = s;
    }

    public static String getS() {
        return s;
    }
}
