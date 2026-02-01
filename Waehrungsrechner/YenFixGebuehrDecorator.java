public class YenFixGebuehrDecorator extends WRDecorator {
    public YenFixGebuehrDecorator(IUmrechnen rechner) { super(rechner); }

    @Override
    public double umrechnen(String variante, double betrag) {
        double ergebnis = super.umrechnen(variante, betrag);
        if (variante.toUpperCase().contains("YEN")) {
            return ergebnis + 5.0; // Fixe Gebühr oben drauf
        }
        return ergebnis;
    }
}