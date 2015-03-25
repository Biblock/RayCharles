package devintAPI;

public enum Sounds {
    BOING("../ressources/sons/boing.wav"),
    FUNNYSLIP("../ressources/sons/funnySlip.wav"),
    METALCLANG("../ressources/sons/metalClang.wav");

    public String getUrl() {
        return url;
    }

    private String url;

    Sounds(String url) {
        this.url = url;
    }
}
