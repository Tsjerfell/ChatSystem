package Interface;
import javax.swing.*;

import connect.otherUser;
import main.Main;

import java.awt.*;
import java.awt.event.*;
import java.net.UnknownHostException;
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
		
		mainPanel = new JPanel(new GridBagLayout());
	    
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
		
		confirm.addActionListener(new ActionListener() { 
			boolean memeNom;
			  public void actionPerformed(ActionEvent e) { 
			    try {
					memeNom = Main.changementPseudo(choosePseudo.getText());
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    if (memeNom) {
			    	writeInPsuedoTextArea("Username amready taken by someone else");
			    } else {
			    	writeInPsuedoTextArea("Your username was changed to " + choosePseudo.getText());
			    }
			  } 
			} );
		
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
    	deconnexion.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if (command.equals("Deconnexion")) {
                    Main.deconnect();
                } 
            }
        }));
    	
    	setPreferredSize (new Dimension (899, 513));
        setLayout (null);
	    
	}

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
	
	public static void writeInPsuedoTextArea(String text) {
		Runnable runnable = new ThreadWriteInPsuedoTextArea(text);
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
