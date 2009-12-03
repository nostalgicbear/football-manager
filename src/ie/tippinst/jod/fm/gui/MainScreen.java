package ie.tippinst.jod.fm.gui;

import ie.tippinst.jod.fm.app.Game;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainScreen extends JFrame {
	
	private static final long serialVersionUID = -3630689263806675808L;
	private JButton newGame;
	private JButton loadGame;
	private JButton quitGame;
	private Game game;
	
	public MainScreen(){
		super("Football Manager");
		game = Game.getInstance();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		newGame = new JButton("Start New Game");
		loadGame = new JButton("Load Game");
		quitGame = new JButton("Quit Game");
		this.setLayout(new FlowLayout());
		this.getContentPane().add(newGame);
		this.getContentPane().add(loadGame);
		this.getContentPane().add(quitGame);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		newGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startNewGame(e);
			}
			
		});
		
		loadGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO: Load Game Behaviour
			}
			
		});
		
		quitGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(getDefaultCloseOperation());
			}
			
		});
	}
	
	private void startNewGame(ActionEvent ae){
		game.loadDatabase();
		NewUserScreen ns = new NewUserScreen();
		ns.setVisible(true);
		this.setVisible(false);
	}
	
	public static void main(String[] args) {
		new MainScreen();
	}

}
