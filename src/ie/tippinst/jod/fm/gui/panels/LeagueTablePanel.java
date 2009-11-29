package ie.tippinst.jod.fm.gui.panels;

import ie.tippinst.jod.fm.app.Game;
import ie.tippinst.jod.fm.model.Club;
import ie.tippinst.jod.fm.model.NonPlayer;
import ie.tippinst.jod.fm.model.Player;

import java.awt.Color;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class LeagueTablePanel extends JPanel {
	
	private JScrollPane jScrollPane1;
    private JTable leagueTable;
    private JTableHeader jTable1Header;
    private Game game;
    private TableColumn column;
    
    public LeagueTablePanel() {
    	game = Game.getInstance();
    	initComponents();
    }
    
    private void initComponents(){
    	jScrollPane1 = new JScrollPane();
        leagueTable = new JTable();
        jTable1Header = leagueTable.getTableHeader();
        
    	String [][] data = game.getLeagueTable("Premier League");    	

        leagueTable.setModel(new DefaultTableModel(data,
            new String [] {
                "P", "Name", "Pld", "W", "D", "L", "GF", "GA", "GD", "Pts"
            }
        ));
        
        for (int i = 0; i < 10; i++) {
            column = leagueTable.getColumnModel().getColumn(i);
            if (i == 1) {
                column.setPreferredWidth(100);
            } else {
                column.setPreferredWidth(10);
            }
        }
        
        leagueTable.setGridColor(new Color(255, 255, 255));
        jTable1Header.setForeground(new Color(0).white);
        jTable1Header.setBackground(new Color(0).red);
        jScrollPane1.setViewportView(leagueTable);
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 12, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 13, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 275, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }

	public JTable getLeagueTable() {
		return leagueTable;
	}

	public void setLeagueTable(JTable leagueTable) {
		this.leagueTable = leagueTable;
	}
}
