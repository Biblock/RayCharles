package engine;

public enum Sound {
    BOING("../ressources/sons/boing.wav"),
    FUNNYSLIP("../ressources/sons/funnySlip.wav"),
    METALCLANG("../ressources/sons/metalClang2.wav"),
    FAIL("../ressources/sons/fail.wav"),

    AIDEACCUEIL("../ressources/sons/AideAccueil(Damien).wav"),
    MESSAGEACCUEIL("../ressources/sons/MessageAccueil(Damien).wav");

    public String getUrl() {
        return url;
    }

    private String url;

    Sound(String url) {
        this.url = url;
    }
}
