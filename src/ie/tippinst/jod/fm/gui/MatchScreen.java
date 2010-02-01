package ie.tippinst.jod.fm.gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import ie.tippinst.jod.fm.model.Card;
import ie.tippinst.jod.fm.model.Goal;
import ie.tippinst.jod.fm.model.Match;
import ie.tippinst.jod.fm.model.Player;

import javax.swing.DefaultComboBoxModel;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.LayoutStyle;
import javax.swing.ListModel;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;


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
public class MatchScreen extends javax.swing.JFrame {
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

	public MatchScreen(Match match) {
		super();
		this.match = match;
		//this.setUndecorated(true);
		initGUI();

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
		    		  ((DefaultListModel) commentaryList.getModel()).add(0, (count + ": GOAL FOR " + match.getHomeTeam().getName().toUpperCase() + "!!! Scored by: " + p.getFirstName() + " " + p.getLastName()));
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
		    		  ((DefaultListModel) commentaryList.getModel()).add(0, (count + ": GOAL FOR " + match.getAwayTeam().getName().toUpperCase() + "!!! Scored by: " + p.getFirstName() + " " + p.getLastName()));
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
		try {
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				homeTeamNameLabel = new JLabel();
				homeTeamNameLabel.setText(match.getHomeTeam().getName());
			}
			{
				homeTeamScoreLabel = new JLabel();
				homeTeamScoreLabel.setText("0");
			}
			{
				clockLabel = new JLabel();
				clockLabel.setText("00:00");
			}
			{
				playButton = new JButton();
				playButton.setText("Play Match");
			}
			{
				leaveMatchButton = new JButton();
				leaveMatchButton.setText("Leave Match");
			}
			{
				ListModel commentaryListModel = new DefaultListModel();
				commentaryList = new JList();
				commentaryList.setModel(commentaryListModel);
			}
			{
				List<String> playerNames = new ArrayList<String>();
				Iterator<Player> i = match.getHomeTeam().getSelectedTeam().iterator();
				while(i.hasNext()){
					Player p = i.next();
					playerNames.add(p.getFirstName() + " " + p.getLastName());
				}
				ListModel homeTeamPlayerListModel = new DefaultComboBoxModel(playerNames.toArray());
				homeTeamPlayerList = new JList();
				homeTeamPlayerList.setModel(homeTeamPlayerListModel);
			}
			{
				List<String> playerNames = new ArrayList<String>();
				//System.out.println(match.getAwayTeam().getSelectedTeam().size());
				Iterator<Player> i = match.getAwayTeam().getSelectedTeam().iterator();
				while(i.hasNext()){
					Player p = i.next();
					playerNames.add(p.getFirstName() + " " + p.getLastName());
				}
				ListModel awayTeamPlayerListModel = new DefaultComboBoxModel(playerNames.toArray());
				awayTeamPlayerList = new JList();
				awayTeamPlayerList.setModel(awayTeamPlayerListModel);
			}
			{
				scoreSeparatorLabel = new JLabel();
				scoreSeparatorLabel.setText("-");
			}
			{
				awayTeamNameLabel = new JLabel();
				awayTeamNameLabel.setText(match.getAwayTeam().getName());
			}
			{
				awayTeamScoreLabel = new JLabel();
				awayTeamScoreLabel.setText("0");
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addComponent(clockLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(12)
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				        .addComponent(awayTeamNameLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
				        .addComponent(homeTeamNameLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				        .addComponent(scoreSeparatorLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
				        .addComponent(awayTeamScoreLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
				        .addComponent(homeTeamScoreLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)))
				.addGap(12)
				.addGroup(thisLayout.createParallelGroup()
				    .addComponent(awayTeamPlayerList, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
				    .addComponent(homeTeamPlayerList, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addGap(83)
				        .addComponent(commentaryList, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)))
				.addGap(85)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(playButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(leaveMatchButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
				.addContainerGap());
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(thisLayout.createParallelGroup()
				    .addComponent(homeTeamPlayerList, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addGap(47)
				        .addComponent(playButton, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
				        .addGap(17)))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(thisLayout.createParallelGroup()
				    .addComponent(commentaryList, GroupLayout.Alignment.LEADING, 0, 464, Short.MAX_VALUE)
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addGroup(thisLayout.createParallelGroup()
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addGap(0, 82, Short.MAX_VALUE)
				                .addComponent(homeTeamNameLabel, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(leaveMatchButton, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
				                .addGap(0, 89, Short.MAX_VALUE)))
				        .addGroup(thisLayout.createParallelGroup()
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(homeTeamScoreLabel, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE)
				                .addComponent(scoreSeparatorLabel, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE)
				                .addGap(12))
				            .addComponent(clockLabel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
				        .addComponent(awayTeamScoreLabel, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE)
				        .addComponent(awayTeamNameLabel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
				        .addGap(126)))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(awayTeamPlayerList, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
				.addContainerGap());
			pack();
			this.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
