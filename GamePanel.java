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
 private int wolfTime=500;
 private int sheepCol=0;
 private boolean close=false;
 private Sheep closest;
 public ArrayList<Wolf> allWolves=new ArrayList<Wolf>();
 public ArrayList<Sheep> allSheep=new ArrayList<Sheep>();
 public ArrayList<Sheep> penSheep=new ArrayList<Sheep>();
 
 
 
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
 
 Player you = new Player(3880,5700);
 private boolean gameover = false;
 
 
 private int hitTime=0;//time since last hit from 20
 private int numSheepCaught = 0;
 private int score = 0;
 
 public void move(boolean[] mouseHeld, boolean[] mouseClicked, int mx, int my, boolean[] keys){
  
  
  updateScreenPos(you);
  
  
  //only if your not go
  if (!gameover){
   you.doAction(mouseHeld, mouseClicked, mx, my, keys, screenx, screeny,allWolves); 
  }
  
  //System.out.println("" + screenx + " " + screeny);
  updateScreenPos(you);
  
  //sheep collecting
  if(you.getX()>3600 && you.getX()<4160 && you.getY()>5850){
    sheepCol+=numSheepCaught;
    numSheepCaught=0;
    for(int i = allSheep.size() - 1; i>= 0; i--){
    	
      if (allSheep.get(i).isCaught){
        allSheep.remove(i);
        
        
        penSheep.add(new Sheep(randint(3700, 4060), 5950));
        score++;
      }
    }
  }
  //System.out.println(sheepCol);
  
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
   
   for(Sheep sheep:penSheep){
     sheep.doMovement(you.getX(),you.getY());
    
   }
   
   wolfTime-=1;
   if(wolfTime<=0){
     int numW=allWolves.size();
     adder(allWolves,you);
     int numWW=allWolves.size();
     if(numW!=numWW){
       wolfTime=500;
     }
     else{
       wolfTime=0;
     }
   }
   for(Wolf w : allWolves){
   w.doAnyAction(you.getX(),you.getY());
   if (w.WolfBox().intersects(you.PlayerBox()) && hitTime<=0 && w.isAlive){
      you.damage();
      hitTime=100;
      scatterAllSheep();
       
    }
    else{
       hitTime-=1;
       hitTime = Math.max(-10, hitTime);
     }
   }
   
   
   if (you.getHP() <= 0 || allSheep.isEmpty()){
     gameover = true;
   }
   
   

 if (gameover){
  
  //you click
  if (mouseClicked[1]){
   mainFrame.kill();
   MainG.main(new String[0]);
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
 
 
 private float transparent = (float)0.0;
 
 @Override
 public void paintComponent(Graphics g){
  Graphics2D g2d = (Graphics2D)g;
  
  
  

   
  //map
  g2d.drawImage(Bitmask.getMap(), -1*screenx, -1*screeny, null);
  
  
  
  you.draw(g2d, screenx, screeny);
  for(Sheep sheep:allSheep){
     sheep.draw(g2d,screenx,screeny);
   }
   
   for(Sheep sheep:penSheep){
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
  
  
  String scoretext = "Score = " + score;
  g2d.drawString(scoretext, 575, 150);
  
  //flash red when hit
  if (hitTime > 90){

   g2d.setColor(new Color(245, 129, 66, 150));
   g2d.fillRect(0,0,800,600);
  }

  
  //draw gameover screen
  if (gameover){
   g2d.setColor(new Color(245, 129, 66));
   g2d.fillRect(0,0,800,600);
   g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparent));
   
   if (!allSheep.isEmpty()){
	   BufferedImage ttemp = Sprites.getGameOver();
	   g2d.drawImage(ttemp,0,0,null);
   }
   else{
   		BufferedImage ttemp = Sprites.getWin();
	   g2d.drawImage(ttemp,0,0,null);
   }
   
   String endscore = "Your Score: " + score;
   g2d.drawString(endscore, 250, 500);
   
   transparent += 0.01;
   transparent = (float)Math.min(1.0, transparent);
   
   
   
   
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
  public void adder(ArrayList<Wolf> allWolves,Player you){
    double degree=(double)randint(0,360);
    degree=Math.toRadians(degree);
    double sx=Math.cos(degree)*500;
    double sy=Math.sin(degree)*500;
    Wolf tempW=new Wolf(you.getX()+(int)sx,you.getY()+(int)sy);
    if(Bitmask.isRectClear(tempW.WolfBox())){
      allWolves.add(tempW);
    }
  }
  public int randint(int low, int high){
     return (int)(Math.random()*(high-low+1)+low);
    }
 }
 
 
 
 
 
 
 
 