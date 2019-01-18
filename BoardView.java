/**
File name: BoardView.java.
This is part of MVC implementation of the program. BoardView displays the UI of the components.
It consists of a 9x9 game board made from GameButton.
It allow user to update the view
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

//Class BoardView inherits from JPanel
class BoardView extends JPanel{
	//board components
    private GameButton[][] gameButton= new GameButton[9][9];

	//constructor
    public BoardView() {
        //board panel setting
        setPreferredSize(new Dimension(500, 500));
        setBorder(BorderFactory.createEmptyBorder(40,40,40,40));
        setLayout(new GridLayout(9, 9, 5, 5));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    //fucntion update UI
    public void updateView(){
        repaint();
		
		//return nothing
    }
    
	//function to get the UI
    public JPanel getBoardPanel(){
        return this;
    }
	//return: BoardView
	
	//getter
    public GameButton[][] getButton(){
        return gameButton;
    }
}
