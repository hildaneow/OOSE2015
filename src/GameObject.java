
public class GameObject {
	protected int posX;
	protected int posY;
	protected int Width;
	protected int Height;
	
	public GameObject(int x, int y, int width, int height){
		this.posX = x;
		this.posY = y;
		this.Width = width;
		this.Height = height;
	}

	public boolean overlaps(GameObject object) {
		// TODO Auto-generated method stub
		return false;
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
