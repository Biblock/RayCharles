package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import t2s.SIVOXDevint;
import view.GameView;
import devintAPI.Preferences;

public class Game implements IGame {
	private static final String pathFolder = "../ressources/sons/";
	private List<KeySound> soundSequence;
	private int cursor;
	private List<Sound> usedSounds;
	private SIVOXDevint voix;

	public Game() {

		soundSequence = new ArrayList<KeySound>();
		usedSounds = new ArrayList<Sound>();
		usedSounds.add(Sound.BOING);
		usedSounds.add(Sound.FUNNYSLIP);
		usedSounds.add(Sound.METALCLANG);

		voix = new SIVOXDevint();
		voix = Preferences.getData().getVoice();
		cursor = 0;
	}

	@Override
	public boolean isCorrect(int Cursor) {
		return false;
	}

	@Override
	public String randomSoundString(String pathFolder) {
		return null;
	}

	@Override
	public void LoadSounds(String pathFolder, ArrayList<String> Sounds) {

	}

	public void initialiazeCursor() {
		cursor = 0;
	}

	public void incrementCursor() {
		cursor++;
	}

	public void playAllSounds() {
		for (Sound i : usedSounds) {
			voix.playWav(i.getUrl(), true);

			System.out.println(i.getUrl());
		}
	}

	public void generateSequence(int i) {
		Random rand = new Random();
		while ((i--) > 0) {
			int r = rand.nextInt(KeySound.getNbSounds());
			System.out.println(r);
			KeySound ks = KeySound.values()[r];
			soundSequence.add(ks);
			System.out.println(ks);
		}
	}

//	private void addRandomSound(int i) {
//		Random rand = new Random();
//		for (int j = 0; j < i; ++j) {
//			int randomNum = rand.nextInt(usedSounds.size());
//			soundSequence.add(new Integer(randomNum));
//		}
//	}

	public void checkKeyCode(int keyCode) {
		KeySound toCheck = KeySound.getKeySound(keyCode);
		if (toCheck != null) {
			if (toCheck.getSound().equals(soundSequence.get(cursor).getSound())) {
				voix.stop();
				voix.playWav(toCheck.getSound().getUrl());
				cursor++;
			} else {
				voix.playWav(Sound.FAIL.getUrl());
				cursor = 0;
			}
		}
	}
}
