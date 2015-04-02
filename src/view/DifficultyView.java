package view;

import devintAPI.MenuAbstrait;
import engine.MemoryGame;
import engine.MosquitoGame;

public class DifficultyView extends MenuAbstrait {

	private int difficulty;
	
	public DifficultyView(String title) {
		super(title);
		
		difficulty = -1;
	}
	
	@Override
	protected String[] nomOptions() {
		String[] noms = {"FACILE", "MOYEN", "DIFFICILE", "Retour"};
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
			case 2 :
				difficulty = 3;
				break;
			case 3 :
				break;
			default: System.err.println("action non définie");
		}
		
		this.dispose();
	}

	@Override
	protected String wavAccueil() {
		// TODO Message info difficulté
		return null;
	}

	@Override
	protected String wavRegleJeu() {
		// TODO Rien ?
		return null;
	}

	/**
	 * @return the difficulty
	 */
	public int getDifficulty() {
		return difficulty;
	}

}
