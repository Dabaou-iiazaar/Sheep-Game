import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.image.*; 
import java.io.*; 
import javax.imageio.*; 
import java.util.*;

//the class that packs together all of the objects and stuff
public class GamePanel extends JPanel implements KeyListener, MouseListener, MouseMotionListener{
	
	private MainG mainFrame;
	
	
	
	
	
	public GamePanel(MainG mainFrame){
		this.mainFrame = mainFrame;
		
		keys = new boolean[KeyEvent.KEY_LAST];
		Arrays.fill(keys,false);
		mouseHeld = new boolean[4];
		Arrays.fill(mouseHeld,false);
		
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		
		
					
	}
	
	public void addNotify(){
		super.addNotify();
		requestFocus();
		mainFrame.start();
	}
	
	
	

	
	public void move(){
		
		
		
			
	}
	
	
	@Override
	public void paintComponent(Graphics g){
		
		
		
		
		
		
		
		
	}
	
	
	/*			keyboard   */
	
	private boolean[] keys;
	
	@Override
	public void keyTyped(KeyEvent e){
		
	}
	
	public void keyPressed(KeyEvent e){
		keys[e.getKeyCode()] = true;
	}
	
	public void keyReleased(KeyEvent e){	
		keys[e.getKeyCode()] = false;	
	}
	
	
	/*		mouse		*/
	private int mx, my;
	
	private boolean mouseHeld[];
	
	@Override
	
	public void mouseClicked(MouseEvent e){
		
	}
	
	public void mousePressed(MouseEvent e){
		mouseHeld[e.getButton()] = true;
	
	}
	
	public void mouseReleased(MouseEvent e){
		mouseHeld[e.getButton()] = false;
	}
	
	public void mouseEntered(MouseEvent e){
		
	}
	
	public void mouseExited(MouseEvent e){
		
	}
	
	public void mouseMoved(MouseEvent e){
		mx = e.getX();
		my = e.getY();
	}
	
	public void mouseDragged(MouseEvent e){
		mx = e.getX();
		my = e.getY();
	}
	
	
	
	
	
	
	
	
}
