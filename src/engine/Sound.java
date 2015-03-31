package engine;

public enum Sound {
    BOING("../ressources/sons/boing.wav"),
    FUNNYSLIP("../ressources/sons/funnySlip.wav"),
    METALCLANG("../ressources/sons/metalClang2.wav"),
    FAIL("../ressources/sons/fail.wav");

    public String getUrl() {
        return url;
    }

    private String url;

    Sound(String url) {
        this.url = url;
    }
}
