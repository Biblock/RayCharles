package engine;


import java.awt.event.KeyEvent;

public enum KeySound {
    LEFT(Sound.BOING, KeyEvent.VK_UP), DOWN(Sound.FUNNYSLIP, KeyEvent.VK_UP), RIGHT(Sound.METALCLANG, KeyEvent.VK_UP);
    private final int keyCode;
    private final Sound sound;


    KeySound(Sound sound, int keyCode) {
        this.sound = sound;
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public Sound getSound() {
        return sound;
    }
}
