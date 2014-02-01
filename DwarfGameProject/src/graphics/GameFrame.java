package graphics;

import items.Inventory;
import entities.Entity;
import gameBoard.GameMap;

import javax.swing.JFrame;

import Utilities.GameValues;
import ui.InventoryPanel;

public class GameFrame extends JFrame{


	/**
	 * 
	 */
	private static final long serialVersionUID = 7580804885571417228L;

	private WorldWindow theWorldsWindow;
	
	private InventoryPanel inventoryPanel;
	
	private Entity player;
	
	public int cellWidth = 40;
	
	public int cellHeight = 40;
	
	
	public GameFrame(){
		GameValues.GAME_TILE_WIDTH = this.cellWidth;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
//		this.setLayout(new BoxLayout(null, BoxLayout.Y_AXIS));
		this.setInventoryToDisplay(new Inventory());
	}
	
	public void setInventoryToDisplay(Inventory i){
		this.inventoryPanel = new InventoryPanel(i);
		if(theWorldsWindow != null){
			this.add(inventoryPanel);
		}
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
		
		if(inventoryPanel != null){
			this.add(inventoryPanel);
		}
		
		this.add(theWorldsWindow);
		
		if(player != null){
			theWorldsWindow.setPerspective(player);
		}
	}
	
	public void toggleinventory(){
		if(inventoryPanel != null){
			inventoryPanel.setVisible(!inventoryPanel.isVisible());
		}
	}

	public void toggleGrid() {
		theWorldsWindow.setShowGrid(!theWorldsWindow.getShowGrid());
	}
}
