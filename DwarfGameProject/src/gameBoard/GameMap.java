package gameBoard;

import java.util.ArrayList;
import java.util.List;

import entities.Entity;
import graphics.Animation;

public class GameMap {
	
	private Entity gameMap[][];
	
	private List<ArrayList<Entity>> sortedEntitiesByHeight = new ArrayList<ArrayList<Entity>>();
	
	/**
	 * This is the height of the map in cells or how many cells will be contained per column (up to down)
	 */
	private int height;
	
	/**
	 * This is the width of the map in cells or how many cells will be contained per row (left to right)
	 */
	private int width;

	private ArrayList<Entity> containedEntities;
	
	public ArrayList<Animation> animations = new ArrayList<Animation>();
	
	/**
	 * Creates a gameMap with the given dimensions
	 * @param width The width of the map in cells
	 * @param height The height of the map in cells
	 */
	public GameMap(int width, int height){
		this.width = width;
		this.height = height;
		gameMap = new Entity[width][height];
		containedEntities = new ArrayList<Entity>();
	}
	
//	public void addEntity(int x, int y, Entity entity){
//		entity.setTileCoordinates(x, y);
//		this.addEntity(entity);
//	}
	
	private void placeEntity(int x, int y, Entity entity){
		System.out.println("Placing entity at: "+x+", "+y);
		int width = entity.getWidthInTiles();
		int height = entity.getHeightInTiles();
		for(int i = 0; i < height; i++){
			for(int c = 0; c < width; c++){
				gameMap[c+x][y-i] = entity;
			}
		}
		entity.setTileCoordinates(x, y);
	}
	
	private boolean validPlacement(int x, int y, Entity entity){
		int width = entity.getWidthInTiles();
		int height = entity.getHeightInTiles();
		boolean validPlacement = true;
		for(int i = 0; i < height; i++){
			for(int c = 0; c < width; c++){
				
				if(gameMap[c+x][y-i] != null && gameMap[c+x][y-i] != entity){
					validPlacement = false;
					break;
				}
			}
		}
		return validPlacement;
	}
	
	public void addEntity(int x, int y, Entity entity){
		boolean validPlacement = this.validPlacement(x, y, entity);
		if(validPlacement){
			this.placeEntity(x, y, entity);
		}
		entity.setTileCoordinates(x, y);
		containedEntities.add(entity);
	}
	
	public void addEntity(Entity entity){
		this.addEntity(entity.getXTileCoordinate(),entity.getYTileCoordinate(), entity);
	}
	
	public void removeEntity(int x, int y){
		containedEntities.remove(gameMap[x][y]);
		gameMap[x][y] = null;
	}
	
//	public void moveEntity(int xStart, int yStart, int xEnd, int yEnd) throws Exception{
//		if(gameMap[xStart][yStart]==null){
//			throw new Exception("Tried to move entity at: ("+xStart+","+yStart+") but this location contained null.");
//		}
//		
//		gameMap[xEnd][yEnd] = gameMap[xStart][yStart];
//		gameMap[xStart][yStart] = null;
//		gameMap[xEnd][yEnd].setTileCoordinates(xEnd, yEnd);
//	}
	
//	public void moveEntity(Entity entity, int xEnd, int yEnd) throws Exception{
//		if(!containedEntities.contains(entity)){
//			throw new Exception("The entity: "+entity.toString()+" is not located on this map.");
//		}
//
//		if(gameMap[entity.getXTileCoordinate()][entity.getYTileCoordinate()] != entity){
//			throw new Exception("According to this map, a different entity at the given entities location ("+entity.getXTileCoordinate()+","+entity.getYTileCoordinate()+") already exists.");
//		}
//		
//		this.moveEntity(entity.getXTileCoordinate(), entity.getYTileCoordinate(), xEnd, yEnd);
//	}
	
	public void moveEntity(Entity entity, int xEnd, int yEnd) throws Exception{
		if(!containedEntities.contains(entity)){
			throw new Exception("The entity: "+entity.toString()+" is not located on this map.");
		}

		if(gameMap[entity.getXTileCoordinate()][entity.getYTileCoordinate()] != entity){
			throw new Exception("According to this map, a different entity at the given entities location ("+entity.getXTileCoordinate()+","+entity.getYTileCoordinate()+") already exists.");
		}
		
		if(this.validPlacement(xEnd, yEnd, entity)){
			//clean up the current occupied tiles.
			int width = entity.getWidthInTiles();
			int height = entity.getHeightInTiles();
			int x = entity.getXTileCoordinate();
			int y = entity.getYTileCoordinate();
			for(int i = 0; i < height; i++){
				for(int c = 0; c < width; c++){
					gameMap[c+x][y-i] = null;
				}
			}
			this.placeEntity(xEnd, yEnd, entity);
		}
	}

	public int getHeight() {
		return height;
	}


	public int getWidth() {
		return width;
	}
	
	public Entity getEntityAtLocation(int x, int y){
		return gameMap[x][y];
	}
	
	public ArrayList<Entity> getContainedEntities(){
		return containedEntities;
	}

}
