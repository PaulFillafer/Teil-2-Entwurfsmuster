public class WRAdapter implements ISammelumrechnung {

    // Das "Adaptee" (das anzupassende Objekt)
    private IUmrechnen rechner;

    public WRAdapter(IUmrechnen rechner) {
        this.rechner = rechner;
    }


    @Override
    public double sammelumrechnen(double[] betraege, String variante) {
        double gesamtsumme = 0;

        for (double einzelBetrag : betraege) {
            // Delegation an die bestehende Logik (Chain, Decorator, etc.)
            gesamtsumme += rechner.umrechnen(variante, einzelBetrag);
        }

        return gesamtsumme;
    }
}