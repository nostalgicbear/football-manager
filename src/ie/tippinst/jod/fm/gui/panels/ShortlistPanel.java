package ie.tippinst.jod.fm.gui.panels;

import ie.tippinst.jod.fm.app.Game;
import ie.tippinst.jod.fm.model.Player;

import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class ShortlistPanel extends JPanel {
	
	private static final long serialVersionUID = 259089926578413368L;
	private JScrollPane shortlistTableScrollPane;
    private JTable shortlistTable;
    private JTableHeader shortlistTableHeader;
    private String user;
    
    private Game game;
    public ShortlistPanel(String user) {
    	game = Game.getInstance();
    	this.user = user;
    	initComponents();
    }
    
    private void initComponents(){
    	shortlistTableScrollPane = new JScrollPane();
        shortlistTable = new JTable();
        shortlistTableHeader = shortlistTable.getTableHeader();
        
        List<Player> list = game.getShortlist(user);
    	int numberOfPlayers = list.size();
    	String [][] data = new String [numberOfPlayers][2];
    	for (int i = 0; i < numberOfPlayers; i++){
    		data[i][0] = list.get(i).getFirstName() + " " + list.get(i).getLastName();
    		data[i][1] = list.get(i).getPosition();
    	}    	

        shortlistTable.setModel(new DefaultTableModel(data,
            new String [] {
                "Name", "Position"
            }
        ));
        shortlistTableScrollPane.setViewportView(shortlistTable);
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 12, Short.MAX_VALUE)
                    .addComponent(shortlistTableScrollPane, GroupLayout.PREFERRED_SIZE, 675, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 13, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 275, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(shortlistTableScrollPane, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }

	public JTable getShortlistTable() {
		return shortlistTable;
	}

	public void setShortlistTable(JTable shortlistTable) {
		this.shortlistTable = shortlistTable;
	}
}
