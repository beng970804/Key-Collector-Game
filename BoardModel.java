/**
File name: BoardModel.java.
This is part of MVC implementation of the program. BoardModel consists of the components of the program, including key, player and chest.
It contains function to initialise the value of the components. 
 */
import java.awt.Component;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

//Class BoardModel
class BoardModel {
	//board components
	private Point[] randKeyCoordinate;
	private int[] keySequence = new int[5];
    private List<Player> playerList = new ArrayList<>(4);
    private KeyA pinkey;
    private KeyB donkey;
    private KeyC keydisk;
    private KeyD keynote;
    private KeyE monkey;
    private Chest chest;
	private String winner;
    private boolean chestUnlock = false;
    private boolean playerMove = false;
    private State saveState = new State();

	//constructor
    public BoardModel() {
    }

	//function to initialise board model
	//parameter: randomised key coordinate, randomised key sequence
    public void initialiseModel(Point[] randKeyCoordinate, int[] keySequence){
        this.randKeyCoordinate = randKeyCoordinate;
        this.keySequence = keySequence;
        
        playerList.add(new Player(1,"bangei", new Point(0,0)));
        playerList.add(new Player(2,"arkimides", new Point(0,8)));
        playerList.add(new Player(3,"canser", new Point(8,0)));
        playerList.add(new Player(4,"dozciztem", new Point(8,8)));
        
        for (int i=0;i<5;i++){
            switch(keySequence[i]){
                case 0:
                    pinkey = new KeyA(randKeyCoordinate[i]);
                    break;
                case 1:
                    donkey = new KeyB(randKeyCoordinate[i]);
                    break;
                case 2:
                    keydisk = new KeyC(randKeyCoordinate[i]);
                    break;
                case 3:
                    keynote = new KeyD(randKeyCoordinate[i]);
                    break;
                case 4:
                    monkey = new KeyE(randKeyCoordinate[i]);
                    break;
                default :
                    break;
            }
        }
        chest = new Chest();
        saveState.initialiseState(playerList,randKeyCoordinate,keySequence);
		
		//return nothing
    }
    
	//function to update state 
	//parameter: latest turn
    public void updateModel(int currentTurn){
        saveState.updateState(playerList,currentTurn);
		
		//return nothing
    }
    
	//function to update value of playerMove
	//parameter: new boolean value if move
    public void updateMove(boolean newMove){
        playerMove = newMove;
		
		//return nothing
    }
    
	//function to check if player is moved
    public boolean isMoved(){
        return playerMove;
    }
	//return player move
    
	//function to update winner
	//parameter: name of winner
    public void updateWinner(String winner){
        chestUnlock = true;
        this.winner = winner;
		
		//return nothing
    }

    //fucntion to update player coordinate
	//parameter: player index, new coordinate
    public void updatePlayerCoordinate(int index, Point newCoordinate) {
        playerList.get(index).setCoordinate(newCoordinate);
		
		//return nothing
    }

	//functon update key that player collected
	//parameter: player index, key name
    public void updatePlayerKey(int index, String keyName) {
        playerList.get(index).collectKey(keyName);
		
		//return nothing
    }
    
	//functon to get key by index
	//parameter: index
    public Key getKey(int index) {
        Key newKey = new Key("");
        switch (index) {
            case 0:
                newKey = pinkey;
                break;
            case 1:
                newKey = donkey;
                break;
            case 2:
                newKey = keydisk;
                break;
            case 3:
                newKey = keynote;
                break;
            case 4:
                newKey = monkey;
                break;
            default:
                break;
        }
        
        return newKey;
    }
	//return new key

    //function to load state
    public int loadState(){
        saveState.load();
        keySequence = saveState.getKeySequence();
        playerList = saveState.getPlayerList();
        int thisTurn = saveState.getCurrentTurn();
		playerMove = true;
        return thisTurn;
    }
	//return the turn of loaded state
	
	//getter
    public List<Player> getPlayerList() {
        return playerList;
    }

    public boolean isChestUnlocked() {
        return chestUnlock;
    }

    public String getWinner() {
        return winner;
    }
    
    public Player getPlayerAt(int index){
        return playerList.get(index);
    }
	
	public Chest getChest() {
        return chest;
    }

    public int[] getKeySequence() {
        return saveState.getKeySequence();
    }

    public Point[] getKeyCoordinate() {
        return saveState.getKeyCoordinate();
    }
    
    public State getState(){
        return saveState;
    }
}
