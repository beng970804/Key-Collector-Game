/**
File name: BoardController.java.
This is part of MVC implementation of the program. BoardController controls interaction between UI and components
It contains function to move the player turn and collect key 
 */
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.*;
import java.io.*;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

//Class BoardController implement ActionListener interface
class BoardController implements ActionListener{
	//controller attrubutes
    private BoardView theView;
    private BoardModel theModel;
    private Point newCoordinate;
    private int myTurn = ThreadLocalRandom.current().nextInt(1, 5);
	
	//constructor
	//parameter: board view and board model
    BoardController(BoardView theView, BoardModel theModel) {
        this.theView = theView;
        this.theModel = theModel;
    }
    
	//function initialise the conroller/random number
    public void initialiseController(){
        int[] keySeed = new int[5];
        int[] keySequence = new int[5];
        
        for(int i=0; i<5; i++){
            keySeed[i] = ThreadLocalRandom.current().nextInt(0,9);
            if(i==2){
                keySeed[i] = ThreadLocalRandom.current().nextInt(0,49);
				//check for the value needed
                if((keySeed[i]!=3) && (keySeed[i]!=10) && (keySeed[i]!=17) && (keySeed[i]<21 || keySeed[i]>27) && (keySeed[i]!=31) && (keySeed[i]!=38) && (keySeed[i]!=45) || (keySeed[i]==24)){
                    i--;
                }
            }
        }
		for(int i=0; i<5; i++){
			keySequence[i] = ThreadLocalRandom.current().nextInt(0,5);
			for(int h=0; h<i; h++){
				if(keySequence[h]==keySequence[i]){
					i--;
				}
			}
        }
		
        Point[] randKeyCoordinate = new Point[5];
        randKeyCoordinate[0] = new Point(((keySeed[0] / 3) + 1),((keySeed[0] % 3) + 1));
        randKeyCoordinate[1] = new Point(((keySeed[1] / 3) + 1),((keySeed[1] % 3) + 5));
        randKeyCoordinate[2] = new Point(((keySeed[2] / 7) + 1),((keySeed[2] % 7) + 1));
        randKeyCoordinate[3] = new Point(((keySeed[3] / 3) + 5),((keySeed[3] % 3) + 1));
        randKeyCoordinate[4] = new Point(((keySeed[4] / 3) + 5),((keySeed[4] % 3) + 5));

        clearPlayerData();
        theModel.initialiseModel(randKeyCoordinate, keySequence);
        printBoard();
        theModel.updateMove(true);
		
		//return nothing
    }
    
	//function to print board
    public void printBoard(){
        theView.removeAll();
        Point[] randKeyCoordinate = theModel.getKeyCoordinate();
        int[] keySequence = theModel.getKeySequence();
        GameButton[][] gb = theView.getButton();
        
        for (int i=0;i<9;i++){
            for (int j=0;j<9;j++){
                if((i==j)&&(i==4)){
                    theModel.getChest().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                        }
                    });
                    theView.add(theModel.getChest());
                    gb[i][j] = new GameButton();
                }else{
                    Point currentCoordinate = new Point(i,j);
                    boolean isKey = false;
                    for(int k=0;k<4;k++){
                        if(currentCoordinate.equals(theModel.getPlayerAt(k).getCoordinate())){
                            isKey = true;
                            theModel.getPlayerAt(k).addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                }
                            });
                            theView.add(theModel.getPlayerAt(k));
                        }
                    }
                    for(int k=0;k<5;k++){
                        if ((currentCoordinate.equals(randKeyCoordinate[k]))&&!isKey){
                            isKey = true;
                            theModel.getKey(keySequence[k]).setCoordinate(currentCoordinate);
                            theModel.getKey(keySequence[k]).addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                }
                            });
                            theView.add(theModel.getKey(keySequence[k]));
                            break;
                        }
                    }
                    gb[i][j] = new GameButton();
                    gb[i][j].setEnabled(false);
                    if(!isKey){
                        gb[i][j].addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent event) {
                            }
                        });
                        theView.add(gb[i][j]);
                    }
                }
            }
        }
        theView.updateView();
		//return nothing
    }
    
	//function to move 
    public void move(GameFrame gf, int aTurn){
		if (aTurn==99){
			start(myTurn, gf);
		}
		else{
			start(aTurn, gf);
		}
		//return nothing
	}
	
	//fucntion to start moving, if the chest is unlock, then will end game. else will repeat until a winner is found
	public void start(int currentTurn, GameFrame gf){
		if(!isChestUnlocked()){
		
			theModel.updateMove(false);
			GameButton[][] gb = theView.getButton();
			
			for (int i=0;i<5;i++){
				theModel.getKey(i).deactivate();
				theModel.getKey(i).clearActionListener();
			}
			theModel.getChest().clearActionListener();
			
			theModel.getPlayerAt(currentTurn - 1).setEnabled(true);
			theModel.getPlayerAt((currentTurn) % 4).setEnabled(false);
			theModel.getPlayerAt((currentTurn+1) % 4).setEnabled(false);
			theModel.getPlayerAt((currentTurn+2) % 4).setEnabled(false);
			
			List<Point> pointList = theModel.getPlayerAt(currentTurn - 1).generatePointList();
			
			for (int i=0;i<pointList.size();i++){
				Point currentCoordinate = new Point((int)pointList.get(i).getX(),(int)pointList.get(i).getY());
				if (currentCoordinate.equals(theModel.getKey(0).getCoordinate())){
					theModel.getKey(0).activate();
					theModel.getKey(0).addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							String command = e.getActionCommand();
							if (command.equals("pinkey")){
								newCoordinate = new Point((int)currentCoordinate.getX(),(int)currentCoordinate.getY());
								theModel.updatePlayerCoordinate(currentTurn -1, newCoordinate);
								theModel.updatePlayerKey(currentTurn-1, command);
								theModel.updateMove(true);
								theModel.updateModel(currentTurn);
								printBoard();
								if(theModel.isMoved()){
									int nextTurn = nextTurn();
									start(nextTurn, gf);
								}
							}
							
						}
					});
				}
				else if (currentCoordinate.equals(theModel.getKey(1).getCoordinate())){
					theModel.getKey(1).activate();
					theModel.getKey(1).addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							String command = e.getActionCommand();
							if (command.equals("donkey")){
								newCoordinate = new Point((int)currentCoordinate.getX(),(int)currentCoordinate.getY());
								theModel.updatePlayerCoordinate(currentTurn -1, newCoordinate);
								theModel.updatePlayerKey(currentTurn-1, command);
								theModel.updateMove(true);
								theModel.updateModel(currentTurn);
								printBoard();
								if(theModel.isMoved()){
									int nextTurn = nextTurn();
									start(nextTurn, gf);
								}
							}
						}
					});
				}
				else if (currentCoordinate.equals(theModel.getKey(2).getCoordinate())){
					theModel.getKey(2).activate();
					theModel.getKey(2).addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							String command = e.getActionCommand();
							if(command.equals("keydisk")){
								newCoordinate = new Point((int)currentCoordinate.getX(),(int)currentCoordinate.getY());
								theModel.updatePlayerCoordinate(currentTurn -1, newCoordinate);
								theModel.updatePlayerKey(currentTurn-1, command);
								theModel.updateMove(true);
								theModel.updateModel(currentTurn);
								printBoard();
								if(theModel.isMoved()){
									int nextTurn = nextTurn();
									start(nextTurn, gf);
								}
							}
						}
					});
				}
				else if (currentCoordinate.equals(theModel.getKey(3).getCoordinate())){
					theModel.getKey(3).activate();
					theModel.getKey(3).addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							String command = e.getActionCommand();
							if(command.equals("keynote")){
								newCoordinate = new Point((int)currentCoordinate.getX(),(int)currentCoordinate.getY());
								theModel.updatePlayerCoordinate(currentTurn -1, newCoordinate);
								theModel.updatePlayerKey(currentTurn-1, command);
								theModel.updateMove(true);
								theModel.updateModel(currentTurn);
								printBoard();
								if(theModel.isMoved()){
									int nextTurn = nextTurn();
									start(nextTurn, gf);
								}
							}
						}
					});
				}
				else if (currentCoordinate.equals(theModel.getKey(4).getCoordinate())){
					theModel.getKey(4).activate();
					theModel.getKey(4).addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							String command = e.getActionCommand();
							if(command.equals("monkey")){
								newCoordinate = new Point((int)currentCoordinate.getX(),(int)currentCoordinate.getY());
								theModel.updatePlayerCoordinate(currentTurn -1, newCoordinate);
								theModel.updatePlayerKey(currentTurn-1, command);
								theModel.updateMove(true);
								theModel.updateModel(currentTurn);
								printBoard();
								if(theModel.isMoved()){
									int nextTurn = nextTurn();
									start(nextTurn, gf);
								}
							}
						}
					});
				}
				else if (currentCoordinate.equals(theModel.getChest().getCoordinate())){
					theModel.getChest().setBorder(new LineBorder(Color.GREEN));
					theModel.getChest().addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							String command = e.getActionCommand();
							if(command.equals("Chest")){
								if(theModel.getPlayerAt(currentTurn-1).getKeyCount()==5){
									newCoordinate = new Point((int)currentCoordinate.getX(),(int)currentCoordinate.getY());
									theModel.updatePlayerCoordinate(currentTurn -1, newCoordinate);
									theModel.updateWinner(theModel.getPlayerAt(currentTurn - 1).getButtonName());
									theModel.updateMove(true);
									theModel.updateModel(currentTurn);
									printBoard();
									if(theModel.isMoved()){
										int nextTurn = nextTurn();
										start(nextTurn, gf);
									}
								}else{
									JOptionPane.showMessageDialog(null, "You have to collect 5 keys first to unlock.");
								}
							}
						}
					});
				}
				gb[(int)currentCoordinate.getX()][(int)currentCoordinate.getY()].activate();
				gb[(int)currentCoordinate.getX()][(int)currentCoordinate.getY()].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String command = e.getActionCommand();
						newCoordinate = new Point((int)currentCoordinate.getX(),(int)currentCoordinate.getY());
						theModel.updatePlayerCoordinate(currentTurn -1, newCoordinate);
						theModel.updateMove(true);
						theModel.updateModel(currentTurn);
						printBoard();
						if(theModel.isMoved()){
							int nextTurn = nextTurn();
							start(nextTurn, gf);
						}
					}
				});
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Congratulations to " + getWinner() + " for winning");
			gf.endGame();
		}
		//return nothing
	}
	
	//function to generate next turn index
	public int nextTurn(){
        myTurn = (myTurn%4 + 1);
        return myTurn;
    }
	//retuen: next turn

	//funciton to empty player list
    public void clearPlayerData(){
        theModel.getPlayerList().clear();
    }
    
	//function to get chest status from model
    public boolean isChestUnlocked(){
        return theModel.isChestUnlocked();
    }
    
	//fucntion to get winner 
    public String getWinner(){
        return theModel.getWinner();
    }

	//overidde superclass function
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
    
}
