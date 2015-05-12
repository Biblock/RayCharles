package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import jeu.Score;

public class AddScore extends JFrame implements ActionListener{
	private int score;
	private JTextField name;
	private JButton sendButton;
	private int numGame;
	private int difficulte;
	public AddScore (int numGame, int difficulte, int score) {
		super("Demande de nom");
		this.score = score;
		this.numGame = numGame;
		this.difficulte = difficulte;
		JPanel Pane = new JPanel(new GridLayout(1, 2));
		name = new JTextField();
		Pane.add(name);
		Pane.add(sendButton = nouveauBouton("Ajouter"));
		this.setContentPane(Pane);
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private JButton nouveauBouton(String texte) {
		JButton bouton = new JButton(texte);
		bouton.setMargin(new Insets(0, 0, 0, 0));
		bouton.setPreferredSize(new Dimension(90, 25));
		bouton.addActionListener(this);
		return bouton;
	}
	@Override
	public void actionPerformed(ActionEvent arg) {
		if (arg.getSource().equals(sendButton)) {
			String str = name.getText();
			Score.addScore(numGame, difficulte, str,score);
			dispose();
		}
	}
}

