package ie.tippinst.jod.fm.gui.panels;

import ie.tippinst.jod.fm.app.Game;
import ie.tippinst.jod.fm.model.Player;

import java.text.DecimalFormat;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class PlayerSearchPanel extends JPanel {
	
	private static final long serialVersionUID = 259089926578413368L;
	private JScrollPane playerSearchTableScrollPane;
    private JTable playerSearchTable;
    private JTableHeader playerSearchTableHeader;
    
    private Game game;
    public PlayerSearchPanel() {
    	game = Game.getInstance();
    	initComponents();
    }
    
    private void initComponents(){
    	playerSearchTableScrollPane = new JScrollPane();
        playerSearchTable = new JTable(){
        	
			private static final long serialVersionUID = -1183658066718019385L;

			@Override
        	public boolean isCellEditable(int row, int col) {   
            int column = getColumnModel().getColumn(col).getModelIndex();   
            return false;
        	}
        };
        playerSearchTableHeader = playerSearchTable.getTableHeader();
        
        List<Player> list = game.getPlayers();
    	int numberOfPlayers = list.size();
    	String [][] data = new String [numberOfPlayers][6];
    	for (int i = 0; i < numberOfPlayers; i++){
    		data[i][0] = list.get(i).getFirstName() + " " + list.get(i).getLastName();
    		data[i][1] = list.get(i).getPosition();
    		data[i][2] = list.get(i).getCurrentClub().getName();
    		data[i][3] = list.get(i).getAge() + "";
    		data[i][4] = list.get(i).getNationality().getName();
    		DecimalFormat format = new DecimalFormat("000,000");
    		String value = format.format(list.get(i).getMarketValue());
    		data[i][5] = "�" + value;
    	}    	

        playerSearchTable.setModel(new DefaultTableModel(data,
            new String [] {
                "Name", "Position", "Club", "Age", "Nationality", "Value"
            }
        ));
        playerSearchTable.setAutoCreateRowSorter(true);
        playerSearchTableScrollPane.setViewportView(playerSearchTable);
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 12, Short.MAX_VALUE)
                    .addComponent(playerSearchTableScrollPane, GroupLayout.PREFERRED_SIZE, 675, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 13, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 275, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(playerSearchTableScrollPane, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }

	public JTable getPlayerSearchTable() {
		return playerSearchTable;
	}

	public void setPlayerSearchTable(JTable playerSearchTable) {
		this.playerSearchTable = playerSearchTable;
	}
}
