package ie.tippinst.jod.fm.gui;

import ie.tippinst.jod.fm.model.Card;
import ie.tippinst.jod.fm.model.Goal;
import ie.tippinst.jod.fm.model.Match;
import ie.tippinst.jod.fm.model.Player;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.WindowConstants;


public class MatchScreen extends JFrame {

	private static final long serialVersionUID = 7516174674476294455L;
	private JLabel homeTeamNameLabel;
	private JLabel homeTeamScoreLabel;
	private JButton leaveMatchButton;
	private JList commentaryList;
	private JButton playButton;
	private JList awayTeamPlayerList;
	private JList homeTeamPlayerList;
	private JLabel clockLabel;
	private JLabel scoreSeparatorLabel;
	private JLabel awayTeamNameLabel;
	private JLabel awayTeamScoreLabel;
	private Match match;
	private Timer timer;
	private ImageIcon icon;
	private JPanel panel;
	private JScrollPane homeTeamScrollPane;
	private JScrollPane awayTeamScrollPane;
	private JScrollPane commentaryScrollPane;

	public MatchScreen(Match match) {
		super();
		
		this.match = match;
		initGUI();
		this.setSize(850, 420);
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);

		timer = new Timer();
		
		playButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				playMatch(e);
			}
			
		});
		
		leaveMatchButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				leaveMatch(e);
			}
			
		});
	}
	
	private void leaveMatch(ActionEvent e) {
		MainGameScreen.getInstance().displayLeague(1, "Barclays Premier League");
		MainGameScreen.getInstance().setEnabled(true);
		this.dispose();
	}

	private void playMatch(ActionEvent ae) {
		playButton.setEnabled(false);
		timer.schedule(new TimerTask(){
			int count = 1;
			public void run() {
				if(count <= 90) {
				  if(count == 1 || count == 46){
					  ((DefaultListModel) commentaryList.getModel()).add(0, "KICK-OFF");
				  }
		    	  clockLabel.setText(count + ":00");
		    	  List<Goal> homeGoals = match.getGoalsForMinute(true, count);
		    	  List<Card> homeCards = match.getCardsForMinute(true, count);
		    	  homeTeamScoreLabel.setText(Integer.parseInt(homeTeamScoreLabel.getText()) + homeGoals.size() + "");
		    	  for(int i = 0; i < homeGoals.size(); i++){
		    		  Player p = homeGoals.get(i).getScorer();
		    		  ((DefaultListModel) commentaryList.getModel()).add(0, (count + ": GOAL FOR " + match.getHomeTeam().getShortName().toUpperCase() + "!!! Scored by: " + p.getFirstName() + " " + p.getLastName()));
		    	  }
		    	  for(int i = 0; i < homeCards.size(); i++){
		    		  Player p = homeCards.get(i).getPlayer();
		    		  String cardColour = "Yellow";
		    		  if(homeCards.get(i).getColour() == 1)
		    			  cardColour = "Red";
		    		  ((DefaultListModel) commentaryList.getModel()).add(0, (count + ": " + cardColour + " Card for " + p.getFirstName() + " " + p.getLastName()));
		    	  }
		    	  List<Goal> awayGoals = match.getGoalsForMinute(false, count);
		    	  List<Card> awayCards = match.getCardsForMinute(false, count);
		    	  awayTeamScoreLabel.setText(Integer.parseInt(awayTeamScoreLabel.getText()) + awayGoals.size() + "");
		    	  for(int i = 0; i < awayGoals.size(); i++){
		    		  Player p = awayGoals.get(i).getScorer();
		    		  ((DefaultListModel) commentaryList.getModel()).add(0, (count + ": GOAL FOR " + match.getAwayTeam().getShortName().toUpperCase() + "!!! Scored by: " + p.getFirstName() + " " + p.getLastName()));
		    	  }
		    	  for(int i = 0; i < awayCards.size(); i++){
		    		  Player p = awayCards.get(i).getPlayer();
		    		  String cardColour = "Yellow";
		    		  if(awayCards.get(i).getColour() == 1)
		    			  cardColour = "Red";
		    		  ((DefaultListModel) commentaryList.getModel()).add(0, (count + ": " + cardColour + " Card for " + p.getFirstName() + " " + p.getLastName()));
		    	  }
		    	  if(count == 45){
					  ((DefaultListModel) commentaryList.getModel()).add(0, "HALF-TIME");
				  }
		    	  count++;
				} else {
					((DefaultListModel) commentaryList.getModel()).add(0, "FULL-TIME");
					timer.cancel();
				}
		    }
		}, 1, 1000);
	}

	private void initGUI() {
		icon = new ImageIcon("football_stadium.jpg");
        panel = new JPanel(){

			private static final long serialVersionUID = -2893292897263506673L;

			protected void paintComponent(Graphics g){
				Dimension d = getSize();
				g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
			}
		};

        homeTeamNameLabel = new javax.swing.JLabel();
        awayTeamNameLabel = new javax.swing.JLabel();
        homeTeamScoreLabel = new javax.swing.JLabel();
        awayTeamScoreLabel = new javax.swing.JLabel();
        scoreSeparatorLabel = new javax.swing.JLabel();
        clockLabel = new javax.swing.JLabel();
        homeTeamScrollPane = new javax.swing.JScrollPane();
        homeTeamPlayerList = new javax.swing.JList();
        awayTeamScrollPane = new javax.swing.JScrollPane();
        awayTeamPlayerList = new javax.swing.JList();
        commentaryScrollPane = new javax.swing.JScrollPane();
        commentaryList = new javax.swing.JList();
        playButton = new javax.swing.JButton();
        leaveMatchButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        homeTeamNameLabel.setFont(new Font("Verdana",1,18));
        homeTeamNameLabel.setText(match.getHomeTeam().getShortName());
        homeTeamNameLabel.setForeground(Color.DARK_GRAY);
        homeTeamNameLabel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        homeTeamNameLabel.setOpaque(true);
        homeTeamNameLabel.setBackground(Color.WHITE);

        awayTeamNameLabel.setFont(new Font("Verdana",1,18));
        awayTeamNameLabel.setText(match.getAwayTeam().getShortName());
        awayTeamNameLabel.setForeground(Color.DARK_GRAY);
        awayTeamNameLabel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        awayTeamNameLabel.setOpaque(true);
        awayTeamNameLabel.setBackground(Color.WHITE);

        homeTeamScoreLabel.setFont(new Font("Verdana",1,18));
        homeTeamScoreLabel.setText("0");
        homeTeamScoreLabel.setForeground(Color.DARK_GRAY);
        homeTeamScoreLabel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        homeTeamScoreLabel.setOpaque(true);
        homeTeamScoreLabel.setBackground(Color.WHITE);

        awayTeamScoreLabel.setFont(new Font("Verdana",1,18));
        awayTeamScoreLabel.setText("0");
        awayTeamScoreLabel.setForeground(Color.DARK_GRAY);
        awayTeamScoreLabel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        awayTeamScoreLabel.setOpaque(true);
        awayTeamScoreLabel.setBackground(Color.WHITE);

        scoreSeparatorLabel.setFont(new Font("Verdana",1,18));
        scoreSeparatorLabel.setText("-");
        scoreSeparatorLabel.setForeground(Color.DARK_GRAY);
        scoreSeparatorLabel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        scoreSeparatorLabel.setOpaque(true);
        scoreSeparatorLabel.setBackground(Color.WHITE);

        clockLabel.setFont(new Font("Verdana",1,18));
        clockLabel.setText("00:00");
        clockLabel.setForeground(Color.WHITE);
        clockLabel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        clockLabel.setOpaque(true);
        clockLabel.setBackground(Color.DARK_GRAY);
        
        List<String> homeTeamPlayerNames = new ArrayList<String>(); 
        Iterator<Player> i = match.getHomeTeam().getSelectedTeam().iterator(); 
        while(i.hasNext()){ 
                Player p = i.next(); 
                homeTeamPlayerNames.add(p.getFirstName() + " " + p.getLastName()); 
        } 
        ListModel homeTeamPlayerListModel = new DefaultComboBoxModel(homeTeamPlayerNames.toArray()); 
        homeTeamPlayerList.setModel(homeTeamPlayerListModel); 
        List<String> awayTeamPlayerNames = new ArrayList<String>(); 
        i = match.getAwayTeam().getSelectedTeam().iterator(); 
        while(i.hasNext()){ 
                Player p = i.next(); 
                awayTeamPlayerNames.add(p.getFirstName() + " " + p.getLastName()); 
        } 
        ListModel awayTeamPlayerListModel = new DefaultComboBoxModel(awayTeamPlayerNames.toArray()); 
        awayTeamPlayerList.setModel(awayTeamPlayerListModel);  

        homeTeamScrollPane.setViewportView(homeTeamPlayerList);

        awayTeamScrollPane.setViewportView(awayTeamPlayerList);

        commentaryList.setModel(new DefaultListModel());
        commentaryScrollPane.setViewportView(commentaryList);
        homeTeamPlayerList.setFont(new Font("Verdana", 0, 11));
        awayTeamPlayerList.setFont(new Font("Verdana", 0, 11));
        commentaryList.setFont(new Font("Verdana", 0, 10));
        homeTeamPlayerList.setBackground(Color.LIGHT_GRAY);
        awayTeamPlayerList.setBackground(Color.LIGHT_GRAY);

        playButton.setFont(new Font("Verdana", 0, 11));
        playButton.setText("Play Match");

        leaveMatchButton.setFont(new Font("Verdana", 0, 11));
        leaveMatchButton.setText("Leave Match");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(clockLabel)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(homeTeamNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(homeTeamScoreLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scoreSeparatorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(awayTeamScoreLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(awayTeamNameLabel)
                .addContainerGap(75, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(homeTeamScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(playButton, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(leaveMatchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(commentaryScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(awayTeamScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(clockLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(homeTeamNameLabel)
                    .addComponent(homeTeamScoreLabel)
                    .addComponent(scoreSeparatorLabel)
                    .addComponent(awayTeamScoreLabel)
                    .addComponent(awayTeamNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(homeTeamScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(awayTeamScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(commentaryScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playButton, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(leaveMatchButton, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

	}

}
