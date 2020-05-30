import java.util.*;//Importing for graphics and other helpful additions.
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;  
import javax.imageio.*; 
import javax.sound.midi.*;
import java.applet.*;
import java.lang.Math;
import javax.swing.Timer;//Specifying which Timer since there would be a conflict with util otherwise.
public class MainG extends JFrame{
  GamePanel game;
  public MainG() {//Constructor.
    super("Sheep Game");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(1000,700);
    myTimer = new Timer(40, new TickListener());
    myTimer.start();
    game = new GamePanel(this);
    add(game);
    setResizable(false);
    setVisible(true);
  }

class TickListener implements ActionListener{//Class and its one method to update the graphics on screen every time the Timer tells them to.
    public void actionPerformed(ActionEvent evt){
      game.repaint();
    }
  }
  public static void startMidi(String midFilename,int len) {//Method for playing the music and loading it up.
    try {//Midi music player function taken from Mr. Mckenzie.
      File midiFile = new File(midFilename);//Getting the music to be loaded in the following lines.
      Sequence song = MidiSystem.getSequence(midiFile);
      midiPlayer = MidiSystem.getSequencer();
      midiPlayer.open();
      midiPlayer.setSequence(song);
      midiPlayer.setLoopCount(len);//In effect the music lasts forever.
      midiPlayer.start();
    } catch (MidiUnavailableException e) {//Below is all for catching potential errors when loading the music.
      e.printStackTrace();
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public static void main(String[] args){//Main method.
    MainG frame = new MainG();//Launching the graphics.
  }
}