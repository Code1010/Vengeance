import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Game extends JPanel {

	public static final long start = System.currentTimeMillis();
	public static long end;
	final Font percentage = new Font("Monospaced", Font.PLAIN, 20);
	final Font score = new Font("Arial Narrow", Font.PLAIN, 20);
	final Font people = new Font("Arial", Font.ITALIC, 26);
	
	static Input i = new Input();
	
	public static String getSecondsElapsed(){
		long now = System.currentTimeMillis();
        return String.format("%.1f", ((now - start) / 1000.0));
	}
	
	public void stopTimer(){
		end = System.currentTimeMillis();
	}
	
	public static void main(String [] args) throws InterruptedException{
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
		
		
		Thread console = new Thread(i);
		console.start();		
		
		while(true){
			//This is for graphics
			frame.repaint();
			Thread.sleep(100);
		}
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D gd = (Graphics2D) g;
		gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		gd.setColor(Color.WHITE);
		gd.fillRect(0, 0, 700, 600);
			
		try {
			BufferedImage img;
			img = ImageIO.read(new File("src/finishedBG.png"));
			gd.drawImage(img, 0, 0, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		gd.setColor(Color.black);
		gd.setFont(score);
		gd.drawString("Score: " + getSecondsElapsed(), 0, 20);
		gd.setFont(people);
		gd.drawString(i.getNearWarpDrive(), 195, 124);
		gd.drawString(i.getNearShields(), 276, 181);
		gd.drawString(i.getNearPhotons(), 438, 94);
		gd.drawString(i.getNearPhasers(), 438, 271);
		gd.drawString(i.getNearBridge(), 438, 174);
		gd.drawString(i.getNearSensors(), 558, 179);
		
		
		if(i.foreShieldsOn()){
			gd.setColor(Color.BLUE);
			gd.setFont(percentage);
			gd.drawArc(530, 20, 100, 300, 270, 180);
			gd.setColor(new Color(174, 191, 233));
			gd.drawString(String.format("%.0f%%", i.getForeShields()), 631, 179);
		}
		
		if(i.aftShieldsOn()){
			gd.setColor(Color.blue);
			gd.setFont(percentage);
			gd.drawArc(60, 20, 100, 300, 90, 180);
			gd.setColor(new Color(174, 191, 233));
			gd.drawString(String.format("%.0f%%", i.getAftShields()), 65, 179);
		}
		
		if(i.rightShieldsOn()){
			gd.setColor(Color.BLUE);
			gd.setFont(percentage);
			gd.drawArc(105, 308, 482, 20, 180, 180);
			gd.setColor(new Color(174, 191, 233));
			gd.drawString(String.format("%.0f%%", i.getRightShields()), 330, 300);
		}
		
		if(i.leftShieldsOn()){
			gd.setColor(Color.BLUE);
			gd.setFont(percentage);
			gd.drawArc(105, 12, 482, 20, 0, 180);
			gd.setColor(new Color(174, 191, 233));
			gd.drawString(String.format("%.0f%%", i.getLeftShields()), 330, 60);
		}
		
		drawPhaserData(gd, 5, 330, Color.black);
		
		if(i.shieldsUp()){
			drawShieldData(gd, 230, 330, Color.LIGHT_GRAY);
		}
		
	}
	
	public Color getContrastingColor(Color c){
		double a = 1 - ( 0.299 * c.getRed() + 0.587 * c.getGreen() + 0.114 * c.getBlue())/255;
		int d;
		if(a < .5){
			d = 0;
		} else {
			d = 255;
		}
		
		return new Color(d, d, d);
	}
	
	public void drawShieldData(Graphics2D gd, int topX, int topY, Color bg){
		
		gd.setColor(bg);
		gd.fillRect(topX, topY, 200, 130);
		gd.setColor(getContrastingColor(bg));
		gd.setFont(score);
		gd.drawString("Shields", topX + 10, topY + gd.getFont().getSize());
		
		gd.setFont(percentage);
		gd.drawString(String.format("%d%%", i.getShieldGenHealth()), topX, topY + 50);
		gd.drawString("LVL", topX, topY + 100);
		
		if(i.getShieldGenHealth() >= 50){
			gd.setColor(new Color(0, 255, 98));
		} else if(i.getShieldGenHealth() >= 25){
			gd.setColor(new Color(211, 72, 20));
		} else {
			gd.setColor(Color.RED);
		}
		
		gd.fillRect(topX, topY + 50, i.getShieldGenHealth() * 2, 20);
		
		if(i.getShieldLevel() >= 50){
			gd.setColor(new Color(0, 255, 98));
		} else if(i.getShieldLevel() >= 25){
			gd.setColor(new Color(211, 72, 20));
		} else {
			gd.setColor(Color.RED);
		}
		
		gd.fillRect(topX, topY + 100, i.getShieldLevel() * 2, 20);
	}
	
	public void drawPhaserData(Graphics2D gd, int topX, int topY, Color bg){	
		
		gd.setColor(bg);
		gd.fillRect(topX, topY, 200, 130);
		gd.setColor(getContrastingColor(bg));
		gd.setFont(score);
		gd.drawString("Phaser Banks", topX + 10, topY + gd.getFont().getSize());
		
		gd.setFont(percentage);
		gd.drawString(String.format("%d%%", i.getPhaserHealth()), topX, topY + 50);
		gd.drawString("LVL", topX, topY + 100);
		
		if(i.getPhaserHealth() >= 50){
			gd.setColor(new Color(0, 255, 98));
		} else if(i.getPhaserHealth() >= 25){
			gd.setColor(new Color(211, 72, 20));
		} else {
			gd.setColor(Color.RED);
		}
		
		gd.fillRect(topX, topY + 50, i.getPhaserHealth() * 2, 20);
		
		if(i.getPhaserLevel() >= 50){
			gd.setColor(new Color(0, 255, 98));
		} else if(i.getPhaserLevel() >= 25){
			gd.setColor(new Color(211, 72, 20));
		} else {
			gd.setColor(Color.RED);
		}
		
		gd.fillRect(topX, topY + 100, i.getPhaserLevel() * 2, 20);
		
		
	}
	
}
