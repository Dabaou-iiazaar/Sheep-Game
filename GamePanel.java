//GamePanel.java for Sheep Game
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
 
 //Start of Update for Menu
 private int screen; //value of screen you're on
 public static final int MENU=1, GAME=2; //non-magic number values for screen transition
 private Point mouse; //to track mouse location
 private Image bUp, bDown; //Buttons TBD
 private Rectangle mInstructions, mPlay;
 //End of Update
 
 public GamePanel(MainG mainFrame){
  this.mainFrame = mainFrame;
  
  //SoU Menu
  screen=MENU;
  mInstructions = new Rectangle(300,300,200,50); //placeholders until button size determined
  mPlay = new Rectangle(300,400,200,50);
  //bUp = new ImageIcon("buttons/button1_up.png").getImage(); //placeholders until we find first button image
  //bDown = new ImageIcon("buttons/button1_down.png").getImage();
  //EoU Menu
  
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
  //System.out.println("" + p.getX() + " " + p.getY());
  
  
 }
 
 Player you = new Player(0, 0);
 
 public void move(){
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
 	}
   
   
   Arrays.fill(mouseClicked, false);
   
 }
 //SoU Menu
 
 /*
 public void update(){
	if(screen == GAME){
		move();
	}
	mouse = MouseInfo.getPointerInfo().getLocation();
	Point offset = getLocationOnScreen();
	mouse.translate(-offset.x, -offset.y);
 }
 */
 
 //EoU Menu
 
 /*   drawing   */
 @Override
 	
 //SoU Menu	
 
 public void imgToRect(Graphics g, Image img, Rectangle area){
	g.drawImage(img, area.x, area.y, area.width, area.height, null);
 }
 
 public void drawMenu(Graphics g){
	g.setColor(new Color(0xB1C4DF));  
	g.fillRect(0,0,800,600);
	if(mInstructions.contains(mouse)){
		imgToRect(g, bUp, mInstructions);
	}
	else{
		imgToRect(g, bDown, mInstructions);			
	}
	if(mPlay.contains(mouse)){
		imgToRect(g, bUp, mPlay);
	}
	else{
		imgToRect(g, bDown, mPlay);
	}
 }
 /*
 public void drawGame(Graphics g){
	g.setColor(new Color(0xB1DFC4));
	g.fillRect(0,0,800,600);
	g.setColor(Color.blue);
	g.fillRect(boxx,boxy,40,40);
 }
 
 
 public void paint(Graphics g){
    if(screen == MENU){
    	drawMenu(g);
    }
    else if(screen == GAME){
    	drawGame(g);
    }
    else if(screen == INSTRUCTIONS){
    	drawInstructions(g);
    }
 }
 */
 
 //EoU Menu
    
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
 	
 System.out.println("pressed");
 
  mouseHeld[e.getButton()] = true;
  
  if (mouseClicked[e.getButton()] == false){
   mouseClicked[e.getButton()] = true;
  }
  else{
   mouseClicked[e.getButton()] = false;
  }
  
  //SoU for Menu
  if(screen == MENU){
	if(mInstructions.contains(mouse)){
		screen = INSTRUCTIONS;	
	}
	if(mPlay.contains(mouse)){
		screen = GAME;
	}
  }
  //EoU for Menu
  
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
