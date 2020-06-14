
public class Player extends Entity{
	private double friction;
	private Vector v;
	private double maxSpeedX;
	private double maxSpeedY;
	
	public Player(String texturePath, int x, int y, int textureX, int textureY) {
		super(texturePath, x, y, textureX, textureY);
		this.friction = 0.8;
		this.maxSpeedX = 15;
		this.maxSpeedY = 30;
		this.v = new Vector();
	}
	
	public Vector getVector() {
		return this.v;
	}
	
	public void setForce(Vector f) {
		this.v.set(f.getX(), f.getY());
	}
	
	public void addForce(Vector f) {
		double x = f.getX()>maxSpeedX?maxSpeedX:f.getX(), 
			   y= f.getY()>maxSpeedY?maxSpeedY:f.getY();
		this.v.add(x,y);
	}
	
	public void moveV() {
		double dx = this.v.getX(), dy = this.v.getY();
		this.xPos += dx;
		this.yPos += dy;
		if(Math.abs(dx) < 0.000001 ) dx = 0;
		if(Math.abs(dy) < 0.000001) dy = 0;
		this.v.set(dx*this.friction, dy*this.friction);
	}
	
}
