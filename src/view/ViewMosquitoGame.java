package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import t2s.SIVOXDevint;
import devintAPI.FenetreAbstraite;
import engine.IGame;
import engine.MosquitoGame;
import engine.Sound;

public class ViewMosquitoGame extends FenetreAbstraite implements ActionListener {

	private MosquitoGame game;
	private SIVOXDevint voix;
	
	public ViewMosquitoGame(String title, MosquitoGame game) {
		super(title);
		this.game = game;
	}

	@Override
	protected String wavAccueil() {
		return Sound.LANCEMENT.getUrl();
		//return game.getFirstMessage();
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
