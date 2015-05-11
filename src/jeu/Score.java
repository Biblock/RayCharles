package jeu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public final class Score {
	private final static String FILE_NAME = "score.txt";

	public static HashMap<String, Integer> getScores(int numGame, int difficulte) {
		HashMap<String, Integer> scores = new HashMap<String, Integer>();

		String chemin = ".." + File.separator + "ressources" + File.separator
				+ "score.txt";
		// on lit le fichier de score et on fait dire chaque ligne par la
		// synthèse vocale
		try {
			BufferedReader l = new BufferedReader(new FileReader(chemin));
			String line = l.readLine();
			while (line != null) {
				String[] tab = line.split(";");
				scores.put(
						tab[(numGame - 1) * 4 + 2 * (difficulte - 1)],
						Integer.valueOf(tab[(numGame - 1) * 4 + 2
								* (difficulte - 1) + 1]));
				line = l.readLine();
			}
			l.close();
		} catch (IOException e) {
			System.out.println("pb lecture fichier");
			e.printStackTrace();
		}

		return scores;
	}
	
	public static void addScore(){
		
	}
}
