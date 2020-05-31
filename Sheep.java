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
	private final int halfsize = 20;
	
	
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
    private final int maxTime = 200;
    
    public void doMovement(int px,int py){
    	
    	
    	if (!isCaught){
    		time++;
    		if (time < maxTime){
    			
    			//in bounds (0-8000, 0-6000)
    			dx = Math.max((double)halfsize, Math.min((double)(8000 - halfsize), dx));
    			dy = Math.max((double)halfsize, Math.min((double)(6000 - halfsize), dy));
    			
    			
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
    	
    	
    	//update position 
    	int oldx = x;
    	int oldy = y;
    	
    	//(bitmask check comes here later)
    	dx += vx;
    	dy += vy;
    	
    	
    	x = (int)dx;
    	y = (int)dy;
    	
    	if (x != oldx || y != oldy){
    		incrementframe();
    	}
    	
    }
    
    
    //frame of the guy
    private int frame = randint(0,3);//0,1,2
    private final int BUFFERTIME = 10;
    private int buffer = randint(0,BUFFERTIME);//up to time
    
    private void incrementframe(){
    	buffer++;
    	if (buffer == BUFFERTIME){
    		buffer = 0;
    		frame++;
    	}
    	frame = frame%4;//4 pics
    	
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
    	vx *= 0.95;
    	vy *= 0.95;
    	if (Math.abs(vx) < 0.001){
    		vx = 0;
    	}
    	if (Math.abs(vy) < 0.001){
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
			
			BufferedImage temp;
			if (vx > 0){
				temp = Sprites.getSheepR(frame);
			}
			else{
				temp = Sprites.getSheepL(frame);	
			}
			g.drawImage(temp, x - temp.getWidth()/2 - screenx, y - temp.getHeight()/2 - screeny, null);
			
			/*
	    	//temp draw rect
	    	g.setColor(Color.BLUE);
	    	g.fillRect(x - halfsize - screenx, y - halfsize - screeny, 2*halfsize, 2*halfsize);
	    	*/
	    	
	    	
	    	
	    	
    	}
    }
    
    public int randint(int low, int high){
    	return (int)(Math.random()*(high-low+1)+low);
    }
    
    public double randouble(int low, int high){
    	return (Math.random()*(high-low+1)+low);
    }
    
    
}
