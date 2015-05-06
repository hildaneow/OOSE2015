package example;

public class numberCounter {

	static int numberOfBalls = 1;
	static int brickHit = 0;
	
	public static void minusBallCount(){
		numberOfBalls--;
		System.out.println(numberCounter.numberOfBalls);
	}
	
	public static void plusBallCount(){
		numberOfBalls++;
		
	}
	


	public static void scoreCounter() {
		// TODO Auto-generated method stub
		brickHit++;
	}
	

}
