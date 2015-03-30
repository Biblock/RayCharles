package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import t2s.SIVOXDevint;
import devintAPI.FenetreAbstraite;
import engine.Game;
import engine.IGame;

public class GameView extends FenetreAbstraite implements ActionListener {

	private Game game;
	private SIVOXDevint voix;
	
	public GameView(String title) {
		super(title);
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

}
