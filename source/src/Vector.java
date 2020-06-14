
public class Vector {
	private double x;
	private double y;
	
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector() {
		this.x = 0;
		this.y = 0;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public double getLength() {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void set(double x, double y) {
		this.setX(x);
		this.setY(y);
	}
	
	public void add(Vector v1) {
		this.x += v1.getX();
		this.y += v1.getY();
	}
	
	public void add(double x, double y) {
		this.x += x;
		this.y += y;
	}
	
	public Vector add(Vector v1, Vector v2) {
		return new Vector(v1.x + v2.x, v1.y + v2.y);
	}
	
	public void setVector(int x1, int y1, int x2, int y2, int i) {
		double x3 = x2 - x1, y3 = y2 - y1;
		double l = Math.sqrt(Math.pow(x3, 2) + Math.pow(y3, 2));
		
		this.x = (x3/l)*i;
		this.y = (y3/l)*i;
	}
	
	public String toString() {
		return this.x + "," + this.y;
	}
}
