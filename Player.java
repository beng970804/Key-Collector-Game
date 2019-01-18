/**
File name: Player.java
This is the class of player who is the controlled by the user.
A player can perform actions such as to collect key
 */
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

//Class Player inherits from GameButton 
public class Player extends GameButton{
	//player attributes
    private ImageIcon playerIcon;
    private int index=0;
    private List<Key> keyList = new ArrayList<>(5);
    private int keyCount=0;
    
	//constructor
	//parameter: player index, player name, starting coordinate
    public Player(int index, String buttonName, Point coordinate) {
        super(buttonName);
		this.index = index;
        for (int i=0;i<5;i++){
            keyList.add(new Key(""));
        }
        setCoordinate(coordinate);
        playerIcon = resizeIcon(getImageIconByIndex(index));
        setIcon(playerIcon);
        setHorizontalAlignment(CENTER);
        setHorizontalTextPosition(CENTER);
        setContentAreaFilled(false);
        setPreferredSize(new Dimension(10,10));
        setEnabled(false);
    }

	//fucntion for player to collect their keys
	//parameter: key name
    public void collectKey(String name){
        boolean isDuplicate = false;
		//check if the key had been collected before
        if (keyCount<5){
            for (int i=0;i<keyCount;i++){
                if(keyList.get(i).getButtonName().equals(name))
                    isDuplicate = true;
            }
            if(!isDuplicate){
                keyList.set(keyCount, new Key(name));
                keyCount++;
            }
        }
    }
    
	//function to retrieve player icon by player index
	//parameter: player index
    public ImageIcon getImageIconByIndex(int index){
        ImageIcon img = new ImageIcon();
        switch(index){      
            case 1:
                img = new ImageIcon("bangei.gif");
                break;
            case 2:
                img = new ImageIcon("arkimides.gif");
                break;
            case 3:
                img = new ImageIcon("canser.gif");
                break;
            case 4:
                img = new ImageIcon("dozciztem.gif");
                break;
            default:
                break;
        }
        return img;
    }
	//return: player icon

	//function to generate available point list avaialable to player before colllecting key
    public List<Point> generatePointList(){
		//can only move 1 or 2 squares in any direction at a time
        List<Point> pointList = new ArrayList<>();
		//if no key collected, then point list will be the original one 
        if(keyCount==0){
            int pointCount = 0;
            int px = (int)getCoordinate().getX();
            int py = (int)getCoordinate().getY();
            
            for (int i=px-2;i<=px+2;i++){
                for (int j=py-2;j<=py+2;j++){
                    if((i>=0)&&(i<9)&&(j>=0)&&(j<9)){
                        int abs_x = (Math.abs(i-px)==0?100:Math.abs(i-px));
                        if(((Math.abs(j-py)/abs_x==1)&& (i!=px) && (j!=py))){
                            pointList.add(pointCount++,new Point(i,j));
                        }
                        if ((i==px)^(j==py)){
                            pointList.add(pointCount++,new Point(i,j));
                        }
                    }
                }
            }
        }//else, get point list of collected key
		else{
            String keyName = keyList.get(keyCount-1).getButtonName();
            Key temp = new Key("");
            switch (keyName) {
                case "pinkey":
                    temp = new KeyA(getCoordinate());
                    break;
                case "donkey":
                    temp = new KeyB(getCoordinate());
                    break;
                case "keydisk":
                    temp = new KeyC(getCoordinate());
                    break;
                case "keynote":
                    temp = new KeyD(getCoordinate());
                    break;
                case "monkey":
                    temp = new KeyE(getCoordinate());
                    break;
                default:
                    break;
            }
            pointList = temp.generatePointList();
        }
        return pointList;
    }
	//return a list of available point to move by player
    
    //getter and setter
    public int getIndex() {
        return index;
    }

    public List<Key> getKeyList() {
        return keyList;
    }

    public int getKeyCount() {
        return keyCount;
    }
    
}
