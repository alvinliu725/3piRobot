package console;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;

import javax.imageio.ImageIO;

public class CaptureImage {
    public static void main (String args[]){
    	JFrame mainFrame = new JFrame("3pi XConsole");
    	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	mainFrame.setSize(800,500);

    	//mainFrame.pack();
    	Panel mainPanel = new Panel();
    	mainFrame.setContentPane(mainPanel);
    	mainFrame.setVisible(true);
    	mainFrame.setResizable(false);
    	
    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    	VideoCapture capture = new VideoCapture(0);

    	if(capture.isOpened()){
    		System.out.println("connection ok");
    	}
    	
    	System.out.println("**********************************************");
    	
    	Mat frame = new Mat();
        int i = 0;
    	   while(true){
    	    if (capture.read(frame)){
    	    	    Highgui.imwrite("camera"+i+".jpg", frame);
    	    	    i++;
//    	   	        try {
//						BufferedImage image = ImageIO.read(new File("camera.jpg")) ;
//				    	ImagePanel camPanel = new ImagePanel("camera.jpg");
//						System.out.println("Read image ok");
//						camPanel = new ImagePanel(new ImageIcon("camera.jpg").getImage());
//                       
//				    	//mainFrame.add(camPanel);
//				        //mainFrame.pack();
//                        
//				    	//mainFrame.remove(camPanel);
//
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						System.out.println("Error: Failed to read the image");
//					}                  
					}
    	    	}
    	    }	
    	 //capture.release(); //Close the camera
}


