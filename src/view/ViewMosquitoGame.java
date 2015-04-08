package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import t2s.SIVOXDevint;
import devintAPI.FenetreAbstraite;
import engine.IGame;
import engine.Sound;

public class ViewMosquitoGame extends FenetreAbstraite implements ActionListener {

	private IGame game;
	private SIVOXDevint voix;
	
	public ViewMosquitoGame(String title, IGame game) {
		super(title);
		this.game = game;
		this.addKeyListener(new RecognizedKeyListener());
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
		return Sound.AIDEMEMORY.getUrl();
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
