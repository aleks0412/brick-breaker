
public class Ball {
	private int x;
	private int y;
	private int vx;
	private int vy;
	private int radius;
	private boolean isLaunched;

	//Constructor
	public Ball(int x, int y, int vx, int vy, int unitSize) {
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.radius = unitSize;
		this.isLaunched = false;
	}

	//Launch the ball
	public void launch() {
		isLaunched = true;
	}

	//Move the ball
	public void move() {
		this.x += this.vx;
		this.y += this.vy;
	}

	//Update the dimensions
	public void updateDimensions(int newUnitSize) {
		this.radius = newUnitSize;
		this.vx /= Math.abs(this.vx);
		this.vx *= (newUnitSize / 5);
		this.vy /= Math.abs(this.vy);
		this.vy *= (newUnitSize / 5);
	}

	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getVx() {
		return this.vx;
	}
	
	public int getVy() {
		return this.vy;
	}
	
	public int getRadius() {
		return this.radius;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	void setVx(int vx) {
		this.vx = vx;
	}
	
	void setVy(int vy) {
		this.vy = vy;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public boolean isLaunched() {
		return isLaunched;
	}
}
