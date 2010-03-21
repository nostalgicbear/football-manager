package ie.tippinst.jod.fm.gui.panels;

import ie.tippinst.jod.fm.app.Game;

import java.awt.Dimension;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class FinancesPanel extends javax.swing.JPanel {

	private static final long serialVersionUID = -1908641996717418250L;
	private JLabel balanceLabel;
	private JLabel transferBudgetValueLabel;
	private JLabel balanceValueLabel;
	private JLabel transferBudgetLable;
	private String club;
	private Game game;
	
	public FinancesPanel(String club) {
		super();
		game = Game.getInstance();
		this.club = club;
		initGUI();
		List<String> list = game.getClubFinances(club);
		balanceValueLabel.setText(list.get(0));
		transferBudgetValueLabel.setText(list.get(1));
	}
	
	private void initGUI() {
		try {
			GroupLayout thisLayout = new GroupLayout((JComponent)this);
			this.setLayout(thisLayout);
			setPreferredSize(new Dimension(400, 300));
			{
				balanceLabel = new JLabel();
				balanceLabel.setText("Balance:");
			}
			{
				transferBudgetLable = new JLabel();
				transferBudgetLable.setText("Transfer Budget:");
			}
			{
				balanceValueLabel = new JLabel();
			}
			{
				transferBudgetValueLabel = new JLabel();
			}
				thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
					.addContainerGap(95, 95)
					.addGroup(thisLayout.createParallelGroup()
					    .addComponent(balanceLabel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					    .addComponent(balanceValueLabel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(thisLayout.createParallelGroup()
					    .addGroup(thisLayout.createSequentialGroup()
					        .addComponent(transferBudgetLable, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
					    .addGroup(thisLayout.createSequentialGroup()
					        .addComponent(transferBudgetValueLabel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(145, Short.MAX_VALUE));
				thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
					.addContainerGap(53, 53)
					.addGroup(thisLayout.createParallelGroup()
					    .addGroup(thisLayout.createSequentialGroup()
					        .addComponent(balanceLabel, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
					        .addGap(0, 0, Short.MAX_VALUE))
					    .addComponent(transferBudgetLable, GroupLayout.Alignment.LEADING, 0, 132, Short.MAX_VALUE))
					.addGap(25)
					.addGroup(thisLayout.createParallelGroup()
					    .addComponent(balanceValueLabel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
					    .addComponent(transferBudgetValueLabel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(77, 77));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setClub(String club) {
		this.club = club;
	}

	public String getClub() {
		return club;
	}

}
