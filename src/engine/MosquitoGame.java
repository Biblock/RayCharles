package engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.Timer;

import devintAPI.Preferences;
import t2s.SIVOXDevint;

public class MosquitoGame implements IGame {

	private Map<Sound, Integer> usedSounds;
	private SIVOXDevint voix;
	private Sound playedSound;
	private int spaceCpt;
	private Timer timer;
	private int points;
	
	public MosquitoGame() {
		points = 0;
		usedSounds = new HashMap<Sound, Integer>();
		
		spaceCpt = -1;
		
		usedSounds.put(Sound.BOING, 1);
		usedSounds.put(Sound.FUNNYSLIP, 2);
		usedSounds.put(Sound.METALCLANG, 3);
		
		voix = new SIVOXDevint();
		voix = Preferences.getData().getVoice();
		
		timer = new Timer(3000, new GameTimerListener());
	}
	
	@Override
	public void runGame() {
		voix.playWav(Sound.COUNTDOWN321.getUrl(), true);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("test");
		timer.start();
		launchRound();
	}
	
	@Override
	public void endGame(boolean win) {
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(win){
			
			points += 10;
			voix.playWav(Sound.WIN2.getUrl());
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			launchRound();
		}else{
			voix.playWav(Sound.FAIL.getUrl(), true);
			timer.stop();
			voix.playShortText("Bien joué ! Votre score est de "+points + " points.");
		}
	}
	
	@Override
	public void checkKeyCode(int keyCode) {		
		if(keyCode == KeyEvent.VK_SPACE){
			if(spaceCpt == -1)
				runGame();
			else{
				spaceCpt++;
				System.out.println(spaceCpt);
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
		timer.restart();
	}
	
	class GameTimerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(spaceCpt != usedSounds.get(playedSound))
				endGame(false);
			else
				endGame(true);
		}
	}
}
