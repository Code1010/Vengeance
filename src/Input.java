import java.util.Scanner;


public class Input implements Runnable{

	private USSVengeance veng;
	private PlayerShip enterprise;
	private int loops;
	private boolean asked = false;
	
	public Input(){
		veng = new USSVengeance();
		enterprise = new PlayerShip(veng);
	}
	
	public USSVengeance getVengeance(){
		return veng;
	}
	
	public PlayerShip getEnterprise(){
		return enterprise;
	}
	
	@Override
	public void run() {
		
		Scanner in = new Scanner(System.in);
		
		while(true){
			System.out.print("> ");
			String command = in.nextLine();
			interpret(command);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void interpret(String command){
		
		command = command.toLowerCase();
		if(command.contains("eat")){
			System.out.println("Captain, I reccommend fending off hunger for now, it is a bad time to eat!");
		} else if(command.contains("drink")){
			System.out.println("Ahhhhh. That was refreshing.");
		} else if(command.contains("joke")){
			System.out.println("Haha!");
		} else if(command.contains("nap")){
			System.out.println("Try to stay awake, captain!");
		} else if(command.contains("suicide")){
			System.out.println("You commit suicide, and your crew is devastated. They perish in battle against the Vengenace without your leadership.");
			System.exit(5);
		} else if(command.contains("kill")){
			System.out.println("I don't reccommend killing, unless it's the Vengeance!");
		} else if(command.contains("surrender")){
			System.out.println("You surrender to the USS Vengeance. You fought nobly."); //add seconds later
			System.exit(5);
		} else if(command.contains("maroon")) {
			System.out.println("I don't reccommend marooning the crew.");
		} else if(command.contains("ram")){
			System.out.println("Captain, the Vengeance is much larger and more massive than the Enterprise. Ramming it will have little effect.");
		} else if(command.contains("hide")){
			System.out.println("Behind what? There's nothing around here");
		} else if(command.contains("help")){
			System.out.println("Doing all we can, Captain");
		} else if(command.contains("scream")){
			System.out.println("Your blood curdling scream is enough to distract your crew for a moment, then they slowly turn their heads away and return to work.");
		} else if(command.contains("starfleet")){
			if(veng.getSensorHealth() <= 0){
				if(asked){
					System.out.println("Already contacted Starfleet, captain. They will arrive within 10 minutes of the distress signal");
				} else {
					System.out.println("Contacting Starfleet now...They are sending backup. We need to hold out for 10 more minutes");
					loops = 0;
					asked = true;
				}
			} else {
				System.out.println("Sir, I cannot hail Starfleet because the Vengeance is jamming all of the subspace channels!");
			}			
		} else if(command.contains("what is")){
			System.out.println("I hope you know, Captain! We're about to be blown to bits by the Vengeance!");
		} else if(command.contains("carol marcus")){
			System.out.println("She is aboard, Captain, but you'll have to be more clever than that to survive.\n"
					+ "The attack subsided for a moment until Admiral Marcus beamed her aboard the Vengeance.");
		} else if(command.contains("hail") || command.contains("vengeance")){
			System.out.println("Hailing....Captain, the Vengeance is rejecting our communications");
		} else if(command.contains("fix") || command.contains("repair")){
			
			//do something to fix the broken thing
			
		} else if(command.contains("relocate") && command.contains("personnel")){ //relocate 8 bridge personnel to warp engines
			//must be in this format:
			//RELOCATE [num] [location] PERSONNEL TO [destination]
			int numLoc = command.indexOf("relocate ") + 9;
			
			
		} else if(command.contains("damage") && command.contains("report")){
			
			//print out a damage report
			
		} else if(command.contains("phaser") && command.contains("fire")){
			
			if(command.contains(" at ")){ //fire phasers at the hull power 157
				
				command = command.replaceAll(" the ", "");
				int powerIndex = command.indexOf("power") + 6;
				int systemIndex = command.indexOf("at") + 3;
				
				String p = command.substring(powerIndex);
				String s = command.substring(systemIndex, (command.indexOf("power")));
				System.out.println(enterprise.firePhasers(Integer.valueOf(p), s));
				
			} else { //fire phasers power 157
				String p = command.substring(command.indexOf("power") + 6);
				System.out.println(enterprise.firePhasers(Integer.valueOf(p)));
			}
			
		} else if(command.contains("photon") && command.contains("fire")){
			
			//fire the photons
			
		} else if(command.contains("seal")){
			
			//find which sections to seal
			
		} else if(command.contains("warp")){
			if(enterprise.getWarpHealth() >= 0){
				System.out.println("Going to warp, Captain.");
				System.out.println("Captain, the vengeance has once again crippled the warp drives!");
			} else {
				System.out.println("Captain, the Warp Drives are not functional!");
			}
		} else if(command.contains("mantis shrimp") && command.contains("fire")){
			System.out.println("Send'em back to where they came from!");
			System.out.println("The mantis shrimps fired successfully, but the cold vacuum of space killed all of them instantly. Good job, you singlehandedly extincted a whole species.");
		} else if(command.contains("viewscreen")){
			System.out.println("Aye, Captain"); //change the viewscreen mode to show the vengeance
		}
		
	}
	
	
	
}
