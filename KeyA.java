/**
File name: KeyA.java
This is the class of Key for pinkey aka Key A.
 */

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

//Class KeyA inherits from Key 
public class KeyA extends Key{
	//constructor
	//parameter: coordinate
    public KeyA(Point coordinate) {
        super("pinkey", coordinate);
        setIconByKeyName("pinkey");
    }
	
    //Function to generate a list of possibles moves able to choose by player after collecting this key
    public List<Point> generatePointList(){
		//Can only move 1 square in any direction.
        List<Point> pointList = new ArrayList<>();
        int pointCount = 0;
        int px = (int)getCoordinate().getX();
        int py = (int)getCoordinate().getY();

        for (int i=px-1;i<=px+1;i++){
            for(int j=py-1;j<=py+1;j++){
                if((i>=0) && (i<9) && (j>=0) && (j<9)){
                    if (!((px==i)&&(py==j))){
                        pointList.add(pointCount, new Point(i,j));
                        pointCount+=1;
                    } 
                }
            }
        }
        return pointList;
    }
	//return: a list of available keys
    
}
