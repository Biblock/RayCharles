package view;

import devintAPI.MenuAbstrait;
import engine.MemoryGame;
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
		String[] noms = {"2 SONS", "3 SONS", "SCORES"};
		return noms;
	}

	@Override
	protected void lancerOption(int i) {
		boolean scoreIsSelected = false;
		switch (i){  
			case 0 : 
				difficulty = 1;
				break;
			case 1 :
				difficulty = 2;
				break;
			case 2 :
				scoreIsSelected = true;
				break;
			default: System.err.println("action non définie");
		}

		switch (idGame) {
			case 1:
				if (scoreIsSelected) 
					new ScoreView("Score", idGame);
				else
					new GameViewMemory("WELCOME TO THE MEMORY", new MemoryGame(difficulty));
				break;
			case 2:
				
				if (scoreIsSelected) 
					new ScoreView("Score", idGame);
				else
					new ViewMosquitoGame("WELCOME TO THE MOSQUITO", difficulty);
				break;
		}
		scoreIsSelected = false;
		
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
