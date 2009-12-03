package ie.tippinst.jod.fm.gui.panels;

import ie.tippinst.jod.fm.app.Game;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class LeagueTablePanel extends JPanel {
	
	private static final long serialVersionUID = 5551537575295544128L;
	private JScrollPane leagueTableScrollPane;
    private JTable leagueTable;
    private JTableHeader leagueTableHeader;
    private Game game;
    private TableColumn leagueTableColumn;
    
    public LeagueTablePanel() {
    	game = Game.getInstance();
    	initComponents();
    }
    
    private void initComponents(){
    	leagueTableScrollPane = new JScrollPane();
        leagueTable = new JTable();
        leagueTableHeader = leagueTable.getTableHeader();
        
    	String [][] data = game.getLeagueTable("Premier League");    	

        leagueTable.setModel(new DefaultTableModel(data,
            new String [] {
                "P", "Name", "Pld", "W", "D", "L", "GF", "GA", "GD", "Pts"
            }
        ));
        
        for (int i = 0; i < 10; i++) {
            leagueTableColumn = leagueTable.getColumnModel().getColumn(i);
            if (i == 1) {
                leagueTableColumn.setPreferredWidth(100);
            } else {
                leagueTableColumn.setPreferredWidth(10);
            }
        }
        
        leagueTable.setGridColor(new Color(255, 255, 255));
		leagueTableHeader.setForeground(Color.white);
        leagueTableHeader.setBackground(Color.red);
        leagueTableScrollPane.setViewportView(leagueTable);
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 12, Short.MAX_VALUE)
                    .addComponent(leagueTableScrollPane, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 13, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 275, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(leagueTableScrollPane, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE)
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
