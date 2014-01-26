package entities;

import graphics.Light;

import java.awt.Point;
import java.util.ArrayList;

public abstract class Entity {

	private Point tileCoordinates = new Point();

	private int facingDirection = 2;

	private double movementVectorLength;//pixels

	private double speed = 10;//pixels/tick
	
	private static final double SQRT2 = Math.sqrt(2);
	
	protected int widthInTiles = 1;

	protected int heightInTiles = 1;
	
	private ArrayList<Light> ownedLightSources;

	public Entity(){
		this.ownedLightSources = new ArrayList<Light>();
	}
	
	public double getMovementVectorLength() {
		return movementVectorLength;
	}

	public void setMovementVectorLength(double movementVectorLength) {
		if(facingDirection%2 == 0){
		this.movementVectorLength = movementVectorLength;
		}else{
			this.movementVectorLength = movementVectorLength * SQRT2;
		}
	}


	public void tick(){

		//Move
		if(movementVectorLength-speed < 0){
			movementVectorLength = 0;
		}else{
			movementVectorLength -= speed;
		}
	}

	public int[] getTileCoordinates(){
		return new int[]{tileCoordinates.x,tileCoordinates.y};
	}
	
	public Point getTileCoordinatesPoint(){
		return this.tileCoordinates;
	}

	public void setTileCoordinates(int x, int y){
		this.setXTileCoordinate(x);
		this.setYTileCoordinate(y);
	}

	public void setXTileCoordinate(int x){
		tileCoordinates.x = x;
	}

	public void setYTileCoordinate(int y){
		tileCoordinates.y = y;
	}

	public int getXTileCoordinate(){
		return this.getTileCoordinates()[0];
	}

	public int getYTileCoordinate(){
		return this.getTileCoordinates()[1];
	}

	public int getFacingDirection(){
		return facingDirection;
	}

	public void setFacingDirection(int facingDirection){
		this.facingDirection = facingDirection;
	}

	public int getWidthInTiles() {
		return widthInTiles;
	}

	public void setWidthInTiles(int widthInTiles) {
		this.widthInTiles = widthInTiles;
	}

	public int getHeightInTiles() {
		return heightInTiles;
	}

	public void setHeightInTiles(int heightInTiles) {
		this.heightInTiles = heightInTiles;
	}
	
	public ArrayList<Light> getOwnedLights(){
		return this.ownedLightSources;
	}
	
	public void addLight(Light l){
		this.ownedLightSources.add(l);
	}
}
