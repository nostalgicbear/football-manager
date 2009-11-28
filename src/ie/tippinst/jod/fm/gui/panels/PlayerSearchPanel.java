package ie.tippinst.jod.fm.gui.panels;

import ie.tippinst.jod.fm.app.Game;
import ie.tippinst.jod.fm.model.NonPlayer;
import ie.tippinst.jod.fm.model.Player;

import java.awt.Color;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class PlayerSearchPanel extends JPanel {
	
	private JScrollPane jScrollPane1;
    private JTable jTable1;
    private JTableHeader jTable1Header;
    private Game game;
    private JButton playerInfoButton;
    
    public PlayerSearchPanel() {
    	game = game.getInstance();
    	initComponents();
    }
    
    private void initComponents(){
    	jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();
        jTable1Header = jTable1.getTableHeader();
        playerInfoButton = new JButton();
        
        List<Player> list = game.getPlayers();
    	int numberOfPlayers = list.size();
    	String [][] data = new String [numberOfPlayers][2];
    	for (int i = 0; i < numberOfPlayers; i++){
    		data[i][0] = list.get(i).getFirstName() + " " + list.get(i).getLastName();
    		data[i][1] = list.get(i).getPosition();
    	}    	

        jTable1.setModel(new DefaultTableModel(data,
            new String [] {
                "Name", "Position"
            }
        ));
        jTable1.setGridColor(new Color(255, 255, 255));
        jTable1Header.setForeground(new Color(0).white);
        jTable1Header.setBackground(new Color(0).red);
        jScrollPane1.setViewportView(jTable1);
        
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

	public JTable getjTable1() {
		return jTable1;
	}

	public void setjTable1(JTable jTable1) {
		this.jTable1 = jTable1;
	}
}
