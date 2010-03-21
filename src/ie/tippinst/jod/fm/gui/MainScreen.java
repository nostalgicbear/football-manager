package ie.tippinst.jod.fm.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileFilter;

public class MainScreen extends JFrame {
	
	private static final long serialVersionUID = -3630689263806675808L;
	private JButton newGame;
	private JButton loadGame;
	private JButton quitGame;
	private JLabel image;
	private JFileChooser fc;
	
	public MainScreen(){
		super("Football Manager");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		fc = new JFileChooser("games");
		fc.setAcceptAllFileFilterUsed(false);
		fc.setFileFilter(new FileFilter(){

			@Override
			public boolean accept(File file) {
				if(file.isDirectory()){
					return true;
				}
				else if(file.getName().endsWith(".fm")){
					return true;
				}
				return false;
			}

			@Override
			public String getDescription() {
				return "Games (*.fm)";
			}
			
		});
		loadGame = new JButton("Load Game");
		loadGame.setFont(new java.awt.Font("Verdana",1,12));
		loadGame.setBackground(Color.LIGHT_GRAY);
		loadGame.setForeground(Color.BLACK);
		loadGame.setToolTipText("Load a previously saved game");
		quitGame = new JButton("Quit Game");
		quitGame.setFont(new java.awt.Font("Verdana",1,12));
		quitGame.setBackground(Color.LIGHT_GRAY);
		quitGame.setForeground(Color.BLACK);
		quitGame.setToolTipText("Exit the game");
		{
			image = new JLabel(new ImageIcon("football_manager_logo.gif"));
			image.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		}
		GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
		getContentPane().setLayout(thisLayout);
		{
			newGame = new JButton("Start New Game");
			newGame.setFont(new java.awt.Font("Verdana",1,12));
			newGame.setBackground(Color.LIGHT_GRAY);
			newGame.setForeground(Color.BLACK);
			newGame.setToolTipText("Create a new game");
			newGame.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					startNewGame(e);
				}
				
			});
		}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addComponent(image, 0, 300, Short.MAX_VALUE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(newGame, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
				    .addComponent(loadGame, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
				    .addComponent(quitGame, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
				.addContainerGap());
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(newGame, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
				        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				        .addComponent(loadGame, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
				        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				        .addComponent(quitGame, 0, 134, Short.MAX_VALUE))
				    .addComponent(image, GroupLayout.Alignment.LEADING, 0, 443, Short.MAX_VALUE))
				.addContainerGap());
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		
		loadGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loadGame(e);
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
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));	    
	    NewUserScreen ns = new NewUserScreen();	    
	    ns.setVisible(true);
	    this.setVisible(false);
	}
	
	private void loadGame(ActionEvent ae){
		File file = null;
		int returnVal = fc.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
            this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            MainGameScreen mg = new MainGameScreen(file.getAbsolutePath());
            mg.setVisible(true);
            this.setVisible(false);
		}
	}
	
	public static void main(String[] args) {
		try {
	        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	    } catch (Exception evt) {}
		new MainScreen();
	}

}
