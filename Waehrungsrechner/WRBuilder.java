public class WRBuilder {
    private double faktor;
    private String variante;
    private WR next;

    public WRBuilder mitFaktor(String variante, double faktor) {
        this.variante = variante;
        this.faktor = faktor;
        return this;
    }

    public WRBuilder setNext(WR next) {
        this.next = next;
        return this;
    }


    public WR build() {
        StandardWR neuerRechner = new StandardWR(variante, faktor);

        if (this.next != null) {
            neuerRechner.add(this.next); // Nutzt die add-Methode der Chain (Aufgabe 4)
        }

        return neuerRechner;
    }
}