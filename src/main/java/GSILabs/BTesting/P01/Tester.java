package GSILabs.BTesting.P01;
public class Tester {
        
    public static void main(String[] args) {
        System.out.println("Inicio de test");
        
        //1
        S01 s01 = new S01();
        System.out.print("Test 1:");
        if(s01.testS1())
            System.out.println("\tcompleto");
        else
            System.out.println("\n\tINCOMPLETO");
        
        //2
        S02 s02 = new S02();
        System.out.print("Test 2:");
        if(s02.testS2())
            System.out.println("\tcompleto");
        else
            System.out.println("\n\tINCOMPLETO");
        
        //3
        S03 s03 = new S03();
        System.out.print("Test 3:");
        if(s03.testS3())
            System.out.println("\tcompleto");
        else
            System.out.println("\n\tINCOMPLETO");
        
        //4
        S04 s04 = new S04();
        System.out.print("Test 4:");
        if(s04.testS4())
            System.out.println("\tcompleto");
        else
            System.out.println("\n\tINCOMPLETO");
        
        //5
        S05 s05 = new S05();
        System.out.print("Test 5:");
        if(s05.testS5())
            System.out.println("\tcompleto");
        else
            System.out.println("\n\tINCOMPLETO");
        
        //6
        S06 s06 = new S06();
        System.out.print("Test 6:");
        if(s06.testS6())
            System.out.println("\tcompleto");
        else
            System.out.println("\n\tINCOMPLETO");
        
        //7
        S07 s07 = new S07();
        System.out.print("Test 7:");
        if(s07.testS7())
            System.out.println("\tcompleto");
        else
            System.out.println("\n\tINCOMPLETO");
        
        //8
        S08 s08 = new S08();
        System.out.print("Test 8:");
        if(s08.testS8())
            System.out.println("\tcompleto");
        else
            System.out.println("\n\tINCOMPLETO");
        
        //9
        S09 s09 = new S09();
        System.out.print("Test 9:");
        if(s09.testS9())
            System.out.println("\tcompleto");
        else
            System.out.println("\n\tINCOMPLETO");
        
        //10
        S10 s10 = new S10();
        System.out.print("Test 10:");
        if(s10.testS10())
            System.out.println("\tcompleto");
        else
            System.out.println("\n\tINCOMPLETO");
    }
}