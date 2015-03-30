package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import t2s.SIVOXDevint;
import devintAPI.FenetreAbstraite;
import engine.Game;
import engine.IGame;

public class GameView extends FenetreAbstraite implements ActionListener {

	private Game game;
	private SIVOXDevint voix;
	
	public GameView(String title) {
		super(title);
		this.addKeyListener(new RecognizedKeyListener());
	}

	@Override
	protected void init() {
		game = new Game();
		game.generateSequence(3);
		
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
		return "../ressources/sons/accueil.wav";
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
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent key) {
			if(key.getKeyCode() == key.VK_LEFT)
			System.out.println("Youpi !");
			
		}
		
	}

}
