package view;

import engine.MemoryGame;
import engine.MosquitoGame;
import engine.Sound;
import jeu.FenetreSimple;
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
//				DifficultyView diffView = new DifficultyView("Choix de la difficulté");
//				
//				int difficulty = diffView.getDifficulty();
//
//				if(difficulty != -1)
				int difficulty = 1;
					new GameView("WELCOME TO THE MEMORY", new MemoryGame(difficulty));
				break;
			case 1 : new GameView("WELCOME TO THE MOSQUITO", new MosquitoGame());break;
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

}
