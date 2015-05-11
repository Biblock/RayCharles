package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.Timer;

import t2s.SIVOXDevint;
import devintAPI.FenetreAbstraite;
import engine.IGame;
import engine.Sound;

public class ViewMosquitoGame extends FenetreAbstraite implements ActionListener {

	private Map<Sound, Integer> usedSounds;
	private Timer timer;
	private Sound playedSound;
	private int spaceCpt;
	private int points;
	
	public ViewMosquitoGame(String title, int difficulty) {
		super(title);
		
		usedSounds = new HashMap<Sound, Integer>();
		usedSounds.put(Sound.BOING, 1);
		usedSounds.put(Sound.POUET, 2);
		if(difficulty == 2)
			usedSounds.put(Sound.METALCLANG, 3);
		
		spaceCpt = -1;
		points = 0;
		
		timer = new Timer(3000, new GameTimerListener());
	}

	@Override
	protected String wavAccueil() {
		return Sound.LANCEMENT.getUrl();
	}

	@Override
	protected String wavRegleJeu() {
		return Sound.AIDEMOSQUITO.getUrl();
	}

	@Override
	protected void init() {
		
	}

	@Override
	protected String wavAide() {
		return Sound.AIDEMOSQUITO.getUrl();
	}

	@Override
	public void changeColor() {
		
	}

	@Override
	public void changeSize() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}	

	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		int keyCode = e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_SPACE){
			if(spaceCpt == -1)
				runGame();
			else{
				spaceCpt++;
				System.out.println("Cpt : "+spaceCpt);
			}
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void runGame() {
		voix.playWav(Sound.COUNTDOWN321.getUrl(), true);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		timer.start();
		launchRound();
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
