package ie.tippinst.jod.fm.gui.panels;

import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

import ie.tippinst.jod.fm.app.Game;

public class PlayerProfilePanel extends JPanel {
	
	private static final long serialVersionUID = -2330063059176063428L;
	private JLabel nameLabel;
    private JLabel fatigueValueLabel;
    private JLabel moraleLabel;
    private JLabel moraleValueLabel;
    private JLabel ageLabel;
    private JLabel nationalityLabel;
    private JLabel positionLabel;
    private JLabel fatigueLabel;
    private JLabel nameValueLabel;
    private JLabel ageValueLabel;
    private JLabel nationalityValueLabel;
    private JLabel positionValueLabel;
    private JLabel injuryValueLabel;
    private JLabel injuryLabel;
    private JLabel conditionLabel;
    private JLabel fitnessLabel;
    private JLabel footLabel;
    private JLabel reputationLabel;
    private JLabel dateOfBirthLabel;
    private JLabel dateOfBirthValueLabel;
    private JLabel reputationValueLabel;
    private JLabel footValueLabel;
    private JLabel fitnessValueLabel;
    private JLabel conditionValueLabel;
    private Game game;

    public PlayerProfilePanel(String player) {
    	game = Game.getInstance();
        initComponents();
        List<String> list = game.getPlayerProfileInfo(player);
    	nameValueLabel.setText(list.get(0));
    	ageValueLabel.setText(list.get(1));
    	dateOfBirthValueLabel.setText(list.get(2));
    	nationalityValueLabel.setText(list.get(3));
    	reputationValueLabel.setText(list.get(4));
    	positionValueLabel.setText(list.get(5));
    	footValueLabel.setText(list.get(6));
    	moraleValueLabel.setText(list.get(7));
    	injuryValueLabel.setText(list.get(8));
    	conditionValueLabel.setText(list.get(9));
    	fatigueValueLabel.setText(list.get(10));
    	fitnessValueLabel.setText(list.get(11));
    }

    private void initComponents() {

    	 nameLabel = new JLabel();
         ageLabel = new JLabel();
         nationalityLabel = new JLabel();
         positionLabel = new JLabel();
         fatigueLabel = new JLabel();
         nameValueLabel = new JLabel();
         ageValueLabel = new JLabel();
         nationalityValueLabel = new JLabel();
         positionValueLabel = new JLabel();
         fatigueValueLabel = new JLabel();
         moraleLabel = new JLabel();
         moraleValueLabel = new JLabel();
         injuryValueLabel = new JLabel();
         injuryLabel = new JLabel();
         conditionLabel = new JLabel();
         fitnessLabel = new JLabel();
         footLabel = new JLabel();
         reputationLabel = new JLabel();
         dateOfBirthLabel = new JLabel();
         dateOfBirthValueLabel = new JLabel();
         reputationValueLabel = new JLabel();
         footValueLabel = new JLabel();
         fitnessValueLabel = new JLabel();
         conditionValueLabel = new JLabel();

         nameLabel.setText("Name:");

         ageLabel.setText("Age:");

         dateOfBirthLabel.setText("Date of Birth:");

         nationalityLabel.setText("Nationality:");

         reputationLabel.setText("Reputation:");

         positionLabel.setText("Position:");

         footLabel.setText("Foot:");

         moraleLabel.setText("Morale:");

         injuryLabel.setText("Injury:");

         conditionLabel.setText("Condition:");

         fatigueLabel.setText("Fatigue:");

         fitnessLabel.setText("Fitness:");

         GroupLayout layout = new GroupLayout(this);
         this.setLayout(layout);
         layout.setHorizontalGroup(
             layout.createParallelGroup(GroupLayout.Alignment.LEADING)
             .addGroup(layout.createSequentialGroup()
                 .addGap(60, 60, 60)
                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                     .addComponent(nameLabel)
                     .addComponent(ageLabel)
                     .addComponent(dateOfBirthLabel)
                     .addGroup(layout.createSequentialGroup()
                         .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                             .addComponent(reputationLabel)
                             .addComponent(positionLabel)
                             .addComponent(footLabel)
                             .addComponent(nationalityLabel))
                         .addGap(40, 40, 40)
                         .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                             .addComponent(nationalityValueLabel)
                             .addComponent(footValueLabel)
                             .addComponent(positionValueLabel)
                             .addComponent(reputationValueLabel)
                             .addComponent(dateOfBirthValueLabel)
                             .addComponent(ageValueLabel)
                             .addComponent(nameValueLabel))))
                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                     .addComponent(conditionLabel)
                     .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                         .addGroup(layout.createSequentialGroup()
                             .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                 .addComponent(injuryLabel)
                                 .addComponent(moraleLabel)
                                 .addComponent(fatigueLabel)
                                 .addComponent(fitnessLabel))
                             .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                             .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                 .addComponent(injuryValueLabel)
                                 .addComponent(moraleValueLabel)
                                 .addComponent(conditionValueLabel)
                                 .addComponent(fitnessValueLabel)
                                 .addComponent(fatigueValueLabel)))))
                         /*.addGroup(layout.createSequentialGroup()
                             .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                 .addComponent(fatigueLabel)
                                 .addComponent(fitnessLabel))
                             .addGap(40, 40, 40)
                             .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                 .addComponent(fitnessValueLabel)
                                 .addComponent(fatigueValueLabel)))))*/
                 .addGap(40, 40, 40))
         );
         layout.setVerticalGroup(
             layout.createParallelGroup(GroupLayout.Alignment.LEADING)
             .addGroup(layout.createSequentialGroup()
                 .addGap(90, 90, 90)
                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                     .addGroup(layout.createSequentialGroup()
                         .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                             .addComponent(nameLabel)
                             .addComponent(nameValueLabel)
                             .addComponent(moraleLabel))
                         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                         .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                             .addComponent(ageLabel)
                             .addComponent(ageValueLabel))
                         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                         .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                             .addComponent(dateOfBirthLabel)
                             .addComponent(dateOfBirthValueLabel))
                         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                         .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                             .addComponent(nationalityLabel)
                             .addComponent(nationalityValueLabel))
                         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                         .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                             .addComponent(reputationLabel)
                             .addComponent(reputationValueLabel))
                         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                         .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                             .addComponent(positionLabel)
                             .addComponent(positionValueLabel))
                         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                         .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                             .addComponent(footLabel)
                             .addComponent(footValueLabel)))
                     .addGroup(layout.createSequentialGroup()
                         .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                             .addGroup(layout.createSequentialGroup()
                                 .addComponent(moraleValueLabel)
                                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                 .addComponent(injuryValueLabel))
                             .addComponent(injuryLabel))
                         .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                             .addGroup(layout.createSequentialGroup()
                                 .addGap(50, 50, 50)
                                 .addComponent(fatigueValueLabel))
                             .addGroup(layout.createSequentialGroup()
                                 .addGap(12, 12, 12)
                                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                     .addComponent(conditionLabel)
                                     .addComponent(conditionValueLabel))
                                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                 .addComponent(fatigueLabel)))
                         .addGap(12, 12, 12)
                         .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                             .addComponent(fitnessValueLabel)
                             .addComponent(fitnessLabel))))
                 .addContainerGap(220, Short.MAX_VALUE))
         );
     }

	public JLabel getNameValueLabel() {
		return nameValueLabel;
	}
}
