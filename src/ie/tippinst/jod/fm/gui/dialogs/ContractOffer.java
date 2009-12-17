package ie.tippinst.jod.fm.gui.dialogs;
import ie.tippinst.jod.fm.app.Game;
import ie.tippinst.jod.fm.gui.MainGameScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.WindowConstants;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class ContractOffer extends javax.swing.JDialog {
	private JLabel wagesLabel;
	private JButton cancelButton;
	private JButton offerContractButton;
	private JComboBox statusComboBox;
	private JLabel statusLabel;
	private JComboBox lengthOfContractComboBox;
	private JLabel contractLengthLabel;
	private JTextField wagesTextField;
	private String player;
	private int value;
	private Game game;
	private MainGameScreen mg;

	public ContractOffer(String player, int value) {
		super();
		mg = MainGameScreen.getInstance();
		mg.setEnabled(false);
		this.player = player;
		this.value = value;
		game = Game.getInstance();
		
		initGUI();
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		offerContractButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				offerContract(e);
			}
			
		});
		
		cancelButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				cancelAction(e);
			}
			
		});
	}
	
	private void cancelAction(ActionEvent ae){
		mg.setEnabled(true);
		this.dispose();
	}
	
	private void offerContract(ActionEvent ae){
		game.offerContractToPlayer(value, player, Integer.parseInt(wagesTextField.getText()), (lengthOfContractComboBox.getSelectedIndex() + 1), statusComboBox.getSelectedIndex());
		mg.setEnabled(true);
		this.dispose();
	}
	
	private void initGUI() {
		try {
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			this.setTitle("Contract Offer");
			{
				wagesLabel = new JLabel();
				wagesLabel.setText("Wages:");
			}
			{
				offerContractButton = new JButton();
				offerContractButton.setText("Offer Contract");
			}
			{
				cancelButton = new JButton();
				cancelButton.setText("Cancel");
			}
			{
				statusLabel = new JLabel();
				statusLabel.setText("Squad Status:");
			}
			{
				ComboBoxModel statusComboBoxModel = 
					new DefaultComboBoxModel(
							new String[] { "Indispensable", "First Team", "Squad Player"});
				statusComboBox = new JComboBox();
				statusComboBox.setModel(statusComboBoxModel);
			}
			{
				wagesTextField = new JTextField();
			}
			{
				contractLengthLabel = new JLabel();
				contractLengthLabel.setText("Length of Contract:");
			}
			{
				ComboBoxModel lengthOfContractComboBoxModel = 
					new DefaultComboBoxModel(
							new String[] { "1 Year", "2 Years", "3 Years", "4 Years", "5 Years" });
				lengthOfContractComboBox = new JComboBox();
				lengthOfContractComboBox.setModel(lengthOfContractComboBoxModel);
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap(59, 59)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(wagesTextField, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(wagesLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(lengthOfContractComboBox, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(contractLengthLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(statusComboBox, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(statusLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
				.addGap(33)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(cancelButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
				    .addComponent(offerContractButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(44, 44));
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap(47, 47)
				.addGroup(thisLayout.createParallelGroup()
				    .addComponent(offerContractButton, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(statusLabel, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
				        .addGap(33))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(contractLengthLabel, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
				        .addGap(24))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(wagesLabel, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
				        .addGap(85)))
				.addGap(24)
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(thisLayout.createSequentialGroup()
				        .addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))
				    .addGroup(thisLayout.createSequentialGroup()
				        .addComponent(statusComboBox, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))
				    .addGroup(thisLayout.createSequentialGroup()
				        .addComponent(lengthOfContractComboBox, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))
				    .addGroup(thisLayout.createSequentialGroup()
				        .addComponent(wagesTextField, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(42, Short.MAX_VALUE));
			setSize(400, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
