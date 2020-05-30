import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.image.*; 
import java.io.*; 
import javax.imageio.*; 
import java.util.*;
import java.lang.Math;

public class Player {

 private int x,y;
 
 public int getX(){
  return x;
 }
 public int getY(){
  return y;
 }
 
 private double ang;
 
 private final int speed = 4;
 private final int halfsize = 20;
 private int shotRange=30;

 public Rectangle PlayerBox(){
  return new Rectangle (x - halfsize, y - halfsize, 2*halfsize, 2*halfsize);
 }
 


    public Player(int x, int y) {
     this.x = x;
     this.y = y;
     
    }
    
    
    public void doAction(boolean[] mouseHeld,boolean[] mouseClicked,  int mx, int my, boolean[] keys, int screenx, int screeny,ArrayList<Wolf> allWolves){
     
     adjustAng(mx,my, screenx, screeny);
     //System.out.println(ang);
     move(keys);
     if(mouseClicked[0]){
       shoot(allWolves, screenx,screeny);
     }
    }
    
    private void shoot(ArrayList<Wolf> allWolves,int screenx, int screeny){
      
      ArrayList<Wolf> allWolves=Wolf.allWolves;
      for(Wolf wolf:allWolves){
        Rectangle wolfB=wolf.WolfBox();
        int wolfX=(wolfB.x+wolfB.width+wolfB.x)/2;
        int wolfY=(wolfB.y+wolfB.height+wolfB.y)/2;
        
        double temp = Math.atan2((double)wolfX - (double)(x - screenx), (double)wolfY - (double)(y - screeny));
        temp = Math.toDegrees(temp);
        temp += 36000000;
        temp %= 360;
        if(Math.abs(temp-ang)<=shotRange){
          //Shoot them.
          wolf.damage();
          System.out.println("Shot");
          
        }
      }
      
    }
    
    private void adjustAng(int mx, int my, int screenx, int screeny){
     
     
     
     int tx = x - screenx;
     int ty = y - screeny;
     
     //System.out.println("" + mx + " " + my + " " +screenx+" " + screeny);
     
     
     double temp = Math.atan2((double)my - (double)ty, (double)mx - (double)tx);
     temp = Math.toDegrees(temp);
     temp += 36000000;
     temp %= 360;
     
     ang = temp;
     
    }
    
    private void move(boolean[] keys){
     
     if (keys[getCode('W')]){
      y -= speed;
     }
     
     if (keys[getCode('S')]){
      y += speed;
     }
     
     if (keys[getCode('A')]){
      x -= speed;
     }
     
     if (keys[getCode('D')]){
      x += speed;
     }
    }
    
    
    
    
    private int getCode(char letter){
     return (int)Character.toUpperCase(letter);
    }
    
    
    
    public void draw(Graphics2D g, int screenx, int screeny){
     
     //temp
     g.setColor(Color.RED);
     g.drawRect(x - halfsize - screenx, y - halfsize - screeny, 2*halfsize, 2*halfsize);
     
    }
    
    
    
    
}