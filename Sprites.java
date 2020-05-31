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
 private static BufferedImage[] wolfR=new BufferedImage[4];
 private static BufferedImage[] wolfL=new BufferedImage[4];
 private static BufferedImage[] shepR=new BufferedImage[4];
 private static BufferedImage[] shepL=new BufferedImage[4];
 
 //call once before main in MainG
 public static void load(String sheepPath, String wolfPath,String shepPath){
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
          for(int k=0;k<4;k++){
            wolfR[k]=ImageIO.read(new File("wolfR"+(k+1)+".png"));
          }
          for(int k=0;k<4;k++){
            wolfL[k]=ImageIO.read(new File("wolfL"+(k+1)+".png"));
          }
        } 
        catch (IOException e) {
            System.out.println(e);
        }
        try {
          for(int k=0;k<4;k++){
            shepR[k]=ImageIO.read(new File("shepR"+(k+1)+".png"));
          }
          for(int k=0;k<4;k++){
            shepL[k]=ImageIO.read(new File("shepL"+(k+1)+".png"));
          }
        } 
        catch (IOException e) {
            System.out.println(e);
        }
  
 }
 
 public static BufferedImage[] getSheepR(){
   return sheepR;
 }
 public static BufferedImage[] getSheepL(){
   return sheepL;
 }
 public static BufferedImage[] getWolfR(){
   return wolfR;
 }
 public static BufferedImage[] getWolfL(){
   return wolfL;
 }
 public static BufferedImage[] getShepR(){
   return shepR;
 }
 public static BufferedImage[] getShepL(){
   return shepL;
 }
}
