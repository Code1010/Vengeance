import java.util.Scanner;


public class Input implements Runnable{

	@Override
	public void run() {
		
		Scanner in = new Scanner(System.in);
		
		while(true){
			System.out.print("Enter Your Name, Captain: ");
			String name = in.nextLine();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Hello, captain " + name);
			
		}
		
	}
	
	public void interpret(String command){
		
	}
	
	
	
}
