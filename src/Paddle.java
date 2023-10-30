import java.awt.*;

public class Paddle extends Brick {
	
	private int maxMovingDistance;
	private Ball ball;

	//Constructor
	public Paddle(int unitX, int unitY, int columnsNum, int rowsNum, int unitWidth, int unitHeight) {
		super(unitX, unitY, columnsNum, rowsNum, unitWidth, unitHeight);
	}

	//Move the paddle
	public void move() {
		super.setX(MouseInfo.getPointerInfo().getLocation().x);
		//Make the ball follow the paddle if it is not launched
		if(!ball.isLaunched()) {
			ball.setX((super.getX() - (ball.getRadius() / 2)) + (super.getWidth() / 2));
			ball.setY(super.getY() - ball.getRadius());
		}
		//Make the paddle collide with the right side of the screen
		if(super.getX() >= maxMovingDistance - super.getWidth()){
			super.setX(maxMovingDistance - super.getWidth());
			if(!ball.isLaunched()) {
				ball.setX((super.getX() - (ball.getRadius() / 2)) + (super.getWidth() / 2));
				ball.setY(super.getY() - ball.getRadius());
			}
		}
	}

	public int getMaxMovingDistance() {
		return maxMovingDistance;
	}

	public void setMaxMovingDistance(int maxMovingDistance) {
		this.maxMovingDistance = maxMovingDistance;
	}

	public Ball getBall() {
		return ball;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}
}
