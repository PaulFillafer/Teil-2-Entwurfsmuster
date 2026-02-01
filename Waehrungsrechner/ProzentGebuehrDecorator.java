public class ProzentGebuehrDecorator extends WRDecorator {
    public ProzentGebuehrDecorator(IUmrechnen rechner) { super(rechner); }

    @Override
    public double umrechnen(String variante, double betrag) {
        double ergebnis = super.umrechnen(variante, betrag);
        if (!variante.toUpperCase().contains("YEN")) {
            return ergebnis * 1.005; // 0,5% Gebühr für alles außer Yen
        }
        return ergebnis;
    }
}