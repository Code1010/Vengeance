
public class USSVengeance extends Ship{
	
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
	private double shieldIntegrity;
	private boolean shieldsUp;
	
	public USSVengeance(){
		super();
		
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
		shieldIntegrity = 100.0;
		shieldsUp = true;
	}
	
	public void damage(double damage) {
		
	}
	
}
