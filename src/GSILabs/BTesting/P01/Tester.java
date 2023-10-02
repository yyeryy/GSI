package GSILabs.BTesting.P01;

/**
 * Clase Tester
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 */
public class Tester {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Inicio de tests");
	System.out.println("");
	
	//Test 1
        S01 s01 = new S01();
        System.out.print("Test 01: ");
        if(s01.testS1())
            System.out.println("Completo");
        else
            System.out.println("\n\tINCOMPLETO");
	System.out.println("");

        //Test 2
        S02 s02 = new S02();
        System.out.print("Test 02: ");
        if(s02.testS2())
            System.out.println("Completo");
        else
            System.out.println("\n\tINCOMPLETO");
	System.out.println("");
	
        //Test 3
        S03 s03 = new S03();
        System.out.print("Test 03: ");
        if(s03.testS3())
            System.out.println("Completo");
        else
            System.out.println("\n\tINCOMPLETO");
	System.out.println("");

        //Test 4
        S04 s04 = new S04();
        System.out.print("Test 04: ");
        if(s04.testS4())
            System.out.println("Completo");
        else
            System.out.println("\n\tINCOMPLETO");
	System.out.println("");

        //Test 5
        S05 s05 = new S05();
        System.out.print("Test 05: ");
        if(s05.testS5())
            System.out.println("Completo");
        else
            System.out.println("\n\tINCOMPLETO");
	System.out.println("");

        //Test 6
        S06 s06 = new S06();
        System.out.print("Test 06: ");
        if(s06.testS6())
            System.out.println("Completo");
        else
            System.out.println("\n\tINCOMPLETO");
	System.out.println("");

        //Test 7
        S07 s07 = new S07();
        System.out.print("Test 07: ");
        if(s07.testS7())
            System.out.println("Completo");
        else
            System.out.println("\n\tINCOMPLETO");
	System.out.println("");

        //Test 8
        S08 s08 = new S08();
        System.out.print("Test 08: ");
        if(s08.testS8())
            System.out.println("Completo");
        else
            System.out.println("\n\tINCOMPLETO");
	System.out.println("");

        //Test 9
        S09 s09 = new S09();
        System.out.print("Test 09: ");
        if(s09.testS9())
            System.out.println("Completo");
        else
            System.out.println("\n\tINCOMPLETO");
	System.out.println("");

        //Test 10
        S10 s10 = new S10();
        System.out.print("Test 10: ");
        if(s10.testS10())
            System.out.println("Completo");
        else
            System.out.println("\n\tINCOMPLETO");
    }
}