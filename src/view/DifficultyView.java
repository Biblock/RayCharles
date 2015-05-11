package view;

import devintAPI.MenuAbstrait;
import engine.MemoryGame;
import engine.MosquitoGame;
import engine.Sound;

public class DifficultyView extends MenuAbstrait {

	private int difficulty;
	private int idGame;
	
	public DifficultyView(String title, int idGame) {
		super(title);
		this.idGame = idGame;
		difficulty = 1;
	}
	
	@Override
	protected String[] nomOptions() {
		String[] noms = {"2 SONS", "3 SONS"};
		return noms;
	}

	@Override
	protected void lancerOption(int i) {
		switch (i){  
			case 0 : 
				difficulty = 1;
				break;
			case 1 :
				difficulty = 2;
				break;
			default: System.err.println("action non définie");
		}
		
		switch (idGame) {
			case 1:
				new GameViewMemory("WELCOME TO THE MEMORY", new MemoryGame(difficulty));
				break;
			case 2:
				new ViewMosquitoGame("WELCOME TO THE MOSQUITO", new MosquitoGame());
				break;
		}
		
		this.dispose();
	}

	@Override
	protected String wavAccueil() {
		// TODO SON TEMPORAIRE POUR EVITER LES EXCEPTIONS
		return Sound.WIN2.getUrl();
	}

	@Override
	protected String wavRegleJeu() {
		// TODO SON TEMPORAIRE POUR EVITER LES EXCEPTIONS
		return Sound.WIN1.getUrl();
	}

}
