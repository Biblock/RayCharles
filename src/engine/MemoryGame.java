package engine;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import t2s.SIVOXDevint;
import view.ViewMosquitoGame;
import devintAPI.Preferences;

public class MemoryGame implements IGame {
	private static final String pathFolder = "../ressources/sons/";
	private List<KeySound> soundSequence;
	private int cursor;
	private int difficulty;
	private int nbSounds;
	private int cptround;
	private List<Sound> usedSounds;
	private List<Sound> winsounds;
	private SIVOXDevint voix;

	public MemoryGame(int difficulty) {

		this.difficulty = difficulty;
		
		soundSequence = new ArrayList<KeySound>();
		winsounds = new ArrayList<Sound>();
		usedSounds = new ArrayList<Sound>();
		usedSounds.add(Sound.BOING);
		usedSounds.add(Sound.POUET);
		usedSounds.add(Sound.METALCLANG);
		
		winsounds.add(Sound.WIN1);
		winsounds.add(Sound.WIN2);
		winsounds.add(Sound.WIN3);

		voix = new SIVOXDevint();
		voix = Preferences.getData().getVoice();
		cursor = 0;
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
	
	@Override
	public void runGame() {
		nbSounds = difficulty + 1;
		cptround = 1;
		voix.playWav("../ressources/sons/countdown321.wav", true);
		launchRound(difficulty);
	}

	@Override
	public void endGame(boolean win){
		Random rand = new Random();
		
		if(win){
			
			if(cptround % 2 == 0 && nbSounds < ((difficulty * difficulty) + 5))
				difficulty++;
			cptround++;
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			int r = rand.nextInt(winsounds.size());
			voix.playWav(winsounds.get(r).getUrl(), true);
			voix.playWav(Sound.COUNTDOWN321.getUrl());


			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// well done sound
			soundSequence.clear();
			launchRound(difficulty);
		}
	}
	
	@Override
	public void checkKeyCode(int keyCode) {
		KeySound toCheck = null;

		if (keyCode == KeyEvent.VK_UP) {
			playSequence();
		}

		if(keyCode == KeyEvent.VK_SPACE && cptround == 0){
			runGame();
		}else{
			toCheck = KeySound.getKeySound(keyCode);
		}
		
		if (toCheck != null && !soundSequence.isEmpty()) {
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
