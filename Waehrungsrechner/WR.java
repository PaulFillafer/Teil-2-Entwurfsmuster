public abstract class WR implements IUmrechnen {
    protected WR next;

    @Override
    public final double umrechnen(String variante, double betrag) {
        if (zustaendig(variante)) {
            return betrag * getFaktor(); // Hook-Aufrufe
        } else if (next != null) {
            return next.umrechnen(variante, betrag); // Chain-Delegation
        }
        throw new IllegalArgumentException("Kein Rechner für " + variante + " gefunden.");
    }


    public void add(WR neuerRechner) {
        if (this.next == null) {
            this.next = neuerRechner;
        } else {
            this.next.add(neuerRechner);
        }
    }


    public void removeLast() {
        if (this.next == null) return;

        if (this.next.next == null) {
            this.next = null; // Letztes Glied kappen
        } else {
            this.next.removeLast();
        }
    }
}