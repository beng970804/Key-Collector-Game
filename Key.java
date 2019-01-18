/**
File Name: Key.java
This is an entity to represent Keys to be collected by the Player
 */
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

//Class Key inherots from GameButton
public class Key extends GameButton{
	//key icon
    private ImageIcon keyIcon;
    
	//constructor
	//parameter: button name and coordinate
    public Key(String buttonName, Point coordinate) {
        super(buttonName);
        setCoordinate(coordinate);
        deactivate();
    }

	//constructor
	//parameter: button name
    public Key(String buttonName) {
        super(buttonName);
        deactivate();
    }
    
	//Function to set key icon by key name
	//parameter: key name
    public void setIconByKeyName(String name){
        switch(name){      
            case "pinkey":
                keyIcon = new ImageIcon("pinkey.gif");
                keyIcon = resizeIcon(keyIcon);
                setIcon(keyIcon);
                break;
            case "donkey":
                keyIcon = new ImageIcon("donkey.gif");
                keyIcon = resizeIcon(keyIcon);
                setIcon(keyIcon);
                break;
            case "keydisk":
                keyIcon = new ImageIcon("keydisk.gif");
                keyIcon = resizeIcon(keyIcon);
                setIcon(keyIcon);
                break;
            case "keynote":
                keyIcon = new ImageIcon("keynote.gif");
                keyIcon = resizeIcon(keyIcon);
                setIcon(keyIcon);
                break;
            case "monkey":
                keyIcon = new ImageIcon("monkey.gif");
                keyIcon = resizeIcon(keyIcon);
                setIcon(keyIcon);
                break;
            default:
                break;
        }
		
		//return nothing
    }
    
	//function to generate point list, to be implemented by all keys
    public List<Point> generatePointList(){
        List<Point> pointList = new ArrayList<>();
        return pointList;
    }
	//return: a list of coordinates
    
	//override super class function
    @Override
    public void activate() {
        setBorder(new LineBorder(Color.GREEN));
        setEnabled(true);
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
            }
        });
    }
    
    public void deactivate(){
        setBorder(new LineBorder(Color.GRAY));
        for( ActionListener al : getActionListeners() ) {
            removeActionListener( al );
        }
    }
}
