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
	static boolean drawViewscreen = false;
	
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
			i.rechargePhaserBanks();
		}
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D gd = (Graphics2D) g;
		gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		gd.setColor(Color.WHITE);
		gd.fillRect(0, 0, 700, 600);
		
		if(!drawViewscreen){
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
			
			drawPhaserData(gd, 5, 330, Color.blue);
			drawPhotonData(gd, 120, 330, Color.blue);
			if(i.shieldsUp()){
				drawShieldData(gd, 235, 330, Color.LIGHT_GRAY);
			}
			drawWarpData(gd, 5, 470, Color.LIGHT_GRAY);
			drawImpulseData(gd, 120, 470, Color.LIGHT_GRAY);
			drawSensorData(gd, 235, 470, Color.LIGHT_GRAY);
			drawPanel(gd, 350, 330, Color.DARK_GRAY);
		} else {
			try {
				BufferedImage img;
				img = ImageIO.read(new File("src/coolBG.png"));
				gd.drawImage(img, 0, 0, null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	
	public void drawPanel(Graphics2D gd, int topX, int topY, Color bg){
		
		int totalPeople = (Integer.valueOf(i.getNearBridge()) + Integer.valueOf(i.getNearPhasers()) + Integer.valueOf(i.getNearPhotons()) + Integer.valueOf(i.getNearSensors()) + Integer.valueOf(i.getNearShields()) + Integer.valueOf(i.getNearWarpDrive()));
		
		gd.setColor(bg);
		gd.fillRect(topX, topY, 325, 205);
		
		gd.setColor(bg.brighter());
		gd.fillRect(topX, topY + 43, 325, 20);
		gd.fillRect(topX, topY + 66, 325, 20);
		
		if(totalPeople >= 30){
			gd.setColor(new Color(0, 255, 98));
		} else if(i.getPhotonHealth() >= 15){
			gd.setColor(new Color(211, 72, 20));
		} else {
			gd.setColor(Color.RED);
		}
		
		gd.fillRect(topX, topY + 43, (int) (((double)totalPeople/60)*325), 20);
		
		if(totalPeople >= 30){
			gd.setColor(new Color(0, 255, 98));
		} else if(i.getPhotonHealth() >= 15){
			gd.setColor(new Color(211, 72, 20));
		} else {
			gd.setColor(Color.RED);
		}
		
		gd.fillRect(topX, topY + 66, (int) ((i.getHullHealth()/100.0) * 325), 20);
		
		gd.setColor(getContrastingColor(bg));
		gd.setFont(score);
		gd.drawString("USS Enterprise NCC-1701", topX, topY + 20);
		gd.drawString("Score: " + getSecondsElapsed(), topX, topY + 40);
		
		gd.setColor(Color.black);
		gd.drawString(String.format("Crew: %d/60", totalPeople), topX, topY + 60);
		gd.drawString(String.format("Hull Integrity: %d%%", i.getHullHealth()), topX, topY + 82);
		
		gd.drawString(String.format("VENGEANCE BEARING: %.2f", i.getVengeanceBearing()), topX + 15, topY + 200);
		gd.setColor(bg.brighter());
		gd.fillOval(topX + 15, topY + 100, 75, 75);
		gd.setColor(Color.red);
		gd.fillArc(topX + 15, topY + 100, 75, 75, 0, (int) i.getVengeanceBearing());
		
	}
	
	public void drawPhotonData(Graphics2D gd, int topX, int topY, Color bg){
		
		gd.setColor(bg);
		gd.fillRect(topX, topY, 100, 130);
		gd.setColor(getContrastingColor(bg));
		gd.setFont(score);
		gd.drawString("Photons", topX, topY + gd.getFont().getSize());
		
		gd.setFont(percentage);
		gd.drawString(String.format("%d%%", i.getPhotonHealth()), topX, topY + 50);
		gd.drawString(String.valueOf(i.getNumPhotons()), topX, topY + 100);
		
		if(i.getNumPhotons() >= 32){
			gd.setColor(new Color(0, 255, 98));
		} else if(i.getPhotonHealth() >= 16){
			gd.setColor(new Color(211, 72, 20));
		} else {
			gd.setColor(Color.RED);
		}
		
		gd.fillRect(topX, topY + 50, i.getPhotonHealth(), 20);
		
		if(i.getPhotonHealth() >= 50){
			gd.setColor(new Color(0, 255, 98));
		} else if(i.getPhotonHealth() >= 25){
			gd.setColor(new Color(211, 72, 20));
		} else {
			gd.setColor(Color.RED);
		}
		
		gd.fillRect(topX, topY + 100, (int)(((double)i.getNumPhotons()/64)*100), 20);
	}
	
	public void drawSensorData(Graphics2D gd, int topX, int topY, Color bg){
		
		gd.setColor(bg);
		gd.fillRect(topX, topY, 100, 65);
		
		if(i.getSensorHealth() >= 50){
			gd.setColor(new Color(0, 255, 98));
		} else if(i.getSensorHealth() >= 25){
			gd.setColor(new Color(211, 72, 20));
		} else {
			gd.setColor(Color.RED);
		}
		
		gd.fillRect(topX, topY + 34, i.getShieldGenHealth(), 20);
		
		gd.setColor(getContrastingColor(bg));
		gd.setFont(score);
		gd.drawString("Sensors", topX, topY + gd.getFont().getSize());
		
		gd.setFont(percentage);
		gd.drawString(String.format("%d%%", i.getSensorHealth()), topX, topY + 50);
		
	}

	public void drawImpulseData(Graphics2D gd, int topX, int topY, Color bg){
		
		gd.setColor(bg);
		gd.fillRect(topX, topY, 100, 65);
		
		if(i.getImpulseHealth() >= 50){
			gd.setColor(new Color(0, 255, 98));
		} else if(i.getImpulseHealth() >= 25){
			gd.setColor(new Color(211, 72, 20));
		} else {
			gd.setColor(Color.RED);
		}
		
		gd.fillRect(topX, topY + 34, i.getImpulseHealth(), 20);
		
		gd.setColor(getContrastingColor(bg));
		gd.setFont(score);
		gd.drawString("Impulse", topX, topY + gd.getFont().getSize());
		
		gd.setFont(percentage);
		gd.drawString(String.format("%d%%", i.getImpulseHealth()), topX, topY + 50);
		
	}

	public void drawWarpData(Graphics2D gd, int topX, int topY, Color bg){
		
		gd.setColor(bg);
		gd.fillRect(topX, topY, 100, 65);
		
		if(i.getWarpDriveHealth() >= 50){
			gd.setColor(new Color(0, 255, 98));
		} else if(i.getWarpDriveHealth() >= 25){
			gd.setColor(new Color(211, 72, 20));
		} else {
			gd.setColor(Color.RED);
		}
		
		gd.fillRect(topX, topY + 34, i.getWarpDriveHealth(), 20);
		
		gd.setColor(getContrastingColor(bg));
		gd.setFont(score);
		gd.drawString("Warp Drive", topX, topY + gd.getFont().getSize());
		
		gd.setFont(percentage);
		gd.drawString(String.format("%d%%", i.getWarpDriveHealth()), topX, topY + 50);
		
	}

	public void drawShieldData(Graphics2D gd, int topX, int topY, Color bg){
		
		gd.setColor(bg);
		gd.fillRect(topX, topY, 100, 130);
		gd.setColor(getContrastingColor(bg));
		gd.setFont(score);
		gd.drawString("Shields", topX, topY + gd.getFont().getSize());
		
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
		
		gd.fillRect(topX, topY + 50, i.getShieldGenHealth(), 20);
		
		if(i.getShieldLevel() >= 50){
			gd.setColor(new Color(0, 255, 98));
		} else if(i.getShieldLevel() >= 25){
			gd.setColor(new Color(211, 72, 20));
		} else {
			gd.setColor(Color.RED);
		}
		
		gd.fillRect(topX, topY + 100, i.getShieldLevel(), 20);
	}
	
	public void drawPhaserData(Graphics2D gd, int topX, int topY, Color bg){	
		
		gd.setColor(bg);
		gd.fillRect(topX, topY, 100, 130);
		gd.setColor(getContrastingColor(bg));
		gd.setFont(score);
		gd.drawString("Phaser Banks", topX, topY + gd.getFont().getSize());
		
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
		
		gd.fillRect(topX, topY + 50, i.getPhaserHealth(), 20);
		
		if(i.getPhaserLevel() >= 50){
			gd.setColor(new Color(0, 255, 98));
		} else if(i.getPhaserLevel() >= 25){
			gd.setColor(new Color(211, 72, 20));
		} else {
			gd.setColor(Color.RED);
		}
		
		gd.fillRect(topX, topY + 100, i.getPhaserLevel(), 20);
	}
	
	public void drawViewScreen(){
		
	}
	
}
