import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class LogObserver implements IObserver {
    @Override
    public void update(double start, String von, String zu, double ziel) {
        try (PrintWriter out = new PrintWriter(new FileWriter("umrechnungen.log", true))) {
            out.printf("[%s] Umrechnung: %.2f %s -> %.2f %s%n",
                    LocalDateTime.now(), start, von, ziel, zu);
        } catch (Exception e) {
            System.err.println("Logging fehlgeschlagen: " + e.getMessage());
        }
    }
}