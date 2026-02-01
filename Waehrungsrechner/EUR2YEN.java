public class EUR2YEN extends WR{
    @Override
    public double getFaktor() { return 162.50; }

    @Override
    public boolean istZustaendig(String variante) {
        return "EUR2YEN".equalsIgnoreCase(variante);
    }
}
