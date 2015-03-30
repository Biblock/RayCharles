package engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import t2s.SIVOXDevint;
import devintAPI.Preferences;

public class Game implements IGame {
	private static final String pathFolder = "../ressources/sons/";
	private List<Integer> soundSequence;
	private int cursor;
	private List<Sound> usedSounds;
	private SIVOXDevint voix;

	public Game() {
		soundSequence = new ArrayList<Integer>();
		usedSounds = new ArrayList<Sound>();
		usedSounds.add(Sound.BOING);
		usedSounds.add(Sound.FUNNYSLIP);
		usedSounds.add(Sound.METALCLANG);

		voix = new SIVOXDevint();
		voix = Preferences.getData().getVoice();
		System.out.println("HEYYYYYYY");
		System.out.println("DONE");
		
	}

	@Override
	public boolean isCorrect(int Cursor) {
		// TODO Auto-generated method stub
		return false;
	}

	public void initialiazeCursor() {
		cursor = 0;
	}

	public void incrementCursor() {
		cursor++;
	}

	@Override
	public String randomSoundString(String pathFolder) {
		return null;
	}

	public void playAllSounds() {
		for (Sound i : usedSounds) {
			voix.playWav(i.getUrl(), true);

			System.out.println(i.getUrl());
		}
	}

	public void generateSequence(int i) {
		addRandomSound(i);
		playAllSounds();
	}

	private void addRandomSound(int i) {
		for (int j = 0; j < i; ++j) {
			Random rand = new Random();
			int randomNum = rand.nextInt(usedSounds.size());
			soundSequence.add(new Integer(randomNum));
		}
	}

	private void addRandomSound() {
		addRandomSound(1);
	}

	@Override
	public void LoadSounds(String pathFolder, ArrayList<String> Sounds) {

	}
	
	

}
