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

public class Sheep {
	

	
	private int x, y;
	private final int halfsize = 10;
	
	
	//real double coordinates
	private double dx,dy,vx,vy;
	private final int maxSpeed = 5;
	private final int panicSpeed = 10;
	
	
	private double ang;
	
	
	
	
	
    public Sheep(int X, int Y) {
    	x=X;
    	y=Y;
    	dx = (double)x;
    	dy = (double)y;
    	isCaught = false;
    	isScattering = false;
    	vx = 0;
    	vy = 0;
    	time = randint(0,maxTime);
    	
    }
    
    private boolean isCaught;
    
    private int time;
    private final int maxTime = 300;
    
    public void doMovement(int px,int py){
    	
    	
    	if (!isCaught){
    		time++;
    		if (time < maxTime){
    			//regular move
    			dx += vx;
    			dy += vy;
    			slowDown();
	
    		}
    		else{
    			//make new vxvy
    			time = 0;
    			isScattering = false;
    			vx = randouble(-1*maxSpeed, maxSpeed - 1) + Math.random();
    			vy = randouble(-1*maxSpeed, maxSpeed - 1) + Math.random();
    		}
    		
	
    	}
    	else{
    		
    		dx = (double)px;
    		dy = (double)py;
    		vx = 0;
    		vy = 0;
    		time = 0;
    		
    	}
    	
    	x = (int)dx;
    	y = (int)dy;
    	
    }
    
    public void setCaught(boolean val){
    	isCaught = val;
    }
    
    
    boolean isScattering;
    double[] scatSpeeds = new double[]{panicSpeed - 1, -1*panicSpeed};
    
    //only call once at hit
    public void scatter(){
    	isCaught = false;
    	isScattering = true;
    	
    	//extends the timer
    	time = -300;
    		
    	vx = scatSpeeds[randint(0,1)] + Math.random();
    	vy = scatSpeeds[randint(0,1)]+ Math.random();
    	
    	dx += vx;
    	dy += vy;
    	
    	
    }
    
    private void slowDown(){
    	vx *= 0.9;
    	vy *= 0.9;
    	if (vx < 0.01){
    		vx = 0;
    	}
    	if (vy < 0.01){
    		vy = 0;
    	}
    }
    
    //called every frame every sheep
    public void potentialCatch(Rectangle playerbox){
    	if (SheepBox().intersects(playerbox) && !isScattering){
    		isCaught = true;
    	}
	
    }
    
    public Rectangle SheepBox(){
    	return new Rectangle(x - halfsize, y - halfsize, 2*halfsize, 2*halfsize); //can be changed accordingly 
    }
    
    public void draw(Graphics2D g, int screenx, int screeny){
    	
    	if (!isCaught){

	    	//temp draw rect
	    	g.setColor(Color.BLUE);
	    	g.fillRect(x - halfsize - screenx, y - halfsize - screeny, 2*halfsize, 2*halfsize);
    	}
    }
    
    public int randint(int low, int high){
    	return (int)(Math.random()*(high-low+1)+low);
    }
    
    public double randouble(int low, int high){
    	return (Math.random()*(high-low+1)+low);
    }
    
    
}
