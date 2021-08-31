import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DBHelper {
    private  int numberForMethod;
    private String connectionString ="jdbc:sqlite:C:/sqlite/db/klausur.db";

    public void methods(int number){
        numberForMethod = number;
        switch (numberForMethod){
            case 1:
                createTableKunden();
                break;
            case 2:
                createTableRechnungen();
                break;
        }
    }

    public void createTableKunden(){
        String createTableSql = "CREATE TABLE IF NOT EXISTS Kunden (\n"
                + "KdNr INTEGER PRIMARY KEY AUTOINCREMENT, \n"
                + "Vorname VARCHAR(50), \n"
                + "Nachname VARCHAR(50), \n"
                + "Geschlecht VARCHAR(10), \n"
                + "Bonuspunkte INTEGER);";

        try (Connection conn = DriverManager.getConnection(connectionString);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSql);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void createTableRechnungen(){
        String createTableSql = "CREATE TABLE IF NOT EXISTS Rechnungen (\n"
                + "ReNr INTEGER PRIMARY KEY AUTOINCREMENT, \n"
                + "Datum VARCHAR(10), \n"
                + "Gesamtbetrag DECIMAL, \n"
                + "KdNr INTEGER, \n"
                + "FOREIGN KEY (KdNr) REFERENCES Kunden (KdNr));";

        try (Connection conn = DriverManager.getConnection(connectionString);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSql);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
