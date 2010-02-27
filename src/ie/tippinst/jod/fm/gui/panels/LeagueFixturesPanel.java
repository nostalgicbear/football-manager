package ie.tippinst.jod.fm.gui.panels;

import ie.tippinst.jod.fm.app.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;

public class LeagueFixturesPanel extends JPanel {
	
	private static final long serialVersionUID = -4770635436027700840L;
	private List<JLabel> matchLabels = new ArrayList<JLabel>();
    private JComboBox dateComboBox;
    private Game game;
    private String date;
    private String name;

    public LeagueFixturesPanel(String name, String date) {
    	game = Game.getInstance();
    	this.name = name;
    	this.date = date;
    	dateComboBox = new JComboBox();
        dateComboBox.setModel(new DefaultComboBoxModel(game.getMatchDates(name)));
        dateComboBox.setSelectedItem(this.date);	
        displayFixtures((String) dateComboBox.getSelectedItem());
        initComponents();
        this.validate();
        dateComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayFixtures((String) dateComboBox.getSelectedItem());
				initComponents();
			}
			
		});
    }
    
    private void displayFixtures(String date){
    	this.removeAll();
    	matchLabels.clear();
    	List<String> fixtures = game.getLeagueFixtures(name, date);
        Collections.sort(fixtures);
        Iterator<String> i = fixtures.iterator();
        while(i.hasNext()){
        	matchLabels.add(new JLabel(i.next()));
        	matchLabels.get(matchLabels.size() - 1).setHorizontalAlignment(SwingConstants.CENTER);
        }
    }

    private void initComponents() {
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        ParallelGroup group = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        Iterator<JLabel> i = matchLabels.iterator();
        while(i.hasNext()){
        	group.addComponent(i.next(), GroupLayout.PREFERRED_SIZE, 675, GroupLayout.PREFERRED_SIZE);
        }
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(group)
                .addContainerGap())
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            	.addContainerGap(305, Short.MAX_VALUE)
            	.addComponent(dateComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            	.addGap(49, 49, 49))
        );
        SequentialGroup seqGroup = layout.createSequentialGroup();
        seqGroup.addContainerGap();
        seqGroup.addComponent(dateComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        i = matchLabels.iterator();
        while(i.hasNext()){
        	seqGroup.addGap(20, 20, 20);
        	seqGroup.addComponent(i.next());
        }
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(seqGroup)
        );
    }
}
