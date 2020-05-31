import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.image.*; 
import java.io.*; 
import javax.imageio.*; 
import java.util.*;

//the class that packs together all of the objects and stuff
public class GamePanel extends JPanel implements KeyListener, MouseListener, MouseMotionListener{
 
 private MainG mainFrame;
 
 private final int mapWidth = 8000;
 private final int mapHeight = 6000;
 public ArrayList<Wolf> allWolves=new ArrayList<Wolf>();
 public ArrayList<Sheep> allSheep=new ArrayList<Sheep>();
 
 
 
 public GamePanel(MainG mainFrame){
  this.mainFrame = mainFrame;
  
  keys = new boolean[KeyEvent.KEY_LAST];
  Arrays.fill(keys,false);
  mouseHeld = new boolean[4];
  Arrays.fill(mouseHeld,false);
  mouseClicked = new boolean[KeyEvent.KEY_LAST];
  Arrays.fill(mouseClicked,false);
  
  addKeyListener(this);
  addMouseListener(this);
  addMouseMotionListener(this);
  
  
  //test load sheeps into the world
  allSheep.add(new Sheep(100,100));
  allWolves.add(new Wolf(500,500));
  
     
 }
 
 public void addNotify(){
  super.addNotify();
  requestFocus();
  
 }
 
 int screenx, screeny;
 
 public void updateScreenPos(Player p){
  
  //800x600
  screenx = p.getX() - 400;
  screeny = p.getY() - 300;
  
  screenx = Math.min(mapWidth - 800, Math.max(0, screenx));
  screeny = Math.min(mapHeight - 600, Math.max(0, screeny));
  System.out.println("" + p.getX() + " " + p.getY());
  
  
 }
 
 Player you = new Player(0, 0);
 
 public void move(){
  updateScreenPos(you);
  
  you.doAction(mouseHeld, mouseClicked, mx, my, keys, screenx, screeny,allWolves);
  //System.out.println("" + screenx + " " + screeny);
  updateScreenPos(you);
  
   
  for(Sheep sheep:allSheep){
     sheep.doMovement(you.getX(),you.getY());
     sheep.potentialCatch(you.PlayerBox());
   }
   
   
   for(Wolf w : allWolves){
 	w.doMovement(you.getX(),you.getY());
 	}
   
   
 }
 
 
 
 
 @Override
 public void paintComponent(Graphics g){
  Graphics2D g2d = (Graphics2D)g;
  
  g2d.setColor(Color.WHITE);
  g2d.fillRect(0,0,800,600);
  
  
  
  
  you.draw(g2d, screenx, screeny);
  for(Sheep sheep:allSheep){
     sheep.draw(g2d,screenx,screeny);
   }
   
  for (Wolf w : allWolves){
  	w.draw(g2d, screenx, screeny);
  }
  
  
  
  
  
 }
 
 
 /*   keyboard   */
 
 private boolean[] keys;
 
 @Override
 public void keyTyped(KeyEvent e){
  
 }
 
 public void keyPressed(KeyEvent e){
  keys[e.getKeyCode()] = true;
 }
 
 public void keyReleased(KeyEvent e){ 
  keys[e.getKeyCode()] = false; 
 }
 
 
 /*  mouse  */
 private int mx, my;
 
 private boolean mouseHeld[];
 private boolean mouseClicked[];
 
 
 @Override
 
 public void mouseClicked(MouseEvent e){
  
 }
 
 public void mousePressed(MouseEvent e){
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
