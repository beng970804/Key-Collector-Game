/**
File name: KeyD.java
This is the class of Key for keynote aka Key D.
 */
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

//Class KeyD inherits from Key 
public class KeyD extends Key {
	//constructor
	//parameter: coordinate
    public KeyD(Point coordinate) {
        super("keynote", coordinate);
        setIconByKeyName("keynote");
    }
    
	//Function to generate a list of possibles moves able to choose by player after collecting this key
    public List<Point> generatePointList(){
		//Must move 2 squares in any direction (i.e. skip 1 square.)
        List<Point> pointList = new ArrayList<>();
        int pointCount = 0;
        int px = (int)getCoordinate().getX();
        int py = (int)getCoordinate().getY();

        for (int i=px-3;i<=px+3;i++){
            for(int j=py-3;j<=py+3;j++){
                if((i>=0) && (i<9) && (j>=0) && (j<9) && ((i!=px)||(j!=py)) && ((Math.abs(i-px)==2 || i-px==0) && (Math.abs(j-py)==2 || j-py==0))){
                    pointList.add(pointCount, new Point(i,j));
                    pointCount+=1;
                }
            }
        }
        return pointList;
    }
	//return: a list of available keys
}
