import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;  
import javax.imageio.*; 
import javax.sound.midi.*;
import java.applet.*;
import java.lang.Math;
import javax.swing.Timer;

public class MainG extends JFrame implements KeyListener, MouseListener, MouseMotionListener{
	
	
  private static Sequencer midiPlayer;
  Timer myTimer;
  GamePanel game;
  menu menu;

  private int scene = 0;
  
  JPanel cards;
  CardLayout cLayout;
  
  public MainG() {
    super("Sheep Game");
    
    
    keys = new boolean[KeyEvent.KEY_LAST];
  Arrays.fill(keys,false);
  mouseHeld = new boolean[4];
  Arrays.fill(mouseHeld,false);
  mouseClicked = new boolean[KeyEvent.KEY_LAST];
  Arrays.fill(mouseClicked,false);
  
  addKeyListener(this);
  addMouseListener(this);
  addMouseMotionListener(this);
    
    cLayout = new CardLayout();
    cards = new JPanel(cLayout);
    menu = new menu();

    game = new GamePanel(this);
    //add(game);
    cards.add(game, "game");
    cards.add(menu, "menu");
    
    add(cards);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(800,600);
    myTimer = new Timer(10, new TickListener());
    myTimer.start();
    setResizable(false);
    setVisible(true);
  }

class TickListener implements ActionListener{
    public void actionPerformed(ActionEvent evt){
        if(scene == 0){
            cLayout.show(cards, "menu");
            menu.repaint();
            
            if(menu.clickedPlay(mouseClicked, mx,my)){
                scene+=1;
            }
            
        }
        if(scene == 1){
            cLayout.show(cards, "game");
            game.move(mouseHeld, mouseClicked, mx, my, keys);
            game.repaint();
        }
        
    }
  }
  public static void startMidi(String midFilename,int len) {
    try {
      File midiFile = new File(midFilename);
      Sequence song = MidiSystem.getSequence(midiFile);
      midiPlayer = MidiSystem.getSequencer();
      midiPlayer.open();
      midiPlayer.setSequence(song);
      midiPlayer.setLoopCount(len);
      midiPlayer.start();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public static void main(String[] args){
  	System.setProperty("sun.java2d.opengl", "True");
  	
  	
  	//call Bitmask.load(String maskpath, String mappath))
  	
  	MainG.loadAll();
  	
  	
    MainG frame = new MainG();
  }
  
  
  public void kill(){
  	myTimer.stop();
  	dispose();
  }
  
  public static boolean alreadyLoaded = false;
  public static void loadAll(){
  	if (!alreadyLoaded){
	  	 Bitmask.load("ColourMask.png", "LightMask.png", "MainMap.png");
	  	 Sprites.load();
	  	 alreadyLoaded = true;
  	}
  }
  
  
  /*   keyboard   */
 
 private boolean[] keys;
 
 @Override
 public void keyTyped(KeyEvent e){
  
 }
 
 public void keyPressed(KeyEvent e){
  keys[e.getKeyCode()] = true;
  System.out.println("key!");
 }
 
 public void keyReleased(KeyEvent e){ 
  keys[e.getKeyCode()] = false; 
 }
 
 
 /*  mouse  */
 private int mx  = -10, my = -10;
 
 private boolean mouseHeld[];
 private boolean mouseClicked[];
 
 
 @Override
 
 public void mouseClicked(MouseEvent e){
  
 }
 
 public void mousePressed(MouseEvent e){
  
 System.out.println("pressed");
  mouseHeld[e.getButton()] = true;
  
  if (mouseClicked[e.getButton()] == false){
   mouseClicked[e.getButton()] = true;
  }
  else{
   mouseClicked[e.getButton()] = false;
  }
  
 
 }
 
 public void mouseReleased(MouseEvent e){
  mouseHeld[e.getButton()] = false;
  mouseClicked[e.getButton()] = false;
 }
 
 public void mouseEntered(MouseEvent e){
  
 }
 
 public void mouseExited(MouseEvent e){
  
 }
 
 public void mouseMoved(MouseEvent e){
  mx = e.getX();
  my = e.getY();
 }
 
 public void mouseDragged(MouseEvent e){
  mx = e.getX();
  my = e.getY();
 }
}