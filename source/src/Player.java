
public class Player extends Entity{
	private int speed;
	
	public Player(String texturePath, int x, int y) {
		super(texturePath, x, y);
		speed = 10;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public void moveRight() {
		this.xPos += this.speed;
	}
	
	public void moveLeft() {
		this.xPos -= this.speed;
	}
	
	public void moveUp() {
		this.yPos -= this.speed;
	}
	
	public void moveDown() {
		this.yPos += this.speed;
	}
	
}
