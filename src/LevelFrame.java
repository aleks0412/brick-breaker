import javax.swing.*;
import java.awt.*;

public class LevelFrame extends JFrame {
    private GridLayout gridLayout = new GridLayout(3, 3, 10, 10);
    private LevelLoader levelLoader = new LevelLoader(this);
    private JPanel levelsPanel = new JPanel();
    private JButton[] levelButtons = new JButton[9];
    private JLabel selectLevelLabel = new JLabel("Select Level:");
    private Color backgroundColor = new Color(153, 217, 234);
    private Color buttonsColor = new Color(0, 162, 232);

    //Constructor
    public LevelFrame(){
        this.getContentPane().setBackground(backgroundColor);
    	selectLevelLabel.setHorizontalAlignment(SwingConstants.CENTER);
    	selectLevelLabel.setFont(new Font("Calibri", Font.BOLD, 20));
        levelsPanel.setBackground(backgroundColor);
        levelsPanel.setLayout(gridLayout);
    	this.add(selectLevelLabel, BorderLayout.NORTH);
        this.add(levelsPanel, BorderLayout.CENTER);
        //Add buttons for each level
        for(int i = 0; i < levelButtons.length; i++) {
            levelButtons[i] = new JButton(String.valueOf(i + 1));
            levelButtons[i].addActionListener(levelLoader);
            levelButtons[i].setBackground(buttonsColor);
            levelsPanel.add(levelButtons[i]);
        }
    }
}