package ie.tippinst.jod.fm.gui.panels;

import ie.tippinst.jod.fm.app.Game;
import ie.tippinst.jod.fm.model.Player;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
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
    private String userClub;
    private TableColumn squadTableColumn;
    private JComboBox selectionComboBox;
    private SquadPanel oldPanel;
    private int unEditableColumn;
    
    public SquadPanel(String club, SquadPanel oldPanel, String userClub) {
    	game = Game.getInstance();
    	this.club = club;
    	this.userClub = userClub;
    	if(this.club.equals(this.userClub))
    		unEditableColumn = 0;
    	else
    		unEditableColumn = -1;
    	this.oldPanel = oldPanel;
    	initComponents();
    }   
    
    private void initComponents(){
    	squadTableScrollPane = new JScrollPane();
        squadTable = new JTable(){    
        	public boolean isCellEditable(int row, int col) {   
            int column = getColumnModel().getColumn(col).getModelIndex();   
            return (column == unEditableColumn);
        }};
        squadTableHeader = squadTable.getTableHeader();
        
        List<Player> list = game.getSquad(club);
    	int squadSize = list.size();
    	String[][] data = new String[squadSize][9];
    	for (int i = 0; i < squadSize; i++){
    		if(this.oldPanel == null)
    			data[i][0] = "";
    		else if(oldPanel.getClub().equals(this.getClub()) && i < oldPanel.getSquadTable().getRowCount())
    			data[i][0] = (String) oldPanel.getSquadTable().getValueAt(i, 0);
    		else if(oldPanel.getClub().equals(this.getClub()))
    			data[i][0] = "";
    		data[i][1] = list.get(i).getFirstName() + " " + list.get(i).getLastName();
    		data[i][2] = list.get(i).getPosition();
    		if(list.get(i).getMorale() >= 8000){
    			data[i][3] = "Very High";
    		}
    		else if(list.get(i).getMorale() >= 6000){
    			data[i][3] = "High";
    		}
    		else if(list.get(i).getMorale() >= 4000){
    			data[i][3] = "Ok";
    		}
    		else if(list.get(i).getMorale() >= 2000){
    			data[i][3] = "Low";
    		}
    		else{
    			data[i][3] = "Very Low";
    		}
    		data[i][4] = Math.round(list.get(i).getMatchCondition()) + "%";
    		data[i][5] = list.get(i).getStatusAsString();
    		String timeOut;
    		if(list.get(i).getDaysUnavailable() < 14){
    			timeOut = list.get(i).getDaysUnavailable() + (list.get(i).getDaysUnavailable() == 1 ? " day" : " days");
    		}
    		else if(list.get(i).getDaysUnavailable() < 60){
    			timeOut = list.get(i).getDaysUnavailable() / 7 + " weeks";
    		}
    		else{
    			timeOut = list.get(i).getDaysUnavailable() / 30 + " months";
    		}
    		data[i][6] = (list.get(i).getInjury() == null ? "None" : (list.get(i).getInjury().getName()) + " (Out for " + timeOut + ")");
    		data[i][7] = list.get(i).getLeagueAppearances() + "";
    		data[i][8] = list.get(i).getLeagueGoals() + "";
    	}    	

        squadTable.setModel(new DefaultTableModel(data,
            new String [] {
                "Selection", "Name", "Position", "Morale", "Condition", "Status", "Injury", "Appearances", "Goals"
            }
        ));
        
        TableColumn selectionColumn = squadTable.getColumnModel().getColumn(0);
        selectionComboBox = new JComboBox();
        selectionComboBox.addItem("");
        selectionComboBox.addItem("GK");
        selectionComboBox.addItem("DR");
        selectionComboBox.addItem("DL");
        selectionComboBox.addItem("CB");
        selectionComboBox.addItem("CB");
        selectionComboBox.addItem("MR");
        selectionComboBox.addItem("ML");
        selectionComboBox.addItem("CM");
        selectionComboBox.addItem("CM");
        selectionComboBox.addItem("S");
        selectionComboBox.addItem("S");
        selectionColumn.setCellEditor(new DefaultCellEditor(selectionComboBox));

        //squadTable.setGridColor(new Color(255, 255, 255));
        squadTableHeader.setForeground(Color.white);
        squadTableHeader.setBackground(Color.red);
        
        for (int i = 0; i < 7; i++) {
            squadTableColumn = squadTable.getColumnModel().getColumn(i);
            if (i == 1 || i == 6) {
                squadTableColumn.setPreferredWidth(100);
            } else {
                squadTableColumn.setPreferredWidth(30);
            }
        }
        
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

	public String getUserClub() {
		return userClub;
	}

	public void setUserClub(String userClub) {
		this.userClub = userClub;
	}

	public String getClub() {
		return club;
	}

	public void setClub(String club) {
		this.club = club;
	}
	
	public List<String> getSelectedPlayers(){
		List<String> players = new ArrayList<String>();
		for(int i = 0; i < this.getSquadTable().getRowCount(); i++){
			if(!(this.getSquadTable().getValueAt(i, 0).equals(""))){
				players.add((String) this.getSquadTable().getValueAt(i, 1)); 
			}
		}
		return players;
	}
}
