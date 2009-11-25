package ie.tippinst.jod.fm.gui;

import ie.tippinst.jod.fm.app.Game;
import ie.tippinst.jod.fm.gui.panels.ClubInformationPanel;
import ie.tippinst.jod.fm.gui.panels.LeagueTablePanel;
import ie.tippinst.jod.fm.gui.panels.PlayerProfilePanel;
import ie.tippinst.jod.fm.gui.panels.SquadPanel;
import ie.tippinst.jod.fm.gui.panels.StaffPanel;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

public class MainGame extends JFrame {
	
	private static final long serialVersionUID = -4239639104320867731L;
	
	// Main Screen
	private JButton backButton;
    private JButton forwardButton;
    private JButton continueButton;
    private JMenu fileMenu;
    private JMenu managerMenu;
    private JMenu clubMenu;
    private JMenu leagueMenu;
    private JMenuBar menuBar;
    private JMenuItem newMenuItem;
    private JMenuItem loadMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem saveAsMenuItem;
    private JMenuItem optionsMenuItem;
    private JMenuItem exitMenuItem;
    private JMenuItem userProfileMenuItem;
    private JMenuItem inboxMenuItem;
    private JMenuItem shortlistMenuItem;
    private JMenuItem playerSearchMenuItem;
    private JMenuItem jobsMenuItem;
    private JMenuItem resignMenuItem;
    private JMenuItem squadMenuItem;
    private JMenuItem tacticsMenuItem;
    private JMenuItem fixturesMenuItem;
    private JMenuItem financesMenuItem;
    private JMenuItem informationMenuItem;
    private JMenuItem staffMenuItem;
    private JMenuItem confidenceMenuItem;
    private JMenuItem leagueTableMenuItem;
    private JPanel mainPanel;
    private JLabel date;
    private JPanel sidebar;
    
    private LeagueTablePanel leagueTablePanel;
    private SquadPanel squadPanel;
    private ClubInformationPanel clubInformationPanel;
    private StaffPanel staffPanel;
    private PlayerProfilePanel playerPanel;
    private JPanel leagueTableSidebar;
    private JPanel squadSidebar;
    private JButton viewSelectedClubButton;
    private JButton viewSquad;
    private JButton viewStaff;
    private JButton viewInformation;
    private JButton viewSelectedPlayerButton;
    
    private Game game;
    private String userClub;
    private String club;
    private String player;

    /** Creates new form MainGame */
    public MainGame(String userClub) {
    	super("Football Manager");
    	game = Game.getInstance();
    	this.userClub = this.club = userClub;
        initComponents();
        newMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				newGame(e);
			}
			
		});
        exitMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
        squadMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayUserSquad(e);
			}
			
		});
        informationMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayUserSquadInformation(e);
			}
			
		});
        staffMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayUserStaff(e);
			}
			
		});
        leagueTableMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayLeagueTable(e);
			}
			
		});
        
        backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				goBack(e);
			}
			
		});
        
        forwardButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				goForward(e);
			}
			
		});
        
        viewSquad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displaySquad(club);
			}
			
		});
        
        viewStaff.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayStaff(club);
			}
			
		});
        
        viewInformation.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayClubInformation(club);
			}
			
		});
        
        viewSelectedClubButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displaySquad(e);
			}
			
		});
        
        viewSelectedPlayerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayPlayer(e);
			}
			
		});
        
        this.setLocationRelativeTo(null);
    }
    
    private void goBack(ActionEvent ae){
    	((CardLayout) mainPanel.getLayout()).previous(mainPanel);
    	//forwardButton.setEnabled(true);
    }
    
    private void goForward(ActionEvent ae){
    	((CardLayout) mainPanel.getLayout()).next(mainPanel);
    }
    
    private void displaySquad(ActionEvent ae){
		club = (String) (leagueTablePanel.getjTable1().getValueAt(leagueTablePanel.getjTable1().getSelectedRow(), 0));
    	displaySquad(club);
	}
    
    private void displayPlayer(ActionEvent ae){
		player = (String) (squadPanel.getjTable1().getValueAt(squadPanel.getjTable1().getSelectedRow(), 0));
    	displayPlayer(player);
	}
    
    private void newGame(ActionEvent ae){
    	game.loadDatabase();
		NewUserScreen ns = new NewUserScreen();
		ns.setVisible(true);
		this.setVisible(false);
    }
    
    private void displayLeagueTable(ActionEvent ae){
    	leagueTablePanel = new LeagueTablePanel();
    	mainPanel.add(leagueTablePanel, "League Table");
    	((CardLayout) mainPanel.getLayout()).next(mainPanel);
    	sidebar.add(leagueTableSidebar, "League Table Sidebar");
    	((CardLayout) sidebar.getLayout()).next(sidebar);
    	//backButton.setEnabled(true);
	}
    
    private void displayPlayer(String player){
    	playerPanel = new PlayerProfilePanel(player);
    	mainPanel.add(playerPanel, "Player");
    	((CardLayout) mainPanel.getLayout()).next(mainPanel);
    	sidebar.add(squadSidebar, "Squad Sidebar");
    	((CardLayout) sidebar.getLayout()).next(sidebar);
    	//backButton.setEnabled(true);
    }
    
    private void displaySquad(String club){
    	squadPanel = new SquadPanel(club);
    	mainPanel.add(squadPanel, "Squad");
    	((CardLayout) mainPanel.getLayout()).next(mainPanel);
    	sidebar.add(squadSidebar, "Squad Sidebar");
    	((CardLayout) sidebar.getLayout()).next(sidebar);
    	//backButton.setEnabled(true);
    }
    
    private void displayUserSquad(ActionEvent ae){
    	displaySquad(userClub);
    	this.club = userClub;
	}
    
    private void displayClubInformation(String club){
    	clubInformationPanel = new ClubInformationPanel(club);
    	mainPanel.add(clubInformationPanel, "Club Information");
    	((CardLayout) mainPanel.getLayout()).next(mainPanel);
    	//backButton.setEnabled(true);
    }
    
    private void displayUserSquadInformation(ActionEvent ae){
    	displayClubInformation(userClub);
	}
    
    private void displayStaff(String club){
    	staffPanel = new StaffPanel(club);
    	mainPanel.add(staffPanel, "Staff");
    	((CardLayout) mainPanel.getLayout()).next(mainPanel);
    	//backButton.setEnabled(true);
    }
    
    private void displayUserStaff(ActionEvent ae){
    	displayStaff(userClub);
	}

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {

        backButton = new JButton();
        forwardButton = new JButton();
        continueButton = new JButton();
        menuBar = new JMenuBar();
        fileMenu = new JMenu();
        newMenuItem = new JMenuItem();
        loadMenuItem = new JMenuItem();
        saveMenuItem = new JMenuItem();
        saveAsMenuItem = new JMenuItem();
        exitMenuItem = new JMenuItem();
        squadMenuItem = new JMenuItem();
        informationMenuItem = new JMenuItem();
        staffMenuItem = new JMenuItem();
        leagueTableMenuItem = new JMenuItem();
        managerMenu = new JMenu();
        clubMenu = new JMenu();
        leagueMenu = new JMenu();
        date = new JLabel();
        mainPanel = new JPanel(new CardLayout());
        sidebar = new JPanel(new CardLayout());
        viewSelectedClubButton = new JButton();
        leagueTableSidebar = new JPanel();
        squadSidebar = new JPanel();
        viewSquad = new JButton();
        viewStaff = new JButton();
        viewInformation = new JButton();
        viewSelectedPlayerButton = new JButton();
        optionsMenuItem = new JMenuItem();
        userProfileMenuItem = new JMenuItem();
        inboxMenuItem = new JMenuItem();
        shortlistMenuItem = new JMenuItem();
        playerSearchMenuItem = new JMenuItem();
        jobsMenuItem = new JMenuItem();
        resignMenuItem = new JMenuItem();
        tacticsMenuItem = new JMenuItem();
        fixturesMenuItem = new JMenuItem();
        financesMenuItem = new JMenuItem();
        confidenceMenuItem = new JMenuItem();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        backButton.setText("Back");
        viewSelectedClubButton.setText("View Club");
        viewSquad.setText("Squad");
        viewStaff.setText("Staff");
        viewInformation.setText("Information");
        viewSelectedPlayerButton.setText("View Player");

        forwardButton.setText("Forward");
        forwardButton.setEnabled(false);

        continueButton.setText("Continue");
        
        displaySquad(userClub);
        backButton.setEnabled(false);
        
        Calendar c = game.getDate();        
        date.setText(c.get(Calendar.DATE) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR));

        fileMenu.setText("File");

        newMenuItem.setText("New");
        fileMenu.add(newMenuItem);

        loadMenuItem.setText("Load");
        fileMenu.add(loadMenuItem);

        saveMenuItem.setText("Save");
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setText("Save As...");
        fileMenu.add(saveAsMenuItem);
        
        optionsMenuItem.setText("Options");
        fileMenu.add(optionsMenuItem);

        exitMenuItem.setText("Exit");
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        managerMenu.setText("Manager");
        
        userProfileMenuItem.setText("Profile");
        managerMenu.add(userProfileMenuItem);
        
        inboxMenuItem.setText("Inbox");
        managerMenu.add(inboxMenuItem);
        
        shortlistMenuItem.setText("Shortlist");
        managerMenu.add(shortlistMenuItem);
        
        playerSearchMenuItem.setText("Player Search");
        managerMenu.add(playerSearchMenuItem);
        
        jobsMenuItem.setText("Jobs");
        managerMenu.add(jobsMenuItem);
        
        resignMenuItem.setText("Resign");
        managerMenu.add(resignMenuItem);
        
        menuBar.add(managerMenu);
        
        clubMenu.setText(userClub);
        
        squadMenuItem.setText("Squad");
        clubMenu.add(squadMenuItem);
        
        tacticsMenuItem.setText("Tactics");
        clubMenu.add(tacticsMenuItem);
        
        fixturesMenuItem.setText("Fixtures");
        clubMenu.add(fixturesMenuItem);
        
        financesMenuItem.setText("Finances");
        clubMenu.add(financesMenuItem);
        
        informationMenuItem.setText("Information");
        clubMenu.add(informationMenuItem);
        
        staffMenuItem.setText("Staff");
        clubMenu.add(staffMenuItem);
        
        confidenceMenuItem.setText("Confidence");
        clubMenu.add(confidenceMenuItem);
        
        menuBar.add(clubMenu);
        
        leagueMenu.setText("League");
        
        leagueTableMenuItem.setText("Table");
        leagueMenu.add(leagueTableMenuItem);
        
        menuBar.add(leagueMenu);

        setJMenuBar(menuBar);
        
        javax.swing.GroupLayout squadSidebarLayout = new javax.swing.GroupLayout(squadSidebar);
        squadSidebar.setLayout(squadSidebarLayout);
        squadSidebarLayout.setHorizontalGroup(
            squadSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(squadSidebarLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(squadSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                	.addComponent(viewSelectedPlayerButton)
                	.addComponent(viewInformation)
                    .addComponent(viewStaff)
                    .addComponent(viewSquad))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        squadSidebarLayout.setVerticalGroup(
            squadSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(squadSidebarLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(viewSquad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(viewStaff)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(viewInformation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(viewSelectedPlayerButton)
                .addContainerGap(190, Short.MAX_VALUE))
        );
        
        javax.swing.GroupLayout leagueTableSidebarLayout = new javax.swing.GroupLayout(leagueTableSidebar);
        leagueTableSidebar.setLayout(leagueTableSidebarLayout);
        leagueTableSidebarLayout.setHorizontalGroup(
            leagueTableSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leagueTableSidebarLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(viewSelectedClubButton)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        leagueTableSidebarLayout.setVerticalGroup(
            leagueTableSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leagueTableSidebarLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(viewSelectedClubButton)
                .addContainerGap(224, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(backButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(forwardButton)
                .addGap(60, 60, 60)
                .addComponent(date)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(continueButton, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sidebar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            		.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sidebar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                    .addComponent(continueButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                    .addComponent(date)
                    .addComponent(forwardButton, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }
}
