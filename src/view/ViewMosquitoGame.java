package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import jeu.Score;
import t2s.SIVOXDevint;
import devintAPI.FenetreAbstraite;
import engine.IGame;
import engine.Sound;

public class ViewMosquitoGame extends FenetreAbstraite implements ActionListener {

	private JLabel viewCpt;
	private JLabel soundName;
	
	private Map<Sound, Integer> usedSounds;
	private Timer timer;
	private Sound playedSound;
	private int spaceCpt;
	private int points;
	private int difficulty;
	
	public ViewMosquitoGame(String title, int difficulty) {
		super(title);
		this.difficulty = difficulty;
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
		this.setLayout(new GridLayout(2, 1));
		
		Font font = new Font("Georgia", Font.BOLD, 40);
		
		viewCpt = new JLabel("0", SwingConstants.CENTER);
		viewCpt.setFont(font);
		soundName = new JLabel("Son joué : ", SwingConstants.CENTER);
		soundName.setFont(font);
		
		this.add(viewCpt);
		this.add(soundName);
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
				viewCpt.setText(Integer.toString(spaceCpt));
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
		viewCpt.setText(Integer.toString(spaceCpt));
		int r = rand.nextInt(usedSounds.size());
		Object val[] = usedSounds.keySet().toArray();
		playedSound = (Sound)val[r];
		soundName.setText("Son joué : "+playedSound.name());
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
			int position = isBestScore();
			System.out.println(position);
			if (position > 0) {
				String strPos = "";
				switch (position) {
				case 1:
					strPos = "premier";
					break;
				case 2:
					strPos = "deuxième";
					break;
				case 3: 
					strPos = "troisième";
					break;
				case 4:
					strPos = "quatrième";
					break;
				case 5: 
					strPos = "cinquième";
					break;
				}
				System.out.println(strPos);
				voix.playText("Vous avez battu un des meilleurs scores !");
				voix.playText("Vous vous classez actuellement " + strPos);
				voix.playText("Comment vous appelez-vous ?");
				new AddScore(2, difficulty, points);
			}
			dispose();
		
		}
	}
	
	public int isBestScore() {
		HashMap<String, Integer> allScores = Score.getScores(1, difficulty);
		Set<String> set = allScores.keySet();
		int cpt = 1;

		for (String str : set) {
			if (allScores.get(str) < points) {
				return cpt;
			}
			cpt++;
		}
		return 0;
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
