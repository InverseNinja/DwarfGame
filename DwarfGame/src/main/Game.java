package main;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

import entities.Dwarf;
import entities.Entity;
import entities.Gold;
import gameBoard.GameMap;

public class Game {

	private boolean gameInProgress;

	public static final long TICK_RATE = 200;

	private static int GAME_TIME_IN_TICKS = 0;
	
	private GameMap gmap;
	
	private Entity player;
	
	private JFrame jpnl;
	
	private JLabel jtf;
	

	public static void main(String[] args){
		Game game = new Game();
		game.startGame();
	}

	/**
	 * 
	 */
	public void startGame(){
		gameInProgress = true;
		
		KeyboardManager kbm = new KeyboardManager();
		

		jpnl = new JFrame();
		jpnl.addKeyListener(kbm);
		jpnl.setVisible(true);
		
		jtf = new JLabel();
		jpnl.add(jtf);
		
		player = new Dwarf();
		gmap = new GameMap(10, 10);
		gmap.addEntity(player);
		gmap.addEntity(5,5,new Gold());
		
		long currentTime;

		int lastTick = GAME_TIME_IN_TICKS;
		long startTime = System.currentTimeMillis();
		
		while(gameInProgress == true){
			currentTime = System.currentTimeMillis();

			//take action
//			System.out.println("These keys are being pressed: "+kbm.getPressedKeys().toString()+" at tick number: "+GAME_TIME_IN_TICKS);
			
			//update objects
			try {
				this.handleKeysPressed(kbm.getPressedKeys());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			//render objects
			this.renderMap(gmap);
			//check game state

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
				break;
			case 68: //right
				gmap.moveEntity(player, player.getXCoordinate()+1, player.getYCoordinate());
				break;
			case 83: //down
				gmap.moveEntity(player, player.getXCoordinate(), player.getYCoordinate()+1);
				break;
			case 87: //up
				gmap.moveEntity(player, player.getXCoordinate(), player.getYCoordinate()-1);
				break;
			}
		}
	}
	
	private void renderMap(GameMap g){
		
		String renderString = "<html><font size=\"20\">";
		
		for(int height = 0; height < g.getHeight();height++){
			for(int width = 0; width < g.getWidth();width++){
				Entity e = g.getEntityAtLocation(width, height);
				if(e == null){
					renderString += "&#9617";
				}else{
					if(e instanceof Dwarf)
						renderString += "웃";

					if(e instanceof Gold)
						renderString += "&#8773";
				}
			}

			renderString += "<br>";
		}
		
		renderString += "</font></html>";
		jtf.setText(renderString);
		
	}
}
