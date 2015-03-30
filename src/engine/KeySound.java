package engine;

import engine.Sound;
import javafx.scene.input.KeyCode;

public enum KeySound {
    LEFT(Sound.BOING, KeyCode.LEFT), DOWN(Sound.FUNNYSLIP, KeyCode.DOWN), RIGHT(Sound.METALCLANG, KeyCode.RIGHT);
    private final KeyCode keyCode;
    private final Sound sound;


    KeySound(Sound sound, KeyCode keyCode) {
        this.sound = sound;
        this.keyCode = keyCode;
    }

    public KeyCode getKeyCode() {
        return keyCode;
    }

    public Sound getSound() {
        return sound;
    }
}
