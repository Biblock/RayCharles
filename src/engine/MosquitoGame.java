package engine;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import devintAPI.Preferences;
import t2s.SIVOXDevint;

public class MosquitoGame implements IGame {

	private Map<Sound, Integer> usedSounds;
	private SIVOXDevint voix;
	private Sound playedSound;
	private int spaceCpt;
	private boolean gameON;
	
	public MosquitoGame() {
		usedSounds = new HashMap<Sound, Integer>();
		
		spaceCpt = -1;
		gameON = false;
		
		usedSounds.put(Sound.BOING, 1);
		usedSounds.put(Sound.FUNNYSLIP, 2);
		usedSounds.put(Sound.METALCLANG, 3);
		
		voix = new SIVOXDevint();
		voix = Preferences.getData().getVoice();
	}
	
	@Override
	public void runGame() {
		voix.playWav("../ressources/sons/countdown321.wav", true);
		launchRound();
	}
	
	@Override
	public void endGame(boolean win) {
		gameON = false;
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		voix.playWav(Sound.WIN2.getUrl());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		launchRound();
	}
	
	@Override
	public void checkKeyCode(int keyCode) {		
		if(keyCode == KeyEvent.VK_SPACE){
			if(spaceCpt == -1)
				runGame();
			else if(gameON){
				spaceCpt++;
				System.out.println(spaceCpt);
				System.out.println(gameON);
				if(spaceCpt == usedSounds.get(playedSound))
					endGame(true);
			}
		}
	}
	
	public void launchRound(){
		Random rand = new Random();
		
		spaceCpt = 0;
		int r = rand.nextInt(usedSounds.size());
		Object val[] = usedSounds.keySet().toArray();
		playedSound = (Sound)val[r];
		voix.playWav(playedSound.getUrl());
		gameON = true;
	}
}
