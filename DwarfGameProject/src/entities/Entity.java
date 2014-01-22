package entities;

public abstract class Entity {

	private int[] coordinates = new int[2];
	
	private int facingDirection = 2;
	
	public int[] getCoordinates(){
		return coordinates;
	}
	
	public void setCoordinates(int x, int y){
		this.setXCoordinate(x);
		this.setYCoordinate(y);
	}
	
	public void setXCoordinate(int x){
		coordinates[0] = x;
	}
	
	public void setYCoordinate(int y){
		coordinates[1] = y;
	}
	
	public int getXCoordinate(){
		return this.getCoordinates()[0];
	}
	
	public int getYCoordinate(){
		return this.getCoordinates()[1];
	}
	
	public int getFacingDirection(){
		return facingDirection;
	}
	
	public void setFacingDirection(int facingDirection){
		this.facingDirection = facingDirection;
	}
}
