package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import devintAPI.FenetreAbstraite;
import t2s.SIVOXDevint;
import view.GameView.RecognizedKeyListener;
import engine.IGame;
import engine.Sound;

public class GameViewMemory extends FenetreAbstraite implements ActionListener {
	private IGame game;
	private SIVOXDevint voix;

	public GameViewMemory(String title, IGame game, SIVOXDevint voix) {
		super(title);
		this.addKeyListener(new RecognizedKeyListener());
		this.game = game;
		this.voix = voix;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String wavAide() {
		return "../ressources/sons/aide.wav";
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
		return Sound.MESSAGEACCUEIL.getUrl();
	}

	@Override
	protected String wavRegleJeu() {
		return "../ressources/sons/aideF1.wav";
	}
	
	class RecognizedKeyListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			game.checkKeyCode(e.getKeyCode());
	
		}


		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
