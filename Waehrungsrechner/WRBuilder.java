public class WRBuilder {
    private WR kette = null;
    private boolean mitProzent = false;
    private boolean mitYenFix = false;


    public WRBuilder addRechner(WR neuerRechner) {
        if (this.kette == null) {
            this.kette = neuerRechner;
        } else {
            this.kette.add(neuerRechner);
        }
        return this; // Ermöglicht das "Chaining" (.add().add())
    }

    public WRBuilder withProzentGebuehr() {
        this.mitProzent = true;
        return this;
    }

    public WRBuilder withYenFixGebuehr() {
        this.mitYenFix = true;
        return this;
    }


    public IUmrechnen build() {
        if (kette == null) {
            throw new IllegalStateException("Der Builder benötigt mindestens einen Währungsrechner!");
        }

        IUmrechnen result = kette;


        if (mitProzent) {
            result = new ProzentGebuehrDecorator(result);
        }

        if (mitYenFix) {
            result = new YenFixGebuehrDecorator(result);
        }

        return result;
    }
}