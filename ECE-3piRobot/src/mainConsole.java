
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
import javax.media.*;
import javax.media.format.RGBFormat;



public class mainConsole {
    public static void main (String args[]){
    	JFrame mainFrame = new JFrame("3pi XConsole");
    	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	mainFrame.setSize(800,500);
    	ImagePanel camPanel = new ImagePanel("camera.jpg");

    	//mainFrame.pack();
    	JPanel mainPanel = new JPanel();
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

    	   while(true){
    	    if (capture.read(frame)){
    	    	    Highgui.imwrite("camera"+".jpg", frame);
    	   	        try {
						BufferedImage image = ImageIO.read(new File("camera.jpg")) ;
						System.out.println("Read image ok");
						camPanel = new ImagePanel(new ImageIcon("camera.jpg").getImage());

				    	mainFrame.add(camPanel);
				        mainFrame.pack();
				        try {
							mainFrame.wait(30);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				    	mainFrame.remove(camPanel);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("Error: Failed to read the image");
					}                  
					}
    	    	}
    	    }	
    	 //capture.release(); //Close the camera
}

class ImagePanel extends JPanel {
	private Image img; 
	
	public ImagePanel(String img){
		this(new ImageIcon(img).getImage());
	}
	
	public ImagePanel(Image img){
		this.img= img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
	}
	
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
}

