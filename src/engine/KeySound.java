package engine;


import engine.Sound;

import java.awt.event.KeyEvent;

public enum KeySound {
    LEFT(Sound.BOING, KeyEvent.VK_LEFT), DOWN(Sound.FUNNYSLIP, KeyEvent.VK_DOWN), RIGHT(Sound.METALCLANG, KeyEvent.VK_RIGHT);
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

    public static KeySound getKeySound(Sound sound) {
        for (KeySound ks : KeySound.values()) {
            if (ks.sound.equals(sound)) {
                return ks;
            }
        }
        return null;
    }
    
    public static KeySound getKeySound(int keyCode) {
        for (KeySound ks : KeySound.values()) {
            if (ks.keyCode == keyCode) {
                return ks;
            }
        }
        return null;
    }
    
    public static int getNbSounds(){
    	return KeySound.values().length;
    }
}
