import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LevelLoader implements ActionListener {

	private LevelFrame levelFrame;
	private final int defaultUnitSize = 25;

	//Constructor
	public LevelLoader(LevelFrame levelFrame) {
		this.levelFrame = levelFrame;
	}
	
	public void loadLevel(String fileName) {
		//Open the file containing the level data
		File levelData = new File("resources/levels/" + fileName);
		Scanner fileReader = null;
		try {
			fileReader = new Scanner(levelData);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int columns = 0;
		int rows = 0;
		ArrayList<Brick> bricks = new ArrayList<>();
		Paddle paddle = null;
		//Read the file
		while(fileReader.hasNextLine()) {
			String line = fileReader.nextLine();
			//Set the paddle where there is a '_' character
			if(line.charAt(0) == '_') {
				paddle = new Paddle(0, rows * 2, 3, 1, defaultUnitSize, defaultUnitSize);
				rows++;
			}
			//Set the bricks where there is a '*' character
			for(int i = 0; i < line.length(); i++) {
				columns++;
				if(line.charAt(i) == '*') {
					if(line.charAt(i + 1) == '*' && i < line.length() - 1) {
						Brick temp = new Brick(i, rows * 2, 2, 1, defaultUnitSize, defaultUnitSize);
						bricks.add(temp);
					}
					else if(line.charAt(i + 1) == '-' && line.charAt(i - 1) == '-' && i < line.length() - 1 && i > 0){
						Brick temp = new Brick(i, rows * 2, 1, 1, defaultUnitSize, defaultUnitSize);
						bricks.add(temp);
					}
				}
			}
			rows++;
		}
		//Get the row and columns
		columns /= (rows - 1);
		rows *= 2;
		GameFrame gameFrame = new GameFrame(bricks, paddle, rows, columns, defaultUnitSize);
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
		//Get the filename of the file containing the level data
        String fileName = new StringBuilder().append("Level ").append(e.getActionCommand()).append(".txt").toString();
        loadLevel(fileName);
    }

	public int getDefaultUnitSize() {
		return defaultUnitSize;
	}
}
