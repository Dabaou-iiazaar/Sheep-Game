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
	

	
	private int x, y, vx, vy;
	private final int halfsize = 10;
	
	
	
    public Sheep(int X, int Y) {
    	x=X;
    	y=Y;
    	
    }
    
    public Rectangle SheepBox(){
    	return new Rectangle(x - halfsize, y - halfsize, 2*halfsize, 2*halfsize); //can be changed accordingly 
    }
    
}
