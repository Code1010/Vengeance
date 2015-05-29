import java.awt.Color;
import java.util.ArrayList;

public class PlayerShip extends Ship {
	
	boolean[] sectionsSealed = {false, false, false, false, false, false, false};
	
	ArrayList<Crewmember> nearBridge = new ArrayList<Crewmember>(); // 0 0
	ArrayList<Crewmember> nearWarpDrive = new ArrayList<Crewmember>(); // 0 1
	ArrayList<Crewmember> nearImpulseEngines = new ArrayList<Crewmember>(); // 0 2
	ArrayList<Crewmember> nearSensors = new ArrayList<Crewmember>(); // 1 0
	ArrayList<Crewmember> nearPhasers = new ArrayList<Crewmember>(); // 1 1
	ArrayList<Crewmember> nearPhotons = new ArrayList<Crewmember>(); // 1 2
	ArrayList<Crewmember> nearShieldGen = new ArrayList<Crewmember>(); // 2 0
	
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
	private double foreShields = 0.0;
	private double aftShields = 0.0;
	private double leftShields = 0.0;
	private double rightShields = 0.0;

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
					if (getAutoTargetLevel() <= rand) {
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
					if (getAutoTargetLevel() <= rand) {
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
	
}
