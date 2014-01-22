package main;

import java.util.List;

import javax.swing.SwingUtilities;

import entities.Dwarf;
import entities.Entity;
import entities.Gold;
import gameBoard.GameMap;
import graphics.GameFrame;

public class Game {

	private boolean gameInProgress;

	public static final long TICK_RATE = 200;

	private static int GAME_TIME_IN_TICKS = 0;

	private GameMap gmap;

	private Entity player;

	private GameFrame gameUI;

	private KeyboardManager kbm;


	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				final Game game = new Game();
				game.loadinitialGameState();
				Thread t = new Thread(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						game.startGame();
					}
					
				});
				t.start();
			}
			
		});
	}

	public Game(){
		kbm = new KeyboardManager();
		gameUI = new GameFrame();
		gameUI.addKeyListener(kbm);
		gameUI.setVisible(true);
	}

	/**
	 * This method will load the game into its needed initial game state. Eventually this is where well be able to load the game into a certain level from a 
	 * Game file.
	 */
	public void loadinitialGameState(){
		//Make The Player
		player = new Dwarf();

		//Add the player and entites to the map
		gmap = new GameMap(50, 50);
		gmap.addEntity(player);
		gmap.addEntity(5,5,new Gold());
		
		gameUI.setFocusedPlayer(player);
		gameUI.setWorld(gmap);
	}
	/**
	 * Starts the game
	 */
	public void startGame(){
		gameInProgress = true;

		long currentTime;

		int lastTick = GAME_TIME_IN_TICKS;
		long startTime = System.currentTimeMillis();

		while(gameInProgress == true){
			currentTime = System.currentTimeMillis();

			//take action
			List<Integer> actionsTaken = kbm.getPressedKeys();

			//update objects
			try {
				this.handleKeysPressed(actionsTaken);
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			//render objects
//			gameUI.paintComponents(gameUI.getGraphics());
//			gameUI.repaint();
			SwingUtilities.invokeLater(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					gameUI.repaint();
				}
				
			});
			
			
			//check game state
			//TODO: check for victory conditions and end game if needed



			//keep the game cycle consistent
			GAME_TIME_IN_TICKS++;
			if((System.currentTimeMillis()-currentTime) < TICK_RATE){
				try {
					Thread.sleep(TICK_RATE - (System.currentTimeMillis()-currentTime));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			//Print performance stats.
			long cycleTime = System.currentTimeMillis()-startTime;
			if(cycleTime > 1000){
//				System.out.println(GAME_TIME_IN_TICKS-lastTick+" ticks per seccond with "+kbm.getEventsSinceLastCall()+" events with cycle time "+cycleTime+"ms.");
				lastTick = GAME_TIME_IN_TICKS;
				startTime = System.currentTimeMillis();
			}
		}
	}

	private void handleKeysPressed(List<Integer> pressedKeys) throws Exception{
		for(Integer keyCode : pressedKeys){
			switch(keyCode){
			case 65: //left
				gmap.moveEntity(player, player.getXCoordinate()-1, player.getYCoordinate());
				player.setFacingDirection(4);
				break;
			case 68: //right
				gmap.moveEntity(player, player.getXCoordinate()+1, player.getYCoordinate());
				player.setFacingDirection(6);
				break;
			case 83: //down
				gmap.moveEntity(player, player.getXCoordinate(), player.getYCoordinate()+1);
				player.setFacingDirection(2);
				break;
			case 87: //up
				gmap.moveEntity(player, player.getXCoordinate(), player.getYCoordinate()-1);
				player.setFacingDirection(8);
				break;
			}
		}
	}

}
