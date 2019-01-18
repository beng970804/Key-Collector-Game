/**
File Name : CMLv2.javax
**To compile: Open Readme.txt in the same folder
		To compile this program ->
		1. open cmd
		2. cd to folder the .java files are stored
		3. javac *.java
		4. echo Main-Class: CMLv2 >manifest.txt
		5. jar cvfm CMLv2.jar manifest.txt *.class

		To run this program ->
		1. java -jar CMLv2.jar OR
		open the CMLv2.jar file at the same folder
*/
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class CMLv2 {

    /**
     * @param args the command line arguments
     */
	 
	 //This is the main function
	 
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
         
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameMenu myGame = new GameMenu();
            }
        });
    }
    
}
