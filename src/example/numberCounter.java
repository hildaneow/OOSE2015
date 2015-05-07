package example;

//Class for keeping track of amount of balls and bricks hit
public class numberCounter {

	static int numberOfBalls = 1;
	static int brickHit = 0;
	
	//Method for counting balls when decreasing - Called in updateBallPosition in Main
	public static void minusBallCount(){
		numberOfBalls--;
		System.out.println(numberCounter.numberOfBalls);
	}
	
	//Method for counting balls when increasing - Called in updateBallPosition in Main
	public static void plusBallCount(){
		numberOfBalls++;
		
	}
	

	//Method for counting amount of bricks hit - Called in brickCollision in Main
	public static void scoreCounter() {
		brickHit++;
	}
	

}
