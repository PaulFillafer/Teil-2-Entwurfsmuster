import com.rometools.rome.feed.synd.*;
import com.rometools.rome.io.SyndFeedOutput;
import java.io.FileWriter;
import java.util.Collections;
import java.util.Date;

public class AtomFeedObserver implements IObserver {
    @Override
    public void update(double start, String von, String zu, double ziel) {
        SyndFeed feed = new SyndFeedImpl();
        feed.setFeedType("atom_1.0");
        feed.setTitle("Währungsrechner Transaktionen");
        feed.setLink("http://mein-waehrungsrechner.de");
        feed.setDescription("Liste der durchgeführten Umrechnungen");

        SyndEntry entry = new SyndEntryImpl();
        entry.setTitle("Transaktion vom " + new Date());

        SyndContent description = new SyndContentImpl();
        description.setValue(String.format("Konvertiert: %.2f %s in %.2f %s", start, von, ziel, zu));
        entry.setDescription(description);

        feed.setEntries(Collections.singletonList(entry));

        try (FileWriter writer = new FileWriter("feed.xml")) {
            new SyndFeedOutput().output(feed, writer);
        } catch (Exception e) {
            System.err.println("Atom-Feed Erstellung fehlgeschlagen.");
        }
    }
}