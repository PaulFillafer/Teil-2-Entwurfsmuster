public class EUR2Dollar extends WR{
    @Override
    public double getFaktor() { return 1.09; }

    @Override
    public boolean istZustaendig(String variante) {
        return "EUR2USD".equalsIgnoreCase(variante);
    }
}
