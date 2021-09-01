import java.sql.*;
import java.util.ArrayList;
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
    public int insertKunde(Kunde k){
        int lastId=0;
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
            lastId = rs.getInt("rowid");

            stmtAutoincrement.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        k.setKdnr(lastId);
        return lastId;
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

    public Kunde getKunde(int kdnr){
        Kunde k = new Kunde();
        String selectSQL = "SELECT * FROM Kunden WHERE KdNr = ?";
        try (Connection conn = DriverManager.getConnection(connectionString);
             PreparedStatement stmt = conn.prepareStatement(selectSQL)) {
            stmt.setInt(1,kdnr);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                k.setKdnr(rs.getInt("KdNr"));
                k.setVorname(rs.getString("Vorname"));
                k.setNachname(rs.getString("Nachname"));
                k.setGeschlecht(rs.getString("Geschlecht"));
                k.setBonuspunkte(rs.getInt("Bonuspunkte"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return k;
    }

    public ArrayList<Kunde>getAlleKunden(){
        String selectSQL = "SELECT * FROM Kunden";
        ArrayList<Kunde> kundenOOP = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(connectionString);
             PreparedStatement stmt = conn.prepareStatement(selectSQL)) {
             ResultSet rs = stmt.executeQuery();
             //String geschlecht=rs.getString("Geschlecht");
                while (rs.next()){
                    int kdnr= rs.getInt("KdNr");
                    Kunde k = getKunde(kdnr);
                    kundenOOP.add(k);
                }

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return kundenOOP;
    }

    public void updateKunde(Kunde k){
        String sqlUpdate = "UPDATE Kunden SET Vorname = ?, Nachname = ?, Geschlecht = ?, Bonuspunkte = ? WHERE KdNr = ?";
        try (Connection conn = DriverManager.getConnection(connectionString);
             PreparedStatement stmt = conn.prepareStatement(sqlUpdate)) {
            stmt.setString(1,k.getVorname());
            stmt.setString(2,k.getNachname());
            stmt.setString(3,k.getGeschlecht());
            stmt.setInt(4,k.getBonuspunkte());
            stmt.setInt(5,k.getKdnr());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows==0){
                System.out.println("Kein Kunde gefunden");
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public int insertRechnung(Rechnungen neueRechnung, Kunde vorhandenerKunde) {
        String insSQL = "INSERT INTO Rechnungen (Datum,Gesamtbetrag,KdNr)";
        insSQL += "VALUES(?,?,?)";
        String pragmaForeignKey = "PRAGMA foreign_keys=on;";
        String sqlText = "SELECT last_insert_rowid() as rowid";
        int lastId = 0;
        try (Connection conn = DriverManager.getConnection(connectionString);
             Statement stmtPragma = conn.createStatement();
             PreparedStatement stmt = conn.prepareStatement(insSQL);
             PreparedStatement stmtLastRowId = conn.prepareStatement(sqlText)   ) {
            stmtPragma.execute(pragmaForeignKey);
            stmt.setString(1, neueRechnung.getDatum());
            stmt.setDouble(2, neueRechnung.getGesamtbetrag());
            stmt.setInt(3, vorhandenerKunde.getKdnr());

            stmt.executeUpdate();

            ResultSet rs = stmtLastRowId.executeQuery();
            rs.next();
            lastId = rs.getInt("rowid");
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        neueRechnung.setReNr(lastId);
        return lastId;
    }
    public void insertKundeUndRechnungen(ArrayList<Rechnungen> neueRechnungen, Kunde neuerKunde){
        int newId = insertKunde(neuerKunde);

        for (int i = 0; i < neueRechnungen.size(); i++) {
            Rechnungen r = neueRechnungen.get(i);
            r.setKdnr(newId);
            insertRechnung(r,neuerKunde);
        }
    }
    public void updateRechnung(Rechnungen neueRechnung){
        String sqlUpdate = "UPDATE Rechnungen SET Datum = ?, Gesamtbetrag = ?, KdNr = ? WHERE ReNr = ?";
        try (Connection conn = DriverManager.getConnection(connectionString);
             PreparedStatement stmt = conn.prepareStatement(sqlUpdate)) {
            stmt.setString(1,neueRechnung.getDatum());
            stmt.setDouble(2,neueRechnung.getGesamtbetrag());
            stmt.setInt(3,neueRechnung.getKdnr());
            stmt.setInt(4,neueRechnung.getReNr());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows==0){
                System.out.println("Kein Kunde gefunden");
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Rechnungen>getRechnungenByKunde(int kdnr){
        ArrayList<Rechnungen> rechnungen = new ArrayList<>();
        String sqlSelect = "SELECT * FROM Rechnungen WHERE KdNr = ?";
        try (Connection conn = DriverManager.getConnection(connectionString);
             PreparedStatement stmt = conn.prepareStatement(sqlSelect)) {
            stmt.setInt(1,kdnr);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Rechnungen r = new Rechnungen();
                r.setReNr(rs.getInt("ReNr"));
                r.setDatum(rs.getString("Datum"));
                r.setGesamtbetrag(rs.getDouble("Gesamtbetrag"));
                r.setKdnr(rs.getInt("KdNr"));
                rechnungen.add(r);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return rechnungen;
    }

    public ArrayList<Kunde>getWeiblicheKunden(){
        ArrayList<Kunde>weiblichKunden = new ArrayList<>();
        String sqlSelect = "SELECT * FROM Kunden WHERE Geschlecht = 'Frau'";
        try (Connection conn = DriverManager.getConnection(connectionString);
             PreparedStatement stmt = conn.prepareStatement(sqlSelect)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int kdnr = rs.getInt("KdNr");
                Kunde k = getKunde(kdnr);
                weiblichKunden.add(k);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return weiblichKunden;
    }

    public Kunde getKundeMitDenMeistenBonusPunkten(){
        Kunde k = new Kunde();
        String selectPunkten = "SELECT * FROM Kunden ORDER BY Bonuspunkte DESC";
        try (Connection conn = DriverManager.getConnection(connectionString);
             PreparedStatement stmt = conn.prepareStatement(selectPunkten)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                int kdnr = rs.getInt("KdNr");
                k = getKunde(kdnr);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return k;
    }

    public void loescheAlleRechnungenUndDanachDenKunden (Kunde k){
        String deleteRechnungen="DELETE FROM Rechnungen WHERE KDNR = ?";
        String deleteKunde="DELETE FROM Kunden WHERE KDNR = ?";
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
        }

        try (
                PreparedStatement pStmtDeleteRechnungen = conn.prepareStatement(deleteRechnungen);
                PreparedStatement pStmtDeleteKunde = conn.prepareStatement(deleteKunde);
        ) {


            pStmtDeleteKunde.setInt(1,k.getKdnr());
            pStmtDeleteRechnungen.setInt(1,k.getKdnr());

            conn.setAutoCommit(false);
            //Order matters
            pStmtDeleteRechnungen.executeUpdate();
            pStmtDeleteKunde.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            if (conn!=null){
                try {
                    conn.rollback();
                } catch (SQLException e2) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void printKundenMetadata()
    {
        try (Connection conn = DriverManager.getConnection(connectionString);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Kunden")) {
            ResultSet rs = stmt.executeQuery();

            ResultSetMetaData  meta = rs.getMetaData();
            int numerics = 0;

            for ( int i = 1; i <= meta.getColumnCount(); i++ )
            {
                System.out.printf( "%-20s %-20s%n", meta.getColumnLabel( i ),
                        meta.getColumnTypeName( i ) );

                if ( meta.isSigned( i ) )
                    numerics++;
            }

            System.out.println();
            System.out.println( "Spalten: " + meta.getColumnCount() +
                    ", Numerisch: " + numerics );

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }
}
