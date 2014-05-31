package console;


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

import console.ButtonPanel;

 public class mainConsole extends JPanel{  

   private BufferedImage image = null;  
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
      if(temp!=null)
         g.drawImage(temp,10,10,temp.getWidth()+5,temp.getHeight()+5, this);  
   }  
   
   
   public static void main(String arg[]){  
    // Load the native library.  
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);    
    JFrame frame = new JFrame("3piRobot");  
    frame.setLayout(new FlowLayout());
    frame.setVisible(true); 
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    frame.setSize(1200,800);  
    mainConsole panel = new mainConsole();  
    frame.setContentPane(panel);  
    //ButtonPanel buttonP = new ButtonPanel();
   // panel.add(buttonP);
    Mat webcam_image=new Mat();  
    BufferedImage temp=null;  
    VideoCapture capture = new VideoCapture(0);  

    if(capture.isOpened())  
     {
      while( true )  
      {    
        capture.read(webcam_image);  
        if( !webcam_image.empty() )  
         {  
           Highgui.imwrite("cameraTemp.jpg", webcam_image);
           try {
			temp=ImageIO.read(new File("cameraTemp.jpg"));
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
       
   }  

 }  
 

