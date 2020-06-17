public class Ball extends Entity{
	private double dx;
	private double dy;
	private double maxSpeedX;
	private double maxSpeedY;
	
	public Ball(String texturePath, int x, int y, int textureX, int textureY) {
		super(texturePath, x, y, textureX, textureY);
		this.maxSpeedX = 300; //15
		this.maxSpeedY = 300; //30
		this.dx = 0;
		this.dy = 0;
	}

	public double getdx() {
		return this.dx;
	}
	
	public double getdy() {
		return this.dy;
	}
	
	public void setForce(Vector f) {
		double fx = f.getX()>maxSpeedX?maxSpeedX:f.getX(), 
			   fy= f.getY()>maxSpeedY?maxSpeedY:f.getY();
			this.dx = fx;
			this.dy = fy;
	}
	
	public void addForce(Vector f) {
		double fx = f.getX()>maxSpeedX?maxSpeedX:f.getX(), 
			   fy= f.getY()>maxSpeedY?maxSpeedY:f.getY();
		this.dx += fx;
		this.dy += fy;
	}
	
	public void update() {
		
		if(this.dx > this.maxSpeedX) dx = this.maxSpeedX;
		else if (this.dx < -this.maxSpeedX) dx = -this.maxSpeedX;
		
		if(this.dy > this.maxSpeedY) dy = this.maxSpeedY;
		else if (this.dy < -this.maxSpeedY) dy = -this.maxSpeedY;
		
		//double nextX = this.xPos + this.dx, nextY = this.yPos + this.dy;

		this.xPos += this.dx;
		this.yPos += this.dy;
	}
	
}
