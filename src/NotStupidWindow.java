import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class NotStupidWindow {

	
	public static void main(String[] args) throws InterruptedException{
		
		JFrame frame = new JFrame("Vengeance HUD");
		Game window = new Game();
		frame.add(window);
		frame.setAlwaysOnTop(true);
		//set the window icon
		BufferedImage icon;
		try {
			icon = ImageIO.read(new File("src/vengeance.jpg"));
			frame.setIconImage(icon);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		frame.setSize(685, 565); //after a lot of back and forth, i've decided on a pleasing window size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null); //make it open in the center, like everything ought to
		
		
		Thread console = new Thread(new Input());
		console.start();		
		
		while(true){
			//This is for graphics
			frame.repaint();
			Thread.sleep(100);
		}
		
	}
	
	public void paint(Graphics g){
		
	}
	
}
