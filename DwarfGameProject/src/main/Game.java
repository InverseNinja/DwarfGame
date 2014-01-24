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

	public static final long TICK_RATE = 20;

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
			List<Integer> otherActionsTaken = kbm.getOtherPressedKeys();
			List<Integer> movementActionsTaken = kbm.getPressedMovementKeys();

			//update objects
			try {
				this.handleKeysPressed(otherActionsTaken, movementActionsTaken);
				for(Entity e: gmap.getContainedEntities()){
					e.tick();
				}
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

	private void handleKeysPressed(List<Integer> otherKeys, List<Integer> movementKeys) throws Exception{
//		System.out.println(otherKeys.size()+", "+movementKeys.size());
		if(movementKeys.size()>0 && player.getMovementVectorLength() ==0){
			int xGridOffset = 0;
			int yGridOffset = 0;
			switch(movementKeys.size()){
			case 1: 
				switch(movementKeys.get(0)){
				case 65: //left
					xGridOffset = -1;
					player.setFacingDirection(4);
					break;
				case 68: //right
					xGridOffset = 1;
					player.setFacingDirection(6);
					break;
				case 83: //down
					yGridOffset = 1;
					player.setFacingDirection(2);
					break;
				case 87: //up
					yGridOffset = -1;
					player.setFacingDirection(8);
					break;
				}
				break;
			case 2: 
				int sum = movementKeys.get(0) + movementKeys.get(1);
				switch(sum){
				case (87+65)://NW
					xGridOffset = -1;
				yGridOffset = -1;
				player.setFacingDirection(7);
				break;
				case (87+68): //NE
					xGridOffset = 1;
				yGridOffset = -1;
				player.setFacingDirection(9);
				break;
				case (83+65): //SW
					xGridOffset = -1;
				yGridOffset = 1;
				player.setFacingDirection(1);
				break;
				case (83+68): //SE
					xGridOffset = 1;
				yGridOffset = 1;
				player.setFacingDirection(3);
				break;
				}
				break;
			case 3: //down
				int sum2 = movementKeys.get(0)+movementKeys.get(1)+movementKeys.get(2);
				switch(sum2){
				case 235: //left
					xGridOffset = -1;
					player.setFacingDirection(4);
					break;
				case 238: //right
					xGridOffset = 1;
					player.setFacingDirection(6);
					break;
				case 216: //down
					yGridOffset = 1;
					player.setFacingDirection(2);
					break;
				case 220: //up
					yGridOffset = -1;
					player.setFacingDirection(8);
					break;
				}
				break;

			}
			gmap.moveEntity(player, player.getXTileCoordinate()+xGridOffset, player.getYTileCoordinate()+yGridOffset);
			player.setMovementVectorLength(gameUI.cellHeight);
		}

		for(Integer keyCode : otherKeys){
			switch(keyCode){
			case 61: //tab
				gameUI.toggleGrid();
				break;
			}
		}
	}

}
