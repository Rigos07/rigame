//import java.util.ArrayList;

public class Player extends Entity{
	private double friction;
	private double dx;
	private double dy;
	private double maxSpeedX;
	private double maxSpeedY;
	private boolean onGround;
	
	public Player(String texturePath, int x, int y, int textureX, int textureY) {
		super(texturePath, x, y, textureX, textureY);
		this.friction = 0.95;
		this.maxSpeedX = 15; //15
		this.maxSpeedY = 30; //30
		this.onGround = false;
		this.dx = 0;
		this.dy = 0;
	}
	
	public double getdx() {
		return this.dx;
	}
	
	public double getdy() {
		return this.dy;
	}
	
	public void setForce(double fx, double fy) {
		this.dx = fx>maxSpeedX?maxSpeedX:fx;
		this.dy = fy>maxSpeedY?maxSpeedY:fy;
	}
	
	public void addForce(Vector f) {
		double fx = f.getX()>maxSpeedX?maxSpeedX:f.getX(), 
			   fy= f.getY()>maxSpeedY?maxSpeedY:f.getY();
		this.dx += fx;
		this.dy += fy;
	}
	
	public boolean isOnGround() {
		return this.onGround;
	}
	
	public void setOnGround(boolean b) {
		this.onGround = b;
	}
	
	public boolean verticalCollision(double YPos, Entity e) {
		return (YPos <= e.getYPos() && YPos + super.getHeight() >= e.getYPos()) || ( YPos >= e.getYPos() && YPos <= e.getYPos() + e.getHeight());
	}
	
	public boolean horizontalCollision(double XPos, Entity e) {
		return (XPos >= e.getXPos() && XPos <= e.getXPos() + e.getWidth()) || (XPos <= e.getXPos() && XPos + super.getWidth() >= e.getXPos());
	}
	
	public boolean colliding(double x, double y, Entity e) {
		return this.verticalCollision(y, e) && this.horizontalCollision(x, e);
	}
	
	public void update(Background background) {
		
		// Friction
		this.dx *= this.friction;
		this.dy *= this.friction;
		
		//double nextX = this.xPos + this.dx, nextY = this.yPos + this.dy;
		//boolean horizontalCollision = false, verticalCollision = false;
		
		//Collisions resolving
		
		/*for(Entity e : this.collisions) {

			boolean FX_FY = this.colliding(nextX, nextY, e),
					FX_Y = this.colliding(nextX, this.yPos, e),
					X_FY = this.colliding(this.xPos, nextY, e);
			
			if(FX_FY && this.horizontalCollision(this.xPos, e) && !this.verticalCollision(this.yPos, e)) {
				verticalCollision = true;
			}
			if(FX_FY && !this.horizontalCollision(this.xPos, e) && this.verticalCollision(this.yPos, e)) {
				horizontalCollision = true;
			}
		}*/
		
		/*if(verticalCollision) {
			this.dy = -this.dy;
			this.setOnGround(true);
			if(jump) this.jump = false;
		} else {
			this.setOnGround(false);
		}
		
		if(horizontalCollision) {
			this.dx = -this.dx;
		}*/
		
		
		/*if (this.collisions.size() > 0) {
			if(this.dy > 0) this.dy = -this.dy;
			this.setOnGround(true);
			this.collisions = new ArrayList<Entity>();
			if(jump) this.jump = false;
		} else {
			this.setOnGround(false);
		}*/
		
		if(!this.onGround) this.dy += 1;
		
		if(this.dx > this.maxSpeedX) dx = this.maxSpeedX;
		else if (this.dx < -this.maxSpeedX) dx = -this.maxSpeedX;
		
		if(this.dy > this.maxSpeedY) dy = this.maxSpeedY;
		else if (this.dy < -this.maxSpeedY) dy = -this.maxSpeedY;
		
		
		if(Math.abs(this.dx) < 0.000001 ) dx = 0;
		if(Math.abs(this.dy) < 0.000001) dy = 0;
		
		/*nextX = this.xPos + this.dx;
		nextY = this.yPos + this.dy;
		if(nextX < 0 || nextX + this.getWidth() > background.getWidth()) this.dx = -this.dx;
		if(nextY < 0 || nextY + this.getHeight() > background.getHeight()) this.dy = -this.dy;*/

		
		this.xPos += this.dx;
		this.yPos += this.dy;
		//this.v.set(this.dx*this.friction, this.dy*this.friction);
		
	}
	
}
