/**
File name: This is Game Menu file.
It will prompt a menu to allow user choose between to start a new game, load a saved game or close the program.
*/
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.layout.Border;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import jdk.nashorn.internal.scripts.JO;

//GameMenu class inherits from JFrame, implementing Action Listener interface
class GameMenu extends JFrame implements ActionListener{
	//Buttons and their instantiation
    JButton newButton = new JButton("New Game"); 
    JButton loadButton = new JButton("Load Game");
    JButton endButton = new JButton("End Game");
	
	//GameFrame, another JFrame that will be called when the user choose to start a new game or load a saved game
    GameFrame gameFrame;
    
    public GameMenu() {
        super("Key Collector Game - Menu");
        //frame setting
        setMinimumSize(getSize());
        setPreferredSize(new Dimension(450,90));
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//end frame setting
        
        //components setting
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BorderLayout());
        newButton.setPreferredSize(new Dimension(150,30));
        newButton.setActionCommand("new");
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("new")){
					//invoke newGame() when user choose newButton
                    newGame();
                }
            }
        });
        loadButton.setPreferredSize(new Dimension(150,30));
        loadButton.setActionCommand("load");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("load")){
					//invoke loadGame() when user choose loadButton
                    loadGame();
                }
            }
        });
        endButton.setPreferredSize(new Dimension(150,30));
        endButton.setActionCommand("end");
        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("end")){
					//invoke endGame() when user choose endButton
                    endGame();
                }
            }
        });
        menuPanel.add(newButton,BorderLayout.WEST);
        menuPanel.add(loadButton,BorderLayout.CENTER);
        menuPanel.add(endButton,BorderLayout.EAST);
		//end components setting by adding to a panel

        //add menu panel to GameMenu frame, pack and display to user
        this.add(menuPanel);
        validate();
        pack();
        setLocationByPlatform(true);
        setVisible(true);
    }

	//Function to start a new game
    private void newGame() {
		//create new game frame, setup the GameFrame components
        gameFrame = new GameFrame();
        //invoke run() function in GameFrame, which allows user to start playing
        gameFrame.run();
        //close the GameMenu frame
        dispose();
    }

	//Function to load a new game
    private void loadGame() {
		//create new game frame, setup the GameFrame components
        gameFrame = new GameFrame();
		//invoke load() function in GameFrame, which allows user to load a saved game and continue playing
		gameFrame.load();
		//close the GameMenu frame
		dispose();
    }

	//Function to close program
    private void endGame() {
        removeAll();
        dispose();
        revalidate();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
