package staticproperties;

public class readerWriter {
    public static void main(String[] args) {
        readerWriter readerWriter = new readerWriter();
        
        readerExternal obj = new readerExternal();
            
            
        System.out.println(StaticPropertyClass.num1);
        System.out.println(StaticPropertyClass.getNum1());
        obj.printData();
        StaticPropertyClass.num1=34;
        System.out.println(StaticPropertyClass.num1);
        System.out.println(StaticPropertyClass.getNum1());
        obj.printData();
        StaticPropertyClass.setNum1(234);
        System.out.println(StaticPropertyClass.num1);
        System.out.println(StaticPropertyClass.getNum1());
        obj.printData();
        
        
        System.out.println(StaticPropertyClass.s);
        System.out.println(StaticPropertyClass.getS());
        obj.printData();
        StaticPropertyClass.s="asasasasa";
        System.out.println(StaticPropertyClass.s);
        System.out.println(StaticPropertyClass.getS());
        obj.printData();
        StaticPropertyClass.setS("wewewwrewr");
        System.out.println(StaticPropertyClass.s);
        System.out.println(StaticPropertyClass.getS());
        obj.printData();
    }
}
