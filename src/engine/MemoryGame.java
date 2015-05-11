package engine;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import t2s.SIVOXDevint;
import view.ViewMosquitoGame;
import devintAPI.Preferences;

public class MemoryGame implements IGame {
	private List<KeySound> soundSequence;
	private int cursor;
	private int stage;
	private int difficulty;
	private int nbSounds;
	private int cptround;
	private List<KeySound> usedKeys;
	private int lifeCpt;
	private final int MAXLIFE = 3;
	
	public MemoryGame(int difficulty) {

		this.difficulty = difficulty;
		
		soundSequence = new ArrayList<KeySound>();
		
		usedKeys = new ArrayList<KeySound>();
		
		if(this.difficulty == 2)
			usedKeys.add(KeySound.LEFT);
		
		usedKeys.add(KeySound.DOWN);
		usedKeys.add(KeySound.RIGHT);
	}
	
	public int getLifeCpt() {
		return lifeCpt;
	}
	
	public int decreaseLifeCpt() {
		if (lifeCpt <= 0) {
			lifeCpt = 0;
		} else {
			lifeCpt--;
		}
		return lifeCpt;
	}
	public void initRound(){
		cursor = 0;
		soundSequence.clear();
		generateSequence(stage);
	}
	
	public void generateSequence(int i) {
		Random rand = new Random();
		while ((i--) > 0) {
			
			int r = rand.nextInt(usedKeys.size());
			KeySound ks = usedKeys.get(r);
			soundSequence.add(ks);
		}
	}
	
	public void initGame(){
		stage = 1;
		cursor = 0;
		nbSounds = stage + 1;
		cptround = 1;
		lifeCpt = MAXLIFE;
	}
	
	public boolean checkEndOfRound(){
		if(cursor == soundSequence.size()){
			if(cptround % 2 == 0 && nbSounds < ((stage * stage) + 5))
				stage++;
			cptround++;
			
			return true;
		
		}
		return false;
	}
	
	public int checkKeyPressed(KeySound toCheck){
		if (toCheck.getSound().equals(soundSequence.get(cursor).getSound())) {
			cursor++;
			return 0;
		} else {
			cursor = 0;
			decreaseLifeCpt();
			if (getLifeCpt() > 0) {
				return 1;
			} else {
				return 2;
			}
					
		}
	}
	
	public List<KeySound> getSoundSequence(){
		return this.soundSequence;
	}
	
	public int getCptround(){
		return this.cptround;
	}
	
	public int getDifficulty(){
		return this.difficulty;
	}
}
