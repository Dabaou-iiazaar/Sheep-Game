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
public class Sprites {
 
 private static BufferedImage[] sheepR=new BufferedImage[4];
 private static BufferedImage[] sheepL=new BufferedImage[4];
 private static BufferedImage[] wolfR=new BufferedImage[3];
 private static BufferedImage[] wolfL=new BufferedImage[3];
 private static BufferedImage[] shepR=new BufferedImage[3];
 private static BufferedImage[] shepL=new BufferedImage[3];
 private static BufferedImage[] wolfDeath =new BufferedImage[16];
 
 //game over
 private static BufferedImage gameOverText;
 private static BufferedImage win;
 
 //call once before main in MainG
 public static void load(){
        try {
          for(int k=0;k<4;k++){
            sheepR[k]=ImageIO.read(new File("sheepR"+(k+1)+".png"));
          }
          for(int k=0;k<4;k++){
            sheepL[k]=ImageIO.read(new File("sheepL"+(k+1)+".png"));
          }
        } 
        catch (IOException e) {
            System.out.println(e);
        }
        try {
          for(int k=0;k<3;k++){
            wolfR[k]=ImageIO.read(new File("wolfR"+(k+1)+".png"));
          }
          for(int k=0;k<3;k++){
            wolfL[k]=ImageIO.read(new File("wolfL"+(k+1)+".png"));
          }
        } 
        catch (IOException e) {
            System.out.println(e);
        }
        try {
          for(int k=0;k<3;k++){
            shepR[k]=ImageIO.read(new File("shep/shepR"+(k+1)+".png"));
          }
          for(int k=0;k<3;k++){
            shepL[k]=ImageIO.read(new File("shep/shepL"+(k+1)+".png"));
          }
        } 
        catch (IOException e) {
            System.out.println(e);
        }
        
        //load wolf death images
        try{
        	for(int k=0;k<16;k++){
            	wolfDeath[k]=ImageIO.read(new File("WolfHit/WolfHit"+(k)+".png"));
          	}	
        }
        catch (IOException e) {
            System.out.println(e);
        }
        
        
        try{
        	gameOverText = ImageIO.read(new File("GameOver.png"));
        }
        catch (IOException e) {
            System.out.println(e);
        }
        
        try{
        	win = ImageIO.read(new File("YouWin.jpg"));
        }
        catch (IOException e) {
            System.out.println(e);
        }
  
 }
 
 public static BufferedImage getSheepR(int f){
   return sheepR[f];
 }
 public static BufferedImage getSheepL(int f){
   return sheepL[f];
 }
 public static BufferedImage getWolfR(int f){
   return wolfR[f];
 }
 public static BufferedImage getWolfL(int f){
   return wolfL[f];
 }
 public static BufferedImage getShepR(int f){
   return shepR[f];
 }
 public static BufferedImage getShepL(int f){
   return shepL[f];
 }
 
 public static BufferedImage getWolfDeath(int f){
	return wolfDeath[f];
 }
 
 public static BufferedImage getGameOver(){
 	return gameOverText;
 }
 
 public static BufferedImage getWin(){
 	return win;
 }
 
}
