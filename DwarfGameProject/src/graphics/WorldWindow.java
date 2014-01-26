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

	private int cellWidth = 20;

	private int cellHeight = 20;

	private GameMap gmap;

	private Entity focusedPlayer;

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
		Color[][] lightMap = WorldWindow.getLightingMap(gmap);

		for(int y = 0; y < gmap.getHeight(); y++ ){//well start with each row
			for(int x = 0; x < gmap.getWidth(); x++){//and then go through each x coordinate

				Color c = lightMap[x][y];
				if(c != null){
					EntityDrawer.drawEmptyGround(graphicsObject, x*cellWidth, y*cellHeight, cellWidth, cellHeight);		
					EntityDrawer.drawFog(graphicsObject,x*cellWidth, y*cellHeight, cellWidth, cellHeight, c);
				}else{
					EntityDrawer.drawDarkTile(graphicsObject, x*cellWidth, y*cellHeight, cellWidth, cellHeight);
				}
				if(drawGridOutline ==true){
					//draw the cell outline
					graphicsObject.setColor(Color.BLUE);
					graphicsObject.drawRect(x*cellWidth, y*cellHeight, cellWidth, cellHeight);
				}
			}
		}

		for(Entity entityBeingDrawn: gmap.getContainedEntities()){
			int x = entityBeingDrawn.getXTileCoordinate();
			int y = entityBeingDrawn.getYTileCoordinate();
			entityBeingDrawn = gmap.getEntityAtLocation(x, y);//this is the entity we are going to draw.

			if(entityBeingDrawn != null){
				//draw the background around the entity
				for(int xunitWidth = entityBeingDrawn.getXTileCoordinate(); xunitWidth < entityBeingDrawn.getXTileCoordinate()+entityBeingDrawn.getWidthInTiles(); xunitWidth++){
					for(int yUnitWidth = entityBeingDrawn.getYTileCoordinate(); yUnitWidth > entityBeingDrawn.getYTileCoordinate()-entityBeingDrawn.getHeightInTiles(); yUnitWidth--){
						EntityDrawer.drawEmptyGround(graphicsObject, xunitWidth*cellWidth, yUnitWidth*cellHeight, cellWidth, cellHeight);
					}
				}
				
				//now draw the entity
				double offsets[] = Distance.getPixleOffsets( entityBeingDrawn.getMovementVectorLength(),entityBeingDrawn.getFacingDirection());
				if(entityBeingDrawn instanceof Dwarf){//if its a dwarf draw a dwarf here
					EntityDrawer.drawDwarf(graphicsObject, (x*cellWidth)+(int)offsets[0], (y*cellHeight+(int)offsets[1])-cellHeight*(entityBeingDrawn.getWidthInTiles()-1), cellWidth*entityBeingDrawn.getWidthInTiles(), cellHeight*entityBeingDrawn.getHeightInTiles(), (Dwarf)entityBeingDrawn);
				}else if(entityBeingDrawn instanceof Gold){//if its gold then draw some gold here
					EntityDrawer.drawGold(graphicsObject, x*cellWidth, y*cellHeight, cellWidth, cellHeight);
				}
				
				//now draw fog and a grid
				for(int xunitWidth = entityBeingDrawn.getXTileCoordinate(); xunitWidth < entityBeingDrawn.getXTileCoordinate()+entityBeingDrawn.getWidthInTiles(); xunitWidth++){
					for(int yUnitWidth = entityBeingDrawn.getYTileCoordinate(); yUnitWidth > entityBeingDrawn.getYTileCoordinate()-entityBeingDrawn.getHeightInTiles(); yUnitWidth--){
						EntityDrawer.drawFog(graphicsObject,xunitWidth*cellWidth, yUnitWidth*cellHeight, cellWidth, cellHeight, lightMap[x][y]);
						if(drawGridOutline ==true){
							//draw the cell outline
							graphicsObject.setColor(Color.BLUE);
							graphicsObject.drawRect(xunitWidth*cellWidth, yUnitWidth*cellHeight, cellWidth, cellHeight);
						}
					}
				}
			}
		}

		for(Animation a: gmap.getContainedAnimations()){
			a.draw(graphicsObject,cellWidth*a.getXCoordinate(),cellWidth*a.getYCoordinate(), (int)(cellWidth*a.getWidth()), (int)(cellHeight*a.getHeight()));
		}
	}

	private static Color[][] getLightingMap(GameMap gmap){
		int width = gmap.getWidth();
		int height = gmap.getHeight();
		Color[][] retMap = new Color[width][height];
		for(Light l: gmap.getContainedLights()){
			int rad = l.getLightRadius();
			int lxs = l.getLightPosition().x;
			int lys = l.getLightPosition().y;
			for(int x = lxs-rad; x < lxs+rad; x++){
				for(int y = lys-rad; y < lys+rad; y++){
					if(x >= 0 && x < width && y >= 0 && y < height){
						int distance = Distance.range(x, y, lxs, lys);
						if(distance - rad <= 0){
							Color existingColor = retMap[x][y];
							if(existingColor == null){
								Color newColor = new Color(0,0,0,distance/((float)rad));
								retMap[x][y] = newColor;
							}else{
								float alpha = (existingColor.getAlpha()/255f)*(distance/((float)rad));
								retMap[x][y] = new Color(0,0,0,alpha);
							}
						}

					}
				}
			}
		}
		return retMap;
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
