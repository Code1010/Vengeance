import java.awt.Color;


public class PlayerShip extends Ship {

	boolean[][] sectionsSealed = {{false, false, false}, {false, false, false}, {false, false, false}};
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
			String federationID) {
		super(sections, impulseEngines, warpDrive, maxWarp, impulseThrusters,
				sensorArray, sensorArrayLevel, shieldGenerator,
				secondaryShieldGenerator, shieldGeneratorLevel,
				secondaryShieldGeneratorLevel, incomingAlarm, phaserBanks, photonTorps,
				numPhotons, phaserLevel, phaserRecharge, photonReload, autoTarget,
				autoTargetLevel, smartRepair, backup, hackingStation, hackerQuality,
				hullMaterial, hullStrength, hullWeight, hullColor, name, captainName,
				federationID);

		impulseHealth = 100.0;
		warpHealth = 100.0;
		warpHealth = 100.0;
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
		
	}
	
	public String firePhasers(int power) {
		if(hasPhaserBanks() && phaserHealth > 0){
			
			if(getPhaserLevel() > power){
				
				if(hasAutoTarget()){
					double rand = 100 * Math.random();
					if(getAutoTargetLevel() > rand){
						
					}
				} else {
					
				}
				
				setPhaserLevel(getPhaserLevel() - power);
				return "<<Phasers fired successfully, but missed the target>>";
			} else {
				return "<<Insufficient Phaser Bank Power!>>";
			}
			
		} else {
			return "<<Phaser Banks nonfunctional or unavailable>>";
		}
	}
	
	
}
