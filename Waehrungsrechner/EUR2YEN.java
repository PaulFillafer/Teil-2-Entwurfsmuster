public class EUR2YEN extends WR{
    @Override
    public double getFaktor() { return 162.50; }

    @Override
    public boolean zustaendig(String variante) {
        return "EUR2YEN".equalsIgnoreCase(variante);
    }
}
