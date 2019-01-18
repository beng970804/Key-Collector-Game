/**
File name: KeyB.java
This is the class of Key for donkey aka Key B.
 */
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

//Class KeyB inherits from Key 
public class KeyB extends Key{
	//constructor
	//parameter: coordinate
    public KeyB(Point coordinate) {
        super("donkey", coordinate);
        setIconByKeyName("donkey");
    }
    
	//Function to generate a list of possibles moves able to choose by player after collecting this key
    public List<Point> generatePointList(){
		//Can move up to 3 squares diagonally, but cannot move horizontally or vertically.
        List<Point> pointList = new ArrayList<>();
        int pointCount = 0;
        int px = (int)getCoordinate().getX();
        int py = (int)getCoordinate().getY();

        for (int i=px-3;i<=px+3;i++){
            for(int j=py-3;j<=py+3;j++){
                if((i>=0) && (i<9) && (j>=0) && (j<9) && ((i!=px)||(j!=py))){
                    if ((((j-i)==(py-px)))||(Math.abs(i-px)==(Math.abs(j-py)))){
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
