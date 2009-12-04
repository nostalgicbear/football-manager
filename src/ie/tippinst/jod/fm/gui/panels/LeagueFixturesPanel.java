package ie.tippinst.jod.fm.gui.panels;

import ie.tippinst.jod.fm.app.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

public class LeagueFixturesPanel extends JPanel {
	
	private static final long serialVersionUID = -4770635436027700840L;
	private JLabel match1Label;
    private JLabel match10Label;
    private JLabel match2Label;
    private JLabel match3Label;
    private JLabel match4Label;
    private JLabel match5Label;
    private JLabel match6Label;
    private JLabel match7Label;
    private JLabel match8Label;
    private JLabel match9Label;
    private JComboBox dateComboBox;
    private Game game;
    private String date;

    public LeagueFixturesPanel(String date) {
    	game = Game.getInstance();
    	this.date = date;
        initComponents();
        displayFixtures((String) dateComboBox.getSelectedItem());
        
        dateComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayFixtures((String) dateComboBox.getSelectedItem());
			}
			
		});
    }
    
    private void displayFixtures(String date){
    	List<String> fixtures = game.getLeagueFixtures("Premier League", date);
        Collections.sort(fixtures);
        match1Label.setText(fixtures.get(0));
        match2Label.setText(fixtures.get(1));
        match3Label.setText(fixtures.get(2));
        match4Label.setText(fixtures.get(3));
        match5Label.setText(fixtures.get(4));
        match6Label.setText(fixtures.get(5));
        match7Label.setText(fixtures.get(6));
        match8Label.setText(fixtures.get(7));
        match9Label.setText(fixtures.get(8));
        match10Label.setText(fixtures.get(9));
    }

    private void initComponents() {

        match1Label = new JLabel();
        match2Label = new JLabel();
        match3Label = new JLabel();
        match4Label = new JLabel();
        match5Label = new JLabel();
        match6Label = new JLabel();
        match7Label = new JLabel();
        match8Label = new JLabel();
        match9Label = new JLabel();
        match10Label = new JLabel();
        dateComboBox = new JComboBox();

        match1Label.setHorizontalAlignment(SwingConstants.CENTER);
        match2Label.setHorizontalAlignment(SwingConstants.CENTER);
        match3Label.setHorizontalAlignment(SwingConstants.CENTER);
        match4Label.setHorizontalAlignment(SwingConstants.CENTER);
        match5Label.setHorizontalAlignment(SwingConstants.CENTER);
        match6Label.setHorizontalAlignment(SwingConstants.CENTER);
        match7Label.setHorizontalAlignment(SwingConstants.CENTER);
        match8Label.setHorizontalAlignment(SwingConstants.CENTER);
        match9Label.setHorizontalAlignment(SwingConstants.CENTER);
        match10Label.setHorizontalAlignment(SwingConstants.CENTER);
        
        dateComboBox.setModel(new DefaultComboBoxModel(
        		new String[] { "15-AUG-09", "19-AUG-09", "22-AUG-09", "29-AUG-09",
        					"12-SEP-09", "19-SEP-09", "26-SEP-09", "03-OCT-09",
        					"17-OCT-09", "24-OCT-09", "31-OCT-09", "07-NOV-09",
        					"21-NOV-09", "28-NOV-09", "05-DEC-09", "12-DEC-09",
        					"16-DEC-09", "19-DEC-09", "26-DEC-09", "28-DEC-09",
        					"09-JAN-10", "16-JAN-10", "27-JAN-10", "30-JAN-10",
        					"06-FEB-10", "10-FEB-10", "20-FEB-10", "27-FEB-10",
        					"06-MAR-10", "13-MAR-10", "20-MAR-10", "27-MAR-10",
        					"03-APR-10", "10-APR-10", "17-APR-10", "24-APR-10",
        					"01-MAY-10", "09-MAY-10" }));
        
        dateComboBox.setSelectedItem(date);
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(match1Label, GroupLayout.PREFERRED_SIZE, 1000, GroupLayout.PREFERRED_SIZE)
                    .addComponent(match2Label, GroupLayout.PREFERRED_SIZE, 1000, GroupLayout.PREFERRED_SIZE)
                    .addComponent(match3Label, GroupLayout.PREFERRED_SIZE, 1000, GroupLayout.PREFERRED_SIZE)
                    .addComponent(match4Label, GroupLayout.PREFERRED_SIZE, 1000, GroupLayout.PREFERRED_SIZE)
                    .addComponent(match5Label, GroupLayout.PREFERRED_SIZE, 1000, GroupLayout.PREFERRED_SIZE)
                    .addComponent(match6Label, GroupLayout.PREFERRED_SIZE, 1000, GroupLayout.PREFERRED_SIZE)
                    .addComponent(match7Label, GroupLayout.PREFERRED_SIZE, 1000, GroupLayout.PREFERRED_SIZE)
                    .addComponent(match8Label, GroupLayout.PREFERRED_SIZE, 1000, GroupLayout.PREFERRED_SIZE)
                    .addComponent(match9Label, GroupLayout.PREFERRED_SIZE, 1000, GroupLayout.PREFERRED_SIZE)
                    .addComponent(match10Label, GroupLayout.PREFERRED_SIZE, 1000, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            	.addContainerGap(305, Short.MAX_VALUE)
            	.addComponent(dateComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            	.addGap(49, 49, 49))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            	.addContainerGap()
                .addComponent(dateComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(match1Label)
                .addGap(30, 30, 30)
                .addComponent(match2Label)
                .addGap(30, 30, 30)
                .addComponent(match3Label)
                .addGap(30, 30, 30)
                .addComponent(match4Label)
                .addGap(30, 30, 30)
                .addComponent(match5Label)
                .addGap(30, 30, 30)
                .addComponent(match6Label)
                .addGap(30, 30, 30)
                .addComponent(match7Label)
                .addGap(30, 30, 30)
                .addComponent(match8Label)
                .addGap(30, 30, 30)
                .addComponent(match9Label, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(match10Label)
                .addGap(30, 30, 30))
        );
    }
}
