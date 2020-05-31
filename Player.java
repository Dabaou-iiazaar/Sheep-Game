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
 private final int shotRange=15;
 private final int shotDist = 200;

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
     if(mouseClicked[1]){
       shoot(allWolves, screenx,screeny);
     }
    }
    
    private void shoot(ArrayList<Wolf> allWolves,int screenx, int screeny){

      for(Wolf wolf:allWolves){
        int wolfX=wolf.getX();
        int wolfY=wolf.getY();
        
        double temp = Math.atan2((double)(wolfY-screeny) - (double)(y - screeny),(double)(wolfX-screenx) - (double)(x - screenx));
        temp = Math.toDegrees(temp);
        temp += 36000000;
        temp %= 360;
        if(temp<=360 && temp>=270 && ang>=0 && ang<=90){
          ang+=360;
        }
        else if(ang<=360 && ang>=270 && temp>=0 && temp<=90){
          temp+=360;
        }
        if(Math.abs(temp-ang)<=shotRange && Math.hypot(x - wolfX, y - wolfY) < shotDist){
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
      if(y<halfsize){
        y=halfsize;
      }
     }
     
     if (keys[getCode('S')]){
      y += speed;
      if(y>6000 -halfsize){
        y=6000-halfsize;
      }
     }
     
     if (keys[getCode('A')]){
      x -= speed;
      if(x<halfsize){
        x=halfsize;
      }
     }
     
     if (keys[getCode('D')]){
      x += speed;
      if(x>8000-halfsize){
        x=8000-halfsize;
      }
     }
    }
    
    
    
    
    private int getCode(char letter){
     return (int)Character.toUpperCase(letter);
    }
    
    
    
    public void draw(Graphics2D g, int screenx, int screeny){
     
     //temp
     g.setColor(Color.RED);
     g.drawRect(x - halfsize - screenx, y - halfsize - screeny, 2*halfsize, 2*halfsize);
     
     g.setColor(Color.BLUE);
     g.drawOval(x - shotDist - screenx, y - shotDist - screeny, 2*shotDist, 2*shotDist);
     
     g.setColor(new Color(255,0,0,100));
     int rang = 360 - (int)ang;
     
     g.fillArc(x - shotDist - screenx, y - shotDist - screeny, 2*shotDist, 2*shotDist, rang - 15, 30);
     
     
    }
    
    
    
    
}
