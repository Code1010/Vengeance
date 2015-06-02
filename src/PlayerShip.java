import java.awt.Color;
import java.util.ArrayList;

public class PlayerShip extends Ship {
	
	boolean[] sectionsSealed = {false, false, false, false, false, false, false};
	
	ArrayList<Crewmember> nearBridge = new ArrayList<Crewmember>(); 
	ArrayList<Crewmember> nearWarpDrive = new ArrayList<Crewmember>(); 
	ArrayList<Crewmember> nearSensors = new ArrayList<Crewmember>(); 
	ArrayList<Crewmember> nearPhasers = new ArrayList<Crewmember>(); 
	ArrayList<Crewmember> nearPhotons = new ArrayList<Crewmember>(); 
	ArrayList<Crewmember> nearShieldGen = new ArrayList<Crewmember>(); 
	
	private double impulseHealth;
	private double warpHealth;
	private double sensorHealth;
	private double shieldGenHealth;
	private double secondShieldGenHealth;
	private double computerHealth;
	private double phaserHealth;
	private double photonHealth;
	private double hullIntegrity;
	private boolean crewAlive;
	private boolean captainAlive;
	private boolean shieldsUp;
	private double shieldIntegrity;
	private USSVengeance vengeanceObject;
	private double foreShields;
	

	private double aftShields;
	private double leftShields;
	private double rightShields;
	
	public PlayerShip(USSVengeance u){
		super(true, true, true, 8.0, 120,
				true, 100, true,
				true, 100,
				100, true, true,
				true, 64, 100, 12,
				560, true, 100, false, true,
				false, 0, "Tritanium", 1500,
				1500, Color.LIGHT_GRAY, "USS Enterprise", "James T. Kirk", "NCC 1701", 1500);
		
		vengeanceObject = u;
		foreShields = 0.0;
		aftShields = 0.0;
		leftShields = 0.0;
		rightShields = 0.0;
		impulseHealth = 59.0;
		warpHealth = 0;
		sensorHealth = 100.0;
		shieldGenHealth = 100.0;
		secondShieldGenHealth = 100.0;
		computerHealth = 100.0;
		phaserHealth = 100.0;
		photonHealth = 100.0;
		hullIntegrity = 100.0;
		crewAlive = true;
		captainAlive = true;
		shieldsUp = true;
		shieldIntegrity = 100.0;
		
		for(int i = 1; i <= 10; i++){
			nearBridge.add(new Crewmember());
		}
		for(int i = 1; i <= 10; i++){
			nearPhasers.add(new Crewmember());
		}
		for(int i = 1; i <= 10; i++){
			nearPhotons.add(new Crewmember());
		}
		for(int i = 1; i <= 10; i++){
			nearSensors.add(new Crewmember());
		}
		for(int i = 1; i <= 10; i++){
			nearShieldGen.add(new Crewmember());
		}
		for(int i = 1; i <= 10; i++){
			nearWarpDrive.add(new Crewmember());
		}
	}
	
	public PlayerShip(boolean sections, boolean impulseEngines,
			boolean warpDrive, double maxWarp, int impulseThrusters,
			boolean sensorArray, int sensorArrayLevel, boolean shieldGenerator,
			boolean secondaryShieldGenerator, int shieldGeneratorLevel,
			int secondaryShieldGeneratorLevel, boolean incomingAlarm,
			boolean phaserBanks, boolean photonTorps, int numPhotons,
			int phaserLevel, int phaserRecharge, int photonReload,
			boolean autoTarget, int autoTargetLevel, boolean smartRepair,
			boolean backup, boolean hackingStation, int hackerQuality,
			String hullMaterial, int hullStrength, int hullWeight,
			Color hullColor, String name, String captainName,
			String federationID, USSVengeance u, int maxPL) {
		super(sections, impulseEngines, warpDrive, maxWarp, impulseThrusters,
				sensorArray, sensorArrayLevel, shieldGenerator,
				secondaryShieldGenerator, shieldGeneratorLevel,
				secondaryShieldGeneratorLevel, incomingAlarm, phaserBanks,
				photonTorps, numPhotons, phaserLevel, phaserRecharge,
				photonReload, autoTarget, autoTargetLevel, smartRepair, backup,
				hackingStation, hackerQuality, hullMaterial, hullStrength,
				hullWeight, hullColor, name, captainName, federationID, maxPL);
		
		foreShields = 0.0;
		aftShields = 0.0;
		leftShields = 0.0;
		rightShields = 0.0;
		impulseHealth = 59.0;
		warpHealth = 0;
		sensorHealth = 100.0;
		shieldGenHealth = 100.0;
		secondShieldGenHealth = 100.0;
		computerHealth = 100.0;
		phaserHealth = 100.0;
		photonHealth = 100.0;
		hullIntegrity = 100.0;
		crewAlive = true;
		captainAlive = true;
		shieldsUp = true;
		shieldIntegrity = 100.0;
		vengeanceObject = u;
	}
	
	public void movePerson(String loc, String dest){
			if(loc.equals("bridge")){
				
				if(dest.equals("bridge")){
					System.out.println("Already there, captain");
				} else if(dest.contains("shield")){
					nearShieldGen.add(nearBridge.remove(0));
				} else if(dest.contains("photon")){
					nearPhotons.add(nearBridge.remove(0));
				} else if(dest.contains("phaser")){
					nearPhasers.add(nearBridge.remove(0));
				} else if(dest.contains("sensor")){
					nearSensors.add(nearBridge.remove(0));
				} else if(dest.contains("warp") || loc.contains("drive")){
					nearWarpDrive.add(nearBridge.remove(0));
				}
				
			} else if(loc.contains("shield")){
				
				if(dest.equals("bridge")){
					nearBridge.add(nearShieldGen.remove(0));
				} else if(dest.contains("shield")){
					System.out.println("Already there, captain");
				} else if(dest.contains("photon")){
					nearPhotons.add(nearShieldGen.remove(0));
				} else if(dest.contains("phaser")){
					nearPhasers.add(nearShieldGen.remove(0));
				} else if(dest.contains("sensor")){
					nearSensors.add(nearShieldGen.remove(0));
				} else if(dest.contains("warp") || loc.contains("drive")){
					nearWarpDrive.add(nearShieldGen.remove(0));
				}
				
			} else if(loc.contains("photon")){
				
				if(dest.equals("bridge")){
					nearBridge.add(nearPhotons.remove(0));
				} else if(dest.contains("shield")){
					nearShieldGen.add(nearPhotons.remove(0));
				} else if(dest.contains("photon")){
					System.out.println("Already there, captain");
				} else if(dest.contains("phaser")){
					nearPhasers.add(nearPhotons.remove(0));
				} else if(dest.contains("sensor")){
					nearSensors.add(nearPhotons.remove(0));
				} else if(dest.contains("warp") || loc.contains("drive")){
					nearWarpDrive.add(nearPhotons.remove(0));
				}
				
			} else if(loc.contains("phaser")){
				
				if(dest.equals("bridge")){
					nearBridge.add(nearPhasers.remove(0));
				} else if(dest.contains("shield")){
					nearShieldGen.add(nearPhasers.remove(0));
				} else if(dest.contains("photon")){
					nearPhotons.add(nearPhasers.remove(0));
				} else if(dest.contains("phaser")){
					System.out.println("Already there, captain");
				} else if(dest.contains("sensor")){
					nearSensors.add(nearPhasers.remove(0));
				} else if(dest.contains("warp") || loc.contains("drive")){
					nearWarpDrive.add(nearPhasers.remove(0));
				}
				
			} else if(loc.contains("sensor")){
				
				if(dest.equals("bridge")){
					nearBridge.add(nearSensors.remove(0));
				} else if(dest.contains("shield")){
					nearShieldGen.add(nearSensors.remove(0));
				} else if(dest.contains("photon")){
					nearPhotons.add(nearSensors.remove(0));
				} else if(dest.contains("phaser")){
					nearPhasers.add(nearSensors.remove(0));
				} else if(dest.contains("sensor")){
					System.out.println("Already there, captain");
				} else if(dest.contains("warp") || loc.contains("drive")){
					nearWarpDrive.add(nearSensors.remove(0));
				}
				
			} else if(loc.contains("warp") || loc.contains("drive")){
				
				if(dest.equals("bridge")){
					nearBridge.add(nearWarpDrive.remove(0));
				} else if(dest.contains("shield")){
					nearShieldGen.add(nearWarpDrive.remove(0));
				} else if(dest.contains("photon")){
					nearPhotons.add(nearWarpDrive.remove(0));
				} else if(dest.contains("phaser")){
					nearPhasers.add(nearWarpDrive.remove(0));
				} else if(dest.contains("sensor")){
					nearSensors.add(nearWarpDrive.remove(0));
				} else if(dest.contains("warp") || loc.contains("drive")){
					System.out.println("Already there, captain");
				}
				
			}
	}
	
	public String damage(String system, double intensity){
		system = system.toLowerCase();
		String ret = "";
		int dif = 0;
		if(shieldsUp){
			shieldIntegrity -= intensity;
			ret = system + "...shields are holding";
			if(shieldIntegrity < 0){
				dif = (int) (shieldIntegrity * -1);
				shieldIntegrity = 0;
				ret = system + "...shields penetrated!";
			}
			
			damageSystem(system, dif);
			
		} else {
			system = damageSystem(system, intensity);
			ret = system + "...shields penetrated!";
		}
		
		return ret;
	}
	
	public String damageSystem(String system, double intensity){
		String ret = "hull";
		
		if(system.contains("impulse")){
			impulseHealth -= intensity;
			ret = "impulse engines";
		} else if(system.contains("warp")){
			warpHealth -= intensity;
			ret = "warp engines";
		} else if(system.contains("sensor")){
			sensorHealth -= intensity;
			ret = "sensor array";
		} else if(system.contains("shield")){
			if(shieldGenHealth > 0){
				shieldGenHealth -= intensity;
				ret = "main shield generator";
			}
			
			if(shieldGenHealth < 0){
				secondShieldGenHealth -= (shieldGenHealth * -1);
				shieldGenHealth = 0;
				ret = "secondary shield generator";
			}
			
			if((shieldGenHealth <= 0) && (secondShieldGenHealth <= 0)){
				shieldsUp = false;
			}
			
		} else if(system.contains("computer")){
			computerHealth -= intensity;
			ret = "computer systems";
		} else if(system.contains("phaser")){
			phaserHealth -= intensity;
			ret = "phaser banks";
		} else if(system.contains("photon")){
			photonHealth -= intensity;
			ret = "photon torpedoes";
		} else {
			hullIntegrity -= intensity;
			ret = "the hull";
		}
		
		return system;
	}

	
	private String targetRandomSystem(double p) {
		String[] enemySystems = { "impulse", "warp", "sensor", "shieldGen",
				"2ndShieldGen", "computer", "phaser banks", "photons", "hull" };
		int index = (int) ((enemySystems.length - 1) * Math.random());
		String system = enemySystems[index];
		vengeanceObject.damageVengeance(p, system);
		return system;
	}

	public String firePhasers(int power, String system) {
		if (hasPhaserBanks() && phaserHealth > 0) {

			if (getPhaserLevel() > power) {

				if (hasAutoTarget()) {
					double rand = 100 * Math.random();
					if (getAutoTargetLevel() >= rand) {
						setPhaserLevel(getPhaserLevel() - power);
						return "<<Phasers fired successfully and hit the "
								+ vengeanceObject.damageVengeance(power, system) + ">>";
					} else {
						setPhaserLevel(getPhaserLevel() - power);
						return "<<Phasers fired successfully, but missed the target>>";
					}
				} else {
					if (getPhaserLevel() >= getMaxPhaserLevel() - 5) {
						setPhaserLevel(getPhaserLevel() - power);
						return "<<Phasers fired successfully and hit the "
						+ vengeanceObject.damageVengeance(power, system) + ">>";
					}
					setPhaserLevel(getPhaserLevel() - power);
					return "<<Phasers fired successfully, but missed the target>>";
				}

			} else {
				return "<<Insufficient Phaser Bank Power!>>";
			}

		} else {
			return "<<Phaser Banks nonfunctional or unavailable>>";
		}
	}

	public String firePhasers(int power) {
		if (hasPhaserBanks() && phaserHealth > 0) {

			if (getPhaserLevel() > power) {

				if (hasAutoTarget()) {
					double rand = 100 * Math.random();
					if (getAutoTargetLevel() >= rand) {
						setPhaserLevel(getPhaserLevel() - power);
						return "<<Phasers fired successfully and hit the "
								+ targetRandomSystem((double) power) + ">>";
					} else {
						setPhaserLevel(getPhaserLevel() - power);
						return "<<Phasers fired successfully, but missed the target>>";
					}
				} else {
					if (getPhaserLevel() >= getMaxPhaserLevel() - 5) {
						setPhaserLevel(getPhaserLevel() - power);
						return "<<Phasers fired successfully and hit the "
								+ targetRandomSystem((double) power) + ">>";
					} else {
						setPhaserLevel(getPhaserLevel() - power);
						return "<<Phasers fired successfully, but missed the target>>";
					}					
				}

			} else {
				return "<<Insufficient Phaser Bank Power!>>";
			}

		} else {
			return "<<Phaser Banks nonfunctional or unavailable>>";
		}
	}

	public String firePhotons(){
		if((getNumPhotons() > 0) && photonHealth > 0){
			if (hasAutoTarget()) {
				double rand = 100 * Math.random();
				if (getAutoTargetLevel() >= rand) {
					setNumPhotons(getNumPhotons()-1);
					return "<<Photon torpedo fired successfully and hit the "
							+ targetRandomSystem(Ship.PHOTON_DAMAGE) + ">>";
				} else {
					setNumPhotons(getNumPhotons()-1);
					return "<<Photon torpedo fired successfully, but missed the target>>";
				}
			} else {
				if (Math.random() > .745) {
					setNumPhotons(getNumPhotons()-1);
					return "<<Photon torpedo fired successfully and hit the "
							+ targetRandomSystem(Ship.PHOTON_DAMAGE) + ">>";
				} else {
					setNumPhotons(getNumPhotons()-1);
					return "<<Photon torpedo fired successfully, but missed the target>>";
				}
			}
		} else {
			return "<<Photon torpedo bays nonfunctional or toepedoes unavailable>>";
		}
	}

	public String firePhotons(String system){
		if((getNumPhotons() > 0) && photonHealth > 0){
			if (hasAutoTarget()) {
				double rand = 100 * Math.random();
				if (getAutoTargetLevel() >= rand) {
					setNumPhotons(getNumPhotons()-1);
					return "<<Photon torpedo fired successfully and hit the "
							+ vengeanceObject.damageVengeance(Ship.PHOTON_DAMAGE, system) + ">>";
				} else {
					setNumPhotons(getNumPhotons()-1);
					return "<<Photon torpedo fired successfully, but missed the target>>";
				}
			} else {
				if (Math.random() > .745) {
					setNumPhotons(getNumPhotons()-1);
					return "<<Photon torpedo fired successfully and hit the "
							+ vengeanceObject.damageVengeance(Ship.PHOTON_DAMAGE, system) + ">>";
				} else {
					setNumPhotons(getNumPhotons()-1);
					return "<<Photon torpedo fired successfully, but missed the target>>";
				}
			}
		} else {
			return "<<Photon torpedo bays nonfunctional or toepedoes unavailable>>";
		}
	}
	
	public boolean[] getSectionsSealed() {
		return sectionsSealed;
	}

	public void setSectionsSealed(boolean[] sectionsSealed) {
		this.sectionsSealed = sectionsSealed;
	}

	public ArrayList<Crewmember> getNearBridge() {
		return nearBridge;
	}

	public void setNearBridge(ArrayList<Crewmember> nearBridge) {
		this.nearBridge = nearBridge;
	}

	public ArrayList<Crewmember> getNearWarpDrive() {
		return nearWarpDrive;
	}

	public void setNearWarpDrive(ArrayList<Crewmember> nearWarpDrive) {
		this.nearWarpDrive = nearWarpDrive;
	}

	public ArrayList<Crewmember> getNearSensors() {
		return nearSensors;
	}

	public void setNearSensors(ArrayList<Crewmember> nearSensors) {
		this.nearSensors = nearSensors;
	}

	public ArrayList<Crewmember> getNearPhasers() {
		return nearPhasers;
	}

	public void setNearPhasers(ArrayList<Crewmember> nearPhasers) {
		this.nearPhasers = nearPhasers;
	}

	public ArrayList<Crewmember> getNearPhotons() {
		return nearPhotons;
	}

	public void setNearPhotons(ArrayList<Crewmember> nearPhotons) {
		this.nearPhotons = nearPhotons;
	}

	public ArrayList<Crewmember> getNearShieldGen() {
		return nearShieldGen;
	}

	public void setNearShieldGen(ArrayList<Crewmember> nearShieldGen) {
		this.nearShieldGen = nearShieldGen;
	}

	public double getImpulseHealth() {
		return impulseHealth;
	}

	public void setImpulseHealth(double impulseHealth) {
		this.impulseHealth = impulseHealth;
	}

	public double getWarpHealth() {
		return warpHealth;
	}

	public void setWarpHealth(double warpHealth) {
		this.warpHealth = warpHealth;
	}

	public double getSensorHealth() {
		return sensorHealth;
	}

	public void setSensorHealth(double sensorHealth) {
		this.sensorHealth = sensorHealth;
	}

	public double getShieldGenHealth() {
		return shieldGenHealth;
	}

	public void setShieldGenHealth(double shieldGenHealth) {
		this.shieldGenHealth = shieldGenHealth;
	}

	public double getSecondShieldGenHealth() {
		return secondShieldGenHealth;
	}

	public void setSecondShieldGenHealth(double secondShieldGenHealth) {
		this.secondShieldGenHealth = secondShieldGenHealth;
	}

	public double getComputerHealth() {
		return computerHealth;
	}

	public void setComputerHealth(double computerHealth) {
		this.computerHealth = computerHealth;
	}

	public double getPhaserHealth() {
		return phaserHealth;
	}

	public void setPhaserHealth(double phaserHealth) {
		this.phaserHealth = phaserHealth;
	}

	public double getPhotonHealth() {
		return photonHealth;
	}

	public void setPhotonHealth(double photonHealth) {
		this.photonHealth = photonHealth;
	}

	public double getHullIntegrity() {
		return hullIntegrity;
	}

	public void setHullIntegrity(double hullIntegrity) {
		this.hullIntegrity = hullIntegrity;
	}

	public boolean isCrewAlive() {
		return crewAlive;
	}

	public void setCrewAlive(boolean crewAlive) {
		this.crewAlive = crewAlive;
	}

	public boolean isCaptainAlive() {
		return captainAlive;
	}

	public void setCaptainAlive(boolean captainAlive) {
		this.captainAlive = captainAlive;
	}

	public boolean isShieldsUp() {
		return shieldsUp;
	}

	public void setShieldsUp(boolean shieldsUp) {
		this.shieldsUp = shieldsUp;
	}

	public double getShieldIntegrity() {
		return shieldIntegrity;
	}

	public void setShieldIntegrity(double shieldIntegrity) {
		this.shieldIntegrity = shieldIntegrity;
	}

	public USSVengeance getVengeanceObject() {
		return vengeanceObject;
	}

	public void setVengeanceObject(USSVengeance vengeanceObject) {
		this.vengeanceObject = vengeanceObject;
	}

	public double getForeShields() {
		return foreShields;
	}

	public void setForeShields(double foreShields) {
		this.foreShields = foreShields;
	}

	public double getAftShields() {
		return aftShields;
	}

	public void setAftShields(double aftShields) {
		this.aftShields = aftShields;
	}

	public double getLeftShields() {
		return leftShields;
	}

	public void setLeftShields(double leftShields) {
		this.leftShields = leftShields;
	}

	public double getRightShields() {
		return rightShields;
	}

	public void setRightShields(double rightShields) {
		this.rightShields = rightShields;
	}
	
}
