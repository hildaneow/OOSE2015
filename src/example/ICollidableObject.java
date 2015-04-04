package example;

import org.newdawn.slick.geom.Vector2f;

public interface ICollidableObject {

	public Vector2f getBounceDirection(Ball ball);

}
