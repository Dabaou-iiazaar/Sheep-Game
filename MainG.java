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
  GamePanel game;
  public MainG() {
    super("Sheep Game");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(800,600);
    myTimer = new Timer(40, new TickListener());
    myTimer.start();
    game = new GamePanel(this);
    add(game);
    setResizable(false);
    setVisible(true);
  }

class TickListener implements ActionListener{
    public void actionPerformed(ActionEvent evt){
      game.move();
      game.repaint();
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
    MainG frame = new MainG();
  }
}