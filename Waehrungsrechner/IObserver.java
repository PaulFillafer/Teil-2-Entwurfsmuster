public interface IObserver {
    void update(double ausgangsBetrag, String ausgangsWaehrung, String zielWaehrung, double zielBetrag);
}