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
	
	private int x, y, vx, vy;
	
    public Wolf(int X, int Y) {
    	x=X;
    	y=Y;
    }
    
    public Rectangle WolfBox(){
    	return new Rectangle(x, y, 30, 30); //can be changed accordingly 
    }
    
}