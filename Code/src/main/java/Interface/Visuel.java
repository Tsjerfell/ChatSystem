package Interface;
import javax.swing.*;

import connect.otherUser;
import main.Main;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class Visuel extends JPanel{
	JFrame frame;
	
	//MainPanel
	JPanel mainPanel;
	
	//Pour les champs connectés à pseudo
	JTextField choosePseudo;
	JLabel pseudoLabel;
	JButton confirm;
	public static JTextArea textChangementDePseudo;
	
	//Pour les champs connectés aux conversation
	public static JTextArea textHistory;
	JTextField message;
	JButton send;
	
	//Pour les champs connectés à otherConnectedUsers
	JLabel textConnectedUsers;
	public static JTextArea textAreaConnectedUsers;
	JButton initConv;
	
	//Pour les champs connecté à other users you are connected to
	JLabel textHavingConv;
	public static JTextArea textUsersHavingConnexionWith;
	JButton quitConv;
	public static JTextArea infoUserHavingConnexionWith;
	
	//Pour le champ connecté à la deconnexion
	JButton deconnexion;
	
	
	public Visuel() {
		
		mainPanel = new JPanel(new GridBagLayout()); //new GridLayout(4, 1)
	    
		// Pour pseudoPanel

		choosePseudo = new JTextField();		
		add(choosePseudo);
		choosePseudo.setBounds (0, 0, 250, 25);
		
		pseudoLabel =  new JLabel("Change name");
		add(pseudoLabel);
		pseudoLabel.setBounds (250, 0, 125, 25);
		
		confirm= new JButton("Confirm") ;
		add(confirm);
		confirm.setBounds (380, 0, 100, 25);
		
		textChangementDePseudo = new JTextArea("You are yet to choose a username",30,1);
		add(textChangementDePseudo);
		textChangementDePseudo.setBounds (0, 25, 480, 25);
        
        //Pour convPanel
        textHistory = new JTextArea();
        add(textHistory);
        textHistory.setBounds (0, 50, 480, 410);
        
    	message = new JTextField();
    	add(message);
    	message.setBounds (0, 460, 405, 25);
    	
    	send = new JButton("send");
    	add(send);
    	send.setBounds (410, 460, 70, 25);
        
        //Pour connectedUsersPanel
    	pseudoLabel =  new JLabel("Current Connected Users:");
    	add(pseudoLabel);
    	pseudoLabel.setBounds (480, 0, 420, 25);
    	
    	textAreaConnectedUsers = new JTextArea(4,4);
    	add(textAreaConnectedUsers);
    	textAreaConnectedUsers.setBounds (480, 25, 415, 225);
    	
    	initConv = new JButton("Initialize conversation with user clicked above");
    	add(initConv);
    	initConv.setBounds (480, 250, 415, 25);
    	
    	//Dans connectedUsersHavingConvPanel il y a
    	textHavingConv = new JLabel("Users your are currently having a conversation with:");
    	add(textHavingConv);
    	textHavingConv.setBounds (480, 275, 415, 25);
    	
    	textUsersHavingConnexionWith = new JTextArea(5,5);
    	add(textUsersHavingConnexionWith);
    	textUsersHavingConnexionWith.setBounds(480, 295, 415, 165);
    	
    	quitConv = new JButton("End conversation with user clicked above");
    	add(quitConv);
    	quitConv.setBounds (485, 460, 410, 25);
    	
    	//infoUserHavingConnexionWith= new JTextArea(1,20);
    	//add(infoUserHavingConnexionWith);
    	
    	//Pour deconnexionPanel
    	deconnexion = new JButton("Deconnexion");
    	add(deconnexion);
    	deconnexion.setBounds (0, 485, 895, 25);
    	
    	setPreferredSize (new Dimension (899, 513));
        setLayout (null);
	    
	}
	
//	private void addWidgets() {
//		
//		// Pour pseudoPanel
//
//		choosePseudo = new JTextField();		
//		mainPanel.add(choosePseudo);
//		choosePseudo.setBounds (0, 0, 250, 25);
//		
//		pseudoLabel =  new JLabel("Choose name");
//		mainPanel.add(pseudoLabel);
//		pseudoLabel.setBounds (250, 0, 125, 25);
//		
//		confirm= new JButton("Confirm") ;
//		mainPanel.add(confirm);
//		confirm.setBounds (380, 0, 100, 25);
//		
//		textChangementDePseudo = new JTextArea("You are yet to choose a username",30,1);
//		mainPanel.add(textChangementDePseudo);
//		textChangementDePseudo.setBounds (0, 25, 480, 25);
//        
//        //Pour convPanel
//        textHistory = new JTextArea();
//        mainPanel.add(textHistory);
//        textHistory.setBounds (0, 50, 480, 410);
//        
//    	message = new JTextField();
//    	mainPanel.add(message);
//    	message.setBounds (0, 460, 405, 25);
//    	
//    	send = new JButton();
//    	mainPanel.add(send);
//    	send.setBounds (410, 460, 70, 25);
//        
//        //Pour connectedUsersPanel
//    	pseudoLabel =  new JLabel("Current Connected Users:");
//    	mainPanel.add(pseudoLabel);
//    	pseudoLabel.setBounds (480, 25, 415, 225);
//    	
//    	textAreaConnectedUsers = new JTextArea(5,5);
//    	mainPanel.add(textAreaConnectedUsers);
//    	textAreaConnectedUsers.setBounds (0, 25, 480, 25);
//    	
//    	initConv = new JButton("Initialize Conversation");
//    	mainPanel.add(initConv);
//    	
//    	//Dans connectedUsersHavingConvPanel il y a
//    	textHavingConv = new JLabel("Users your are currently having a conversation with");
//    	mainPanel.add(textHavingConv);
//    	textHavingConv.setBounds (480, 275, 415, 25);
//    	
//    	textUsersHavingConnexionWith = new JTextArea(6,5);
//    	mainPanel.add(textUsersHavingConnexionWith);
//    	
//    	quitConv = new JButton("End conversation with user clicked above");
//    	mainPanel.add(quitConv);
//    	quitConv.setBounds (485, 460, 410, 25);
//    	
//    	infoUserHavingConnexionWith= new JTextArea(1,20);
//    	mainPanel.add(infoUserHavingConnexionWith);
//    	
//    	//Pour deconnexionPanel
//    	deconnexion = new JButton("Deconnexion");
//    	mainPanel.add(deconnexion);
//    	deconnexion.setBounds (0, 485, 895, 25);
//    	
//        usersPanel=new JPanel ();
//        pseudoPanel.add(chosePseudo);
//        pseudoPanel.add(pseudoLabel);  
//        pseudoLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
//               
//        //Visuel.textAreaconnectedUsers.setCaretPosition(Visuel.textAreaconnectedUsers.getText().length() - 1);
//        updateConnectedUsers();        
//        
//        changementDePseudoPanel.add(textChangementDePseudo);
//        
//        
//        usersPanel.add(textAreaconnectedUsers);
//        convPanel.add(message);
//        confirm = new JButton("confirm");
//        send = new JButton("send");
//        pseudoPanel.add(confirm);
//        convPanel.add(send);
//        
//        message = new JTextField(70);
//    }

	private static void createAndShowGUI() {
	    JFrame.setDefaultLookAndFeelDecorated(true);
	    JDialog.setDefaultLookAndFeelDecorated(true);
	    JFrame frame = new JFrame("Visuel");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    Container contentPane = frame.getContentPane();
	    contentPane.setLayout(new GridLayout(1,1));
	    //contentPane.add(new Visuel(frame));
	    frame.pack();
	    frame.setVisible(true);
	}
	
	public static void updateConnectedUsers() {
		Runnable runnable = new ThreadTextAreaConnectedUsers();
        Thread thread = new Thread(runnable);
        thread.start();
	}
	
	public void run() {
        createAndShowGUI();
    }
	
	public static void main(String[] args) {
	    javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            createAndShowGUI();
	        }
	    });
	}
}
