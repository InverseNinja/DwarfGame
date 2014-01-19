package graphics;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import entities.Dwarf;
import entities.Entity;
import entities.Gold;
import gameBoard.GameMap;

public class WorldWindow extends JPanel{

	private int cellWidth = 80;

	private int cellHeight = 80;
	
	/**
	 * This method will draw the given gameMap
	 * @param gmap
	 */
	public void drawWorld(GameMap gmap){
		//draw stuff
		Graphics graphicsObject = this.getGraphics();//grab the graphics to draw with
		
		this.update(graphicsObject);
		
		//Now we want to loop through each cell in he map grid and draw it.
		for(int y = 0; y < gmap.getHeight(); y++ ){//well start with each row
			for(int x = 0; x < gmap.getWidth(); x++){//and then go through each x coordinate
				
				//draw the cell
				graphicsObject.setColor(Color.BLUE);
				graphicsObject.drawRect(x*cellWidth, y*cellWidth, cellWidth, cellHeight);
				

				Entity entityBeingDrawn = gmap.getEntityAtLocation(x, y);//this is the entity we are going to draw.
				
				if(entityBeingDrawn != null){
					
					if(entityBeingDrawn instanceof Dwarf){//if its a dwarf draw a dwarf here
						EntityDrawer.drawDwarf(graphicsObject, x*cellWidth, y*cellHeight, cellWidth, cellHeight);
					}else if(entityBeingDrawn instanceof Gold){//if its gold then draw some gold here
						EntityDrawer.drawGold(graphicsObject, x*cellWidth, y*cellHeight, cellWidth, cellHeight);
					}
				}
				
			}
		}
	}
}
