public abstract class WR implements IUmrechnen {
    protected WR next;

    @Override
    public final double umrechnen(String variante, double betrag) {
        if (istZustaendig(variante)) {
            return betrag * getFaktor();
        } else if (next != null) {
            return next.umrechnen(variante, betrag);
        }
        throw new IllegalArgumentException("Kein Rechner für " + variante + " gefunden.");
    }

    // Schablone für Zuständigkeit: Wandert durch die gesamte Kette
    @Override
    public final boolean zustaendig(String variante) {
        if (istZustaendig(variante)) {
            return true;
        } else if (next != null) {
            return next.zustaendig(variante);
        }
        return false;
    }

    // Hooks für die konkreten Währungen
    protected abstract boolean istZustaendig(String variante);
    public abstract double getFaktor();

    public void add(WR neuerRechner) {
        if (this.next == null) this.next = neuerRechner;
        else this.next.add(neuerRechner);
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