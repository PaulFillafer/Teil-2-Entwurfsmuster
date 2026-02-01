public abstract class WRDecorator implements IUmrechnen {
    protected IUmrechnen geschmueckterRechner;

    public WRDecorator(IUmrechnen rechner) {
        this.geschmueckterRechner = rechner;
    }

    @Override
    public boolean zustaendig(String variante) {
        return geschmueckterRechner.zustaendig(variante);
    }

    @Override
    public double getFaktor() {
        return geschmueckterRechner.getFaktor();
    }

    @Override
    public double umrechnen(String variante, double betrag) {
        // Transparente Weiterleitung an die Kette
        return geschmueckterRechner.umrechnen(variante, betrag);
    }
}