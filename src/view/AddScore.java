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

public class AddScore extends JFrame implements ActionListener{
	private int score;
	private JTextField name;
	private JButton sendButton;
	public AddScore (int score) {
		super("Demande de nom");
		this.score = score;
		JPanel Pane = new JPanel(new GridLayout(1, 2));
		Pane.add(name);
		Pane.add(sendButton = nouveauBouton("Ajouter"));
		this.setContentPane(Pane);
		this.pack();

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
			Score.addScore(name,score);
		}
	}
}

