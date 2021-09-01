import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MyStarter {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        PrintStream output = new PrintStream(System.out);
        DBHelper db = new DBHelper();
        boolean count = true;
//        output.println("Bitte gib eine Zahl (3 -  ); (1 und 2 erstellen Tabllen falls sie noch nicht existieren); 0 zum beenden");
//        int number;
//        do {
//            number= input.nextInt();
//            if (number == 0){
//                output.println("Programm beendet");
//                count = false;
//            }else {
//                db.methods(number);
//            }
//        }while (count);


//        Kunde k = new Kunde(0,"Elisabeth","Mayer","",23);
//        db.addKunde(k);
//        Rechnungen r = new Rechnungen(0,"20.8.2021",300,2);
//        db.addRechnung(r);
        Kunde k = new Kunde();
        //System.out.println(db.getKunde(1));
        //System.out.println(db.getAlleKunden(4));
        //db.getAlleKunden(4);
      // Kunde k4 = db.getKunde(4);
     //  k4.setGeschlecht("Frau");
       // db.updateKunde(k4);
       // System.out.println(k4);
//        Kunde k1 = db.getKunde(1);
//        Rechnungen r1 =new Rechnungen();
//        r1.setDatum("1.03.2021");
//        r1.setGesamtbetrag(200);
//        r1.setKdnr(k1.getKdnr());
//        db.insertRechnung(r1,k1);
//        System.out.println("Neue Rechnung " +r1);
//        Kunde kNeu =new Kunde();
//        kNeu.setVorname("Josef");
//        kNeu.setNachname("Mayer");
//        kNeu.setGeschlecht("Mann");
//        kNeu.setBonuspunkte(100);
//        Rechnungen r = new Rechnungen();
//        r.setDatum("1.03.2021");
//        r.setGesamtbetrag(400);
//
//        ArrayList<Rechnungen> rechnungen = new ArrayList<>();
//        rechnungen.add(r);
//        db.insertKundeUndRechnungen(rechnungen,kNeu);
//        Rechnungen r = new Rechnungen();
//        r.setDatum("1.03.2021");
//        r.setGesamtbetrag(400);
//        r.setKdnr(1);
//        r.setReNr(1);
//        db.updateRechnung(r);
        //System.out.println(db.getRechnungenByKunde(1));
        ;
//        System.out.println(db.getWeiblicheKunden());
//        System.out.println(db.getKundeMitDenMeistenBonusPunkten());

//        Kunde kDelete = new Kunde();
//        kDelete.setKdnr(5);
//        db.loescheAlleRechnungenUndDanachDenKunden(kDelete);
        db.printKundenMetadata();
    }
}
