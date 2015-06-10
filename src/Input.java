import java.util.Scanner;

public class Input implements Runnable {

	private USSVengeance veng = new USSVengeance();
	private PlayerShip enterprise = new PlayerShip(veng);
	private int loops;
	private boolean asked = false;

	@Override
	public void run() {

		Scanner in = new Scanner(System.in);
		Thread evil = new Thread(enterprise);
		evil.start();

		while (true) {
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

	public void interpret(String command) {

		try {
			command = command.toLowerCase();
			if (command.contains("eat ")) {
				System.out
						.println("Captain, I reccommend fending off hunger for now, it is a bad time to eat!");
			} else if (command.contains("drink ")) {
				System.out.println("Ahhhhh. That was refreshing.");
			} else if (command.contains("joke")) {
				System.out.println("Haha!");
			} else if (command.contains("nap") || command.contains("sleep")) {
				System.out.println("Try to stay awake, captain!");
			} else if (command.contains("suicide")) {
				System.out.println("Bad idea. You have so much to live for...like not getting killed by the Vengeance!");
			} else if (command.contains("kill")) {
				System.out
						.println("I don't reccommend killing, unless it's the Vengeance!");
			} else if (command.contains("surrender")) {
				System.out
						.println("You surrender to the USS Vengeance. You fought nobly."); // add
																							// seconds
																							// later
				System.exit(5);
			} else if (command.contains("maroon")) {
				System.out.println("I don't reccommend marooning the crew.");
			} else if (command.contains("course")) {
				System.out.println("I cannot set a course. The helm is not responding, Captain!");
			} else if (command.contains("ram")) {
				System.out
						.println("Captain, the Vengeance is much larger and more massive than the Enterprise. Ramming it will have little effect.");
			} else if (command.contains("hide")) {
				System.out.println("Behind what? There's nothing around here");
			} else if (command.contains("help")) {
				System.out.println("Doing all we can, Captain");
			} else if (command.contains("scream")) {
				System.out
						.println("Your blood curdling scream is enough to distract your crew for a moment, then they slowly turn their heads away and return to work.");
			} else if (command.contains("starfleet")) {
				if (veng.getSensorHealth() <= 0) {
					if (asked) {
						System.out
								.println("Already contacted Starfleet, captain. They will arrive within 10 minutes of the distress signal");
					} else {
						System.out
								.println("Contacting Starfleet now...They are sending backup. We need to hold out for 10 more minutes");
						loops = 0;
						asked = true;
					}
				} else {
					System.out
							.println("Sir, I cannot hail Starfleet because the Vengeance is jamming all of the subspace channels!");
				}
			} else if (command.contains("what is")) {
				System.out
						.println("I hope you know, Captain! We're about to be blown to bits by the Vengeance!");
			} else if (command.contains("carol marcus")) {
				System.out
						.println("She is aboard, Captain, but you'll have to be more clever than that to survive.\n"
								+ "The attack subsided for a moment until Admiral Marcus beamed her aboard the Vengeance.");
			} else if (command.contains("hail") || command.contains("vengeance")) {
				System.out
						.println("Hailing....Captain, the Vengeance is rejecting our communications");
			} else if (command.contains("shield") && (command.contains("%"))) {
	
				if(enterprise.shieldsUp()){ //left shields 100%
					boolean hasShields = true;
					int shieldsIndex = command.indexOf("shields");
					if(shieldsIndex == -1){
						shieldsIndex = command.indexOf("shield");
						hasShields = false;
					}
					int percentSign = command.indexOf("%");
					
					String side = command.substring(0, shieldsIndex - 1);
					String percent;
					if(hasShields){
						percent = command.substring(shieldsIndex + 7, percentSign).trim();
					} else {
						percent = command.substring(shieldsIndex + 6, percentSign).trim();
					}
					
					int intPercent = Integer.valueOf(percent);
					if(intPercent > 100){
						intPercent = 100;
					} else if(intPercent < 0){
						intPercent = 0;
					}
					
					
					
					if(side.equals("left")){
						int totalPercents = (int) (enterprise.getRightShields() + enterprise.getAftShields() + enterprise.getForeShields() + intPercent);
						int miss = totalPercents - 100;
						
						if(miss > 0){
							enterprise.setLeftShields(intPercent - miss);
						} else {
							enterprise.setLeftShields(intPercent);
						}
						
					} else if(side.equals("right")){
						
						int totalPercents = (int) (enterprise.getLeftShields() + enterprise.getAftShields() + enterprise.getForeShields() + intPercent);
						int miss = totalPercents - 100;
						
						if(miss > 0){
							enterprise.setRightShields(intPercent - miss);
						} else {
							enterprise.setRightShields(intPercent);
						}
						
					} else if(side.equals("aft")) {
						
						int totalPercents = (int) (enterprise.getLeftShields() + enterprise.getRightShields() + enterprise.getForeShields() + intPercent);
						int miss = totalPercents - 100;
						
						if(miss > 0){
							enterprise.setAftShields(intPercent - miss);
						} else {
							enterprise.setAftShields(intPercent);
						}
						
					} else if(side.equals("fore")){

						int totalPercents = (int) (enterprise.getLeftShields() + enterprise.getRightShields() + enterprise.getAftShields() + intPercent);
						int miss = totalPercents - 100;
						
						if(miss > 0){
							enterprise.setForeShields(intPercent - miss);
						} else {
							enterprise.setForeShields(intPercent);
						}
						
					} else {
						System.out.println("Cannot comply: invalid side");
					}
					
				} else {
					System.out.println("Cannot comply: sheields are down");
				}
				
			} else if (command.contains("repair")) {
	
				command = command.replaceAll(" the ", " ");
				int repairIndex = command.indexOf("repair") + 7;
				String system = command.substring(repairIndex);
				if(system.contains("warp")){
					enterprise.setWarpHealth(getWarpDriveHealth() + Integer.valueOf(getNearWarpDrive()));
					if(enterprise.getWarpHealth() > 100){
						enterprise.setWarpHealth(100.0);
					}
				} else if(system.contains("impulse")){
					enterprise.setImpulseHealth(getImpulseHealth() + Integer.valueOf(getNearWarpDrive()));
					if(enterprise.getImpulseHealth() > 100){
						enterprise.setImpulseHealth(100.0);
					}
				} else if(system.contains("shield")){
					enterprise.setShieldGenHealth(getShieldGenHealth() + Integer.valueOf(getNearShields()));
					if(enterprise.getShieldGenHealth() > 100){
						enterprise.setShieldGenHealth(100.0);
					}
				} else if(system.contains("photon")){
					enterprise.setPhotonHealth(getPhotonHealth() + Integer.valueOf(getNearPhotons()));
					if(enterprise.getPhotonHealth() > 100){
						enterprise.setPhotonHealth(100.0);
					}
				} else if(system.contains("phaser")){
					enterprise.setPhaserHealth(getPhaserHealth() + Integer.valueOf(getNearPhasers()));
					if(enterprise.getPhaserHealth() > 100){
						enterprise.setPhaserHealth(100.0);
					}
				} else if(system.contains("hull")){
					System.out.println("I'm sorry, Captain, we don't have the resources to fix the hull out here in deep space.");
				} else if(system.contains("sensor")){
					enterprise.setSensorHealth(getSensorHealth() + Integer.valueOf(getNearSensors()));
					if(enterprise.getSensorHealth() > 100){
						enterprise.setSensorHealth(100.0);
					}
				} else {
					System.out.println("Uhhh...We're not sure what you want us to fix, Captain.");
				}
	
			} else if (command.contains("fix")) {
	
				command = command.replaceAll(" the ", " ");
				int repairIndex = command.indexOf("fix") + 4;
				String system = command.substring(repairIndex);
				if(system.contains("warp")){
					enterprise.setWarpHealth(getWarpDriveHealth() + Integer.valueOf(getNearWarpDrive()));
					if(enterprise.getWarpHealth() > 100){
						enterprise.setWarpHealth(100.0);
					}
				} else if(system.contains("impulse")){
					enterprise.setImpulseHealth(getImpulseHealth() + Integer.valueOf(getNearWarpDrive()));
					if(enterprise.getImpulseHealth() > 100){
						enterprise.setImpulseHealth(100.0);
					}
				} else if(system.contains("shield")){
					enterprise.setShieldGenHealth(getShieldGenHealth() + Integer.valueOf(getNearShields()));
					if(enterprise.getShieldGenHealth() > 100){
						enterprise.setShieldGenHealth(100.0);
					}
				} else if(system.contains("photon")){
					enterprise.setPhotonHealth(getPhotonHealth() + Integer.valueOf(getNearPhotons()));
					if(enterprise.getPhotonHealth() > 100){
						enterprise.setPhotonHealth(100.0);
					}
				} else if(system.contains("phaser")){
					enterprise.setPhaserHealth(getPhaserHealth() + Integer.valueOf(getNearPhasers()));
					if(enterprise.getPhaserHealth() > 100){
						enterprise.setPhaserHealth(100.0);
					}
				} else if(system.contains("hull")){
					System.out.println("I'm sorry, Captain, we don't have the resources to fix the hull out here in deep space.");
				} else if(system.contains("sensor")){
					enterprise.setSensorHealth(getSensorHealth() + Integer.valueOf(getNearSensors()));
					if(enterprise.getSensorHealth() > 100){
						enterprise.setSensorHealth(100.0);
					}
				} else {
					System.out.println("Uhhh...We're not sure what you want us to fix, Captain.");
				}
	
			} else if (command.contains("relocate")
					&& command.contains("personnel")) { // relocate 8 bridge
														// personnel to warp engines
				// must be in this format:
				// RELOCATE [num] [location] PERSONNEL TO [destination]
				try {
					command = command.replaceAll(" the ", "");
					int numLoc = command.indexOf("relocate ") + 9;
					String temp = command.substring(numLoc);
					int endNum = command.indexOf(" ", numLoc);
					int num = Integer.valueOf(command.substring(numLoc, endNum)
							.trim());
					String loc = command.substring(endNum,
							command.indexOf(" personnel")).trim();
					String dest = command.substring(command.indexOf("to ") + 3)
							.trim();
					for (int i = 1; i <= num; i++) {
						enterprise.movePerson(loc, dest);
					}
				} catch (IndexOutOfBoundsException e) {
					System.err.println("You cannot perform that operation.");
				}
	
			} else if (command.contains("damage") && command.contains("report")) {

				System.out.println("****************************************");
				System.out.println("*USS ENTERPRISE NCC 1701 DAMAGE REPORT:*");
				System.out.println("________________________________________");
				System.out.printf("|SHIELDS: %d%% Integrity\n", getShieldGenHealth());
				System.out.printf("|WARP DRIVE: %d%% Integrity\n", getWarpDriveHealth());
				System.out.printf("|IMPULSE ENGINES: %d%% Integrity\n", getImpulseHealth());
				System.out.printf("|PHOTONS: %d%% Integrity\n", getPhotonHealth());
				System.out.printf("|PHASER BANKS: %d%% Integrity\n", getPhaserHealth());
				System.out.printf("|SENSOR ARRAYS: %d%% Integrity\n", getSensorHealth());
				System.out.printf("|HULL: %d%% Integrity\n", getHullHealth());
				int totalPeople =  (Integer.valueOf(getNearBridge()) + Integer.valueOf(getNearPhasers()) + Integer.valueOf(getNearPhotons()) + Integer.valueOf(getNearSensors()) + Integer.valueOf(getNearShields()) + Integer.valueOf(getNearWarpDrive()));
				if(totalPeople == 60){
					System.out.println("|NO CASUALTIES TO REPORT.");
				} else {
					System.out.printf("CREW CASUALTIES: %d\n", 60 - totalPeople);
				}
				
				System.out.println("|\n|_______________________________________");
				System.out.println("*END OF DAMAGE REPORT                  *");
				System.out.println("****************************************");
			} else if (command.contains("phaser") && command.contains("fire")) {
	
				if (command.contains(" at ")) { // fire phasers at the hull power
												// 157
	
					command = command.replaceAll(" the ", "");
					int powerIndex = command.indexOf("power") + 6;
					int systemIndex = command.indexOf("at") + 2;
	
					String p = command.substring(powerIndex);
					String s = command.substring(systemIndex,
							(command.indexOf("power")));
					System.out
							.println(enterprise.firePhasers(Integer.valueOf(p), s));
	
				} else { // fire phasers power 157
					String p = command.substring(command.indexOf("power") + 6);
					System.out.println(enterprise.firePhasers(Integer.valueOf(p)));
				}
	
			} else if (command.contains("photon") && command.contains("fire")) {
	
				if (command.contains(" at ")) { // fire photons at the hull
	
					command = command.replaceAll(" the ", "");
					int systemIndex = command.indexOf("at") + 2;
	
					String s = command.substring(systemIndex);
					System.out.println(enterprise.firePhotons(s));
	
				} else { // fire potons
					System.out.println(enterprise.firePhotons());
				}
	
			} else if ((command.contains("lower") || command.contains("down"))
					&& command.contains("shield")) {
	
				if(Integer.valueOf(getNearBridge()) + Integer.valueOf(getNearShields()) > 0){
					enterprise.setShieldsUp(false);
					enterprise.setAftShields(0);
					enterprise.setForeShields(0);
					enterprise.setRightShields(0);
					enterprise.setLeftShields(0);
				} else {
					System.out.println("No personnel available to operate the shields!");
				}
	
			} else if ((command.contains("raise") || command.contains("up"))
					&& command.contains("shield")) {
				
				if(Integer.valueOf(getNearBridge()) + Integer.valueOf(getNearShields()) > 0){
					enterprise.setShieldsUp(true);
					enterprise.setAftShields(25);
					enterprise.setForeShields(25);
					enterprise.setRightShields(25);
					enterprise.setLeftShields(25);
				} else {
					System.out.println("No personnel available to operate the shields!");
				}
	
			} else if (command.contains("warp")) {
				if (enterprise.getWarpHealth() >= 0) {
					veng.setNumPhotons(getNumPhotons() - (int)(getWarpDriveHealth()/Ship.PHOTON_DAMAGE));
					veng.setNumPhotons(getNumPhotons() - (int)(getImpulseHealth()/Ship.PHOTON_DAMAGE));
					System.out.println("Going to warp, Captain.");
					System.out.println("Captain, the vengeance has once again crippled our propulsion systems!");
					enterprise.setWarpHealth(0);
					enterprise.setImpulseHealth(0);
				} else {
					System.out
							.println("Captain, the Warp Drives are not functional!");
				}
			} else if (command.contains("mantis shrimp")
					&& command.contains("fire")) {
				System.out.println("Send'em back to where they came from!");
				System.out
						.println("The mantis shrimps fired successfully, but the cold vacuum of space killed all of them instantly. Good job, you singlehandedly extincted a whole species.");
			} else if (command.contains("viewscreen")) {
				System.out.println("On screen, captain"); // change the viewscreen mode to
													// show the vengeance
				Game.drawViewscreen = !Game.drawViewscreen;
			} else {
				System.out.println("Be sure to use accepted starfleet protocol, Captain!");
			}
		} catch(Exception e){
			System.out.println("Are you feeling alright, Captain? You aren't making sense.");
		}

	}

	public void rechargePhaserBanks(){
		enterprise.rechargePhasers();
	}
	
	public int getImpulseHealth(){
		return (int) enterprise.getImpulseHealth();
	}
	
	public double getForeShields() {
		return enterprise.getForeShields();
	}

	public double getAftShields() {
		return enterprise.getAftShields();
	}

	public double getLeftShields() {
		return enterprise.getLeftShields();
	}

	public double getRightShields() {
		return enterprise.getRightShields();
	}

	public boolean foreShieldsOn() {
		return enterprise.getForeShields() > 0.0;
	}

	public boolean aftShieldsOn() {
		return enterprise.getAftShields() > 0.0;
	}

	public boolean leftShieldsOn() {
		return enterprise.getLeftShields() > 0.0;
	}

	public boolean rightShieldsOn() {
		return enterprise.getRightShields() > 0.0;
	}

	public int getNumNearBridge() {
		return enterprise.nearBridge.size();
	}

	public String getNearBridge() {
		return String.valueOf(enterprise.nearBridge.size());
	}

	public String getNearSensors() {
		return String.valueOf(enterprise.nearSensors.size());
	}

	public String getNearPhasers() {
		return String.valueOf(enterprise.nearPhasers.size());
	}

	public String getNearPhotons() {
		return String.valueOf(enterprise.nearPhotons.size());
	}

	public String getNearShields() {
		return String.valueOf(enterprise.nearShieldGen.size());
	}

	public String getNearWarpDrive() {
		return String.valueOf(enterprise.nearWarpDrive.size());
	}

	public int getPhaserHealth() {
		return (int) enterprise.getPhaserHealth();
	}

	public int getPhotonHealth() {
		return (int) enterprise.getPhotonHealth();
	}

	public int getShieldGenHealth() {
		return (int) enterprise.getShieldGenHealth();
	}

	public int getSensorHealth() {
		return (int) enterprise.getSensorHealth();
	}

	public int getWarpDriveHealth() {
		return (int) enterprise.getWarpHealth();
	}

	public int getPhaserLevel() {
		return enterprise.getPhaserLevel();
	}

	public boolean shieldsUp() {
		return enterprise.shieldsUp();
	}
	
	public int getHullHealth(){
		return (int) enterprise.getHullIntegrity();
	}
	
	public int getShieldLevel() {
		return (int) (enterprise.getForeShields() + enterprise.getAftShields() + enterprise.getRightShields() + enterprise.getLeftShields());
	}

	public int getNumPhotons() {
		return enterprise.getNumPhotons();
	}
	
	public double getVengeanceBearing(){
		return veng.getBearing();
	}
}
