import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.image.*; 
import java.io.*; 
import javax.imageio.*; 
import java.util.*;


//everything is static so that all objects can access it



//NEED TO GET PICTURE, 8000x6000
public class Bitmask {
	
	private static BufferedImage mask;
	private static BufferedImage lightmask;
	private static BufferedImage map;
	
	//call once before main in MainG
	public static void load(String maskpath,String lightpath ,String mappath){
		try {
            mask = ImageIO.read(new File(maskpath));
        } 
        catch (IOException e) {
            System.out.println(e);
        }
        
        try{
        	lightmask = ImageIO.read(new File(lightpath));
        }
        catch(IOException e){
        	System.out.println(e);
        }
        
        try {
            map = ImageIO.read(new File(mappath));
        } 
        catch (IOException e) {
            System.out.println(e);
        }
        
		
	}
	
	
	public static final Color pink = new Color(246, 0, 255);
	
	//true = you are open
	public static boolean isClear(int x, int y){
		
		
		//out of bounds
        if(x<0 || x>= mask.getWidth(null) || y<0 || y>= mask.getHeight(null)){
            return false;
        }
        
        //in bounds compare colors
        int WALL = Bitmask.pink.getRGB();
        int c = mask.getRGB(x, y);
        return c != WALL;
    
	}
	
	//true = all 4 corners clear
	public static boolean isRectClear(Rectangle r){
		int tx = (int)r.getX();
		int ty = (int)r.getY();
		
		
		int wid = (int)r.getWidth();
		int hei = (int)r.getHeight();
		
		return Bitmask.isClear(tx,ty) && 
			   Bitmask.isClear(tx,ty + hei) && 
			   Bitmask.isClear(tx + wid,ty) && 
			   Bitmask.isClear(tx + wid,ty + hei);
		
		
	}
	
	public static BufferedImage getMap(){
		return map;
	}
	
	public static BufferedImage getDark(){
		return lightmask;
	}
	
	
	
	
	
}
