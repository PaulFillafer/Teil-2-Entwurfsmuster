public class StandardWR extends WR {
    private double faktor;
    private String variante;

    public StandardWR(String variante, double faktor) {
        this.variante = variante;
        this.faktor = faktor;
    }

    @Override
    public double getFaktor() {
        return this.faktor;
    }

    @Override
    public boolean istZustaendig(String variante) {
        return this.variante.equalsIgnoreCase(variante);
    }
}