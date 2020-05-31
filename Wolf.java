//IMPORTING
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Rectangle;
import java.awt.Image;
import java.io.*;


public class Wolf {
 
 public boolean isAlive = true;
 
 private boolean deathAniFinished = false;
 public boolean isFinished(){
 	return deathAniFinished;
 }
 
 
 boolean attack=false;
 private int x, y;
 
 private double dx, dy, vx, vy;
 private final int halfsize = 20;
 
  private final double a = 0.15; //accellaration is high, but slowdown will also be high
  
  
 public int getX(){
  return x;
 }
 public int getY(){
  return y;
 }
 
 
 
    public Wolf(int X, int Y) {
     x=X;
     y=Y;
     dx = (double)x;
     dy = (double)y;
     
      vx = 0;
      vy = 0;
      
    }
    

    private final int aggroDist = 400;
    private final double maxS = 8.0;
    
    public void doAnyAction(int px, int py){
    	if (isAlive){
    		doMovement(px,py);
    	}
    	//playing the hit animation
    	else{
    		incrementDframe();
    	}
    	
    }
    
    
    private void doMovement(int px,int py){
     
     
     //only speed u[ if in range
     if (Math.hypot(px - x, py - y) < aggroDist){
     
      double newx, newy;
      
      //x velocity change
      if (x < px){
       vx += a;
      }
      else{
       vx -= a;
      }
      
      //y velocity cange
      if (y < py){
       vy += a;
      }
      else{
       vy -= a;
      }
      
      //cap the speed
      if (Math.abs(vx) > maxS){
       if (vx < 0){
        vx = -1*maxS;
       }
       else{
        vx = maxS;
       }
      }
      
      if (Math.abs(vy) > maxS){
       if (vy < 0){
        vy = -1*maxS;
       }
       else{
        vy = maxS;
       }
      }
      
 
     }
     //slow down if not in range
     else{
      slowDown();
     }
     
     int oldx = x;
     int oldy = y;
     
     
     //update position + check collision
     
     //collide with xy;
     Rectangle poten = new Rectangle((int)(dx+ vx) - halfsize, (int)(dy + vy) - halfsize, 2*halfsize, 2*halfsize);
     
     if (Bitmask.isRectClear(poten)){
      dx += vx;
      dy += vy; 
     }
     else{
     	vx = 0;
     	vy = 0;
     }
     
     
     x = (int)dx;
     y = (int)dy;
     
     if (oldx != x || oldy != y){
      incrementframe();
     }
     
     
    }
    
    private void slowDown(){
     vx *= 0.8;
     vy *= 0.8;
     
     if (Math.abs(vx) < 0.001){
      vx = 0;
     }
     if (Math.abs(vy) < 0.001){
      vy = 0;
     }
    }
    
    
    public Rectangle WolfBox(){
     return new Rectangle(x - halfsize, y - halfsize, 2*halfsize, 2*halfsize); //can be changed accordingly 
    }
    public void damage(){
     isAlive = false;
    }
    
    
    //frame of the guy
    private int frame = randint(0,2);//0,1,2
    private final int BUFFERTIME = 8;
    private int buffer = randint(0,BUFFERTIME);//up to time
    
    private void incrementframe(){
     buffer++;
     if (buffer == BUFFERTIME){
      buffer = 0;
      frame++;
     }
     frame = frame%3;//4 pics
     
    }
    
    
    //frame of the guy
    private int dframe = 0;
    private final int DBUFFERTIME = 8;
    private int dbuffer = 0;//up to time
    
    private void incrementDframe(){
     dbuffer++;
     if (dbuffer == DBUFFERTIME){
      dbuffer = 0;
      dframe++;
     }
     
     if (dframe == 15 && dbuffer == DBUFFERTIME - 1){
     	deathAniFinished = true;
     }
     
     
    }
    
    
    
    public void draw(Graphics2D g,int screenx,int screeny){
     
     if (isAlive){

		     BufferedImage temp;
		  if (vx > 0){
		   temp = Sprites.getWolfR(frame);
		  }
		  else{
		   temp = Sprites.getWolfL(frame); 
		  }
		  g.drawImage(temp, x - temp.getWidth()/2 - screenx, y - temp.getHeight()/2 - screeny, null);
     }
     //draw if dead
     else{
     	BufferedImage temp = Sprites.getWolfDeath(dframe);
     	g.drawImage(temp, x - temp.getWidth()/2 - screenx, y - temp.getHeight()/2 - screeny, null);
     }
  
  
     
     /*
     g.setColor(Color.GREEN);
     g.fillRect(x-halfsize-screenx,y-halfsize-screeny + 5,2*halfsize,2*halfsize);
     */
    }
    
    
    public int randint(int low, int high){
     return (int)(Math.random()*(high-low+1)+low);
    }
}