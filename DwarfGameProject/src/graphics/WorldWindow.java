package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Utilities.Distance;
import entities.Dwarf;
import entities.Entity;
import entities.Gold;
import gameBoard.GameMap;

public class WorldWindow extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8851154365224541643L;

	private int cellWidth = 80;

	private int cellHeight = 80;

	private GameMap gmap;

	private Entity focusedPlayer;

	private float MAX_RANGE = 7;

	private boolean drawGridOutline = true;
	
	
	public WorldWindow(GameMap gmap, int cellWidth, int cellHeight){
		this.gmap = gmap;
		this.setDoubleBuffered(true);
		this.cellHeight = cellHeight;
		this.cellWidth = cellWidth;
	}

	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;        
		super.paintComponent(g2d);
		drawWorld(g2d);
	}

	/**
	 * This method will draw the given gameMap
	 * @param gmap
	 */
	private void drawWorld(Graphics2D graphicsObject){
		//draw stuff

		//Now we want to loop through each cell in he map grid and draw it.
		for(int y = 0; y < gmap.getHeight(); y++ ){//well start with each row
			for(int x = 0; x < gmap.getWidth(); x++){//and then go through each x coordinate
				int range = Distance.range(focusedPlayer.getXTileCoordinate(), focusedPlayer.getYTileCoordinate(), x, y);
				if(range > MAX_RANGE){
					EntityDrawer.drawDarkTile(graphicsObject, x*cellWidth, y*cellHeight, cellWidth, cellHeight);
				}else{
					EntityDrawer.drawEmptyGround(graphicsObject, x*cellWidth, y*cellHeight, cellWidth, cellHeight);
				}
			}
		}

		for(Entity entityBeingDrawn: gmap.getContainedEntities()){
			int x = entityBeingDrawn.getXTileCoordinate();
			int y = entityBeingDrawn.getYTileCoordinate();
			entityBeingDrawn = gmap.getEntityAtLocation(x, y);//this is the entity we are going to draw.

			int range = Distance.range(focusedPlayer.getXTileCoordinate(), focusedPlayer.getYTileCoordinate(), x, y);
			if(range <= MAX_RANGE){
				EntityDrawer.drawEmptyGround(graphicsObject, x*cellWidth, y*cellHeight, cellWidth, cellHeight);
				if(entityBeingDrawn != null){
					double offsets[] = Distance.getPixleOffsets( entityBeingDrawn.getMovementVectorLength(),entityBeingDrawn.getFacingDirection());
					if(entityBeingDrawn instanceof Dwarf){//if its a dwarf draw a dwarf here
						EntityDrawer.drawDwarf(graphicsObject, (x*cellWidth)+(int)offsets[0], (y*cellHeight+(int)offsets[1])-cellHeight*(entityBeingDrawn.getWidthInTiles()-1), cellWidth*entityBeingDrawn.getWidthInTiles(), cellHeight*entityBeingDrawn.getWidthInTiles(), (Dwarf)entityBeingDrawn);
					}else if(entityBeingDrawn instanceof Gold){//if its gold then draw some gold here
						EntityDrawer.drawGold(graphicsObject, x*cellWidth, y*cellHeight, cellWidth, cellHeight);
					}

				}


			}
		}
		
		for(Animation a: gmap.animations){
			a.draw(graphicsObject, cellWidth, cellHeight);
		}
		
		for(int y = 0; y < gmap.getHeight(); y++ ){//well start with each row
			for(int x = 0; x < gmap.getWidth(); x++){//and then go through each x coordinate
				int range = Distance.range(focusedPlayer.getXTileCoordinate(), focusedPlayer.getYTileCoordinate(), x, y);
				if(range <= MAX_RANGE){
					EntityDrawer.drawFog(graphicsObject,x*cellWidth, y*cellHeight, cellWidth, cellHeight, 1 - range/MAX_RANGE);
				}

				if(drawGridOutline ==true){
					//draw the cell outline
					graphicsObject.setColor(Color.BLUE);
					graphicsObject.drawRect(x*cellWidth, y*cellWidth, cellWidth, cellHeight);
				}
			}
		}

	}

	public void setPerspective(Entity player) {
		this.focusedPlayer = player;
	}

	public boolean getShowGrid() {
		return this.drawGridOutline;
	}

	public void setShowGrid(boolean b) {
		this.drawGridOutline = b;
	}
}
