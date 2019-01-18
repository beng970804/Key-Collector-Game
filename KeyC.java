/**
File name: KeyC.java
This is the class of Key for keydisk aka Key C.
 */
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

//Class KeyC inherits from Key 
public class KeyC extends Key {
	//constructor
	//parameter: coordinate
    public KeyC(Point coordinate) {
        super("keydisk", coordinate);
        setIconByKeyName("keydisk");
    }
    
	//Function to generate a list of possibles moves able to choose by player after collecting this key
    public List<Point> generatePointList(){
		//Can move up to 3 squares horizontally or vertically, but cannot move diagonally.
        List<Point> pointList = new ArrayList<>();
        int pointCount = 0;
        int px = (int)getCoordinate().getX();
        int py = (int)getCoordinate().getY();

        for (int i=px-3;i<=px+3;i++){
            for(int j=py-3;j<=py+3;j++){
                if((i>=0) && (i<9) && (j>=0) && (j<9) && ((i!=px)||(j!=py))){
                    if ((i==px)||(j==py)){
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
