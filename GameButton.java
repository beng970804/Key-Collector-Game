/**
File name: GameButton.java
This is the class that is used to create the 9x9 board of the game.
The GameButton has attributes such as Name, Icon and Coordinate
It also has function to highlight available buttons to pressed when user moved.
*/
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

//Class GameButton inherits from JButton
public class GameButton extends JButton{
	//Button attributes
    private String buttonName;
    private Point coordinate;
    private ImageIcon movableIcon = new ImageIcon("moving.png");
    private ImageIcon stationaryIcon = new ImageIcon("square.png");

	//Constructor
    public GameButton() {
        stationaryIcon = resizeIcon(stationaryIcon);
        setIcon(stationaryIcon);
        setHorizontalAlignment(CENTER);
        setHorizontalTextPosition(CENTER);
        setContentAreaFilled(false);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
            }
        });
        setPreferredSize(new Dimension(10,10));
    }

	//Constructor with button name as parameter
    public GameButton(String buttonName) {
        super(buttonName);
        this.buttonName = buttonName;
        stationaryIcon = resizeIcon(stationaryIcon);
        setIcon(stationaryIcon);
        setHorizontalAlignment(CENTER);
        setHorizontalTextPosition(CENTER);
        setContentAreaFilled(false);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
            }
        });
        setPreferredSize(new Dimension(10,10));
    }

	//Function to resize icon
	//Parameter: ImageIcon
    public ImageIcon resizeIcon(ImageIcon icon){
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH );
        ImageIcon resizedIcon = new ImageIcon(newimg);
        return resizedIcon;
    }
	//Return: ImageIcon
    
	//Function to allow the buttons to be clicked
    public void activate() {
        movableIcon = resizeIcon(movableIcon);
        setIcon(movableIcon);
        setEnabled(true);
        setBorder(new LineBorder(Color.GREEN));
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                GameButton btn = (GameButton)event.getSource();
                //System.out.println(buttonName);
            }
        });
		
		//return nothing
    }

	//Function to disable the button
    public void deactivate() {
        stationaryIcon = resizeIcon(stationaryIcon);
        setIcon(stationaryIcon);
        setEnabled(false);
		
		//return nothing
    }
    
	//Function to clear action listener of all the buttons
    public GameButton clearActionListener(){
        for(ActionListener actionListener:this.getActionListeners()){
            this.removeActionListener(actionListener);
        }
        return this;
    }
	//Return: GameButton with cleared action listener
	
	//getters and setters
    public String getButtonName() {
        return buttonName;
    }
	
    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }
    public Point getCoordinate() {
        return coordinate;
    }
	
    public void setCoordinate(Point coordinate) {
        this.coordinate = coordinate;
    }
}
