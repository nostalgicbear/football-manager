package ie.tippinst.jod.fm.gui.panels;

import ie.tippinst.jod.fm.app.Game;
import ie.tippinst.jod.fm.gui.dialogs.ContractOffer;
import ie.tippinst.jod.fm.model.Message;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;


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
	private JButton acceptOfferButton;
	private JButton rejectOfferButton;
	private boolean showContractButtons = true;
	private boolean showOfferButtons = true;
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
				showContractButtons = false;
				displayMessage();
				Document d = messageTextPane.getDocument();
				try {
					d.insertString(d.getLength(), "Your offer has been withdrawn!", new SimpleAttributeSet());
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
			}			
		});
		
		offerContractButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showContractButtons = false;
				displayMessage();
				Document d = messageTextPane.getDocument();
				try {
					d.insertString(d.getLength(), "Your have offered a contract!", new SimpleAttributeSet());
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
				offerContract(e);
			}			
		});
		acceptOfferButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showOfferButtons = false;
				displayMessage();
				sellPlayer(e);
			}			
		});
		
		rejectOfferButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showOfferButtons = false;
				displayMessage();
				rejectOffer(e);
			}			
		});
	}
	
	private void rejectOffer(ActionEvent e) {
		String header = (String) messageList.getSelectedValue();
		header = header.replace(" bid for ", "/");
		int index = header.indexOf("/");
		String playerName = header.substring(0, index);
		String clubName = header.substring(index + 1);
		game.rejectOffer(playerName, clubName);		
	}

	private void sellPlayer(ActionEvent e) {
		String header = (String) messageList.getSelectedValue();
		header = header.replace(" bid for ", "/");
		int index = header.indexOf("/");
		String clubName = header.substring(0, index);
		String playerName = header.substring(index + 1);
		index = playerName.indexOf("(");
		playerName = playerName.substring(0, index - 1);
		System.out.println(playerName);
		System.out.println(clubName);
		game.sellPlayer(playerName, clubName);	
	}

	private void offerContract(ActionEvent ae){
		String header = (String) messageList.getSelectedValue();
		int index;
		for(index = 0; index < header.length(); index++){
			if(header.charAt(index) == '('){
				index--;
				break;
			}
		}
		header = header.substring(0, index);
		String temp = header.replace(" Accepted", "");
		int firstIndex = messageTextPane.getText().indexOf("€");
		int secondIndex = messageTextPane.getText().indexOf("offer");
		int value = Integer.parseInt(messageTextPane.getText().substring((firstIndex+1), (secondIndex - 1)));
		String player = temp.substring(10);
		ContractOffer contract = new ContractOffer(player, value);
		contract.setVisible(true);
	}
	
	private void displayMessage(){
		String header = ((String) messageList.getSelectedValue());
		int index;
		for(index = 0; index < header.length(); index++){
			if(header.charAt(index) == '('){
				index--;
				break;
			}
		}
		header = header.substring(0, index);
		SimpleAttributeSet heading = new SimpleAttributeSet();
		StyleConstants.setBold(heading, true);
		StyleConstants.setFontSize(heading, 20);
		Document d = messageTextPane.getDocument();
		try {
			d.remove(0, d.getLength());
			d.insertString(d.getLength(), header + "\n\n", heading);
			d.insertString(d.getLength(), game.getMessageBody(messageList.getSelectedIndex()) + "\n\n", new SimpleAttributeSet());
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		//messageTextPane.setText(header + "\n\n" + game.getMessageBody(messageList.getSelectedIndex()) + "\n\n");
		if((showContractButtons) && (messageTextPane.getText().contains("You now have permission to offer him a contract!")) 
				&& (game.getDate().get(Calendar.DAY_OF_MONTH) == game.getMessageDate(messageList.getSelectedIndex()).get(Calendar.DAY_OF_MONTH))){
			//game.getDate().compareTo(game.getMessageDate(messageList.getSelectedIndex())) == 0
			//System.out.println(game.getDate().getTime());
			//System.out.println(game.getMessageDate(messageList.getSelectedIndex()).getTime());
			messageTextPane.insertComponent(offerContractButton);
			messageTextPane.insertComponent(withdrawOfferButton);
		}
		else if((showOfferButtons) && (messageTextPane.getText().contains("bid to you")) 
				&& (game.getDate().get(Calendar.DAY_OF_MONTH) == game.getMessageDate(messageList.getSelectedIndex()).get(Calendar.DAY_OF_MONTH))){
			messageTextPane.insertComponent(acceptOfferButton);
			messageTextPane.insertComponent(rejectOfferButton);
		}
	}
	
	private void displayMessage(ListSelectionEvent ae){
		displayMessage();
	}
	
	private void initGUI() {
		offerContractButton = new JButton("Offer Contract");
		withdrawOfferButton = new JButton("Withdraw Offer");
		acceptOfferButton = new JButton("Accept");
		rejectOfferButton = new JButton("Reject");
		try {
			GroupLayout thisLayout = new GroupLayout((JComponent)this);
			this.setLayout(thisLayout);
			setPreferredSize(new Dimension(400, 300));
			{
				List<Message> list = game.getMessages();
				String[] data = new String[list.size()];
				for(int i = 0; i < data.length; i++){
					data[i] = list.get(i).getHeading() + " (" + list.get(i).getDate().get(Calendar.DAY_OF_MONTH) + "/"+ (list.get(i).getDate().get(Calendar.MONTH) + 1) + "/" + list.get(i).getDate().get(Calendar.YEAR) + ")";
				}
				/*Iterator<Message> i = list.iterator();
				while(i.hasNext()){
					System.out.println(i.next().getBody());
				}*/
				ListModel messageListModel = new DefaultComboBoxModel(data);
				messageList = new JList();
				messageList.setModel(messageListModel);
				messageListScrollPane = new JScrollPane(messageList);
				//messageList.setPreferredSize(new java.awt.Dimension(396, 92));
			}
			{
				messageTextPane = new JTextPane();
				messageTextPane.setText("No message selected");
				//messageTextPane.setPreferredSize(new java.awt.Dimension(397, 198));
				messageTextPane.setEditable(false);
				if(messageList.getModel().getSize() > 0){
					messageList.setSelectedIndex(0);
					displayMessage();
				}
			}
			
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
