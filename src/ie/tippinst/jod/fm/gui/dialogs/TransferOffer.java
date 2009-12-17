package ie.tippinst.jod.fm.gui.dialogs;
import ie.tippinst.jod.fm.app.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

import ie.tippinst.jod.fm.gui.MainGameScreen;


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
public class TransferOffer extends javax.swing.JDialog {
	private JLabel feeLabel;
	private JTextField feeTextField;
	private JButton cancelButton;
	private JButton makeOfferButton;
	private JLabel euroSymbolLabel;
	private Game game = Game.getInstance();
	private String player;
	private String user;
	
	public TransferOffer(JFrame frame, String user, String player) {
		super(frame);
		this.player = player;
		this.user = user;
		frame.setEnabled(false);
		initGUI();
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cancelAction(e);
			}
			
		});
		
		makeOfferButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				makeOfferAction(e);
			}
			
		});
		
		this.setLocationRelativeTo(null);
	}
	
	private void makeOfferAction(ActionEvent ae){
		game.makeOfferForPlayer(user, player, Integer.parseInt(feeTextField.getText()));
		this.getParent().setEnabled(true);
		((MainGameScreen) this.getParent()).getAddToShortlistButton().setText("Remove from Shortlist");
		this.getParent().validate();
		this.dispose();
	}
	
	private void cancelAction(ActionEvent ae){
		this.getParent().setEnabled(true);
		this.dispose();
	}
	
	private void initGUI() {
		try {
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			this.setTitle("Transfer Offer");
			{
				feeLabel = new JLabel();
				GroupLayout feeLabelLayout = new GroupLayout((JComponent)feeLabel);
				feeLabel.setLayout(null);
				feeLabel.setText("Fee:   ");
				feeLabelLayout.setHorizontalGroup(feeLabelLayout.createParallelGroup());
				feeLabelLayout.setVerticalGroup(feeLabelLayout.createParallelGroup());
			}
			{
				feeTextField = new JTextField();
			}
			{
				euroSymbolLabel = new JLabel();
				euroSymbolLabel.setText("\u20ac");
			}
			{
				makeOfferButton = new JButton();
				makeOfferButton.setText("Make Offer");
			}
			{
				cancelButton = new JButton();
				cancelButton.setText("Cancel");
			}
				thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
					.addContainerGap(88, 88)
					.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					    .addComponent(feeLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					    .addComponent(feeTextField, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					    .addComponent(euroSymbolLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(51)
					.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					    .addComponent(makeOfferButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					    .addComponent(cancelButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(77, 77));
				thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
					.addContainerGap(40, 40)
					.addGroup(thisLayout.createParallelGroup()
					    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
					        .addComponent(feeLabel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					        .addGap(0, 32, Short.MAX_VALUE)
					        .addComponent(euroSymbolLabel, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
					        .addGap(0, 7, GroupLayout.PREFERRED_SIZE)
					        .addComponent(feeTextField, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE))
					    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
					        .addGap(0, 24, Short.MAX_VALUE)
					        .addComponent(makeOfferButton, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
					        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					        .addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
					        .addGap(21)))
					.addContainerGap(57, 57));
			setSize(400, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
