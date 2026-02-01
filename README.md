# Entwurfsmuster

## Aufgabe 1: ENTWURFSMUSTER 1: Defintion von Entwurfsmustern

### Definition
Entwurfsmuster (Design Patterns) sind bewährte, abstrakte Lösungsschablonen für wiederkehrende Architekturprobleme in der Softwareentwicklung. Sie stellen keinen fertigen Code dar, sondern definieren ein **Best-Practice-Konzept**, um Objekte so zu strukturieren, dass das System flexibel, wartbar und erweiterbar bleibt.

### Beschreibung von Entwurfsmustern
Ein Muster wird nach einem standardisierten Schema dokumentiert, um die Kommunikation zwischen Entwicklern zu präzisieren:

1.  **Name:** Ein prägnanter Fachbegriff (Vokabular), z. B. *Strategy* oder *Observer*.
2.  **Problem (Kontext):** Die Situation, in der das Muster hilft (z. B. "Ich muss zur Laufzeit das Verhalten eines Objekts ändern").
3.  **Lösung (Struktur):** Die Anordnung von Klassen und Interfaces. Diese wird fast immer durch **UML-Klassendiagramme** visualisiert, um Beziehungen wie Vererbung, Realisierung oder Komposition auf einen Blick zu zeigen.
4.  **Konsequenzen:** Eine Abwägung der Vor- und Nachteile (z. B. "Höhere Flexibilität, aber potenziell höhere Komplexität durch mehr Klassen").



### Warum nutzen wir Entwurfsmuster?
* **Gemeinsames Vokabular:** Anstatt komplexe Klassenbeziehungen langatmig zu erklären, reicht der Name des Musters ("Hier nutzen wir einen Decorator"), um das Design für andere Entwickler sofort verständlich zu machen.
* **Wiederverwendbarkeit von Erfahrung:** Man nutzt Lösungen, die sich über Jahrzehnte in der industriellen Praxis bewährt haben (z. B. aus dem "Gang of Four"-Katalog).
* **Wartbarkeit durch Entkopplung:** Muster helfen dabei, wichtige Software-Prinzipien wie das **Open-Closed-Prinzip** einzuhalten: Code bleibt offen für Erweiterungen, ist aber geschlossen für Modifikationen am bestehenden Kern.

## Aufgabe 2: ENTWURFSMUSTER 2: 6 Wichtige Entwurfsmuster


### 1. Chain of Responsibility (Verantwortlichkeitskette)
* **Was es macht:** Es reicht eine Anfrage wie eine heiße Kartoffel in einer Kette von Objekten weiter.
* **Warum:** Damit der Absender nicht wissen muss, wer die Anfrage am Ende bearbeitet. Jedes Glied entscheidet selbst: „Bin ich zuständig? Wenn nicht -> ab zum Nächsten.“
* **Aufgabenstellung:** Ein technischer Support mit drei Stufen (Level 1, Level 2, Experte).
* **Zentraler Kern:** Das Feld `nextHandler` und die Delegationslogik in der Bearbeitungsmethode.
* **Code-Beispiel:**
    ```java
    public abstract class SupportHandler {
        protected SupportHandler next; // Die Kette
        public void setNext(SupportHandler next) { this.next = next; }

        public void handleRequest(int difficulty) {
            if (canHandle(difficulty)) {
                resolve();
            } else if (next != null) {
                next.handleRequest(difficulty); // Weitergabe an den Nächsten
            } else {
                System.out.println("Niemand zuständig.");
            }
        }
        protected abstract boolean canHandle(int diff);
        protected abstract void resolve();
    }
    ```


### 2. Template Method (Schablonenmethode)
* **Was es macht:** Es legt die starre Reihenfolge eines Algorithmus in einer Oberklasse fest, lässt aber Lücken (Methoden) für die Details.
* **Warum:** Damit Unterklassen zwar Details ändern können (z. B. CSV statt JSON exportieren), aber den übergeordneten Prozess nicht zerstören können.
* **Aufgabenstellung:** Ein einheitlicher Prozess für den Daten-Export (CSV und JSON).
* **Zentraler Kern:** Eine `final` Methode in der Basisklasse, die den Algorithmus-Ablauf erzwingt, während Details in Subklassen "gehookt" werden.
* **Code-Example:**
    ```java
    public abstract class DataExporter {
        // Das Skelett: Festgelegter Ablauf
        public final void export() {
            readData();
            String formatted = formatData(); // Hook: Variabler Teil
            saveToFile(formatted);
        }
        
        private void readData() { System.out.println("Lese DB..."); }
        private void saveToFile(String d) { System.out.println("Speichere: " + d); }
        protected abstract String formatData(); // Unterklassen liefern Format
    }
    ```

### 3. Decorator (Dekorierer)
* **Was es macht:** Es wickelt ein Objekt in ein anderes Objekt desselben Typs ein, um neue Funktionen hinzuzufügen.
* **Warum:** Man kann Funktionalität (wie Gebühren oder Verschlüsselung) flexibel zur Laufzeit „draufschlagen“, ohne die ursprüngliche Klasse zu verändern.
* **Aufgabenstellung:** Ein Nachrichtensystem, das Nachrichten verschlüsseln oder signieren kann.
* **Zentraler Kern:** Die Decorator-Klasse implementiert das Interface UND besitzt eine Instanz desselben Typs (Wrapper-Prinzip).
* **Code-Example:**
    ```java
    public class EncryptionDecorator implements Message {
        private Message wrappedMessage; // Das Original

        public EncryptionDecorator(Message m) { this.wrappedMessage = m; }

        @Override
        public String getContent() {
            // Erst Original holen, dann verändern (dekorieren)
            return encrypt(wrappedMessage.getContent());
        }
    }
    ```


### 4. Builder (Erbauer)
* **Was es macht:** Er baut ein komplexes Objekt Schritt für Schritt zusammen, anstatt alles in einen riesigen, unübersichtlichen Konstruktor zu stopfen.
* **Warum:** Damit man bei Objekten mit vielen Optionen (z. B. ein Auto mit oder ohne Navi, Klima, Schiebedach) den Überblick behält und nur das konfiguriert, was man braucht.
* **Aufgabenstellung:** Erstellung eines komplexen Benutzerprofils mit vielen optionalen Feldern.
* **Zentraler Kern:** Methoden, die das Objekt schrittweise konfigurieren und `return this` für Method-Chaining nutzen.
* **Code-Example:**
    ```java
    User user = new User.Builder()
                .withName("Max")
                .withAge(25)
                .isPremium(true) // Optional
                .build(); // Finaler Schritt
    ```

### 5. Adapter
* **Was es macht:** Er übersetzt die Schnittstelle einer Klasse in eine andere Schnittstelle, die der Client erwartet.
* **Warum:** Damit zwei Klassen zusammenarbeiten können, die eigentlich nicht zusammenpassen (wie ein runder Stecker in eine eckige Dose).
* **Aufgabenstellung:** Ein modernes System erwartet ein `Logger`-Interface, muss aber eine alte `LegacyLog`-Klasse integrieren.
* **Zentraler Kern:** Die Adapter-Klasse "übersetzt" den Aufruf einer Methode in eine andere.
* **Code-Example:**
    ```java
    public class LogAdapter implements ModernLogger {
        private OldLegacySystem legacy;

        public void logMessage(String msg) {
            legacy.oldLogFunction(msg, 1); // Übersetzung der Parameter/Signatur
        }
    }
    ```

### 6. Observer (Beobachter)
* **Was es macht:** Ein zentrales Objekt (Subjekt) führt eine Liste von Beobachtern und gibt allen Bescheid, sobald sich sein Zustand ändert.
* **Warum:** Damit andere Programmteile automatisch reagieren können (z. B. UI-Update), ohne ständig selbst nachfragen zu müssen („Bist du schon fertig?“).
* **Aufgabenstellung:** Ein Aktienkurs-Ticker, der mehrere Displays aktualisiert.
* **Zentraler Kern:** Das Subjekt hält eine `List<Observer>` und informiert diese in einer Schleife (Push-Prinzip).
* **Code-Example:**
    ```java
    public void notifyObservers(double newPrice) {
        for (StockObserver observer : observers) {
            observer.update(newPrice); // Alle Abonnenten benachrichtigen
        }
    }
    ```

## Aufgabe 3: Architektur-Fundament

Bevor die Entwurfsmuster implementiert werden, wurde die grundlegende Domäne des Währungsrechners definiert. Ziel dieses Schrittes ist die Herstellung der Typsicherheit und die Definition der Verträge (Interfaces).

### Struktur
1. **IUmrechnen (Interface):** Das funktionale Herzstück. Es legt fest, dass jeder Rechner umrechnen kann, einen Faktor besitzt und seine eigene Zuständigkeit prüfen muss.
2. **ISammelumrechnung (Interface):** Eine Erweiterung für die Stapelverarbeitung, die eine Trennung der Verantwortlichkeiten (Separation of Concerns) zwischen Einzel- und Massenberechnung ermöglicht.
3. **WR (Abstrakte Klasse):** Dient als Basistyp. In diesem Stadium ist sie rein abstrakt und erzwingt lediglich, dass alle zukünftigen Rechner die `IUmrechnen`-Schnittstelle bedienen.

## Aufgabe 4: Chain of Responsibility (Verantwortlichkeitskette)

### 1. Architektur & Klassen
* **`WR` (Abstrakter Handler):** Definiert das Grundgerüst für die Kette und implementiert die zentrale Weiterleitungslogik.
* **`EUR2Dollar` & `EUR2YEN` (Konkrete Handler):** Implementieren die spezifische Umrechnungslogik für ihre jeweilige Währungsvariante.

### 2. Erfüllung der funktionalen Anforderungen
* **Zuständigkeitsprüfung:** Jeder Handler prüft über die Methode `zustaendig(String variante)`, ob er die Anfrage selbst bearbeiten kann.
* **Delegation (Der Kern):** Ist ein Glied nicht zuständig, wird die Anfrage über `next.umrechnen(...)` an den Nachfolger delegiert.
* **Dynamische Kettenverwaltung:** * **Aufnahme:** Die Methode `add(WR neuerRechner)` durchläuft die Kette rekursiv bis zum Ende (`next == null`), um ein neues Element anzuhängen.
    * **Löschen:** Die Methode `removeLast()` sucht rekursiv das vorletzte Glied, um dessen Verbindung zum letzten Glied zu kappen.



### 3. Lern-Check: Identifikation des Musters
Der **zentrale Kern** dieses Musters lässt sich im Code an zwei Merkmalen sofort identifizieren:
1. **Struktur (Selbstähnlichkeit):** Die Klasse besitzt eine Referenz auf den eigenen Typ (`protected WR next`). Dies ermöglicht eine beliebig lange Verkettung von Objekten.
2. **Verhalten (Delegation):** Die Methode `umrechnen` implementiert eine "Entweder-Oder"-Logik: Entweder der aktuelle Handler bearbeitet die Anfrage selbst, oder er reicht sie an das nächste Glied weiter.

> **Wichtig für die Praxis:** Um einen Endlos-Loop oder einen "Silent Failure" zu verhindern, bricht die Kette ab, sobald `next == null` erreicht ist. In diesem Fall wird eine `IllegalArgumentException` geworfen, was dem Client signalisiert, dass kein passender Rechner existiert.

## Aufgabe 5: Template Method (Schablonenmethode)

### Lernziel
Das Ziel der **Template Method** ist es, das Grundgerüst eines Algorithmus festzulegen und einzelne Schritte an Unterklassen zu delegieren. Dies verhindert Code-Duplizierung und sorgt für einen stabilen Prozessablauf.

### Umsetzung im Projekt
In der Klasse `WR` fungiert die Methode `umrechnen()` als Schablone. Sie definiert exakt drei Phasen:
1. **Validierung:** Aufruf der abstrakten Methode `zustaendig()`.
2. **Berechnung:** Aufruf der abstrakten Methode `getFaktor()`.
3. **Delegation:** Weitergabe an `next`, falls die Validierung fehlschlägt.

### Der "zentrale Kern" im Code
Der Kern ist das Zusammenspiel zwischen einer **finalen Methode** (der Schablone) und **abstrakten Methoden** (den Hooks) in derselben Klasse:

* **Schablone:** `public final double umrechnen(...)` – Garantiert, dass die Ketten-Logik nicht überschrieben werden kann.
* **Hooks:** `zustaendig()` und `getFaktor()` – Müssen von konkreten Rechnern implementiert werden.

### Vorteil dieser Architektur
Würde man dieses Muster nicht nutzen, müsste jeder neue Rechner (z.B. `EuroToPfund`) die gesamte Logik der `if-else`-Kette und die Fehlerbehandlung selbst kopieren. Durch die Template Method ist dieser Ablauf zentralisiert und wartungsfreundlich.
