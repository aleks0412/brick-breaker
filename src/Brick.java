
public class Brick {
	private int unitX;
	private int unitY;
	private int columnsNum;
	private int rowsNum;
	private int x;
	private int y;
	private int width;
	private int height;

	//Constructor
	public Brick(int unitX, int unitY, int columnsNum, int rowsNum, int unitWidth, int unitHeight) {
		this.unitX = unitX;
		this.unitY = unitY;
		this.columnsNum = columnsNum;
		this.rowsNum = rowsNum;
		this.x = this.unitX * unitWidth;
		this.y = this.unitY * unitHeight;
		this.width = this.columnsNum * unitWidth;
		this.height = this.rowsNum * unitHeight;
	}

	//Detect Collision
	public boolean detectCollision(Ball ball) {
		//Get the collision points
		int collisionX = Math.max(this.x, Math.min(this.x + this.width - ball.getRadius(), ball.getX()));
		int collisionY = Math.max(this.y, Math.min(this.y + this.height - ball.getRadius(), ball.getY()));
		//Get the distance between the ball`s centre and the collision point
		int xDistance = ball.getX() - collisionX;
		int yDistance = ball.getY() - collisionY;
		int distance = xDistance * xDistance + yDistance * yDistance;
		//Check if the distance is smaller than the radius
		if(distance <= ball.getRadius() * ball.getRadius()) {
			//Bounce the ball
			if(ball.getX() >= this.x && ball.getX() <= this.x + this.width) {
				ball.setVy(-ball.getVy());
			}
			else if(ball.getY() >= this.y && ball.getY() <= this.y + this.height) {
				ball.setVx(-ball.getVx());
			}
			else {
				ball.setVx(-ball.getVx());
				ball.setVy(-ball.getVy());
			}
			ball.move();
			return true;
		}
		else {
			return false;
		}
	}

	//Update the dimensions
	public void updateDimensions(int newUnitWidth, int newUnitHeight) {
		this.x = this.unitX * newUnitWidth;
		this.y = this.unitY * newUnitHeight;
		this.width = this.columnsNum * newUnitWidth;
		this.height = this.rowsNum * newUnitHeight;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	void setX(int x) {
		this.x = x;
	}
	
	void setY(int y) {
		this.y = y;
	}
	
	void setWidth(int width) {
		this.width = width;
	}
	
	void setHeight(int height) {
		this.height = height;
	}

	public int getUnitX() {
		return unitX;
	}

	public void setUnitX(int unitX) {
		this.unitX = unitX;
	}

	public int getUnitY() {
		return unitY;
	}

	public void setUnitY(int unitY) {
		this.unitY = unitY;
	}

	public int getColumnsNum() {
		return columnsNum;
	}

	public void setColumnsNum(int columnsNum) {
		this.columnsNum = columnsNum;
	}

	public int getRowsNum() {
		return rowsNum;
	}

	public void setRowsNum(int rowsNum) {
		this.rowsNum = rowsNum;
	}
}
