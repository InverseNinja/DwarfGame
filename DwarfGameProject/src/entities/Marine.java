package entities;

import Utilities.GameValues;
import main.Game;
import graphics.Animation;
import graphics.MarineSprite;

public class Marine extends Entity{

	private MarineSprite animation;
	
	public Marine(){
		this.heightInTiles = 2;
		this.widthInTiles = 2;
		this.speed = 1;
		this.animation = new MarineSprite();
		this.animation.pixelsPerTick = (int) this.speed;
		this.animation.timePerTick = (int) Game.TICK_RATE;
		this.animation.unitDistance = GameValues.GAME_TILE_WIDTH;
	}

	@Override
	public Animation getAnimation(){
		if(animation == null){
			animation = new MarineSprite();
		}
		return animation;
	}
	
	@Override
	public void setFacingDirection(int facingDirection){
		super.setFacingDirection(facingDirection);
	}
	
	@Override
	public void tick(){
		super.tick();
		this.animation.update();
		if(this.movementVectorLength == 0){
			this.animation.moving = false; 
		}else{
			this.animation.moving = true; 
		}
	}
}
