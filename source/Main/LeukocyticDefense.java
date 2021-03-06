package Main;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Desktop;
import java.io.File;
import Main.GameScreen;

/** This class is the main driver of Leukocytic Defense. It is a window and adds a GameScreen object to itself, starting the game thread.
  * @author Kareem Golaub
  * @version 2.0 May 23rd, 2014
  */
public class LeukocyticDefense extends JFrame implements ActionListener, KeyListener
{
  /** This object is the pane in which Leukocytic Defense takes place.
    */
  GameScreen g;
  
  /** This object is used to open up the .chm file.
    */
  Desktop desk = Desktop.getDesktop();
  
  /** This constructor creates the JFrame, with the game title, sets window properties 
    * and adds a GameScreen object to the frame. 
    * <p><b>Variable Dictionary: Reference, Type, Purpose</b>
    * <ul>
    * <li>bar, JMenuBar, is the bar that holds the file and help menus
    * <li>helpMe, JMenu, is the menu that holds the option to open up the .chm file
    * <li>file, JMenu, is the menu that holds the option to open up the main menu
    * <li>helpItem, JMenuItem, opens up the .chm file
    * <li>fileItem, JMenuItem, opens up the main menu
    * </ul>
    */
  public LeukocyticDefense() {
    super("Leukocytic Defense");
    JMenuBar bar = new JMenuBar();
    JMenu helpMe = new JMenu("Help");
    JMenu file = new JMenu("File");
    JMenuItem helpItem = new JMenuItem("Help");
    JMenuItem fileItem = new JMenuItem("Main Menu");
    helpMe.add(helpItem);
    file.add(fileItem);
    setJMenuBar(bar);
    bar.add(file);
    bar.add(helpMe);
    helpItem.addActionListener(this);
    fileItem.addActionListener(this);
    add(g = new GameScreen());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    setVisible(true);
    pack();
    addKeyListener(this);
    setLocationRelativeTo(null);
    setFocusable(true);
    requestFocus();
  }
  
  /** This method overrides the abstract method actionPerformed and executes instructions according to what menu item is pressed.
    * @param ae ActionEvent takes in actionevents from jmenubar
    */
  public void actionPerformed(ActionEvent ae) {
    if (ae.getActionCommand().equals("Help")) {
      try {
        desk.open(new File("User Manual.chm"));
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
    else
      g.gsm.setState(g.gsm.MENUSTATE);
  }
  
  /** This method overrides the abstract method keyPressed and checks if the user pressed P.
    *  @param e KeyEvent takes in a key event
    * @param k int gets the key code of the key event
    */
  public void keyPressed(KeyEvent e) {
    int k = e.getKeyCode();
    if (k == KeyEvent.VK_P)
      g.check = true;
  }
  
  /** This method overrides the abstract method keyReleased.
    * @param e KeyEvent takes in a key event
    */
  public void keyReleased(KeyEvent e) {}
  
  /** This method overrides the abstract method keyTyped.
    * @param e KeyEvent takes in a key event
    */
  public void keyTyped(KeyEvent e) {}
  
  /** This method is the main method of the class, and creates a LeukocyticDefense object.
    * @param args Takes in the command-line arguments.
    */
  public static void main (String[]args) {
    new LeukocyticDefense();
  }
}
