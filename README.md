# Entwurfsmuster

## Aufgabe 1: ENTWURFSMUSTER 1

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
