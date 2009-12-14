package ie.tippinst.jod.fm.gui.panels;

import ie.tippinst.jod.fm.app.Game;
import ie.tippinst.jod.fm.model.Message;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


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
public class InboxPanel extends JPanel {
	private JList messageList;
	private JTextPane messageTextPane;
	private JScrollPane messageListScrollPane;
	private JButton offerContractButton;
	private JButton withdrawOfferButton;
	private Game game;
	
	public InboxPanel() {
		super();
		game = Game.getInstance();
		initGUI();
		
		messageList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				displayMessage(e);				
			}
			
		});
		
		withdrawOfferButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				messageTextPane.remove(offerContractButton);
				messageTextPane.remove(withdrawOfferButton);
				messageTextPane.setText(messageTextPane.getText() + "Your offer has been withdrawn!");
			}
			
		});
	}
	
	private void displayMessage(ListSelectionEvent ae){
		messageTextPane.setText((String) messageList.getSelectedValue() + "\n\n" + game.getMessageBody((String) messageList.getSelectedValue()) + "\n\n");
		if(messageTextPane.getText().contains("You now have permission to offer him a contract!")){
			messageTextPane.insertComponent(offerContractButton);
			messageTextPane.insertComponent(withdrawOfferButton);
		}
	}
	
	private void initGUI() {
		offerContractButton = new JButton("Offer Contract");
		withdrawOfferButton = new JButton("Withdraw Offer");
		try {
			GroupLayout thisLayout = new GroupLayout((JComponent)this);
			this.setLayout(thisLayout);
			setPreferredSize(new Dimension(400, 300));
			{
				List<Message> list = game.getMessages();
				String [] data = new String[list.size()];
				for(int i = 0; i < data.length; i++){
					data[i] = list.get(i).getHeading();
				}
				messageListScrollPane = new JScrollPane();
				ListModel messageListModel = new DefaultComboBoxModel(data);
				messageList = new JList();
				messageList.setModel(messageListModel);
				messageList.setPreferredSize(new java.awt.Dimension(396, 92));
			}
			{
				messageTextPane = new JTextPane();
				messageTextPane.setText("No message selected");
				messageTextPane.setPreferredSize(new java.awt.Dimension(397, 198));
				messageTextPane.setEditable(false);
			}
			
			messageListScrollPane.setViewportView(messageList);
			
				thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
			.addContainerGap(2, 2)
			.addGroup(thisLayout.createParallelGroup()
			    .addComponent(messageListScrollPane, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 1000, GroupLayout.PREFERRED_SIZE)
			    .addComponent(messageTextPane, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 1000, GroupLayout.PREFERRED_SIZE))
			.addContainerGap(1, 1));
				thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
			.addContainerGap(5, 5)
			.addComponent(messageListScrollPane, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addComponent(messageTextPane, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
