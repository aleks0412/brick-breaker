import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameFrame extends JFrame {

    private Color backgroundColor = new Color(153, 217, 234);
	private JLabel ballsLeftLabel = new JLabel("Balls Left: 2");

	//Constructor
	public GameFrame(ArrayList<Brick> bricks, Paddle paddle, int rows, int columns, int defaultUnitSize) {
		//Make the game fullscreen
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.getContentPane().setBackground(Color.BLUE);
		//Make the cursor invisible
		this.setCursor( this.getToolkit().createCustomCursor(new BufferedImage( 1, 1, BufferedImage.TYPE_INT_ARGB ), new Point(), null ) );
		//Label showing how many ball are left
		ballsLeftLabel.setFont(new Font("Calibri", Font.BOLD, 30));
		ballsLeftLabel.setForeground(Color.WHITE);
		ballsLeftLabel.setVerticalAlignment(SwingConstants.CENTER);
		this.add(ballsLeftLabel, BorderLayout.NORTH);
		//The game panel
		GamePanel gamePanel = new GamePanel(bricks, paddle, rows, columns , defaultUnitSize);
		//Set the title of the frame
		this.setTitle("Brick");
		// Center the frame
		this.setLocationRelativeTo(null);
		//Make the frame visible
		this.setVisible(true);
		this.add(gamePanel);
		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent componentEvent) {
		        gamePanel.updateDimensions();
		    }
		});
	}

	//Update the label showing how many balls are left
	public void updateBallsLeftLabel(int ballsLeft) {
		ballsLeftLabel.setText(new StringBuilder().append("Balls Left: ").append(ballsLeft).toString());
	}
	
}
