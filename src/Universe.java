
public class Universe {

	private String[][] map;
	private int enterpriseRow;
	private int enterpriseCol;
	private int vRow;
	private int vCol;
	
	public Universe(){
		map = new String[10][10]; //this needs to be a square
		String[] features = {"*", "#", "^", "M", "P", "$", " ", " ", " "};
		/*
		 * Symbol | Meaning
		 * *      | Rock
		 * #	  | Abandoned Ship
		 * ^	  | Space Junk
		 * M	  | Moon
		 * P	  | Planet
		 * $	  | Star
		 * E      | Enterprise
		 * V      | Vengeance
		 */
		
		
		//This is the starting location of the enterprise
		enterpriseRow = 3;
		enterpriseCol = 1;
		
		//this is the starting location of the vengeance
		vRow = 7;
		vCol = 9;
		
		for(int r = 0; r < map.length; r++){
			for(int c = 0; c < map[r].length; c++){
				int in = (int) ((features.length) * Math.random());
				map[r][c] = features[in];
			}
		}
		
		map[enterpriseRow][enterpriseCol] = "E";
		map[vRow][vCol] = "V";
		
	}
	
	@Override
	public String toString(){
		
		String toReturn = "";
		for(int r = 0; r < map.length; r++){
			for(int c = 0; c < map[r].length; c++){
				toReturn += map[r][c] + " ";
			}
			toReturn += "\n";
		}
		
		return toReturn;
	}
	
}
