package ie.tippinst.jod.fm.gui;

import ie.tippinst.jod.fm.app.Game;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.border.BevelBorder;


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
public class NewUserScreen extends JFrame {
	private static final long serialVersionUID = -4736216712349129748L;
	private JLabel firstNameLbl;
	private JLabel surnameLbl;
	private JTextField firstNameTxt;
	private JTextField surnameTxt;
	private JLabel dobLbl;
	private JComboBox dobDayCbo;
	private JComboBox dobMonthCbo;
	private JComboBox dobYearCbo;
	private JLabel nationalityLbl;
	private JComboBox nationalityCbo;
	private JLabel clubLbl;
	private JComboBox clubCbo;
	private JButton addUser;
	private Game game;
	private String [] days = {"1", "2", "3", "4", "5", "6" ,"7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
	private String [] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	private String [] years = {"2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996",
			"1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980"};
	private String [] clubs = {"Arsenal", "Aston Villa", "Birmingham City", "Blackburn Rovers", "Bolton Wanderers", "Burnley", "Chelsea", "Everton", "Fulham", "Hull City",
			"Liverpool", "Manchester City", "Manchester United", "Portsmouth", "Stoke City", "Sunderland", "Tottenham Hotspur", "West Ham United", "Wigan Athletic", "Wolverhampton Wanderers"};
	
	public NewUserScreen(){
		super("Football Manager");
		game = Game.getInstance();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		firstNameLbl = new JLabel("First Name:");
		surnameLbl = new JLabel("Surname:");
		firstNameTxt = new JTextField(10);
		surnameTxt = new JTextField(15);
		dobLbl = new JLabel("Date of Birth:");
		dobDayCbo = new JComboBox(days);
		dobMonthCbo = new JComboBox(months);
		dobYearCbo = new JComboBox(years);
		nationalityLbl = new JLabel("Nationality:");
		nationalityCbo = new JComboBox(game.getNationNames().toArray());
		clubLbl = new JLabel("Select Club to Manage:");
		clubCbo = new JComboBox(clubs);
		addUser = new JButton("Add New User");
		firstNameLbl.setFont(new java.awt.Font("Verdana",1,12));
		surnameLbl.setFont(new java.awt.Font("Verdana",1,12));
		dobLbl.setFont(new java.awt.Font("Verdana",1,12));
		nationalityLbl.setFont(new java.awt.Font("Verdana",1,12));
		clubLbl.setFont(new java.awt.Font("Verdana",1,12));
		addUser.setFont(new java.awt.Font("Verdana",1,12));
		addUser.setBackground(Color.LIGHT_GRAY);
		addUser.setForeground(Color.BLACK);
		//addUser.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		firstNameTxt.setFont(new java.awt.Font("Verdana",0,12));
		surnameTxt.setFont(new java.awt.Font("Verdana",0,12));
		dobDayCbo.setFont(new java.awt.Font("Verdana",0,12));
		dobMonthCbo.setFont(new java.awt.Font("Verdana",0,12));
		dobYearCbo.setFont(new java.awt.Font("Verdana",0,12));
		nationalityCbo.setFont(new java.awt.Font("Verdana",0,12));
		clubCbo.setFont(new java.awt.Font("Verdana",0,12));
		init();
		pack();

		this.setLocationRelativeTo(null);
		//getContentPane().setBackground(Color.LIGHT_GRAY);
		this.setResizable(false);
		
		addUser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addNewUser(e);
				}
			});
		}
	
	private void init(){
		GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
		getContentPane().setLayout(thisLayout);
		thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
			.addContainerGap()
			.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			    .addComponent(firstNameTxt, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			    .addComponent(firstNameLbl, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
			.addGap(17)
			.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(surnameLbl, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(surnameTxt, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
			.addGap(17)
			.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			    .addComponent(dobDayCbo, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			    .addComponent(dobLbl, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			    .addComponent(dobMonthCbo, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			    .addComponent(dobYearCbo, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
			.addGap(20)
			.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			    .addComponent(nationalityLbl, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			    .addComponent(nationalityCbo, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
			.addGap(20)
			.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			    .addComponent(clubCbo, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			    .addComponent(clubLbl, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
			.addGap(24)
			.addComponent(addUser, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
			.addContainerGap(27, Short.MAX_VALUE));
		thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
			.addContainerGap()
			.addGroup(thisLayout.createParallelGroup()
			    .addGroup(GroupLayout.Alignment.CENTER, thisLayout.createSequentialGroup()
			        .addComponent(firstNameLbl, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
			        .addGap(5)
			        .addComponent(firstNameTxt, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
			    .addGroup(GroupLayout.Alignment.CENTER, thisLayout.createSequentialGroup()
			        .addComponent(surnameLbl, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
			        .addGap(5)
			        .addComponent(surnameTxt, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
			    .addGroup(GroupLayout.Alignment.CENTER, thisLayout.createSequentialGroup()
			        .addComponent(dobLbl, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
			        .addGap(5)
			        .addComponent(dobDayCbo, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
			        .addGap(5)
			        .addComponent(dobMonthCbo, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
			        .addGap(5)
			        .addComponent(dobYearCbo, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
			    .addGroup(GroupLayout.Alignment.CENTER, thisLayout.createSequentialGroup()
			        .addComponent(nationalityLbl, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
			        .addGap(5)
			        .addComponent(nationalityCbo, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
			    .addGroup(GroupLayout.Alignment.CENTER, thisLayout.createSequentialGroup()
			        .addComponent(clubLbl, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
			        .addGap(5)
			        .addComponent(clubCbo, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
			     .addGroup(GroupLayout.Alignment.CENTER, thisLayout.createSequentialGroup()
			        .addComponent(addUser, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
			.addContainerGap());
	}
	
	private void addNewUser(ActionEvent ae){
		if(firstNameTxt.getText().equals("") || surnameTxt.getText().equals("")){
    		JOptionPane.showMessageDialog(this, "You need to enter a first name and surname!");
		}
		else{
			Calendar c = new GregorianCalendar();
			c.set((2009 - dobYearCbo.getSelectedIndex()), dobMonthCbo.getSelectedIndex(), (dobDayCbo.getSelectedIndex()+1));
			game.createNewUser(firstNameTxt.getText(), surnameTxt.getText(), c, (String) nationalityCbo.getSelectedItem(), (String) clubCbo.getSelectedItem());
			MainGameScreen mg = new MainGameScreen((String)clubCbo.getSelectedItem(), firstNameTxt.getText() + " " + surnameTxt.getText());
			mg.setVisible(true);
			this.setVisible(false);
		}
	}
} 