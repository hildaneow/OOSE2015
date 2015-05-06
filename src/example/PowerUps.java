package example;

import java.util.ArrayList;
import java.util.List;

public class PowerUps {

	
	private static List<PowerUpExtendBar> PUEB;
	private static List<PowerUpSmallerBar> PUSB;
	private static List<PowerUpNewBall> PUNB;
	private static List<PowerUpIncreaseSpeed> PUIS;
	private static List<PowerUpDecreaseSpeed> PUDS;
	
	
	public static void init() {
		
		PUEB = new ArrayList<PowerUpExtendBar>();
		PUSB = new ArrayList<PowerUpSmallerBar>();
		PUNB = new ArrayList<PowerUpNewBall>();
		PUIS = new ArrayList<PowerUpIncreaseSpeed>();
		PUDS = new ArrayList<PowerUpDecreaseSpeed>();
		
		
	}
	
		
}
