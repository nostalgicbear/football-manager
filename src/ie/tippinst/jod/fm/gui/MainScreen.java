package ie.tippinst.jod.fm.gui;

import ie.tippinst.jod.fm.app.Game;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.LayoutStyle;
import javax.swing.ProgressMonitor;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class MainScreen extends JFrame {
	
	private static final long serialVersionUID = -3630689263806675808L;
	private JButton newGame;
	private JButton loadGame;
	private JButton quitGame;
	private JLabel image;
	//private Game game;
	
	public MainScreen(){
		super("Football Manager");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		loadGame = new JButton("Load Game");
		loadGame.setFont(new java.awt.Font("Verdana",1,12));
		loadGame.setBackground(new java.awt.Color(64,0,64));
		loadGame.setForeground(new java.awt.Color(0,159,0));
		loadGame.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		loadGame.setToolTipText("Load a previously saved game");
		quitGame = new JButton("Quit Game");
		quitGame.setFont(new java.awt.Font("Verdana",1,12));
		quitGame.setBackground(new java.awt.Color(64,0,64));
		quitGame.setForeground(new java.awt.Color(0,159,0));
		quitGame.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
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
			newGame.setBackground(new java.awt.Color(64,0,64));
			newGame.setForeground(new java.awt.Color(0,159,0));
			newGame.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
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
		getContentPane().setBackground(new java.awt.Color(255,255,255));
		this.setResizable(false);
		this.setVisible(true);

		loadGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO: Load Game Behaviour
			}
			
		});
		
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
		//game = Game.getInstance();
	    NewUserScreen ns = new NewUserScreen();	    
	    ns.setVisible(true);
	    this.setVisible(false);
	}
	
	private void loadGame(ActionEvent ae){
	    String message = "Description of Task";
	    String note = "subtask";
	    String title = "Task Title";
	    UIManager.put("ProgressMonitor.progressText", title);

	    int min = 0;
	    int max = 1000000000;
	    JFrame component = this;
	    ProgressMonitor pm = new ProgressMonitor(component, message, note, min, max);
	    pm.setMillisToDecideToPopup(1);
	    pm.setMillisToPopup(1);
	    System.out.println("Stop task");

	    boolean cancelled = pm.isCanceled();
	    if (cancelled) {
	      System.out.println("Stop task");
	    } else {
	      pm.setProgress(100);
	      pm.setNote("New Note");
	    }
	}
	
	public static void main(String[] args) {
		try {
	        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	    } catch (Exception evt) {}
		new MainScreen();
	}

}
