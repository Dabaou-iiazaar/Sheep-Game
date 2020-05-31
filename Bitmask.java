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
	private static BufferedImage map;
	
	//call once before main in MainG
	public static void load(String maskpath, String mappath){
		try {
            mask = ImageIO.read(new File(maskpath));
        } 
        catch (IOException e) {
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
	
	
	
}
