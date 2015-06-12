
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
	private double bearing;
	private int smokey;
	
	public USSVengeance(){
		super();
		
		impulseHealth = 360.0;
		warpHealth = 360.0;
		warpHealth = 360.0;
		sensorHealth = 360.0;
		shieldGenHealth = 360.0;
		secondShieldGenHealth = 360.0;
		computerHealth = 360.0;
		phaserHealth = 360.0;
		photonHealth = 360.0;
		hullIntegrity = 360.0;
		crewAlive = true;
		captainAlive = true;
		shieldIntegrity = 360.0;
		shieldsUp = true;
		smokey = 0;
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
	
	public String damageVengeance(double intensity, String system){
		system = system.toLowerCase();
		String ret = "";
		int dif = 0;
		if(shieldsUp){
			shieldIntegrity -= intensity;
			ret = system + "...Vengeance shields are holding";
			if(shieldIntegrity < 0){
				dif = (int) (shieldIntegrity * -1);
				shieldIntegrity = 0;
				ret = system + "...Vengeance shields penetrated!";
			}
			
			damageSystem(system, dif);
			
		} else {
			system = damageSystem(system, intensity);
			ret = system + "...Vengeance shields penetrated!";
		}
		
		return ret;
	}

	public void rechargePhasers(){
		if(phaserHealth > 0){
			if(getPhaserLevel() < 360){
				setPhaserLevel(getPhaserLevel() + 1);
			}
		}
	}
	
	public void rechargeShields(){
		if(shieldGenHealth > 0){
			if(shieldIntegrity < 360)
				shieldIntegrity += .5;
		}
	}
	
	public void bear(){
		
		boolean changeDir = false;
		if(Math.random() > .65){
			changeDir = !changeDir;
		}
		
		if(changeDir){
			bearing += (Math.random() * 75);
		} else {
			bearing -= (Math.random() * 75);
		}
		
		if(bearing < 0){
			bearing = bearing + 360;
		} else if(bearing > 360){
			bearing = bearing - 360;
		}
	}
	
	public double getBearing() {
		return bearing;
	}

	public void setBearing(double bearing) {
		this.bearing = bearing;
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

	public double getShieldIntegrity() {
		return shieldIntegrity;
	}

	public void setShieldIntegrity(double shieldIntegrity) {
		this.shieldIntegrity = shieldIntegrity;
	}

	public boolean isShieldsUp() {
		return shieldsUp;
	}

	public void setShieldsUp(boolean shieldsUp) {
		this.shieldsUp = shieldsUp;
	}
	
}
