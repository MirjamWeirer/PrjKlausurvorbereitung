public class Kunde {
    private int kdnr, bonuspunkte;
    private String vorname, nachname, geschlecht;

    public Kunde() {
    }

    public Kunde(int kdnr, String vorname, String nachname, String geschlecht, int bonuspunkte) {
        this.kdnr = kdnr;
        this.bonuspunkte = bonuspunkte;
        this.vorname = vorname;
        this.nachname = nachname;
        this.geschlecht = geschlecht;
    }

    public int getKdnr() {
        return kdnr;
    }

    public void setKdnr(int kdnr) {
        this.kdnr = kdnr;
    }

    public int getBonuspunkte() {
        return bonuspunkte;
    }

    public void setBonuspunkte(int bonuspunkte) {
        this.bonuspunkte = bonuspunkte;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }

    @Override
    public String toString() {
        return "Kunde{" +
                "kdnr=" + kdnr +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", geschlecht='" + geschlecht + '\'' +
                ", bonuspunkte=" + bonuspunkte +
                '}';
    }
}
