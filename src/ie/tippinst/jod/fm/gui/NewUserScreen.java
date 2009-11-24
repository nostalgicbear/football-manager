package ie.tippinst.jod.fm.gui;

import ie.tippinst.jod.fm.app.Game;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

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
			"Liverpool", "Manchester City", "Manchester United", "Portsmouth", "Stoke City", "Sunderland", "Tottenham Hotspur", "West Ham United",
			"Wigan Athletic", "Wolverhampton Wanderers"};
	
	public NewUserScreen(){
		super("Football Manager");
		game = game.getInstance();
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
		this.setLayout(new FlowLayout());
		this.getContentPane().add(firstNameLbl);
		this.getContentPane().add(firstNameTxt);
		this.getContentPane().add(surnameLbl);
		this.getContentPane().add(surnameTxt);
		this.getContentPane().add(dobLbl);
		this.getContentPane().add(dobDayCbo);
		this.getContentPane().add(dobMonthCbo);
		this.getContentPane().add(dobYearCbo);
		this.getContentPane().add(nationalityLbl);
		this.getContentPane().add(nationalityCbo);
		this.getContentPane().add(clubLbl);
		this.getContentPane().add(clubCbo);
		this.getContentPane().add(addUser);
		this.setSize(475,170);
		this.setLocationRelativeTo(null);
		
		addUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addNewUser(e);
			}
			
		});
	}
	
	private void addNewUser(ActionEvent ae){
		Calendar c = new GregorianCalendar();
		c.set((2009 - dobYearCbo.getSelectedIndex()), dobMonthCbo.getSelectedIndex(), (dobDayCbo.getSelectedIndex()+1));
		game.createNewUser(firstNameTxt.getText(), surnameTxt.getText(), c, (String) nationalityCbo.getSelectedItem(), (String) clubCbo.getSelectedItem());
		MainGame mg = new MainGame((String)clubCbo.getSelectedItem());
		mg.setVisible(true);
		this.setVisible(false);
	}

}
