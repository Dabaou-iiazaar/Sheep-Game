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
 private boolean close=false;
 private Sheep closest;
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
 
 private int hitTime=0;//time since last hit from 20
 private int numSheepCaught = 0;
 
 public void move(boolean[] mouseHeld, boolean[] mouseClicked, int mx, int my, boolean[] keys){
  updateScreenPos(you);
  
  you.doAction(mouseHeld, mouseClicked, mx, my, keys, screenx, screeny,allWolves);
  //System.out.println("" + screenx + " " + screeny);
  updateScreenPos(you);
  
  
  //remove dead wolves
  for(int i = allWolves.size() - 1; i>= 0; i--){
   if (allWolves.get(i).isFinished()){
    allWolves.remove(i);
   }
  }
  
  int tempsheepcount = 0;
  for(Sheep sheep:allSheep){
     sheep.doMovement(you.getX(),you.getY());
     sheep.potentialCatch(you.PlayerBox());
     if (sheep.isCaught){
      tempsheepcount++;
     }
   }
   numSheepCaught = tempsheepcount;
   
   
   for(Wolf w : allWolves){
   w.doAnyAction(you.getX(),you.getY());
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
  
  
  //indicators
  
  
  //box that points towards closest sheep
  if(!close){
    closest=closestSheep();
  }
  else{
    if(closest.isCaught){
      close=false;
    }
  }
  double temp = Math.atan2((double)(closest.getY()-screeny) - (double)(you.getY() - screeny),(double)(closest.getX()-screenx) - (double)(you.getX() - screenx));
  temp = Math.toDegrees(temp);
  temp += 36000000;
  temp %= 360;
  double cy=Math.sin(Math.toRadians(temp))*100-10;
  double cx=Math.cos(Math.toRadians(temp))*100-10;
  g2d.setColor(new Color(245, 129, 66, 150));
  if(closest.getY()!=-1000 && closest.getX()!=-1000){
    g2d.fillRect(you.getX()-screenx+(int)cx,you.getY()-screeny+(int)cy,20,20);
  }
  //sheep icon
  BufferedImage sheeplogo = Sprites.getSheepL(2);
  g2d.drawImage(Sprites.getSheepL(2), 675 - sheeplogo.getWidth(), 100 - sheeplogo.getHeight(), null);
  g2d.setColor(Color.WHITE);
  g2d.setFont(new Font("Serif", Font.BOLD, 50));
  
  String sheepcount = "x " + numSheepCaught;
  g2d.drawString(sheepcount, 650, 50);
  
  int health = you.getHP();
  String healthtext = "HP = " + health;
  g2d.drawString(healthtext, 575, 100);
  
  //flash red when hit
  if (hitTime > 10){

   g2d.setColor(new Color(245, 129, 66, 150));
   g2d.fillRect(0,0,800,600);
  }
  
 }
  public Sheep closestSheep(){
    double min=9999999;
    Sheep ret=new Sheep(-1000,-1000);
    for(Sheep sheep:allSheep){
      if(!sheep.isCaught){
        double dist=Math.hypot(sheep.getX()-you.getX(),sheep.getY()-you.getY());
        if(dist<min){
          min=dist;
          ret=sheep;
          close=true;
        }
      }
    }
    return ret;
  }
 }
 
 
 
 
 
 
 
 