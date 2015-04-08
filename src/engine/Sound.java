package engine;

public enum Sound {
    BOING("../ressources/sons/boing.wav"),
    FUNNYSLIP("../ressources/sons/funnySlip.wav"),
    METALCLANG("../ressources/sons/metalClang2.wav"),
    FAIL("../ressources/sons/gameover.wav"),

    WIN1("../ressources/sons/incredible.wav"),
    WIN2("../ressources/sons/cool.wav"),
    WIN3("../ressources/sons/unbelievable.wav"),

    COUNTDOWN321("../ressources/sons/countdown321.wav"),

    LANCEMENTMEMORY("../ressources/sons/LancementDuJeu(Damien).wav"),
    AIDEMEMORY("../ressources/sons/RegleDuJeuMemory(Damien).wav"),

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
