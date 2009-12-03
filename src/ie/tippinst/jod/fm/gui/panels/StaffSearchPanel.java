package ie.tippinst.jod.fm.gui.panels;

import ie.tippinst.jod.fm.app.Game;
import ie.tippinst.jod.fm.model.NonPlayer;

import java.awt.Color;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class StaffSearchPanel extends JPanel {

	private static final long serialVersionUID = 2839000647528695746L;
	private JScrollPane staffSearchTableScrollPane;
    private JTable staffSearchTable;
    private JTableHeader staffSearchTableHeader;
    private Game game;
    
    public StaffSearchPanel() {
    	game = Game.getInstance();
    	initComponents();
    }
    
    private void initComponents(){
    	staffSearchTableScrollPane = new JScrollPane();
        staffSearchTable = new JTable();
        staffSearchTableHeader = staffSearchTable.getTableHeader();
        new JButton();
        
        List<NonPlayer> list = game.getAllStaff();
    	int numberOfStaff = list.size();
    	String [][] data = new String [numberOfStaff][2];
    	for (int i = 0; i < numberOfStaff; i++){
    		data[i][0] = list.get(i).getFirstName() + " " + list.get(i).getLastName();
    		data[i][1] = list.get(i).getRole();
    	}    	

        staffSearchTable.setModel(new DefaultTableModel(data,
            new String [] {
                "Name", "Role"
            }
        ));
        staffSearchTable.setGridColor(new Color(255, 255, 255));
        staffSearchTableHeader.setForeground(Color.white);
        staffSearchTableHeader.setBackground(Color.red);
        staffSearchTableScrollPane.setViewportView(staffSearchTable);
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 12, Short.MAX_VALUE)
                    .addComponent(staffSearchTableScrollPane, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 13, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 275, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(staffSearchTableScrollPane, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }

	public JTable getStaffSearchTable() {
		return staffSearchTable;
	}

	public void setStaffSearchTable(JTable staffSearchTable) {
		this.staffSearchTable = staffSearchTable;
	}
}
