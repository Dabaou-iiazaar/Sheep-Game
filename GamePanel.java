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

public class MainG extends JFrame{
	
	
  private static Sequencer midiPlayer;
  Timer myTimer;
  GamePanel game, menu;

  private int scene;
  
  JPanel cards;
  CardLayout cLayout;
  
  public MainG() {
    super("Sheep Game");
    
    cLayout = new CardLayout();
    cards = new JPanel(cLayout);
    

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
            if(menu.clickedPlay()){
                scene+=1;
            }
        }
        if(scene == 1){
            cLayout.show(cards, "game");
            game.move();
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
  	
  	// Bitmask.load("ColourMask.png", "LightMask.png", "MainMap.png");
  	// Sprites.load();
  	
  	
    MainG frame = new MainG();
  }
}
