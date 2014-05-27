package test;


import java.awt.*;  
import java.awt.image.BufferedImage;  
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;  

import org.opencv.core.Core;
import org.opencv.core.Mat;  
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;  


 public class mainConsole extends JPanel{  

   private BufferedImage image;  
   // Create a constructor method  
   public mainConsole(){  
     super();  
   }  
   private BufferedImage getimage(){  
     return image;  
   }  
   private void setimage(BufferedImage newimage){  
     image=newimage;  
     return;  
   }  
 
   public void paintComponent(Graphics g){  
      BufferedImage temp=getimage();  
      g.drawImage(temp,10,10,temp.getWidth(),temp.getHeight(), this);  
   }  
  
   public static void main(String arg[]){  
    // Load the native library.  
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);    
    JFrame frame = new JFrame("3piRobot");  
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    frame.setSize(640,480);  
    mainConsole panel = new mainConsole();  
    frame.setContentPane(panel);       
    frame.setVisible(true);       
    Mat webcam_image=new Mat();  
    BufferedImage temp=null;  
    VideoCapture capture =new VideoCapture(0);  
    if( capture.isOpened())  
     {  
      while( true )  
      {  
        capture.read(webcam_image);  
        if( !webcam_image.empty() )  
         {  
           System.out.println("Camera Connection OK - Stream Testing.............");
           frame.setSize(webcam_image.width()+40,webcam_image.height()+40);  
           Highgui.imwrite("camera.jpg", webcam_image);
           try {
			temp=ImageIO.read(new File("camera.jpg"));
		   } 
           catch (IOException e) {
			e.printStackTrace();
			System.out.println("Read the camera failed");
		   }
           
           panel.setimage(temp);  
           panel.repaint();  
         }  
         else  
         {  
           System.out.println("Camera Issues! Please check the connection");  
           break;  
         }  
        }  
       }  
       return;  
   }  
 }  
 

