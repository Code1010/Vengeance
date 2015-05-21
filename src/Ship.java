import java.awt.Color;


public class Ship {

	public static final double PHOTON_DAMAGE = 25;
	
	/*
	 * location in the universe
	 */
	private int sector;
	
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
	private double velocity;
	
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
	private int maxPhaserLevel;
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
			String federationID, int maxPL) {
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
		velocity = 0;
		maxPhaserLevel = maxPL;
	}
	
	//the vengeance should use this one
	public Ship(){
		sections = false;
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
		phaserLevel = 80;
		phaserRecharge = 20;
		photonReload = 5; //lower is better
		autoTarget = true;
		autoTargetLevel = 80;
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
		velocity = 0;
		maxPhaserLevel = 1000;
	}

	public boolean isSections() {
		return sections;
	}

	public void setSections(boolean sections) {
		this.sections = sections;
	}

	public boolean isImpulseEngines() {
		return impulseEngines;
	}

	public void setImpulseEngines(boolean impulseEngines) {
		this.impulseEngines = impulseEngines;
	}

	public boolean isWarpDrive() {
		return warpDrive;
	}

	public void setWarpDrive(boolean warpDrive) {
		this.warpDrive = warpDrive;
	}

	public double getMaxWarp() {
		return maxWarp;
	}

	public void setMaxWarp(double maxWarp) {
		this.maxWarp = maxWarp;
	}

	public int getImpulseThrusters() {
		return impulseThrusters;
	}

	public void setImpulseThrusters(int impulseThrusters) {
		this.impulseThrusters = impulseThrusters;
	}

	public boolean isSensorArray() {
		return sensorArray;
	}

	public void setSensorArray(boolean sensorArray) {
		this.sensorArray = sensorArray;
	}

	public int getSensorArrayLevel() {
		return sensorArrayLevel;
	}

	public void setSensorArrayLevel(int sensorArrayLevel) {
		this.sensorArrayLevel = sensorArrayLevel;
	}

	public boolean isShieldGenerator() {
		return shieldGenerator;
	}

	public void setShieldGenerator(boolean shieldGenerator) {
		this.shieldGenerator = shieldGenerator;
	}

	public boolean isSecondaryShieldGenerator() {
		return secondaryShieldGenerator;
	}

	public void setSecondaryShieldGenerator(boolean secondaryShieldGenerator) {
		this.secondaryShieldGenerator = secondaryShieldGenerator;
	}

	public int getShieldGeneratorLevel() {
		return shieldGeneratorLevel;
	}

	public void setShieldGeneratorLevel(int shieldGeneratorLevel) {
		this.shieldGeneratorLevel = shieldGeneratorLevel;
	}

	public int getSecondaryShieldGeneratorLevel() {
		return secondaryShieldGeneratorLevel;
	}

	public void setSecondaryShieldGeneratorLevel(int secondaryShieldGeneratorLevel) {
		this.secondaryShieldGeneratorLevel = secondaryShieldGeneratorLevel;
	}

	public boolean isIncomingAlarm() {
		return incomingAlarm;
	}

	public void setIncomingAlarm(boolean incomingAlarm) {
		this.incomingAlarm = incomingAlarm;
	}

	public boolean hasPhaserBanks() {
		return phaserBanks;
	}

	public void setPhaserBanks(boolean phaserBanks) {
		this.phaserBanks = phaserBanks;
	}

	public boolean isPhotonTorps() {
		return photonTorps;
	}

	public void setPhotonTorps(boolean photonTorps) {
		this.photonTorps = photonTorps;
	}

	public int getNumPhotons() {
		return numPhotons;
	}

	public void setNumPhotons(int numPhotons) {
		this.numPhotons = numPhotons;
	}

	public int getPhaserLevel() {
		return phaserLevel;
	}

	public void setPhaserLevel(int phaserLevel) {
		this.phaserLevel = phaserLevel;
	}

	public int getPhaserRecharge() {
		return phaserRecharge;
	}

	public void setPhaserRecharge(int phaserRecharge) {
		this.phaserRecharge = phaserRecharge;
	}

	public int getPhotonReload() {
		return photonReload;
	}

	public void setPhotonReload(int photonReload) {
		this.photonReload = photonReload;
	}

	public boolean hasAutoTarget() {
		return autoTarget;
	}

	public void setAutoTarget(boolean autoTarget) {
		this.autoTarget = autoTarget;
	}

	public int getAutoTargetLevel() {
		return autoTargetLevel;
	}

	public void setAutoTargetLevel(int autoTargetLevel) {
		this.autoTargetLevel = autoTargetLevel;
	}

	public boolean isSmartRepair() {
		return smartRepair;
	}

	public void setSmartRepair(boolean smartRepair) {
		this.smartRepair = smartRepair;
	}

	public boolean isBackupComputer() {
		return backupComputer;
	}

	public void setBackupComputer(boolean backupComputer) {
		this.backupComputer = backupComputer;
	}

	public boolean isHackingStation() {
		return hackingStation;
	}

	public void setHackingStation(boolean hackingStation) {
		this.hackingStation = hackingStation;
	}

	public int getHackerQuality() {
		return hackerQuality;
	}

	public void setHackerQuality(int hackerQuality) {
		this.hackerQuality = hackerQuality;
	}

	public String getHullMaterial() {
		return hullMaterial;
	}

	public void setHullMaterial(String hullMaterial) {
		this.hullMaterial = hullMaterial;
	}

	public int getHullStrength() {
		return hullStrength;
	}

	public void setHullStrength(int hullStrength) {
		this.hullStrength = hullStrength;
	}

	public int getHullWeight() {
		return hullWeight;
	}

	public void setHullWeight(int hullWeight) {
		this.hullWeight = hullWeight;
	}

	public Color getHullColor() {
		return hullColor;
	}

	public void setHullColor(Color hullColor) {
		this.hullColor = hullColor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCaptainName() {
		return captainName;
	}

	public void setCaptainName(String captainName) {
		this.captainName = captainName;
	}

	public String getFederationID() {
		return federationID;
	}

	public void setFederationID(String federationID) {
		this.federationID = federationID;
	}

	public int getSector() {
		return sector;
	}

	public void setSector(int sector) {
		this.sector = sector;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public int getMaxPhaserLevel() {
		return maxPhaserLevel;
	}

	public void setMaxPhaserLevel(int maxPhaserLevel) {
		this.maxPhaserLevel = maxPhaserLevel;
	}
}
