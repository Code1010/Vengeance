
public class Crewmember {

	private boolean alive = true;
	private double health = 100.0;
	
	public boolean isAlive() {
		return alive;
	}
	
	public void kill() {
		alive = false;
	}
	
	public double getHealth() {
		return health;
	}
	
	public void damage(double damage){
		health -= damage;
		if(health <= 0){
			alive = false;
		}
	}
	
	public void setHealth(double health) {
		this.health = health;
	}
	
	
}
