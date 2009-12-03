package ie.tippinst.jod.fm.gui.panels;

import ie.tippinst.jod.fm.app.Game;

import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

public class ClubInformationPanel extends JPanel {
	
	private static final long serialVersionUID = -1713831500080838679L;
	private JLabel nationalityLabel;
    private JLabel reputationLabel;
    private JLabel nationalityValueLabel;
    private JLabel reputationValueLabel;
    private JLabel financesValueLabel;
    private JLabel stadiumValueLabel;
    private JLabel stadiumCapacityValueLabel;
    private JLabel stadiumLabel;
    private JLabel stadiumCapacityLabel;
    private JLabel financesLabel;
    private JLabel divisionLabel;
    private JLabel divisionValueLabel;
    private Game game;

    public ClubInformationPanel(String club) {
    	game = Game.getInstance();
    	initComponents();
    	List<String> list = game.getClubInformation(club);
    	nationalityValueLabel.setText(list.get(0));
    	reputationValueLabel.setText(list.get(1));
    	financesValueLabel.setText(list.get(2));
    	stadiumValueLabel.setText(list.get(4));
    	stadiumCapacityValueLabel.setText(list.get(5));
    	divisionValueLabel.setText(list.get(3));
    }

    private void initComponents() {

        stadiumCapacityValueLabel = new JLabel();
        nationalityLabel = new JLabel();
        nationalityValueLabel = new JLabel();
        stadiumCapacityLabel = new JLabel();
        stadiumLabel = new JLabel();
        stadiumValueLabel = new JLabel();
        financesValueLabel = new JLabel();
        reputationValueLabel = new JLabel();
        reputationLabel = new JLabel();
        financesLabel = new JLabel();
        divisionLabel = new JLabel();
        divisionValueLabel = new JLabel();
        
        
        divisionLabel.setText("Division:");
        
        nationalityLabel.setText("Nationality:");

        stadiumCapacityLabel.setText("Stadium Capacity:");

        stadiumLabel.setText("Stadium:");

        reputationLabel.setText("Reputation:");

        financesLabel.setText("Finances:");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(divisionLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(divisionValueLabel))
                    .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(financesLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(financesValueLabel))
                    .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(stadiumLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(stadiumValueLabel))
                    .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(stadiumCapacityLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(stadiumCapacityValueLabel))
                    .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(nationalityLabel)
                            .addComponent(reputationLabel))
                        .addGap(59, 59, 59)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(nationalityValueLabel)
                            .addComponent(reputationValueLabel))))
                .addContainerGap(150, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(nationalityLabel)
                    .addComponent(nationalityValueLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(reputationLabel)
                    .addComponent(reputationValueLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(financesLabel)
                    .addComponent(financesValueLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(stadiumLabel)
                    .addComponent(stadiumValueLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(stadiumCapacityLabel)
                    .addComponent(stadiumCapacityValueLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(divisionValueLabel)
                    .addComponent(divisionLabel))
                .addContainerGap(69, Short.MAX_VALUE))
        );
    }

}