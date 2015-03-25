package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import devintAPI.FenetreAbstraite;

public class GameView extends FenetreAbstraite implements ActionListener {

	public GameView(String title) {
		super(title);
		// TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeSize() {
		// TODO Auto-generated method stub
		
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
