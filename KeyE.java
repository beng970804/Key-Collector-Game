/**
File name: KeyE.java
This is the class of Key for monkey aka Key E.
 */
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

//Class KeyE inherits from Key 
public class KeyE extends Key {
	//constructor
	//parameter: coordinate
    public KeyE(Point coordinate) {
        super("monkey", coordinate);
        setIconByKeyName("monkey");
    }
    
	//Function to generate a list of possibles moves able to choose by player after collecting this key
    public List<Point> generatePointList(){
		//Can move up to 3 squares in any direction.
        List<Point> pointList = new ArrayList<>();
        int pointCount = 0;
        int px = (int)getCoordinate().getX();
        int py = (int)getCoordinate().getY();

        for (int i=px-3;i<=px+3;i++){
            for(int j=py-3;j<=py+3;j++){
                if((i>=0)&&(i<9)&&(j>=0)&&(j<9)){
                    double abs_x = (Math.abs(i-px)==0? 100 : (double)Math.abs(i-px));
                    if((((double)Math.abs(j-py)/abs_x)==1.0) && i!=px && j!=py){
                        pointList.add(pointCount, new Point(i,j));
                        pointCount+=1;
                    } 
                    if((i==px)^(j==py)){
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
