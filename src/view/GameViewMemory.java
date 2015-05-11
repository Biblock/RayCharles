package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	private JLabel keyLeftUser;
	private JLabel keyRightUser;
	private JLabel keyDownUser;

	public GameViewMemory(String title, MemoryGame game) {
		super(title);
		this.game = game;
		
		winsounds = new ArrayList<Sound>();
		
		winsounds.add(Sound.WIN1);
		winsounds.add(Sound.WIN2);
		winsounds.add(Sound.WIN3);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void init() {
		
		this.setLayout(new GridLayout(2, 4));
		
		keyLeftGame = new JLabel();
		keyRightGame = new JLabel();
		keyDownGame = new JLabel();
		keyLeftUser = new JLabel();
		keyRightUser = new JLabel();
		keyDownUser = new JLabel();
		
		initImages();
		
		this.add(new JLabel("Ordinateur : "));
		this.add(keyLeftGame);
		this.add(keyDownGame);
		this.add(keyRightGame);
		
		this.add(new JLabel("Joueur : "));
		this.add(keyLeftUser);
		this.add(keyDownUser);
		this.add(keyRightUser);
	}

	private void initImages(){
		keyLeftGame.setIcon((new ImageIcon("../ressources/images/keyboard/BlueLeft.png")));
		keyRightGame.setIcon((new ImageIcon("../ressources/images/keyboard/BlueRight.png")));
		keyDownGame.setIcon((new ImageIcon("../ressources/images/keyboard/BlueDown.png")));
		keyLeftUser.setIcon((new ImageIcon("../ressources/images/keyboard/BlueLeft.png")));
		keyRightUser.setIcon((new ImageIcon("../ressources/images/keyboard/BlueRight.png")));
		keyDownUser.setIcon((new ImageIcon("../ressources/images/keyboard/BlueDown.png")));
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
			if(game.checkKeyPressed(toCheck)){
				visualPressKey(toCheck, 1, true);

				if(game.checkEndOfRound()){
					launchWinRound();
				}
			}else{
				visualPressKey(toCheck, 1, false);
				voix.playWav(Sound.FAIL.getUrl());
			}
		}
	}


	private void visualPressKey(KeySound toCheck, int i, boolean b) {
		String color;
		if(b)
			color = "Green";
		else
			color = "Red";
		
		switch (toCheck) {
		case LEFT:
			if(i == 0){
				keyLeftGame.setIcon((new ImageIcon("../ressources/images/keyboard/"+color+"Left.png")));
			}
			else if (i == 1){
				keyLeftUser.setIcon((new ImageIcon("../ressources/images/keyboard/"+color+"Left.png")));
			}
			break;
		case RIGHT:
			if(i == 0){
				keyRightGame.setIcon((new ImageIcon("../ressources/images/keyboard/"+color+"Right.png")));
			}
			else if (i == 1){
				keyRightUser.setIcon((new ImageIcon("../ressources/images/keyboard/"+color+"Right.png")));
			}
			break;
		case DOWN:
			if(i == 0){
				keyDownGame.setIcon((new ImageIcon("../ressources/images/keyboard/"+color+"Down.png")));
			}
			else if (i == 1){
				keyDownUser.setIcon((new ImageIcon("../ressources/images/keyboard/"+color+"Down.png")));
			}
			break;
		}
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
		voix.playWav(Sound.COUNTDOWN321.getUrl(), true);
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
		voix.playWav(winsounds.get(r).getUrl(), true);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		runRound();
	}
	
	public void playSequence() {
		for (KeySound ks : game.getSoundSequence()) {
			visualPressKey(ks, 0, true);
			voix.playWav(ks.getSound().getUrl(), true);
			//initImages();
			System.out.println(ks.getSound().getUrl());
		}
	}
}
