package ie.tippinst.jod.fm.gui.dialogs;
import ie.tippinst.jod.fm.app.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

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
	private JLabel euroSymbolLabel2;
	private JLabel transferBudgetValueLabel;
	private JLabel transferBudgetLabel;
	private JButton cancelButton;
	private JButton makeOfferButton;
	private JLabel euroSymbolLabel;
	private Game game = Game.getInstance();
	private String player;
	private String user;
	private double transferBudget;
	
	public TransferOffer(JFrame frame, String user, String player, double transferBudget) {
		super(frame);
		this.player = player;
		this.user = user;
		this.transferBudget = transferBudget;
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
		this.getParent().setEnabled(true);
		((MainGameScreen) this.getParent()).getAddToShortlistButton().setText("Remove from Shortlist");
		this.getParent().validate();
		this.dispose();
		if(!(game.makeOfferForPlayer(user, player, Integer.parseInt(feeTextField.getText())))){
			//TODO: Dialogue box to inform user that they do not have enough transfer budget to make that bid
			System.out.println("Transfer budget too low");
		}
			
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
				transferBudgetLabel = new JLabel();
				transferBudgetLabel.setText("Remaining Transfer Budget:");
			}
			{
				transferBudgetValueLabel = new JLabel();
				DecimalFormat format = new DecimalFormat("000,000");
				String value = format.format(this.transferBudget);
				transferBudgetValueLabel.setText(value);
			}
			{
				euroSymbolLabel2 = new JLabel();
				euroSymbolLabel2.setText("\u20ac");
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
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addGroup(thisLayout.createParallelGroup()
					    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
					        .addComponent(transferBudgetValueLabel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
					    .addComponent(transferBudgetLabel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
					        .addComponent(euroSymbolLabel2, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
					.addGap(18)
					.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					    .addComponent(makeOfferButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					    .addComponent(cancelButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(77, 77));
				thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
					.addContainerGap(26, 26)
					.addGroup(thisLayout.createParallelGroup()
					    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
					        .addComponent(transferBudgetLabel, 0, 194, Short.MAX_VALUE)
					        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					        .addComponent(euroSymbolLabel2, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
					        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					        .addComponent(transferBudgetValueLabel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
					    .addGroup(thisLayout.createSequentialGroup()
					        .addPreferredGap(transferBudgetLabel, feeLabel, LayoutStyle.ComponentPlacement.INDENT)
					        .addGroup(thisLayout.createParallelGroup()
					            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
					                .addComponent(feeLabel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					                .addGap(0, 36, Short.MAX_VALUE)
					                .addComponent(euroSymbolLabel, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
					                .addGap(0, 7, GroupLayout.PREFERRED_SIZE)
					                .addComponent(feeTextField, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE))
					            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
					                .addGap(26)
					                .addComponent(makeOfferButton, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
					                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					                .addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
					                .addGap(0, 23, Short.MAX_VALUE)))
					        .addGap(17)))
					.addContainerGap(38, 38));
			setSize(400, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setTransferBudget(double transferBudget) {
		this.transferBudget = transferBudget;
	}

	public double getTransferBudget() {
		return transferBudget;
	}

}
