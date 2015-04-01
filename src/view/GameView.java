package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import t2s.SIVOXDevint;
import devintAPI.FenetreAbstraite;
import engine.IGame;
import engine.MemoryGame;
import engine.Sound;

public class GameView extends FenetreAbstraite implements ActionListener {

	private IGame game;
	private SIVOXDevint voix;
	
	public GameView(String title, IGame game) {
		super(title);
		this.addKeyListener(new RecognizedKeyListener());
		this.game = game;
	}

	@Override
	protected void init() {
		
	}

	@Override
	protected String wavAide() {
		return "../ressources/sons/aide.wav";
	}

	@Override
	public void changeColor() {
		
	}

	@Override
	public void changeSize() {
		
	}

	@Override
	protected String wavAccueil() {
		return Sound.MESSAGEACCUEIL.getUrl();
	}

	@Override
	protected String wavRegleJeu() {
		return "../ressources/sons/aideF1.wav";
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public void keyPressed(KeyEvent e) {
    	super.keyPressed(e);
    	if (e.getKeyCode()==KeyEvent.VK_F5){
    	   	voix.playText("Vous venez d'appuyer sur EFFE 5");
    	}
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
