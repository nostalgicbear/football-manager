package ie.tippinst.jod.fm.gui.panels;

import ie.tippinst.jod.fm.app.Game;
import ie.tippinst.jod.fm.model.NonPlayer;

import java.awt.Color;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class StaffPanel extends JPanel {
	
	private static final long serialVersionUID = 8447045691610505898L;
	private JScrollPane staffTableScrollPane;
    private JTable staffTable;
    private JTableHeader staffTableHeader;
    private Game game;
    private String club;
    
    public StaffPanel(String club) {
    	game = Game.getInstance();
    	this.club = club;
    	initComponents();
    }
    
    private void initComponents(){
    	staffTableScrollPane = new JScrollPane();
        staffTable = new JTable();
        staffTableHeader = staffTable.getTableHeader();
        
        List<NonPlayer> list = game.getStaff(club);
    	int staffMembers = list.size();
    	String [][] data = new String [staffMembers][2];
    	for (int i = 0; i < staffMembers; i++){
    		data[i][0] = list.get(i).getFirstName() + " " + list.get(i).getLastName();
    		data[i][1] = list.get(i).getRole();
    	}    	

        staffTable.setModel(new DefaultTableModel(data,
            new String [] {
                "Name", "Role"
            }
        ));
        staffTable.setGridColor(new Color(255, 255, 255));
        staffTableHeader.setForeground(Color.white);
        staffTableHeader.setBackground(Color.red);
        staffTableScrollPane.setViewportView(staffTable);
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 12, Short.MAX_VALUE)
                    .addComponent(staffTableScrollPane, GroupLayout.PREFERRED_SIZE, 800, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 13, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 275, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(staffTableScrollPane, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }

	public JTable getStaffTable() {
		return staffTable;
	}

	public void setStaffTable(JTable staffTable) {
		this.staffTable = staffTable;
	}
}
