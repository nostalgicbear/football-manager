package ie.tippinst.jod.fm.gui.panels;

import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

import ie.tippinst.jod.fm.app.Game;

public class StaffProfilePanel extends JPanel {
	
	private static final long serialVersionUID = -670048094102046679L;
	private JLabel nameLabel;
    private JLabel clubValueLabel;
    private JLabel wagesLabel;
    private JLabel wagesValueLabel;
    private JLabel dateOfBirthLabel;
    private JLabel nationalityLabel;
    private JLabel roleLabel;
    private JLabel clubLabel;
    private JLabel nameValueLabel;
    private JLabel dateOfBirthValueLabel;
    private JLabel nationalityValueLabel;
    private JLabel roleValueLabel;
    private Game game;

    public StaffProfilePanel(String staffMember) {
    	game = Game.getInstance();
        initComponents();
        List<String> list = game.getStaffProfileInfo(staffMember);
    	nameValueLabel.setText(list.get(0));
    	dateOfBirthValueLabel.setText(list.get(1));
    	nationalityValueLabel.setText(list.get(2));
    	roleValueLabel.setText(list.get(3));
    	clubValueLabel.setText(list.get(4));
    	wagesValueLabel.setText(list.get(5));
    }

    private void initComponents() {

    	 nameLabel = new JLabel();
         dateOfBirthLabel = new JLabel();
         nationalityLabel = new JLabel();
         roleLabel = new JLabel();
         clubLabel = new JLabel();
         nameValueLabel = new JLabel();
         dateOfBirthValueLabel = new JLabel();
         nationalityValueLabel = new JLabel();
         roleValueLabel = new JLabel();
         clubValueLabel = new JLabel();
         wagesLabel = new JLabel();
         wagesValueLabel = new JLabel();

         nameLabel.setText("Name:");

         dateOfBirthLabel.setText("Date of Birth:");

         nationalityLabel.setText("Nationality:");

         roleLabel.setText("Role:");

         clubLabel.setText("Club:");

         wagesLabel.setText("Wages:");

         GroupLayout layout = new GroupLayout(this);
         this.setLayout(layout);
         layout.setHorizontalGroup(
             layout.createParallelGroup(GroupLayout.Alignment.LEADING)
             .addGroup(layout.createSequentialGroup()
                 .addGap(114, 114, 114)
                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                     .addGroup(layout.createSequentialGroup()
                         .addComponent(wagesLabel)
                         .addContainerGap())
                     .addGroup(layout.createSequentialGroup()
                         .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                             .addGroup(layout.createSequentialGroup()
                                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                     .addComponent(roleLabel)
                                     .addComponent(clubLabel))
                                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                     .addComponent(clubValueLabel)
                                     .addComponent(roleValueLabel)
                                     .addComponent(wagesValueLabel)))
                             .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                     .addComponent(nameLabel)
                                     .addComponent(dateOfBirthLabel)
                                     .addComponent(nationalityLabel))
                                 .addGap(49, 49, 49)
                                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                     .addComponent(nationalityValueLabel)
                                     .addComponent(dateOfBirthValueLabel)
                                     .addComponent(nameValueLabel))))
                         .addContainerGap(142, Short.MAX_VALUE))))
         );
         layout.setVerticalGroup(
             layout.createParallelGroup(GroupLayout.Alignment.LEADING)
             .addGroup(layout.createSequentialGroup()
                 .addGap(64, 64, 64)
                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                     .addComponent(nameLabel)
                     .addComponent(nameValueLabel))
                 .addGap(18, 18, 18)
                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                     .addComponent(dateOfBirthLabel)
                     .addComponent(dateOfBirthValueLabel))
                 .addGap(18, 18, 18)
                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                     .addComponent(nationalityLabel)
                     .addComponent(nationalityValueLabel))
                 .addGap(18, 18, 18)
                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                     .addComponent(roleLabel)
                     .addComponent(roleValueLabel))
                 .addGap(18, 18, 18)
                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                     .addComponent(clubLabel)
                     .addComponent(clubValueLabel))
                 .addGap(18, 18, 18)
                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                     .addComponent(wagesLabel)
                     .addComponent(wagesValueLabel))
                 .addContainerGap(62, Short.MAX_VALUE))
         );
     }
}
