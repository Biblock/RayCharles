package view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.*;

import devintAPI.FenetreAbstraite;
import t2s.SIVOXDevint;
import engine.IGame;
import engine.KeySound;
import engine.MemoryGame;
import engine.Sound;

public class GameViewMemory extends FenetreAbstraite implements ActionListener {
	private MemoryGame game;
	private List<Sound> winsounds;
	private JLabel keyLeftGame;
	private JLabel keyRightGame;
	private JLabel keyDownGame;

	public GameViewMemory(String title, MemoryGame game) {
		super(title);
		this.game = game;
		
		winsounds = new ArrayList<Sound>();
		
		winsounds.add(Sound.WIN1);
		winsounds.add(Sound.WIN2);
		winsounds.add(Sound.WIN3);
		
		if(game.getDifficulty() == 1)
			keyLeftGame.setIcon(null);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void init() {
		
		this.setLayout(new GridLayout(1, 4));
		
		keyLeftGame = new JLabel();
		keyRightGame = new JLabel();
		keyDownGame = new JLabel();
		
		initImages();
		
		this.add(new JLabel("Ordinateur : "));
		this.add(keyLeftGame);
		this.add(keyDownGame);
		this.add(keyRightGame);
	}

	private void initImages(){
		keyLeftGame.setIcon((new ImageIcon("../ressources/images/keyboard/BlueLeft.png")));
		keyRightGame.setIcon((new ImageIcon("../ressources/images/keyboard/BlueRight.png")));
		keyDownGame.setIcon((new ImageIcon("../ressources/images/keyboard/BlueDown.png")));
		
		if(game != null && game.getDifficulty() == 1)
			keyLeftGame.setIcon(null);
	}
	
	@Override
	protected String wavAide() {
		return Sound.AIDEMEMORY.getUrl();
	}

	@Override
	public void changeColor() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeSize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String wavAccueil() {
		return Sound.LANCEMENT.getUrl();
	}

	@Override
	protected String wavRegleJeu() {
		return "../ressources/sons/aideF1.wav";
	}
	

	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		int keyCode = e.getKeyCode();
		
		KeySound toCheck = null;

		if (keyCode == KeyEvent.VK_UP) {
			playSequence();
		}

		if(keyCode == KeyEvent.VK_SPACE && game.getCptround() == 0){
			runGame();
		}else{
			toCheck = KeySound.getKeySound(keyCode);
		}
		
		if (toCheck != null && !game.getSoundSequence().isEmpty()) {
			voix.stop();
			voix.playWav(toCheck.getSound().getUrl());
			int state = game.checkKeyPressed(toCheck);
			if(state == 0){
				visualPressKey(toCheck, true);
				if(game.checkEndOfRound()){
					launchWinRound();
				}
			} else if (state == 1){
				visualPressKey(toCheck, false);
				voix.playWav(Sound.ESSAIE.getUrl());
			} else if (state == 2) {
				voix.playWav(Sound.FAIL.getUrl());
				endOfGame();
				
			}
		}
	}
	
	public void endOfGame() {
		int position = game.isBestScore();
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
			new AddScore(1, game.getDifficulty(), game.getScore());
		}
		dispose();
	}


	private void visualPressKey(KeySound toCheck, boolean b) {
		String color;
		if(b)
			color = "Green";
		else
			color = "Red";
		
		switch (toCheck) {
		case LEFT:
			keyLeftGame.setIcon((new ImageIcon("../ressources/images/keyboard/"+color+"Left.png")));
			
			break;
		case RIGHT:
			keyRightGame.setIcon((new ImageIcon("../ressources/images/keyboard/"+color+"Right.png")));
			
			break;
		case DOWN:
			keyDownGame.setIcon((new ImageIcon("../ressources/images/keyboard/"+color+"Down.png")));
			
			break;
		}
		
		if(game.getDifficulty() == 1)
			keyLeftGame.setIcon(null);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		initImages();
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
		
	public void runGame(){
		game.initGame();
		runRound();
	}

	public void runRound(){
		game.initRound();
		voix.playWav(Sound.COUNTDOWN321.getUrl());
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		playSequence();
	}
	
	public void launchWinRound(){
		Random rand = new Random();

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		int r = rand.nextInt(winsounds.size());
		voix.playWav(winsounds.get(r).getUrl());
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		runRound();
	}

	public void playSequence() {
		int count = 0;

		for (final KeySound ks : game.getSoundSequence()) {

			ActionListener visualPressKey = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					visualPressKey(ks, true);
					voix.playWav(ks.getSound().getUrl());
				}
			};

			ActionListener initImages = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					initImages();
				}
			};
			Timer t1 = new Timer(1000 * count, visualPressKey);
			t1.setRepeats(false);
			t1.start();
			Timer t2 = new Timer(1000 * count + 250, initImages);
			t2.setRepeats(false);
			t2.start();
			++count;
		}
	}
}
