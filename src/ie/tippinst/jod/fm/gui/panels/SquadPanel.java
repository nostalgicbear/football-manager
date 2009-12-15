package ie.tippinst.jod.fm.gui.panels;

import ie.tippinst.jod.fm.app.Game;
import ie.tippinst.jod.fm.model.Player;

import java.awt.Color;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class SquadPanel extends JPanel {
	
	private static final long serialVersionUID = -3633691508818748379L;
	private JScrollPane squadTableScrollPane;
    private JTable squadTable;
    private JTableHeader squadTableHeader;
    private Game game;
    private String club;
    private TableColumn squadTableColumn;
    
    public SquadPanel(String club) {
    	game = Game.getInstance();
    	this.club = club;
    	initComponents();
    }
    
    private void initComponents(){
    	squadTableScrollPane = new JScrollPane();
        squadTable = new JTable();
        squadTableHeader = squadTable.getTableHeader();
        
        List<Player> list = game.getSquad(club);
    	int squadSize = list.size();
    	String [][] data = new String [squadSize][4];
    	for (int i = 0; i < squadSize; i++){
    		data[i][0] = list.get(i).getFirstName() + " " + list.get(i).getLastName();
    		data[i][1] = list.get(i).getPosition();
    		if(list.get(i).getMorale() >= 8000){
    			data[i][2] = "Very High";
    		}
    		else if(list.get(i).getMorale() >= 6000){
    			data[i][2] = "High";
    		}
    		else if(list.get(i).getMorale() >= 4000){
    			data[i][2] = "Ok";
    		}
    		else if(list.get(i).getMorale() >= 2000){
    			data[i][2] = "Low";
    		}
    		else{
    			data[i][2] = "Very Low";
    		}
    		data[i][3] = list.get(i).getMatchCondition() + "%";
    	}    	

        squadTable.setModel(new DefaultTableModel(data,
            new String [] {
                "Name", "Position", "Morale", "Condition"
            }
        ));
        for (int i = 0; i < 4; i++) {
            squadTableColumn = squadTable.getColumnModel().getColumn(i);
            if (i == 0) {
                squadTableColumn.setPreferredWidth(100);
            } else {
                squadTableColumn.setPreferredWidth(30);
            }
        }
        //squadTable.setGridColor(new Color(255, 255, 255));
        squadTableHeader.setForeground(Color.white);
        squadTableHeader.setBackground(Color.red);
        squadTableScrollPane.setViewportView(squadTable);
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 12, Short.MAX_VALUE)
                    .addComponent(squadTableScrollPane, GroupLayout.PREFERRED_SIZE, 1000, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 13, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 275, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(squadTableScrollPane, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }

	public JTable getSquadTable() {
		return squadTable;
	}

	public void setSquadTable(JTable squadTable) {
		this.squadTable = squadTable;
	}
}
