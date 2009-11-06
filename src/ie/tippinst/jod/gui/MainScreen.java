package ie.tippinst.jod.gui;

import ie.tippinst.jod.fm.app.Game;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainScreen extends JFrame {
	
	private static final long serialVersionUID = -3630689263806675808L;
	private JButton newGame;
	private JButton newUser;
	private Game game;
	
	public MainScreen(){
		super("Football Manager");
		game = new Game();
		newGame = new JButton("Start New Game");
		newUser = new JButton("Create New User");
		this.setLayout(new FlowLayout());
		this.getContentPane().add(newGame);
		this.getContentPane().add(newUser);
		this.setSize(300,300);
		this.setVisible(true);
		
		newGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				game.loadDatabase();
				System.out.println("Database loaded");
			}
			
		});
		
		/*newUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				game.createNewUser();
				System.out.println("New User created");
			}
			
		});*/
	}
	
	

}
