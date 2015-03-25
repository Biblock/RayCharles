package view;

import jeu.FenetreSimple;
import devintAPI.MenuAbstrait;

public class MainMenu extends MenuAbstrait {

	public MainMenu(String title) {
		super(title);
	}

	@Override
	protected String[] nomOptions() {
		String[] noms = {"Jouer","Quitter"};
		return noms;	
	}

	@Override
	protected void lancerOption(int i) {
		switch (i){  
			case 0 : new GameView("WELCOME TO THE GAME");break;
			case 1 : System.exit(0);
			default: System.err.println("action non définie");
		}
	}

	@Override
	protected String wavAccueil() {
		return "../ressources/sons/accueil.wav";
	}

	@Override
	protected String wavRegleJeu() {
		return "../ressources/sons/accueil.wav";
	}

}
