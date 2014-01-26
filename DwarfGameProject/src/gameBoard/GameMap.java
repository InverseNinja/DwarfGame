package gameBoard;

import java.util.ArrayList;
import java.util.List;

import entities.Entity;
import graphics.Animation;
import graphics.Light;

public class GameMap {
	
	private Entity gameMap[][];
	
	/**
	 * This is the height of the map in cells or how many cells will be contained per column (up to down)
	 */
	private int height;
	
	/**
	 * This is the width of the map in cells or how many cells will be contained per row (left to right)
	 */
	private int width;

	private ArrayList<Entity> containedEntities;
	
	private ArrayList<Light> containedLightSources;
	
	private ArrayList<Animation> animations = new ArrayList<Animation>();
	
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
		containedLightSources = new ArrayList<Light>();
	}

	public void addLight(Light light){
		this.containedLightSources.add(light);
	}
	
	public void addAnimation(Animation a){
		this.animations.add(a);
		if(a instanceof Light){
			this.containedLightSources.add((Light)a);
		}
	}
	
	private void placeEntity(int x, int y, Entity entity){
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
		if(entity instanceof Light){
			containedLightSources.add((Light) entity);
		}
		if(entity.getOwnedLights().size()>0){
			for(Light l: entity.getOwnedLights()){
				containedLightSources.add(l);
			}
		}
	}
	
	public void addEntity(Entity entity){
		this.addEntity(entity.getXTileCoordinate(),entity.getYTileCoordinate(), entity);
	}
	
	public void removeEntity(int x, int y){
		containedEntities.remove(gameMap[x][y]);
		if(gameMap[x][y] instanceof Light){
			containedLightSources.remove((Light) gameMap[x][y]);
		}
		gameMap[x][y] = null;
	}
	
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

	public ArrayList<Animation> getContainedAnimations(){
		return animations;
	}
	
	public List<Light> getContainedLights() {
		return this.containedLightSources;
	}

}
