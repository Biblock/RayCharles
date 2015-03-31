package engine;

import java.awt.event.KeyEvent;
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
	public void runGame() {
		launchRound(3);
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
	
	public void endGame(boolean win){
		if(win){
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// well done sound
			launchRound(3);
		}
	}

	public void playSequence() {
		for (KeySound ks : soundSequence) {
			voix.playWav(ks.getSound().getUrl(), true);
			System.out.println(ks.getSound().getUrl());
		}
	}

	public void launchRound(int difficulty){
		cursor = 0;
		generateSequence(difficulty);
		playSequence();
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

	public void checkKeyCode(int keyCode) {
		KeySound toCheck = null;
		
		if(keyCode == KeyEvent.VK_SPACE){
			runGame();
		}else{
			toCheck = KeySound.getKeySound(keyCode);
		}
		
		if (toCheck != null) {
			if (toCheck.getSound().equals(soundSequence.get(cursor).getSound())) {
				voix.stop();
				voix.playWav(toCheck.getSound().getUrl());
				cursor++;
				if(cursor == soundSequence.size())
					endGame(true);
			} else {
				voix.playWav(Sound.FAIL.getUrl());
				cursor = 0;
			}
		}
	}
}
