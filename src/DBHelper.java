import java.sql.*;
import java.util.Scanner;


public class DBHelper {
    private  int numberForMethod;
    private String connectionString ="jdbc:sqlite:C:/sqlite/db/klausur.db";
    Scanner input = new Scanner(System.in);
    public void methods(int number){
        numberForMethod = number;
        switch (numberForMethod){
            case 1:
                createTableKunden();
                break;
            case 2:
                createTableRechnungen();
                break;
            case 3:
                input.next();
               // addKunde();
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

    public void addKunde(Kunde k){
        String insSQL = "INSERT INTO Kunden (Vorname,Nachname,Geschlecht,Bonuspunkte)";
        insSQL += "VALUES(?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(connectionString);
             PreparedStatement stmt = conn.prepareStatement(insSQL)) {
            stmt.setString(1, k.getVorname());
            stmt.setString(2, k.getNachname());
            stmt.setString(3, k.getGeschlecht());
            stmt.setInt(4,k.getBonuspunkte());
            stmt.executeUpdate();
            stmt.close();
            String sqlText ="SELECT last_insert_rowid() as rowid";
            PreparedStatement stmtAutoincrement = conn.prepareStatement(sqlText);
            ResultSet rs = stmtAutoincrement.executeQuery();
            rs.next();
            int autoincrementValue = rs.getInt("rowid");
            k.setKdnr(autoincrementValue);
            System.out.println(autoincrementValue);
            stmtAutoincrement.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void addRechnung(Rechnungen r){
        String insSQL = "INSERT INTO Rechnungen (Datum,Gesamtbetrag,KdNr)";
        insSQL += "VALUES(?,?,?)";
        String pragmaForeignKey = "PRAGMA foreign_keys=on;";
        try (Connection conn = DriverManager.getConnection(connectionString);
             Statement stmtPragma = conn.createStatement();
             PreparedStatement stmt = conn.prepareStatement(insSQL)) {
            stmtPragma.execute(pragmaForeignKey);
            stmt.setString(1, r.getDatum());
            stmt.setDouble(2, r.getGesamtbetrag());
            stmt.setInt(3,r.getKdnr());
            stmt.executeUpdate();
            stmt.close();
            String sqlText ="SELECT last_insert_rowid() as rowid";
            PreparedStatement stmtAutoincrement = conn.prepareStatement(sqlText);
            ResultSet rs = stmtAutoincrement.executeQuery();
            rs.next();
            int autoincrementValue = rs.getInt("rowid");
            r.setReNr(autoincrementValue);
            System.out.println(autoincrementValue);
            stmtAutoincrement.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
