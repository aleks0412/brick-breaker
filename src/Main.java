import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		//Start the level frame
		LevelFrame levelFrame = new LevelFrame();
		levelFrame.setSize(400, 600);
		levelFrame.setTitle("Brick");
		levelFrame.setLocationRelativeTo(null); // Center the frame
		levelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		levelFrame.setVisible(true);
	}

}