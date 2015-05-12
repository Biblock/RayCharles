package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

import jeu.Score;


public class MemoryGame implements IGame {
	private List<KeySound> soundSequence;
	private int cursor;
	private int stage;
	private int difficulty;
	private int nbSounds;
	private int cptround;
	private List<KeySound> usedKeys;
	private int lifeCpt;
	private int score;
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
	
	public int getScore() {
		return score;
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
			score++;
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
	
	public int isBestScore() {
		HashMap<String, Integer> allScores = Score.getScores();
		Set<String> set = allScores.keySet();
		int cpt = 1;

		for (String str : set) {
			if (allScores.get(str) < getScore()) {
				return cpt;
			}
			cpt++;
		}
		return 0;
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
