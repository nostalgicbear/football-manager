package ie.tippinst.jod.fm.gui.panels;

import java.util.Calendar;
import java.util.List;

import ie.tippinst.jod.fm.app.Game;
import ie.tippinst.jod.fm.model.Match;

import javax.swing.AbstractListModel;
import javax.swing.GroupLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class FixturesPanel extends JPanel {

    public FixturesPanel(String club) {
    	game = Game.getInstance();
    	this.club = club;
        initComponents();
    }

    private void initComponents() {

        fixturesScrollPane = new javax.swing.JScrollPane();
        fixturesTable = new JTable();
        
        List<Match> list = game.getFixtures(this.club);
        String[][] data = new String[list.size()][5];
        for(int i = 0; i < data.length; i++){
        	String day = "";
        	switch(list.get(i).getDate().get(Calendar.DAY_OF_WEEK)){
        	case 1:	day = "Sun";
        			break;
        	case 2:	day = "Mon";
					break;
        	case 3:	day = "Tue";
					break;
        	case 4:	day = "Wed";
					break;
        	case 5:	day = "Thu";
					break;
        	case 6:	day = "Fri";
					break;
        	case 7:	day = "Sat";
					break;
        	default:System.out.println("Error");
					break;
        	}
        	data[i][0] =  day + ", " + list.get(i).getDate().get(Calendar.DAY_OF_MONTH) + "/" + (list.get(i).getDate().get(Calendar.MONTH) + 1) + "/" + list.get(i).getDate().get(Calendar.YEAR);
            data[i][1] = (list.get(i).getHomeTeam().getName().equals(this.club) ? list.get(i).getAwayTeam().getName() : list.get(i).getHomeTeam().getName());
            data[i][2] = (list.get(i).getHomeTeam().getName().equals(this.club) ? "H" : "A");
            data[i][3] = (list.get(i).getHomeTeam().getName().equals(this.club) ? ((list.get(i).getHomeScore() == -1) ? "" : list.get(i).getHomeScore()) : ((list.get(i).getAwayScore() == -1) ? "" : list.get(i).getAwayScore())) + (list.get(i).isPostponed() ? "P" : "") + " - " + (list.get(i).isPostponed() ? "P" : "") + (list.get(i).getAwayTeam().getName().equals(this.club) ? ((list.get(i).getHomeScore() == -1) ? "" : list.get(i).getHomeScore()) : ((list.get(i).getAwayScore() == -1) ? "" : list.get(i).getAwayScore()));
            data[i][4] = list.get(i).getCompetition().getName();        	
        }
        
        fixturesTable.setModel(new DefaultTableModel(data,
            new String [] {
                "Date", "Opponent", "H/A", "Score", "Competition"
            }
        ));
        
        for (int i = 0; i < 4; i++) {
            fixturesTableColumn = fixturesTable.getColumnModel().getColumn(i);
            if (i == 1 || i == 4) {
            	fixturesTableColumn.setPreferredWidth(150);
            } else if (i == 2 || i == 3) {
            	fixturesTableColumn.setPreferredWidth(10);
            }
        }

        fixturesScrollPane.setViewportView(fixturesTable);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 12, Short.MAX_VALUE)
                    .addComponent(fixturesScrollPane, GroupLayout.PREFERRED_SIZE, 675, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 13, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 275, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(fixturesScrollPane, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }

    // Variables declaration - do not modify
    private javax.swing.JScrollPane fixturesScrollPane;
    private JTable fixturesTable;
    private TableColumn fixturesTableColumn;
    private Game game;
    private String club;
    // End of variables declaration

}
