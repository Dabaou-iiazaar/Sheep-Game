
import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.image.*; 
import java.io.*; 
import javax.imageio.*; 
import java.util.*;

public class menu extends JPanel {
	public boolean ready=false;
    
    private int delay = 0, frame = 0;
    private Image[] pics = new Image[8];
    
    private final Rectangle button = new Rectangle(270, 229, 258, 108);
    
    
	public menu(){
		
		//init mouse listeners
		
		
		
		
        setSize(800,600);
        for(int i = 1; i<=8; i++){
            pics[i-1] = new ImageIcon("menuPics/Menu" + i + ".png").getImage();
        }
	}
	
    public void addNotify() {
        super.addNotify();
        requestFocus();
        ready = true;
    }
    
    public boolean clickedPlay(boolean[] mouseClicked, int mx, int my){
        if (mouseClicked[1] && button.contains(mx,my)){
        	return true;
        }
        
        return false;
    }

    public void paint(Graphics g){
        g.drawImage(pics[frame], 0, 0, null);
        if(delay % 20 == 0){
            frame = (frame+1)%8;
        }
        delay++;
    } 
    	
    	
    
}
