package Interface;
import javax.swing.*;

import connect.otherUser;
import main.Main;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class Visuel extends JPanel{
	JFrame frame;
	JTextField chosePseudo, message;
	JLabel pseudoLabel;
	JPanel mainPanel, pseudoPanel, convPanel, usersPanel, displayPanel, histoPanel;
	
	public static JTextArea textAreaconnectedUsers = new JTextArea(5,5);
	
	JButton confirm, send;
	
	public Visuel(JFrame frame) {
		super(new BorderLayout());
		this.frame = frame;
		mainPanel = new JPanel(new GridLayout(3, 1));
	    addWidgets();
	    frame.getContentPane().add(mainPanel, BorderLayout.CENTER); 
	    mainPanel.add(pseudoPanel);
	    mainPanel.add(usersPanel);
	    mainPanel.add(convPanel);
	    frame.getRootPane().setDefaultButton(confirm);
	    frame.getRootPane().setDefaultButton(send);
	}
	
	private void addWidgets() {
        chosePseudo = new JTextField(40);
        message = new JTextField(70);
        pseudoLabel = new JLabel("choose your nickname", SwingConstants.RIGHT);
        //Add the widgets to the container.
        pseudoPanel=new JPanel ();
        convPanel=new JPanel (); 
        usersPanel=new JPanel ();
        pseudoPanel.add(chosePseudo);
        pseudoPanel.add(pseudoLabel);  
        pseudoLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
               
        //Visuel.textAreaconnectedUsers.setCaretPosition(Visuel.textAreaconnectedUsers.getText().length() - 1);
        updateConnectedUsers();        
        
        usersPanel.add(textAreaconnectedUsers);
        convPanel.add(message);
        confirm = new JButton("confirm");
        send = new JButton("send");
        pseudoPanel.add(confirm);
        convPanel.add(send);
    }

	private static void createAndShowGUI() {
	    JFrame.setDefaultLookAndFeelDecorated(true);
	    JDialog.setDefaultLookAndFeelDecorated(true);
	    JFrame frame = new JFrame("Visuel");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    Container contentPane = frame.getContentPane();
	    contentPane.setLayout(new GridLayout(1,1));
	    contentPane.add(new Visuel(frame));
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
