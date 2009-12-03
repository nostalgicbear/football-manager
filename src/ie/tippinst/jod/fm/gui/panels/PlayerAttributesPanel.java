package ie.tippinst.jod.fm.gui.panels;

import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

import ie.tippinst.jod.fm.app.Game;

public class PlayerAttributesPanel extends JPanel {
	
	private static final long serialVersionUID = -4562195385405420531L;
	private JLabel handlingLabel;
    private JLabel penaltiesValueLabel;
    private JLabel dribblingLabel;
    private JLabel tacklingValueLabel;
    private JLabel reflexesLabel;
    private JLabel decisionsLabel;
    private JLabel headingLabel;
    private JLabel penaltiesLabel;
    private JLabel handlingValueLabel;
    private JLabel reflexesValueLabel;
    private JLabel decisionsValueLabel;
    private JLabel headingValueLabel;
    private JLabel longShotsValueLabel;
    private JLabel tacklingLabel;
    private JLabel longShotsLabel;
    private JLabel influenceLabel;
    private JLabel markingLabel;
    private JLabel shootingLabel;
    private JLabel commandOfAreaLabel;
    private JLabel commandOfAreaValueLabel;
    private JLabel shootingValueLabel;
    private JLabel markingValueLabel;
    private JLabel influenceValueLabel;
    private JLabel dribblingValueLabel;
    private JLabel paceLabel;
    private JLabel paceValueLabel;
    private JLabel passingLabel;
    private JLabel passingValueLabel;
    private JLabel strengthLabel;
    private JLabel strengthValueLabel;
    private JLabel crossingLabel;
    private JLabel crossingValueLabel;
    private JLabel staminaLabel;
    private JLabel staminaValueLabel;
    private Game game;

    public PlayerAttributesPanel(String player) {
    	game = Game.getInstance();
        initComponents();
        List<String> list = game.getPlayerProfileInfo(player);
    	handlingValueLabel.setText(list.get(12));
    	reflexesValueLabel.setText(list.get(13));
    	commandOfAreaValueLabel.setText(list.get(14));
    	decisionsValueLabel.setText(list.get(15));
    	shootingValueLabel.setText(list.get(16));
    	headingValueLabel.setText(list.get(17));
    	markingValueLabel.setText(list.get(18));
    	passingValueLabel.setText(list.get(19));
    	crossingValueLabel.setText(list.get(20));
    	tacklingValueLabel.setText(list.get(21));
    	longShotsValueLabel.setText(list.get(22));
    	dribblingValueLabel.setText(list.get(23));
    	penaltiesValueLabel.setText(list.get(24));
    	influenceValueLabel.setText(list.get(25));
    	paceValueLabel.setText(list.get(26));
    	strengthValueLabel.setText(list.get(27));
    	staminaValueLabel.setText(list.get(28));
    }

    private void initComponents() {

    	 handlingLabel = new JLabel();
         reflexesLabel = new JLabel();
         decisionsLabel = new JLabel();
         headingLabel = new JLabel();
         penaltiesLabel = new JLabel();
         handlingValueLabel = new JLabel();
         reflexesValueLabel = new JLabel();
         decisionsValueLabel = new JLabel();
         headingValueLabel = new JLabel();
         penaltiesValueLabel = new JLabel();
         dribblingLabel = new JLabel();
         tacklingValueLabel = new JLabel();
         longShotsValueLabel = new JLabel();
         tacklingLabel = new JLabel();
         longShotsLabel = new JLabel();
         influenceLabel = new JLabel();
         markingLabel = new JLabel();
         shootingLabel = new JLabel();
         commandOfAreaLabel = new JLabel();
         commandOfAreaValueLabel = new JLabel();
         shootingValueLabel = new JLabel();
         markingValueLabel = new JLabel();
         influenceValueLabel = new JLabel();
         dribblingValueLabel = new JLabel();
         paceLabel = new JLabel();
         paceValueLabel = new JLabel();
         passingLabel = new JLabel();
         passingValueLabel = new JLabel();
         strengthLabel = new JLabel();
         strengthValueLabel = new JLabel();
         crossingLabel = new JLabel();
         crossingValueLabel = new JLabel();
         staminaLabel = new JLabel();
         staminaValueLabel = new JLabel();

         handlingLabel.setText("Handling:");

         reflexesLabel.setText("Reflexes:");

         commandOfAreaLabel.setText("Command of Area:");

         decisionsLabel.setText("Decisions:");

         shootingLabel.setText("Shooting:");

         headingLabel.setText("Heading:");

         markingLabel.setText("Marking:");

         passingLabel.setText("Passing:");

         crossingLabel.setText("Crossing:");

         tacklingLabel.setText("Tackling:");

         longShotsLabel.setText("Long Shots:");

         dribblingLabel.setText("Dribbling:");
         
         penaltiesLabel.setText("Penalties:");

         influenceLabel.setText("Influence:");

         paceLabel.setText("Pace:");

         strengthLabel.setText("Strength:");

         staminaLabel.setText("Stamina:");

         GroupLayout layout = new GroupLayout(this);
         this.setLayout(layout);
         layout.setHorizontalGroup(
             layout.createParallelGroup(GroupLayout.Alignment.LEADING)
             .addGroup(layout.createSequentialGroup()
                 .addGap(25, 25, 25)
                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                     .addComponent(handlingLabel)
                     .addComponent(reflexesLabel)
                     .addComponent(commandOfAreaLabel)
                     .addComponent(shootingLabel)
                     .addComponent(headingLabel)
                     .addComponent(markingLabel)
                     .addComponent(decisionsLabel)
                     .addComponent(passingLabel)
                     .addComponent(crossingLabel))
                 .addGap(26, 26, 26)
                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                     .addComponent(decisionsValueLabel)
                     .addComponent(markingValueLabel)
                     .addComponent(headingValueLabel)
                     .addComponent(shootingValueLabel)
                     .addComponent(commandOfAreaValueLabel)
                     .addComponent(reflexesValueLabel)
                     .addComponent(handlingValueLabel)
                     .addComponent(passingValueLabel)
                     .addComponent(crossingValueLabel))
                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                     .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                         .addGroup(layout.createSequentialGroup()
                             .addComponent(staminaLabel)
                             .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 15, GroupLayout.PREFERRED_SIZE))
                         .addGroup(GroupLayout.Alignment.TRAILING, layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                             .addGroup(layout.createSequentialGroup()
                                 .addComponent(strengthLabel)
                                 .addGap(11, 11, 11))
                             .addGroup(GroupLayout.Alignment.TRAILING, layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                 .addComponent(longShotsLabel)
                                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                     .addGroup(layout.createSequentialGroup()
                                         .addComponent(tacklingLabel)
                                         .addGap(15, 15, 15))
                                     .addGroup(layout.createSequentialGroup()
                                         .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                             .addComponent(influenceLabel)
                                             .addComponent(penaltiesLabel))
                                         .addGap(6, 6, 6)))
                                 .addGroup(layout.createSequentialGroup()
                                     .addComponent(paceLabel)
                                     .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 30, GroupLayout.PREFERRED_SIZE)))))
                     .addComponent(dribblingLabel))
                 .addGap(59, 59, 59)
                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                     .addComponent(longShotsValueLabel, GroupLayout.Alignment.TRAILING)
                     .addComponent(tacklingValueLabel, GroupLayout.Alignment.TRAILING)
                     .addComponent(dribblingValueLabel, GroupLayout.Alignment.TRAILING)
                     .addGroup(GroupLayout.Alignment.TRAILING, layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                         .addComponent(influenceValueLabel)
                         .addComponent(penaltiesValueLabel)
                         .addComponent(paceValueLabel)
                         .addComponent(strengthValueLabel)
                         .addComponent(staminaValueLabel)))
                 .addGap(41, 41, 41))
         );
         layout.setVerticalGroup(
             layout.createParallelGroup(GroupLayout.Alignment.LEADING)
             .addGroup(layout.createSequentialGroup()
                 .addGap(45, 45, 45)
                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                     .addGroup(layout.createSequentialGroup()
                         .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                             .addGroup(layout.createSequentialGroup()
                                 .addComponent(handlingValueLabel)
                                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                 .addComponent(reflexesValueLabel)
                                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                     .addComponent(commandOfAreaValueLabel)
                                     .addComponent(dribblingLabel))
                                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                     .addComponent(decisionsValueLabel)
                                     .addComponent(penaltiesLabel))
                                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                     .addComponent(shootingValueLabel)
                                     .addComponent(influenceLabel))
                                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                 .addComponent(headingValueLabel)
                                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                     .addComponent(markingValueLabel)
                                     .addComponent(paceLabel)))
                             .addGroup(layout.createSequentialGroup()
                                 .addComponent(tacklingLabel)
                                 .addGap(6, 6, 6)
                                 .addComponent(longShotsLabel)
                                 .addGap(21, 21, 21)))
                         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                         .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                             .addComponent(passingValueLabel)
                             .addComponent(strengthLabel))
                         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                         .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                             .addComponent(crossingValueLabel)
                             .addComponent(staminaLabel)))
                     .addGroup(layout.createSequentialGroup()
                         .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                             .addGroup(layout.createSequentialGroup()
                                 .addComponent(handlingLabel)
                                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                 .addComponent(reflexesLabel)
                                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                 .addComponent(commandOfAreaLabel)
                                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                 .addComponent(decisionsLabel)
                                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                 .addComponent(shootingLabel)
                                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                 .addComponent(headingLabel)
                                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                     .addComponent(markingLabel)
                                     .addComponent(paceValueLabel)))
                             .addGroup(layout.createSequentialGroup()
                                 .addComponent(tacklingValueLabel)
                                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                 .addComponent(longShotsValueLabel)
                                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                     .addGroup(layout.createSequentialGroup()
                                         .addGap(25, 25, 25)
                                         .addComponent(penaltiesValueLabel))
                                     .addGroup(layout.createSequentialGroup()
                                         .addGap(6, 6, 6)
                                         .addComponent(dribblingValueLabel)))
                                 .addGap(7, 7, 7)
                                 .addComponent(influenceValueLabel)))
                         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                         .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                             .addComponent(passingLabel)
                             .addComponent(strengthValueLabel))
                         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                         .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                             .addComponent(crossingLabel)
                             .addComponent(staminaValueLabel))))
                 .addContainerGap(81, Short.MAX_VALUE))
         );
     }
}
