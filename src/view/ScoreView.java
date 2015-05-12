/*
* Copyright 2007-2011, HÃ©lÃ¨ne Collavizza, Jean-Paul Stromboni
* 
* This file is part of project 'Modele_de_Jeu'
* 
* 'Modele_de_Jeu' is free software: you can redistribute it and/or modify
* it under the terms of the GNU Lesser General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
* 
* 'Modele_de_Jeu'is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU Lesser General Public License for more details.
* 
* You should have received a copy of the GNU Lesser General Public License
* along with 'Modele_de_Jeu'. If not, see <http://www.gnu.org/licenses/>.
*/
package view;

import javax.swing.*;
import javax.swing.border.LineBorder;

import devintAPI.FenetreAbstraite;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import jeu.Score;

/** Cette classe est un exemple d'utilisation d'un fichier
 * 
 * @author helene
 * @author Jean-Paul, mars 2011
 */

public class ScoreView extends FenetreAbstraite implements ActionListener{

	// le bouton pour lire un fichier
	private JButton lire;
	private int idGame;

	// appel au constructeur de la classe mère
    public ScoreView(String title, int numGame) {
    	super(title);
    	idGame = numGame;
    	init2();
     }

    protected void init(){
    }
    
    // définition de la méthode abstraite "init()"
    // initialise le frame 
    private void init2() {
    	setLayout(new BorderLayout());
 
    	String text = "";
    	System.out.println(idGame);
     	HashMap<String, Integer> scores1 = Score.getScores(idGame, 1);
     	HashMap<String, Integer> scores2 = Score.getScores(idGame, 2);

     	Set<String> joueurs = scores1.keySet();
     	text += "2 SONS :\n";
		for (String joueur : joueurs){
     		text += joueur + " -> " + scores1.get(joueur) + "\n";
     	}
		
     	joueurs = scores2.keySet();
     	text += "\n3 SONS :\n";
		for (String joueur : joueurs){
     		text += joueur + " -> " + scores2.get(joueur) + "\n";
     	}   

     	JTextArea lb1 = new JTextArea (text); 
    	lb1.setLineWrap(true);
    	lb1.setEditable(false);
    	lb1.setFont(new Font("Georgia",1,30));
    	// on place le premier composant en bas
    	this.add(lb1,BorderLayout.CENTER);
     	
      	// bouton pour lancer la lecture dans le fichier
    	lire = new JButton();
    	lire.setText("Cliquer pour lire le fichier");
    	lire.setBackground(new Color(55,34,255));
    	lire.setForeground(new Color(250,250,210));
    	lire.setBorder(new LineBorder(Color.BLACK,10));
    	lire.setFont(new Font("Georgia",1,40));
     	// c'est l'objet Jeu lui-même qui réagit au clic souris
    	lire.addActionListener(this);
    	// on met le bouton en haut
     	this.add(lire,BorderLayout.SOUTH);
     	
   }
    
    // lire la question si clic sur le bouton 
    public void actionPerformed(ActionEvent ae){
       	// toujours stopper la voix avant de parler
    	voix.stop();
    	// on récupère la source de l'évènement
     	Object source = ae.getSource();
     	
     	HashMap<String, Integer> scores = Score.getScores(idGame, 1);
     	
     	Set<String> joueurs = scores.keySet();
     	
     	voix.playText("Avec deux touches.");
     	for (String joueur : joueurs){
     		voix.playText(joueur + " : " + scores.get(joueur));
     		try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
     	}    	
     	scores = Score.getScores(idGame, 2);
     	joueurs = scores.keySet();
     	
     	voix.playText("Avec deux touches.");
     	for (String joueur : joueurs){
     		voix.playText(joueur + " : " + scores.get(joueur));
     		try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
     	} 
    	// on redonne le focus au JFrame principal 
    	// (après un clic, le focus est sur le bouton)
    	this.requestFocus();
    }

	@Override
	public void changeColor() {
	}
	
	@Override
	public void changeSize() {
	}

	// renvoie le fichier wave contenant le message d'accueil
	protected  String wavAccueil() {
		return "../ressources/sons/accueilFichier.wav";
	}
	
	// renvoie le fichier wave contenant la règle du jeu
	protected  String wavRegleJeu() {
		return "../ressources/sons/accueilFichier.wav";
	}
	
	// renvoie le fichier wave contenant la règle du jeu
	protected  String wavAide() {
		return "../ressources/sons/aide.wav";
	}

}
