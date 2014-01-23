package graphics;

import entities.Entity;
import gameBoard.GameMap;

import javax.swing.JFrame;

public class GameFrame extends JFrame{


	/**
	 * 
	 */
	private static final long serialVersionUID = 7580804885571417228L;

	private WorldWindow theWorldsWindow;
	
	private Entity player;
	
	public int cellWidth = 80;
	
	public int cellHeight = 80;
	
	public GameFrame(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800, 600);
	}

	public void setFocusedPlayer(Entity player){
		this.player = player;
		if(theWorldsWindow != null){
			theWorldsWindow.setPerspective(player);
		}
	}
	
	public void setWorld(GameMap gmap){
		if(theWorldsWindow != null){
			this.remove(theWorldsWindow);
		}
		theWorldsWindow = new WorldWindow(gmap, cellWidth, cellHeight);
		this.add(theWorldsWindow);
		
		if(player != null){
			theWorldsWindow.setPerspective(player);
		}
	}

	public void toggleGrid() {
		theWorldsWindow.setShowGrid(!theWorldsWindow.getShowGrid());
	}
}
