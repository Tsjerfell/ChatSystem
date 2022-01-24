package Interface;
import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import chatting.ThreadManagerSender;
import chatting.otherUserTalkingTo;
import connect.otherUser;
import main.Main;

import java.awt.*;
import java.awt.event.*;
import java.net.UnknownHostException;
import java.util.Vector;

public class Visuel extends JPanel{
	JFrame frame;
	public static otherUserTalkingTo currentTalkingWith = new otherUserTalkingTo();
	
	
	
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
	int rowNumberConnectedUsers;
	
	//Pour les champs connecté à other users you are connected to
	JLabel textHavingConv;
	public static JTextArea textUsersHavingConnexionWith;
	int rowNumberConvWith;
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
			    	writeInpseudoTextArea("Username amready taken by someone else");
			    } else {
			    	writeInpseudoTextArea("Your username was changed to " + choosePseudo.getText());
			    }
			  } 
			} );
		
		textChangementDePseudo = new JTextArea("You are yet to choose a username",30,1);
		textChangementDePseudo.setEditable(false);
		//textChangementDePseudo.getCaret().setVisible(true);
		add(textChangementDePseudo);
		textChangementDePseudo.setBounds (0, 25, 480, 25);
        
        //Pour convPanel
        textHistory = new JTextArea();
        textHistory.setEditable(false);
        //textHistory.getCaret().setVisible(true);
        add(textHistory);
        textHistory.setBounds (0, 50, 480, 410);
        
    	message = new JTextField();
    	add(message);
    	message.setBounds (0, 460, 405, 25);
    	
    	send = new JButton("send");
    	add(send);
    	send.setBounds (410, 460, 70, 25);
    	send.addActionListener(new ActionListener() { 
    		public void actionPerformed(ActionEvent e) {
    			String command = e.getActionCommand();
    			if (command.equals("send")) {
    				
    				String messageAEnvoyer = message.getText();  
    				try {
    				Visuel.currentTalkingWith.thread.sendMessage(messageAEnvoyer,false);
    				}catch(Exception ex) {}
    				
    			}	
    		}
    	});
        
        //Pour connectedUsersPanel
    	pseudoLabel =  new JLabel("Current Connected Users:");
    	add(pseudoLabel);
    	pseudoLabel.setBounds (480, 0, 420, 25);
    	
    	textAreaConnectedUsers = new JTextArea(4,4);
    	//textAreaConnectedUsers.setEditable(false);
    	textAreaConnectedUsers.getCaret().setVisible(true);
    	textAreaConnectedUsers.addCaretListener(new CaretListener() {
            // Each time the caret is moved, it will trigger the listener and its method caretUpdate.
            // It will then pass the event to the update method including the source of the event (which is our textarea control)
            public void caretUpdate(CaretEvent e) {
                JTextArea editArea = (JTextArea)e.getSource();

                // Lets start with some default values for the line and column.
                rowNumberConnectedUsers = 0;
                int columnnum = 1;

                // We create a try catch to catch any exceptions. We will simply ignore such an error for our demonstration.
                try {
                    // First we find the position of the caret. This is the number of where the caret is in relation to the start of the JTextArea
                    // in the upper left corner. We use this position to find offset values (eg what line we are on for the given position as well as
                    // what position that line starts on.
                    int caretpos = editArea.getCaretPosition();
                    rowNumberConnectedUsers = editArea.getLineOfOffset(caretpos);

                    // We subtract the offset of where our line starts from the overall caret position.
                    // So lets say that we are on line 5 and that line starts at caret position 100, if our caret position is currently 106
                    // we know that we must be on column 6 of line 5.

                    // We have to add one here because line numbers start at 0 for getLineOfOffset and we want it to start at 1 for display.
                    rowNumberConnectedUsers += 1;
                }
                catch(Exception ex) { }

            }
        });
    	add(textAreaConnectedUsers);
    	textAreaConnectedUsers.setBounds (480, 25, 415, 225);
    	
    	initConv = new JButton("Initialize conversation with user clicked above");
    	add(initConv);
    	initConv.setBounds (480, 250, 415, 25);
    	
    	initConv.addActionListener(new ActionListener() { 
    		public void actionPerformed(ActionEvent e) {
    			String command = e.getActionCommand();
    			if (command.equals("Initialize conversation with user clicked above")) { 
    				
    				otherUser ComWith = Main.listOtherConnectedUsers.get(rowNumberConnectedUsers-1);
    				boolean AlreadyTalkingWith = false ;
    				otherUserTalkingTo otherUserNeed = null;
    				for (otherUserTalkingTo otherUser : Main.listotherUserTalkingTo) {
    					if (otherUser.addIP.equals(ComWith.addressIP.toString().substring(1))) {// Alors on parle déjà avec la personne
    						AlreadyTalkingWith = true;
    						otherUserNeed = otherUser;
    					}
    				}
    				
    				if (AlreadyTalkingWith) {// Alors on parle déjà avec la personne
						Visuel.currentTalkingWith = otherUserNeed;
						Visuel.WriteHistoryField("You are already chatting with " + ComWith.pseudo +", here is the conversation:");
					} else {
						ThreadManagerSender thread = new ThreadManagerSender(ComWith);
	    				thread.start();
					}
    				
    				
    			}	
    		}
    	});

    	
    	//Dans connectedUsersHavingConvPanel il y a
    	textHavingConv = new JLabel("Users your are currently having a conversation with:");
    	add(textHavingConv);
    	textHavingConv.setBounds (480, 275, 415, 25);
    	
    	textUsersHavingConnexionWith = new JTextArea(5,5);
    	//textUsersHavingConnexionWith.setEditable(false);
    	textUsersHavingConnexionWith.getCaret().setVisible(true);
    	add(textUsersHavingConnexionWith);
    	textUsersHavingConnexionWith.setBounds(480, 295, 415, 165);
    	textUsersHavingConnexionWith.addCaretListener(new CaretListener() {
            // Each time the caret is moved, it will trigger the listener and its method caretUpdate.
            // It will then pass the event to the update method including the source of the event (which is our textarea control)
            public void caretUpdate(CaretEvent e) {
                JTextArea editArea = (JTextArea)e.getSource();

                // Lets start with some default values for the line and column.
                rowNumberConvWith = 0;
                int columnnum = 1;

                // We create a try catch to catch any exceptions.
                try {
                    // First we find the position of the caret. This is the number of where the caret is in relation to the start of the JTextArea
                    // in the upper left corner. We use this position to find offset values (eg what line we are on for the given position as well as
                    // what position that line starts on.
                    int caretpos = editArea.getCaretPosition();
                    rowNumberConvWith = editArea.getLineOfOffset(caretpos);

                    // We subtract the offset of where our line starts from the overall caret position.
                    // So lets say that we are on line 5 and that line starts at caret position 100, if our caret position is currently 106
                    // we know that we must be on column 6 of line 5.

                    // We have to add one here because line numbers start at 0 for getLineOfOffset and we want it to start at 1 for display.
                    rowNumberConvWith += 1;
                    currentTalkingWith = Main.listotherUserTalkingTo.get(rowNumberConvWith -1);
                    WriteHistoryField("Currently talking with "+currentTalkingWith.pseudo);
                    Runnable runnable = new ThreadDisplayHistory(Main.findIp(currentTalkingWith.pseudo),Main.addressIP);
                    Thread thread = new Thread(runnable);
                    thread.start();
                }
                catch(Exception ex) { }

            }
        });
    	
    	quitConv = new JButton("End conversation with user clicked above");
    	quitConv.addActionListener(new ActionListener() { 
    		public void actionPerformed(ActionEvent e) {
    			String command = e.getActionCommand();
    			
    			if (command.equals("End conversation with user clicked above")) { 
    				
    				currentTalkingWith.thread.endConversation();  
    				currentTalkingWith.thread.sendMessage("Terminé",true);
    				
    				Main.listotherUserTalkingTo.remove(currentTalkingWith);
    				Visuel.updateUserHavingConvWith();
    				
    				
    				if (Main.listotherUserTalkingTo.size() == 0) {
    					Visuel.WriteHistoryField("You dont have any other conversations");
    					currentTalkingWith = null;
    				} else {
    					currentTalkingWith = Main.listotherUserTalkingTo.get(0);
    					Visuel.WriteHistoryField("Currently talking with" + currentTalkingWith.pseudo);
    				}
    				
    				
    				
    				
    				
    			}	
    		}
    	});
  
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
                    System.exit(0);
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
	
	public static void writeInpseudoTextArea(String text) {
		Runnable runnable = new ThreadWriteInpseudoTextArea(text);
        Thread thread = new Thread(runnable);
        thread.start();
	}
	
	public static void updateUserHavingConvWith() {
		Runnable runnable = new ThreadWriteInUserConvWith();
        Thread thread = new Thread(runnable);
        thread.start();
	}
	public static void WriteHistoryField(String text) {
		Runnable runnable = new ThreadWriteHistory(text);
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
