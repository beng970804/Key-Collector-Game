/**
File name: Chest.java
This is a class to represent the chest of the game
 */

import java.awt.Dimension;
import java.awt.Point;
import javax.swing.ImageIcon;

//Class Chest inherits from GameButton
public class Chest extends GameButton {
	//icon of the chest
    private ImageIcon chestIcon;
    
    public Chest() {
        super("Chest");
		//chest setting
        chestIcon = new ImageIcon("chest.gif");
        chestIcon = resizeIcon(chestIcon);
        setCoordinate(new Point(4,4));
        setIcon(chestIcon);
        setHorizontalAlignment(CENTER);
        setHorizontalTextPosition(CENTER);
        setContentAreaFilled(false);
        setPreferredSize(new Dimension(10,10));
    }
}
