public class Rechnungen {
    private int reNr, kdnr;
    private String datum;
    private double gesamtbetrag;

    public Rechnungen() {
    }

    public Rechnungen(int reNr, String datum, double gesamtbetrag, int kdnr) {
        this.reNr = reNr;
        this.kdnr = kdnr;
        this.datum = datum;
        this.gesamtbetrag = gesamtbetrag;
    }

    public int getReNr() {
        return reNr;
    }

    public void setReNr(int reNr) {
        this.reNr = reNr;
    }

    public int getKdnr() {
        return kdnr;
    }

    public void setKdnr(int kdnr) {
        this.kdnr = kdnr;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public double getGesamtbetrag() {
        return gesamtbetrag;
    }

    public void setGesamtbetrag(double gesamtbetrag) {
        this.gesamtbetrag = gesamtbetrag;
    }

    @Override
    public String toString() {
        return "Rechnungen{" +
                "reNr=" + reNr +
                ", kdnr=" + kdnr +
                ", datum='" + datum + '\'' +
                ", gesamtbetrag=" + gesamtbetrag +
                '}';
    }
}
