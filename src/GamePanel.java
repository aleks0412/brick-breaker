import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.lang.Thread;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class GamePanel extends JPanel implements MouseListener {

	private Ball ball;
	private Paddle paddle;
	private int rows;
	private int columns;
	private int unitWidth;
	private int unitHeight;
	private ArrayList<Brick> bricks;
	private int ballsLeft = 2;
	private int frameCounter;
	private int endFrame = 0;

	//Constructor
	public GamePanel(ArrayList<Brick> bricks, Paddle paddle, int rows, int columns, int defaultUnitSize) {
		this.ball = new Ball(0, 0, defaultUnitSize / 3, defaultUnitSize / 3, defaultUnitSize);
		this.bricks = bricks;
		this.paddle = paddle;
		this.paddle.setBall(this.ball);
		this.rows = rows;
		this.columns = columns;
		this.unitWidth = this.getWidth() / this.columns;
		this.unitHeight = this.getHeight() / this.rows;
		this.paddle.setMaxMovingDistance(this.unitWidth * columns);
		this.setBackground(Color.BLUE);
		this.addMouseListener(this);
	}

	//Main Loop
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//Set the maximum moving distance of the paddle
		paddle.setMaxMovingDistance(unitWidth * columns);
		//Set the ball color
		g.setColor(Color.YELLOW);
		//Draw the ball
		g.fillOval(ball.getX(), ball.getY(), ball.getRadius(), ball.getRadius());
		if(ball.isLaunched()) {
			ball.move(); //Move the ball
		}
		//Check if the ball is at the left or the right of the screen
		if(ball.getX() + ball.getRadius() >= this.getWidth() || ball.getX() <= 0) {
			//Bounce from the wall
			ball.setVx(-ball.getVx());
			ball.move();
		}
		//Check if the balls is at the top or the bottom of the screen
		if(ball.getY() + ball.getRadius() >= this.getHeight() || ball.getY() <= 0) {
			//Check if the ball is at the bottom of the screen and loose a ball
			if(ball.getY() + ball.getRadius() >= this.getHeight()) {
				looseBall();
			}
			//Bounce from the wall
			ball.setVy(-ball.getVy());
			ball.move();
		}
		//Set the brick color
		g.setColor(Color.WHITE);
		for(int i = 0; i < bricks.size(); i++) {
			//Draw the bricks
			g.fillRect(bricks.get(i).getX(), bricks.get(i).getY(), bricks.get(i).getWidth(), bricks.get(i).getHeight());
			//Check for collision between the bricks and the ball
			if(bricks.get(i).detectCollision(ball)) {
				g.clearRect(bricks.get(i).getX(), bricks.get(i).getY(), bricks.get(i).getWidth(), bricks.get(i).getHeight());
				bricks.remove(i);
			}
		}
		//Check if the bricks are over
		if(bricks.size() == 0){
			//End the game
			if(endFrame == 0) {
				endFrame = frameCounter;
			}
			if(frameCounter == endFrame + 5) {
				GameFrame parentFrame = (GameFrame) SwingUtilities.getWindowAncestor(this); //Get the parent window
				JOptionPane.showMessageDialog(parentFrame, "You Win"); //You Win message
				parentFrame.dispatchEvent(new WindowEvent(parentFrame, WindowEvent.WINDOW_CLOSING)); //Close the level
			}
		}
		//Set the paddle color
		g.setColor(Color.GREEN);
		//Draw the paddle
		g.fillRect(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight());
		//Move the paddle
		paddle.move();
		//Detect collision between the ball and the paddle
		if(ball.isLaunched()) {
			paddle.detectCollision(ball);
		}
		//Keep the game running at 60 FPS
		try {
			Thread.sleep(1000 / 60);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		frameCounter++;
		this.repaint();
	}

	//Update the dimensions
	public void updateDimensions() {
		this.unitWidth = this.getWidth() / this.columns;
		this.unitHeight = this.getHeight() / this.rows;
		for(int i = 0; i < bricks.size(); i++) {
			bricks.get(i).updateDimensions(this.unitWidth, this.unitHeight);
			paddle.updateDimensions(this.unitWidth, this.unitHeight);
			ball.updateDimensions(this.unitHeight);
		}
	}

	//Loose a ball
	public void looseBall() {
		//Get the parent window
		GameFrame parentFrame = (GameFrame) SwingUtilities.getWindowAncestor(this);
		//Remove one ball
		if(ballsLeft > 0) {
			ballsLeft--;
			parentFrame.updateBallsLeftLabel(ballsLeft);
		}
		else {
			//Game Over message
			JOptionPane.showMessageDialog(parentFrame, "Game Over");
			//Close the level
			parentFrame.dispatchEvent(new WindowEvent(parentFrame, WindowEvent.WINDOW_CLOSING));
		}
	}

	//Mouse events
	@Override
	public void mouseClicked(MouseEvent e) {
		ball.launch();
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
