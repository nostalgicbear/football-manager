package ie.tippinst.jod.fm.gui;

import ie.tippinst.jod.fm.app.Game;
import ie.tippinst.jod.fm.gui.dialogs.ContractOffer;
import ie.tippinst.jod.fm.gui.dialogs.TransferOffer;
import ie.tippinst.jod.fm.gui.panels.ClubInformationPanel;
import ie.tippinst.jod.fm.gui.panels.FinancesPanel;
import ie.tippinst.jod.fm.gui.panels.FixturesPanel;
import ie.tippinst.jod.fm.gui.panels.InboxPanel;
import ie.tippinst.jod.fm.gui.panels.LeagueFixturesPanel;
import ie.tippinst.jod.fm.gui.panels.LeagueTablePanel;
import ie.tippinst.jod.fm.gui.panels.PlayerAttributesPanel;
import ie.tippinst.jod.fm.gui.panels.PlayerContractPanel;
import ie.tippinst.jod.fm.gui.panels.PlayerProfilePanel;
import ie.tippinst.jod.fm.gui.panels.PlayerSearchPanel;
import ie.tippinst.jod.fm.gui.panels.ShortlistPanel;
import ie.tippinst.jod.fm.gui.panels.SquadPanel;
import ie.tippinst.jod.fm.gui.panels.StaffPanel;
import ie.tippinst.jod.fm.gui.panels.StaffProfilePanel;
import ie.tippinst.jod.fm.gui.panels.StaffSearchPanel;
import ie.tippinst.jod.fm.model.Match;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

public class MainGameScreen extends JFrame {
	
	private static final long serialVersionUID = -4239639104320867731L;
	private static MainGameScreen mg = null;
	
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
    private JMenuItem leagueFixturesMenuItem;
    private JMenu worldMenu;
    private JMenuItem englandMenuItem;
    private JMenuItem barclaysPremierLeagueMenuItem;
    private JMenuItem cocaColaChampionshipMenuItem;
    private JMenuItem cocaColaLeagueOneMenuItem;
    private JMenuItem cocaColaLeagueTwoMenuItem;
    private JMenuItem blueSquarePremierLeagueMenuItem;
	private JTabbedPane clubPanel;
	private JTabbedPane playerPanel;
	private JTabbedPane leaguePanel;
    private JButton continueButton;    
    private JPanel mainPanel;
    private JPanel sidePanel;
    private JLabel date;
    
    private JToolBar toolbar;
    private JButton newButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton inboxButton;
    private JButton playerSearchButton;
    private JButton shortlistButton;
    private JButton squadButton;
    private JButton fixturesButton;
    private JButton financesButton;
    private JButton leagueButton;

    // Other panels for main panel
    private LeagueTablePanel leagueTablePanel;
    private LeagueFixturesPanel leagueFixturesPanel;
    private SquadPanel squadPanel;
    private FixturesPanel fixturesPanel;
    private ClubInformationPanel clubInformationPanel;
    private StaffPanel staffPanel;
    private FinancesPanel financesPanel;
    private PlayerProfilePanel playerProfilePanel;
    private PlayerAttributesPanel playerAttributesPanel;
    private PlayerContractPanel playerContractPanel;
    private StaffProfilePanel staffProfilePanel;
    private PlayerSearchPanel playerSearchPanel;
    private StaffSearchPanel staffSearchPanel;
    private InboxPanel inboxPanel;
    private ShortlistPanel shortlistPanel;
    
    //Other panels for side panel and their buttons
    private JPanel leagueTableSidePanel;
    private JButton viewSelectedClubButton;
    private JPanel squadSidePanel;
    private JButton viewSelectedPlayerButton;
    private JButton viewStaffProfileButton;
    private JPanel playerSidePanel;
    private JButton makeOfferForPlayerButton;
    private JButton addToShortlistButton;
    private JButton scoutPlayerButton;
    private JPanel userPlayerSidePanel;
    private JButton setTransferStatusButton;
    private JButton offerNewContractButton;
    private JPanel playerSearchSidePanel;
    private JButton viewSelectedPlayerForPlayerSearchButton;
    private JPanel nonPlayerSidePanel;
    private JPanel inboxSidePanel;
    
    private Game game;
    private String userClub;
    private String club;
    private String player;
    private String user;
    private boolean processFixtures;
    private JFileChooser fc;
    
    /*Creates new MainGameScreen */
    public MainGameScreen(String file) {
    	super("Football Manager");
    	fc = new JFileChooser("H:\\usb\\College\\Year 3\\Project\\FootballManager\\FootballManager\\games");
		fc.setAcceptAllFileFilterUsed(false);
		fc.setFileFilter(new FileFilter(){

			@Override
			public boolean accept(File file) {
				if(file.isDirectory()){
					return true;
				}
				else if(file.getName().endsWith(".fm")){
					return true;
				}
				return false;
			}

			@Override
			public String getDescription() {
				return "Games (*.fm)";
			}
			
		});
    	if(file == null){
    		game = Game.getInstance();
    	}
    	else{
    		game = Game.getInstance(file);
    	}
    	this.userClub = this.club = game.getUserClubName();
    	this.user = game.getUserName();
        initComponents();
        mg = this;
        
        // Event listener for File -> New Menu Item
        newMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				newGame(e);
			}
			
		});
        
     // Event listener for File -> Load Menu Item
        loadMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loadGame(e);
			}
			
		});
        
        // Event listener for File -> Save as Menu Item
        saveAsMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saveGameAs();
			}
			
		});
        
        // Event listener for File -> Save Menu Item
        saveMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saveGame();
			}
			
		});
        
     // Event listener for File -> Exit Menu Item
        exitMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exitGame();
			}
			
		});
        
        // Event listener for Manager -> Profile Menu Item
        userProfileMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayUserProfile(e);
			}
			
		});
        
        // Event listener for Manager -> Inbox Menu Item
        inboxMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayInbox(e);
			}
			
		});
        
        // Event listener for Manager -> Player Search Menu Item
        shortlistMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayShortlist(e);
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
        
        // Event listener for Team -> Fixtures Menu Item
        fixturesMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayUserSquadFixtures(e);
			}
			
		});
        
        // Event listener for Team -> Information Menu Item
        informationMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayUserSquadInformation(e);
			}
			
		});
        
        // Event listener for Team -> Finances Menu Item
        financesMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayClubFinances(e);
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
				displayLeague(0, "Barclays Premier League");
			}
			
		});
        
        // Event listener for League -> Fixtures Menu Item
        leagueFixturesMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayLeague(1, "Barclays Premier League");
			}
			
		});
        
        barclaysPremierLeagueMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayLeague(0, barclaysPremierLeagueMenuItem.getText());
			}
			
		});
        
        cocaColaChampionshipMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayLeague(0, cocaColaChampionshipMenuItem.getText());
			}
			
		});
        
        cocaColaLeagueOneMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayLeague(0, cocaColaLeagueOneMenuItem.getText());
			}
			
		});
        
        cocaColaLeagueTwoMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayLeague(0, cocaColaLeagueTwoMenuItem.getText());
			}
			
		});
        
        blueSquarePremierLeagueMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayLeague(0, blueSquarePremierLeagueMenuItem.getText());
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
        
        // Event listener for view player button
        viewSelectedPlayerForPlayerSearchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayPlayerFromPlayerSearch(e);
			}
			
		});
        
        // Event listener for view staff member button
        viewStaffProfileButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayStaffProfile(e);
			}
			
		});
        
        // Event listener for continue button
        continueButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				continueGame(e);
			}
			
		});
        
        // Event listener for make offer button
        makeOfferForPlayerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				makeOffer(e);
			}
			
		});
        
        // Event listener for make offer button
        offerNewContractButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				offerContract(e);
			}
			
		});
        
        // Event listener for make offer button
        addToShortlistButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addToShortlist(e);
			}
			
		});
        
        // Event listener for make offer button
        newButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				newGame(e);
			}
			
		});
        
        // Event listener for make offer button
        loadButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loadGame(e);
			}
			
		});
        
        // Event listener for make offer button
        saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saveGame();
			}
			
		});
        
        // Event listener for make offer button
        inboxButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayInbox(e);
			}
			
		});
        
        // Event listener for make offer button
        playerSearchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayPlayers(e);
			}
			
		});
        
        // Event listener for make offer button
        shortlistButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayShortlist(e);
			}
			
		});
        
        // Event listener for make offer button
        squadButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayUserSquad(e);
			}
			
		});
        
        // Event listener for make offer button
        financesButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayClubFinances(e);
			}
			
		});
        
        // Event listener for make offer button
        fixturesButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayUserSquadFixtures(e);
			}
			
		});
        
        // Event listener for make offer button
        leagueButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayLeague(0, "Barclays Premier League");
			}
			
		});
        
        // Centre window on screen
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

	private void exitGame() {
		int result = JOptionPane.showConfirmDialog(this, "You will lose any unsaved changes to your current game.  Do you want to save before continuing?");
		if(result <= 1){
    		if(result == 0)
    			saveGame();
    		System.exit(0);
		}
	}

	private void loadGame(ActionEvent ae){
		int result = JOptionPane.showConfirmDialog(this, "You will lose any unsaved changes to your current game.  Do you want to save before continuing?");
		if(result <= 1){
    		if(result == 0)
    			saveGame();
    		File file = null;
    		int returnVal = fc.showOpenDialog(this);
    		if (returnVal == JFileChooser.APPROVE_OPTION) {
    			file = fc.getSelectedFile();
    			this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
    			Game.destroyInstance();
    			MainGameScreen mg = new MainGameScreen(file.getAbsolutePath());
    			mg.setVisible(true);
    			this.dispose();
    		}
		}
	}

	private void saveGameAs() {
		File file = null;
		int returnVal = fc.showSaveDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
            String fileName = file.getAbsolutePath();
            if(!(fileName.endsWith(".fm"))){
            	fileName = fileName + ".fm";
            }
            game.save(fileName);
		}
	}

	private void saveGame() {
		if(game.isSaved()){
			game.save(game.getFileName());
		}
		else{
			saveGameAs();
		}
	}

	private void displayClubFinances(ActionEvent e) {
		displayClub(userClub, 4);	
	}

	private void addToShortlist(ActionEvent ae){
    	if(addToShortlistButton.getText().equals("Add to Shortlist")){
        	game.addPlayerToShortlist(playerProfilePanel.getNameValueLabel().getText(), user);
        	addToShortlistButton.setText("Remove from Shortlist");
    	}
    	else{
    		game.removePlayerFromShortlist(playerProfilePanel.getNameValueLabel().getText(), user);
    		addToShortlistButton.setText("Add to Shortlist");
    	}
    	this.validate();
    }
    
    private void offerContract(ActionEvent ae){
    	ContractOffer c = new ContractOffer(playerProfilePanel.getNameValueLabel().getText(), 0);
    	c.setVisible(true);
    }
    
    private void makeOffer(ActionEvent ae){
    	TransferOffer t = new TransferOffer(this, user, playerProfilePanel.getNameValueLabel().getText(), game.getTransferBudget(userClub));
    	t.setVisible(true);
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
        leagueFixturesMenuItem = new JMenuItem("Results/Fixtures");
        worldMenu = new JMenu("World");
        englandMenuItem = new JMenu("England");
        barclaysPremierLeagueMenuItem = new JMenuItem("Barclays Premier League");
        cocaColaChampionshipMenuItem = new JMenuItem("Coca-Cola Championship");
        cocaColaLeagueOneMenuItem = new JMenuItem("Coca-Cola League One");
        cocaColaLeagueTwoMenuItem = new JMenuItem("Coca-Cola League Two");
        blueSquarePremierLeagueMenuItem = new JMenuItem("Blue Square Premier");
        date = new JLabel();
        mainPanel = new JPanel(new CardLayout());
        sidePanel = new JPanel(new CardLayout());
        viewSelectedClubButton = new JButton("View Club");
        leagueTableSidePanel = new JPanel();
        squadSidePanel = new JPanel();
        viewSelectedPlayerButton = new JButton("View Player");
        viewStaffProfileButton = new JButton("View NonPlayer");
        playerSidePanel = new JPanel();
        makeOfferForPlayerButton = new JButton("Make Offer");
        addToShortlistButton = new JButton("Add to Shortlist");
        scoutPlayerButton = new JButton("Get Scout Report");
        userPlayerSidePanel = new JPanel();
        setTransferStatusButton = new JButton("Set Transfer Status");
        offerNewContractButton = new JButton("Offer New Contract");
        clubPanel = new JTabbedPane();
        playerPanel = new JTabbedPane();
        leaguePanel = new JTabbedPane();
        continueButton = new JButton("Continue");
        playerSearchSidePanel = new JPanel();
        viewSelectedPlayerForPlayerSearchButton = new JButton("View Player");
        nonPlayerSidePanel = new JPanel();
        inboxSidePanel = new JPanel();
        processFixtures = false;
        toolbar = new JToolBar();
        newButton = new JButton(new ImageIcon("football-icon.png"));
        loadButton = new JButton(new ImageIcon("My-Games-icon.png"));
        saveButton = new JButton(new ImageIcon("Floppy disk-32.png"));
        inboxButton = new JButton(new ImageIcon("mail_black-32.png"));
        playerSearchButton = new JButton(new ImageIcon("Magnifying-Glass-icon.png"));
        shortlistButton = new JButton(new ImageIcon("Task-List-icon.png"));
        squadButton = new JButton(new ImageIcon("soccer_5_32.png"));
        fixturesButton = new JButton(new ImageIcon("Soccer-Stadium-32.png"));
        financesButton = new JButton(new ImageIcon("Money-32.png"));
        leagueButton = new JButton(new ImageIcon("Soccer-Trophy-48.png"));
        newButton.setToolTipText("Create a New Game");
        loadButton.setToolTipText("Load a Previously Saved Game");
        saveButton.setToolTipText("Save Current Game");
        inboxButton.setToolTipText("Check your Mail");
        playerSearchButton.setToolTipText("Search for Players");
        shortlistButton.setToolTipText("View your Shortlist");
        squadButton.setToolTipText("View your Squad");
        fixturesButton.setToolTipText("View your Fixtures");
        financesButton.setToolTipText("View your Finances");
        leagueButton.setToolTipText("View League Table");
        
        toolbar.setFloatable(false);
        toolbar.setRollover(true);
        toolbar.addSeparator();
        toolbar.add(newButton);
        toolbar.add(loadButton);
        toolbar.add(saveButton);
        toolbar.addSeparator();
        toolbar.add(inboxButton);
        toolbar.add(playerSearchButton);
        toolbar.add(shortlistButton);
        toolbar.addSeparator();
        toolbar.add(squadButton);
        toolbar.add(fixturesButton);
        toolbar.add(financesButton);
        toolbar.addSeparator();
        toolbar.add(leagueButton);
        
        continueButton.setFont(new java.awt.Font("Verdana",1,16));
        continueButton.setBackground(Color.LIGHT_GRAY);
        continueButton.setForeground(Color.BLACK);
        date.setFont(new java.awt.Font("Verdana",1,12));
        date.setBackground(Color.LIGHT_GRAY);
        date.setForeground(Color.BLACK);
        viewSelectedClubButton.setFont(new java.awt.Font("Verdana",1,12));
        viewSelectedClubButton.setBackground(Color.LIGHT_GRAY);
        viewSelectedClubButton.setForeground(Color.BLACK);
        viewSelectedPlayerButton.setFont(new java.awt.Font("Verdana",1,12));
        viewSelectedPlayerButton.setBackground(Color.LIGHT_GRAY);
        viewSelectedPlayerButton.setForeground(Color.BLACK);
        viewStaffProfileButton.setFont(new java.awt.Font("Verdana",1,12));
        viewStaffProfileButton.setBackground(Color.LIGHT_GRAY);
        viewStaffProfileButton.setForeground(Color.BLACK);
        makeOfferForPlayerButton.setFont(new java.awt.Font("Verdana",1,12));
        makeOfferForPlayerButton.setBackground(Color.LIGHT_GRAY);
        makeOfferForPlayerButton.setForeground(Color.BLACK);
        addToShortlistButton.setFont(new java.awt.Font("Verdana",1,12));
        addToShortlistButton.setBackground(Color.LIGHT_GRAY);
        addToShortlistButton.setForeground(Color.BLACK);
        scoutPlayerButton.setFont(new java.awt.Font("Verdana",1,12));
        scoutPlayerButton.setBackground(Color.LIGHT_GRAY);
        scoutPlayerButton.setForeground(Color.BLACK);
        setTransferStatusButton.setFont(new java.awt.Font("Verdana",1,12));
        setTransferStatusButton.setBackground(Color.LIGHT_GRAY);
        setTransferStatusButton.setForeground(Color.BLACK);
        offerNewContractButton.setFont(new java.awt.Font("Verdana",1,12));
        offerNewContractButton.setBackground(Color.LIGHT_GRAY);
        offerNewContractButton.setForeground(Color.BLACK);

        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowListener(){

			@Override
			public void windowActivated(WindowEvent arg0) {
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				exitGame();
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				
			}
        	
        });
        //this.setExtendedState(this.getExtendedState()|JFrame.MAXIMIZED_BOTH);
        this.setSize(900, 630);
        
        //Display the users squad as the initial screen
        displayClub(userClub, 0);
        
        //Display the date
        setDate(game.getDate());

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
        leagueMenu.add(leagueFixturesMenuItem);
        menuBar.add(leagueMenu);
        englandMenuItem.add(barclaysPremierLeagueMenuItem);
        englandMenuItem.add(cocaColaChampionshipMenuItem);
        englandMenuItem.add(cocaColaLeagueOneMenuItem);
        englandMenuItem.add(cocaColaLeagueTwoMenuItem);
        englandMenuItem.add(blueSquarePremierLeagueMenuItem);
        worldMenu.add(englandMenuItem);
        menuBar.add(worldMenu);
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
        
        // Layout player search side pane        
        GroupLayout playerSearchSidePanelLayout = new GroupLayout(playerSearchSidePanel);
        playerSearchSidePanel.setLayout(playerSearchSidePanelLayout);
        playerSearchSidePanelLayout.setHorizontalGroup(
            playerSearchSidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(playerSearchSidePanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(playerSearchSidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                	.addComponent(viewSelectedPlayerForPlayerSearchButton))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        playerSearchSidePanelLayout.setVerticalGroup(
            playerSearchSidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(playerSearchSidePanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(viewSelectedPlayerForPlayerSearchButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
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
        
     // Layout player side panel
        GroupLayout playerSidePaneLayout = new GroupLayout(playerSidePanel);
        playerSidePanel.setLayout(playerSidePaneLayout);
        playerSidePaneLayout.setHorizontalGroup(
            playerSidePaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(playerSidePaneLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(playerSidePaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(scoutPlayerButton)
                	.addComponent(addToShortlistButton)
                	.addComponent(makeOfferForPlayerButton))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        playerSidePaneLayout.setVerticalGroup(
            playerSidePaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(playerSidePaneLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(makeOfferForPlayerButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addToShortlistButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scoutPlayerButton)
                .addContainerGap(224, Short.MAX_VALUE))
        );
        
     // Layout user's player side panel
        GroupLayout userPlayerSidePaneLayout = new GroupLayout(userPlayerSidePanel);
        userPlayerSidePanel.setLayout(userPlayerSidePaneLayout);
        userPlayerSidePaneLayout.setHorizontalGroup(
        		userPlayerSidePaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(userPlayerSidePaneLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(userPlayerSidePaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(offerNewContractButton)
                	.addComponent(setTransferStatusButton))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        userPlayerSidePaneLayout.setVerticalGroup(
        		userPlayerSidePaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(userPlayerSidePaneLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(setTransferStatusButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(offerNewContractButton)
                .addContainerGap(224, Short.MAX_VALUE))
        );
        
        // Layout window
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(toolbar, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(date)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(continueButton, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sidePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            		.addGroup(layout.createSequentialGroup()
                            .addComponent(toolbar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(10, Short.MAX_VALUE))
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

	private void continueGame(ActionEvent ae){
		squadPanel = new SquadPanel(this.userClub, squadPanel, this.userClub);
		List<String> players = squadPanel.getSelectedPlayers();
		int result = game.continueGame(processFixtures, players);
    	if(result == 1){
    		displayLeague(1, "Barclays Premier League");
    		processFixtures = true;
    	}
    	else if(processFixtures){
    		Match m = game.getUserMatch();
    		//Match m = null;
    		if(m != null){
    			this.setEnabled(false);
    			MatchScreen ms = new MatchScreen(m);
    			ms.setVisible(true);
    		}
    		else{
    			displayLeague(1, "Barclays Premier League");
    		}
    		processFixtures = false;
    	}
    	else if(result == 2){
    		displayClub(userClub ,0);
    		JOptionPane.showMessageDialog(this, "You have a match today and need to select 11 available players to continue to it!");
    	}
    	else{
    		displayInbox();
    	}
    	setDate(game.getDate());
    }
    
    private void displayInbox(){
    	inboxPanel = new InboxPanel();
        mainPanel.add(inboxPanel, "Inbox");
        ((CardLayout) mainPanel.getLayout()).show(mainPanel, "Inbox");
        sidePanel.add(inboxSidePanel, "Inbox Sidebar");
    	((CardLayout) sidePanel.getLayout()).show(sidePanel, "Inbox Sidebar");
    }
    
    /*Display Inbox Screen*/
    private void displayInbox(ActionEvent ae){
    	displayInbox();
    }
    
    /*Display Shortlist Screen*/
    private void displayShortlist(ActionEvent ae){
    	mainPanel.removeAll();
    	shortlistPanel = new ShortlistPanel(user);
        mainPanel.add(shortlistPanel, "Shortlist");
        ((CardLayout) mainPanel.getLayout()).show(mainPanel, "Shortlist");
        sidePanel.add(squadSidePanel, "Squad Sidebar");
    	((CardLayout) sidePanel.getLayout()).show(sidePanel, "Squad Sidebar"); 
    }
    
    /*Display Player Search Screen*/
    private void displayPlayers(ActionEvent ae){
    	mainPanel.removeAll();
    	playerSearchPanel = new PlayerSearchPanel();
        mainPanel.add(playerSearchPanel, "Player Search");
        ((CardLayout) mainPanel.getLayout()).show(mainPanel, "Player Search");
        sidePanel.add(playerSearchSidePanel, "Player Search Sidebar");
    	((CardLayout) sidePanel.getLayout()).show(sidePanel, "Player Search Sidebar"); 
    }
    
    /*Display Staff Search Screen*/
    private void displayAllStaff(ActionEvent ae){
    	mainPanel.removeAll();
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
    	displayClub(club, 0);
	}
    
    /*Get Player to Display*/
    private void displayPlayerFromPlayerSearch(ActionEvent ae){
		player = (String) (playerSearchPanel.getPlayerSearchTable().getValueAt(playerSearchPanel.getPlayerSearchTable().getSelectedRow(), 0));
    	displayPlayer(player);
	}
    
    /*Get Player to Display*/
    private void displayPlayer(ActionEvent ae){
    	try{
    		player = (String) (squadPanel.getSquadTable().getValueAt(squadPanel.getSquadTable().getSelectedRow(), 1));
    		displayPlayer(player);
    	}catch(ArrayIndexOutOfBoundsException e){}
	}
    
    /*Display staff member*/
    private void displayStaffProfile(String staffMember){
    	mainPanel.removeAll();
    	staffProfilePanel = new StaffProfilePanel(staffMember);
    	mainPanel.add(staffProfilePanel, "Staff Profile");
    	((CardLayout) mainPanel.getLayout()).show(mainPanel, "Staff Profile");
    	sidePanel.add(nonPlayerSidePanel, "Non-Player Sidebar");
    	((CardLayout) sidePanel.getLayout()).show(sidePanel, "Non-Player Sidebar");
    }
    
    /*Get staff profile to display*/
    private void displayStaffProfile(ActionEvent ae){
    	try{
    		displayStaffProfile((String) (staffPanel.getStaffTable().getValueAt(staffPanel.getStaffTable().getSelectedRow(), 0)));
    	}catch(ArrayIndexOutOfBoundsException e){}
	}
    
    /*Creates a new game*/
    private void newGame(ActionEvent ae){
    	int result = JOptionPane.showConfirmDialog(this, "You will lose any unsaved changes to your current game.  Do you want to save before continuing?");
    	if(result <= 1){
    		if(result == 0)
    			saveGame();
    		Game.destroyInstance();
    		NewUserScreen ns = new NewUserScreen();
    		ns.setVisible(true);
    		this.dispose();
    	}
    }
    
    public void displayLeague(int tab, String name){
    	mainPanel.removeAll();
    	leagueTablePanel = new LeagueTablePanel(name);
    	DateFormat format = new SimpleDateFormat("dd-MMM-yy");
    	leagueFixturesPanel = new LeagueFixturesPanel(name, format.format(game.getDate().getTime()));
    	leaguePanel.removeAll();
    	leaguePanel.add("Table", leagueTablePanel);
    	leaguePanel.add("Fixtures", leagueFixturesPanel);
    	leaguePanel.setSelectedIndex(tab);
    	mainPanel.add(leaguePanel, "League");
    	((CardLayout) mainPanel.getLayout()).show(mainPanel, "League");
    	sidePanel.add(leagueTableSidePanel, "League Table Sidebar");
    	((CardLayout) sidePanel.getLayout()).show(sidePanel, "League Table Sidebar");
    }
    
    /*Display player profile*/
    private void displayPlayer(String player){
		mainPanel.removeAll();
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
    	if(playerContractPanel.getClubValueLabel().getText().equals(userClub)){
    		sidePanel.add(userPlayerSidePanel, "User Player Sidebar");
    		((CardLayout) sidePanel.getLayout()).show(sidePanel, "User Player Sidebar");
    	}
    	else{
    		if(game.checkShortlistForPlayer(player, user)){
    			addToShortlistButton.setText("Remove from Shortlist");
    		}
    		else{
    			addToShortlistButton.setText("Add to Shortlist");
    		}
    		sidePanel.add(playerSidePanel, "Player Sidebar");
    		((CardLayout) sidePanel.getLayout()).show(sidePanel, "Player Sidebar");
    	}
    }
    
    /*Display particular club*/
    private void displayClub(String club, int defaultTab){
    	mainPanel.removeAll();
    	squadPanel = new SquadPanel(club, squadPanel, this.userClub);
    	fixturesPanel = new FixturesPanel(club);
    	staffPanel = new StaffPanel(club);
    	clubInformationPanel = new ClubInformationPanel(club);
    	if(club.equals(userClub))
    		financesPanel = new FinancesPanel(userClub);
    	clubPanel.removeAll();
    	clubPanel.addTab("Squad", squadPanel);
    	clubPanel.addTab("Fixtures", fixturesPanel);
        clubPanel.addTab("Staff", staffPanel);
        clubPanel.addTab("Information", clubInformationPanel);
        if(club.equals(userClub))
        	clubPanel.addTab("Finances", financesPanel);
        clubPanel.setSelectedIndex(defaultTab);
        mainPanel.add(clubPanel, "Club");
        ((CardLayout) mainPanel.getLayout()).show(mainPanel, "Club");
        sidePanel.add(squadSidePanel, "Squad Sidebar");
    	((CardLayout) sidePanel.getLayout()).show(sidePanel, "Squad Sidebar"); 
    }
    
    private void displayUserSquad(ActionEvent ae){
    	displayClub(userClub, 0);
    	this.club = userClub;
	}
    
    private void displayClubInformation(String club){
    	displayClub(club, 3); 
    }
    
    private void displayUserSquadInformation(ActionEvent ae){
    	displayClubInformation(userClub);
	}
    
    private void displayStaff(String club){
    	displayClub(club, 2); 
    }
    
    private void displayUserSquadFixtures(ActionEvent ae){
    	displayFixtures(userClub);
	}
    
    private void displayFixtures(String club){
    	displayClub(club, 1); 
    }
    
    private void displayUserStaff(ActionEvent ae){
    	displayStaff(userClub);
	}
    
    public static MainGameScreen getInstance(){
    	return mg;
    }

	public JButton getAddToShortlistButton() {
		return addToShortlistButton;
	}
	
	private void setDate(Calendar c){
		String text = "";
        int day = c.get(Calendar.DAY_OF_WEEK);
        switch(day){
        	case 1: text = text + "Sun";
        			break;
        	case 2: text = text + "Mon";
					break;
        	case 3: text = text + "Tue";
					break;
        	case 4: text = text + "Wed";
					break;
        	case 5: text = text + "Thu";
					break;
        	case 6: text = text + "Fri";
					break;
        	case 7: text = text + "Sat";
					break;
			default:System.out.println("ERROR");
        }
        text = text + " " + c.get(Calendar.DATE);
        int month = c.get(Calendar.MONTH);
        switch(month){
        	case 0: text = text + " Jan";
        			break;
        	case 1: text = text + " Feb";
					break;
        	case 2: text = text + " Mar";
					break;
        	case 3: text = text + " Apr";
					break;
        	case 4: text = text + " May";
					break;
        	case 5: text = text + " Jun";
					break;
        	case 6: text = text + " Jul";
					break;
        	case 7: text = text + " Aug";
					break;
			case 8: text = text + " Sept";
					break;
			case 9: text = text + " Oct";
					break;
			case 10:text = text + " Nov";
					break;
			case 11:text = text + " Dec";
					break;
			default:System.out.println("ERROR");
        }
        text = text + " " + c.get(Calendar.YEAR);
        date.setText(text);
	}
}