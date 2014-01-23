package entities;

public abstract class Entity {

	private int[] tileCoordinates = new int[2];

	private int facingDirection = 2;

	private double movementVectorLength;//pixels

	private double speed = 5;//pixels/tick
	
	private static final double SQRT2 = Math.sqrt(2);

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
		return tileCoordinates;
	}

	public void setTileCoordinates(int x, int y){
		this.setXTileCoordinate(x);
		this.setYTileCoordinate(y);
	}

	public void setXTileCoordinate(int x){
		tileCoordinates[0] = x;
	}

	public void setYTileCoordinate(int y){
		tileCoordinates[1] = y;
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
}
