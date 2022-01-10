package InterfaceTest;


	//Generated by GuiGenie - Copyright (c) 2004 Mario Awad.
	//Home Page http://guigenie.cjb.net - Check often for new versions!

	import java.awt.*;
	import java.awt.event.*;
	import javax.swing.*;
	import javax.swing.event.*;

	public class InterfaceTest2 extends JPanel {
	    private JLabel jcomp1;
	    private JTextField jcomp2;
	    private JButton jcomp3;
	    private JLabel jcomp4;
	    private JTextArea jcomp5;
	    private JTextArea jcomp6;
	    private JButton jcomp7;
	    private JButton jcomp8;
	    private JTextArea jcomp9;
	    private JTextArea jcomp10;
	    private JButton jcomp11;
	    private JTextArea jcomp12;
	    private JButton jcomp13;
	    private JLabel jcomp14;

	    public InterfaceTest2() {
	        //construct components
	        jcomp1 = new JLabel ("Change Name");
	        jcomp2 = new JTextField (5);
	        jcomp3 = new JButton ("Confirm");
	        jcomp4 = new JLabel ("Current connected users:");
	        jcomp5 = new JTextArea (5, 5);
	        jcomp6 = new JTextArea (5, 5);
	        jcomp7 = new JButton ("Deconnect");
	        jcomp8 = new JButton ("send");
	        jcomp9 = new JTextArea (5, 5);
	        jcomp10 = new JTextArea (5, 5);
	        jcomp11 = new JButton ("Initialize conversation with user clicked above");
	        jcomp12 = new JTextArea (5, 5);
	        jcomp13 = new JButton ("End conversation with user clicked aboce");
	        jcomp14 = new JLabel ("Users you have a converation with ");

	        //adjust size and set layout
	        setPreferredSize (new Dimension (899, 513));
	        setLayout (null);

	        //add components
	        add (jcomp1);
	        add (jcomp2);
	        add (jcomp3);
	        add (jcomp4);
	        add (jcomp5);
	        add (jcomp6);
	        add (jcomp7);
	        add (jcomp8);
	        add (jcomp9);
	        add (jcomp10);
	        add (jcomp11);
	        add (jcomp12);
	        add (jcomp13);
	        add (jcomp14);

	        //set component bounds (only needed by Absolute Positioning)
	        jcomp1.setBounds (250, 0, 125, 25);
	        jcomp2.setBounds (0, 0, 250, 25);
	        jcomp3.setBounds (380, 0, 100, 25);
	        jcomp4.setBounds (480, 0, 420, 25);
	        jcomp5.setBounds (0, 50, 480, 410);
	        jcomp6.setBounds (0, 25, 480, 25);
	        jcomp7.setBounds (0, 485, 895, 25);
	        jcomp8.setBounds (410, 460, 70, 25);
	        jcomp9.setBounds (0, 460, 405, 25);
	        jcomp10.setBounds (480, 25, 415, 225);
	        jcomp11.setBounds (480, 250, 415, 25);
	        jcomp12.setBounds (480, 295, 415, 165);
	        jcomp13.setBounds (485, 460, 410, 25);
	        jcomp14.setBounds (480, 275, 415, 25);
	    }


	    public static void main (String[] args) {
	        JFrame frame = new JFrame ("Interfacetest2");
	        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	        frame.getContentPane().add (new InterfaceTest2());
	        frame.pack();
	        frame.setVisible (true);
	    }
	}