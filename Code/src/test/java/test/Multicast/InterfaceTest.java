package test.Multicast;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class InterfaceTest extends JPanel {
  private JButton jcomp1;
  private JLabel jcomp2;
  private JTextField jcomp3;
  private JLabel jcomp4;
  private JTextArea jcomp5;
  private JTextArea jcomp6;
  private JTextArea jcomp7;
  private JTextField jcomp8;
  private JButton jcomp9;
  private JButton jcomp10;
  private JTextArea jcomp11;
  private JButton jcomp12;
  private JTextArea jcomp13;
  private JButton jcomp14;
  private JTextArea jcomp15;

  public InterfaceTest() {
      //construct components
      jcomp1 = new JButton ("Confirm");
      jcomp2 = new JLabel ("Change Username");
      jcomp3 = new JTextField (5);
      jcomp4 = new JLabel ("Current connected users:");
      jcomp5 = new JTextArea (5, 5);
      jcomp6 = new JTextArea (5, 5);
      jcomp7 = new JTextArea (5, 5);
      jcomp8 = new JTextField (5);
      jcomp9 = new JButton ("Deconnexion");
      jcomp10 = new JButton ("send");
      jcomp11 = new JTextArea (5, 5);
      jcomp12 = new JButton ("Initialize conversation with users clicked above");
      jcomp13 = new JTextArea (5, 5);
      jcomp14 = new JButton ("Quit conversation with user clicked above");
      jcomp15 = new JTextArea (5, 5);

      //adjust size and set layout
      setPreferredSize (new Dimension (762, 528));
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
      add (jcomp15);

      //set component bounds (only needed by Absolute Positioning)
      jcomp1.setBounds (325, 0, 85, 20);
      jcomp2.setBounds (200, 0, 125, 20);
      jcomp3.setBounds (0, 0, 200, 20);
      jcomp4.setBounds (410, 0, 345, 20);
      jcomp5.setBounds (0, 20, 410, 20);
      jcomp6.setBounds (0, 40, 410, 20);
      jcomp7.setBounds (0, 60, 410, 390);
      jcomp8.setBounds (0, 450, 340, 25);
      jcomp9.setBounds (0, 475, 760, 25);
      jcomp10.setBounds (340, 450, 70, 25);
      jcomp11.setBounds (410, 20, 350, 180);
      jcomp12.setBounds (410, 200, 350, 25);
      jcomp13.setBounds (410, 450, 350, 25);
      jcomp14.setBounds (410, 425, 350, 25);
      jcomp15.setBounds (410, 225, 350, 200);
  }


  public static void main (String[] args) {
      JFrame frame = new JFrame ("ChatSystem");
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add (new InterfaceTest());
      frame.pack();
      frame.setVisible (true);
  }
}