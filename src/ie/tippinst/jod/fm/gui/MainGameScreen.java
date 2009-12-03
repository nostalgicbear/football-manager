package ie.tippinst.jod.fm.gui;

import ie.tippinst.jod.fm.app.Game;
import ie.tippinst.jod.fm.gui.panels.ClubInformationPanel;
import ie.tippinst.jod.fm.gui.panels.FixturesPanel;
import ie.tippinst.jod.fm.gui.panels.LeagueFixturesPanel;
import ie.tippinst.jod.fm.gui.panels.LeagueTablePanel;
import ie.tippinst.jod.fm.gui.panels.PlayerAttributesPanel;
import ie.tippinst.jod.fm.gui.panels.PlayerContractPanel;
import ie.tippinst.jod.fm.gui.panels.PlayerProfilePanel;
import ie.tippinst.jod.fm.gui.panels.PlayerSearchPanel;
import ie.tippinst.jod.fm.gui.panels.SquadPanel;
import ie.tippinst.jod.fm.gui.panels.StaffPanel;
import ie.tippinst.jod.fm.gui.panels.StaffProfilePanel;
import ie.tippinst.jod.fm.gui.panels.StaffSearchPanel;

import java.awt.CardLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

public class MainGameScreen extends JFrame {
	
	private static final long serialVersionUID = -4239639104320867731L;
	
	// Main Screen Components
	private JMenuBar menuBar;	
	private JMenu fileMenu;
	private JMenuItem newMenuItem;
    private JMenuItem loadMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem saveAsMenuItem;
    private JMenuItem optionsMenuItem;
    private JMenuItem exitMenuItem;
    private JMenu managerMenu;
    private JMenuItem userProfileMenuItem;
    private JMenuItem inboxMenuItem;
    private JMenuItem shortlistMenuItem;
    private JMenuItem playerSearchMenuItem;
    private JMenuItem staffSearchMenuItem;
    private JMenuItem jobsMenuItem;
    private JMenuItem resignMenuItem;
    private JMenu clubMenu;
    private JMenuItem squadMenuItem;
    private JMenuItem tacticsMenuItem;
    private JMenuItem fixturesMenuItem;
    private JMenuItem financesMenuItem;
    private JMenuItem informationMenuItem;
    private JMenuItem staffMenuItem;
    private JMenuItem confidenceMenuItem;
    private JMenu leagueMenu;
    private JMenuItem leagueTableMenuItem;    
	private JTabbedPane clubPanel;
	private JTabbedPane playerPanel;
	private JTabbedPane leaguePanel;
    private JButton continueButton;    
    private JPanel mainPanel;
    private JPanel sidePanel;
    private JLabel date;
    
    // Other panels for main panel
    private LeagueTablePanel leagueTablePanel;
    private LeagueFixturesPanel leagueFixturesPanel;
    private SquadPanel squadPanel;
    private FixturesPanel fixturesPanel;
    private ClubInformationPanel clubInformationPanel;
    private StaffPanel staffPanel;
    private PlayerProfilePanel playerProfilePanel;
    private PlayerAttributesPanel playerAttributesPanel;
    private PlayerContractPanel playerContractPanel;
    private StaffProfilePanel staffProfilePanel;
    private PlayerSearchPanel playerSearchPanel;
    private StaffSearchPanel staffSearchPanel;
    
    //Other panels for side panel and their buttons
    private JPanel leagueTableSidePanel;
    private JButton viewSelectedClubButton;
    private JPanel squadSidePanel;
    private JButton viewSelectedPlayerButton;
    private JButton viewStaffProfileButton;
    
    private Game game;
    private String userClub;
    private String club;
    private String player;
    private String user;
    private boolean processFixtures;

    /*Creates new MainGameScreen */
    public MainGameScreen(String userClub, String user) {
    	super("Football Manager");
    	game = Game.getInstance();
    	this.userClub = this.club = userClub;
    	this.user = user;
        initComponents();
        
        // Event listener for File -> New Menu Item
        newMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				newGame(e);
			}
			
		});
        
     // Event listener for File -> Exit Menu Item
        exitMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
        
        // Event listener for Manager -> Profile Menu Item
        userProfileMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayUserProfile(e);
			}
			
		});
        
     // Event listener for Manager -> Player Search Menu Item
        playerSearchMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayPlayers(e);
			}
			
		});
        
     // Event listener for Manager -> Staff Search Menu Item
        staffSearchMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayAllStaff(e);
			}
			
		});
        
        // Event listener for Team -> Squad Menu Item
        squadMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayUserSquad(e);
			}
			
		});
        
        // Event listener for Team -> Information Menu Item
        informationMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayUserSquadInformation(e);
			}
			
		});
        
     // Event listener for Team -> Staff Menu Item
        staffMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayUserStaff(e);
			}
			
		});
        
     // Event listener for League -> Table Menu Item
        leagueTableMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayLeagueTable(e);
			}
			
		});
        
        // Event listener for view club button
        viewSelectedClubButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayClub(e);
			}
			
		});
        
        // Event listener for view player button
        viewSelectedPlayerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayPlayer(e);
			}
			
		});
        
        // Event listener for view staff member button
        viewStaffProfileButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayStaffProfile(e);
			}
			
		});
        
        // Event listener for view continue button
        continueButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				continueGame(e);
			}
			
		});
        
        // Centre window on screen
        this.setLocationRelativeTo(null);
    }
    
    /*Initialise all GUI components*/
    private void initComponents() {

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        newMenuItem = new JMenuItem("New");
        loadMenuItem = new JMenuItem("Load");
        saveMenuItem = new JMenuItem("Save");
        saveAsMenuItem = new JMenuItem("Save as...");
        optionsMenuItem = new JMenuItem("Options");
        exitMenuItem = new JMenuItem("Exit");
        managerMenu = new JMenu(user);
        userProfileMenuItem = new JMenuItem("Profile");
        inboxMenuItem = new JMenuItem("Inbox");
        shortlistMenuItem = new JMenuItem("Shortlist");
        playerSearchMenuItem = new JMenuItem("Player Search");
        staffSearchMenuItem = new JMenuItem("Staff Search");
        jobsMenuItem = new JMenuItem("Jobs");
        resignMenuItem = new JMenuItem("Resign");
        clubMenu = new JMenu(userClub);
        squadMenuItem = new JMenuItem("Squad");
        informationMenuItem = new JMenuItem("Information");
        staffMenuItem = new JMenuItem("Staff");
        tacticsMenuItem = new JMenuItem("Tactics");
        fixturesMenuItem = new JMenuItem("Fixtures");
        financesMenuItem = new JMenuItem("Finances");
        confidenceMenuItem = new JMenuItem("Confidence");
        leagueMenu = new JMenu("League");
        leagueTableMenuItem = new JMenuItem("Table");
        date = new JLabel();
        mainPanel = new JPanel(new CardLayout());
        sidePanel = new JPanel(new CardLayout());
        viewSelectedClubButton = new JButton("View Club");
        leagueTableSidePanel = new JPanel();
        squadSidePanel = new JPanel();
        viewSelectedPlayerButton = new JButton("View Player");
        viewStaffProfileButton = new JButton("View NonPlayer");
        clubPanel = new JTabbedPane();
        playerPanel = new JTabbedPane();
        leaguePanel = new JTabbedPane();
        continueButton = new JButton("Continue");
        processFixtures = false;

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setExtendedState(this.getExtendedState()|JFrame.MAXIMIZED_BOTH);
        
        //Display the users squad as the initial screen
        displayClub(userClub);
        
        //Display the date
        Calendar c = game.getDate();        
        date.setText(c.get(Calendar.DATE) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR));

        // Add menus to menubar and menu items to menus
        fileMenu.add(newMenuItem);
        fileMenu.add(loadMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(saveAsMenuItem);        
        fileMenu.add(optionsMenuItem);
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);        
        managerMenu.add(userProfileMenuItem);        
        managerMenu.add(inboxMenuItem);        
        managerMenu.add(shortlistMenuItem);        
        managerMenu.add(playerSearchMenuItem);        
        managerMenu.add(staffSearchMenuItem);        
        managerMenu.add(jobsMenuItem);        
        managerMenu.add(resignMenuItem);        
        menuBar.add(managerMenu);        
        clubMenu.add(squadMenuItem);        
        clubMenu.add(tacticsMenuItem);        
        clubMenu.add(fixturesMenuItem);        
        clubMenu.add(financesMenuItem);        
        clubMenu.add(informationMenuItem);        
        clubMenu.add(staffMenuItem);        
        clubMenu.add(confidenceMenuItem);        
        menuBar.add(clubMenu);        
        leagueMenu.add(leagueTableMenuItem);        
        menuBar.add(leagueMenu);
        setJMenuBar(menuBar);
        
        // Layout squad side pane        
        GroupLayout squadSidePanelLayout = new GroupLayout(squadSidePanel);
        squadSidePanel.setLayout(squadSidePanelLayout);
        squadSidePanelLayout.setHorizontalGroup(
            squadSidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(squadSidePanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(squadSidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                	.addComponent(viewStaffProfileButton)
                	.addComponent(viewSelectedPlayerButton))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        squadSidePanelLayout.setVerticalGroup(
            squadSidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(squadSidePanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(viewSelectedPlayerButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(viewStaffProfileButton)
                .addContainerGap(190, Short.MAX_VALUE))
        );
        
        // Layout league table side panel
        GroupLayout leagueTableSidebarLayout = new GroupLayout(leagueTableSidePanel);
        leagueTableSidePanel.setLayout(leagueTableSidebarLayout);
        leagueTableSidebarLayout.setHorizontalGroup(
            leagueTableSidebarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(leagueTableSidebarLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(viewSelectedClubButton)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        leagueTableSidebarLayout.setVerticalGroup(
            leagueTableSidebarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(leagueTableSidebarLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(viewSelectedClubButton)
                .addContainerGap(224, Short.MAX_VALUE))
        );
        
        // Layout window
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(date)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(continueButton, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sidePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(sidePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mainPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(continueButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                    .addComponent(date)))
        );

        //pack();
    }
    
    /*Display Player Search Screen*/
    private void continueGame(ActionEvent ae){
    	DateFormat format = new SimpleDateFormat("dd-MMM-yy");
		String date;
    	if(game.continueGame(processFixtures)){
    		date = format.format(game.getDate().getTime()).toUpperCase();
    		displayLeagueFixtures(date);
    		processFixtures = true;
    	}
    	else if(processFixtures){
    		date = format.format(game.getDate().getTime()).toUpperCase();
    		displayLeagueFixtures(date);
    		processFixtures = false;
    	}
    	
		this.date.setText((game.getDate().get(Calendar.DATE) + "/" + (game.getDate().get(Calendar.MONTH) + 1) + "/" + game.getDate().get(Calendar.YEAR)));
    }
    
    /*Display Player Search Screen*/
    private void displayPlayers(ActionEvent ae){
    	playerSearchPanel = new PlayerSearchPanel();
        mainPanel.add(playerSearchPanel, "Player Search");
        ((CardLayout) mainPanel.getLayout()).show(mainPanel, "Player Search");
        sidePanel.add(squadSidePanel, "Squad Sidebar");
    	((CardLayout) sidePanel.getLayout()).show(sidePanel, "Squad Sidebar"); 
    }
    
    /*Display Staff Search Screen*/
    private void displayAllStaff(ActionEvent ae){
    	staffSearchPanel = new StaffSearchPanel();
        mainPanel.add(staffSearchPanel, "Staff Search");
        ((CardLayout) mainPanel.getLayout()).show(mainPanel, "Staff Search");
        sidePanel.add(squadSidePanel, "Squad Sidebar");
    	((CardLayout) sidePanel.getLayout()).show(sidePanel, "Squad Sidebar"); 
    }
    
    /*Display Users Profile*/
    private void displayUserProfile(ActionEvent ae){
    	displayStaffProfile(user);
    }
    
    /*Get Club to Display*/
    private void displayClub(ActionEvent ae){
		club = (String) (leagueTablePanel.getLeagueTable().getValueAt(leagueTablePanel.getLeagueTable().getSelectedRow(), 1));
    	displayClub(club);
	}
    
    /*Get Player to Display*/
    private void displayPlayer(ActionEvent ae){
		player = (String) (squadPanel.getSquadTable().getValueAt(squadPanel.getSquadTable().getSelectedRow(), 0));
    	displayPlayer(player);
	}
    
    /*Display staff member*/
    private void displayStaffProfile(String staffMember){
    	staffProfilePanel = new StaffProfilePanel(staffMember);
    	mainPanel.add(staffProfilePanel, "Staff Profile");
    	((CardLayout) mainPanel.getLayout()).show(mainPanel, "Staff Profile");
    	sidePanel.add(squadSidePanel, "Squad Sidebar");
    	((CardLayout) sidePanel.getLayout()).show(sidePanel, "Squad Sidebar");
    }
    
    /*Get staff profile to display*/
    private void displayStaffProfile(ActionEvent ae){
		displayStaffProfile((String) (staffPanel.getStaffTable().getValueAt(staffPanel.getStaffTable().getSelectedRow(), 0)));
	}
    
    /*Creates a new game*/
    private void newGame(ActionEvent ae){
    	game.loadDatabase();
		NewUserScreen ns = new NewUserScreen();
		ns.setVisible(true);
		this.dispose();
    }
    
    /*Display the league table*/
    private void displayLeagueTable(ActionEvent ae){
    	leagueTablePanel = new LeagueTablePanel();
    	DateFormat format = new SimpleDateFormat("dd-MMM-yy");
		String date = format.format(game.getDate().getTime()).toUpperCase();
    	leagueFixturesPanel = new LeagueFixturesPanel(date);
    	leaguePanel.removeAll();
    	leaguePanel.add("Table", leagueTablePanel);
    	leaguePanel.add("Fixtures", leagueFixturesPanel);
    	leaguePanel.setSelectedIndex(0);
    	mainPanel.add(leaguePanel, "League");
    	((CardLayout) mainPanel.getLayout()).show(mainPanel, "League");
    	sidePanel.add(leagueTableSidePanel, "League Table Sidebar");
    	((CardLayout) sidePanel.getLayout()).show(sidePanel, "League Table Sidebar");
	}
    
    /*Display the league table*/
    private void displayLeagueFixtures(String date){
    	leagueTablePanel = new LeagueTablePanel();
    	leagueFixturesPanel = new LeagueFixturesPanel(date);
    	leaguePanel.removeAll();
    	leaguePanel.add("Table", leagueTablePanel);
    	leaguePanel.add("Fixtures", leagueFixturesPanel);
    	leaguePanel.setSelectedIndex(1);
    	mainPanel.add(leaguePanel, "League");
    	((CardLayout) mainPanel.getLayout()).show(mainPanel, "League");
    	sidePanel.add(leagueTableSidePanel, "League Table Sidebar");
    	((CardLayout) sidePanel.getLayout()).show(sidePanel, "League Table Sidebar");
	}
    
    /*Display player profile*/
    private void displayPlayer(String player){
    	playerProfilePanel = new PlayerProfilePanel(player);
    	playerAttributesPanel = new PlayerAttributesPanel(player);
    	playerContractPanel = new PlayerContractPanel(player);
    	playerPanel.removeAll();
    	playerPanel.add("Profile", playerProfilePanel);
    	playerPanel.add("Attributes", playerAttributesPanel);
    	playerPanel.add("Contract", playerContractPanel);
    	playerPanel.setSelectedIndex(0);;
    	mainPanel.add(playerPanel, "Player");
    	((CardLayout) mainPanel.getLayout()).show(mainPanel, "Player");
    	sidePanel.add(squadSidePanel, "Squad Sidebar");
    	((CardLayout) sidePanel.getLayout()).show(sidePanel, "Squad Sidebar");
    }
    
    /*Display particular club*/
    private void displayClub(String club){
    	squadPanel = new SquadPanel(club);
    	fixturesPanel = new FixturesPanel(club);
    	staffPanel = new StaffPanel(club);
    	clubInformationPanel = new ClubInformationPanel(club);
    	clubPanel.removeAll();
    	clubPanel.addTab("Squad", squadPanel);
    	clubPanel.addTab("Fixtures", fixturesPanel);
        clubPanel.addTab("Staff", staffPanel);
        clubPanel.addTab("Information", clubInformationPanel);
        clubPanel.setSelectedIndex(0);
        mainPanel.add(clubPanel, "Club");
        ((CardLayout) mainPanel.getLayout()).show(mainPanel, "Club");
        sidePanel.add(squadSidePanel, "Squad Sidebar");
    	((CardLayout) sidePanel.getLayout()).show(sidePanel, "Squad Sidebar"); 
    }
    
    private void displayUserSquad(ActionEvent ae){
    	displayClub(userClub);
    	this.club = userClub;
	}
    
    private void displayClubInformation(String club){
    	squadPanel = new SquadPanel(club);
    	staffPanel = new StaffPanel(club);
    	clubInformationPanel = new ClubInformationPanel(club);
    	//mainPanel.add(squadPanel, "Squad");
    	//((CardLayout) mainPanel.getLayout()).next(mainPanel);
    	clubPanel.removeAll();
    	clubPanel.addTab("Squad", squadPanel);
        clubPanel.addTab("Staff", staffPanel);
        clubPanel.addTab("Information", clubInformationPanel);
        clubPanel.setSelectedIndex(2);
        mainPanel.add(clubPanel, "Club");
        ((CardLayout) mainPanel.getLayout()).show(mainPanel, "Club");
        sidePanel.add(squadSidePanel, "Squad Sidebar");
    	((CardLayout) sidePanel.getLayout()).show(sidePanel, "Squad Sidebar");
    }
    
    private void displayUserSquadInformation(ActionEvent ae){
    	displayClubInformation(userClub);
	}
    
    private void displayStaff(String club){
    	squadPanel = new SquadPanel(club);
    	staffPanel = new StaffPanel(club);
    	clubInformationPanel = new ClubInformationPanel(club);
    	clubPanel.removeAll();
    	clubPanel.addTab("Squad", squadPanel);
        clubPanel.addTab("Staff", staffPanel);
        clubPanel.addTab("Information", clubInformationPanel);
        clubPanel.setSelectedIndex(1);
        mainPanel.add(clubPanel, "Club");
        ((CardLayout) mainPanel.getLayout()).show(mainPanel, "Club");
        sidePanel.add(squadSidePanel, "Squad Sidebar");
    	((CardLayout) sidePanel.getLayout()).show(sidePanel, "Squad Sidebar");
    }
    
    private void displayUserStaff(ActionEvent ae){
    	displayStaff(userClub);
	}
}
