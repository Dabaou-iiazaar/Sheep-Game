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
public class GamePanel extends JPanel{
 
 private MainG mainFrame;
 
 private final int mapWidth = 8000;
 private final int mapHeight = 6000;
 public ArrayList<Wolf> allWolves=new ArrayList<Wolf>();
 public ArrayList<Sheep> allSheep=new ArrayList<Sheep>();
 
 
 
 public GamePanel(MainG mainFrame){
  this.mainFrame = mainFrame;
  
  this.requestFocus();
  
  /*
  keys = new boolean[KeyEvent.KEY_LAST];
  Arrays.fill(keys,false);
  mouseHeld = new boolean[4];
  Arrays.fill(mouseHeld,false);
  mouseClicked = new boolean[KeyEvent.KEY_LAST];
  Arrays.fill(mouseClicked,false);
  
  addKeyListener(this);
  addMouseListener(this);
  addMouseMotionListener(this);
  */
  
  //test load sheeps into the world
  allSheep.add(new Sheep(100,100));
  allSheep.add(new Sheep(1370,1130));
  allSheep.add(new Sheep(3400,1500));
  allSheep.add(new Sheep(3800,2200));
  allSheep.add(new Sheep(6666,594));
  allSheep.add(new Sheep(7700,1900));
  allSheep.add(new Sheep(4000,3800));
  allSheep.add(new Sheep(927,4000));
  allSheep.add(new Sheep(7566,4644));
  allSheep.add(new Sheep(4700,5500));
  allSheep.add(new Sheep(5000,5600));
  allSheep.add(new Sheep(5900,3800));
  allSheep.add(new Sheep(1700,1460));
  allSheep.add(new Sheep(4000,2000));
  allSheep.add(new Sheep(6000,600));
  allSheep.add(new Sheep(250,4000));
  allSheep.add(new Sheep(3875,3000));
  allSheep.add(new Sheep(4600,5000));
  allSheep.add(new Sheep(6444,500));
  allSheep.add(new Sheep(7200,2000));
  allSheep.add(new Sheep(7800,200));
  allSheep.add(new Sheep(3300,1500));
  allSheep.add(new Sheep(2000,4050));
  allSheep.add(new Sheep(6500,5000));
  allSheep.add(new Sheep(4000,4500));
  allSheep.add(new Sheep(2000,100));
  allSheep.add(new Sheep(5000,150));
  allSheep.add(new Sheep(7000,100));
  allSheep.add(new Sheep(3600,5600));
  allSheep.add(new Sheep(7000,3800));
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
  //System.out.println("" + p.getX() + " " + p.getY());
  
  
 }
 
 Player you = new Player(0, 0);
 
 private int hitTime=20;
 
 
 public void move(boolean[] mouseHeld, boolean[] mouseClicked, int mx, int my, boolean[] keys){
  updateScreenPos(you);
  
  you.doAction(mouseHeld, mouseClicked, mx, my, keys, screenx, screeny,allWolves);
  //System.out.println("" + screenx + " " + screeny);
  updateScreenPos(you);
  
  
  //remove dead wolves
  for(int i = allWolves.size() - 1; i>= 0; i--){
   if (!allWolves.get(i).isAlive){
    allWolves.remove(i);
   }
  }
   
  for(Sheep sheep:allSheep){
     sheep.doMovement(you.getX(),you.getY());
     sheep.potentialCatch(you.PlayerBox());
   }
   
   
   for(Wolf w : allWolves){
  	w.doMovement(you.getX(),you.getY());
  	if (w.WolfBox().intersects(you.PlayerBox()) && hitTime<=0){
      you.damage();
      hitTime=20;
      scatterAllSheep();
      
      
      
    }
    else{
       hitTime-=1;
       hitTime = Math.max(-10, hitTime);
     }
  }
   
   
   Arrays.fill(mouseClicked, false);
   
 }
 
 private void scatterAllSheep(){
 	for (Sheep s : allSheep){
 		if (s.isCaught){
 			s.scatter();
 		}
 	}
 	
 }
 
 
 @Override
 public void paintComponent(Graphics g){
  Graphics2D g2d = (Graphics2D)g;
  
  /*
  g2d.setColor(Color.WHITE);
  g2d.fillRect(0,0,800,600);
  */
  
  //map
  g2d.drawImage(Bitmask.getMap(), -1*screenx, -1*screeny, null);
  
  
  
  you.draw(g2d, screenx, screeny);
  for(Sheep sheep:allSheep){
     sheep.draw(g2d,screenx,screeny);
   }
   
  for (Wolf w : allWolves){
   w.draw(g2d, screenx, screeny);
  }
  
  //blackness
  g2d.drawImage(Bitmask.getDark(), -1*screenx, -1*screeny, null);
  
  
 }
 /*
 
 
 
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
 
 
 
 private int mx, my;
 
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
 
 */
 
 
 
 
 
 
 
 
}