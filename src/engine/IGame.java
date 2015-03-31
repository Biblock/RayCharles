package engine;

import java.util.ArrayList;

public interface IGame {
	public void runGame();
	public boolean isCorrect(int Cursor);
	public String randomSoundString(String pathFolder);
	public void LoadSounds(String pathFolder, ArrayList<String> Sounds);	
}