import java.awt.Color;


public class Ship {

	/*
	 * sections
	 */
	private boolean sections; //you can shut off life support or other systems in these areas
	
	/*
	 * moving about
	 */
	private boolean impulseEngines;
	private boolean warpDrive;
	private double maxWarp;
	private int impulseThrusters;
	
	/*
	 * detection
	 */
	private boolean sensorArray;
	private int sensorArrayLevel;
	
	/*
	 * defensive
	 */ 
	private boolean shieldGenerator;
	private boolean secondaryShieldGenerator;
	private int shieldGeneratorLevel;
	private int secondaryShieldGeneratorLevel;
	private boolean incomingAlarm;
	
	/*
	 * offensive
	 */
	private boolean phaserBanks;
	private boolean photonTorps;
	private int numPhotons;
	private int phaserLevel;
	private int phaserRecharge; //rate per cycle
	private int photonReload; //number of cycles, a delay
	
		
	/*
	 * computer systems
	 */
	private boolean autoTarget;
	private int autoTargetLevel;
	private boolean smartRepair;
	private boolean backupComputer;
	private boolean hackingStation;
	private int hackerQuality;
	
	/*
	 * the hull
	 */
	private String hullMaterial;
	private int hullStrength;
	private int hullWeight;
	private Color hullColor;
	
	/*
	 * basic stuff
	 */
	private String name;
	private String captainName;
	private String federationID;

	//holy constructor!
	public Ship(boolean sections, boolean impulseEngines, boolean warpDrive,
			double maxWarp, int impulseThrusters, boolean sensorArray,
			int sensorArrayLevel, boolean shieldGenerator,
			boolean secondaryShieldGenerator, int shieldGeneratorLevel,
			int secondaryShieldGeneratorLevel, boolean incomingAlarm,
			boolean phaserBanks, boolean photonTorps, int numPhotons,
			int phaserLevel, int phaserRecharge, int photonReload,
			boolean autoTarget, int autoTargetLevel, boolean smartRepair,
			boolean backup, boolean hackingStation, int hackerQuality,
			String hullMaterial, int hullStrength, int hullWeight,
			Color hullColor, String name, String captainName,
			String federationID) {
		super();
		this.sections = sections;
		this.impulseEngines = impulseEngines;
		this.warpDrive = warpDrive;
		this.maxWarp = maxWarp;
		this.impulseThrusters = impulseThrusters;
		this.sensorArray = sensorArray;
		this.sensorArrayLevel = sensorArrayLevel;
		this.shieldGenerator = shieldGenerator;
		this.secondaryShieldGenerator = secondaryShieldGenerator;
		this.shieldGeneratorLevel = shieldGeneratorLevel;
		this.secondaryShieldGeneratorLevel = secondaryShieldGeneratorLevel;
		this.incomingAlarm = incomingAlarm;
		this.phaserBanks = phaserBanks;
		this.photonTorps = photonTorps;
		this.numPhotons = numPhotons;
		this.phaserLevel = phaserLevel;
		this.phaserRecharge = phaserRecharge;
		this.photonReload = photonReload;
		this.autoTarget = autoTarget;
		this.autoTargetLevel = autoTargetLevel;
		this.smartRepair = smartRepair;
		this.backupComputer = backup;
		this.hackingStation = hackingStation;
		this.hackerQuality = hackerQuality;
		this.hullMaterial = hullMaterial;
		this.hullStrength = hullStrength;
		this.hullWeight = hullWeight;
		this.hullColor = hullColor;
		this.name = name;
		this.captainName = captainName;
		this.federationID = federationID;
	}
	
	//the vengeance should use this one
	public Ship(){
		sections = true;
		impulseEngines = true;
		warpDrive = true;
		maxWarp = 9.999;
		impulseThrusters = 100; //E. max is 50
		sensorArray = true;
		sensorArrayLevel = 20; //E. Max is 10
		shieldGenerator = true;
		secondaryShieldGenerator = true;
		shieldGeneratorLevel = 20; //E. max is 10
		secondaryShieldGeneratorLevel = 20;
		incomingAlarm = true;
		phaserBanks = true;
		photonTorps = true;
		numPhotons = 640;
		phaserLevel = 20;
		phaserRecharge = 20;
		photonReload = 5; //lower is better
		autoTarget = true;
		autoTargetLevel = 20;
		smartRepair = true;
		backupComputer = true;
		hackingStation = true;
		hackerQuality = 5;
		hullMaterial = "Neutronium";
		hullStrength = 1500;
		hullWeight = 15000;
		hullColor = new Color (255, 255, 255);
		name = "USS VENGEANCE";
		captainName = "Khan Noonien Singh";
		federationID = "Unknown";
		
		
		
	}
}
