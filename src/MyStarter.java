import java.io.PrintStream;
import java.util.Scanner;

public class MyStarter {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        PrintStream output = new PrintStream(System.out);
        DBHelper db = new DBHelper();
        boolean count = true;
        output.println("Bitte gib eine Zahl (3 -  ); (1 und 2 erstellen Tabllen falls sie noch nicht existieren); 0 zum beenden");
        int number;
        do {
            number= input.nextInt();
            if (number == 0){
                output.println("Programm beendet");
                count = false;
            }else {
                db.methods(number);
            }
        }while (count);

    }
}
