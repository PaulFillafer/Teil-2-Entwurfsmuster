import java.util.ArrayList;
import java.util.List;

public abstract class WR implements IUmrechnen {
    private WR next;
    // Wir machen die Liste protected, damit Decorator darauf zugreifen könnten,
    // oder wir lassen sie private und stellen sicher, dass add sie verteilt.
    private List<IObserver> observers = new ArrayList<>();

    public void addObserver(IObserver observer) {
        observers.add(observer);
        // WICHTIG: Den Observer an die gesamte Kette weitergeben!
        if (next != null) {
            next.addObserver(observer);
        }
    }

    @Override
    public final double umrechnen(String variante, double betrag) {
        if (zustaendig(variante)) {
            double ergebnis = betrag * getFaktor();
            // Hier wird benachrichtigt
            notifyObservers(betrag, "EUR", variante, ergebnis);
            return ergebnis;
        } else if (next != null) {
            // Die Kette geht weiter
            return next.umrechnen(variante, betrag);
        }
        throw new IllegalArgumentException("Kein Rechner für " + variante + " gefunden.");
    }

    protected void notifyObservers(double start, String von, String zu, double ziel) {
        for (IObserver obs : observers) {
            obs.update(start, von, zu, ziel);
        }
    }

    public void add(WR next) {
        if (this.next == null) {
            this.next = next;
            // Wenn wir bereits Observer haben, geben wir sie dem neuen Glied weiter
            for (IObserver obs : this.observers) {
                next.addObserver(obs);
            }
        } else {
            this.next.add(next);
        }
    }

    // Hook-Methoden für Unterklassen (Aufgabe 5)
    public abstract double getFaktor();

    // In deinem vorherigen Fehler-Check hieß sie 'istZustaendig'
    public abstract boolean istZustaendig(String variante);

    // Die finale Weiche für die Template-Methode (Aufgabe 3)
    public final boolean zustaendig(String variante) {
        return istZustaendig(variante);
    }
}