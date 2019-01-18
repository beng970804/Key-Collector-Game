/**
File name: State.java
This is the current board status of the game, showing player and the keys they collected. 
This class allow user to save and load game state. 
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

//Class State inherits from JPanel
public class State extends JPanel{
	//State attributes
    private List<Player> playerList = new ArrayList<>(4);
    private Point[] keyCoordinate = new Point[5];
    private int[] keySequence = new int[5];
    private ImageIcon[] playerIcon = {new ImageIcon("bangei.gif"), 
        new ImageIcon("arkimides.gif"), new ImageIcon("canser.gif"), 
        new ImageIcon("dozciztem.gif")};
    private JLabel[] keyIcon = new JLabel[24];
    private int currentTurn;
    
	//constructor
    public State(){
        setPreferredSize(new Dimension(500,500));
        setLayout(new GridLayout(4,6));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    
	//function to initialise current game state
	//parameter: player list, randomised coordinate of keys, randomised sequence of key
    public void initialiseState(List<Player> playerList, Point[] randKeyCoordinate, int[] keySequence){
        removeAll();
        this.playerList = playerList;
        keyCoordinate = randKeyCoordinate;
        this.keySequence = keySequence;
        for (int i=0;i<4;i++){
            JLabel tempPlayer = new JLabel();
            ImageIcon tempPlayerIcon = playerIcon[i];
            tempPlayerIcon=resizeIcon(tempPlayerIcon);
            tempPlayer.setIcon(tempPlayerIcon);
            tempPlayer.setPreferredSize(new Dimension(30,30));
            add(tempPlayer);
            for (int j=0;j<5; j++){
                keyIcon[j+(i*6)] = new JLabel();
                ImageIcon temp = new ImageIcon();
                temp = new ImageIcon("nokey.png");
                temp = resizeIcon(temp);
                keyIcon[j+(i*6)].setIcon(temp);
                keyIcon[j+(i*6)].setPreferredSize(new Dimension(30,30));
                add(keyIcon[j+(i*6)]);
            }
        }
		
		//return nothing
    }
    
	//function update the latest state
	//parameter: player list and latets player turn
    public void updateState(List<Player> playerList, int currentTurn){
        removeAll();
        this.playerList = playerList;
        this.currentTurn = currentTurn;
        for (int i=0;i<4;i++){
            JLabel tempPlayer = new JLabel();
            ImageIcon tempPlayerIcon = playerIcon[i];
            tempPlayerIcon = resizeIcon(tempPlayerIcon);
            tempPlayer.setIcon(tempPlayerIcon);
            tempPlayer.setPreferredSize(new Dimension(30,30));
            add(tempPlayer);
            List<Key> keyList = playerList.get(i).getKeyList();
            for (int j=0;j<5; j++){
                keyIcon[j+(i*6)] = new JLabel();
                ImageIcon temp = new ImageIcon();
                temp = new ImageIcon("nokey.png");
                //if (j<keyList.size()){
                    switch(keyList.get(j).getButtonName()){
                        case "pinkey":
                            temp = new ImageIcon("pinkey.gif");
                            break;
                        case "donkey":
                            temp = new ImageIcon("donkey.gif");
                            break;
                        case "keydisk":
                            temp = new ImageIcon("keydisk.gif");
                            break;
                        case "keynote":
                            temp = new ImageIcon("keynote.gif");
                            break;
                        case "monkey":
                            temp = new ImageIcon("monkey.gif");
                            break;
                        default:
                            
                            break;
                    }
                //}
                temp = resizeIcon(temp);
                keyIcon[j+(i*6)].setIcon(temp);
                keyIcon[j+(i*6)].setPreferredSize(new Dimension(30,30));
                add(keyIcon[j+(i*6)]);
            }
        }
        repaint();
		//return nothing
    }
	
    //function to save current state
    public boolean save(){
        boolean isSuccess = false;
        try{
            PrintWriter myWriter = new PrintWriter("state.txt");
            myWriter.println(Integer.toString(currentTurn+1));
			for(int i=0; i<5; i++){
                myWriter.println((int)keyCoordinate[i].getX());
                myWriter.println((int)keyCoordinate[i].getY());
            }
            for(int i=0; i<5; i++){
                myWriter.println(keySequence[i]);
            }
            for(int i=0; i<4; i++){
                Player player = playerList.get(i);
                myWriter.println((int)player.getCoordinate().getX());
                myWriter.println((int)player.getCoordinate().getY());
                List<Key> keyList = playerList.get(i).getKeyList();
                myWriter.println(player.getKeyCount());
				if(player.getKeyCount()>0){
					for(int j=0; j<player.getKeyCount(); j++){
						myWriter.println(keyList.get(j).getButtonName());
					}
				}  
            }
            myWriter.close();
            isSuccess = true;
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
        return isSuccess;
    }
	//return: true if the game is saved successfully
    
	//function to load 
    public boolean load(){
        boolean isSuccess = false;
        try{
            BufferedReader myReader = new BufferedReader(new FileReader("state.txt"));
            currentTurn = Integer.parseInt(myReader.readLine());
			for(int i=0; i<5; i++){
                int x = Integer.parseInt(myReader.readLine());
                int y = Integer.parseInt(myReader.readLine());
                keyCoordinate[i] = new Point(x,y);
            }
            for(int i=0; i<5; i++){
                keySequence[i] = Integer.parseInt(myReader.readLine());
            }
            for(int i=0; i<4; i++){
                int x = Integer.parseInt(myReader.readLine());
                int y = Integer.parseInt(myReader.readLine());
                Point point = new Point(x,y);
                String btnName = getButtonNameByIndex(i+1);
                Player player = new Player(i+1,btnName,point);
                int keyCount = Integer.parseInt(myReader.readLine());
                for(int j=0; j<keyCount; j++){
                    String keyName = myReader.readLine();
                    player.collectKey(keyName);
                }
                playerList.set(i, player);
            }
			isSuccess=true;
			myReader.close();
			updateState(playerList, currentTurn);
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
        return isSuccess;
    }
	//return true if game is loaded successfully
    
	//function to resize icons on state panel
	//parameter: any icon
    public ImageIcon resizeIcon(ImageIcon icon){
        Image img = icon.getImage();
        ImageIcon resizedIcon = new ImageIcon();
        if (img !=null){
            Image newimg = img.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH );
            resizedIcon = new ImageIcon(newimg);
        }
        else{    
        }
        return resizedIcon;
    }
	//return: a resize version of icon passed
    
	//function to retrieve key name by index
	//parameter: index
    public String getButtonNameByIndex(int index){
        String buttonName="";
        switch(index){      
            case 1:
                buttonName =  "bangei";
                break;
            case 2:
                buttonName = "arkimides";
                break;
            case 3:
                buttonName="canser";
                break;
            case 4:
                buttonName = "dozciztem";
                break;
            default:
                break;
        }
        return buttonName;
    }
	//return: key name
    
	//getters and setters
    public List<Player> getPlayerList() {
        return playerList;
    }

    public Point[] getKeyCoordinate() {
        return keyCoordinate;
    }

    public int[] getKeySequence() {
        return keySequence;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }
}
