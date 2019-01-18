/**
File Name : GameFrame.java
This file to create a GameFrame, which includes the components such as side menu, game board and game state.
It also allow user to run game, save game, load game, end game and display instructions and hints for the program.
*/
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.*;

//GameFrame class inherits from JFrame
public class GameFrame extends JFrame{
	//Part of MVC Design, View of Board
    public BoardView theView;
	//Part of MVC Design, Model of Board
    public BoardModel theModel;
	//Part of MVC Design, Controller of Board
    public BoardController theController;
	//side menu panel 
    JPanel leftPanel;
	//buttons to perform function on clicked
	JButton newButton = new JButton("New Game");
    JButton loadButton = new JButton("Load Game");
    JButton saveButton = new JButton("Save Game");
    JButton endButton = new JButton("End Game");
	JButton instruction = new JButton("Instructions");
	
    public GameFrame(){
        super("Key Collector Game");
        //frame setting
        setLayout(new BorderLayout());
        setMinimumSize(getSize());
        setPreferredSize(new Dimension(1200,530));
        setSize(800,500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//end frame setting
        
		//side menu Panel setting
		leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(150,500));
		leftPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		leftPanel.setLayout(new FlowLayout());
		newButton.setPreferredSize(new Dimension(120,30));
        newButton.setActionCommand("new");
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("new")){
					//invoke newGame() fucntion
                    newGame();
                }
            }
        });
        loadButton.setPreferredSize(new Dimension(120,30));
        loadButton.setActionCommand("load");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("load")){
					//invoke loadGame() fucntion
                    loadGame();
                }
            }
        });
		saveButton.setPreferredSize(new Dimension(120,30));
        saveButton.setActionCommand("save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("save")){
					//invoke saveGame() fucntion
                    saveGame();
                }
            }
        });
        endButton.setPreferredSize(new Dimension(120,30));
        endButton.setActionCommand("end");
        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("end")){
					//invoke endGame() fucntion
                    endGame();
                }
            }
        });
		instruction.setPreferredSize(new Dimension(120,30));
        instruction.setActionCommand("ins");
        instruction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("ins")){
					//invoke endGame() fucntion
                   instruction();
                }
            }
        });
		leftPanel.add(newButton);
		leftPanel.add(loadButton);
		leftPanel.add(saveButton);
		leftPanel.add(endButton);
		leftPanel.add(instruction);
		//end side menu setting by adding buttons into the panel
		
		//instantiate MVC
        theView = new BoardView();
        theModel = new BoardModel();
        theController = new BoardController(theView, theModel);
		
		//initialise value of controller
        theController.initialiseController();
        
		//pack all 3 panels: side menu, board view and game state into frame 
        this.add(leftPanel, BorderLayout.WEST);
        this.add(theView, BorderLayout.CENTER);
        this.add(theModel.getState(), BorderLayout.EAST);
        pack();
        setLocationByPlatform(true);
        validate();
        setVisible(true);
    }
    
	//Function to start new game
	public void newGame(){
		//initialise value of controller
		theController.initialiseController();
		//invoke run()
		run();
		
		//return nothing
	}
	
	//Function to start the game play
	public void run(){
		//invoke move() function in the controller by passing the game frame and a value(which will be explained in the move() function)
		theController.move(this, 99);
		
		//returns nothing
	}
	
	//Function to load game
	public void loadGame(){
		//invoke load()
		load();
		
		//return nothing
	}
	
	//Function to load saved program
	public void load(){
		//get the last saved turn from saved state
		int thisTurn = theModel.loadState();

		//update board after loading
		theView.updateView();
		theController.printBoard();
		
		//display message that the game is loaded successfully
		JOptionPane.showMessageDialog(null, "Game loaded successfully");
		
		//invoke move() function  by passing the GameFrame and the latest turn
		theController.move(this, thisTurn);
		
		//returns nothing
	}
	
	//Function to save a game
	public void saveGame(){
		//check is the game is saved successfully
		if (theModel.getState().save()){
			//display message if success
			JOptionPane.showMessageDialog(null, "Game saved successfully");
		}
		
		//return nothing
	}

    //Function to end the game
    public void endGame(){
		//remove all components
        removeAll();
        dispose();
        revalidate();
		
		//display the initial GameMenu
        GameMenu gm = new GameMenu();
		
		//returns nothing
    }
	
	public void instruction(){
		JOptionPane.showMessageDialog(null, "Pinkey Can only move 1 square in any direction. \nDonkey Can move up to" +
		"3 squares diagonally, but cannot move horizontally or vertically." + 
		"\nkey disk Can move up to 3 squares horizontally or vertically, but cannot move diagonally." + 
		"\nkey note Must move 2 squares in any direction (i.e. skip 1 square.)" + 
		"\nmonkey Can move up to 3 squares in any direction.");
	}
}
