package view;

import java.awt.event.KeyEvent;

import engine.MemoryGame;
import engine.Sound;
import jeu.FenetreSimple;
import jeu.FichierScore;
import devintAPI.MenuAbstrait;

public class MainMenu extends MenuAbstrait {

	public MainMenu(String title) {
		super(title);
	}

	@Override
	protected String[] nomOptions() {
		String[] noms = {"Jouer au MEMORY", "Jouer au MOSQUITO", "Quitter"};
		return noms;	
	}

	@Override
	protected void lancerOption(int i) {
		switch (i){  
			case 0 :
				new DifficultyView("Choix de la difficulté", 1);
				break;
			case 1 : 
				new DifficultyView("Choix de la difficulté", 2);
				break;
			case 2 : System.exit(0);
			default: System.err.println("action non définie");
		}
	}

	@Override
	protected String wavAccueil() {
		return Sound.MESSAGEACCUEIL.getUrl();
	}

	@Override
	protected String wavRegleJeu() {
		return Sound.AIDEACCUEIL.getUrl();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
			System.exit(0);
		else
			super.keyPressed(e);
		
	}

}
