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
 
	boolean attack=false;
	private int x, y;
	
	private double dx, dy, vx, vy;
	private final int halfsize = 10;
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
    public void doMovement(int px,int py){
    	
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
    	
    	dx += vx;
	    dy += vy;
    	
    	x = (int)dx;
    	y = (int)dy;
    	
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
    	return;
    }
    public void draw(Graphics2D g,int screenx,int screeny){
    	g.setColor(Color.GREEN);
    	g.fillRect(x-halfsize-screenx,y-halfsize-screeny,2*halfsize,2*halfsize);
    }
}
