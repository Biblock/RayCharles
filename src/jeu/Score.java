package jeu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public final class Score {
	private final static String FILE_NAME = "score.txt";

	public static HashMap<String, Integer> getScores(int numGame, int difficulte) {
		HashMap<String, Integer> scores = new HashMap<String, Integer>();

		String chemin = ".." + File.separator + "ressources" + File.separator
				+ "score"+ numGame + "" + difficulte + ".txt" ;
		// on lit le fichier de score et on fait dire chaque ligne par la
		// synthèse vocale
		try {
			BufferedReader l = new BufferedReader(new FileReader(chemin));
			String line = l.readLine();
			while (line != null) {
				String[] tab = line.split(";");
				scores.put(
						tab[0],
						Integer.valueOf(tab[1]));
				line = l.readLine();
			}
			l.close();
		} catch (IOException e) {
			System.out.println("pb lecture fichier");
			e.printStackTrace();
		}

		return scores;
	}
	
	public static void addScore(int numGame, int difficulte, String name, Integer score){
		HashMap<String, Integer> scores = getScores(numGame, difficulte);
		Set<String> names = scores.keySet();
		int i = 0;
		String content = "";
		String low = "";
		for (String str : names) {
			if (i == 0) {
				low = str;
			} else if (scores.get(str) < scores.get(low)) {
				content += low+';'+scores.get(low)+'\n';
				low = str;
			} else {
				content += str+';'+scores.get(str)+'\n';
			}
			i++;
		}
		if (scores.get(low) < score) {
			content+= name +';'+score+'\n';
		} else {
			content += low+';'+scores.get(low)+'\n';
		}
		
		String chemin = ".." + File.separator + "ressources" + File.separator
				+ "score"+ numGame + "" + difficulte + ".txt" ;
		try {
			File file = new File(chemin);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(content);
			bw.close();
		} catch (IOException e) {
			System.out.println("pb lecture fichier");
			e.printStackTrace();
		}
	}
	
}
