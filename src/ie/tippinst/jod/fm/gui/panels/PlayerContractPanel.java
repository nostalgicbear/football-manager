package ie.tippinst.jod.fm.gui.panels;

import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

import ie.tippinst.jod.fm.app.Game;

public class PlayerContractPanel extends JPanel {
	
	private static final long serialVersionUID = -4583409302166116466L;
	private JLabel clubLabel;
    private JLabel playerValueValueLabel;
    private JLabel wagesValueLabel;
    private JLabel happinessValueLabel;
    private JLabel contractExpiryLabel;
    private JLabel squadStatusLabel;
    private JLabel playerValueLabel;
    private JLabel wagesLabel;
    private JLabel happinessLabel;
    private JLabel clubValueLabel;
    private JLabel contractExpiryValueLabel;
    private JLabel squadStatusValueLabel;
    private Game game;

    public PlayerContractPanel(String player) {
    	game = Game.getInstance();
        initComponents();
        List<String> list = game.getPlayerProfileInfo(player);
    	clubValueLabel.setText(list.get(29));
    	contractExpiryValueLabel.setText(list.get(30));
    	squadStatusValueLabel.setText(list.get(31));
    	playerValueValueLabel.setText(list.get(32));
    	wagesValueLabel.setText(list.get(33));
    	happinessValueLabel.setText(list.get(34));
    }

    private void initComponents() {

    	 clubLabel = new JLabel();
         contractExpiryLabel = new JLabel();
         squadStatusLabel = new JLabel();
         playerValueLabel = new JLabel();
         wagesLabel = new JLabel();
         happinessLabel = new JLabel();
         clubValueLabel = new JLabel();
         contractExpiryValueLabel = new JLabel();
         squadStatusValueLabel = new JLabel();
         playerValueValueLabel = new JLabel();
         wagesValueLabel = new JLabel();
         happinessValueLabel = new JLabel();

         clubLabel.setText("Club:");

         contractExpiryLabel.setText("Contract Expiry:");

         squadStatusLabel.setText("Squad Status:");

         playerValueLabel.setText("Value:");

         wagesLabel.setText("Wages:");

         happinessLabel.setText("Happiness:");

         GroupLayout layout = new GroupLayout(this);
         this.setLayout(layout);
         layout.setHorizontalGroup(
             layout.createParallelGroup(GroupLayout.Alignment.LEADING)
             .addGroup(layout.createSequentialGroup()
                 .addGap(93, 93, 93)
                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                     .addComponent(wagesLabel)
                     .addComponent(happinessLabel)
                     .addComponent(playerValueLabel)
                     .addComponent(clubLabel)
                     .addComponent(squadStatusLabel)
                     .addComponent(contractExpiryLabel))
                 .addGap(82, 82, 82)
                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                     .addComponent(clubValueLabel)
                     .addComponent(playerValueValueLabel)
                     .addComponent(happinessValueLabel)
                     .addComponent(wagesValueLabel)
                     .addComponent(squadStatusValueLabel)
                     .addComponent(contractExpiryValueLabel))
                 .addContainerGap(112, Short.MAX_VALUE))
         );
         layout.setVerticalGroup(
             layout.createParallelGroup(GroupLayout.Alignment.LEADING)
             .addGroup(layout.createSequentialGroup()
                 .addGap(59, 59, 59)
                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                     .addGroup(layout.createSequentialGroup()
                         .addComponent(clubLabel)
                         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                         .addComponent(contractExpiryLabel, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
                         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                         .addComponent(squadStatusLabel)
                         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                         .addComponent(playerValueLabel)
                         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                         .addComponent(wagesLabel)
                         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                         .addComponent(happinessLabel))
                     .addGroup(layout.createSequentialGroup()
                         .addComponent(clubValueLabel)
                         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                         .addComponent(contractExpiryValueLabel)
                         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                         .addComponent(squadStatusValueLabel)
                         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                         .addComponent(playerValueValueLabel)
                         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                         .addComponent(wagesValueLabel)
                         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                         .addComponent(happinessValueLabel)))
                 .addContainerGap(127, Short.MAX_VALUE))
         );
     }
}
